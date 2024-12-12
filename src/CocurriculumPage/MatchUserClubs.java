/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CocurriculumPage;
/**
 *
 * @author HAOLEONGWONG
 */
public class MatchUserClubs {
 
//    Notes for getUserClubs method:
//    clubmatch[i][j]
//    i=0:society club
//    i=1:uniform body
//    i=2:Sport club
//    i=3: matricnum
//    
//    j=0:code
//    j=1:name
//    j=2:category
    
    public static String[][] getUserClubs(String matricnum, String [] userData, String [][] afterparse){
        String[][]clubmatch=new String[4][3];
        String B="" ;
        String P="" ;
        String S="" ;
        clubmatch[3][0]=matricnum;
        for (int i=0;i<userData.length;i++){
            if(matricnum.equals(userData[i])){
                String[] parts = userData[i=i+3].split(",");
                B = parts[0];
                P = parts[1];
                S = parts[2];
                break;
            }
        }
       
        for(int a=0;a<afterparse.length;a++){
            if(afterparse[a][0] == null){
                break;
            }
            if(P.equals(afterparse[a][0])){
                clubmatch[0][0]=afterparse[a][0];
                clubmatch[0][1]=afterparse[a][1];
                clubmatch[0][2]=afterparse[a][2];
            }
            if(B.equals(afterparse[a][0])){
                clubmatch[1][0]=afterparse[a][0];
                clubmatch[1][1]=afterparse[a][1];
                clubmatch[1][2]=afterparse[a][2];
            }
            if(S.equals(afterparse[a][0])){
                clubmatch[2][0]=afterparse[a][0];
                clubmatch[2][1]=afterparse[a][1];
                clubmatch[2][2]=afterparse[a][2];
            }
        }
    return clubmatch;
    }
    
    //Checking (can delete after )
    public static void main(String[]args){
        //Import class
        ReadFilesintoArrays rfa= new ReadFilesintoArrays();
        ParseClubData pcd=new ParseClubData();
        
        //userData
        int usernum =11;
        String[] userData = rfa.readFile("UserData.txt", usernum);
        
        // afterparse
        int clubnum =10;
        String[] clubLines = rfa.readFile("ClubSocieties.txt", clubnum);
        String [][] afterparse= pcd.parseClubData(clubLines);        
        
        //Call method getUserClubs
        String[][]clubmatch = getUserClubs("s100202", userData, afterparse);
        
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print( clubmatch[i][j]);
            }
            System.out.println("");
        }
    }
    //until here 
}