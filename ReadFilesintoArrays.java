/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CocurriculumPage;

/**
 *
 * @author HAOLEONGWONG
 */
import java.io.*;
public class ReadFilesintoArrays {
   
    //Create a method that read the data in .txt 
    public static String[] readFile(String fileName, int maxreadlines){
        String[]lines= new String[maxreadlines];
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < maxreadlines) {
                lines[i++] = line.trim();
            }
        } 
        catch (IOException e) {
            System.out.println("Error reading file: " + fileName);
        }
        return lines;
    }
    
    //Checking output( Can delete after)
    public static void main(String[]args)throws IOException{
        int usernum =11;
        int clubnum =10;
        String[] userData= readFile("UserData.txt",usernum);
//        String[] clubData= readFile("ClubSocieties.txt",clubnum);
        
//        for(int i=0;i<clubData.length;i++){
//            System.out.println(clubData[i]);
//        }
        System.out.println("");
        for(int i=0;i<userData.length;i++){
            System.out.println(userData[i]);
        }
    }
    // until here
}
