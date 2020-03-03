/*
Nick Abel
03/02/20
 */
package cardtastic.card.game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author NickAbel
 */
public class Help extends Application {
    
    StackPane root;
    Stage helpStage;
    Scene startScene, blackjackScene;
    
    @Override
    public void start(Stage primaryStage) {
        helpStage = primaryStage;
        
        // start scene \\
        
        
        Label welcomeMessage = new Label("Select below which game you want to know more about.");
        welcomeMessage.setFont(new Font("calibre", 40));
        Button solitaireBtn = new Button("Solitaire");
        Button freeCellBtn = new Button("Freecell");
        Button blackJackBtn = new Button("Blackjack");
        blackJackBtn.setOnAction(this::helpBlackjack);
        Button warBtn = new Button("War");
        
        VBox sceneVB = new VBox();
        sceneVB.setAlignment(Pos.CENTER);
        sceneVB.setSpacing(20);
        sceneVB.getChildren().add(welcomeMessage);
        
        HBox buttonHB = new HBox();
        buttonHB.setAlignment(Pos.CENTER);
        buttonHB.setSpacing(20);
        buttonHB.getChildren().add(solitaireBtn);
        buttonHB.getChildren().add(freeCellBtn);
        buttonHB.getChildren().add(blackJackBtn);
        buttonHB.getChildren().add(warBtn);
        sceneVB.getChildren().add(buttonHB);
        
        // Scene housekeeping
        root = new StackPane();
        root.setStyle("-fx-background-color: ForestGreen");
        root.getChildren().add(sceneVB);
        Scene scene = new Scene(root, 1280, 550);
        
        helpStage.setTitle("Help");
        helpStage.setScene(scene);
        helpStage.show();
        // end start scene \\
        
    }
    
    public void helpBlackjack(ActionEvent e) {
        
        
        
        StackPane blackJackStack = new StackPane();
        blackJackStack.setStyle("-fx-background-color: ForestGreen");
        blackjackScene = new Scene(blackJackStack, 1280, 550);
        
        helpStage.setScene(blackjackScene);
    }
    
}
