/*
 CSI 2999
 Create Account page 
 Yousif Hanani 
 01/30/2020

 */
package cardtastic.card.game;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author yousif
 */
public class createAccount {

    
    public void begin(){
        
       
     
        Text fName = new Text("First Name: ");
        Text lName = new Text("Last Name: ");
        Text user = new Text("Username: "); 
        Text e = new Text("Email: "); 
        Text p = new Text("Password: "); 
        Text cp = new Text("Confirm Password: "); 
        
        TextField first = new TextField(); 
        TextField last = new TextField(); 
        TextField username = new TextField(); 
        TextField email = new TextField(); 
        PasswordField password = new PasswordField(); 
        PasswordField confPass = new PasswordField(); 
        
        Button confirm = new Button("Confirm"); 
        Button clear = new Button("Clear"); 
        
        GridPane grid = new GridPane();
        
        grid.setStyle("-fx-font-family: \"Comic Sans MS\";" + "-fx-background-color: darkgreen;" + "-fx-font-size: 15px;");
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setPadding(new Insets(10,10,10,10));
        grid.setAlignment(Pos.CENTER);
        
        grid.add(fName, 0, 0);
        grid.add(lName, 0, 1);
        grid.add(user, 0, 2);
        grid.add(e, 0, 3);
        grid.add(p, 0, 4);
        grid.add(cp, 0, 5);
        
        grid.add(first, 1, 0);
        grid.add(last, 1, 1);
        grid.add(username, 1, 2);
        grid.add(email, 1, 3);
        grid.add(password, 1, 4);
        grid.add(confPass, 1, 5);
        grid.add(confirm,1,6);
        grid.add(clear,1,7); 
        
        
        
        EventHandler<ActionEvent> erase = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                
                first.setText("");
                last.setText("");
                username.setText("");
                email.setText("");
                password.setText("");
                confPass.setText("");
                  
            } 
        }; 
        clear.setOnAction(erase);
        
            
        EventHandler<ActionEvent> save = new EventHandler<ActionEvent>()  { 
            public void handle (ActionEvent e) 
            { 
                
                try {
                    UserInfo newUser = new UserInfo(first,last,username,email,password,confPass);
                    Alert a = new Alert(AlertType.NONE); 
                    
                    if(first.getText().isEmpty() || last.getText().isEmpty() || username.getText().isEmpty()
                       || email.getText().isEmpty() || password.getText().isEmpty() || confPass.getText().isEmpty()){
                        a.setAlertType(AlertType.ERROR);
                        a.show();
                        
                        
                    }
                    else if(newUser.checkAccount(email) == false){
                        a.setAlertType(AlertType.ERROR);
                        a.setContentText("Account Already Exists");
                        a.show();
                        
                    }
                    else if(password.getText().compareTo(confPass.getText()) != 0){
                        a.setAlertType(AlertType.ERROR);
                        a.setContentText("Password Does Not Match");
                        a.show();
                        
                    }
                    else if(password.getText().length() < 4 || confPass.getText().length() < 4){
                        a.setAlertType(AlertType.ERROR);
                        a.setContentText("Password must have at least 4 characters");
                        a.show();
                        
                    }
                    
                        
               
                    else{
                        newUser.toFile("userDatabase.txt");
                        Alert a1 = new Alert(AlertType.NONE, "default Dialog",ButtonType.APPLY); 
                        a1.setContentText("Account created!");
                        a1.show();
                        
                    }
                } catch (IOException ex) {
                   // Logger.getLogger(createAccount.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                  
            }  
        }; 
            
            confirm.setOnAction(save);
            
             
        
        Scene root = new Scene(grid,500,450); 
        
        Stage createAccount = new Stage();
        createAccount.setTitle("Create Account");
        createAccount.setScene(root);
        createAccount.show();
        
    }
}
