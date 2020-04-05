package Cards;


public final class Card {
    // Indexed by suit, then rank
    private static final Card[][] CARDS = new Card[Suit.values().length][];

    // Create the flyweight objects
    static
    {
        for( Suit suit : Suit.values() )
        {
            CARDS[suit.ordinal()] = new Card[Rank.values().length];
            for( Rank rank : Rank.values() )
            {
                CARDS[suit.ordinal()][rank.ordinal()] = new Card(rank, suit);
            }
        }
    }

    private final Rank numRank;
    private final Suit suitSymbol;

    private Card(Rank rankValue, Suit suitType )
    {
        numRank = rankValue;
        suitSymbol = suitType;
    }

    // parameter numRank is the rank of the card
    // parameter suitSymbol is the suit of the card
    // returns the card object representing the card with rankValue and suitType

    public static Card get(Rank rankValue, Suit suitType)
    {
        assert rankValue != null && suitType != null;
        return CARDS[suitType.ordinal()][rankValue.ordinal()];
    }

    //parameter cardStringId is the id string for the card
    //get(String cardStringId) returns the card object with cardStringId string
    // get(String cardStringId) has to produce the cardStringId in order for it to be a valid input to the method

    public static Card get( String cardStringId )
    {
        assert cardStringId != null;
        int id = Integer.parseInt(cardStringId);
        return get(Rank.values()[id % Rank.values().length],
                Suit.values()[id / Rank.values().length]);
    }

    //getRank() gets the rank of the card and returns an object that represents the rank of the card
    public Rank getRank()
    {
        return numRank;
    }

    //getIDString() returns a string representing a specific card, it's compatible with the format expected by Card.get(String)

    public String getIDString()
    {
        return Integer.toString(getSuit().ordinal() * Rank.values().length + getRank().ordinal());
    }

    //getSuit() gets the suit of the card and returns an object representing the suit of the card

    public Suit getSuit()
    {
        return suitSymbol;
    }

    
    @Override
    public String toString()
    {
        return numRank + " of " + suitSymbol;
    }
}
