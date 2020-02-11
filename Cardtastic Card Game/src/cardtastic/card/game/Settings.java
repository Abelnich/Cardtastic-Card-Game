/*
Sophomore Project
 */
package cardtastic.card.game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author NickAbel
 */
public class Settings extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        // VBox for all settings buttons
        VBox AllSettingsVBox = new VBox();
        
        /* Card Back Images HBox
        HBox cardBackImageBox = new HBox();
        Image redImg = new Image("CardBack_Red.png");
        Image yellowImg = new Image("CardBack_Yellow.png");
        Image greenImg = new Image("CardBack_Green.png");
        Image blueImg = new Image("CardBack_Blue.png");
        Image purpleImg = new Image("CardBack_Purple.png");
        Image grayImg = new Image("CardBack_Gray.png");
        
        ImageView redImgView = new ImageView(redImg);
        ImageView yellowImgView = new ImageView(yellowImg);
        ImageView greenImgView = new ImageView(greenImg);
        ImageView blueImgView = new ImageView(blueImg);
        ImageView purpleImgView = new ImageView(purpleImg);
        ImageView grayImgView = new ImageView(grayImg);
        
        cardBackImageBox.getChildren().add(redImgView);
        cardBackImageBox.getChildren().add(yellowImgView);
        cardBackImageBox.getChildren().add(greenImgView);
        cardBackImageBox.getChildren().add(blueImgView);
        cardBackImageBox.getChildren().add(purpleImgView);
        cardBackImageBox.getChildren().add(grayImgView);
        */ //End Card Back Images HBox
        
        // Card Back Toggle HBox
        HBox cardBackRadioBox = new HBox();
        ToggleGroup cardBackToggle = new ToggleGroup();
        RadioButton redBack = new RadioButton();
        redBack.setToggleGroup(cardBackToggle);
        RadioButton yellowBack = new RadioButton();
        yellowBack.setToggleGroup(cardBackToggle);
        RadioButton greenBack = new RadioButton();
        greenBack.setToggleGroup(cardBackToggle);
        RadioButton blueBack = new RadioButton();
        blueBack.setToggleGroup(cardBackToggle);
        RadioButton purpleBack = new RadioButton();
        purpleBack.setToggleGroup(cardBackToggle);
        RadioButton grayBack = new RadioButton();
        grayBack.setToggleGroup(cardBackToggle);
        
        cardBackRadioBox.getChildren().add(redBack);
        cardBackRadioBox.getChildren().add(yellowBack);
        cardBackRadioBox.getChildren().add(greenBack);
        cardBackRadioBox.getChildren().add(blueBack);
        cardBackRadioBox.getChildren().add(purpleBack);
        cardBackRadioBox.getChildren().add(grayBack);
        // End Card Back Toggle HBox
        
        //AllSettingsVBox.getChildren().add(cardBackImageBox);
        AllSettingsVBox.getChildren().add(cardBackRadioBox);
        
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: ForestGreen");
        
        Scene scene = new Scene(root, 500, 500);
        root.getChildren().add(AllSettingsVBox);
        
        primaryStage.setTitle("User Settings");
        primaryStage.setScene(scene);
        primaryStage.show();
    } // end Start
    
} // end Class
