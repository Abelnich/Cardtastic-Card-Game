package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;

import Cards.Card;
import Cards.CardStack;
import Cards.Deck;
import Cards.Rank;


//this class represents the seven piles of cards that cascade downwards where the cards are stacked with alternating suits in sequential order
// and can be moved from pile to pile
class Tableau {
    private final Map<TableauPile, CardStack> tabPiles = new HashMap<>();
    private final Set<Card> cardVisible = new HashSet<>();


    //tableau() creates an empty tableau
    Tableau()
    {
        for( TableauPile index : TableauPile.values() )
        {
            tabPiles.put(index, new CardStack());
        }
    }


    //initializeTableau fills the tableau by drawing cards from pileDeck
    //pileDeck must not be empty
    void initializeTableau(Deck pileDeck)
    {
        assert pileDeck != null;
        cardVisible.clear();
        for( int i = 0; i < TableauPile.values().length; i++ )
        {
            tabPiles.get(TableauPile.values()[i]).clear();
            for( int j = 0; j < i+1; j++ )
            {
                Card card = pileDeck.draw();
                tabPiles.get(TableauPile.values()[i]).push(card);
                if( j == i )
                {
                    cardVisible.add(card);
                }
            }
        }
    }



    //cardCanMoveTo() determines if its legal to move moveCard on top of pileDestination, like nonsequential order or the suits aren't
    //alternating
    // returns true if the move is legal
    //moveCard and pileDestination cannot be null
    boolean canMoveTo(Card moveCard, TableauPile pileDestination )
    {
        assert moveCard != null && pileDestination != null;
        CardStack pile = tabPiles.get(pileDestination);
        if( pile.isEmpty() )
        {
            return moveCard.getRank() == Rank.KING;
        }
        else
        {
            return moveCard.getRank().ordinal() == pile.peek().getRank().ordinal()-1 &&
                    !moveCard.getSuit().sameColorAs(pile.peek().getSuit());
        }
    }


    //isBottomCardKing returns true if checkCard is a visible king at the bottom of pile
    public boolean isBottomCardKing(Card checkCard)
    {
        assert checkCard != null && contains(checkCard);
        return checkCard.getRank() == Rank.KING && tabPiles.get(getPile(checkCard)).peek(0) == checkCard;
    }



    //getPile returns a copy of the whole pile at the specific location in the tableau
    CardStack getPile(TableauPile pileCopy)
    {
        assert pileCopy != null;
        return new CardStack(tabPiles.get(pileCopy));
    }


    //revealTopCard returns true if moving testCard away reveals the top of the card
    // returns true if testCard and the card above testCard aren't visible
    boolean revealTopCard(Card testCard)
    {
        assert testCard != null && contains(testCard);
        Optional<Card> previous = getPreviousCard(testCard);
        if( !previous.isPresent() )
        {
            return false;
        }
        return cardVisible.contains(testCard) && !cardVisible.contains(previous.get());
    }


    private TableauPile getPile(Card tabCard)
    {
        assert contains(tabCard);
        for( TableauPile pile : TableauPile.values() )
        {
            if( contains(tabCard, pile))
            {
                return pile;
            }
        }
        assert false;
        return null;
    }

    private Optional<Card> getPreviousCard(Card pileCopy)
    {
        Optional<Card> previous = Optional.empty();
        for( Card card : tabPiles.get(getPile(pileCopy)))
        {
            if( card == pileCopy )
            {
                return previous;
            }
            previous = Optional.of(card);
        }
        return Optional.empty();
    }



    //moveCardWithin moves cardToMove and all cards below to cardDestination with cardOrigin being the location of the
    //card before it is move
    // has to be a legal move
    void moveWithin(Card cardToMove, TableauPile cardOrigin, TableauPile cardDestination )
    {
        assert cardToMove != null && cardOrigin != null && cardDestination != null;
        assert contains(cardToMove, cardOrigin);
        assert isCardVisible(cardToMove);
        Stack<Card> temp = new Stack<>();
        Card card = tabPiles.get(cardOrigin).pop();
        temp.push(card);
        while( card != cardToMove )
        {
            card = tabPiles.get(cardOrigin).pop();
            temp.push(card);
        }
        while( !temp.isEmpty() )
        {
            tabPiles.get(cardDestination).push(temp.pop());
        }
    }


    //getSequenceOfCards returns a sequence of cards starting at bottomCard, being the bottom card, and all the cards on top of it
    //returns a copy of the requested sequence
    CardStack getSequenceOfCards(Card bottomCard, TableauPile targetPile)
    {
        assert bottomCard != null && targetPile != null;
        CardStack stack = tabPiles.get(targetPile);
        List<Card> lReturn = new ArrayList<>();
        boolean aSeen = false;
        for( Card card : stack )
        {
            if( card == bottomCard )
            {
                aSeen = true;
            }
            if( aSeen )
            {
                lReturn.add(card);
            }
        }
        return new CardStack(lReturn);
    }


    //showTopPileCard makes the top card of the pile visible with pileIndex being the index of the specific pile
    void showTopPileCard(TableauPile pileIndex)
    {
        assert !tabPiles.get(pileIndex).isEmpty();
        cardVisible.add(tabPiles.get(pileIndex).peek());
    }


    //hideTopCardOfPile makes the top card of the pile not visible with stackIndex being the index of the specific stack
    void hideTopCardOfPile(TableauPile stackIndex)
    {
        assert !tabPiles.get(stackIndex).isEmpty();
        cardVisible.remove(tabPiles.get(stackIndex).peek());
    }


    //contains returns true if pileIndex contains cardCheck
    boolean contains(Card cardCheck, TableauPile pileIndex)
    {
        assert cardCheck != null && pileIndex != null;
        for( Card card : tabPiles.get(pileIndex))
        {
            if( card == cardCheck )
            {
                return true;
            }
        }
        return false;
    }


    //containsCard returns whether cardCheck is contained in any stack
    boolean contains(Card cardCheck)
    {
        assert cardCheck != null;
        for( TableauPile index : TableauPile.values())
        {
            if( contains(cardCheck, index))
            {
                return true;
            }
        }
        return false;
    }


    //isCardVisible returns true if cardCheck is visible in piles
    boolean isCardVisible(Card cardCheck)
    {
        assert contains(cardCheck);
        return cardVisible.contains(cardCheck);
    }


    //isLowestCardVisible returns true if the card is visible and there's no visible card below it in its pile, including when it is
    //the only card at the bottom of the pile
    boolean isLowestCardVisible(Card cardCheck)
    {
        assert cardCheck != null && contains(cardCheck);
        if( !isCardVisible(cardCheck ))
        {
            return false;
        }
        else
        {
            Optional<Card> previousCard = getPreviousCard(cardCheck);
            return !previousCard.isPresent() || !isCardVisible(previousCard.get());
        }
    }


    //popPile removes the top card from the pile at popIndex
    void popPile(TableauPile popIndex)
    {
        assert !tabPiles.get(popIndex).isEmpty();
        cardVisible.remove(tabPiles.get(popIndex).pop());
    }


    // pushPile places a card at the top on the pile at stackIndex which will be visible by default
    void pushPile(Card cardPush, TableauPile stackIndex)
    {
        assert cardPush != null && stackIndex != null;
        tabPiles.get(stackIndex).push(cardPush);
        cardVisible.add(cardPush);
    }
}
