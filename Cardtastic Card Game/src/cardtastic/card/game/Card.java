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
        return this.suit;
    } 
    
    public String getValue() {
        return this.value;
    }
    
    public int getRank() {
        if (this.value.equals("K") || this.value.equals("Q") || this.value.equals("J")) {
            return 10;
        } else if (this.value.equals("A")) {
            return 11;
        } else {
            return Integer.getInteger(this.value);
        }
    }
    
    public Image getImageFile(String reqSuit, String reqVal) {
        
        String fileName = "";
        fileName += reqVal;
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
