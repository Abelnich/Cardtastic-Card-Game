/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardtastic.card.game;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author yousif
 */


public  class freeCell { 
        private double startDragX; 
        private double startDragY; 
        private double endDragX; 
        private double endDragY;
        GridPane root = new GridPane();
        

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

        
        
        root.add(imgView, 0 , 0 ); 
       
        
         for(int i = 0; i < deck.getDeck().size() ; i++){
             
            System.out.println(deck.deal().getInfo());
    
            ImageView main = new ImageView(deck.deal().getImageFile());
//            String value = deck.getDeck().get(i).getValue(); 
//            char suit = deck.getDeck().get(i).getSuit().charAt(0); 
//            ImageView main = new ImageView("file:" + value + suit + ".png"); 
            main.setFitWidth(cardW);
            main.setFitHeight(cardH);
            root.add(main, i, i);
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