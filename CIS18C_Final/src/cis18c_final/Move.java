package cis18c_final;

public class Move {
/*    Absorb
      Grass
        20
        100
        25
*/
    public Move(String _name, String _type, int _basepower, int _accuracy, int _basepp) {
        name = _name;
        type = _type;
        basepower = _basepower;
        accuracy = _accuracy;
        basepp = _basepp;
    }

    private final String name, type;
    private final int basepower, accuracy, basepp;
}
