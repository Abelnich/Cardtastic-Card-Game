/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardtastic.card.game;

import com.sun.org.apache.bcel.internal.generic.GOTO;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author NickAbel
 */
public class CrazyEights extends Application {
    // hands can only display 12 cards currently

    private ArrayList<Card> discardPile = new ArrayList();

    private ArrayList<Card> playerHand;
    private ArrayList<ImageView> playersIVs;
    private ArrayList<Card> cpuHand_1;
    private ArrayList<ImageView> cpu1IVs;
    private ArrayList<Card> cpuHand_2;
    private ArrayList<ImageView> cpu2IVs;
    private ArrayList<Card> cpuHand_3;
    private ArrayList<ImageView> cpu3IVs;

    private ArrayList<Card>[] hands = new ArrayList[4];
    private ArrayList<ImageView>[] handDisplays = new ArrayList[4];

    private Card currentDiscard, selectedCard;
    private Deck deck;
    private String cardBack;
    private ImageView discardIV;

    // Layout Stuff
    private HBox cpu2HB, pHandHB;
    private VBox cpu1VB, cpu3VB;
    private Button nextBtn, againBtn;
    private Circle turnIndicator;
    // Layout Stuff

    private Boolean playersTurn = false, winner = false;

    @Override
    public void start(Stage primaryStage) {

        FileReader fr = new FileReader("Settings.txt");
        cardBack = fr.readFile().get(0);

        selectedCard = null;

        deck = new Deck();
        deck.Shuffle();

        playerHand = new ArrayList();
        playersIVs = new ArrayList();
        cpuHand_1 = new ArrayList();
        cpu1IVs = new ArrayList();
        cpuHand_2 = new ArrayList();
        cpu2IVs = new ArrayList();
        cpuHand_3 = new ArrayList();
        cpu3IVs = new ArrayList();

        hands[0] = playerHand;
        hands[1] = cpuHand_1;
        hands[2] = cpuHand_2;
        hands[3] = cpuHand_3;

        handDisplays[0] = playersIVs;
        handDisplays[1] = cpu1IVs;
        handDisplays[2] = cpu2IVs;
        handDisplays[3] = cpu3IVs;

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
                    // Checks if deck is empty; will refill from discard pile except for the current card
                    if (deck.getDeck().size() == 1) {
                        System.out.println("Cover me I'm reloading");
                        for (int i = 0; i < discardPile.size(); i++) {
                            Card c = discardPile.get(i);
                            if (!c.equals(currentDiscard)) {
                                deck.getDeck().add(c);
                                discardPile.remove(c);
                            }
                        }
                    } // end check for empty deck
                    Card drawnCard = deck.deal();
                    ImageView drawnIV = createIV(drawnCard, false);
                    playerHand.add(drawnCard);
                    playersIVs.add(drawnIV);
                    pHandHB.getChildren().add(drawnIV);
                }
            }
        });

        // Adds invisble spacers to help with alignment
        Spacer spaceL = new Spacer();
        Spacer spaceM = new Spacer();
        Spacer spaceR = new Spacer();

        midHB.getChildren().addAll(cpu1VB, spaceL, discardIV, spaceM, deckIV, spaceR, cpu3VB);

        HBox bottomHB = new HBox();
        bottomHB.setAlignment(Pos.CENTER);
        UserInfo user = new UserInfo();
        Label playerName = new Label(user.getActiveUser());
        playerName.setFont(Font.font(16));
        turnIndicator = new Circle(25);
        turnIndicator.setFill(Color.RED);

        nextBtn = new Button("Next Turn");
        nextBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (!playersTurn && !winner) {
                    if (!winner) {
                        play(1);
                    }
                    if (!winner) {
                        play(2);
                    }
                    if (!winner) {
                        play(3);
                    }
                    playersTurn = true;
                    turnIndicator.setFill(Color.LIME);
                }
            }
        });
        
        againBtn = new Button("Play Again");
        againBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                CrazyEights crazyGame = new CrazyEights();
                crazyGame.start(primaryStage);
            }
        });
        againBtn.setVisible(false);

        Button crazy = new Button("Go Crazy");
        crazy.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

            }
        });

        crazy.setVisible(false);

        Button mainBtn = new Button("Main menu");
        mainBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (!winner) {
                    // Asks for leave confirmation if game is in progress
                    Alert cancelAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    cancelAlert.setTitle("Go back to main menu?");
                    cancelAlert.setHeaderText("You are about to abandon the game.");
                    cancelAlert.setContentText("Are you sure?");

                    Optional<ButtonType> result = cancelAlert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        Main main = new Main();
                        try {
                            main.start(primaryStage);
                        } catch (Exception e2) {
                            System.out.println("Exception going to Main with confimation: " + e2.getMessage());
                        }
                    }
                } else {
                    Main main = new Main();
                    try {
                        main.start(primaryStage);
                    } catch (Exception e3) {
                        System.out.println("Exception going to Main without confimation: " + e3.getMessage());
                    }
                }
            }
        });

        bottomHB.getChildren().addAll(turnIndicator, nextBtn, crazy, mainBtn, againBtn);

        VBox screenVB = new VBox();
        screenVB.setAlignment(Pos.CENTER);
        screenVB.getChildren().addAll(cpu2HB, midHB, pHandHB, bottomHB);

        StackPane root = new StackPane();
        root.getChildren().add(screenVB);

        root.setStyle(fr.readFile().get(1));

        Scene scene = new Scene(root, 1200, 700);

        primaryStage.setTitle("Crazy Eights");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

    }// end start()

    private void play(int num) {
        // Num = 0 is for the player; should only be used if player needs to be simulated
        Boolean played = false;

        whileLoop:
        while (!played) {
            for (Card c : hands[num]) {
                // Iterate through each card of the hand
                if (c.getSuit().equals(currentDiscard.getSuit()) || c.getValue().equals(currentDiscard.getValue())) {
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
                    discardPile.add(c);
                    currentDiscard = c;
                    discardIV.setImage(c.getImageFile());
                    if (hands[num].isEmpty()) {
                        winner = true;
                        againBtn.setVisible(true);
                        System.out.println("CPU " + num + " Wins!");

                        // Winner Alert
                        Alert winnerAlert = new Alert(Alert.AlertType.INFORMATION);
                        winnerAlert.setTitle("Game Over");
                        winnerAlert.setHeaderText("CPU " + num + " Wins!");
                        winnerAlert.setContentText("Better luck next time pal :/");
                        winnerAlert.show();
                        // end Winner Alert

                        nextBtn.setVisible(false);

                        break whileLoop;
                    }
                    played = true;
                    break whileLoop;
                }
            } // end for each

            // Will reach here if hand does not have a suitable hand
            // Checks if deck is empty; will refill from discard pile except for the current card
            if (deck.getDeck().size() == 1) {
                for (int i = 0; i < discardPile.size(); i++) {
                    Card c = discardPile.get(i);
                    if (!c.equals(currentDiscard)) {
                        deck.getDeck().add(c);
                        discardPile.remove(c);
                    }
                }
            } // end check for empty deck
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
                    ImageView iv2 = createIV();
                    cpu2HB.getChildren().add(iv2);
                    cpu2IVs.add(iv2);
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
                    if (playersTurn) {
                        if (c.getValue().equals("800")) {
                            // Wild
                            System.out.println("Its an eight hundred, everyone panic!");
                        } else {
                            if (c.getSuit().equals(currentDiscard.getSuit()) || c.getValue().equals(currentDiscard.getValue())) {
                                currentDiscard = c;
                                discardPile.add(c);
                                discardIV.setImage(c.getImageFile());

                                playersIVs.remove(iv);
                                pHandHB.getChildren().remove(iv);
                                playerHand.remove(c);
                                if (playerHand.isEmpty()) {
                                    winner = true;
                                    againBtn.setVisible(true);
                                    // Winner Alert
                                    Alert winnerAlert = new Alert(Alert.AlertType.INFORMATION);
                                    winnerAlert.setTitle("Game Over");
                                    winnerAlert.setHeaderText("You Won!");
                                    winnerAlert.setContentText("Remember to drink water.");
                                    winnerAlert.show();
                                    // end Winner Alert
                                } else {
                                    playersTurn = false;
                                    turnIndicator.setFill(Color.RED);
                                }
                            }
                        } // end check for 8
                    }// end if (playersTurn)

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
