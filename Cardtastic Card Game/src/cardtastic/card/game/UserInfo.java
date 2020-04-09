/*
Yousif Hanani 
CSI 2999
 */
package cardtastic.card.game;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

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
    private String activeUser; 
    
    private ArrayList<String> master = new ArrayList(); 
    private ArrayList<String> checkLogin = new ArrayList(); 
    private ArrayList<String> checkAccount = new ArrayList(); 
    
    public UserInfo(){
        
    }
    
    public UserInfo(TextField fName, TextField lName, TextField username, TextField email, PasswordField password, PasswordField confPass){
        
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
    
    // writes the user inputed information to a file 
    public void toFile(String fileName) throws IOException{ 
        PrintWriter writer = new PrintWriter(new FileWriter(fileName, true)); 
        
        
        writer.write("\n"); 
        for(String i : master){
            writer.write(i);
        }
        
         writer.close();
        
        
    }
    
    // validates the username and password for the login page
    public void validate(TextField user, PasswordField pass) throws IOException{ 
        
        Scanner reader = new Scanner(new File("userDatabase.txt")); 
        
        //takes the contents of the file and adds them to a new arrayList called check 
        while(reader.hasNext()){
            String test = reader.next(); 
            checkLogin.add(test); 
        }
        
        Stage main = new Stage(); // test stage 
        
        Main m = new Main(); 
        
        // this loop sperates all the users in the text file
        for(int a = 0; a < checkLogin.size(); a+=6){
//            System.out.println(check.get(a+2));
//            
//            System.out.println(check.get(a+4));

            // checks the username and password and displays the stage if there is a match
            if(user.getText().equals(checkLogin.get(a+2)) && pass.getText().equals(checkLogin.get(a+4))){
             
                
                try {
                    
                    activeUser = user.getText();
                    m.userName.setText("Username: " + activeUser);
                    m.userName.setLayoutX(500);
                    m.userName.setLayoutY(500);
                    m.userName.setStyle("-fx-font: 22 calibre;");
                    
                    m.start(main);
                    
                } catch (Exception ex) {
                    Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
                }
                
               //  a = 10000000; 
            }
            
            
            
        }
        
        
        if(main.isShowing() == false){
            Alert a = new Alert(AlertType.NONE); 
              a.setAlertType(Alert.AlertType.ERROR);
              a.setContentText("Invalid Username or Password");
              a.show();
             
             activeUser = null; 
        }
        else{
              
        }

        
        System.out.println("Active User: " + activeUser);
        
       
        
//        for(int i = 0; i < check.size(); i++){
//            System.out.println(check.get(i) + " -- " + i);
//        }
        
        
    }
    
    public boolean checkAccount(TextField email) throws IOException{
       Scanner reader = new Scanner(new File("userDatabase.txt")); 
       boolean result = true; 
       while(reader.hasNext()){
           String test = reader.next(); 
           checkAccount.add(test); 
       }
       
       for(int a = 0; a < checkAccount.size(); a+=6){
           if(email.getText().equals(checkAccount.get(a+3))){
               result = false; 
               
           }
       }
       
       
       return result; 
    }
    
     public String getActiveUser(){
            return activeUser; 
        }
     public void setActiveUser(String user){
         activeUser = user; 
     }
    
    
    
   
    
}
