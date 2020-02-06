/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardtastic.card.game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
        
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: ForestGreen");
        Scene scene = new Scene(root, 500, 500);
        
        primaryStage.setTitle("User Settings");
        primaryStage.setScene(scene);
        primaryStage.show();
    } // end Start
} // end Class
