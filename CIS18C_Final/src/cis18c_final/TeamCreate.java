package cis18c_final;

import java.util.ArrayList;
import java.util.Scanner;

public class TeamCreate implements MenuItem {
    public TeamCreate(Pokedex _pd, Scanner _input) {
        pd = _pd;
        input = _input;
    }

    public void execute() {
        
        Scanner input = new Scanner(System.in);
        String name;
        String p;
        ArrayList<CustomPokemon> party = new ArrayList<>();
        String[] teamPokemonNames = new String[6];
        int year = 0;
        int month = 0;
        int day = 0;
        String answer;
        boolean moreMoves = true;
        boolean pokemonFound = false;
        ArrayList<Move> pokemonMoveList;
        CustomPokemon cp;
        
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
        
        System.out.println("Please enter the team's 6 pokemon:");
        for(int i = 0; i < 6; i++)
        {
            System.out.println("Name of pokemon " + (i + 1) + ": ");
            p = input.nextLine();
            while(pokemonFound == false)
            {
                for(int j = 1; j < 151; j++)
                {
                    if(p.toLowerCase() == pd.getPokemon(j).getName().toLowerCase())
                    {
                        pokemonFound = true;
                    }
                    else if(j > 151 && pokemonFound == false) 
                    {
                        System.out.println("we do not have this pokemon. First generation only.  Try again: ");
                        p = input.nextLine();
                    }
                }
            }
            teamPokemonNames[i] = p.toLowerCase();
            String s;
            pokemonMoveList = new ArrayList<Move>();    //creates a new arraylist of moves per loop/pokemon  
            while (moreMoves == true) 
            {
                System.out.print("Enter a move you want the pokemon to have: ");
                s = input.nextLine();

                Move mv = pd.getMove(s.toLowerCase());
                if (mv == null) 
                {
                    System.out.println("We don't have move " + s);
                }
                else {
                    pokemonMoveList.add(mv);
                    System.out.println("Move added to pokemon's repertoire");
                    System.out.print("Do you want to add another move? y/n: ");
                    answer = input.nextLine();
                    while(answer.toLowerCase() != "y" || answer.toLowerCase() != "n")
                    {
                        if(answer.toLowerCase() == "y")
                        {
                            moreMoves = true;
                        }
                        else if(answer.toLowerCase() == "n")
                        {
                            moreMoves = false;
                        }
                        else
                            System.out.print("Please enter either a 'y' or an 'n': ");
                            answer = input.nextLine();
                    }
                }
            }
            cp = new CustomPokemon(pd.getPokemon(teamPokemonNames[i]), pokemonMoveList);  //adds the selected pokemon with it's selected moves.
            party.add(cp);
        }
        Team newTeam = new Team(name, year, month, day, party); //creates a new team
        pd.addTeam(newTeam);                                    //Adds the new team to the arraylist of teams in pokedex
    }
    private Pokedex pd;
    private Scanner input;
}
