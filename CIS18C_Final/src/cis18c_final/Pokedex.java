package cis18c_final;

import java.io.BufferedReader;
import java.io.IOException;
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
        movelist = new ArrayList<>();

        initdex();
        initmoves();
    }

    private Integer getIdFromName(String name) {
        return translate.get(name);
    }

    public Pokemon getName(int id) {
        return fullpokedex.get(id);
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
            input = read(String.format("CIS18C_Final/src/cis18c_final/data/%d", i));
            input.clear();

            ArrayList<Integer> evolutions = new ArrayList<>();
            String name = input.get(1);

            translate.put(name, id);

            for (i = 4; i < input.size() - 1; ++i) {
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
            fullpokedex.add(new Pokemon(Integer.parseInt(input.get(0)), name, input.get(2), input.get(3), evolutions));
        }
    }

    private void initmoves() {
        int i;

        ArrayList<String> input = read("CIS18C_Final/src/cis18c_final/data/movelist");

        /* name, type, power, accuracy, pp */
        for (i = 0; i < input.size() - 1; i += 5) {
            movelist.add(new Move(input.get(i), input.get(i + 1), Integer.parseInt(input.get(i + 2)),
                    Integer.parseInt(input.get(i + 3)),
                    Integer.parseInt(input.get(i + 4))));
        }
    }
    private final HashMap<String, Integer> translate;
    private final ArrayList<Team> teams;
    private final ArrayList<Pokemon> fullpokedex;
    private final ArrayList<Move> movelist;
}
