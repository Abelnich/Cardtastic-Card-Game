/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardtastic.card.game;

import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author nik
 */
public class BlackJack extends Application {
    
    //for button
    int i = 2;//count for player
    int x = 2;//count for dealer
    int c = 1;
    int d = 1;
    int xtranslate = 195;
    int xtranslatedealer = 195;
    int playerScore, dealerScore;
    @Override
    public void start(Stage primaryStage) {
        Button btnHit = new Button();
        Button btnStay = new Button();
        Button btnRestart = new Button();
        btnRestart.setText("    Play again    ");
        btnStay.setText("      Stay      ");
        btnHit.setText("      hit      ");
        StackPane root = new StackPane();
        
        //creates deck and deals cards
        Deck deck = new Deck();
        Card playerHand[] = new Card[10];
        Card dealerHand[] = new Card[10]; 
        playerHand[0] = deck.deal();
        playerHand[1] = deck.deal();
        dealerHand[0] = deck.deal();
        dealerHand[1] = deck.deal();
        
        dealerScore = dealerHand[0].getNumVal() + dealerHand[1].getNumVal();
        playerScore = playerHand[0].getNumVal() + playerHand[1].getNumVal();
        Image image6 = dealerHand[0].getImageFile();
        ImageView iv6 = new ImageView();
        iv6.setImage(image6);
        iv6.setFitHeight(150);
        iv6.setPreserveRatio(true);
        iv6.setTranslateX(-65);
        iv6.setTranslateY(-225);
        //restart game action
        btnRestart.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                BlackJack app = new BlackJack();
                app.start(primaryStage);
            }
        });
        //hit button action
        btnHit.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
        playerHand[i] = deck.deal(); 
        playerScore = playerScore + playerHand[i].getNumVal();
        if(((playerHand[i].getNumVal() == 11)||(playerHand[0].getNumVal() == 11)||(playerHand[1].getNumVal() == 11)) && (playerScore > 21) && (c==1)){
            playerScore = playerScore - 10;
            c++;
        }
        //image displays image for hit card
        Image image4 = playerHand[i].getImageFile();
        ImageView iv5 = new ImageView();
        iv5.setImage(image4);
        iv5.setFitHeight(150);
        iv5.setPreserveRatio(true);
        iv5.setTranslateX(xtranslate);
        iv5.setTranslateY(175);
        root.getChildren().add(iv5);
        if(playerScore > 21){
        Label labelbust = new Label("Bust! you lose!");
        labelbust.setFont(new Font("Arial", 40));
        root.getChildren().add(labelbust);
        //makes dealer card face up
        root.getChildren().add(iv6);
        }
        
        i++;
        xtranslate = xtranslate + 130;
            }
        });
        
        //stay button action
                btnStay.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
        //make dealers first card face up
        root.getChildren().add(iv6);
        
        while(dealerScore < 17){
        dealerHand[x] = deck.deal(); 
        dealerScore = dealerScore + dealerHand[x].getNumVal();
        if(((dealerHand[x].getNumVal() == 11)||(dealerHand[0].getNumVal() == 11)||(dealerHand[1].getNumVal() == 11))  && (dealerScore > 21) && (d == 1)){
            dealerScore = dealerScore - 10;
            d++;
        }
        //displays image for dealer hit card
        Image image6 = dealerHand[x].getImageFile();
        ImageView iv6 = new ImageView();
        iv6.setImage(image6);
        iv6.setFitHeight(150);
        iv6.setPreserveRatio(true);
        iv6.setTranslateX(xtranslatedealer);
        iv6.setTranslateY(-225);
        root.getChildren().add(iv6);
        xtranslatedealer = xtranslatedealer + 130;
        x++;
                }
        if((dealerScore > playerScore) && dealerScore<22){
        Label labelLose = new Label(" you lose!");
        labelLose.setFont(new Font("Arial", 40));
        root.getChildren().add(labelLose);
        }else if(playerScore > dealerScore){
        Label labelWin = new Label(" you win!");
        labelWin.setFont(new Font("Arial", 40));
        root.getChildren().add(labelWin);
        }else if(dealerScore > 21){
        Label labelDealerBust = new Label(" Dealer busts! you win! ");
        labelDealerBust.setFont(new Font("Arial", 40));
        root.getChildren().add(labelDealerBust);   
        }else if(dealerScore == playerScore){
        Label labelTie = new Label(" Push! (Tie)");
        labelTie.setFont(new Font("Arial", 40));
        root.getChildren().add(labelTie);
        }
            }
        });
        
        //players cards label
        Label labelPlayer = new Label("Your cards");
        labelPlayer.setTranslateY(75);
        labelPlayer.setFont(new Font("Arial", 20));
        
        //dealers cards label
        Label labelDealer = new Label("Dealer's cards");
        labelDealer.setTranslateY(-335);
        labelDealer.setFont(new Font("Arial", 20));
        
        //deals

        
        //players card 1 image
        Image image = playerHand[1].getImageFile();
        ImageView iv = new ImageView();
        iv.setImage(image);
        iv.setFitHeight(150);
        iv.setPreserveRatio(true);
        iv.setTranslateX(65);
        iv.setTranslateY(175);
        
        //players card 2 image

        Image image2 = playerHand[0].getImageFile();
        
        ImageView iv2 = new ImageView();
        iv2.setImage(image2);
        iv2.setFitHeight(150);
        iv2.setPreserveRatio(true);
        iv2.setTranslateX(-65);
        iv2.setTranslateY(175);
        
        //dealers card facedown image
        Image image3 = new Image("resources/CardBack_Blue.png");
        ImageView iv3 = new ImageView();
        iv3.setImage(image3);
        iv3.setFitHeight(150);
        iv3.setPreserveRatio(true);
        iv3.setTranslateX(-65);
        iv3.setTranslateY(-225);
        
        //dealers card face up
        Image image4 = dealerHand[1].getImageFile();
        ImageView iv4 = new ImageView();
        iv4.setImage(image4);
        iv4.setFitHeight(150);
        iv4.setPreserveRatio(true);
        iv4.setTranslateX(65);
        iv4.setTranslateY(-225);
        
        
        
        btnHit.setTranslateX(200);
        btnHit.setTranslateY(300);
        btnStay.setTranslateX(-200);
        btnStay.setTranslateY(300);
        btnRestart.setTranslateX(500);
        btnRestart.setTranslateY(300);
        //getting children
        root.getChildren().add(btnHit);
        root.getChildren().add(btnStay);
        root.getChildren().add(btnRestart);
        root.getChildren().add(iv);
        root.getChildren().add(iv2);
        root.getChildren().add(labelPlayer);
        root.getChildren().add(labelDealer);
        root.getChildren().add(iv3);
        root.getChildren().add(iv4);
        
        Scene scene = new Scene(root, 1200, 700);
        
        primaryStage.setTitle("Blackjack");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */

}
    

