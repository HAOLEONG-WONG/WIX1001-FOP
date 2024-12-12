/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HandlingSelectionFromMultipleActivities;

/**
 *
 * @author HAOLEONGWONG
 */
import java.io.*;
import java.util.Scanner;

public class FileReadAndAppend {

    // Method to read from a file and print its content
    public static void readFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //for checking purpose
//                System.out.println(line);
            }
        } 
        catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to append a single line to a file
    public static void appendToFile(String fileName, String newContent) {
        try (FileWriter writer = new FileWriter(fileName, true)) { // 'true' enables append mode
            writer.write(newContent + "\n"); // Append the content with a newline
            
            //Use for checking only
//            System.out.println("\nAppended: " + newContent);
        } 
        catch (IOException e) {
            
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    //Convert info to String so that it can be append into txt file
    public static String transferinfoToString(String matricnum,String code,String compname,String complevel,String achievelevel){
        String newActivities= matricnum+","+code+","+compname+","+complevel+","+achievelevel;
        return newActivities;
    }
    
    
    //Checking whether the txt file is updated or not 
    public static int main(String matricnum) {
        Scanner sc=new Scanner(System.in);
        
        //Prompt user input the new club code ,activity name,activity level,achievement level
        //The matricnum will remain the same as previous 
        System.out.println("Student "+matricnum);
        System.out.println("New activities: ");
        System.out.println("---------------------------------------------------------");
        System.out.print("Club code: ");
        String code =sc.nextLine();
        System.out.print("Activity name: ");
        String compname =sc.nextLine();
        System.out.print("Activity level: ");
        String complevel =sc.nextLine();
        System.out.print("Achievement level: ");
        String achievelevel =sc.nextLine();
        System.out.println("---------------------------------------------------------");
        
        // Create New activities to append one by one, everytime user need to rerun the program to insert&store the new activities
        String newActivity ;
        newActivity = transferinfoToString(matricnum,code,compname,complevel,achievelevel);
        
        // Trace back to(readFromFile method) if no need to display the before and after txt file 
        // Read and display the file content
        String fileName = "ActivitiesLog.txt";
        //Chechking purpose
//        System.out.println("Current File Content:");
        readFromFile(fileName);

        // Append the new data
        appendToFile(fileName, newActivity);

        //Display updated file content
        //Checking purpose
//        System.out.println("\nUpdated File Content:");
        readFromFile(fileName);
        System.out.println("Generate new Transcript?");
        System.out.println("Press 1 to Yes");
        System.out.println("Press 2 to No and Go back to main page");
        System.out.print("Your choice: ");
        int choice=sc.nextInt();
        while((choice != 1)&&(choice != 2) ){
            System.out.print("\nInvalid option! Please try again.");
            System.out.println("\nPress 1 to Yes");
            System.out.println("Press 2 to No and Go back to main page");
            System.out.print("Your choice: ");
            choice=sc.nextInt();
            
        }
        return choice;
    }
    //until here
}
