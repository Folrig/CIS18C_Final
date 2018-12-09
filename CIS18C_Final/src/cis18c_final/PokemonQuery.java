package cis18c_final;


import java.util.Scanner;

public class PokemonQuery implements MenuItem {
    public PokemonQuery(Pokedex _pd, Scanner _input) {
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
        Integer id;
        Pokemon pokemon;
        while (true) {
            System.out.print("Enter a Pokemon's name or id: ");
            String p = input.nextLine();
            p = p.toLowerCase();

            if (!isInteger(p)) {
                pokemon = pd.getPokemon(p);
            }
            else {
                pokemon = pd.getPokemon(Integer.parseInt(p));
            }
            if (pokemon != null) {
                System.out.println(pokemon);
                return;
            }
            else
                System.out.println("We don't have  " + (isInteger(p) ? Integer.parseInt(p) : p) + "!");
        }
    }

    private final Pokedex pd;
    private final Scanner input;
}
