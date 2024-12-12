/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CocurriculumPage;

/**
 *
 * @author HAOLEONGWONG
 */
import java.io.*;
import java.util.Scanner;
public class Main {

    /**
     * @param args the command line arguments
     */
    public static int CocuDisplaymain(String matricnum) {
        //Import class
        Scanner sc=new Scanner (System.in);
        ReadFilesintoArrays rfa= new ReadFilesintoArrays();
        ParseClubData pcd=new ParseClubData();
        MatchUserClubs muc=new MatchUserClubs();
        CategorizeandDisplay cad=new CategorizeandDisplay();

        int userLinesCount = 11;
        int clubLinesCount = 10;

        // Read files
        String[] userData = rfa.readFile("UserData.txt", userLinesCount);
        String[] clubData = rfa.readFile("ClubSocieties.txt", clubLinesCount);

        // Parse club data
        String[][] clubMapping = pcd.parseClubData(clubData);

        // Get user clubs
//        String matricNumber = "s100201"; // Replace with desired matric number
        String[][] userClubs = muc.getUserClubs(matricnum, userData, clubMapping);

        // Display user clubs
        cad.displayUserClubs(userClubs);
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
}
    
