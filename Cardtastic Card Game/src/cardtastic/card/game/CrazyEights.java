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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
        
        HBox cpu2HB = new HBox();
        cpu2HB.setAlignment(Pos.CENTER);
        cpu2HB.setSpacing(20);
        for (int i = 0; i < cpuHand_2.size(); i++) {
            ImageView iv = createIV();
            cpu2HB.getChildren().add(iv);
        }
        
        HBox midHB = new HBox();
        midHB.setAlignment(Pos.CENTER);
        midHB.setSpacing(200);
        
        VBox cpu1VB = new VBox();
        cpu1VB.setAlignment(Pos.CENTER);
        for (int i = 0; i < cpuHand_1.size(); i++) {
            ImageView iv = createIV();
            iv.setRotate(-90);
            cpu1VB.getChildren().add(iv);
        }
        
        VBox cpu3VB = new VBox();
        cpu3VB.setAlignment(Pos.CENTER);
        for (int i = 0; i < cpuHand_3.size(); i++) {
            ImageView iv = createIV();
            iv.setRotate(90);
            cpu3VB.getChildren().add(iv);
        }
        
        midHB.getChildren().addAll(cpu1VB, cpu3VB);
        
        HBox pHandHB = new HBox();
        pHandHB.setAlignment(Pos.CENTER);
        pHandHB.setSpacing(20);
        
        VBox screenVB = new VBox();
        screenVB.setAlignment(Pos.CENTER);
        screenVB.getChildren().addAll(cpu2HB, pHandHB);
        
        for (int i = 0; i < playerHand.size(); i++) {
            // Add player cards to the screen
            ImageView iv = createIV(playerHand.get(i));
            pHandHB.getChildren().add(iv);
        }
        
        StackPane root = new StackPane();       
        root.getChildren().add(screenVB);
        Scene scene = new Scene(root, 1200, 700);
        
        primaryStage.setTitle("Crazy Eights");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    
    public ImageView createIV(Card c) {
        ImageView iv = new ImageView(c.getImageFile());
        iv.setFitHeight(150.2);
        iv.setFitWidth(100);
        iv.setPreserveRatio(true);
        return iv;
    }
    
    public ImageView createIV() {
        Image back = new Image("resources/CardBack_Blue.png");
        ImageView iv = new ImageView(back);
        iv.setFitHeight(150.2);
        iv.setFitWidth(100);
        iv.setPreserveRatio(true);
        return iv;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
