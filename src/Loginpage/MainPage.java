/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Loginpage;

/**
 *
 * @author HAOLEONGWONG
 */
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

public class MainPage {
    private static HashMap<String, String> loginInfo = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    

    public static String entrypoint () {
        // Initialize loginInfo (username, password) directly
        try {
            // Read from the file "data.txt"
            Scanner in = new Scanner(new FileInputStream("UserData.txt"));
            String email = "";
            String password = "";
            
            int linecount=1;
            int lineEmail=1;
            int linePassword=3;
            while(in.hasNextLine()){
                String line = in.nextLine().trim();
                if(linecount==lineEmail){
                    email = line;
                    lineEmail+=6;
                }
                if(linecount==linePassword){
                    password=line;
                    linePassword+=6;
                    loginInfo.put(email, password);
                }
                linecount++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File was not found");
        }
        // Start the Login process
        return startLogin();
    }
    
    // Start the login process
    public static String startLogin() {
        System.out.println("Please enter your Email Address:");
        String userID = scanner.nextLine();

        System.out.println("Please enter your Password:");
        String password = scanner.nextLine();

        if (loginInfo.containsKey(userID)) {
            if (loginInfo.get(userID).equals(password)) {
                System.out.println("Login successful");
                return userID;
//                System.out.println("Welcome to the next page!");
                // After successful login, go to next page
//                startNextPage();
            } else {
                System.out.println("Wrong password");
                handleRetry();
            }
        } else {
            System.out.println("Wrong User ID");
            handleRetry();
        }
        return null;
    }

    // Handle retry after failed login attempt
    private static void handleRetry() {
        System.out.println("Do you want to try again or reset your password? (retry/reset/exit)");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("retry")) {
            startLogin();  // Retry login
        } else if (choice.equalsIgnoreCase("reset")) {
            startResetPasswordPage();
        } else {
            System.out.println("Goodbye!");
            scanner.close();
            System.exit(0);
        }
    }

    // Start the password reset process
    private static void startResetPasswordPage() {
        System.out.println("Password reset page");

        System.out.println("Please enter your Email Address for reset:");
        String resetUserID = scanner.nextLine();

        System.out.println("Please enter your ID Card Number:");
        String idCard = scanner.nextLine();

        System.out.println("Please enter your new Password:");
        String newPassword = scanner.nextLine();

        if (!idCard.isEmpty() && !newPassword.isEmpty()) {
            if (updatePasswordInDatabase(idCard, newPassword)) {
                System.out.println("Password reset successful!");
                startLogin();  // After resetting, go back to login page
            } else {
                System.out.println("Invalid ID Card Number!");
                handleRetry();
            }
        } else {
            System.out.println("Please fill in all fields.");
            handleRetry();
        }
    }

    // Update password in the database (simulated)
    private static boolean updatePasswordInDatabase(String idCard, String newPassword) {
        // In a real scenario, this method would interact with a database
        // For this simulation, let's assume the ID card and password update always succeed
        System.out.println("Updating password for ID Card: " + idCard);
        // Simulate a successful update
        return true;
    }

//    // After successful login, move to the next page
//    private static void startNextPage() {
//        System.out.println("Welcome to the next page!");
//        // Additional logic for the next page could go here
//    }
}