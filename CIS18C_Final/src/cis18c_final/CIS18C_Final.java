/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis18c_final;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author solac
 */
public class CIS18C_Final {
    private static void printMenu() {
        System.out.println("1. Query a Pokémon");
        System.out.println("2. Query a Pokémon Move");
        System.out.println("3. Query all moves");
        System.out.println("4. Create a custom team");
        System.out.println("5. Query all your custom teams");
        System.out.println("6. Filter custom teams (by date)");
        System.out.println("7. Search for a custom team (by name)");
        System.out.println("8. Delete a custom team");
        System.out.println("9. Quit the Pokedex");
    }

    private static ArrayList<MenuItem> initMenu(Scanner input, Pokedex pd) {
        ArrayList<MenuItem> execMenu = new ArrayList<>();
        execMenu.add(new PokemonQuery(pd, input));
        execMenu.add(new MoveQuery(pd, input));
        execMenu.add(new MovelistQuery(pd, input));
        execMenu.add(new TeamCreate(pd, input));
        execMenu.add(new TeamlistQuery(pd));
        execMenu.add(new SearchTeamByDate(pd));
        execMenu.add(new SearchTeamByName(pd, input));
        execMenu.add(new DeleteTeam(pd, input));
        execMenu.add(new Exit());

        return execMenu;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("need a path to the data folder!");
            return;
        }
        
        Scanner input = new Scanner(System.in);
        int menuChoice;
        Pokedex pokedex = new Pokedex(args[0]);
        ArrayList<MenuItem> execMenu = initMenu(input, pokedex);

        System.out.println("Welcome to Generation I Pokedex");

        do {
            printMenu();
            System.out.print("Option: ");
            menuChoice = input.nextInt();
            input.nextLine();
            if (menuChoice < 1 || menuChoice > execMenu.size()) {
                System.out.print("Invalid choice.");
            }
            else {
                execMenu.get(menuChoice - 1).execute();
            }
        } while (menuChoice != execMenu.size());

    }
}
