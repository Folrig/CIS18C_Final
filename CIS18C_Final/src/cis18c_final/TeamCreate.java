package cis18c_final;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class TeamCreate implements MenuItem {
    public TeamCreate(Pokedex _pd, Scanner _input) {
        pd = _pd;
        input = _input;
    }

    private boolean isInteger(String s) {
        return isInteger(s,10);
    }

    private boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1)
                    return false;
                else
                    continue;
            }
            if (Character.digit(s.charAt(i),radix) < 0)
                return false;
        }
        return true;
    }

    public void execute() {

        String name;
        String p;
        ArrayList<CustomPokemon> party = new ArrayList<>(6);
        Pokemon pokemon;
        int i = 0;
        System.out.println("Enter your new team's name: ");
        name = input.nextLine();

        System.out.println("Please enter the team's 6 pokemon:");
        while (i < 6) {
            System.out.println("Enter Pokemon party member #" + (i + 1) + "\'s name or id.");
            p = input.nextLine();
            if (!isInteger(p)) {
                pokemon = pd.getPokemon(p.toLowerCase());
            }
            else {
                pokemon = pd.getPokemon(Integer.parseInt(p));
            }

            if (pokemon == null) {
                System.out.println("We don't have a " + p + " here.");
                continue;
            }

            ArrayList<Move> pokemonMoveList = new ArrayList<>(4);    //creates a new arraylist of moves per loop/pokemon
            int j = 0;
            while (j < 4) {
                System.out.println("What is " + pokemon.getName() + "\'s #" + (j + 1) + " move?");
                while (true) {
                    System.out.print("Enter the move's name: ");
                    p = input.nextLine();

                    Move mv = pd.getMove(p.toLowerCase());
                    if (mv == null) {
                        System.out.println("We don't have  " + (isInteger(p) ? Integer.parseInt(p) : p) + "!");
                    } else {
                        pokemonMoveList.add(mv);
                        break;
                    }
                }
                ++j;
            }

            CustomPokemon cp = new CustomPokemon(pokemon, pokemonMoveList);  //adds the selected pokemon with it's selected moves.
            party.add(cp);
            ++i;
        }
        Calendar cal = Calendar.getInstance();
        Team newTeam = new Team(name, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), party); //creates a new team
        pd.addTeam(newTeam);
        System.out.printf("Team stored at %s%n%n", pd.getRoot().toAbsolutePath().toString());
        newTeam.write(pd.getRoot().toAbsolutePath().toString() + "/");//Adds the new team to the arraylist of teams in pokedex
    }
    private Pokedex pd;
    private Scanner input;
}