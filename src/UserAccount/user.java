/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package UserAccount;

/**
 *
 * @author HAOLEONGWONG
 */
public class user {

    /**
     * @param args the command line arguments
     */
    String userEmail;
    String matricnum;
    String password;
    String academicCode;
    String cocurricularCode;
    
    public user(String UserEmail,String matricnum,String password,String academicCode,String cocurricularCode){
        this.userEmail = UserEmail;
        this.matricnum = matricnum;
        this.password = password;
        this.academicCode=academicCode;
        this.cocurricularCode = cocurricularCode;
    }
    
    public String getEmail(){
        return userEmail;
    }
    
    public String getMatricnum(){
        return matricnum;
    }
}
