package cis18c_final;


import java.util.Scanner;

public class PokemonQuery implements MenuItem {
    public PokemonQuery(Pokedex _pd) {
        pd = _pd;
    }

    public void execute() {
        Scanner input = new Scanner(System.in);
        Integer id;
        Pokemon pokemon;
        while (true) {
            System.out.print("Enter a Pokemon's name or id: ");
            String p = input.nextLine();
            id = Integer.getInteger(p);


            if (id != null)
                pokemon = pd.getPokemon(id);
            else {
                try {
                    pokemon = pd.getPokemon(p);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("ID from 1-151 only!");
                    continue;
                }
            }
            if (pokemon == null) {
                System.out.println("We don't have a " + p + " here.");
                continue;
            }
        }




    }

    private Pokedex pd;
}
