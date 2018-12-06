package cis18c_final;


import java.util.ArrayList;
import java.util.Scanner;

public class TeamCreate implements MenuItem {
    public TeamCreate(Pokedex _pd, Scanner _input) {
        pd = _pd;
        input = _input;
    }

    public void execute() {

        String name;
        ArrayList<CustomPokemon> party;
        String[] teamPokemonNames = new String[6];
        int year = 0;
        int month = 0;
        int day = 0;

        System.out.println("Date:\nWhat is the four digit year?");
        try {
                year = input.nextInt();
            }
        catch (IllegalArgumentException e) {
            while(year == 0)
            {
                System.out.println("Please enter a valid 4-digit year");
                year = input.nextInt();
            }
        }
        System.out.println("What is the month in integer form(1=January, 12=December)");
        try {
                month = input.nextInt();
            }
        catch (IllegalArgumentException e) {
            while(month > 12 || month < 1)
            {
                System.out.println("Please enter a valid month from 1-12");
                month = input.nextInt();
            }
        }
        System.out.println("What day of the month is it (1-30)");
        day = input.nextInt();
        try {
                day = input.nextInt();
            }
        catch (IllegalArgumentException e) {
            while(day > 30 || day < 1)  //day is initialized at 0, so it will loop until the user enters a valid number
            {
                System.out.println("Please enter a valid day from 1-30");   //try catch block doesn't validate the lengths of each month
                day = input.nextInt();                                      //Someone could theoretically enter february 30th
            }
        }

        System.out.println("Enter your new team's name: ");
        name = input.nextLine();

        System.out.println("Please enter the team's 6 pokemon");
        for(int i = 0; i < 6; i++)
        {
            System.out.println("Name of pokemon " + (i + 1) + ": ");
            teamPokemonNames[i] = input.nextLine();
            //TODO: Access the pokemon hashmap and put the pokemon objects into the ArrayList CustomPokemon.
        }

    }

    private Pokedex pd;
    private Scanner input;
}
