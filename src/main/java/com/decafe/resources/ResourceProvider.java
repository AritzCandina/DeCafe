package com.decafe.resources;

import java.io.*;

import com.decafe.ApplicationMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class ResourceProvider {

    private static final String DIR_SRC = "src";
    private static final String DIR_MAIN = "main";
    private static final String DIR_RESOURCES = "resources";
    private static final String DIR_COM = "com";
    private static final String DIR_DECAFE = "decafe";


    public static Image createImage(String filename) throws FileNotFoundException {
        InputStream stream = new FileInputStream(getResourceFilePath() + filename); // Convert path into stream
        return new Image(stream);
    }

    public static AudioClip createAudioFile(String filename){
        String fileName = getResourceFilePath() + filename;
        return new AudioClip(new File(fileName).toURI().toString());
    }

    public static void loadScene(String sceneName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource(sceneName));
        Scene scene = new Scene(fxmlLoader.load());
        ApplicationMain.stage.setScene(scene);
        ApplicationMain.stage.show();
    }

    private static String getResourceFilePath(){
        return new File("").getAbsolutePath() + File.separator + DIR_SRC + File.separator + DIR_MAIN + File.separator + DIR_RESOURCES + File.separator + DIR_COM + File.separator + DIR_DECAFE + File.separator;
    }

}
