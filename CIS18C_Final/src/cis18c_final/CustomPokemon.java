package cis18c_final;

import java.util.ArrayList;

public class CustomPokemon extends Pokemon {
    private ArrayList<Move> movelist;

    public CustomPokemon(ArrayList<Move> movelist) {
        this.movelist = movelist;
    }
}
