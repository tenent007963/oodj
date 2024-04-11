/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.edu.apu.jp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tenen
 */
public class DataAbstract {
    String fileName; 

    // Create a class constructor for the Main class
    public DataAbstract(String arg) {
      fileName = arg;
    }

    public String[] getRow(int rowIndex) {
        try {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(fileName));
            List<String> rowData = new ArrayList<>();
            String line;
            int currentRow = 1;
            while ((line = reader.readLine()) != null) {
                if (currentRow == rowIndex) {
                    String[] values = line.split(";");
                    for (String value : values) {
                        rowData.add(value.trim());
                    }
                    break;
                }
                currentRow++;
            }
            reader.close();
            // Convert the List<String> to a String[] array
            return rowData.toArray(new String[0]);
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null,"An error occurred. Please try again, or check your input");
            e.printStackTrace();
            return new String[0]; // Return an empty array in case of an error
        }
    }
    
    public List<String[]> getAllRows() {
        List<String[]> allRows = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] values = line.split(";");
                    for (int i = 0; i < values.length; i++) {
                        values[i] = values[i].trim();
                    }
                    allRows.add(values);
                }
            }
            reader.close();
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null,"An error occurred. Please try again, or check your input");
            e.printStackTrace();
            return null; // Return an empty array in case of an error
        }
        return allRows;
    }

    public boolean writeTo(String[] data) {
        try {
            File faru = new File(fileName);
            faru.createNewFile();
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(faru, true));
            String line = String.join(";", data);
            writer.write(line);
            writer.newLine();
            writer.close();
            return true;
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null,"An error occurred." + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateRow(int rowIndex, String[] data) {
        try {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(fileName));
            List<String> lines = new ArrayList<>();
            String line;
            int currentRow = 1;
            while ((line = reader.readLine()) != null) {
                if (currentRow == rowIndex) {
                    lines.add(String.join(";", data));
                } else {
                    lines.add(line);
                }
                currentRow++;
            }
            reader.close();

            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(fileName));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();
            return true;
        } catch (IOException e) {
            //System.err.println("An error occurred: " + e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null,"An error occurred." + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public int getIndex(String searchString) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.fileName));
            String line;
            int currentLine = 1;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchString)) {
                    reader.close();
                    return currentLine; // Return the line number where the string is found
                }
                currentLine++;
            }
            reader.close();
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null,"An error occurred." + e.getMessage());
            e.printStackTrace();
        }
        return -1; // Return -1 if the string is not found
    }
    
    public List<String[]> getOutstanding() {
        List<String[]> results = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");
                if (values.length >= 3) { 
                    String login = values[0];
                    String balanceStr = values[2];
                    try {
                        double balance = Double.parseDouble(balanceStr);
                        if (balance < 0) {
                            // Balance is less than 0, add Login and Balance to results
                            results.add(new String[]{login, balanceStr});
                        }
                    } catch (NumberFormatException e) {
                        // Handle parsing errors if needed
                        System.err.println("Error parsing balance: " + e.getMessage());
                        return new ArrayList();
                    }
                }
            }
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null,"An error occurred." + e.getMessage());
            return new ArrayList();
        }
        return results;
    }
    
    public int countLines(String fileName) {
        int lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
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
    
    public boolean deleteRow(int rowIndex) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            List<String> lines = new ArrayList<>();
            String line;
            int currentRow = 0;
            while ((line = reader.readLine()) != null) {
                if (currentRow != rowIndex) {
                    lines.add(line);
                }
                currentRow++;
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();
            return true;
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null,"An error occurred." + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}