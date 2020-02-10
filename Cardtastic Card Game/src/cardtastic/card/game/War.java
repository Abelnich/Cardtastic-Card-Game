/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardtastic.card.game;
import static java.awt.SystemColor.window;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import static javafx.application.Application.launch;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 *
 * @author tq
 */
public class War {
    
   public static void main(String[] args){




//Creates the hands and the deck
       ArrayList<Card> hands = new ArrayList<Card>();
       String[] vals = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
       Deck myDeck = new Deck();
       for (int i = 0; i < vals.length; i++) {
            Card tempSpade = new Card("Spades", vals[i]);
            hands.add(tempSpade);
            Card tempDiamond = new Card("Diamonds", vals[i]);
            hands.add(tempDiamond);
            Card tempClub = new Card("Clubs", vals[i]);
            hands.add(tempClub);
            Card tempHeart = new Card("Hearts", vals[i]);
            hands.add(tempHeart);
        }
        
       Collections.shuffle(hands, new Random());
       
       LinkedList<Card> deck1 = new LinkedList<Card>();
       LinkedList<Card> deck2 = new LinkedList<Card>();
       
       deck1.addAll(hands.subList(0, 25));
       deck2.addAll(hands.subList(26, hands.size()));
       
       
   
   
   
   }

    public void start(Stage primaryStage) {
               
        VBox warLayout = new VBox();
        Button strtBtn, drwBtn;
        
        MenuBar warMenuBar = new MenuBar();
        VBox warMenuVbox = new VBox();
        Menu warMenu = new Menu("User");
        MenuItem settingsMenuItem = new MenuItem("Settings");
        
        settingsMenuItem.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                Settings settingsPage = new Settings();
                settingsPage.start(primaryStage);

            }
        });
        
        warMenu.getItems().add(settingsMenuItem);
        warMenuBar.getMenus().add(warMenu);
        
        
        warLayout.setStyle("-fx-background-color: ForestGreen");
        Scene war = new Scene(warLayout, 1280, 550);
        warLayout.setPrefWidth(300);
        warLayout.setSpacing(20);
        warLayout.setPadding(new Insets(0, 0, 0, 0));
        primaryStage.setTitle("War Game");
        Label warTitle = new Label("War");
        warTitle.setFont(new Font("calibre", 40));
        warLayout.getChildren().addAll(warMenuBar);
        warLayout.getChildren().addAll(warTitle);
        warLayout.setAlignment(Pos.TOP_CENTER);
        primaryStage.setScene(war);
        primaryStage.show();
      
        
        
    }
        
        
    
         }
    
    
    

