/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CocurriculumPage;

/**
 *
 * @author HAOLEONGWONG
 */

// Input in this class:( By readfilesintoarray class )
//B01,Scout
//B03,Police Cadet
//B07,The Boys� Brigade
//P27,Computer Society
//P81,Young Entrepreneur Society
//P82,Robotic Club
//S01,Badminton Club
//S10,Swimming Club
//S15,Fencing Club
//null
public class ParseClubData {
    public static String[][] parseClubData ( String[] clubLines) {
        String[][] clubMapping = new String[clubLines.length][3]; // [code, name, category]
        for (int i = 0; i < clubLines.length; i++) {
            
            // Skip null lines
            if (clubLines[i] == null) {
                break; 
            }
            // split the input to 2 parts:[0] is code,[1]is name
            String[] parts = clubLines[i].split(",");
            String code = parts[0];
            String name = parts[1];
            
            //Create another section to identify and store either sociesties,uniform body or club
            String category = code.startsWith("P") ? "Societies" :
                              code.startsWith("B") ? "Uniform Body" :
                              "Sports Club";
            // [i] refers to a new array of each line of the input, it will store[0]for code,[1]for name,and [2] for sociesties,uniform body or club 
            clubMapping[i][0] = code;
            clubMapping[i][1] = name;
            clubMapping[i][2] = category;
        }
        return clubMapping;     
    }
    
    //Checking output( Can delete after)
    public static void main(String[]args){
        // Call the readfilesintoarray class
        ReadFilesintoArrays rfa= new ReadFilesintoArrays();
        
        int clubnum =10;
        // Call the method in the class
        String[] clubLines = rfa.readFile("ClubSocieties.txt", clubnum);
        
        // Call the method parseClubData
        String [][] afterparse= parseClubData (clubLines);
        for(int i=0;i<clubLines.length;i++){
            for(int j=0;j<3;j++){
                System.out.print(afterparse[i][j]);
            }
            System.out.println("");
        }
    }
    // Until here
}
