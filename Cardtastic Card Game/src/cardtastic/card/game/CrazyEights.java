/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardtastic.card.game;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author NickAbel
 */
public class CrazyEights extends Application {
    // hands can only display 12 cards currently
    
    private ArrayList<Card> discardPile = new ArrayList();
    private ArrayList<Card> playerHand;
    private ArrayList<Card> cpuHand_1;
    private ArrayList<Card> cpuHand_2;
    private ArrayList<Card> cpuHand_3;
    
    private Card currentDiscard;
    private Deck deck;
    private Boolean[] turn = {true, false, false, false};
    private ArrayList[] hands = {playerHand, cpuHand_1, cpuHand_2, cpuHand_3};
    
    @Override
    public void start(Stage primaryStage) {

        deck = new Deck();
        deck.Shuffle();
        
        playerHand = new ArrayList();
        cpuHand_1 = new ArrayList();
        cpuHand_2 = new ArrayList();
        cpuHand_3 = new ArrayList();
        
        for (int i = 1; i <= 5; i++) {
            playerHand.add(deck.deal());
            cpuHand_1.add(deck.deal());
            cpuHand_2.add(deck.deal());
            cpuHand_3.add(deck.deal());
        }
        
        HBox cpu2HB = new HBox();
        cpu2HB.setAlignment(Pos.CENTER);
        cpu2HB.setSpacing(-75);
        for (int i = 0; i < cpuHand_2.size(); i++) {
            ImageView iv = createIV();
            cpu2HB.getChildren().add(iv);
        }
        
        HBox midHB = new HBox();
        midHB.setAlignment(Pos.CENTER);
        
        VBox cpu1VB = new VBox();
        cpu1VB.setAlignment(Pos.CENTER);
        cpu1VB.setSpacing(-112.5);
        for (int i = 0; i < cpuHand_1.size(); i++) {
            ImageView iv = createIV();
            iv.setRotate(-90);
            cpu1VB.getChildren().add(iv);
        }
        
        VBox cpu3VB = new VBox();
        cpu3VB.setAlignment(Pos.CENTER);
        cpu3VB.setSpacing(-112.5);
        for (int i = 0; i < cpuHand_3.size(); i++) {
            ImageView iv = createIV();
            iv.setRotate(90);
            cpu3VB.getChildren().add(iv);
        }
        
        HBox pHandHB = new HBox();
        pHandHB.setAlignment(Pos.CENTER);
        for (int i = 0; i < playerHand.size(); i++) {
            // Add player cards to the screen
            ImageView iv = createIV(playerHand.get(i));
            pHandHB.getChildren().add(iv);
        }
        
        currentDiscard = deck.deal(); // First card to start game
        ImageView discardIV = createIV(currentDiscard);
        
        ImageView deckIV = createIV();
        deckIV.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
            @Override
            public void handle(Event event) {
                if (turn[0]) {
                    Card newCard = deck.deal();
                    playerHand.add(newCard);
                    pHandHB.getChildren().add(createIV(newCard));
                }
            }
        });
        
        // Adds invisble spacers to help with alignment
        Spacer spacerL = new Spacer();
        Spacer spacerM = new Spacer();
        Spacer spacerR = new Spacer();
        
        midHB.getChildren().addAll(cpu1VB, spacerL, discardIV, spacerM, deckIV, spacerR, cpu3VB);
        
        VBox screenVB = new VBox();
        screenVB.setAlignment(Pos.CENTER);
        screenVB.getChildren().addAll(cpu2HB, midHB, pHandHB);
        
        StackPane root = new StackPane();       
        root.getChildren().add(screenVB);
        Scene scene = new Scene(root, 1200, 700);
        
        primaryStage.setTitle("Crazy Eights");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Play the game
//        while ( !playerHand.isEmpty() || !cpuHand_1.isEmpty() || !cpuHand_2.isEmpty() || !cpuHand_3.isEmpty() ) {
//            turn[0] = true;
//            while(turn[0]) {
//                turn[0] = false;
//            }
//            
//            
//        }
        
    }
    
    private void play(int num) {
        // Space used for planning
        ArrayList<Card> hand = hands[num];
        int total_spades = 0, total_diamonds = 0, total_clubs = 0, total_hearts = 0;
        int[] total_nums = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        
        // Populate counters above
        for (int i = 0; i < hand.size(); i++) {
            switch (hand.get(i).getSuit()) {
                case "Spades":
                    total_spades++;
                    break;
                case "Diamonds":
                    total_diamonds++;
                    break;
                case "Clubs":
                    total_clubs++;
                    break;
                case "Hearts":
                    total_hearts++;
                    break;
            }
            
            if (hand.get(i).getValue().equals("A")) {
                total_nums[0]++;
            } else {
                total_nums[hand.get(i).getRank()]++;
            }
        } // end populating for loop
        
        for (int i = 0; i < hand.size(); i++) {
            if (currentDiscard.getValue().equals(hand.get(i).getValue()) || currentDiscard.getSuit().equals(hand.get(i).getSuit())) {
                currentDiscard = hand.get(i);
                hand.remove(hand.get(i)); 
            } else {
                // Player doesn't have a card to play
                if (total_nums[8] != 0) {
                    
                }
            }
        }
        
    } // end play()
    
    private ImageView createIV(Card c) {
        ImageView iv = new ImageView(c.getImageFile());
        iv.setFitHeight(150.2);
        iv.setFitWidth(100);
        iv.setPreserveRatio(true);
        return iv;
    }
    
    private ImageView createIV() {
        Image back = new Image("resources/CardBack_Blue.png");
        ImageView iv = new ImageView(back);
        iv.setFitHeight(150.2);
        iv.setFitWidth(100);
        iv.setPreserveRatio(true);
        return iv;
    }
    
}

class Spacer extends Rectangle {
  
    public Spacer() {
        super(100, 100);
        this.setFill(Color.TRANSPARENT);
    }
    
}
