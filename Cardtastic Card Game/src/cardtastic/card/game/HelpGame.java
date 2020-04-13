/*
Nick Abel
03/02/20
 */
package cardtastic.card.game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author NickAbel
 */
public class HelpGame extends Application {
    
    private StackPane sp_root;
    private Stage stage_help;
    private Scene scene_start, scene_guide;
    private HBox hb_iv, hb_button;
    private Button btn_solitaire, btn_freeCell, btn_blackJack, btn_war, btn_crazy;
    private Label lbl_message;
    private Image img_guide1, img_guide2, img_guide3, img_guide4;
    private ImageView iv_guide1, iv_guide2, iv_guide3, iv_guide4;
    
    @Override
    public void start(Stage primaryStage) {
        stage_help = primaryStage;
        
        // start scene \\
        
        img_guide1 = new Image("resources/AS.png", 100, 150.2, true, true);
        iv_guide1 = new ImageView(img_guide1);
        img_guide2 = new Image("resources/AD.png", 100, 150.2, true, true);
        iv_guide2 = new ImageView(img_guide2);
        img_guide3 = new Image("resources/AC.png", 100, 150.2, true, true);
        iv_guide3 = new ImageView(img_guide3);
        img_guide4 = new Image("resources/AH.png", 100, 150.2, true, true);
        iv_guide4 = new ImageView(img_guide4);
        hb_iv = new HBox();
        hb_iv.setAlignment(Pos.CENTER);
        hb_iv.setSpacing(20);
        hb_iv.getChildren().addAll(iv_guide1, iv_guide2, iv_guide3, iv_guide4);
        hb_iv.setVisible(false);
        
        lbl_message = new Label("Select below which game you want to know more about.");
        lbl_message.setFont(new Font("calibre", 40));
        btn_solitaire = new Button("Solitaire");
        btn_solitaire.setOnAction(e -> handleButton(btn_solitaire));
        btn_freeCell = new Button("Freecell");
        btn_freeCell.setOnAction(e -> handleButton(btn_freeCell));
        btn_blackJack = new Button("Blackjack");
        btn_blackJack.setOnAction(e -> handleButton(btn_blackJack));
        btn_war = new Button("War");
        btn_war.setOnAction(e -> handleButton(btn_war));
        btn_crazy = new Button("Crazy Eights");
        btn_crazy.setOnAction(e -> handleButton(btn_crazy));
        
        hb_button = new HBox();
        hb_button.setAlignment(Pos.CENTER);
        hb_button.setSpacing(20);
        hb_button.getChildren().addAll(btn_solitaire, btn_freeCell, btn_blackJack, btn_war, btn_crazy);
        
        VBox vb_scene = new VBox();
        vb_scene.setAlignment(Pos.CENTER);
        vb_scene.setSpacing(20);
        vb_scene.getChildren().addAll(hb_iv, lbl_message, hb_button);
        
        // Scene housekeeping
        sp_root = new StackPane();
        FileReader fr = new FileReader("Settings.txt"); 
        sp_root.setStyle(fr.readFile().get(1));
        sp_root.getChildren().add(vb_scene);
        Scene scene = new Scene(sp_root, 1280, 550);
        
        stage_help.setTitle("Help");
        stage_help.setScene(scene);
        stage_help.show();
        // end start scene \\
        
    }
    
    private void handleButton(Button b) {
        switch (b.getText()) {
            case "Solitaire":
                lbl_message.setText("The object of the game is to get all the cards off the main board by stacking them based on their respective suits. Each stack is organized from least"
                        + " to most, with Ace being the low card and King being the highest. On the main board, the player can move the uncovered card around and place it on another if "
                        + "the next card is one higher in ranking (or a blank in the case of the king) and is also the opposite color. The player may draw from the deck if no moves are "
                        + "available. The game ends if/when all the suit stacks are completed.");
                break;
            case "Freecell":
                lbl_message.setText("The objective of the game is to fill all the home cells in the top right by stacking cards in ascending order in stacks based on their ranks. "
                        + "The player may also opt to store some cards in the free cell in the top left to free up other cards underneath. The game ends when all the stacks are completed.");
                break;
            case "Blackjack":
                lbl_message.setText("The object of the game is to get the sum of your cards as close to 21 as you can without going over and to have a higher score than the dealer. "
                        + "The Ace is worth either 1 or 11, whichever is higher without going over. The face cards are each worth 10. "
                        + "The rest of the cards are worth their respective values. The dealer and player both start with 2 cards and can choose to \"Hit\" or \"Stay\" by pressing the "
                        + "respective buttons.");
                break;
            case "War":
                lbl_message.setText("The object of the game is to get your opponent to have no cards in their hand. Each turn, the top card of each hand is played. "
                        + "Whoever's card is ranked higher takes both cards. The cards are ranked from 2-K, with face cards being worth more than the one under it, and As are the highest."
                        + " If both players play the same card, a war is declared. The players then place more cards down until someone wins. The game ends when one hand is depleted.");
                break;
            case "Crazy Eights":   
                lbl_message.setText("The object of the game is to get rid of all your cards. You do so by playing a card that matches either the suit or the "
                        + "value of the card on the discard pile. To begin, press the start button. Each computer will take its turn and then the red circle "
                        + "will turn green to indicate it is your turn to go. You may either play a card or draw. Eights are wild! If you play on, you will need "
                        + "to select a new suit to play with. Once your turn is over, click \"Next Turn\". Repeat until someone runs out of cards and is declared the winner.");
                break;
            default:
                System.out.println("help " + b.getText());
                break;
        }
        lbl_message.setFont(new Font("calibre", 24));
        lbl_message.setTextFill(Color.WHITE);
        lbl_message.setTextAlignment(TextAlignment.CENTER);
        lbl_message.setWrapText(true);
                
    }
    
}
