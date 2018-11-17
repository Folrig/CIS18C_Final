package cis18c_final;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Pokedex {
    private HashMap<String, Integer> translate;
    private ArrayList<Team> teams;
    private ArrayList<Pokemon> fullpokedex;

    public Pokedex() {
        initdex();
    }
    /* utility functions */
    /* init pokedex */
    private ArrayList<Pokemon> initdex() {
        ArrayList<Pokemon> ret = new ArrayList<>();
        String str;
        int i;
        /* working directory is currently at the root of the git repository */
        /* this assumes that the format is correct and consistent */
        for (i = 1; i <= 151; ++i) {
            Path file = Paths.get(String.format("CIS18C_Final/src/cis18c_final/data/%d", i)).toAbsolutePath();
            ArrayList<String> input = new ArrayList<>();
            try {
                BufferedReader reader = Files.newBufferedReader(file);
                while ((str = reader.readLine()) != null) {
                    input.add(str);
                }
            } catch (IOException e) {
                System.out.println("Could not read file:" + file);
            }

            ArrayList<Integer> evolutions = new ArrayList<>();

            Integer id = Integer.parseInt(input.get(0));
            String name = input.get(1);
            String type1 = input.get(2);
            String type2 = input.get(3);

            translate.put(name, id);

            for (i = 4; i < input.size() - 1; ++i) {
                /* branched evolution?  (eevee)*/
                String[] branched_evolutions;
                if ((branched_evolutions = input.get(i).split(" ")).length > 1) {
                    for (int j = 0; j < branched_evolutions.length; ++j) {
                        evolutions.add(Integer.parseInt(branched_evolutions[j]));
                    }
                }
                else {
                    evolutions.add(Integer.parseInt(input.get(i)));
                }
            }
        }

        return ret;
    }

}
