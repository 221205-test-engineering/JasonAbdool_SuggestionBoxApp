package com.abdool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utility {

    public void persist(String suggestion, String priority) throws IOException {
        // Read the file, messages.txt, and append the new data to the end
        File file = new File("characters.txt");

        FileWriter fw = new FileWriter(file, true); // The "true" parameter specifies that we would
        // like to append to the file instead of overwriting it
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(suggestion + ", " + priority); // tomato,10
        bw.newLine(); // Add a new line after the entry

        bw.close();
        fw.close();
    }

    public String[][] getAllEntries() throws IOException {
        String[] entries = Files.readString(Paths.get("characters.txt")).split("\n");

        int length = entries.length;

        String[][] splitEntries = new String[length][2];

        for(int i = 0; i < length; i++) {
            splitEntries[i] = entries[i].split(",");
        }

        return splitEntries;
    }

}
