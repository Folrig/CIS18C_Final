package cis18c_final;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Team {
    /* file output for name.team
        name
        YY MM DD
        Pokemon1.id all 4 moves.name (separated by space
        Pokemon2.id etc..

     */

    public Team(String _name, int _year, int _month, int _day, ArrayList<CustomPokemon> _team) {
        name = _name;
        party = _team;
        year = _year;
        month = _month;
        day = _day;
    }
    public int write(String loc) {
        Calendar cal = Calendar.getInstance();
        loc = loc.toLowerCase();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(loc + suffix, true))) {
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
    private static final String suffix = ".team";
    private ArrayList<CustomPokemon> party;
    private String name;
    private final int year, month, day;

}
