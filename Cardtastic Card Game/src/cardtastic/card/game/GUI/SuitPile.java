package GUI;

import Cards.Card;
import Cards.CardImage;
import Model.GameFacade;
import Model.GameModelChange;
import Model.FoundationPile;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;

//part that shows a stack of cards where a completed suit is gathered

public class SuitPile extends StackPane implements GameModelChange{
    private static final int PADDING = 5;
    private static final String styleOfBorder = "-fx-border-color: lightgray;"
            + "-fx-border-width: 3;" + " -fx-border-radius: 10.0";
    private static final String draggedBorderStyle = "-fx-border-color: darkgray;"
            + "-fx-border-width: 3;" + " -fx-border-radius: 10.0";
    private static final String normalBorderStyle = "-fx-border-color: lightgray;"
            + "-fx-border-width: 3;" + " -fx-border-radius: 10.0";

    private DragCard crdDrag;
    private FoundationPile pileIndex;

    SuitPile(FoundationPile diffPileIndex)
    {
        pileIndex = diffPileIndex;
        setPadding(new Insets(PADDING));
        setStyle(styleOfBorder);
        final ImageView img = new ImageView(CardImage.getBackOfCard());
        img.setVisible(false);
        getChildren().add(img);
        crdDrag = new DragCard(img);
        img.setOnDragDetected(crdDrag);
        setOnDragOver(createOnDragOverHandler(img));
        setOnDragEntered(createOnDragEnteredHandler());
        setOnDragExited(createOnDragExitedHandler());
        setOnDragDropped(createOnDragDroppedHandler());
        GameFacade.instanceOfGameFacade().addGameModelListener(this);
    }

    @Override
    public void changeInGameState()
    {
        if( GameFacade.instanceOfGameFacade().isFoundationPileEmpty(pileIndex))
        {
            getChildren().get(0).setVisible(false);
        }
        else
        {
            getChildren().get(0).setVisible(true);
            Card cardOnTop = GameFacade.instanceOfGameFacade().peekSuitStack(pileIndex);
            ImageView img = (ImageView)getChildren().get(0);
            img.setImage(CardImage.getCard(cardOnTop));
            crdDrag.setCard(cardOnTop);
        }
    }

    private EventHandler<DragEvent> createOnDragOverHandler(final ImageView imgView)
    {
        return new EventHandler<DragEvent>()
        {
            public void handle(DragEvent drgEvent)
            {
                if(drgEvent.getGestureSource() != imgView && drgEvent.getDragboard().hasString())
                {
                    CardRelocate relocate = new CardRelocate(drgEvent.getDragboard().getString());
                    if( relocate.size() == 1 && GameFacade.instanceOfGameFacade().isCardMoveLegal(relocate.getTopCard(), pileIndex) )
                    {
                        drgEvent.acceptTransferModes(TransferMode.MOVE);
                    }
                }

                drgEvent.consume();
            }
        };
    }

    private EventHandler<DragEvent> createOnDragEnteredHandler()
    {
        return new EventHandler<DragEvent>()
        {
            public void handle(DragEvent newEvent)
            {
                CardRelocate relocate = new CardRelocate(newEvent.getDragboard().getString());
                if( relocate.size() == 1 && GameFacade.instanceOfGameFacade().isCardMoveLegal(relocate.getTopCard(), pileIndex) )
                {
                    setStyle(draggedBorderStyle);
                }
                newEvent.consume();
            }
        };
    }

    private EventHandler<DragEvent> createOnDragExitedHandler()
    {
        return new EventHandler<DragEvent>()
        {
            public void handle(DragEvent newEvent)
            {
                setStyle(normalBorderStyle);
                newEvent.consume();
            }
        };
    }

    private EventHandler<DragEvent> createOnDragDroppedHandler()
    {
        return new EventHandler<DragEvent>()
        {
            public void handle(DragEvent newEvent)
            {
                Dragboard db = newEvent.getDragboard();
                boolean success = false;
                if(db.hasString())
                {
                    CardRelocate relocate = new CardRelocate(newEvent.getDragboard().getString());
                    GameFacade.instanceOfGameFacade().getCardMove(relocate.getTopCard(), pileIndex).performMove();
                    success = true;
                }
                newEvent.setDropCompleted(success);
                newEvent.consume();
            }
        };
    }
}
