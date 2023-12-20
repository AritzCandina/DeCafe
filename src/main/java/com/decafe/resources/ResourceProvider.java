package com.decafe.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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

    private static String getResourceFilePath(){
        return new File("").getAbsolutePath() + File.separator + DIR_SRC + File.separator + DIR_MAIN + File.separator + DIR_RESOURCES + File.separator + DIR_COM + File.separator + DIR_DECAFE + File.separator;
    }

}
