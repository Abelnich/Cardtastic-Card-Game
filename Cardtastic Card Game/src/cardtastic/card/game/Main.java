/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardtastic.card.game;

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
public class Main extends Application {
    
    //test for push


   Stage window;
    Button solitaireButton, freeCellButton, blackJackButton;
    VBox mainLayout, menuVbox;
    HBox cardGames;

    @Override
    public void start(Stage primaryStage) throws Exception{
       // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
       // primaryStage.setTitle("Card Games");
        //primaryStage.setScene(new Scene(root, 500, 475));


        //Menu bar
        MenuBar menuBar = new MenuBar();
        VBox menuVbox = new VBox();
        Menu menu1 = new Menu("User");

        //adding login and log out options to menu1
        MenuItem menuItem1 = new MenuItem("Login");
        MenuItem menuItem2 = new MenuItem("Log out");

        menu1.getItems().add(menuItem1);
        menu1.getItems().add(menuItem2);
        menuBar.getMenus().add(menu1);

        //LoginPage loginPage = new LoginPage();

//        menuItem1.setOnAction(new EventHandler() {
//            @Override
//            public void handle(Event event) {
//                LoginPage loginPage = new LoginPage();
//                loginPage.launch(primaryStage);
//
//                //Label greetings = new Label();
//                //greetings.setText("Accepted");
//
//            }
//        });



        //window
        window = primaryStage;
        window.setTitle("Card Games");

        //VBox
        mainLayout = new VBox();
        mainLayout.setPrefWidth(300);
        mainLayout.setSpacing(20);
        mainLayout.setPadding(new Insets(0, 0, 0, 0));


        //MainLayout color
        mainLayout.setStyle("-fx-background-color: ForestGreen");

        //HBox for button
        cardGames = new HBox();
        cardGames.setPrefWidth(300);
        cardGames.setSpacing(20);
        cardGames.setPadding(new Insets( 200, 0, 0, 0));


        //Title
        Label cardGamesTitle = new Label("Card Games");

        //Title font
        cardGamesTitle.setFont(new Font("calibre", 40));


        //Buttons
        solitaireButton = new Button("Solitaire");
        freeCellButton = new Button("Free Cell");
        blackJackButton = new Button("Black Jack");

        //Button font and base color
        solitaireButton.setStyle("-fx-font: 22 calibre; -fx-base: tomato");
        freeCellButton.setStyle("-fx-font: 22 calibre; -fx-base: maroon");
        blackJackButton.setStyle("-fx-font: 22 calibre; -fx-base: red");

        //Button images

        //Image clubButtonImage = new ImageIcon("club.png").getImage();

        //Image clubButtonImage = new Image(getClass().getResourceAsStream(("club.png")));
        //ImageView clubButtonView = new ImageView(clubButtonImage);
        //clubButtonView.setFitHeight(15);
        //clubButtonView.setFitWidth(15);
        //solitaireButton.setGraphic(clubButtonView);

        //Label greetings = new Label();


        //mapping buttons to run game when clicked
//        solitaireButton.setOnAction(new EventHandler() {
//            @Override
//            public void handle(Event event) {
//                ButtonClassTest buttonClassTest = new ButtonClassTest();
//                buttonClassTest.launch(primaryStage);
//
//                //Label greetings = new Label();
//                        //greetings.setText("Accepted");
//
//            }
//        });


        //Add elements to GUI
        cardGames.getChildren().addAll(solitaireButton, freeCellButton, blackJackButton);
        mainLayout.getChildren().addAll(menuBar);
        mainLayout.getChildren().addAll( cardGamesTitle);
        mainLayout.getChildren().addAll(cardGames);


        //center stuff optional
        cardGames.setAlignment(Pos.TOP_CENTER);
        mainLayout.setAlignment(Pos.TOP_CENTER);

        //Show window
        Scene main = new Scene(mainLayout, 1280, 550);
        window.setScene(main);
        window.show();




    }


    public static void main(String[] args) {
        launch(args);
    }
}
