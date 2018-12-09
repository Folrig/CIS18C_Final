package cis18c_final;

import java.util.Comparator;

public class Move {
/*    Absorb
      Grass
        20
        100
        25
*/
    private class ByName implements Comparator<Move> {
        @Override
        public int compare(Move t, Move t1) {
            return t.name.compareTo(t1.name);
        }
    }

    private class ByPower implements Comparator<Move> {
        @Override
        public int compare(Move t, Move t1) {
            if (t.basepower < t1.basepower)
                return -1;
            else if (t.basepower == t1.basepower)
                return 0;
            else
                return 1;
        }
    }
    public Move() {
        name = "";
        type = null;
        basepower = accuracy = basepp = 0;
        BY_NAME = new ByName();
        BY_POWER = new ByPower();
    }

    public Move(String _name, Type _type, int _basepower, int _accuracy, int _basepp) {
        name = _name;
        type = _type;
        basepower = _basepower;
        accuracy = _accuracy;
        basepp = _basepp;

        BY_NAME = new ByName();
        BY_POWER = new ByPower();
    }

    @Override
    public String toString() {
        StringBuilder movStr = new StringBuilder();
        movStr.append("Name: ").append(name).append("\n").append(" Type: ");
        movStr.append(type).append("\n").append(" Base Power: ").append(basepower == 0 ? "-" : basepower).append("\n");
        movStr.append(" Accuracy: ").append(accuracy == 0 ? "-" : accuracy).append("\n").append(" Base Power Points: ");
        movStr.append(basepp);
        return movStr.toString();
    }

    public String getName() {
        return name;
    }

    private final String name;
    private final Type type;
    private final int basepower, accuracy, basepp;
    public final Comparator<Move> BY_NAME;
    public final Comparator<Move> BY_POWER;
}
