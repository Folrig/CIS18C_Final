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

/*
    public Pokemon(int _id, String _name, Type _type1, Type _type2,
                    ArrayList<Pokemon> _evolutions) {
        id = _id;
        name = _name;
        type1 = _type1;
        type2 = _type2;
        evolutions = _evolutions;
    }
*/

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
        sb.append("ID: ").append(id).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Type 1: ").append(type1.name()).append("\n");
        sb.append("Type 2: ").append(type2.name()).append("\n").append("Evolutions: ");

        if (id != 133) {
            for (Pokemon p : evolutions) {
                sb.append(p.getName()).append(" -> ");
            }
            sb.delete(sb.length() - 3, sb.length() - 1);
        }
        else {
            sb.append(evolutions.get(0).getName()).append(" -> ");
            sb.append("{");
            for (int j = 1; j < evolutions.size(); ++j) {
                sb.append(evolutions.get(j).getName()).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length() - 1);
            sb.append("}");
        }


        String s = sb.toString();
        return s;
    }

    private int id;
    private String name;
    private Type type1, type2;
    private ArrayList<Pokemon> evolutions;
}
