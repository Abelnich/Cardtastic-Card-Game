package AI;

//plays x games and for each game undoes and redoes all moves

import Model.GameFacade;

public final class GameRefresh {
    private static final int NumOfGames = 1000;

    private GameRefresh() {}

    public static void main(String[] pArgs)
    {
        for(int i = 0; i < NumOfGames; i++ )
        {
            playAnotherGame(GameFacade.instanceOfGameFacade());
        }
        System.out.println("Runs completed.");
    }

    private static void playAnotherGame(GameFacade xModel)
    {
        xModel.resetModel();
        boolean progress = true;
        while( progress )
        {
            progress = xModel.possibleAutoMove();
        }
        while( xModel.canUndoMove() )
        {
            xModel.undoLastMove();
        }
        progress = true;
        while( progress )
        {
            progress = xModel.possibleAutoMove();
        }
    }
}
