package com.decafe.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class ResourceProvider {

    public static Image createImage(String filename) throws FileNotFoundException {
        InputStream stream = new FileInputStream(getResourceFilePath() + filename); // Convert path into stream
        return new Image(stream);
    }

    public static AudioClip createAudioFile(String filename){
        String fileName = getResourceFilePath() + filename;
        return new AudioClip(new File(fileName).toURI().toString());
    }

    private static String getResourceFilePath(){
        return new File("").getAbsolutePath() + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "com" + File.separator + "decafe" + File.separator;
    }

}
