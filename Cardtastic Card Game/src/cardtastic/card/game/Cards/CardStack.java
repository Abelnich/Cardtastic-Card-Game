package Cards;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// CardStack class is used to represent a stack of cards, new instances of this class are initialized to be empty

public class CardStack implements Iterable<Card> {
    private final List<Card> stkCards;


    //CardStack() makes an empty CardStack

    public CardStack()
    {
        stkCards = new ArrayList<>();
    }


    //CardStack(Iterable<Card> pCards) makes a CardStack that has all the cards in pCard in order from bottom to top
    // placeCards parameter are the cards that initialize the stack

    public CardStack(Iterable<Card> placeCards)
    {
        this();
        for(Card card : placeCards )
        {
            stkCards.add(card);
        }
    }



    // push(Card cardPush) pushes the card into the stack
    // the parameter cardPush is the card being pushed
    // expect cardPush to not be null and for the stkCards to contain the cardPush

    public void push(Card cardPush)
    {
        assert cardPush != null && !stkCards.contains(cardPush);
        stkCards.add(cardPush);
    }



    // pop() removes the card on the top of the stack and returns it
    // expect the stack to not be empty

    public Card pop()
    {
        assert !isEmpty();
        return stkCards.remove(stkCards.size()-1);
    }



    //peek() just returns the top card, it does not remove it, allows us to see what the top card is
    //expect stack to not be empty

    public Card peek()
    {
        assert !isEmpty();
        return stkCards.get(stkCards.size()-1);
    }



    // peekIndex is the index being peeked at
    // peek(int peekIndex) returns the card at the peekIndex position
    // expect peekIndex to be greater than zero and less than the size of the stack

    public Card peek(int peekIndex)
    {
        assert peekIndex >= 0 && peekIndex < size();
        return stkCards.get(peekIndex);
    }



    //size() returns the num of cards in stack

    public int size()
    {
        return stkCards.size();
    }


    // clear() removes all cards in stack

    public void clear()
    {
        stkCards.clear();
    }


    // isEmpty() returns true if the stack has no cards in it

    public boolean isEmpty()
    {
        return stkCards.size() == 0;
    }

    @Override
    public Iterator<Card> iterator()
    {
        return stkCards.iterator();
    }
}
