package edu.gatech;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scratchpad {

    public Scratchpad() {
    }

    public List<String[]> uploadFileContents(String inputFileName) {
//        System.out.println(inputFileName);

        // Input file which needs to be parsed
        String fileToParse = inputFileName;
        BufferedReader fileReader = null;
        List<String[]> tokenGrid = new ArrayList<String[]>();

        // Delimiter used in CSV file
        final String DELIMITER = ",";
        try {
            String line = "";
            // Create the file reader
            fileReader = new BufferedReader(new FileReader(fileToParse));


            // Read the file line by line
            while ((line = fileReader.readLine()) != null) {
                // Get all tokens available in line
                String[] tokens = line.split(DELIMITER);
                tokenGrid.add(tokens);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tokenGrid;
    }

}
