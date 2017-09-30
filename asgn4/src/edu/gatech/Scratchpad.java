package edu.gatech;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Scratchpad {

    public Scratchpad() {
    }

    private void processFileContents(String inputFileName, String[] tokens) {
        //TODO REMOVE THESE PRINT STATEMENTS BEFORE SUBMISSION
        // display the contents (token) in the line selected for processing
        switch (inputFileName) {
            case "test/courses.csv":
                System.out.println(tokens[0] + ", " + tokens[1] + ", " + tokens[2]);
                break;
            case "test/instructors.csv":
                System.out.println(tokens[0] + ", " + tokens[1] + ", " + tokens[2] + ", " + tokens[3] + ", " + tokens[4]);
                break;
            case "test/listings.csv":
                System.out.println(tokens[0] + ", " + tokens[1]);
                break;
            case "test/prereqs.csv":
                System.out.println(tokens[0] + ", " + tokens[1]);
                break;
            case "test/programs.csv":
                System.out.println(tokens[0] + ", " + tokens[1]);
                break;
            case "test/records.csv":
                System.out.println(tokens[0] + ", " + tokens[1] + ", " + tokens[2] + ", " + tokens[3] + ", " + tokens[4]);
                break;
            case "test/requests.csv":
                System.out.println(tokens[0] + ", " + tokens[1]);
                break;
            case "test/students.csv":
                System.out.println(tokens[0] + ", " + tokens[1] + ", " + tokens[2] + ", " + tokens[3] + ", " + tokens[4]);
                break;
            default:
                System.out.println("# error: unknown input file name");
                break;
        }
    }

    public void uploadFileContents(String inputFileName) {
        //TODO REMOVE THESE PRINT STATEMENTS BEFORE SUBMISSION
        System.out.println(inputFileName);

        // Input file which needs to be parsed
        String fileToParse = inputFileName;
        BufferedReader fileReader = null;

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
                processFileContents(inputFileName, tokens);
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
    }

}
