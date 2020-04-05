/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardtastic.card.game;

import GUI.Solitaire;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javax.swing.ImageIcon;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.lang.String;
import java.util.HashSet;
import javafx.event.EventType;

public class Main extends Application {

    Stage window;
    Button solitaireButton, freeCellButton, blackJackButton, warButton, crazyButton;
    VBox mainLayout, menuVbox;
    HBox cardGames;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Top Menu Bar
        MenuBar menuBar = new MenuBar();
        VBox menuVbox = new VBox(); // Is this needed?
        Menu userMenu = new Menu("User");
        Menu helpMenu = new Menu("Help");

        MenuItem loginButton = new MenuItem("Login");
        MenuItem menuItem2 = new MenuItem("Log out");
        MenuItem settingsMenuItem = new MenuItem("Settings");

        MenuItem userHelpItem = new MenuItem("User Help");
        MenuItem gameHelpItem = new MenuItem("Game Help");

        userMenu.getItems().add(loginButton);
        userMenu.getItems().add(menuItem2);
        userMenu.getItems().add(settingsMenuItem);

        helpMenu.getItems().add(userHelpItem);
        helpMenu.getItems().add(gameHelpItem);

        menuBar.getMenus().add(userMenu);
        menuBar.getMenus().add(helpMenu);

        // Menu bar actions
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                LoginPage loginPage = new LoginPage();
                loginPage.start();
            }
        });

        settingsMenuItem.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                Settings settingsPage = new Settings();
                Stage newStage = new Stage();
                settingsPage.start(newStage);
            }
        });

//        userHelpItem.setOnAction(new EventHandler() {
//            @Override
//            public void handle(Event event) {
//                HelpUser helpUser = new HelpUser();
//                Stage newStage = new Stage();
//                helpUser.start(newStage);
//            }
//        });
        gameHelpItem.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                HelpGame helpGame = new HelpGame();
                Stage newStage = new Stage();
                helpGame.start(newStage);
            }
        });

        // Window
        window = primaryStage;
        window.setTitle("Card Games");

        // MainLayout
        mainLayout = new VBox();
        mainLayout.setPrefWidth(300);
        mainLayout.setSpacing(20);
        mainLayout.setPadding(new Insets(0, 0, 0, 0));
        FileReader fr = new FileReader("Settings.txt"); mainLayout.setStyle(fr.readFile().get(1));        

        // HBox for card game buttons
        cardGames = new HBox();
        cardGames.setPrefWidth(300);
        cardGames.setSpacing(20);
        cardGames.setPadding(new Insets(200, 0, 0, 0));

        // Title
        Label cardGamesTitle = new Label("Card Games");
        cardGamesTitle.setFont(new Font("calibre", 40));

        // Buttons
        solitaireButton = new Button("Solitaire");
        freeCellButton = new Button("Free Cell");
        blackJackButton = new Button("Black Jack");
        warButton = new Button("War");
        crazyButton = new Button("Crazy 8s");

        // Button font and base color
        solitaireButton.setStyle("-fx-font: 22 calibre; -fx-base: tomato");
        freeCellButton.setStyle("-fx-font: 22 calibre; -fx-base: maroon");
        blackJackButton.setStyle("-fx-font: 22 calibre; -fx-base: red");
        warButton.setStyle("-fx-font: 22 calibre; -fx-base: pink");
        crazyButton.setStyle("-fx-font: 22 calibre; -fx-base: blue");

        // Action Methods for each Button
        blackJackButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                BlackJack blackJackGame = new BlackJack();
                blackJackGame.start(primaryStage);
            }
        });

        warButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                War warGame = new War();
                warGame.start(primaryStage);
            }
        });

        crazyButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                CrazyEights crazyGame = new CrazyEights();
                crazyGame.start(primaryStage);
            }
        });
        
        freeCellButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                freeCell freeCell = new freeCell(); 
                freeCell.main(primaryStage);
            }
        });

        solitaireButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                Solitaire solitaire = new Solitaire();
                solitaire.start(primaryStage);
            }
        });

        // end Action Methods
        // Add elements to GUI
        mainLayout.getChildren().add(menuBar);
        mainLayout.getChildren().add(cardGamesTitle);
        cardGames.getChildren().addAll(solitaireButton, freeCellButton, blackJackButton, warButton, crazyButton);
        mainLayout.getChildren().add(cardGames);

        // GUI Alignment
        cardGames.setAlignment(Pos.TOP_CENTER);
        mainLayout.setAlignment(Pos.TOP_CENTER);

        //Show window
        Scene main = new Scene(mainLayout, 1280, 550);
        //main.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        window.setScene(main);
        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
