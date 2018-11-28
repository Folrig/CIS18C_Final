package cis18c_final;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Pokedex {

    public Pokedex() {
        translate = new HashMap<>();
        teams = new ArrayList<>();
        fullpokedex = new ArrayList<>();
        moveHashMap = new HashMap<>();
        movelist = new ArrayList<>();

        for (int i = 0; i < Type.values().length; ++i) {
            movelist.add(new ArrayList<>());
        }

        initdex();
        initmoves();
        initteams();
    }

    private Integer getIdFromName(String name) {
        return translate.get(name);
    }

    public Pokemon getPokemon(int id) {
        return fullpokedex.get(id);
    }
    
    public Pokemon getPokemon(String name) {
        return fullpokedex.get(getIdFromName(name));
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }
    /* utility functions */
    /* init pokedex and translation hashmap */
    /* TODO: possibly move the file reading on its own function */

    private ArrayList<String> read(String dir)  {
        Path file = Paths.get(dir);
        String str;
        ArrayList<String> input = new ArrayList<>();
        try {
            BufferedReader reader = Files.newBufferedReader(file);
            while ((str = reader.readLine()) != null) {
                input.add(str);
            }
        } catch (IOException e) {
            System.out.println("Could not read file: " + file.toAbsolutePath());
        }

        return input;
    }

    private void initdex() {
        String str;
        int i;
        ArrayList<String> input;
        /* working directory is currently at the root of the git repository */
        /* this assumes that the format is correct and consistent */
        for (i = 1; i <= 151; ++i) {
            input = read(String.format(root + "%d", i));
            input.clear();

            ArrayList<Integer> evolutions = new ArrayList<>();
            int id = Integer.parseInt(input.get(0));
            String name = input.get(1);

            translate.put(name, id);

            for (i = 4; i < input.size(); ++i) {
                /* branched evolution?  (eevee)*/
                String[] branched_evolutions;
                if ((branched_evolutions = input.get(i).split(" ")).length > 1) {
                    for (String branched_evolution : branched_evolutions) {
                        evolutions.add(Integer.parseInt(branched_evolution));
                    }
                } else {
                    evolutions.add(Integer.parseInt(input.get(i)));
                }
            }
            fullpokedex.add(new Pokemon(id, name, Type.valueOf(input.get(2)), Type.valueOf(input.get(3)), evolutions));
        }
    }

    private void initmoves() {
        int i;

        ArrayList<String> input = read(root + "movelist");

        /* name, type, power, accuracy, pp */
        for (i = 0; i < input.size(); i += 5) {
            Type type = Type.valueOf(input.get(i + 1));
            Move mv = new Move(input.get(i), type, Integer.parseInt(input.get(i + 2)),
                    Integer.parseInt(input.get(i + 3)),
                    Integer.parseInt(input.get(i + 4)));
            ArrayList<Move> sublist = movelist.get(type.ordinal());

            sublist.add(mv);

            moveHashMap.put(input.get(i), mv);
        }
    }

    private void initteams() {
        Path p = Paths.get(root);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(p)) {
            for (Path tm : stream) {
                ArrayList<String> input = read(tm.toString());

                /* name */
                String name = input.get(0);
                String[] date = input.get(1).split(" ");
                int year = Integer.parseInt(date[0]);
                int month = Integer.parseInt(date[1]);
                int day = Integer.parseInt(date[2]);
                ArrayList<CustomPokemon> mon = new ArrayList<>();

                for (int i = 2; i < input.size(); ++i) {
                    Pokemon poke;
                    ArrayList<Move> moves = new ArrayList<>();
                    String[] line = input.get(i).split(" ");
                    poke = getPokemon(Integer.parseInt(line[0]));
                    for (int j = 1; j < line.length; ++j) {
                        moves.add(moveHashMap.get(line[j]));
                    }

                    mon.add(new CustomPokemon(poke, moves));
                }

                teams.add(new Team(name, year, month, day, mon));


            }
        } catch (IOException e) {
            System.out.println("Exception in initteams(): " + p.toAbsolutePath());
        }

    }

    private static final String root = "CIS18C_Final/src/cis18c_final/data/";
    private final HashMap<String, Integer> translate;
    private final HashMap<String, Move> moveHashMap;
    private final ArrayList<Team> teams;
    private final ArrayList<Pokemon> fullpokedex;
    private final ArrayList<ArrayList<Move>> movelist;
}
