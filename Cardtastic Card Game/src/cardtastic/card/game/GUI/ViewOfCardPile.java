package GUI;

import Cards.Card;
import Cards.CardImage;
import Cards.CardStack;
import Model.GameFacade;
import Model.GameModelChange;
import Model.TableauPile;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;


// ViewOfCardPile shows a stack of cards in the bottom stacks
public class ViewOfCardPile extends StackPane implements GameModelChange {
        private static final int PADDING = 5;
        private static final int Y_OFFSET = 17;

        private TableauPile aIndex;

        ViewOfCardPile(TableauPile pIndex)
        {
            aIndex = pIndex;
            setPadding(new Insets(PADDING));
            setAlignment(Pos.TOP_CENTER);
            buildLayout();
            GameFacade.instanceOfGameFacade().addGameModelListener(this);
        }

        private static Image getImage (Card pCard)
        {
            if (GameFacade.instanceOfGameFacade().isCardVisibleInTableau(pCard)) {
                return CardImage.getCard(pCard);
            } else {
                return CardImage.getBackOfCard();
            }
        }

        private void buildLayout ()
        {
            getChildren().clear();

            int offset = 0;
            CardStack stack = GameFacade.instanceOfGameFacade().getTableauPile(aIndex);
            if (stack.isEmpty()) // this essentially acts as a spacer
            {
                ImageView image = new ImageView(CardImage.getBackOfCard());
                image.setVisible(false);
                getChildren().add(image);
                return;
            }

            for (Card cardView : stack) {
                final ImageView image = new ImageView(getImage(cardView));
                image.setTranslateY(Y_OFFSET * offset);
                offset++;
                getChildren().add(image);

                setOnDragOver(createDragOverHandler(image, cardView));
                setOnDragEntered(createDragEnteredHandler(image, cardView));
                setOnDragExited(createDragExitedHandler(image, cardView));
                setOnDragDropped(createDragDroppedHandler(image, cardView));

                if (GameFacade.instanceOfGameFacade().isCardVisibleInTableau(cardView)) {
                    image.setOnDragDetected(createDragDetectedHandler(image, cardView));
                }
            }
        }

        private EventHandler<MouseEvent> createDragDetectedHandler ( final ImageView pImageView, final Card pCard)
        {
            return new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent pMouseEvent) {
                    Dragboard db = pImageView.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    content.putString(CardRelocate.calibrated(GameFacade.instanceOfGameFacade().getStackSequence(pCard, aIndex)));
                    db.setContent(content);
                    pMouseEvent.consume();
                }
            };
        }

        private EventHandler<DragEvent> createDragOverHandler ( final ImageView pImageView, final Card pCard)
        {
            return new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent pEvent) {
                    if (pEvent.getGestureSource() != pImageView && pEvent.getDragboard().hasString()) {
                        CardRelocate transfer = new CardRelocate(pEvent.getDragboard().getString());
                        if (GameFacade.instanceOfGameFacade().isCardMoveLegal(transfer.getTopCard(), aIndex)) {
                            pEvent.acceptTransferModes(TransferMode.MOVE);
                        }
                    }
                    pEvent.consume();
                }
            };
        }

        private EventHandler<DragEvent> createDragEnteredHandler ( final ImageView pImageView, final Card pCard)
        {
            return new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent pEvent) {
                    CardRelocate transfer = new CardRelocate(pEvent.getDragboard().getString());
                    if (GameFacade.instanceOfGameFacade().isCardMoveLegal(transfer.getTopCard(), aIndex)) {
                        pImageView.setEffect(new DropShadow());
                    }
                    pEvent.consume();
                }
            };
        }

        private EventHandler<DragEvent> createDragExitedHandler ( final ImageView pImageView, final Card pCard)
        {
            return new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent pEvent) {
                    pImageView.setEffect(null);
                    pEvent.consume();
                }
            };
        }

        private EventHandler<DragEvent> createDragDroppedHandler ( final ImageView pImageView, final Card pCard)
        {
            return new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent pEvent) {
                    Dragboard db = pEvent.getDragboard();
                    boolean success = false;
                    if (db.hasString()) {
                        GameFacade.instanceOfGameFacade().getCardMove(new CardRelocate(db.getString()).getTopCard(), aIndex).performMove();
                        success = true;
                    }

                    pEvent.setDropCompleted(success);

                    pEvent.consume();
                }
            };
        }

        @Override
        public void changeInGameState ()
        {
            buildLayout();
        }
}

