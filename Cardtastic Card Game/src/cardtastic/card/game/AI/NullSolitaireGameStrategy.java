package AI;

import Model.ReadGameModel;
import Model.CardAction;

//does not do anything
public class NullSolitaireGameStrategy implements SolitaireGameStrategy {
    @Override
    public CardAction getLegalMove(ReadGameModel gameModel)
    {
        return gameModel.getNullCardMove();
    }
}
