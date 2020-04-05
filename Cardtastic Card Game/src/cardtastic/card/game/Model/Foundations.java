package Model;

import java.util.HashMap;
import java.util.Map;

import Cards.Card;
import Cards.CardStack;
import Cards.Rank;



//Foundations is the class that represents the four foundation piles that have to be completed to win the game
// going from ace at the bottom to king at the top in sequence and all being the same suit
class Foundations {
    private final Map<FoundationPile, CardStack> foundPiles = new HashMap<>();


    //Foundations creates an initialized FoundationPile object that consists of 4 empty piles
    Foundations()
    {
        initializeFoundationPile();
    }


    //getTotalNumOfCards returns the total number of cards in all of the foundation piles
    int getTotalNumOfCards()
    {
        int total = 0;
        for( CardStack stack : foundPiles.values())
        {
            total += stack.size();
        }
        return total;
    }


    //initializeFoundationPile initializes the FoundationPile object to 4 empty piles
    void initializeFoundationPile()
    {
        for( FoundationPile index : FoundationPile.values() )
        {
            foundPiles.put(index, new CardStack());
        }
    }


    //isFoundationPilEmpty returns true if the pile at cardLocation is empty
    //pileLocation is the location of the pile to check and it is assumed that there is something at pileLocation
    boolean isFoundationPileEmpty(FoundationPile pileLocation)
    {
        assert pileLocation != null;
        return foundPiles.get(pileLocation).isEmpty();
    }



    //cardCanMoveTo returns true if moveCard can be moved to pileLocation, only possible if rank is next in sequence to card on top
    //or empty if card is ace. There needs to be a card to move and pileLocation can't be empty for it to be true
    //moveCard is the card being moved
    //pileLocation is the location for moveCard
    boolean cardCanMoveTo(Card moveCard, FoundationPile pileLocation )
    {
        assert moveCard != null && pileLocation != null;
        if( isFoundationPileEmpty(pileLocation))
        {
            return moveCard.getRank() == Rank.ACE;
        }
        else
        {
            return moveCard.getSuit() == peek(pileLocation).getSuit() &&
                    moveCard.getRank().ordinal() == peek(pileLocation).getRank().ordinal()+1;
        }
    }


    //peek returns the top card on the pile of pileLocation
    //pileLocation is the location of the pile being peeked at
    // pileLocation cannot be empty
    Card peek(FoundationPile pileLocation)
    {
        assert pileLocation != null && !foundPiles.get(pileLocation).isEmpty();
        return foundPiles.get(pileLocation).peek();
    }


    //push() places cardPush on top of the pile at pileLocation
    //there has to be a card to push and pileLocation cannot be empty
    void push(Card cardPush, FoundationPile pileLocation)
    {
        assert cardPush != null && pileLocation != null;
        foundPiles.get(pileLocation).push(cardPush);
    }


    //pop() removes the card at the top of the pile at pileLocation and returns it
    Card pop(FoundationPile pileLocation)
    {
        assert pileLocation != null && !isFoundationPileEmpty(pileLocation);
        return foundPiles.get(pileLocation).pop();
    }
}
