/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardtastic.card.game;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author NickAbel
 */
public class CrazyEights extends Application {
    
    @Override
    public void start(Stage primaryStage) {

        Deck myDeck = new Deck();
        myDeck.Shuffle();
        
        ArrayList<Card> playerHand = new ArrayList();
        ArrayList<Card> cpuHand_1 = new ArrayList();
        ArrayList<Card> cpuHand_2 = new ArrayList();
        ArrayList<Card> cpuHand_3 = new ArrayList();
        
        for (int i = 1; i <= 5; i++) {
            playerHand.add(myDeck.deal());
            cpuHand_1.add(myDeck.deal());
            cpuHand_2.add(myDeck.deal());
            cpuHand_3.add(myDeck.deal());
        }
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.println("My Hand: " + " " + playerHand.get(i).getInfo());
        }
        
        StackPane root = new StackPane();
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Crazy Eights");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
