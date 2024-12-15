/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package finalassignment;

/**
 *
 * @author Lenovo02
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FinalAssignment {

    public static void main(String[] args) {
        String studentId = "s100201"; // Replace with the desired student ID
        displayActivities(studentId);
    }

    public static void displayActivities(String studentId) {
        String fileName = "ActivitiesLog.txt"; // Path to your ActivitiesLog.txt file
        boolean hasActivities = false;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            System.out.println("Activities for student ID: " + studentId);

            // Read each line from the file
            while ((line = br.readLine()) != null) {
                // Split the line into parts
                String[] parts = line.split(",");

                // Check if the student ID matches
                if (parts[0].equals(studentId)) {
                    hasActivities = true; // Mark that activities were found
                    String clubCode = parts[1];
                    String activityName = parts[2];
                    String activityLevel = parts[3];
                    String achievementLevel = parts[4];

                    // Display the activity details
                    System.out.println("Club: " + clubCode);
                    System.out.println(" - Activity Name: " + activityName);
                    System.out.println("   Level of Activity: " + activityLevel);
                    System.out.println("   Achievement Level: " + achievementLevel);
                }
            }

            // If no activities were found
            if (!hasActivities) {
                System.out.println("No activities found for student ID: " + studentId);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}