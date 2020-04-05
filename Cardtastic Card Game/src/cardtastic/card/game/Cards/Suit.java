package Cards;

// suits of playing cards

public enum Suit {
    CLUBS, DIAMONDS, HEARTS, SPADES;


    //parameter suitType is the suit to test against.
    //returns True if this suit and suitType are of the same color.
    //assert suitType != null since we expect it to be not null
    // rule of solitaire is that layering cards are different colors, makes sure same colored cards aren't layered

    public boolean sameColorAs(Suit suitType)
    {
        assert suitType != null;
        if( this == CLUBS || this == SPADES )
        {
            return suitType == CLUBS || suitType == SPADES;
        }
        else
        {
            return suitType == DIAMONDS || suitType == HEARTS;
        }
    }
}
