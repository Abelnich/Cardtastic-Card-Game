/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardtastic.card.game;
import static java.awt.SystemColor.window;
import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 *
 * @author tq
 */
public class War {
    
        
        int rnd = 0;
        String rdW = "";
        int pc;
        int cc;
        String pc1 = "";
        String cc1 = "";
        String wnr = "";
        String warG = "";
        Image card1;
        Image card2;
        Image card3;
        Image card4;
    
   //public static void main(String[] args){


    public void start(Stage primaryStage) {
        
        



//Creates deck
       ArrayList<Card> hands = new ArrayList<Card>(52);
       Deck myDeck1 = new Deck();
       Deck myDeck2 = new Deck();
      
        String[] vals = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
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
       
       
       
       //deals to hand
       myDeck1.Shuffle();
       myDeck1.deal();
       
       myDeck2.Shuffle();
       myDeck2.deal();
       
       
       
       
       
       //splits deck in two
       hand1.addAll(hands.subList(0, 25));
       hand2.addAll(hands.subList(26, hands.size()));
       
        
           pc = hand1.size() + 1;//not sure why i doesnt display 26
           cc = hand2.size();
           
           
        

       
       
      
        
         
    
         

   
   
               
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
        
        Button drawButton = new Button("Draw Cards");
        
        
        
        
        warLayout.setStyle("-fx-background-color: ForestGreen");
        Scene war = new Scene(warLayout, 1280, 550);
        warLayout.setPrefWidth(300);
        
        warLayout.setPadding(new Insets(0, 0, 0, 0));
        primaryStage.setTitle("War Game");
        Label warTitle = new Label("War");
        Label round = new Label("Round#: " + rnd);
        Label roundWon = new Label("Round Winner is: " + rdW);
        Label p1Hand = new Label("Player's cards remaining: " + pc);
        Label p2Hand = new Label("Computer's cards remaining: " + cc);
        Label p1Draw = new Label("Player's card: " + pc1);
        Label p2Draw = new Label("Computer's card: " + cc1);
        Label winner = new Label("Winner is: " + wnr);
        Label warGm = new Label (warG);
        warTitle.setFont(new Font("calibre", 40));
        ImageView imv1 = new ImageView();
        
        //Image d1, d2 = new Image("C:\\Users\\tq\\Desktop\\SophProj\\Cardtastic-Card-Game\\Cardtastic Card Game");
        
        
        drawButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                rnd = rnd+1;
                round.setText("Round#: " + rnd);
                //Start of the Game
                warGm.setText("");
          //takes the first card out of each hand
           Card p1 = hand1.pop();
           Card p2 = hand2.pop();
           
           
           
           //compares the cards
           if(p1.getRank() > p2.getRank()){
               //adds cards to player 1's hand if they win
               hand1.addLast(p1);
               hand1.addLast(p2);
               
               rdW = "Player";
               roundWon.setText("Round Winner is: " + rdW);
               pc += 1;
               p1Hand.setText("Player's cards remaining: " + pc);
               cc -= 1;
               p2Hand.setText("Computer's cards remaining: " + cc);
               pc1 = p1.getSuit() +" "+ p1.getValue();
               p1Draw.setText("Player's card: " + pc1);
               cc1 = p2.getSuit() +" "+ p2.getValue();
               p2Draw.setText("Computer's card: " + cc1);
               imv1.setImage(p2.getImageFile(p1.getSuit(), p1.getValue()));
               
               
               
               
           }
               else if(p1.getRank() < p2.getRank()){
                       //adds card to players 2's hand if they win
                       hand2.addLast(p1);
                       hand2.addLast(p2);
                       
                        rdW = "Computer";
                        roundWon.setText("Round Winner is: " + rdW);
                        pc -= 1;
                        p1Hand.setText("Player's cards remaining: " + pc);
                        cc += 1;
                        p2Hand.setText("Computer's cards remaining: " + cc);
                        pc1 = p1.getSuit() +" "+ p1.getValue();
                        p1Draw.setText("Player's card: " + pc1);
                        cc1 = p2.getSuit() +" "+ p2.getValue();
                        p2Draw.setText("Computer's card: " + cc1);
                        p1.getImageFile(p1.getSuit(), p1.getValue());
                        p2.getImageFile(p1.getSuit(), p1.getValue());
                        
                        
                       }//end of else if
                            else{
                                //war if cards match
                                
                                pc1 = p1.getSuit() +" "+ p1.getValue();
                                p1Draw.setText("Player's card: " + pc1);
                                cc1 = p2.getSuit() +" "+ p2.getValue();
                                p2Draw.setText("Computer's card: " + cc1);
                                p1.getImageFile(p1.getSuit(), p1.getValue());
                                p2.getImageFile(p1.getSuit(), p1.getValue());
                                
                                ArrayList<Card> war1 = new ArrayList<Card>();
                                ArrayList<Card> war2 = new ArrayList<Card>();
                       
                                for(int x = 0; x < 3; x++){
        
                                // checks to see if players have enough cards in their hands to continue
                                    if(hand1.isEmpty()){
                                        
                                        wnr = "Computer";
                                        winner.setText("Winner is: " + wnr);
                                        rnd = 0;
                                        round.setText("Round#: " + rnd);
                                        
                                         break;
                                         
                                    }//end of if
                                    else if (hand2.isEmpty()){
                                        
                                        wnr = "Player";
                                        winner.setText("Winner is: " + wnr);
                                        rnd = 0;
                                        round.setText("Round#: " + rnd);
                                        break;
                                        
                                    }//end of elseif
                                war1.add(hand1.pop());
                                war2.add(hand2.pop());

                                }//end of for
        
                            if (war1.size() == 3 && war2.size() == 3){
                       
                                if(war1.get(2).getRank() > war2.get(2).getRank()){
                                     hand1.addAll(war1);
                                     hand1.addAll(war2);
                                    
                                    warG = "War Declared: Player Wins";
                                    warGm.setText(warG);
                                    pc += 3;
                                    p1Hand.setText("Player's cards remaining: " + pc);
                                    cc -= 3;
                                    p2Hand.setText("Computer's cards remaining: " + cc);
                                    
                                    
                                }//end of if
        
                            else{
                                hand2.addAll(war1);
                                hand2.addAll(war2);
                                
                                warG = "War Declared: Computer Wins";
                                warGm.setText(warG);
                                pc -= 3;
                                p1Hand.setText("Player's cards remaining: " + pc);
                                cc += 3;
                                p2Hand.setText("Computer's cards remaining: " + cc);
                               
                                
                            }//end of else
                            }//end of if
                }//end of else
           if(hand1.isEmpty()){
               
               wnr = "Computer";
               winner.setText("Winner is: " + wnr);
               rnd = 0;
               round.setText("Round#: " + rnd);
               
         }
        
         else if(hand2.isEmpty()){
             
             wnr = "Player";
             winner.setText("Winner is: " + wnr);
             rnd = 0;
             round.setText("Round#: " + rnd);
               
         }
           
            
            }
        });
        
        warLayout.getChildren().addAll(warMenuBar);
        warLayout.getChildren().addAll(warTitle);
        warLayout.setAlignment(Pos.TOP_CENTER);
        warLayout.getChildren().addAll(winner,round,roundWon,p1Hand,p1Draw,p2Draw,p2Hand, warGm);
        warLayout.getChildren().addAll(drawButton);
        warLayout.getChildren().addAll(imv1);
        primaryStage.setScene(war);
        primaryStage.show();
      
        
        
    }//end of start
        
        
    
}//end of class
    
    
    

