package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import AI.AlternativeSolitaireGameStrategy;
import AI.SolitaireGameStrategy;
import Cards.Card;
import Cards.CardStack;
import Cards.Deck;
import Cards.Rank;
import Cards.Suit;



//provides a facade to current game as well as keeps track of its current state and implements design pattern
// there are 4 conceptual game state elements: deck, discard pile, foundations (where suits are gathered), and
// tableau ( which is where the seven piles of cards cascade down in sequential order with alternating suit colors
public final class GameFacade implements ReadGameModel{
    private static final GameFacade instance = new GameFacade();

    private static final CardAction nullCardMove = new CardAction() {
        @Override
        public void performMove() {

        } // Does nothing on purpose

        @Override
        public boolean isNull() {
            return true;
        }

        @Override
        public void undoMove() {

        } // Does nothing on purpose
    };

    private final CardAction discardmove = new CardAction()
    {
        @Override
        public void performMove()
        {
            assert !isDeckEmpty();
            discardCrd.push(crdDeck.draw());
            crdMove.push(this);
            alertListeners();
        }

        @Override
        public void undoMove()
        {
            assert !isDiscardPileEmpty();
            crdDeck.push(discardCrd.pop());
            alertListeners();
        }
    };

    private final Deck crdDeck = new Deck();
    private final Stack<CardAction> crdMove = new Stack<>();
    private final CardStack discardCrd = new CardStack();
    private final Foundations foundtn = new Foundations();
    private final Tableau tabl = new Tableau();
    private final List<GameModelChange> lstnrs = new ArrayList<>();
    private final SolitaireGameStrategy solGmeStrat = new AlternativeSolitaireGameStrategy();

    private GameFacade()
    {
        resetModel();
    }


    //getGameScore() returns the number of cards in the foundation
    public int getGameScore()
    {

        return foundtn.getTotalNumOfCards();
    }


    //possibleAutoMove() tries to automatically make a move, if the auto strategy cannot make a decision its possible that
    //nothing happens. It returns whether a moves performed or not
    public boolean possibleAutoMove()
    {
        CardAction move = solGmeStrat.getLegalMove(this);
        move.performMove();
        return !move.isNull();
    }


    //instanceOfGameFacade returns the single instance for this class
    public static GameFacade instanceOfGameFacade()
    {
        return instance;
    }


    //addGameModelListener registers an observer for the state of the game model
    //gameListener is a listener to register, assumes its not null
    public void addGameModelListener(GameModelChange gameListener)
    {
        assert gameListener != null;
        lstnrs.add(gameListener);
    }

    private void alertListeners()
    {
        for( GameModelChange listener : lstnrs)
        {
            listener.changeInGameState();
        }
    }


    //resetModel restores the model sto the state corresponding to the start of a new game
    public void resetModel()
    {
        crdMove.clear();
        crdDeck.shuffle();
        discardCrd.clear();
        foundtn.initializeFoundationPile();
        tabl.initializeTableau(crdDeck);
        alertListeners();
    }


    //gameIsCompleted will return true if the game is completed
    public boolean gameIsCompleted()
    {
        return foundtn.getTotalNumOfCards() == Rank.values().length * Suit.values().length;
    }

    @Override
    public boolean isDeckEmpty()
    {
        return crdDeck.isEmpty();
    }

    @Override
    public boolean isDiscardPileEmpty()
    {
        return discardCrd.isEmpty();
    }

    @Override
    public boolean isFoundationPileEmpty(FoundationPile pPile)
    {
        return foundtn.isFoundationPileEmpty(pPile);
    }


    //peekSuitStack obtains the card on top of the foundation pile pileCheck without removing it and returns it
    // assumes pileCheck isn't null and the foundation pile isn't empty
    public Card peekSuitStack(FoundationPile pileCheck)
    {
        assert pileCheck != null && !isFoundationPileEmpty(pileCheck);
        return foundtn.peek(pileCheck);
    }

    @Override
    public Card peekAtDiscardPile()
    {
        assert discardCrd.size() != 0;
        return discardCrd.peek();
    }


    //findCard returns the game location where cardToLocate currently is
    //cardToLocate has to be in a location where it can be found and moved
    private LocationOfCard findCard(Card cardToLocate)
    {
        if( !discardCrd.isEmpty() && discardCrd.peek() == cardToLocate )
        {
            return AnotherLocation.DiscardPile;
        }
        for( FoundationPile index : FoundationPile.values() )
        {
            if( !foundtn.isFoundationPileEmpty(index) && foundtn.peek(index) == cardToLocate )
            {
                return index;
            }
        }
        for( TableauPile index : TableauPile.values() )
        {
            if( tabl.contains(cardToLocate, index))
            {
                return index;
            }
        }
        assert false; // We did not find the card: the precondition was not met.
        return null;
    }


    //undoLastMove undoes the last move performed
    public void undoLastMove()
    {
        if( !crdMove.isEmpty() )
        {
            crdMove.pop().undoMove();
        }
    }


