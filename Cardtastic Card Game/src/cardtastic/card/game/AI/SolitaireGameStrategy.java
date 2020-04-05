package AI;

import Model.ReadGameModel;
import Model.CardAction;


//SolitaireGameStrategy() is the game playing behavior of solitaire. The implementation of the interface makes sure
//that the sequence of CardAction instances returned doesn't end in a non winning endless cycle
public interface SolitaireGameStrategy {


    //returns either a legal solitaire game move or the Null move is that isn't possible
    CardAction getLegalMove(ReadGameModel pModel);
}
