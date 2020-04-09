/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardtastic.card.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.control.Button;

/**
 *
 * @author yousif
 */


public  class freeCell { 
        private double startDragX; 
        private double startDragY; 
        private double endDragX; 
        private double endDragY;
        private Group root = new Group();
        private ArrayList<Card> cards = new ArrayList();
        private ArrayList<ImageView> x = new ArrayList(); 
        private ArrayList<ImageView> a1 = new ArrayList();
        private ArrayList<ImageView> a2 = new ArrayList();
        private ArrayList<ImageView> a3 = new ArrayList();
        private ArrayList<ImageView> a4 = new ArrayList();
        private ArrayList<ImageView> a5 = new ArrayList();
        private ArrayList<ImageView> a6 = new ArrayList();
        private ArrayList<ImageView> a7 = new ArrayList();
        private ArrayList<ImageView> a8 = new ArrayList();
        private int setx; // set x position for each card 
        private int sety; // set y position for each card 
        private final double sceneWidth = 1650; 
        private final double sceneHieght = 1000; 
        private final double cardW = 125;
        private final double cardH = 175; 
        private Deck deck = new Deck(); 
        private Stage freeCellStage; 
        private Image background; 
    public freeCell(){
        setx = 50;
        sety = 0;
        
    }
    public void buttons(){
        Button newGame = new Button("New Game"); 
        Button home = new Button("Exit"); 
       
        newGame.setLayoutX(1513);
        newGame.setLayoutY(50);
        newGame.setScaleX(1.3);
        newGame.setScaleY(5);
        newGame.setStyle("-fx-background-color: red;");
        
        home.setLayoutX(1533);
        home.setLayoutY(180);
        home.setScaleX(2.8);
        home.setScaleY(5);
        home.setStyle("-fx-background-color: green;");
        
        newGame.setOnMousePressed(e -> {
            newGame.setStyle("-fx-background-color: green;");
          
            freeCell f = new freeCell(); 
           
            f.main(freeCellStage);
            
            
        });
        
         home.setOnMousePressed(e -> {
            newGame.setStyle("-fx-background-color: green;");
          
            freeCellStage.close();
            Main m = new Main(); 
            try {
                m.start(freeCellStage);
            } catch (Exception ex) {
                Logger.getLogger(freeCell.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });

        root.getChildren().add(newGame); 
        root.getChildren().add(home); 
        
    }
    
    public void load(){
        
        FileReader settings = new FileReader("Settings.txt"); 
        System.out.println();
        if(settings.readFile().get(1).equals("-fx-background-color: ForestGreen")){
            background = new Image("file:freeCell.png");
        }
        
        else if(settings.readFile().get(1).equals("-fx-background-color: Crimson")){
            background = new Image("file:freeCellRed.png");
            
        }
        else if(settings.readFile().get(1).equals("-fx-background-color: DodgerBlue")){
            background = new Image("file:freeCellBlue.png");
        }
        
        
        
        
        
        
        ImageView imgView = new ImageView(background); 
        

        imgView.setFitWidth(1500);
        imgView.setFitHeight(1000);

        
        
        root.getChildren().add(imgView); // background image 
       
         deck.Shuffle();
         // this loop loads the images 
         for(int i = 0; i < deck.getDeck().size() ; i++){
 //         ImageView main = new ImageView(deck.deal().getImageFile()); //the .getImageFile() isnt working 
            String value = deck.getDeck().get(i).getValue(); 
            char suit = deck.getDeck().get(i).getSuit().charAt(0); 
            ImageView main = new ImageView("file:" + value + suit + ".png"); 
            main.setFitWidth(cardW);
            main.setFitHeight(cardH);
            main.setY(10000);
            x.add(main);
               
        }

         
         // the next 8 for loops take the images and sort them into 8 columns 
         for(int i = 0; i < 7; i++){ // card index 0 - 7 
             sety +=50;
             a1.add(x.get(i)); 
             root.getChildren().add(a1.get(i)); 
             
             a1.get(i).setY(sety + 200);
             a1.get(i).setX(setx);
         }
         sety =0; 
         setx +=180;
         for(int i = 7; i < 14; i++){ // card index 7 - 13
             sety +=50;
             a2.add(x.get(i)); 
             root.getChildren().add(a2.get(i - 7)); 
             a2.get(i - 7).setY(sety + 200);
             a2.get(i - 7).setX(setx);
         }
         sety =0;
         setx += 180; 
         for(int i = 14; i < 21; i++){ // card index 14 - 20
             sety +=50;
             a3.add(x.get(i)); 
             root.getChildren().add(a3.get(i - 14)); 
             a3.get(i -14).setY(sety+ 200);
             a3.get(i - 14).setX(setx);
         }
         sety =0;
         setx +=180; 
         for(int i = 21; i < 28; i++){ // card index 21 - 27
             sety +=50;
             a4.add(x.get(i)); 
             root.getChildren().add(a4.get(i -21)); 
             a4.get(i -21).setY(sety+ 200);
             a4.get(i - 21).setX(setx);
         }
         sety =0;
         setx +=180; 
        for(int i = 28; i < 34; i++){ // card index 28 - 33
             sety +=50;
             a5.add(x.get(i)); 
             root.getChildren().add(a5.get(i - 28)); 
             a5.get(i -28).setY(sety+ 200);
             a5.get(i - 28).setX(setx);
         }
         sety =0;
         setx += 180; 
        for(int i = 34; i < 40; i++){ // card index 34 - 39
             sety +=50;
             a6.add(x.get(i)); 
             root.getChildren().add(a6.get(i - 34)); 
             a6.get(i - 34).setY(sety+ 200);
             a6.get(i - 34).setX(setx);
         }
         sety =0;
         setx += 180;
        for(int i = 40; i < 46; i++){ // card index 40 - 45
             sety +=50;
             a7.add(x.get(i)); 
             root.getChildren().add(a7.get(i - 40)); 
             a7.get(i - 40).setY(sety+ 200);
             a7.get(i -40).setX(setx);
         }
         sety =0;
         setx += 180; 
        for(int i = 46; i < 52; i++){ // card index 46 - 51
             sety +=50;
             a8.add(x.get(i)); 
             root.getChildren().add(a8.get(i - 46)); 
             a8.get(i - 46).setY(sety+ 200);
             a8.get(i - 46).setX(setx);
         }
    }
    
    public void logic(){
        
    }

    public void main(Stage freeCellStage)  {
        this.freeCellStage = freeCellStage; 
        load(); 
        logic(); 
        buttons();
        Scene scene = new Scene(root, sceneWidth, sceneHieght);
        freeCellStage.setScene(scene);
        freeCellStage.setTitle("Free Cell");
       // freeCellStage.setResizable(false); 
        freeCellStage.show();
    }
}