package GUI;

import Cards.CardImage;
import Model.GameFacade;
import Model.GameModelChange;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


//part that shows the deck and allows clicking to draw cards. disappears when game facade state changes

public class ViewOfCardDeck extends HBox implements GameModelChange{
    private static final String normalButtonStyle = "-fx-background-color: transparent; -fx-padding: 5, 5, 5, 5;";
    private static final String pressedButtonStyle = "-fx-background-color: transparent; -fx-padding: 6 4 4 6;";
    private static final int imgNewLineWidth = 10;
    private static final int imgFontSize = 15;

    ViewOfCardDeck()
    {
        final Button button = new Button();
        button.setGraphic(new ImageView(CardImage.getBackOfCard()));
        button.setStyle(normalButtonStyle);

        button.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent xEvent)
            {
                ((Button)xEvent.getSource()).setStyle(pressedButtonStyle);
            }
        });

        button.setOnMouseReleased(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent yEvent)
            {
                ((Button)yEvent.getSource()).setStyle(normalButtonStyle);
                if( GameFacade.instanceOfGameFacade().isDeckEmpty() )
                {
                    GameFacade.instanceOfGameFacade().resetModel();
                }
                else
                {
                    GameFacade.instanceOfGameFacade().getDiscardCardMove().performMove();
                }
            }
        });

        getChildren().add(button);
        GameFacade.instanceOfGameFacade().addGameModelListener(this);
    }

    private Canvas makeNewGameImg()
    {
        double width = CardImage.getBackOfCard().getWidth();
        double height = CardImage.getBackOfCard().getHeight();
        Canvas canvas = new Canvas( width, height );
        GraphicsContext context = canvas.getGraphicsContext2D();

        // The reset image
        context.setStroke(Color.DARKGREEN);
        context.setLineWidth(imgNewLineWidth);
        context.strokeOval(width/4, height/2-width/4 + imgFontSize, width/2, width/2);

        // The text

        context.setTextAlign(TextAlignment.CENTER);
        context.setTextBaseline(VPos.CENTER);
        context.setFill(Color.DARKKHAKI);
        context.setFont(Font.font(Font.getDefault().getName(), imgFontSize));



        if( GameFacade.instanceOfGameFacade().gameIsCompleted() )
        {
            context.fillText("You won!", Math.round(width/2), imgFontSize);
        }
        else
        {
            context.fillText("Give up?", Math.round(width/2), imgFontSize);
        }
        context.setTextAlign(TextAlignment.CENTER);
        return canvas;
    }

    @Override
    public void changeInGameState()
    {
        if( GameFacade.instanceOfGameFacade().isDeckEmpty() )
        {
            ((Button)getChildren().get(0)).setGraphic(makeNewGameImg());
        }
        else
        {
            ((Button)getChildren().get(0)).setGraphic(new ImageView(CardImage.getBackOfCard()));
        }
    }

    public void resetModel()
    {

        getChildren().get(0).setVisible(true);
    }
}
