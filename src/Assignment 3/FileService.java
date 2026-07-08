// Prakruti Jain

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    // stores all lines from the file after reading
    private List<String> allLines = new ArrayList<>();

    // reads the file and returns first 5 lines for preview
    public String readFile(String fileName) throws IOException {

        allLines.clear(); // clear old data

        // open and read the file line by line
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            allLines.add(line);
        }
        reader.close();

        // if file is empty, throw an error
        if (allLines.isEmpty()) {
            throw new IOException("File is empty!");
        }

        // build preview: header + first 5 lines
        StringBuilder preview = new StringBuilder();
        int linesToShow = Math.min(allLines.size(), 6); // header + 5 lines
        for (int i = 0; i < linesToShow; i++) {
            preview.append(allLines.get(i)).append("\n");
        }

        return preview.toString();
    }

    // parses first 3 columns and writes to new file
    public String writeFile(String outputFileName) throws IOException {

        // make sure readFile was called first
        if (allLines.isEmpty()) {
            throw new IOException("No data to write. Please read a file first!");
        }

        // write to new file
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
        List<String> writtenLines = new ArrayList<>();

        for (String line : allLines) {
            // split by comma
            String[] columns = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            if (columns.length >= 3) {
                // take first 3 columns only
                String newLine = columns[0] + "," + columns[1] + "," + columns[2];
                writer.write(newLine);
                writer.newLine();
                writtenLines.add(newLine);
            } else {
                // row has fewer than 3 columns - skip it
                System.out.println("Skipping row with fewer than 3 columns: " + line);
            }
        }
        writer.close();

        // build preview of new file: header + first 5 lines
        StringBuilder preview = new StringBuilder();
        int linesToShow = Math.min(writtenLines.size(), 6);
        for (int i = 0; i < linesToShow; i++) {
            preview.append(writtenLines.get(i)).append("\n");
        }

        return preview.toString();
    }
}