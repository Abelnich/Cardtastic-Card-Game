package Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// Models a deck of 52 cards.

public class Deck {
    private CardStack newCards;


    //deck creates a new shuffled deck of 52 cards

    public Deck() {
        shuffle();
    }


    // shuffle reinitializes and shuffles all 52 cards in the deck

    public void shuffle() {

        List<Card> cards = new ArrayList<>();
        for( Suit suit : Suit.values() )
        {
            for( Rank rank : Rank.values() )
            {
                cards.add( Card.get( rank, suit ));
            }
        }
        Collections.shuffle(cards);
        newCards = new CardStack(cards);
    }



    //push(Card placeCard) puts parameter placeCard on top of the deck, expect placeCard to not be null, assert makes sure it is

    public void push(Card placeCard)
    {
        assert placeCard != null;
        newCards.push(placeCard);
    }



    // draw() draws a card from deck, removes it from the deck, and returns the drawn card. expect there to be a card to draw

    public Card draw()
    {
        assert !isEmpty();
        return newCards.pop();
    }



    // isEmpty() returns true if there are no cards in the deck left to draw

    public boolean isEmpty()
    {
        return newCards.isEmpty();
    }
}
