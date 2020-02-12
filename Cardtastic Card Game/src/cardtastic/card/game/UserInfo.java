/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardtastic.card.game;

import java.io.IOException;
import java.io.PrintWriter;
import javafx.scene.control.TextField;

/**
 *
 * @author yousif
 */
public class UserInfo {
    
    private TextField fName; 
    private TextField lName; 
    private TextField username;
    private TextField email; 
    private TextField password; 
    private TextField confPass;
    
    
    public UserInfo(TextField fName, TextField lName, TextField username, TextField email, TextField password, TextField confPass){
        
        this.fName = fName; 
        this.lName = lName; 
        this.username = username; 
        this.email = email; 
        this.password = password; 
        this.confPass = confPass; 
        
       
        
    }
    
    public void toFile(String fileName, TextField textfield) throws IOException{
        PrintWriter out = new PrintWriter(fileName); 
        
        //out.write(textfield.getText().getBytes());
    }
    
   
    
}
