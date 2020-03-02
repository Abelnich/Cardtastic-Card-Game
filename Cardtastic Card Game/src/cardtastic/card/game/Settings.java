/*
Sophomore Project
 */
package cardtastic.card.game;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author NickAbel
 */
public class Settings extends Application {
    
    Stage primaryStage;
    FileReader reader;
    ArrayList<String> settingsInfo;
    
    @Override
    public void start(Stage sentStage) {
        this.primaryStage = sentStage;
        
        reader = new FileReader("Settings.txt");
        settingsInfo = reader.readFile();
        
        if (settingsInfo.isEmpty()) {
            // If the settings file is empty, adds default settings for card and window backs
            settingsInfo.add(0, "CardBack_Red");
            settingsInfo.add(1, "Back_Green");
        }
        
        // VBox for all settings
        VBox AllSettingsVBox = new VBox();
        AllSettingsVBox.setSpacing(40);
        
        // Card Back HBox to hold each VBox
        HBox CardBackHBox = new HBox();
        CardBackHBox.setAlignment(Pos.CENTER);
        CardBackHBox.setSpacing(40);
        VBox RedCardVBox = new VBox();
        RedCardVBox.setAlignment(Pos.CENTER);
        VBox YellowCardVBox = new VBox();
        YellowCardVBox.setAlignment(Pos.CENTER);
        VBox GreenCardVBox = new VBox();
        GreenCardVBox.setAlignment(Pos.CENTER);
        VBox BlueCardVBox = new VBox();
        BlueCardVBox.setAlignment(Pos.CENTER);
        VBox PurpleCardVBox = new VBox();
        PurpleCardVBox.setAlignment(Pos.CENTER);
        VBox GrayCardVBox = new VBox();
        GrayCardVBox.setAlignment(Pos.CENTER);
        
        // Card Back Images 
        Image imgCardBackRed = new Image(new File("CardBack_Red.png").toURI().toString(), 75, 75, true, true);
        Image imgCardBackYellow = new Image(new File("CardBack_Yellow.png").toURI().toString(), 75, 75, true, true);
        Image imgCardBackGreen = new Image(new File("CardBack_Green.png").toURI().toString(), 75, 75, true, true);
        Image imgCardBackBlue = new Image(new File("CardBack_Blue.png").toURI().toString(), 75, 75, true, true);
        Image imgCardBackPurple = new Image(new File("CardBack_Purple.png").toURI().toString(), 75, 75, true, true);
        Image imgCardBackGray = new Image(new File("CardBack_Gray.png").toURI().toString(), 75, 75, true, true);
        
        ImageView redImgView = new ImageView(imgCardBackRed);
        ImageView yellowImgView = new ImageView(imgCardBackYellow);
        ImageView greenImgView = new ImageView(imgCardBackGreen);
        ImageView blueImgView = new ImageView(imgCardBackBlue);
        ImageView purpleImgView = new ImageView(imgCardBackPurple);
        ImageView grayImgView = new ImageView(imgCardBackGray);
        
        RedCardVBox.getChildren().add(redImgView);
        YellowCardVBox.getChildren().add(yellowImgView);
        GreenCardVBox.getChildren().add(greenImgView);
        BlueCardVBox.getChildren().add(blueImgView);
        PurpleCardVBox.getChildren().add(purpleImgView);
        GrayCardVBox.getChildren().add(grayImgView);
        // End Card Back Images
        
        // Card Back Toggles
        ToggleGroup cardBackToggle = new ToggleGroup();
        RadioButton radRedBack = new RadioButton();
        radRedBack.setToggleGroup(cardBackToggle);
        RadioButton radYellowBack = new RadioButton();
        radYellowBack.setToggleGroup(cardBackToggle);
        RadioButton radGreenBack = new RadioButton();
        radGreenBack.setToggleGroup(cardBackToggle);
        RadioButton radBlueBack = new RadioButton();
        radBlueBack.setToggleGroup(cardBackToggle);
        RadioButton radPurpleBack = new RadioButton();
        radPurpleBack.setToggleGroup(cardBackToggle);
        RadioButton radGrayBack = new RadioButton();
        radGrayBack.setToggleGroup(cardBackToggle);
        
        switch (settingsInfo.get(0)) {
            case "CardBack_Red":
                radRedBack.setSelected(true);
                break;
            case "CardBack_Yellow":
                radYellowBack.setSelected(true);
                break;
            case "CardBack_Green":
                radGreenBack.setSelected(true);
                break;
            case "CardBack_Blue":
                radBlueBack.setSelected(true);
                break;
            case "CardBack_Purple":  
                radPurpleBack.setSelected(true);
                break;
            case "CardBack_Gray":
                radGrayBack.setSelected(true);
                break;
        }
        
        RedCardVBox.getChildren().add(radRedBack);
        YellowCardVBox.getChildren().add(radYellowBack);
        GreenCardVBox.getChildren().add(radGreenBack);
        BlueCardVBox.getChildren().add(radBlueBack);
        PurpleCardVBox.getChildren().add(radPurpleBack);
        GrayCardVBox.getChildren().add(radGrayBack);
        // End Card Back Toggles
        
        CardBackHBox.getChildren().add(RedCardVBox);
        CardBackHBox.getChildren().add(YellowCardVBox);
        CardBackHBox.getChildren().add(GreenCardVBox);
        CardBackHBox.getChildren().add(BlueCardVBox);
        CardBackHBox.getChildren().add(PurpleCardVBox);
        CardBackHBox.getChildren().add(GrayCardVBox);
        
        AllSettingsVBox.getChildren().add(CardBackHBox);
        // End Card Back Settings
        
        // Background Settings
        HBox Background = new HBox();
        Background.setSpacing(40);
        Background.setAlignment(Pos.CENTER);
        VBox greenBGVBox = new VBox();
        greenBGVBox.setAlignment(Pos.CENTER);
        VBox redBGVBox = new VBox();
        redBGVBox.setAlignment(Pos.CENTER);
        VBox blueBGVBox = new VBox();
        blueBGVBox.setAlignment(Pos.CENTER);
        
        Rectangle greenBG = new Rectangle(75, 75);
        greenBG.setFill(Color.valueOf("Green"));
        greenBG.setStroke(Color.valueOf("Black"));
        greenBG.setStrokeWidth(3);
        greenBGVBox.getChildren().add(greenBG);
        Rectangle redBG = new Rectangle(75, 75);
        redBG.setFill(Color.valueOf("Red"));
        redBG.setStroke(Color.valueOf("Black"));
        redBG.setStrokeWidth(3);
        redBGVBox.getChildren().add(redBG);
        Rectangle blueBG = new Rectangle(75, 75);
        blueBG.setFill(Color.valueOf("Blue"));
        blueBG.setStroke(Color.valueOf("Black"));
        blueBG.setStrokeWidth(3);
        blueBGVBox.getChildren().add(blueBG);
        
        // Background toggles
        ToggleGroup backgroundTgl = new ToggleGroup();
        RadioButton greenBGTgl = new RadioButton();
        greenBGTgl.setToggleGroup(backgroundTgl);
        greenBGVBox.getChildren().add(greenBGTgl);
        RadioButton redBGTgl = new RadioButton();
        redBGTgl.setToggleGroup(backgroundTgl);
        redBGVBox.getChildren().add(redBGTgl);
        RadioButton blueBGTgl = new RadioButton();
        blueBGTgl.setToggleGroup(backgroundTgl);
        blueBGVBox.getChildren().add(blueBGTgl);
        
        switch(settingsInfo.get(1)) {
            // Reads the saved setting for the background and toggles the corresponding radio button
            case "Back_Green":
                greenBGTgl.setSelected(true);
                break;
            case "Back_Red":
                redBGTgl.setSelected(true);
                break;
            case "Back_Blue":
                blueBGTgl.setSelected(true);
                break;
        }
        
        Background.getChildren().add(greenBGVBox);
        Background.getChildren().add(redBGVBox);
        Background.getChildren().add(blueBGVBox);
        AllSettingsVBox.getChildren().add(Background);
        
        // Save and Close Buttons
        HBox buttonHBox = new HBox();
        buttonHBox.setSpacing(40);
        buttonHBox.setAlignment(Pos.CENTER);
        Button btnSave = new Button("Save");
        btnSave.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                reader.writeFile(settingsInfo);
            }
        }); 
        buttonHBox.getChildren().add(btnSave);
        Button btnCancel = new Button("Cancel");
        btnCancel.setTextFill(Paint.valueOf("Red"));
        btnCancel.setOnAction(this::CancelSettings);
        buttonHBox.getChildren().add(btnCancel);
        // End buttons
        
        AllSettingsVBox.getChildren().add(buttonHBox);
                
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: ForestGreen");
        
        Scene scene = new Scene(root, 500, 500);
        root.getChildren().add(AllSettingsVBox);
        
        primaryStage.setTitle("User Settings");
        primaryStage.setScene(scene);
        primaryStage.show();
    } // end Start
    
    public void CancelSettings(ActionEvent e) {
        Alert cancelAlert = new Alert(AlertType.CONFIRMATION);
        cancelAlert.setTitle("Discard Changes");
        cancelAlert.setHeaderText("You are about to discard any changes.");
        cancelAlert.setContentText("Are you sure?");
        
        Optional<ButtonType> result = cancelAlert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // user says okay den
            primaryStage.close();
        } else {
            // stay on page
        }
        
    }
    
} // end Class
