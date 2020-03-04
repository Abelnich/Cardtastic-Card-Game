package cardtastic.card.game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nik
 */
import javafx.scene.image.Image;

public class Card {
    private String suit;
    private String value;
    private int numVal;
    
    public Card(String suit, String val, int numVal) {
        this.suit = suit;
        this.value = val;
        this.numVal = numVal;
    }
    
    public String getSuit() {
        String suitSymbol = "";
        
        switch (this.suit) {
            // 
            case "Spades":
                suitSymbol = "♤";
                break;
            case "Diamonds":
                suitSymbol = "♢";
                break;
            case "Clubs":
                suitSymbol = "♧";
                break;
            case "Hearts":
                suitSymbol = "♡";
                break;

        }
        
        return suitSymbol;
    } // end getSuit()
    
    public String getValue() {
        return this.value;
    }
    public int getnumVal(){
        return this.numVal;
    }
    
    public Image getImageFile(String reqSuit, String reqVal) {
        
        String fileName = "/img/" + reqVal;
        
        if (reqSuit.equals("♤")) {
            fileName += 'S';
        } else if (reqSuit.equals("♢")) {
            fileName += 'D';
        } else if (reqSuit.equals("♧")) {
            fileName += 'C';
        } else if (reqSuit.equals("♡")) {
            fileName += 'H';
        }
        
        //fileName += reqVal;
        fileName += ".png";
        
        Image reqImage = new Image(fileName);
        
        return (reqImage);
    } //  end getImageFile()
    
}
