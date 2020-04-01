/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardtastic.card.game;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
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
    private ArrayList<Card> cpuHand_1; private ArrayList<ImageView> cpu1IVs;
    private ArrayList<Card> cpuHand_2; private ArrayList<ImageView> cpu2IVs;
    private ArrayList<Card> cpuHand_3; private ArrayList<ImageView> cpu3IVs;
    
    private ArrayList<Card>[] hands = new ArrayList[4];
    private ArrayList<ImageView>[] handDisplays = new ArrayList[4];

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
        cpuHand_1 = new ArrayList(); cpu1IVs = new ArrayList();
        cpuHand_2 = new ArrayList(); cpu2IVs = new ArrayList();
        cpuHand_3 = new ArrayList(); cpu3IVs = new ArrayList();
        
        hands[0] = playerHand; hands[1] = cpuHand_1; hands[2] = cpuHand_2; hands[3] = cpuHand_3;
        
        handDisplays[0] = playersIVs; handDisplays[1] = cpu1IVs; handDisplays[2] = cpu2IVs; handDisplays[3] = cpu3IVs;

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
            cpu2IVs.add(iv);
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
            cpu1IVs.add(iv);
        }

        cpu3VB = new VBox();
        cpu3VB.setAlignment(Pos.CENTER);
        cpu3VB.setSpacing(-112.5);
        for (int i = 0; i < cpuHand_3.size(); i++) {
            ImageView iv = createIV();
            iv.setRotate(90);
            cpu3VB.getChildren().add(iv);
            cpu3IVs.add(iv);
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
        Spacer spaceL = new Spacer();
        Spacer spaceM = new Spacer();
        Spacer spaceR = new Spacer();

        midHB.getChildren().addAll(cpu1VB, spaceL, discardIV, spaceM, deckIV, spaceR, cpu3VB);

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
        
        
        // * Playing the Game * \\

        // while nobody has won
        
        play(1); play(2); play(3);
        
        // end while
    }
    
    private void play (int num) {
        // Num = 0 is for the player; should only be used if player needs to be simulated
        
        Boolean played = false;
        
        while (!played) {
            for (Card c : hands[num]) {
                if (c.getSuit().equals(currentDiscard.getSuit()) || c.getValue().equals(currentDiscard.getValue())) {
                    System.out.println("Playing " + c.getInfo());
                    hands[num].remove(c);
                    switch (num) {
                        case 1: 
                            cpu1IVs.remove(0);
                            cpu1VB.getChildren().remove(0);
                            break;
                        case 2: 
                            cpu2IVs.remove(0);
                            cpu2HB.getChildren().remove(0);
                            break;
                        case 3: 
                            cpu3IVs.remove(0);
                            cpu3VB.getChildren().remove(0);
                            break;
                    } 
                    handDisplays[num].remove(0);
                    discardPile.add(c);
                    currentDiscard = c;
                    discardIV.setImage(c.getImageFile());
                    played = true;
                    break;
                }
            } // end for each
            
        // Will reach here if hand does not have a suitable hand
        // Adds card to hand and goes back through again
        hands[num].add(deck.deal());
        switch (num) {
            case 1: 
                ImageView iv1 = createIV();
                iv1.setRotate(90);
                cpu1VB.getChildren().add(iv1);
                cpu1IVs.add(iv1);
                break;
            case 2: 
                cpu2HB.getChildren().add(createIV());
                break;
            case 3: 
                ImageView iv3 = createIV();
                iv3.setRotate(90);
                cpu3VB.getChildren().add(iv3);
                cpu3IVs.add(iv3);
                break;
        } // end switch 
        
        } // end while
    }

    private ImageView createIV(Card c, boolean isDiscard) {
        // NOT FOR CPUS
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
        // To create imageviews for the computers' hands showing just the card back
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
