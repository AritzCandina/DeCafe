package com.decafe;

import java.io.IOException;

import com.decafe.resources.ResourceProvider;
import com.decafe.resources.files.ImageFiles;
import com.decafe.resources.files.Scenes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ApplicationMain extends Application {

    public static final String GAME_TITLE = "DeCafé";
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        ApplicationMain.stage = stage;
        stage.getIcons().add(ResourceProvider.createImage(ImageFiles.MUG_TAB));
        stage.setTitle(GAME_TITLE);
        ApplicationMain.stage.setResizable(false);
        ResourceProvider.loadScene(Scenes.START);
    }

    public static void main(String[] args) {
        launch();
    }
}