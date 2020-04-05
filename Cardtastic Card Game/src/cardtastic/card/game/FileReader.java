/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardtastic.card.game;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author NickAbel
 */
public class FileReader {

    private String fileName;
    private File myFile;
    private Scanner fileIn;

    public FileReader(String fileName) {
        this.fileName = fileName;

        try {
            // Creates an File object 
            myFile = new File(fileName);
            if (myFile.createNewFile()) {
                newFile(fileName);
                System.out.println("File created: " + myFile.getName());
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

    }

    public ArrayList<String> readFile() {
        ArrayList<String> contents = new ArrayList();

        try {
            fileIn = new Scanner(myFile);
        } catch (Exception e) {
            // Will print any exception to the console
            System.out.println(e.getMessage());
        }

        String line;

        while (fileIn.hasNext()) {
            // Iterates through the file line by line. Adds each whole line to the array.
            line = fileIn.nextLine();
            contents.add(line);
        }

        // Close file to avoid potential corruption.
        fileIn.close();

        return contents;
    }

    public void writeFile(ArrayList<String> writeMe) {

        // Converts writeMe Array to a String seperated by a new line character
        String arrayToString = String.join("\n", writeMe);

        try {
            FileWriter myWriter = new FileWriter(this.fileName);
            myWriter.write(arrayToString);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        
    }
    
    private void newFile(String fileName) {
        if (fileName.equals("Settings.txt")) {
            ArrayList<String> settingsInfo = readFile();
        
            if (settingsInfo.isEmpty()) {
                // If the settings file is empty, adds default settings for card and window backs
                settingsInfo.add(0, "CardBack_Red.png");
                settingsInfo.add(1, "-fx-background-color: ForestGreen");
            }
            writeFile(settingsInfo);
        } // end if Settings.txt
    }

}
