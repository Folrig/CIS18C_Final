package cis18c_final;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MovelistQuery implements MenuItem {
    public MovelistQuery(Pokedex _pd, Scanner _input) {
        pd = _pd;
        input = _input;

        ml = pd.getMoves();
    }

    public void execute() {
        String s;
        ArrayList<Move> copy = new ArrayList<>();
        Move jockey = new Move();
        while (true) {
            System.out.print("Print moves by [n]ame, [p]ower, or [t]ype? :");
            s = input.nextLine();
            if (s.equals("t")) {
                System.out.print("What type? (Reminder, this is Gen I only so no Steel or Dark) ");
                s = input.nextLine();
                s = s.toUpperCase().substring(0, 1) + s.substring(1);
                Type type;
                try  {
                    type = Type.valueOf(s);
                } catch (IllegalArgumentException e) {
                    System.out.println("No such type " + s);
                    continue;
                }
                if (type == Type.None) {
                    System.out.println("You cannot search for that.");
                }
                else {
                    for (Move mv : ml.get(type.ordinal())) {
                        System.out.println(mv);
                    }
                    return;
                }
            }

            else if (s.equals("p")|| s.equals("n")) {
                linearize(copy);
                Collections.sort(copy, s.equals("p") ? jockey.BY_POWER : jockey.BY_NAME);
                for (Move mv : copy) {
                    System.out.println(mv);
                }
                return;
            }
            else {
                System.out.println("Invalid argument.");
            }
        }
    }

    private void linearize(ArrayList<Move> cpy) {
        for (ArrayList<Move> subl : ml) {
            cpy.addAll(subl);
        }
    }

    private final ArrayList<ArrayList<Move>> ml;
    private Pokedex pd;
    private Scanner input;
}
