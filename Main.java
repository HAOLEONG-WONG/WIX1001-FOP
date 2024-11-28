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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Import class
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
        String matricNumber = "s100201"; // Replace with desired matric number
        String[][] userClubs = muc.getUserClubs(matricNumber, userData, clubMapping);

        // Display user clubs
        cad.displayUserClubs(userClubs);
    }
}
    
