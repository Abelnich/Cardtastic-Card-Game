package GUI;

import Cards.Card;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

// stores a string representing the card that is being dragged

public class DragCard implements EventHandler<MouseEvent>{
    private static final ClipboardContent clipboardContent = new ClipboardContent();

    private Card someCard;
    private ImageView imgView;

    DragCard(ImageView aView )
    {
        imgView = aView;
    }

    void setCard(Card bCard)
    {
        someCard = bCard;
    }

    @Override
    public void handle(MouseEvent someMouseEvent)
    {
        Dragboard db = imgView.startDragAndDrop(TransferMode.ANY);
        clipboardContent.putString(someCard.getIDString());
        db.setContent(clipboardContent);
        someMouseEvent.consume();
    }
}
