package cis18c_final;

import java.util.ArrayList;

public class Pokemon {

    public Pokemon() {
        id = 0;
        name = "";
        type1 = null;
        type2 = null;
        evolutions = new ArrayList<>();
    }

    public Pokemon(int _id, String _name, Type _type1, Type _type2,
                    ArrayList<Pokemon> _evolutions) {
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

    public Type[] getTypes() {
        return new Type[]{type1, type2};

    }

    public ArrayList<Pokemon> getEvolutions() {
        return evolutions;
    }

    public void setId(int _id) {
        id = _id;
    }

    public void setName(String _name) {
        name = _name;
    }

    public void setType1(Type _type1) {
        type1 = _type1;
    }

    public void setType2(Type _type2) {
        type2 = _type2;
    }

    public void setEvolutions(ArrayList<Pokemon> _evolutions) {
        evolutions = _evolutions;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("\n");
        sb.append(name).append("\n");
        sb.append(type1.name()).append("\n");
        sb.append(type2.name()).append("\n");

        for (Pokemon p : evolutions) {
            sb.append(p.getName()).append(" ");
        }

        String s = sb.toString();
        return s.substring(0, s.length() - 1);
    }

    private int id;
    private String name;
    private Type type1, type2;
    private ArrayList<Pokemon> evolutions;
}
