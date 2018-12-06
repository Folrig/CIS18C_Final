package cis18c_final;

import java.util.Scanner;

public class MoveQuery implements MenuItem {
    public MoveQuery(Pokedex _pd, Scanner _input) {
        pd = _pd;
        input = _input;
    }
    public void execute() {
        String s;
        System.out.print("Enter the move's name: ");
        while (true) {
            s = input.nextLine();

            Move mv = pd.getMove(s.toLowerCase());
            if (mv == null) {
                System.out.println("We don't have move " + s);
            }
            else {
                System.out.println(mv.toString());
                return;
            }
        }
    }

    private Pokedex pd;
    private Scanner input;
}
