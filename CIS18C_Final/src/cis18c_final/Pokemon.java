package cis18c_final;

import java.util.ArrayList;

public class Pokemon {
    /* TODO: handle branched evolutions like Eevee */
    public Pokemon() {
        id = 0;
        name = type1 = type2 = "";
        evolutions = new ArrayList<>();
    }

    public Pokemon(int _id, String _name, String _type1, String _type2,
                    ArrayList<Integer> _evolutions) {
        id = _id;
        name = _name;
        type1 = _type1;
        type2 = _type2;
        evolutions = _evolutions;
    }

    public Pokemon(Pokemon p) {
        id = p.id;
        name = p.name;
        type1 = p.type1;
        type2 = p.type2;
        evolutions = p.evolutions;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getTypes() {
        return new String[]{type1, type2};

    }

    public ArrayList<Integer> getEvolutions() {
        return evolutions;
    }

    private final int id;
    private final String name;
    private final String type1, type2;
    private final ArrayList<Integer> evolutions;
}
