package cis18c_final;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class TeamCreate implements MenuItem {
    public TeamCreate(Pokedex _pd, Scanner _input) {
        pd = _pd;
        input = _input;
    }

    public void execute() {

        String name;
        String p;
        ArrayList<CustomPokemon> party = new ArrayList<>(6);
        Integer id;
        Pokemon pokemon;
        int i = 0;
        System.out.println("Enter your new team's name: ");
        name = input.nextLine();

        System.out.println("Please enter the team's 6 pokemon:");
        while (i != party.size()) {
            System.out.println("Enter Pokemon party member #" + (i + 1) + "\'s name or id.");
            p = input.nextLine();
            id = Integer.getInteger(p);
            if (id == null)
                pokemon = pd.getPokemon(p.toLowerCase());
            else {
                try {
                    pokemon = pd.getPokemon(id);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("ID from 1-151 only!");
                    continue;
                }
            }
            if (pokemon == null) {
                System.out.println("We don't have a " + p + " here.");
                continue;
            }

            ArrayList<Move> pokemonMoveList = new ArrayList<>(4);    //creates a new arraylist of moves per loop/pokemon
            int j = 0;
            while (j != pokemonMoveList.size()) {
                System.out.println("What is " + pokemon.getName() + "\'s #" + (j + 1) + " move?");
                while (true) {
                    System.out.print("Enter the move's name: ");
                    p = input.nextLine();

                    Move mv = pd.getMove(p.toLowerCase());
                    if (mv == null) {
                        System.out.println("We don't have move " + p);
                    } else {
                        pokemonMoveList.set(j, mv);
                        break;
                    }
                }
                ++j;
            }

            CustomPokemon cp = new CustomPokemon(pokemon, pokemonMoveList);  //adds the selected pokemon with it's selected moves.
            party.set(i, cp);
            ++i;
        }
        Calendar cal = Calendar.getInstance();

        Team newTeam = new Team(name, cal.YEAR, cal.MONTH, cal.DAY_OF_MONTH, party); //creates a new team
        pd.addTeam(newTeam);
        newTeam.write(pd.getRoot().toAbsolutePath().toString());//Adds the new team to the arraylist of teams in pokedex

    }
    private Pokedex pd;
    private Scanner input;

}
