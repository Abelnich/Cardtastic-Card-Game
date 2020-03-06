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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author NickAbel
 */
public class HelpGame extends Application {
    
    StackPane root;
    Stage helpStage;
    Scene startScene, guideScene;
    
    @Override
    public void start(Stage primaryStage) {
        helpStage = primaryStage;
        
        // start scene \\
        
        
        Label welcomeMessage = new Label("Select below which game you want to know more about.");
        welcomeMessage.setFont(new Font("calibre", 40));
        Button solitaireBtn = new Button("Solitaire");
        solitaireBtn.setOnAction(e -> handleButton(solitaireBtn));
        Button freeCellBtn = new Button("Freecell");
        freeCellBtn.setOnAction(e -> handleButton(freeCellBtn));
        Button blackJackBtn = new Button("Blackjack");
        blackJackBtn.setOnAction(e -> handleButton(blackJackBtn));
        Button warBtn = new Button("War");
        warBtn.setOnAction(e -> handleButton(warBtn));
        
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
    
    public void handleButton(Button b) {
        switch (b.getText()) {
            case "Solitaire":
                break;
            case "Freecell":
                break;
            case "Blackjack":
                break;
            case "War":
                break;
            default:
                System.out.println("help " + b.getText());
                break;
        }
                
    }
    
    public void setup() {
        
        int currMessage = 0;
        String message1 = "Testing messages number 1";
        String message2 = "2 number messages testing";
        String[] messages = {message1, message2};  
        
        // Layout
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        
        // Images for guide
        Image test1 = new Image("resources/AC.png", 100, 152.821997106, false, true);
        ImageView iv1 = new ImageView(test1);
        vbox.getChildren().add(iv1);
  
        Label lblMessage = new Label(messages[currMessage]);
        lblMessage.setFont(new Font("calibre", 40));
        
        HBox btnBox = new HBox();
        btnBox.setAlignment(Pos.CENTER);
        btnBox.setSpacing(20);
        Button previous = new Button("Previous");
        previous.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
              lblMessage.setText("prev");
            }
        });
        Button next = new Button("Next");
        next.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
              lblMessage.setText("next");
            }
        });
        btnBox.getChildren().add(previous);
        btnBox.getChildren().add(next);
        
        vbox.getChildren().add(lblMessage);
        vbox.getChildren().add(btnBox);

        
        StackPane guideStack = new StackPane();
        guideStack.getChildren().add(vbox);
        guideStack.setStyle("-fx-background-color: ForestGreen");
        guideScene = new Scene(guideStack, 1280, 550);
        
        helpStage.setScene(guideScene);
    }
    
}
