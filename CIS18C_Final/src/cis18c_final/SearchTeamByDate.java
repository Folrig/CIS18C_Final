package cis18c_final;

import java.util.ArrayList;
import java.util.Collections;

public class SearchTeamByDate implements MenuItem {
    public SearchTeamByDate(Pokedex _pd) {
        pd = _pd;
    }

    public void execute() {
        if (pd.getTeams().isEmpty()) {
            System.out.println("There are no teams.");
            return;

        }
        ArrayList<Team> copy = new ArrayList<>(pd.getTeams());
        Team temp = new Team();

        Collections.sort(copy, temp.BY_NAME);

        for (Team tm : copy) {
            System.out.println(tm);
        }


    }

    private Pokedex pd;
}
