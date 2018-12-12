package cis18c_final;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DeleteTeam implements MenuItem {
    public DeleteTeam(Pokedex _pd, Scanner _input) {
        pd = _pd;
        input = _input;
    }

    public void execute() {
        if (pd.getTeams().isEmpty()) {
            System.out.println("There are no teams.");
            return;
        }

        Path path = pd.getRoot();
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**.{team}");
        ArrayList<String> names = new ArrayList<>();
        String deed;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path tm : stream) {
                if (matcher.matches(tm)) {
                    String name = tm.getFileName().toString();
                    System.out.println(name);
                    name = name.substring(0, name.lastIndexOf("."));
                    names.add(name);
                }
            }

        } catch (IOException e) {
            System.out.println("Exception in MovelistQuery.execute(): " + path.toAbsolutePath());
        }

        while (true) {
            System.out.println("Here are the teams located in " + path.toAbsolutePath() + ":");
            for (String n : names) {
                System.out.println(n);
            }
            System.out.print("Enter the name to delete (don't include the .team extension): ");
            deed = input.nextLine();
            Path marked = Paths.get(pd.getRoot().toString() + "/" + deed + ".team");
            try {
                Files.delete(marked);
            } catch (NoSuchFileException x) {
                System.out.println("There's no file called " + deed + ".team");
                continue;
            } catch (DirectoryNotEmptyException x) {
                System.out.println("You tried to delete a directory.");
                continue;
            } catch (IOException x) {
                // File permission problems are caught here.
                System.out.println("No permissions to delete the file! Exiting menu item.");
                return;
            }

            pd.deleteTeam(deed);
            System.out.printf("Team successfully deleted.%n%n");
            return;
        }
    }

    private Pokedex pd;
    private Scanner input;
}
