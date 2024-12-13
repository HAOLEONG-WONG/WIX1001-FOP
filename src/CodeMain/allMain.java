/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CodeMain;

/**
 *
 * @author HAOLEONGWONG
 */
import java.util.Scanner;
import java.io.*;
import UserAccount.*;
import Loginpage.*;
import AcademicPage.*;
import CocurriculumPage.*;
import Co_curriculumCalculationPage.*;
import HandlingSelectionFromMultipleActivities.*;


public class allMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        //Basic feature:
       
        //UserAccount
        //Loginpage 
        MainPage loginP =new MainPage();
        //AcademicPage
        NewClass academicP = new NewClass();
        //CocurriculumPage
        Main cocuDisplay =new Main();
        //Co_curriculumCalculatorPage
        CocurriculumCalculator cocuCalculator = new CocurriculumCalculator();
        
        //Extra features:
        //Handling selection from multiple Activities
        FileReadAndAppend multipleActivitesP =new FileReadAndAppend();
        
        //Start coding part
        
        //1st page LoginPage ---------------------------------------------------
        String UserEmail =loginP.entrypoint();
        
        //Link loginPage to user class
        String userEmail="";
        String matricnum="";
        String password="";
        String academicCode="";
        String cocurricularCode="";
        
        try{
            Scanner in= new Scanner(new FileInputStream("UserData.txt"));
            
            while(in.hasNextLine()){
                String line = in.nextLine().trim();
                if(UserEmail.equals(line)){
                    userEmail = line;
                    line = in.nextLine().trim();
                    matricnum= line;
                    line = in.nextLine().trim();
                    password=line;
                    line = in.nextLine().trim();
                    academicCode=line;
                    line = in.nextLine().trim();
                    cocurricularCode=line;
                }
            }
            in.close();
        }catch(FileNotFoundException e){
            System.out.println("File was not found");
        }
        
        user current_user= new user(userEmail,matricnum,password,academicCode,cocurricularCode);
        //End LoginPage

        //2nd page(Selection)-------------------------------------------------------------------------
        boolean page=true;
        while(page){
            System.out.println("=".repeat(65));
            System.out.println("Welcome student "+ current_user.getMatricnum() +": ");
            System.out.println("""
                           ------------------------
                           Press: 
                           1. to Academic page
                           2. to Cocurriculum page
                           3. to add more club activity
                           4. to end the programme""");
            System.out.print("Your selection: ");
            int choice=sc.nextInt();
            System.out.println("=================================================================");
            
            switch (choice) {
                case 1:
                    System.out.println("Student "+current_user.getMatricnum());
                    academicP.main(current_user.getMatricnum());
                    break;
                case 2:
                    int transcriptchoice = cocuDisplay.CocuDisplaymain(current_user.getMatricnum());
                    if(transcriptchoice==1){
                        cocuCalculator.main(current_user.getMatricnum());
                    }
                    break;
                case 3:
                    int addedtranscriptchoice=multipleActivitesP.main(current_user.getMatricnum());
                    if(addedtranscriptchoice==1){
                        cocuCalculator.main(current_user.getMatricnum());
                    }
                    break;
                case 4:
                    page=false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter again.");
                    break;
            }
        }
    }
}
