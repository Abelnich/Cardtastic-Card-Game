/*
Nick Abel
01/22/20
Sophomore Project
 */
package card.game;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author NickAbel
 */
public class Deck {
    
    ArrayList<Card> deckList = new ArrayList<Card>(); 
    String[] vals = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    
    public Deck() {
        for (int i = 0; i < vals.length; i++) {
            // Creates a card from each suit with the corresponding value from the vals array
            Card tempSpade = new Card("Spades", vals[i]);
            deckList.add(tempSpade);
            Card tempDiamond = new Card("Diamonds", vals[i]);
            deckList.add(tempDiamond);
            Card tempClub = new Card("Clubs", vals[i]);
            deckList.add(tempClub);
            Card tempHeart = new Card("Hearts", vals[i]);
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
            Card fake = new Card("Empty", "0");
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
            int firstSpot = place.nextInt(51);
            int secondSpot = place.nextInt(51);
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
        
    
}

