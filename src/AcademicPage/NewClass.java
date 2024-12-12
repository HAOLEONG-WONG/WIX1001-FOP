/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package AcademicPage;

/**
 *
 * @author HAOLEONGWONG
 */
import java.io.*;
import java.util.Scanner;
public class NewClass {

    public static void main(String a) {
        Scanner sc=new Scanner(System.in);
        
        // Step 1: Prepare to store subject data and target user
        String userSubjectCodes = ""; // To store the subject codes for the target user

        String targetUser = a;

        // Step 2: Read AcademicSubjects.txt into a string (for mapping)
        String subjectsData = "";
        try (BufferedReader subjectReader = new BufferedReader(new FileReader("AcademicSubjects.txt"))) {
            String line;
            while ((line = subjectReader.readLine()) != null) {
                subjectsData += line.trim() + "\n"; // Append subject data to the string
            }
        } catch (IOException e) {
            System.out.println("Error reading AcademicSubjects.txt: " + e.getMessage());
            return; // Exit if file reading fails
        }

        // Step 3: Read UserData.txt and find the target user's subjects
        String userData = ""; // To store all user data
        try (BufferedReader userReader = new BufferedReader(new FileReader("UserData.txt"))) {
            String line;
            while ((line = userReader.readLine()) != null) {
                userData += line.trim() + "\n"; // Append user data to the string
            }
        } catch (IOException e) {
            System.out.println("Error reading UserData.txt: " + e.getMessage());
            return; // Exit if file reading fails
        }

        // Split user data into individual users
        String[] users = userData.split("\n\n"); // Assume users are separated by double newlines
        boolean userFound = false;

        for (String userBlock : users) {
            String[] userLines = userBlock.split("\n");

            // Validate user block format (must have at least 5 lines)
            if (userLines.length >= 5 && userLines[1].trim().equalsIgnoreCase(targetUser)) {
                userSubjectCodes = userLines[3].trim(); // Subject codes are on the 4th line
                userFound = true;
                break;
            }
        }

        if (!userFound) {
            System.out.println("User not found: " + targetUser);
            return;
        }

        // Step 4: Map subject codes to names and sort them
        String[] subjectLines = subjectsData.split("\n");
        String[] subjectCodes = userSubjectCodes.split(",");
        String enrolledSubjects = ""; // To store formatted subject names

        for (String code : subjectCodes) {
            code = code.trim();
            for (String subjectLine : subjectLines) {
                if (subjectLine.startsWith(code + ",")) { // Find the matching subject
                    String subjectName = subjectLine.split(",", 2)[1].trim();
                    enrolledSubjects += code + ": " + subjectName + "\n";
                    break;
                }
            }
        }

        // Sort the subjects (basic sorting)
        String[] subjectsArray = enrolledSubjects.split("\n");
        for (int i = 0; i < subjectsArray.length - 1; i++) {
            for (int j = i + 1; j < subjectsArray.length; j++) {
                if (subjectsArray[i].compareTo(subjectsArray[j]) > 0) {
                    String temp = subjectsArray[i];
                    subjectsArray[i] = subjectsArray[j];
                    subjectsArray[j] = temp;
                }
            }
        }

        // Step 5: Display the sorted subjects
        System.out.println("Enrolled Subjects for " + targetUser + ":");
        System.out.println("-".repeat(30));
        for (String subject : subjectsArray) {
            if (!subject.isEmpty()) { // Avoid printing empty lines
                System.out.println(subject);
            }
        }
        while(true){
        System.out.print("\nPress 1 to return to main page: ");
        int option=sc.nextInt();
        switch(option){
            case 1:{
                return;
            }
            default:{
                System.out.print("Invalid option! Please try again.");
            }  
        }
        }
    }
}