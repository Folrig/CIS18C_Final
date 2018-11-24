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

/**
 *
 * @author solac
 */
public class CIS18C_Final {
    /* todo: move to a separate class? */
    public void printMenu() {
        System.out.println("1. Query a Pokémon");
        System.out.println("2. Query a Pokémon Move");
        System.out.println("3. Create a custom team");
        System.out.println("4. Query all your custom teams");
        System.out.println("5. Search for a custom team (by date)");
        System.out.println("6. Search for a custom team (by name)");
        System.out.println("7. Delete a custom team");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    }
    
}
