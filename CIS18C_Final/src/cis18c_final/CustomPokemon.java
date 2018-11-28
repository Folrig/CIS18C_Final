package cis18c_final;

import java.util.ArrayList;

public class CustomPokemon extends Pokemon {
    public CustomPokemon(ArrayList<Move> _movelist) {
        movelist = _movelist;
    }

    public CustomPokemon(Pokemon p, ArrayList<Move> _movelist) {
        super(p);
        movelist = _movelist;
    }

    public ArrayList<Move> getMoves() {
        return movelist;
    }

    private ArrayList<Move> movelist;
    public String toString() {
        String CP
    }
}
