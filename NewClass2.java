/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment.pkgnew;

/**
 *
 * @author Lenovo02
 */
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Collections;

public class NewClass2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for matriculation number
        System.out.print("Enter matriculation number of the user (e.g., s100201): ");
        String targetUser = scanner.nextLine().trim();

        // Step 1: Read AcademicSubjects.txt into a list
        LinkedList<String> academicSubjects = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("AcademicSubjects.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                academicSubjects.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading academic subject.txt: " + e.getMessage());
            return;
        }

        // Step 2: Read userdata.txt and find the target user's subject codes
        LinkedList<String> userSubjectCodes = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("UserData.txt"))) {
            String line;
            boolean foundUser = false;
            StringBuilder userBlock = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) { // End of a user block
                    if (foundUser) {
                        break; // Exit if we found the target user
                    }
                    userBlock.setLength(0); // Reset for the next user block
                } else {
                    userBlock.append(line).append("\n");
                    if (userBlock.toString().contains(targetUser)) {
                        foundUser = true;
                        userSubjectCodes = extractSubjectCodes(userBlock.toString());
                    }
                }
            }

            if (userSubjectCodes == null) {
                System.out.println("User not found: " + targetUser);
                return;
            }
        } catch (IOException e) {
            System.out.println("Error reading userdata.txt: " + e.getMessage());
            return;
        }

        // Step 3: Match subject codes with names and sort by name
        LinkedList<String> sortedSubjects = new LinkedList<>();
        for (String code : userSubjectCodes) {
            for (String subjectLine : academicSubjects) {
                if (subjectLine.startsWith(code + ",")) {
                    String subjectName = subjectLine.split(",", 2)[1].trim();
                    String subjectEntry = code + ": " + subjectName;

                    // Insert subject into the list in sorted order by name
                    sortedSubjects.add(subjectEntry);
                }
            }
        }

        // Sort the subjects alphabetically by name
        Collections.sort(sortedSubjects, (a, b) -> {
            String nameA = a.split(": ", 2)[1];
            String nameB = b.split(": ", 2)[1];
            return nameA.compareTo(nameB);
        });

        // Step 4: Display the sorted subjects
        System.out.println("Enrolled Subjects for " + targetUser + ":");
        System.out.println("=".repeat(76));
        for (String subject : sortedSubjects) {
            System.out.println(subject);
        }
    }

    // Helper method to extract subject codes from a user block
    private static LinkedList<String> extractSubjectCodes(String userBlock) {
        LinkedList<String> codes = new LinkedList<>();
        Scanner scanner = new Scanner(userBlock);
        scanner.useDelimiter("\n");

        boolean foundSubjectCodes = false;
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            if (line.equals("")) {
                continue; // Skip empty lines
            }

            if (foundSubjectCodes) {
                for (String code : line.split(",")) {
                    codes.add(code.trim());
                }
            } else if (line.startsWith(userBlock.split("\n")[0])) {
                // Skip the first line (user info)
            } else {
                // Found the subject code line
                foundSubjectCodes = true;
                for (String code : line.split(",")) {
                    codes.add(code.trim());
                }
            }
        }

        return codes;
    }
}
