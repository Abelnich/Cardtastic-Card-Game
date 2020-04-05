package AI;

import Model.GameFacade;

//computes wins out of games won
public final class GameWinCount {
    private static final int totalCards = 52;
    private static final int numOfGames = 10000;
    private static final int percent = 100;

    private GameWinCount() {}


    public static void main(String[] pArgs)
    {
        int total = 0;
        int totalWon = 0;
        for(int i = 0; i < numOfGames; i++ )
        {
            playGame(GameFacade.instanceOfGameFacade());
            int score = GameFacade.instanceOfGameFacade().getGameScore();
            total += score;
            if( score == totalCards)
            {
                totalWon++;
            }
        }
        System.out.println(String.format("Ratio won     %d/%d=%.1f%%", totalWon, numOfGames,
                ((double)totalWon)/((double) numOfGames)* percent));
        System.out.println(String.format("Average score %d/%d=%.1f", total, numOfGames,
                ((double)total)/((double) numOfGames)));
    }

    private static void playGame(GameFacade pModel)
    {
        pModel.resetModel();
        boolean advanced = true;
        while( advanced )
        {
            advanced = pModel.possibleAutoMove();
        }
    }
}
