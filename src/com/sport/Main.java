package com.sport;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args){

        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("controllers/mainView.fxml"));

        stage.setTitle("Players DB");
        stage.setScene(new Scene(root, 1000,700));
        stage.show();
    }
}
