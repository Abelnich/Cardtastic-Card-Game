package Model;


// is an interface that will be implemented by classes with objects that would benefit by notified by the changes in the state of the game model
public interface GameModelChange {


    //called when the state of the game model changes
    void changeInGameState();
}



