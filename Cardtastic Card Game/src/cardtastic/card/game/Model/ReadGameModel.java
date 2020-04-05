package Model;

import Cards.Card;
import Cards.CardStack;


// version of the game that is readable
public interface ReadGameModel {

    //isDiscardPileEmpty() returns true if the discard pile is empty
    boolean isDiscardPileEmpty();


    //isDeckEmpty() returns true if there are no cards left in the deck
    boolean isDeckEmpty();


    //pileIndex is the suit stack to check
    // isFoundationPileEmpty returns true if suitType is empty
    boolean isFoundationPileEmpty(FoundationPile pileIndex);


    //peekAtDiscardPile returns the card at the top of the discard pile as long as the discard pile is not empty
    Card peekAtDiscardPile();


    // getTableauPile returns a copy of the stack at the pileIndex position
    CardStack getTableauPile(TableauPile pileIndex);


    // isCardVisibleInTableau returns true if card's value is visible in the tableau, cardPush is a card to test for the visibility
    boolean isCardVisibleInTableau(Card cardPush);


    //isCardLowestVisibleInTableau returns true if cardPush is: in the tableau, the lowest card visible in the pile including if its the
    // only one in the pile
    boolean isCardLowestVisibleInTableau(Card cardPush);


    // isBottomCardKing returns true if cardPush is a visible king at bottom of pile provided that there is a cardPush and containsCard(tabCard)
    boolean isBottomCardKing(Card cardPush);


    //isCardMoveLegal determines if cardPush can be moved to cardDestination according to the rules of solitaire and the current game state
    //cardPush is the card being moved
    //cardDestination is the destination of the move
    // returns true is the card move is legal
    boolean isCardMoveLegal(Card cardPush, LocationOfCard cardDestination );


    //getNullCardMove returns an instance of the CardMove interface that represents no move at all
    CardAction getNullCardMove();


    //getDiscardCardMove returns an instance of CardMove that represents discarding a card from the deck
    CardAction getDiscardCardMove();



    //getCardMove returns instance of CardMove interface that represents moving a card ( or all if needed) to cardDestination
    //cardPush is the card being moved, and is presumed to be in a legal position
    // cardDestination is the required destination of the card
    CardAction getCardMove(Card cardPush, LocationOfCard cardDestination);
}
