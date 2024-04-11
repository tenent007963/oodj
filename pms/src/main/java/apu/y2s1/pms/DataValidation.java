/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.edu.apu.jp;

/**
 *
 * @author tenen
 */
public class DataValidation {
    // Function that check and sanitize input for general data
    public static String dettolXL(String input) {
        // Replace multiple spaces with a single space
        input = input.replaceAll("\\s+", " ");
        // Trim the string
        input = input.trim();
        // Replace "\\" with "\" and "\"" with "\""
        input = input.replace(";", "").replace("\"", "");
        return input;
    }

    // Function that checks and sanitizes a valid TP number
    public static String sanitizeTPNumber(String tpNumber) {
        // Check if it's a valid TP number
        if (tpNumber.matches("^TP0\\d{5}$")) {
            // Sanitize the TP number to be uppercase
            return tpNumber.toUpperCase();
        }
        return null;
    }

    // Function that checks and sanitizes a valid username
    public static String sanitizeUsername(String username) {
        // Remove symbols and special characters, lowercase all alphabets
        username = username.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        // Check if it meets the length requirements
        if (username.length() >= 3 && username.length() <= 20) {
            return username;
        }
        return null;
    }
}
