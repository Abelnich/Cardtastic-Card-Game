/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardtastic.card.game;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author nik
 */
public class Deck {
    private ArrayList<Card> deckList = new ArrayList<Card>(); 
    private String[] vals = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private int[] numVal = {11,2,3,4,5,6,7,8,9,10,10,10,10};
    
    public Deck() {
        for (int i = 0; i < vals.length; i++) {
            // Creates a card from each suit with the corresponding value from the vals array
            Card tempSpade = new Card("Spades", vals[i], numVal[i]);
            deckList.add(tempSpade);
            Card tempDiamond = new Card("Diamonds", vals[i], numVal[i]);
            deckList.add(tempDiamond);
            Card tempClub = new Card("Clubs", vals[i], numVal[i]);
            deckList.add(tempClub);
            Card tempHeart = new Card("Hearts", vals[i], numVal[i]);
            deckList.add(tempHeart);
        }
    } // end Deck() constructor
    
    public Card deal() {
        // Create a random number object, generate an integer between 0 and the size of the deck -1
        
        Random rnd = new Random();
        int rand = rnd.nextInt(deckList.size() - 1);
        
        // Checks if deck is empty
        if (deckList.size() == 0) {
            System.out.println("Empty Deck");
            Card fake = new Card("Empty", "0" , 0);
            // Calling instance will need to be able to handle a fake card being returned
            return (fake);
        } else {
            // Returns random card and removes it from the deck
            Card ret = deckList.get(rand);
            deckList.remove(rand);
        return (ret);
        }
    } // end deal()    
    
    public void Shuffle() { 
        // Randomly relocates cards in the deck a predefined amount of times
        
        for (int i = 0; i < 100; i++) {
            Random place = new Random();
            int firstSpot = place.nextInt(this.deckList.size());
            int secondSpot = place.nextInt(this.deckList.size());
            Card tempCard;

            tempCard = deckList.get(firstSpot);
            deckList.set(firstSpot, deckList.get(secondSpot));
            deckList.set(secondSpot, tempCard);
            
        } // end for
    } // end Shuffle()
    
    public void Shuffle(int shuffleTimes) {
        // Randomly relocates cards in the deck a user defined amount of times

        for (int i = 0; i < shuffleTimes; i++) {
            Random place = new Random();
            int firstSpot = place.nextInt(51);
            int secondSpot = place.nextInt(51);
            Card tempCard;

            tempCard = deckList.get(firstSpot);
            deckList.set(firstSpot, deckList.get(secondSpot));
            deckList.set(secondSpot, tempCard);
        } // end for
    } // end Shuffle(int)
    
    public void RemoveCard(String suit, String val) {
        // Use if you don't want to use all the cards in the deck
        Card removeMe = new Card(suit, val);
        this.deckList.remove(removeMe);
    }
    
    public ArrayList<Card> getDeck() {
        return this.deckList;
    }
}
