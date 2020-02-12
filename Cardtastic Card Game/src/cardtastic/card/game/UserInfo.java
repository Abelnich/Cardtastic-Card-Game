/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardtastic.card.game;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.scene.control.TextField;

/**
 *
 * @author yousif
 */
public class UserInfo {
    
    private String fName; 
    private String lName; 
    private String username;
    private String email; 
    private String password; 
    private String confPass;
    
    private ArrayList<String> master = new ArrayList(); 
    
    public UserInfo(TextField fName, TextField lName, TextField username, TextField email, TextField password, TextField confPass){
        
        this.fName = fName.getText(); 
        this.lName = lName.getText(); 
        this.username = username.getText(); 
        this.email = email.getText(); 
        this.password = password.getText(); 
        this.confPass = confPass.getText(); 
        
        
        
        master.add(this.fName + " "); 
        master.add(this.lName + " "); 
        master.add(this.username + " "); 
        master.add(this.email + " "); 
        master.add(this.password + " "); 
        master.add(this.confPass + " "); 
        
        
       
        
    }
    
    public void toFile(String fileName) throws IOException{
        PrintWriter writer = new PrintWriter(new FileWriter(fileName, true)); 
        
        writer.write("\n"); 
        for(String i : master){
            writer.write(i);
        }
        
         writer.close();
        
        
    }
    
   
    
}