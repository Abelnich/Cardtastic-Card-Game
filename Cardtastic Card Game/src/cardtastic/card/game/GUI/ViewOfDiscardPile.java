package GUI;

import Cards.Card;
import Cards.CardImage;
import Model.GameFacade;
import Model.GameModelChange;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

//part that shows the status of the discard pile and allows cards to be dragged from it

class ViewOfDiscardPile extends HBox implements GameModelChange{
    private static final int PADDING = 5;
    private DragCard crdDrag;

    ViewOfDiscardPile() {
        setPadding(new Insets(PADDING));
        final ImageView img = new ImageView(CardImage.getBackOfCard());
        img.setVisible(false);
        getChildren().add(img);
        crdDrag = new DragCard(img);
        img.setOnDragDetected(crdDrag);
        GameFacade.instanceOfGameFacade().addGameModelListener(this);
    }

    @Override
    public void changeInGameState() {
        if( GameFacade.instanceOfGameFacade().isDiscardPileEmpty())
        {
            getChildren().get(0).setVisible(false);
        }
        else
        {
            getChildren().get(0).setVisible(true);
            Card topCard = GameFacade.instanceOfGameFacade().peekAtDiscardPile();
            ImageView img = (ImageView)getChildren().get(0);
            img.setImage(CardImage.getCard(topCard));
            crdDrag.setCard(topCard);
        }
    }
}
