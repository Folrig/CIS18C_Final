package cis18c_final;

import java.util.Scanner;

public class TeamCreate implements MenuItem {
    public TeamCreate(Pokedex _pd, Scanner _input) {
        pd = _pd;
        input = _input;
    }

    public void execute() {

    }

    private Pokedex pd;
    private Scanner input;
}
