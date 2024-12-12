/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HandlingSelectionFromMultipleActivities;

import java.io.File;

/**
 *
 * @author HAOLEONGWONG
 */
public class CheckingFilesintheCurrentDirectory {
     public static void main(String[] args) {
        File currentDir = new File(System.getProperty("user.dir"));
        String[] files = currentDir.list();
        
        System.out.println("Files in the current directory:");
        if (files != null) {
            for (String file : files) {
                System.out.println(file);
            }
        } else {
            System.out.println("No files found!");
        }
    }
}
