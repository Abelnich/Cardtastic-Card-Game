/*
 CSI 2999
 Login Page 
 Yousif Hanani 
 01/30/2020

 */
package cardtastic.card.game;



import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author yousif
 */
public class LoginPage {
    
    
   private Stage loginPage;
    
    public void start(Stage loginPage ) {
     
        this.loginPage = loginPage; 
        
        
        GridPane grid = new GridPane();
        grid.setStyle("-fx-font-family: \"Comic Sans MS\";" + "-fx-background-color: darkgreen;" + "-fx-font-size: 15px;");
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setPadding(new Insets(10,10,10,10));
        grid.setAlignment(Pos.CENTER);
        
         Text user = new Text("Username:"); 
         Text pass = new Text("Password:"); 
         TextField username = new TextField();
         PasswordField password = new PasswordField(); 
         Button login = new Button("Login"); 
         Button create = new Button("Create Account"); 
         
         grid.add(user, 0, 0);
         grid.add(pass, 0, 1);
         grid.add(username, 1, 0);
         grid.add(password, 1, 1);
         grid.add(login, 1, 2);
         grid.add(create, 1, 3);
         
         createAccount x = new createAccount();
         UserInfo y = new UserInfo(); 
         Main m = new Main(); 
         
             EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                
                x.begin();
                
                  
            } 
        }; 
             
              EventHandler<ActionEvent> loginBtn = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                try { 
                    y.validate(username,password);
                    if(m.window.isShowing()){
                        loginPage.hide();
                    }
                    
                } catch (IOException ex) {
                 //   Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                  
            } 
        }; 
             
           
           create.setOnAction(event);
           login.setOnAction(loginBtn);
         
         
        
       
        
      
        
        Scene scene = new Scene(grid, 500, 450);
        
        loginPage.setTitle("Login");
        loginPage.setScene(scene);
        loginPage.show();
    }
    
    public Stage getStage(){
        return loginPage; 
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        
//        launch(args);
//    }
//    
}