    //canUndoMove() returns if there is a move to undo
    public boolean canUndoMove()
    {

        return !crdMove.isEmpty();
    }


    //absorbMoveableCard removes the moveable card from crdLocale
    private void absorbMoveableCard(LocationOfCard crdLocale)
    {
        if( crdLocale == AnotherLocation.DiscardPile )
        {
            assert !discardCrd.isEmpty();
            discardCrd.pop();
        }
        else if( crdLocale instanceof FoundationPile )
        {
            assert !foundtn.isFoundationPileEmpty((FoundationPile)crdLocale);
            foundtn.pop((FoundationPile)crdLocale);
        }
        else
        {
            assert crdLocale instanceof TableauPile;
            tabl.popPile((TableauPile)crdLocale);
        }
    }

    private void move(Card someCard, LocationOfCard cardDestination)
    {
        LocationOfCard source = findCard(someCard);
        if( source instanceof TableauPile && cardDestination instanceof TableauPile )
        {
            tabl.moveWithin(someCard, (TableauPile)source, (TableauPile) cardDestination);
        }
        else
        {
            absorbMoveableCard(source);
            if( cardDestination instanceof FoundationPile )
            {
                foundtn.push(someCard, (FoundationPile)cardDestination);
            }
            else if( cardDestination == AnotherLocation.DiscardPile )
            {
                discardCrd.push(someCard);
            }
            else
            {
                assert cardDestination instanceof TableauPile;
                tabl.pushPile(someCard, (TableauPile)cardDestination);
            }
        }
        alertListeners();
    }

    @Override
    public CardStack getTableauPile(TableauPile pIndex)
    {
        return tabl.getPile(pIndex);
    }

    @Override
    public boolean isCardVisibleInTableau(Card aCrd)
    {
        return tabl.contains(aCrd) && tabl.isCardVisible(aCrd);
    }

    @Override
    public boolean isCardLowestVisibleInTableau(Card someCrd)
    {
        return tabl.contains(someCrd) && tabl.isLowestCardVisible(someCrd);
    }


    //getStackSequence gets the sequence consisting of crdSeq and all of the cards below it from the tableau
    //returns the non empty sequence of cards
    // assumes topCard is not empty and is in the specified pile pileNeeded
    public CardStack getStackSequence(Card topCard, TableauPile pileNeeded)
    {
        assert topCard != null && pileNeeded != null && findCard(topCard) == pileNeeded;
        return tabl.getSequenceOfCards(topCard, pileNeeded);
    }

    @Override
    public boolean isCardMoveLegal(Card aCard, LocationOfCard dest )
    {
        if( dest instanceof FoundationPile )
        {
            return foundtn.cardCanMoveTo(aCard, (FoundationPile) dest);
        }
        else if( dest instanceof TableauPile )
        {
            return tabl.canMoveTo(aCard, (TableauPile) dest);
        }
        else
        {
            return false;
        }
    }

    @Override
    public CardAction getNullCardMove()
    {
        return nullCardMove;
    }

    @Override
    public CardAction getDiscardCardMove()
    {
        return discardmove;
    }

    @Override
    public CardAction getCardMove(Card aCard, LocationOfCard dest)
    {
        LocationOfCard source = findCard( aCard );
        if( source instanceof TableauPile  && tabl.revealTopCard(aCard))
        {
            return new CardMove(new CardMovement(aCard, dest), new RevealTopOfStack((TableauPile)source) );
        }
        return new CardMovement(aCard, dest);
    }

    @Override
    public boolean isBottomCardKing(Card crdBottom)
    {
        assert crdBottom != null && tabl.contains(crdBottom);
        return tabl.isBottomCardKing(crdBottom);
    }



    // CardMovement() is a move that represents the intention to move crdMve to cardDest, possibly moving all cards stacked on top
    // of crdMve if its in a working stack
    private class CardMovement implements CardAction {
        private Card crdMve;
        private LocationOfCard cardOrigin;
        private LocationOfCard cardDest;

        CardMovement(Card pCard, LocationOfCard pDestination)
        {
            crdMve = pCard;
            cardDest = pDestination;
            cardOrigin = findCard(pCard);
        }

        @Override
        public void performMove()
        {
            assert isCardMoveLegal(crdMve, cardDest);
            move(crdMve, cardDest);
            crdMove.push(this);
        }

        @Override
        public void undoMove()
        {
            move(crdMve, cardOrigin);
        }
    }


    // RevealTopOfStack reveals the top of the stack
    private class RevealTopOfStack implements CardAction{
        private final TableauPile tableauIndex;

        RevealTopOfStack(TableauPile pIndex)
        {
            tableauIndex = pIndex;
        }

        @Override
        public void performMove()
        {
            tabl.showTopPileCard(tableauIndex);
            crdMove.push(this);
            alertListeners();
        }

        @Override
        public void undoMove()
        {
            tabl.hideTopCardOfPile(tableauIndex);
            crdMove.pop().undoMove();
            alertListeners();
        }
    }
}
