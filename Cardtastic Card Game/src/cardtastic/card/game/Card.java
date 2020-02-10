/*
Nick Abel
01/22/20
Sophomore Project
 */
package cardtastic.card.game;

import javafx.scene.image.Image;

/**
 *
 * @author NickAbel
 */
public class Card {
    
    private String suit;
    private String value;
    
    public Card(String suit, String val) {
        this.suit = suit;
        this.value = val;
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
    
    public Image getImageFile(String reqSuit, String reqVal) {
        
        String fileName = "";
        
        if (reqSuit.equals("♤")) {
            fileName += 'S';
        } else if (reqSuit.equals("♢")) {
            fileName += 'D';
        } else if (reqSuit.equals("♧")) {
            fileName += 'C';
        } else if (reqSuit.equals("♡")) {
            fileName += 'H';
        }
        
        fileName += reqVal;
        fileName += ".png";
        
        Image reqImage = new Image(fileName);
        
        return (reqImage);
    } //  end getImageFile()
    
}
