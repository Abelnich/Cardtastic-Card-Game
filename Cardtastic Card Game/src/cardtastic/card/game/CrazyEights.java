/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardtastic.card.game;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
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
    // Player hand can only display 19 cards currently
    // CPUs hands are limited to displaying 14 cards

    private ArrayList<Card> discardPile = new ArrayList();

    private ArrayList<Card> arryLst_playerHand;
    private ArrayList<ImageView> arryLst_playersIVs;
    private ArrayList<Card> arryLst_cpuHand_1;
    private ArrayList<ImageView> arryLst_cpu1IVs;
    private ArrayList<Card> arryLst_cpuHand_2;
    private ArrayList<ImageView> arryLst_cpu2IVs;
    private ArrayList<Card> arryLst_cpuHand_3;
    private ArrayList<ImageView> arryLst_cpu3IVs;

    private ArrayList<Card>[] arry_hands = new ArrayList[4];
    private ArrayList<ImageView>[] arry_handDisplays = new ArrayList[4];

    private Card card_currentDiscard, selectedCard;
    private Deck deck;
    private String str_cardBack;
    private ImageView iv_discard, iv_deck;

    // Layout Stuff
    private HBox hb_cpu2, hb_pHand, hb_Bottom;
    private VBox vb_cpu1, vb_cpu3;
    private Button btn_next, btn_again;
    private Circle circ_turnIndicator;
    // Layout Stuff

    private Boolean playersTurn = false, winner = false;

    @Override
    public void start(Stage primaryStage) {

        FileReader fr = new FileReader("Settings.txt");
        str_cardBack = fr.readFile().get(0);

        selectedCard = null;

        deck = new Deck();
        deck.Shuffle();

        arryLst_playerHand = new ArrayList();
        arryLst_playersIVs = new ArrayList();
        arryLst_cpuHand_1 = new ArrayList();
        arryLst_cpu1IVs = new ArrayList();
        arryLst_cpuHand_2 = new ArrayList();
        arryLst_cpu2IVs = new ArrayList();
        arryLst_cpuHand_3 = new ArrayList();
        arryLst_cpu3IVs = new ArrayList();

        arry_hands[0] = arryLst_playerHand;
        arry_hands[1] = arryLst_cpuHand_1;
        arry_hands[2] = arryLst_cpuHand_2;
        arry_hands[3] = arryLst_cpuHand_3;

        arry_handDisplays[0] = arryLst_playersIVs;
        arry_handDisplays[1] = arryLst_cpu1IVs;
        arry_handDisplays[2] = arryLst_cpu2IVs;
        arry_handDisplays[3] = arryLst_cpu3IVs;

        for (int i = 1; i <= 5; i++) {
            arryLst_playerHand.add(deck.deal());
            arryLst_cpuHand_1.add(deck.deal());
            arryLst_cpuHand_2.add(deck.deal());
            arryLst_cpuHand_3.add(deck.deal());
        }

        hb_cpu2 = new HBox();
        hb_cpu2.setAlignment(Pos.CENTER);
        hb_cpu2.setSpacing(-75);
        for (int i = 0; i < arryLst_cpuHand_2.size(); i++) {
            ImageView iv = createIV();
            hb_cpu2.getChildren().add(iv);
            arryLst_cpu2IVs.add(iv);
        }

        HBox midHB = new HBox();
        midHB.setAlignment(Pos.CENTER);

        vb_cpu1 = new VBox();
        vb_cpu1.setAlignment(Pos.CENTER);
        vb_cpu1.setSpacing(-112.5);
        for (int i = 0; i < arryLst_cpuHand_1.size(); i++) {
            ImageView iv = createIV();
            iv.setRotate(-90);
            vb_cpu1.getChildren().add(iv);
            arryLst_cpu1IVs.add(iv);
        }

        vb_cpu3 = new VBox();
        vb_cpu3.setAlignment(Pos.CENTER);
        vb_cpu3.setSpacing(-112.5);
        for (int i = 0; i < arryLst_cpuHand_3.size(); i++) {
            ImageView iv = createIV();
            iv.setRotate(90);
            vb_cpu3.getChildren().add(iv);
            arryLst_cpu3IVs.add(iv);
        }

        hb_pHand = new HBox();
        hb_pHand.setAlignment(Pos.CENTER);
        for (int i = 0; i < arryLst_playerHand.size(); i++) {
            // Add player cards to the screen
            ImageView iv = createIV(arryLst_playerHand.get(i), false);
            hb_pHand.getChildren().add(iv);
            arryLst_playersIVs.add(iv);
        }

        card_currentDiscard = deck.deal(); // First card to start game
        iv_discard = createIV(card_currentDiscard, true);

        iv_deck = createIV();
        iv_deck.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
            @Override
            public void handle(Event event) {
                if (playersTurn) {
                    // Checks if deck is empty; will refill from discard pile except for the current card
                    if (deck.getDeck().size() <= 1) {
                        for (int i = 0; i < discardPile.size(); i++) {
                            Card c = discardPile.get(i);
                            if (!c.equals(card_currentDiscard)) {
                                deck.getDeck().add(c);
                                discardPile.remove(c);
                            }
                        }
                    } // end check for empty deck
                    Card drawnCard = deck.deal();
                    arryLst_playerHand.add(drawnCard);

                    ImageView drawnIV = createIV(drawnCard, false);
                    arryLst_playersIVs.add(drawnIV);
                    hb_pHand.getChildren().add(drawnIV);
                }
            }
        });

        // Adds invisble spacers to help with alignment
        Spacer spaceL = new Spacer();
        Spacer spaceM = new Spacer();
        Spacer spaceR = new Spacer();

        midHB.getChildren().addAll(vb_cpu1, spaceL, iv_discard, spaceM, iv_deck, spaceR, vb_cpu3);

        hb_Bottom = new HBox();
        hb_Bottom.setAlignment(Pos.CENTER);

        // Circle to inform the player if it is their turn or not
        circ_turnIndicator = new Circle(25);
        circ_turnIndicator.setFill(Color.RED);

        // Makes the computer players go so the player can play again; in place of an infinite loop
        btn_next = new Button("Start");
        btn_next.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                // Changes next turn button to say next turn after the game has started
                if (btn_next.getText().equals("Start")) {
                    btn_next.setText("Next Turn");
                }
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
                    circ_turnIndicator.setFill(Color.LIME);
                }
            }
        });

        // To play the game again after it has completed.
        btn_again = new Button("Play Again");
        btn_again.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                CrazyEights crazyGame = new CrazyEights();
                Stage secondaryStage = new Stage();
                secondaryStage.setMaximized(true); // maximizes the window for the next game
                primaryStage.close();
                crazyGame.start(secondaryStage);
            }
        });
        btn_again.setVisible(false);

        // For testing
        Button btn_crazy = new Button("Go Crazy");
        btn_crazy.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //showWild();
            }
        });

        btn_crazy.setVisible(false);

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

        hb_Bottom.getChildren().addAll(circ_turnIndicator, btn_next, btn_crazy, mainBtn, btn_again);

        VBox screenVB = new VBox();
        screenVB.setAlignment(Pos.CENTER);
        screenVB.getChildren().addAll(hb_cpu2, midHB, hb_pHand, hb_Bottom);

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
            for (Card c : arry_hands[num]) {
                // Iterate through each card of the hand
                if (c.getSuit().equals(card_currentDiscard.getSuit()) || c.getValue().equals(card_currentDiscard.getValue())) {
                    arry_hands[num].remove(c);
                    if (arry_hands[num].size() <= 14) {
                        // Won't remove an IV if there's more than 14 cards in computers hand
                        switch (num) {

                            case 1:
                                arryLst_cpu1IVs.remove(0);
                                vb_cpu1.getChildren().remove(0);
                                break;
                            case 2:
                                arryLst_cpu2IVs.remove(0);
                                hb_cpu2.getChildren().remove(0);
                                break;
                            case 3:
                                arryLst_cpu3IVs.remove(0);
                                vb_cpu3.getChildren().remove(0);
                                break;
                        }
                    }
                    discardPile.add(c);
                    card_currentDiscard = c;
                    iv_discard.setImage(c.getImageFile());
                    if (arry_hands[num].isEmpty()) {
                        winner = true;
                        btn_again.setVisible(true);

                        // Winner Alert
                        Alert winnerAlert = new Alert(Alert.AlertType.INFORMATION);
                        winnerAlert.setTitle("Game Over");
                        winnerAlert.setHeaderText("CPU " + num + " Wins!");
                        winnerAlert.setContentText("It is possible to commit no mistakes and still lose. That is not a weakness; that is life.");
                        winnerAlert.show();
                        // end Winner Alert

                        btn_next.setVisible(false);

                        break whileLoop;
                    }
                    played = true;
                    break whileLoop;
                } // end if equal to discard check
            } // end for each

            // Will reach here if hand does not have a suitable hand
            // Checks if deck is empty; will refill from discard pile except for the current card
            if (deck.getDeck().size() == 1) {
                for (int i = 0; i < discardPile.size(); i++) {
                    Card c = discardPile.get(i);
                    if (!c.equals(card_currentDiscard)) {
                        deck.getDeck().add(c);
                        discardPile.remove(c);
                    }
                }
            } // end check for empty deck   
            // CPU Drawing: 
            // Adds card to hand and goes back through again
            arry_hands[num].add(deck.deal());
            if (arry_hands[num].size() + 1 <= 14) {
                // Won't add another IV if it doesn't fit 
                switch (num) {
                    case 1:
                        ImageView iv1 = createIV();
                        iv1.setRotate(90);
                        vb_cpu1.getChildren().add(iv1);
                        arryLst_cpu1IVs.add(iv1);
                        break;
                    case 2:
                        ImageView iv2 = createIV();
                        hb_cpu2.getChildren().add(iv2);
                        arryLst_cpu2IVs.add(iv2);
                        break;
                    case 3:
                        ImageView iv3 = createIV();
                        iv3.setRotate(90);
                        vb_cpu3.getChildren().add(iv3);
                        arryLst_cpu3IVs.add(iv3);
                        break;
                } // end switch 
            }

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
                        if (c.getValue().equals("8")) {
                            // Wild
                            showWild(iv, c);
                        } else {
                            if (c.getSuit().equals(card_currentDiscard.getSuit()) || c.getValue().equals(card_currentDiscard.getValue())) {
                                card_currentDiscard = c;
                                discardPile.add(c);
                                iv_discard.setImage(c.getImageFile());

                                arryLst_playersIVs.remove(iv);
                                hb_pHand.getChildren().remove(iv);
                                arryLst_playerHand.remove(c);
                                if (arryLst_playerHand.isEmpty()) {
                                    winner = true;
                                    btn_again.setVisible(true);
                                    // Winner Alert
                                    Alert winnerAlert = new Alert(Alert.AlertType.INFORMATION);
                                    winnerAlert.setTitle("Game Over");
                                    winnerAlert.setHeaderText("You Won!");
                                    GiveMeInspiration ins = new GiveMeInspiration();
                                    winnerAlert.setContentText(ins.give());
                                    winnerAlert.show();
                                    // end Winner Alert
                                } else {
                                    playersTurn = false;
                                    circ_turnIndicator.setFill(Color.RED);
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
    } // end createIV(Card, boolean)

    private ImageView createIV() {
        // To create imageviews for the computers' hands showing just the card back
        Image back = new Image("resources/" + str_cardBack);
        ImageView iv = new ImageView(back);
        iv.setFitHeight(150.2);
        iv.setFitWidth(100);
        iv.setPreserveRatio(true);
        return iv;
    } // end createIV()

    private void showWild(ImageView iv_played, Card card_played) {

        // Disables player cards and deck
        for (ImageView iv : arryLst_playersIVs) {
            iv.setVisible(false);
        }
        iv_deck.setDisable(true);

        ImageView iv_spade = wildIV("Spades");
        ImageView iv_diamond = wildIV("Diamonds");
        ImageView iv_club = wildIV("Clubs");
        ImageView iv_heart = wildIV("Hearts");

        // Event handlers: create a 8 of respective suit and plays it. Does not get added to deck when reloaded
        iv_spade.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                // Player switches to spades
                card_currentDiscard = new Card("Spades", "8");
                hb_pHand.getChildren().removeAll(iv_spade, iv_diamond, iv_club, iv_heart);
                wildPlayed(iv_played, card_played);
            }
        });
        iv_diamond.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                // Player switches to diamonds
                card_currentDiscard = new Card("Diamonds", "8");
                hb_pHand.getChildren().removeAll(iv_spade, iv_diamond, iv_club, iv_heart);
                wildPlayed(iv_played, card_played);
            }
        });
        iv_club.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                // Player switches to clubs
                card_currentDiscard = new Card("Clubs", "8");
                hb_pHand.getChildren().removeAll(iv_spade, iv_diamond, iv_club, iv_heart);
                wildPlayed(iv_played, card_played);
            }
        });
        iv_heart.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                // Player switches to hearts
                card_currentDiscard = new Card("Hearts", "8"); // creates fake card for discard display
                hb_pHand.getChildren().removeAll(iv_spade, iv_diamond, iv_club, iv_heart);
                wildPlayed(iv_played, card_played);
            }
        });

        hb_pHand.getChildren().addAll(iv_spade, iv_diamond, iv_club, iv_heart);

    } // end showWild()

    private void wildPlayed(ImageView iv_played, Card card_played) {
        // Places the fake card into the discard
        iv_discard.setImage(card_currentDiscard.getImageFile());

        // Enables player card and deck
        for (ImageView iv : arryLst_playersIVs) {
            iv.setVisible(true);
        }
        iv_deck.setDisable(false);

        arryLst_playersIVs.remove(iv_played);
        hb_pHand.getChildren().remove(iv_played);
        arryLst_playerHand.remove(card_played);
        if (arryLst_playerHand.isEmpty()) {
            winner = true;
            btn_again.setVisible(true);
            // Winner Alert
            Alert winnerAlert = new Alert(Alert.AlertType.INFORMATION);
            winnerAlert.setTitle("Game Over");
            winnerAlert.setHeaderText("You Won!");
            GiveMeInspiration ins = new GiveMeInspiration();
            winnerAlert.setContentText(ins.give());
            winnerAlert.show();
            // end Winner Alert
        } else {
            playersTurn = false;
            circ_turnIndicator.setFill(Color.RED);
        }
    }

    private ImageView wildIV(String suit) {
        // for creating the suit only cards
        String fileName;
        switch (suit) {
            case "Diamonds":
                fileName = "DD.png";
                break;
            case "Clubs":
                fileName = "CC.png";
                break;
            case "Hearts":
                fileName = "HH.png";
                break;
            default:
                fileName = "SS.png";
                break;
        }

        Image img_wild = new Image("resources/" + fileName);

        ImageView iv_wild = createIV();
        iv_wild.setImage(img_wild);

        Card card_wild = new Card(suit, "8");

        return iv_wild;
    }
}

class Spacer extends Rectangle {

    public Spacer() {
        super(100, 100);
        this.setFill(Color.TRANSPARENT);
    }

}

class GiveMeInspiration {

    ArrayList<String> inspirations;

    public GiveMeInspiration() {
        // inspirations.add("");
        inspirations = new ArrayList();
        inspirations.add("If you fear failure, you will never go anywhere.");
        inspirations.add("Remember to drink water.");
        inspirations.add("One must learn to be content with being happier than they think they deserve");
        inspirations.add("Life's true gift is the capacity to enjoy enjoyment.");
        inspirations.add("You Heard About Pluto? That's messed up, right?");
        inspirations.add("I don’t lose things. I place things in locations which later elude me.");
        inspirations.add("If he didn't have any hair then no one had any business calling him Fuzzy Wuzzy.");
        inspirations.add("Ross and Rachel were on a break.");
    }

    public String give() {
        Random rand = new Random();
        return (inspirations.get(rand.nextInt(inspirations.size())));
    }
}
