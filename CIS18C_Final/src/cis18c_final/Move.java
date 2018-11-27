package cis18c_final;

public class Move {
/*    Absorb
      Grass
        20
        100
        25
*/
    public Move(String _name, Type _type, int _basepower, int _accuracy, int _basepp) {
        name = _name;
        type = _type;
        basepower = _basepower;
        accuracy = _accuracy;
        basepp = _basepp;
    }

    public String getName() {
        return name;
    }

    private final String name;
    private final Type type;
    private final int basepower, accuracy, basepp;
}
