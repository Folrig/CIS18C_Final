package cis18c_final;

import java.util.Comparator;

public class Move {
/*    Absorb
      Grass
        20
        100
        25
*/
    public static class ByName implements Comparator<Move> {
        @Override
        public int compare(Move t, Move t1) {
            return t.name.compareTo(t1.name);
        }
    }

    public static class ByPower implements Comparator<Move> {
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
    public static class ByAccuracy implements Comparator<Move>{
        @Override
        public int compare(Move t, Move t1){
            if (t.accuracy < t1.accuracy)
                return -1;
            else if (t.accuracy == t1.accuracy)
                return 0;
            else
                return 1;
        }
    }
    public static class ByBasePowerPoints implements Comparator<Move>{
        @Override
        public int compare(Move t, Move t1) {
            if (t.basepp < t1.basepp)
                return -1;
            else if (t.basepp == t1.basepp)
                return 0;
            else
                return 1;
        }
    }

    public Move(String _name, Type _type, int _basepower, int _accuracy, int _basepp) {
        name = _name;
        type = _type;
        basepower = _basepower;
        accuracy = _accuracy;
        basepp = _basepp;
    }

    public String toString() {
        StringBuilder movStr = new StringBuilder();
        movStr. = "Name: ";
        movStr += name;
        movStr += " Type: ";
        movStr += type;
        movStr += " Base Power: ";
        movStr += basepower;
        movStr += " Accuracy ";
        movStr += accuracy;
        movStr += " Base Power Point: ";
        movStr += basepp;
        movStr.append("Name: ").append(movStr.name).append(" Type: ");
        movStr.append(movStr.type).append(" Base Power: ").append(movStr.basepower)
        return movStr;
    }

    public String getName() {
        return name;
    }

    private final String name;
    private final Type type;
    private final int basepower, accuracy, basepp;
}
