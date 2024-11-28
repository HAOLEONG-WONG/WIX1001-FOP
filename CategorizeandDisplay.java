/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CocurriculumPage;

/**
 *
 * @author HAOLEONGWONG
 */
public class CategorizeandDisplay {
    public static void displayUserClubs(String[][] Matchedclubs) {
    // Print clubs
    System.out.println("Student "+Matchedclubs[3][0]);
    System.out.println("Your Cocurricular Clubs:");
    System.out.println("=================================================================");
    System.out.printf("%s%7s%1s%1s\n","Societies: ",Matchedclubs[0][0]," - ",Matchedclubs[0][1]);
    System.out.printf("%s%4s%1s%1s\n","Uniform Body: ",Matchedclubs[1][0]," - " ,Matchedclubs[1][1]);
    System.out.printf("%s%5s%1s%1s\n","Sports Club: " ,Matchedclubs[2][0]," - " ,Matchedclubs[2][1]);
    System.out.println("=================================================================");
    System.out.println("Generate Transcript?");
    }
    
    
    //Checking can delete after 
    public static void main(String[]args){
        //Import class
        ReadFilesintoArrays rfa= new ReadFilesintoArrays();
        ParseClubData pcd=new ParseClubData();
        MatchUserClubs muc=new MatchUserClubs();
        //userData
        int usernum =11;
        String[] userData = rfa.readFile("UserData.txt", usernum);
        // afterparse
        int clubnum =10;
        String[] clubLines = rfa.readFile("ClubSocieties.txt", clubnum);
        String [][] afterparse= pcd.parseClubData(clubLines);        
        //Call method getUserClubs
        String[][]clubmatch = muc.getUserClubs("s100202", userData, afterparse);
        //Call displayUserClubs method
        displayUserClubs(clubmatch);
    }
    // until here
}