package AI;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import Cards.Card;
import Cards.CardStack;
import Model.FoundationPile;
import Model.ReadGameModel;
import Model.CardAction;
import Model.TableauPile;




//Order of possible first moves: 1) discarding if discard pile is empty, 2) move card from discard pile to foundation pile
//3) move card from discard pile to the tableau, 4) move card from tableau to foundation pile, 5) move a pile from one tableau
//to another if it either reveals a fresh card or makes a pile available for a king, 6) if no previous option available, discards if possible
//7) if discarding is not an option a null move is returned
public class AlternativeSolitaireGameStrategy implements SolitaireGameStrategy {
    private static final List<Function<ReadGameModel, CardAction>> SUBSTRATEGIES = new ArrayList<>();

    static
    {
        SUBSTRATEGIES.add(AlternativeSolitaireGameStrategy::substrategyDiscardIfDiscardPileIsEmpty);
        SUBSTRATEGIES.add(AlternativeSolitaireGameStrategy::substrategyMoveDiscardToFoundation);
        SUBSTRATEGIES.add(AlternativeSolitaireGameStrategy::substrategyMoveDiscardToTableau);
        SUBSTRATEGIES.add(AlternativeSolitaireGameStrategy::substrategyMoveFromTableauToFoundation);
        SUBSTRATEGIES.add(AlternativeSolitaireGameStrategy::substrategyMoveWithinTableau);
        SUBSTRATEGIES.add(AlternativeSolitaireGameStrategy::substrategyDiscard);
    }



    //discards if the discard pile is empty
    private static CardAction substrategyDiscardIfDiscardPileIsEmpty(ReadGameModel gameModel)
    {
        if( gameModel.isDiscardPileEmpty() && !gameModel.isDeckEmpty() )
        {
            return gameModel.getDiscardCardMove();
        }
        else
        {
            return gameModel.getNullCardMove();
        }
    }


    // move the top card in discard pile to a foundation pile if possible
    private static CardAction substrategyMoveDiscardToFoundation(ReadGameModel gameModel)
    {
        if( gameModel.isDiscardPileEmpty() )
        {
            return gameModel.getNullCardMove();
        }
        for(FoundationPile pile : FoundationPile.values())
        {
            if( gameModel.isCardMoveLegal(gameModel.peekAtDiscardPile(), pile))
            {
                return gameModel.getCardMove(gameModel.peekAtDiscardPile(), pile);
            }
        }
        return gameModel.getNullCardMove();
    }

    private static CardAction substrategyMoveDiscardToTableau(ReadGameModel gameModel)
    {
        if( gameModel.isDiscardPileEmpty() )
        {
            return gameModel.getNullCardMove();
        }
        for(TableauPile pile : TableauPile.values())
        {
            if( gameModel.isCardMoveLegal(gameModel.peekAtDiscardPile(), pile))
            {
                return gameModel.getCardMove(gameModel.peekAtDiscardPile(), pile);
            }
        }
        return gameModel.getNullCardMove();
    }

    private static CardAction substrategyMoveFromTableauToFoundation(ReadGameModel gameModel)
    {
        for(TableauPile tabPile : TableauPile.values())
        {
            CardStack stack = gameModel.getTableauPile(tabPile);
            if( !stack.isEmpty() )
            {
                Card card = stack.peek();
                for(FoundationPile pile : FoundationPile.values())
                {
                    if( gameModel.isCardMoveLegal(card, pile))
                    {
                        return gameModel.getCardMove(card, pile);
                    }
                }
            }
        }
        return gameModel.getNullCardMove();
    }



    //move card if it will either reveal a card or empty a pile, can't move kings inbetween empty piles
    private static CardAction substrategyMoveWithinTableau(ReadGameModel gameModel)
    {
        for( TableauPile pile : TableauPile.values())
        {
            CardStack stack = gameModel.getTableauPile(pile);
            for( Card cards : stack )
            {
                if( gameModel.isBottomCardKing(cards))
                {
                    continue;
                }
                if( gameModel.isCardLowestVisibleInTableau(cards))
                {
                    for( TableauPile pile2 : TableauPile.values() )
                    {
                        if( gameModel.isCardMoveLegal(cards, pile2))
                        {
                            return gameModel.getCardMove(cards, pile2);
                        }
                    }
                }
            }
        }
        return gameModel.getNullCardMove();
    }

    private static CardAction substrategyDiscard(ReadGameModel gameModel)
    {
        if( gameModel.isDeckEmpty() )
        {
            return gameModel.getNullCardMove();
        }
        else
        {
            return gameModel.getDiscardCardMove();
        }
    }

    @Override
    public CardAction getLegalMove(ReadGameModel gameModel)
    {
        for( Function<ReadGameModel, CardAction> substrategy : SUBSTRATEGIES )
        {
            CardAction action = substrategy.apply(gameModel);
            if( !action.isNull() )
            {
                return action;
            }
        }
        return gameModel.getNullCardMove();
    }
}


