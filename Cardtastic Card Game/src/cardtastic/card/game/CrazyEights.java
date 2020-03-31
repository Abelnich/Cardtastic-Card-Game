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
    private ArrayList<Card> playerHand; private ArrayList<ImageView> playersIVs;
    private ArrayList<Card> cpuHand_1;
    private ArrayList<Card> cpuHand_2;
    private ArrayList<Card> cpuHand_3;

    private Card currentDiscard, selectedCard;
    private Deck deck;
    private String cardBack;
    private ImageView discardIV;
    
    // Layout Stuff
    HBox cpu2HB, pHandHB;
    VBox cpu1VB, cpu3VB;
    // Layout Stuff

    private Boolean playersTurn = true;

    @Override
    public void start(Stage primaryStage) {

        FileReader fr = new FileReader("Settings.txt");
        cardBack = fr.readFile().get(0);
        
        selectedCard = null;

        deck = new Deck();
        deck.Shuffle();

        playerHand = new ArrayList(); playersIVs = new ArrayList();
        cpuHand_1 = new ArrayList();
        cpuHand_2 = new ArrayList();
        cpuHand_3 = new ArrayList();

        for (int i = 1; i <= 5; i++) {
            playerHand.add(deck.deal());
            cpuHand_1.add(deck.deal());
            cpuHand_2.add(deck.deal());
            cpuHand_3.add(deck.deal());
        }

        cpu2HB = new HBox();
        cpu2HB.setAlignment(Pos.CENTER);
        cpu2HB.setSpacing(-75);
        for (int i = 0; i < cpuHand_2.size(); i++) {
            ImageView iv = createIV();
            cpu2HB.getChildren().add(iv);
        }

        HBox midHB = new HBox();
        midHB.setAlignment(Pos.CENTER);

        cpu1VB = new VBox();
        cpu1VB.setAlignment(Pos.CENTER);
        cpu1VB.setSpacing(-112.5);
        for (int i = 0; i < cpuHand_1.size(); i++) {
            ImageView iv = createIV();
            iv.setRotate(-90);
            cpu1VB.getChildren().add(iv);
        }

        cpu3VB = new VBox();
        cpu3VB.setAlignment(Pos.CENTER);
        cpu3VB.setSpacing(-112.5);
        for (int i = 0; i < cpuHand_3.size(); i++) {
            ImageView iv = createIV();
            iv.setRotate(90);
            cpu3VB.getChildren().add(iv);
        }

        pHandHB = new HBox();
        pHandHB.setAlignment(Pos.CENTER);
        for (int i = 0; i < playerHand.size(); i++) {
            // Add player cards to the screen
            ImageView iv = createIV(playerHand.get(i), false);
            pHandHB.getChildren().add(iv);
            playersIVs.add(iv);
        }

        currentDiscard = deck.deal(); // First card to start game
        discardIV = createIV(currentDiscard, true);
        discardIV.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
            @Override
            public void handle(Event event) {
//                System.out.println("yeha");
//                if (playersTurn) {
//                    if (selectedCard != null) {
//                        // if the player has selected a card
//                       if (currentDiscard.getValue().equals(selectedCard.getValue()) || currentDiscard.getSuit().equals(selectedCard.getSuit())) {
//                            playerHand.remove(selectedCard);
//                            currentDiscard = selectedCard;
//                            discardIV.setImage(selectedCard.getImageFile());
//                            playersTurn = false;
//                        } else if (selectedCard.getValue().equals("8")) {
//                            // Player has played a eight
//                            
//                        } else {
//                            System.out.println("not possible");
//                        }
//                    }
//                }
            }
        });

        ImageView deckIV = createIV();
        deckIV.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
            @Override
            public void handle(Event event) {
                if (playersTurn) {
                    playerHand.add(deck.deal());
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
        
        root.setStyle(fr.readFile().get(1));
        
        Scene scene = new Scene(root, 1200, 700);

        primaryStage.setTitle("Crazy Eights");
        primaryStage.setScene(scene);
        primaryStage.show();

        

    }

    private void play(int num) {
        

    } // end play()

    private ImageView createIV(Card c, boolean isDiscard) {
        ImageView iv = new ImageView(c.getImageFile());
        
        if (!isDiscard) {
            // If it is a discard IV, it doesn't need the click event
            iv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
                @Override
                public void handle(Event event) {
                    if (c.getValue().equals("8")) {
                        // Wild
                        System.out.println("Its an eight, everyone panic!");
                    } else {
                        if (c.getSuit().equals(currentDiscard.getSuit()) || c.getValue().equals(currentDiscard.getValue())) {
                            currentDiscard = c;
                            discardPile.add(c);
                            discardIV.setImage(c.getImageFile());
                            
                            playersIVs.remove(iv);
                            pHandHB.getChildren().remove(iv);
                            playerHand.remove(c);
                            playersTurn = false;
                        } else {
                            System.out.println("Can't play that buddy.");
                        }
                    }
                    
                    selectedCard = c;
                }
            });
        }
        iv.setFitHeight(150.2);
        iv.setFitWidth(100);
        iv.setPreserveRatio(true);
        return iv;
    }

    private ImageView createIV() {
        // To create imageviews for the computers' hands
        Image back = new Image("resources/" + cardBack);
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
