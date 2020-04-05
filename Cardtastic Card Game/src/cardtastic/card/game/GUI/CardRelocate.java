package GUI;

import Cards.Card;
import Cards.CardStack;


//CardRelocate facilitates the transfer of card through the drag and drop space, it's always "on"
public class CardRelocate {
    private static final String disconnect = ";";

    private Card[] cards;


    //CardRelocate() creates a card transfer from a serialized version of the cards
    public CardRelocate(String serializedString)
    {
        assert serializedString != null && serializedString.length() > 0;
        String[] tokens = serializedString.split(disconnect);
        cards = new Card[tokens.length];
        for( int i = 0; i < tokens.length; i++ )
        {
            cards[i] = Card.get(tokens[i]);
        }
        assert cards.length > 0;
    }

    
    //serialize() converts an array of cards into an id string that can be deserialized by the constructor
    // crdArray is the array of cards with high ranking cards first, returns the id string
    public static String calibrated(CardStack crdArray)
    {
        String product = "";
        for(Card card : crdArray)
        {
            product += card.getIDString() + disconnect;
        }
        if( product.length() > 0)
        {
            product = product.substring(0, product.length()-1);
        }
        return product;
    }


    //getTopCard returns the top card in the transfer ( one with the highest rank)
    public Card getTopCard()
    {

        return cards[0];
    }


    //size returns the number of cards in the transfer
    public int size()
    {

        return cards.length;
    }
}
