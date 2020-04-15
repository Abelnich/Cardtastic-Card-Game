package GUI;

import Cards.Suit;
import Model.FoundationPile;
import Model.GameFacade;
import Model.TableauPile;
import cardtastic.card.game.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

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
        MenuBar menuBar = new MenuBar();

        Menu quitMenu = new Menu("Game");

        MenuItem quitButton = new MenuItem("Quit to Main Menu");

        quitMenu.getItems().add(quitButton);

        menuBar.getMenus().add(quitMenu);

        quitButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                Main main = new Main();
                try {
                    main.start(primaryStge);
                } catch (Exception e) {
                    System.out.println("Problem going back to main: " + e.getMessage());
                }
            }
        });

        primaryStge.setTitle(title + " " + version);

        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: green;");
        gridPane.setHgap(outerMargin);
        gridPane.setVgap(outerMargin);
        gridPane.setPadding(new Insets(outerMargin));

        gridPane.add(crdDeckView, 0, 0);
        gridPane.add(discardPileV, 1, 0);

        for( FoundationPile index : FoundationPile.values() )
        {
            suitPle[index.ordinal()] = new SuitPile(index);
            gridPane.add(suitPle[index.ordinal()], 3+index.ordinal(), 0);
        }

        for( TableauPile index : TableauPile.values() )
        {
            aStck[index.ordinal()] = new ViewOfCardPile(index);
            gridPane.add(aStck[index.ordinal()], index.ordinal(), 1);
        }

        gridPane.setOnKeyTyped(new EventHandler<KeyEvent>()
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

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(gridPane);

        primaryStge.setResizable(false);
        primaryStge.setScene(new Scene(borderPane, width, height));
        primaryStge.show();
    }
}
