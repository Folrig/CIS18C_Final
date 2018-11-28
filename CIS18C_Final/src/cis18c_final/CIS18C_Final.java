/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis18c_final;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author solac
 */
public class CIS18C_Final {
    /* todo: move to a separate class? */
    public static void printMenu() {
        System.out.println("1. Query a Pokémon");
        System.out.println("2. Query a Pokémon Move");
        System.out.println("3. Query all moves");
        System.out.println("4. Create a custom team");
        System.out.println("5. Query all your custom teams");
        System.out.println("6. Search for a custom team (by date)");
        System.out.println("7. Search for a custom team (by name)");
        System.out.println("8. Delete a custom team");
        System.out.println("9. Quit the Pokedex");
    }

    public static void printMoveMenu() {
        System.out.println("1. Query moves by type.");
        System.out.println("2. Query moves by power.");
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Integer menuChoice = 0;
        Pokedex pokedex = new Pokedex();
        
        do {
            System.out.println("Welcome to the Pokedex. Please select a menu option.");
            printMenu();
            menuChoice = input.nextInt();
        } while(menuChoice != 9);
        
        
    }
}
