package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Composite object in the composite
 * design pattern.
 */
//CardMove
public class CardMove implements CardAction
{
    private final List<CardAction> crdActions = new ArrayList<>();

    //cAct is any move that's added to the CardMove class

    public CardMove(CardAction ... cAct)
    {
        for( CardAction crdAct : cAct )
        {
            crdActions.add(crdAct);
        }
    }

    @Override
    public void performMove()
    {
        for( CardAction crdAct : crdActions )
        {
            crdAct.performMove();
        }
    }

    @Override
    public void undoMove()
    {
        for( int i = crdActions.size()-1; i >=0; i-- )
        {
            crdActions.get(i).undoMove();
        }
    }
}
