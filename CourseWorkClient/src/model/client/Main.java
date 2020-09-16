package model.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/authorization.fxml"));
            primaryStage.setTitle("Course Work");
            primaryStage.setScene(new Scene(root, 500, 400));
            primaryStage.show();
        }


        public static void main(String[] args) {
            launch(args);
        }
}
