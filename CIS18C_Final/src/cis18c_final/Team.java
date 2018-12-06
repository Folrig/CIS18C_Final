package cis18c_final;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class Team {

    /* file output for name.team
        name
        YY MM DD
        Pokemon1.id all 4 moves.name (separated by space
        Pokemon2.id etc..
    */

    public class ByName implements Comparator<Team> {
        @Override
        public int compare(Team lhs, Team rhs) {
            return lhs.name.compareTo(rhs.name);
        }
    }

    public class ByDate implements Comparator<Team> {
        @Override
        public int compare(Team lhs, Team rhs) {
            if (lhs.year < rhs.year) return -1;
            if (lhs.year > rhs.year) return 1;
            if (lhs.month < rhs.month) return -1;
            if (lhs.month > rhs.month) return 1;
            if (lhs.day < rhs.day) return -1;
            if (lhs.day > rhs.day) return 1;
            return 0;
        }

    }

    public Team(String _name) {
        BY_DATE = new ByDate();
        BY_NAME = new ByName();

        year = month = day = 0;
        name = _name;
    }
    public Team(String _name, int _year, int _month, int _day, ArrayList<CustomPokemon> _team) {
        name = _name;
        party = _team;
        year = _year;
        month = _month;
        day = _day;

        BY_DATE = new ByDate();
        BY_NAME = new ByName();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        sb.append(month).append('-').append(day).append('-').append(year).append("\n");
        for (CustomPokemon cp : party) {
            sb.append(cp.toString()).append("\n");
        }

        return sb.toString();
    }

    public int write(String loc) {
        Calendar cal = Calendar.getInstance();
        loc = loc.toLowerCase();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(loc + name + suffix, true))) {
            bw.write(name);
            bw.newLine();
            bw.write(Calendar.YEAR + " " + Calendar.MONTH + " " + Calendar.DAY_OF_MONTH);
            bw.newLine();
            for (CustomPokemon poke : party) {
                StringBuilder sb = new StringBuilder();
                sb.append(poke.getId());
                sb.append(" ");

                ArrayList<Move> moves = poke.getMoves();
                for (int i = 0; i < moves.size(); ++i) {
                    sb.append(moves.get(i).getName());
                    if (i != moves.size() - 1)
                        sb.append(" ");
                }
                bw.write(sb.toString());
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            return 0;
        }

        return 1;
    }
    
    public void addToTeam(CustomPokemon pokemon) {
        if (this.party.size() < 6) {
            this.party.add(pokemon);
        } else {
            System.out.println("Max team size reached. Please remove a Pokemon from this team first.");
        }
    }
    
    public void removeFromTeam(CustomPokemon pokemon) {
        ArrayList<Integer> toremove = new ArrayList<>();
        for (int i = 0; i < this.party.size(); i++) {
            if (this.party.get(i).getName().compareTo(pokemon.getName()) == 0){
                toremove.add(i);
            }
        }
        for (int i : toremove) {
            party.remove(i);
        }
    }

    private static final String suffix = ".team";
    private ArrayList<CustomPokemon> party;
    private String name;
    private final int year, month, day;
    public final Comparator<Team> BY_DATE;
    public final Comparator<Team> BY_NAME;
}
