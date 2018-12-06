package cis18c_final;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SearchTeamByName implements MenuItem {
    public SearchTeamByName(Pokedex _pd, Scanner _input) {
        pd = _pd;
        input = _input;
    }
    public void execute() {
        if (pd.getTeams().isEmpty()) {
            System.out.println("There are no teams.");
            return;

        }
        ArrayList<Team> copy = new ArrayList<>(pd.getTeams());
        String name;

        System.out.print("Enter a name: ");
        name = input.nextLine();

        Team temp = new Team(name);

        Collections.sort(copy, temp.BY_NAME);
        int ret = Collections.binarySearch(copy, new Team(name), temp.BY_NAME);
        if (ret >= 0)
            System.out.println(copy.get(ret));
    }

    private Pokedex pd;
    private Scanner input;

}
