/*
 * Reusing this class for easier text file I/O operations, customized to meet current requirements
 * To use this class, do:
 * import apu.y2s1.pms.DataAbstract;
 * at the beginning of the file, following a normal initialization of:
 * DataAbstract db = new DataAbstract(filename.txt);
 */
package apu.y2s1.pms;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tenen
 */
public class DataAbstract {
    String fileName; 
    String regEx;

    public DataAbstract(String arg, String ch) {
        this.fileName = arg;
        this.regEx = ch;
    }

    public DataAbstract(String arg) {
            this(arg, ";");
    }

    /*
     * getRow() requires an integer rowIndex(id) as an argument, and returns a String[] array that contains all elements in that row.
     */
    public String[] getRow(int rowIndex) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(this.fileName));
            List<String> rowData = new ArrayList<>();
            String line;
            int currentRow = 1;
            while ((line = reader.readLine()) != null) {
                if (currentRow == rowIndex) {
                    String[] values = line.split(this.regEx);
                    for (String value : values) {
                        rowData.add(value.trim());
                    }
                    break;
                }
                currentRow++;
            }
            // Convert the List<String> to a String[] array and return
            return rowData.toArray(new String[0]);
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null,"An error occurred. Please try again, or check your input");
            e.printStackTrace();
            return new String[0]; // Return an empty array in case of an error
        } finally {
            if (reader!= null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /*
     * getAllRows() returns a List<String[]> array that contains all rows in the file. Each element of this list is itself an array with all elements in that row.
     */
    public List<String[]> getAllRows() {
        List<String[]> allRows = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(this.fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] values = line.split(this.regEx);
                    for (int i = 0; i < values.length; i++) {
                        values[i] = values[i].trim();
                    }
                    allRows.add(values);
                }
            }
            return allRows;
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null,"An error occurred. Please try again, or check your input");
            e.printStackTrace();
            return new ArrayList<>(); // Return an empty collection in case of an error
        } finally {
            if (reader!= null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }

    /*
     * writeTo() requires a String[] array data as an argument, and writes the data to the end of the file.
     * Note: This method is the first try to have a positive double-check implemented, where the return result will be changed if file creation failed.
     */
    public boolean writeTo(String[] data) {
        File txt = new File(this.fileName);
        boolean created = true;
            if (!txt.exists()) {
                try {
                    created = txt.createNewFile();
                } catch (IOException e) {
                    javax.swing.JOptionPane.showMessageDialog(null,"File creation failed." + e.getMessage());
                    e.printStackTrace();
                    return false;
                }
            }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(txt, true)); ){
            String line = String.join(this.regEx, data);
            writer.write(line);
            writer.newLine();
            return created;
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null,"An error occurred while writing into file." + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /*
     * updateRow() requires an integer rowIndex(id) and a String[] array data as arguments, and updates the row with that index with the new data.
     */    
    public boolean updateRow(int rowIndex, String[] data) {
    try {
        // Read the existing content of the file
        List<String> lines = Files.readAllLines(Paths.get(this.fileName), StandardCharsets.UTF_8);

        // Update the specific row with new data
        if (rowIndex >= 0 && rowIndex < lines.size()) {
            lines.set(rowIndex, String.join(this.regEx, data));
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Invalid row index.");
            return false;
        }

        // Write the updated content back to the file
        Files.write(Paths.get(this.fileName), lines, StandardCharsets.UTF_8);

        return true;
    } catch (IOException e) {
        javax.swing.JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
    
    /*
     * getIndex() requires a string searchString as an argument, and returns the line number(id) where the string is found.
     */
    public int getIndex(String searchString) {
        try(BufferedReader reader = new BufferedReader(new FileReader(this.fileName))) {
            String line;
            int currentLine = 1;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchString)) {
                    return currentLine; // Return the line number where the string is found
                }
                currentLine++;
            }
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null,"An error occurred." + e.getMessage());
            e.printStackTrace();
        }
        return -1; // Return -1 if the string is not found
    }
    
    /* 
     * countLines() returns the number of lines in the file.
     */
    public int countLines() {
        int lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(this.fileName))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null,"An error occurred." + e.getMessage());
            e.printStackTrace();
            return 0;
        }
        return lineCount;
    }
    
    /*
     * deleteRow() requires an integer rowIndex(id) as an argument, and deletes the row with that index from the file.
     */
    public boolean deleteRow(int rowIndex) {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.fileName))) {
            List<String> lines = new ArrayList<>();
            String line;
            int currentRow = 0;
            while ((line = reader.readLine()) != null) {
                if (currentRow != rowIndex) {
                    lines.add(line);
                }
                currentRow++;
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName))){
                for (String updatedLine : lines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            } catch (IOException e) {
                javax.swing.JOptionPane.showMessageDialog(null,"An error occurred while writing file." + e.getMessage());
                e.printStackTrace();
                return false;
            }
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null,"An error occurred." + e.getMessage());
            e.printStackTrace();
            return false;
        } 
        return true;
    }
}