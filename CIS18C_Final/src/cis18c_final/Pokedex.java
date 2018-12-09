package cis18c_final;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Pokedex {

    public Pokedex(String data) {
        translate = new HashMap<>();
        teams = new ArrayList<>();
        fullpokedex = new ArrayList<>(151);
        moveHashMap = new HashMap<>();
        movelist = new ArrayList<>();
        root = data;
        for (int i = 0; i < Type.values().length; ++i) {
            movelist.add(new ArrayList<>());
        }

        for (int i = 0; i < 151; ++i) {
            fullpokedex.add(new Pokemon());
        }

        initdex();
        initmoves();
        initteams();
    }

    private Integer getIdFromName(String name){
        return translate.get(name);
    }

    public Pokemon getPokemon(int id) {
        if (id > fullpokedex.size())
            return null;
        return fullpokedex.get(id - 1);
    }
    
    public Pokemon getPokemon(String name) {
        Integer id = getIdFromName(name);
        if (id == null)
            return null;
        return fullpokedex.get(id - 1);
    }

    public ArrayList<ArrayList<Move>> getMoves() {
        return movelist;
    }

    public Move getMove(String name) {
        return moveHashMap.get(name);
    }

    public Path getRoot() {
        return Paths.get(root);
    }

    public void deleteTeam(String name) {
        Team temp = new Team(name);
        teams.remove(Collections.binarySearch(teams, temp, temp.BY_NAME));
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }
    /* utility functions */
    /* init pokedex and translation hashmap */

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
            System.out.println("Error: " + e);
        }

        return input;
    }

    private void initdex() {
        int i;
        ArrayList<String> input;
        /* working directory is currently at the root of the git repository */
        /* this assumes that the format is correct and consistent */
        for (i = 0; i < 151; ++i) {
            input = read(root + (i + 1));

            ArrayList<Pokemon> evolutions = new ArrayList<>();

            int id = Integer.parseInt(input.get(0));
            String name = input.get(1);
            Type t1 = Type.valueOf(input.get(2));
            Type t2 = input.get(3).length() == 0 ? Type.None : Type.valueOf(input.get(3));

            translate.put(name.toLowerCase(), id);

            for (int j = 4; j < input.size(); ++j) {
                /* branched evolution?  (eevee)*/
                String[] branched_evolutions;
                if ((branched_evolutions = input.get(j).split(" ")).length > 1) {
                    for (String branched_evolution : branched_evolutions) {
                        evolutions.add(getPokemon(Integer.parseInt(branched_evolution)));
                    }
                }
                else {
                    evolutions.add(getPokemon(Integer.parseInt(input.get(j))));
                }
            }

            Pokemon p = fullpokedex.get(i);
            p.setId(id);
            p.setName(name);
            p.setType1(t1);
            p.setType2(t2);
            p.setEvolutions(evolutions);

            input.clear();
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

            moveHashMap.put(input.get(i).toLowerCase(), mv);
        }

        Move jockey = new Move();
        for (ArrayList<Move> sublist : movelist) {
            Collections.sort(sublist, jockey.BY_NAME);
        }
    }

    private void initteams() {
        Path p = Paths.get(root);
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**.team");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(p)) {
            for (Path tm : stream) {
                if (matcher.matches(tm)) {
                    System.out.println(tm.toString());
                    ArrayList<String> input = read(tm.toString());

                    /* name */
                    String name = input.get(0);
                    String[] date = input.get(1).split(" ");
                    int year = Integer.parseInt(date[0]);
                    int month = Integer.parseInt(date[1]);
                    int day = Integer.parseInt(date[2]);
                    ArrayList<CustomPokemon> mon = new ArrayList<>();

                    for (int i = 2; i < input.size(); i = i + 5) {
                        System.out.println(i);
                        Pokemon poke;
                        ArrayList<Move> moves = new ArrayList<>();
                        poke = getPokemon(Integer.parseInt(input.get(i)));
                        for (int j = i; j < i + 4; ++j)
                            moves.add(moveHashMap.get(input.get(j)));
                        mon.add(new CustomPokemon(poke, moves));
                    }

                    teams.add(new Team(name, year, month, day, mon));
                }
            }
        } catch (IOException e) {
            System.out.println("Exception in initteams(): " + p.toAbsolutePath());
        }

    }
    
    public void addTeam(Team t)
    {
        teams.add(t);
    }

    private final String root;
    private final HashMap<String, Integer> translate;
    private final HashMap<String, Move> moveHashMap;
    private final ArrayList<Team> teams;
    private final ArrayList<Pokemon> fullpokedex;
    private final ArrayList<ArrayList<Move>> movelist;
}
