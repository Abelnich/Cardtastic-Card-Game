/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardtastic.card.game;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Group;

/**
 *
 * @author yousif
 */


public  class freeCell { 
        private double startDragX; 
        private double startDragY; 
        private double endDragX; 
        private double endDragY;
        Group root = new Group();
        ArrayList<ImageView> x = new ArrayList();
        ArrayList<ImageView> a1 = new ArrayList();
        ArrayList<ImageView> a2 = new ArrayList();
        ArrayList<ImageView> a3 = new ArrayList();
        ArrayList<ImageView> a4 = new ArrayList();
        ArrayList<ImageView> a5 = new ArrayList();
        ArrayList<ImageView> a6 = new ArrayList();
        ArrayList<ImageView> a7 = new ArrayList();
        ArrayList<ImageView> a8 = new ArrayList();
        int setx = 50;
        int sety = 0;
    public void start(Stage freeCellStage)  {
        final double width = 1500; 
        final double hieght = 1000; 
        final double cardW = 150;
        final double cardH = 200; 
        Deck deck = new Deck(); 
        


        Image background = new Image("file:freeCell.png");
        ImageView imgView = new ImageView(background); 
        
        Image card = new Image("file:2C.png"); 
        ImageView cardImg = new ImageView(card); 
        cardImg.setFitWidth(cardW);
        cardImg.setFitHeight(cardH);
        imgView.setFitWidth(width);
        imgView.setFitHeight(hieght);

        
        
        root.getChildren().add(imgView); // background image 
       
         deck.Shuffle();
         for(int i = 0; i < deck.getDeck().size() ; i++){
 //           ImageView main = new ImageView(deck.deal().getImageFile());
            String value = deck.getDeck().get(i).getValue(); 
            char suit = deck.getDeck().get(i).getSuit().charAt(0); 
            ImageView main = new ImageView("file:" + value + suit + ".png"); 
            main.setFitWidth(cardW);
            main.setFitHeight(cardH);
            main.setY(10000);
            x.add(main);
               
        }
         
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
        for(int i = 46; i < 52; i++){ // card index 46 - 15
             sety +=50;
             a8.add(x.get(i)); 
             root.getChildren().add(a8.get(i - 46)); 
             a8.get(i - 46).setY(sety+ 200);
             a8.get(i - 46).setX(setx);
         }
       
        
//        main.setOnMousePressed(e -> {
//            startDragX = e.getSceneX(); 
//            startDragY = e.getSceneY(); 
//        
//        });
//        main.setOnMouseReleased(c -> {
//            endDragX = c.getX(); 
//            endDragY = c.getY(); 
//            endDragX = startDragX; 
//            endDragY = startDragY; 
//        
//        });
//     
//        
//        main.setOnMouseDragged(e ->{
//          
//           
//            cardImg.setTranslateX(e.getSceneX() - startDragX);
//            cardImg.setTranslateY(e.getSceneY() - startDragY);
//            
//        
//        
//        });
        
        
        
        
        

        Scene scene = new Scene(root, width, hieght);
       
        
        
        freeCellStage.setScene(scene);
        freeCellStage.setTitle("Free Cell");
       // freeCellStage.setResizable(false); 
        freeCellStage.show();
    }
}