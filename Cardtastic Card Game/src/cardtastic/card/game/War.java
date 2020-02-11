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
    
   //public static void main(String[] args){


    public void start(Stage primaryStage) {
        
        /*



//Creates deck
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
        
       //shuffles deck
       Collections.shuffle(hands, new Random());
       
       //creates hands
       LinkedList<Card> hand1 = new LinkedList<Card>();
       LinkedList<Card> hand2 = new LinkedList<Card>();
       
       //splits deck in two
       hand1.addAll(hands.subList(0, 25));
       hand2.addAll(hands.subList(26, hands.size()));
       
       //Start of the Game
       while(true){
           //takes the first card out of each hand
           Card p1 = hand1.pop();
           Card p2 = hand2.pop();
           
           //compares the cards
           if(p1.getValue() > p2.getValue()){
               //adds cards to player 1's hand if they win
               hand1.addLast(p1);
               hand1.addLast(p2);
               
               else if(p1.getValue() < p2.getValue()){
                       //adds card to players 2's hand if they win
                       hand2.addLast(p1);
                       hand2.addLast(p2);
                       }
                       else{
                       //war if cards match
                        ArrayList<Card> war1 = new ArrayList<Card>();
                        ArrayList<Card> war2 = new ArrayList<Card>();
                       
                        for(int x = 0; x < 3; x++){
                       // checks to see if players have enough cards in their hands to continue
                            if(hand1.size() == 0 || hand2.size() == 0){
                                break;
                       }
                       hand1.addAll(war1);
                       hand1.addAll(war2);
                       }
                       if war1.size() == 3 && war2.size() == 3){
                       
                       
                        if(war1.get(2).getValue() > war2.get(2).getValue()){
                            hand1.addAll(war1);
                            hand1.addAll(war2);
                       }
                            else{
                                hand2.addAll(war1);
                                hand2.addAll(war2);
                       }
                       }
                       }
           
       }
           if(hand1.size() == 0){
               break;
           }
           else if(hand2.size() == 0){
               break;
           }
       }
   
   
   }*/
               
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
    
    
    

