package cis18c_final;

import java.util.ArrayList;

public class TeamlistQuery implements MenuItem {
    public TeamlistQuery(Pokedex _pd) {
        pd = _pd;
    }

    public void execute() {
        ArrayList<Team> teams = pd.getTeams();

        if (teams.isEmpty()) {
            System.out.println("No teams loaded!");
            return;
        }

        for (Team t : teams) {
            System.out.println(t);
        }
    }

    private Pokedex pd;
}
