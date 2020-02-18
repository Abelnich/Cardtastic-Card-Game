/*
Sophomore Project
 */
package cardtastic.card.game;

import java.io.File;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    
    @Override
    public void start(Stage sentStage) {
        this.primaryStage = sentStage;
        
        FileReader reader = new FileReader("Settings.txt");
        
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
        Image redImg = new Image(new File("CardBack_Red.png").toURI().toString(), 75, 75, true, true);
        Image yellowImg = new Image(new File("CardBack_Yellow.png").toURI().toString(), 75, 75, true, true);
        Image greenImg = new Image(new File("CardBack_Green.png").toURI().toString(), 75, 75, true, true);
        Image blueImg = new Image(new File("CardBack_Blue.png").toURI().toString(), 75, 75, true, true);
        Image purpleImg = new Image(new File("CardBack_Purple.png").toURI().toString(), 75, 75, true, true);
        Image grayImg = new Image(new File("CardBack_Gray.png").toURI().toString(), 75, 75, true, true);
        
        ImageView redImgView = new ImageView();
        redImgView.setImage(redImg);
        ImageView yellowImgView = new ImageView(yellowImg);
        ImageView greenImgView = new ImageView(greenImg);
        ImageView blueImgView = new ImageView(blueImg);
        ImageView purpleImgView = new ImageView(purpleImg);
        ImageView grayImgView = new ImageView(grayImg);
        
        RedCardVBox.getChildren().add(redImgView);
        YellowCardVBox.getChildren().add(yellowImgView);
        GreenCardVBox.getChildren().add(greenImgView);
        BlueCardVBox.getChildren().add(blueImgView);
        PurpleCardVBox.getChildren().add(purpleImgView);
        GrayCardVBox.getChildren().add(grayImgView);
        // End Card Back Images
        
        // Card Back Toggles
        ToggleGroup cardBackToggle = new ToggleGroup();
        RadioButton redBackTGL = new RadioButton();
        redBackTGL.setToggleGroup(cardBackToggle);
        RadioButton yellowBackTGL = new RadioButton();
        yellowBackTGL.setToggleGroup(cardBackToggle);
        RadioButton greenBackTGL = new RadioButton();
        greenBackTGL.setToggleGroup(cardBackToggle);
        RadioButton blueBackTGL = new RadioButton();
        blueBackTGL.setToggleGroup(cardBackToggle);
        RadioButton purpleBackTGL = new RadioButton();
        purpleBackTGL.setToggleGroup(cardBackToggle);
        RadioButton grayBackTGL = new RadioButton();
        grayBackTGL.setToggleGroup(cardBackToggle);
        
        RedCardVBox.getChildren().add(redBackTGL);
        YellowCardVBox.getChildren().add(yellowBackTGL);
        GreenCardVBox.getChildren().add(greenBackTGL);
        BlueCardVBox.getChildren().add(blueBackTGL);
        PurpleCardVBox.getChildren().add(purpleBackTGL);
        GrayCardVBox.getChildren().add(grayBackTGL);
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
        greenBGVBox.getChildren().add(greenBG);
        Rectangle redBG = new Rectangle(75, 75);
        redBG.setFill(Color.valueOf("Red"));
        redBGVBox.getChildren().add(redBG);
        Rectangle blueBG = new Rectangle(75, 75);
        blueBG.setFill(Color.valueOf("Blue"));
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
        
        Background.getChildren().add(greenBGVBox);
        Background.getChildren().add(redBGVBox);
        Background.getChildren().add(blueBGVBox);
        AllSettingsVBox.getChildren().add(Background);
        
        // Save and Close Buttons
        HBox buttonHBox = new HBox();
        buttonHBox.setSpacing(40);
        buttonHBox.setAlignment(Pos.CENTER);
        Button btnSave = new Button("Save");
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
    
    public void LoadSettings() {
        
    }
    
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
