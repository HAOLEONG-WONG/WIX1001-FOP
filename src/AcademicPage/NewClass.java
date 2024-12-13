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
import java.io.*;
import java.util.*;
import java.util.Scanner;
public class NewClass {

    public static void main(String a) {
        Scanner sc=new Scanner(System.in);
         try {
            // Read AcademicSubjects.txt and create a map of SubjectCode to SubjectName
            Map<String, String> subjectMap = new HashMap<>();
            BufferedReader subjectReader = new BufferedReader(new FileReader("AcademicSubjects.txt"));
            String line;
            while ((line = subjectReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    subjectMap.put(parts[0].trim(), parts[1].trim());
                }
            }
            subjectReader.close();

            // Read UserData.txt to extract subjects for a specific student
            BufferedReader userReader = new BufferedReader(new FileReader("UserData.txt"));
            String studentId = a; // Change this to the desired student ID
            List<String> enrolledSubjectCodes = new ArrayList<>();

            while ((line = userReader.readLine()) != null) {
                if (line.equals(studentId)) { // Found the student ID
                    userReader.readLine(); // Skip password
                    String subjectLine = userReader.readLine(); // Get enrolled subject codes
                    enrolledSubjectCodes = Arrays.asList(subjectLine.split(","));
                    break;
                }
            }
            userReader.close();

            // Map enrolled subjects to their names and sort them alphabetically by name
            List<String> enrolledSubjects = new ArrayList<>();
            for (String code : enrolledSubjectCodes) {
                if (subjectMap.containsKey(code)) {
                    enrolledSubjects.add(code + ": " + subjectMap.get(code));
                }
            }

            // Bubble sort to sort by subject name
            for (int i = 0; i < enrolledSubjects.size() - 1; i++) {
                for (int j = 0; j < enrolledSubjects.size() - i - 1; j++) {
                    String current = enrolledSubjects.get(j);
                    String next = enrolledSubjects.get(j + 1);
                    // Compare the subject names to determine the order
                    if (current.split(": ")[1].compareTo(next.split(": ")[1]) > 0) {
                        // Swap if the next element is smaller
                        String temp = enrolledSubjects.get(j);
                        enrolledSubjects.set(j, enrolledSubjects.get(j + 1));
                        enrolledSubjects.set(j + 1, temp);
                    }
                }
            }

            // Display the result
            System.out.println("Enrolled Subjects:");
            System.out.println("------------------------");
            for (String subject : enrolledSubjects) {
                System.out.println(subject);
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
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