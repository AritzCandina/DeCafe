package com.decafe;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ApplicationMain extends Application {

    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource("startScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ApplicationMain.stage = stage;
        stage.getIcons().add(new Image("file:src/main/resources/com/decafe/mugTabPic.png"));
        stage.setTitle("DeCafé");
        ApplicationMain.stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}