package cis18c_final;


import java.util.Scanner;

public class PokemonQuery implements MenuItem {
    public PokemonQuery(Pokedex _pd, Scanner _input) {
        pd = _pd;
        input = _input;
    }

    public void execute() {
        Integer id;
        Pokemon pokemon;
        while (true) {
            System.out.print("Enter a Pokemon's name or id: ");
            String p = input.nextLine();
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
            if (pokemon != null) {
                System.out.println(pokemon);
                return;
            }
            else {
                System.out.println("We don't have a " + p + " here.");
            }
        }
    }

    private final Pokedex pd;
    private final Scanner input;
}
