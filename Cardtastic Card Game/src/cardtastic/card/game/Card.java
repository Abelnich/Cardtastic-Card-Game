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
    private int numVal;
    
    public Card(String suit, String val, int numVal) {
        this.suit = suit;
        this.value = val;
        this.numVal = numVal;
    }
    
    public String getSuit() {
        return this.suit;
    } 
    
    public String getValue() {
        return this.value;
    }
    
    public int getNumVal() {
        return this.numVal;
    }
    
    public int getRank() {
        if (this.value.equals("A")) {
            return 14;
        } else if (this.value.equals("K")) {
           return 13;
        } else if (this.value.equals("Q")) {
            return 12;
        } else if (this.value.equals("J")) {
            return 11;  
        } else {
            return Integer.parseInt(this.value);
        }
    }
    
    public Image getImageFile(String reqSuit, String reqVal) {
        
        String fileName = "/Cardtastic Card Game/" + reqVal;
        if (this.equals("Spades")) {
            fileName += 'S';
        } else if (reqSuit.equals("Diamonds")) {
            fileName += 'D';
        } else if (reqSuit.equals("Clubs")) {
            fileName += 'C';
        } else if (reqSuit.equals("Hearts")) {
            fileName += 'H';
        }
        
        fileName += ".png";
        
        Image reqImage = new Image(fileName);
        
        return (reqImage);
    } //  end getImageFile()
    
}
