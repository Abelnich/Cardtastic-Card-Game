/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardtastic.card.game;

import static java.awt.SystemColor.window;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    private int rnd = 0;
    private String rdW = "";
    private int pc;
    private int cc;
    private String pc1 = "";
    private String cc1 = "";
    private String wnr = "";
    private String warG = "";
    Image cardIp;
    Image cardIc;
    

    public void start(Stage primaryStage) {

        VBox warLayout = new VBox();
        
        MenuBar warMenuBar = new MenuBar();
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
        Label warGm = new Label(warG);
        warTitle.setFont(new Font("calibre", 40));

       VBox pD = new VBox();
       pD.setAlignment(Pos.BOTTOM_LEFT);
        Image cardbk = new Image("resources/CardBack_Red.png");
        ImageView imv1 = new ImageView();
        imv1.setImage(cardbk);
        imv1.setFitHeight(150);
        imv1.setPreserveRatio(true);
        pD.getChildren().add(imv1);
        
        //VBox cD = new VBox();
        //pD.setAlignment(Pos.CENTER_RIGHT);
        Image cardbk2 = new Image("resources/CardBack_Red.png");
        ImageView imv2 = new ImageView();
        imv2.setImage(cardbk2);
        imv2.setFitHeight(150);
        imv2.setPreserveRatio(true);
        pD.getChildren().add(imv2);
        
        
        
        




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
            //hands.add(myDeck1.deal());
        }

        //shuffles deck
        Collections.shuffle(hands, new Random());

        //creates hands
        LinkedList<Card> hand1 = new LinkedList<Card>();
        LinkedList<Card> hand2 = new LinkedList<Card>();

        //deals to hand
        //myDeck1.Shuffle();
        myDeck1.deal();

        //myDeck2.Shuffle();
        myDeck2.deal();

        //splits deck in two
        hand1.addAll(hands.subList(0,25));
        hand2.addAll(hands.subList(26, hands.size()));

        pc = hand1.size() + 1;//not sure why i doesnt display 26
        cc = hand2.size();
        
        
        
        
        
        
        
        


        
        drawButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                rnd = rnd + 1;
                round.setText("Round#: " + rnd);
                //Start of the Game
                warGm.setText("");
                
                
                //takes the first card out of each hand
                Card p1 = hand1.pop();
                Card p2 = hand2.pop();
                
                //VBox pcV = new VBox();
                //pcV.setAlignment(Pos.CENTER_LEFT);
                cardIp = p1.getImageFile();
                ImageView i = new ImageView();
                warLayout.getChildren().remove(i);
                i.setFitHeight(150);
                i.setPreserveRatio(true);
                i.setImage(cardIp);
                //pcV.getChildren().add(i);
                
                //VBox ccV = new VBox();
                //ccV.setAlignment(Pos.CENTER_RIGHT);
                ImageView i2 = new ImageView();
                warLayout.getChildren().remove(i2);
                i2.setFitHeight(150);
                i2.setPreserveRatio(true);
                cardIc = p2.getImageFile();
                i2.setImage(cardIc);
                //ccV.getChildren().add(i2);
                
                warLayout.getChildren().addAll(i,i2);

                //compares the cards
                if (p1.getRank() > p2.getRank()) {
                    //adds cards to player 1's hand if they win
                    hand1.addLast(p1);
                    hand1.addLast(p2);
                    
                    
                    rdW = "Player";
                    roundWon.setText("Round Winner is: " + rdW);
                    pc += 1;
                    p1Hand.setText("Player's cards remaining: " + pc);
                    cc -= 1;
                    p2Hand.setText("Computer's cards remaining: " + cc);
                    pc1 = p1.getSuit() + " " + p1.getValue();
                    p1Draw.setText("Player's card: " + pc1);
                    cc1 = p2.getSuit() + " " + p2.getValue();
                    p2Draw.setText("Computer's card: " + cc1);
                    

                } else if (p1.getRank() < p2.getRank()) {
                    //adds card to players 2's hand if they win
                    hand2.addLast(p1);
                    hand2.addLast(p2);

                    rdW = "Computer";
                    roundWon.setText("Round Winner is: " + rdW);
                    pc -= 1;
                    p1Hand.setText("Player's cards remaining: " + pc);
                    cc += 1;
                    p2Hand.setText("Computer's cards remaining: " + cc);
                    pc1 = p1.getSuit() + " " + p1.getValue();
                    p1Draw.setText("Player's card: " + pc1);
                    cc1 = p2.getSuit() + " " + p2.getValue();
                    p2Draw.setText("Computer's card: " + cc1);
                   

                } else {
                    //war if cards match

                    pc1 = p1.getSuit() + " " + p1.getValue();
                    p1Draw.setText("Player's card: " + pc1);
                    cc1 = p2.getSuit() + " " + p2.getValue();
                    p2Draw.setText("Computer's card: " + cc1);


                    ArrayList<Card> war1 = new ArrayList<Card>();
                    ArrayList<Card> war2 = new ArrayList<Card>();

                    for (int x = 0; x < 3; x++) {

                        // checks to see if players have enough cards in their hands to continue
                        if (hand1.isEmpty()) {

                            wnr = "Computer";
                            winner.setText("Winner is: " + wnr);
                            rnd = 0;
                            round.setText("Round#: " + rnd);

                            break;

                        }//end of if
                        else if (hand2.isEmpty()) {

                            wnr = "Player";
                            winner.setText("Winner is: " + wnr);
                            rnd = 0;
                            round.setText("Round#: " + rnd);
                            break;

                        }//end of elseif
                        war1.add(hand1.pop());
                        war2.add(hand2.pop());

                    }//end of for

                    if (war1.size() == 3 && war2.size() == 3) {

                        if (war1.get(2).getRank() > war2.get(2).getRank()) {
                            hand1.addAll(war1);
                            hand1.addAll(war2);

                            warG = "War Declared: Player Wins";
                            warGm.setText(warG);
                            pc += 3;
                            p1Hand.setText("Player's cards remaining: " + pc);
                            cc -= 3;
                            p2Hand.setText("Computer's cards remaining: " + cc);

                        }//end of if
                        else {
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
                if (hand1.isEmpty()) {

                    wnr = "Computer";
                    winner.setText("Winner is: " + wnr);
                    rnd = 0;
                    round.setText("Round#: " + rnd);

                } else if (hand2.isEmpty()) {

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
        warLayout.getChildren().addAll(winner, round, roundWon, p1Hand, p1Draw, p2Draw, p2Hand, warGm);
        warLayout.getChildren().addAll(drawButton);
        warLayout.getChildren().addAll( pD);
        primaryStage.setScene(war);
        primaryStage.show();

    }//end of start

}//end of class

