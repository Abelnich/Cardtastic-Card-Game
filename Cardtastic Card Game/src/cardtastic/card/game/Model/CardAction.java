package Model;


// CardAction is a representation of a possible action in the solitaire game

public interface CardAction {

    // performMove() performs a move if the move is legal
    void performMove();

    // undoMove() undoes a move by reversing it
    void undoMove();

    //isNull() returns true if the move performed is not a move that will advance the game and if false by default
    default boolean isNull() {
        return false; }
}
