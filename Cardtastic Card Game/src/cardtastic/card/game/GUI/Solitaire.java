package GUI;

import Cards.Suit;
import Model.FoundationPile;
import Model.GameFacade;
import Model.TableauPile;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//app class for solitaire. it assembles the major UI components and launches the app
//its composed elements handle the gesture handling which acts as an observer of the game model
public class Solitaire extends Application{
    private static final int width = 680;
    private static final int height = 500;
    private static final int outerMargin = 10;
    private static final String title = "Solitaire";
    private static final String version = "1.0";

    private ViewOfCardDeck crdDeckView = new ViewOfCardDeck();
    private ViewOfDiscardPile discardPileV = new ViewOfDiscardPile();
    private SuitPile[] suitPle = new SuitPile[Suit.values().length];
    private ViewOfCardPile[] aStck = new ViewOfCardPile[TableauPile.values().length];


    //launches app
    public static void main(String[] pArgs)
    {
        launch(pArgs);
    }

    @Override
    public void start(Stage primaryStge)
    {
        primaryStge.setTitle(title + " " + version);

        GridPane root = new GridPane();
        root.setStyle("-fx-background-color: green;");
        root.setHgap(outerMargin);
        root.setVgap(outerMargin);
        root.setPadding(new Insets(outerMargin));

        root.add(crdDeckView, 0, 0);
        root.add(discardPileV, 1, 0);

        for( FoundationPile index : FoundationPile.values() )
        {
            suitPle[index.ordinal()] = new SuitPile(index);
            root.add(suitPle[index.ordinal()], 3+index.ordinal(), 0);
        }

        for( TableauPile index : TableauPile.values() )
        {
            aStck[index.ordinal()] = new ViewOfCardPile(index);
            root.add(aStck[index.ordinal()], index.ordinal(), 1);
        }

        root.setOnKeyTyped(new EventHandler<KeyEvent>()
        {

            @Override
            public void handle(final KeyEvent newEvent)
            {
                if( newEvent.getCharacter().equals("\r"))
                {
                    GameFacade.instanceOfGameFacade().possibleAutoMove();
                }
                else if( newEvent.getCharacter().equals("\b"))
                {
                    GameFacade.instanceOfGameFacade().undoLastMove();
                }
                newEvent.consume();
            }

        });

        primaryStge.setResizable(false);
        primaryStge.setScene(new Scene(root, width, height));
        primaryStge.show();
    }
}
