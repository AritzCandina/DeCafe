package com.decafe.resources;

public abstract class SoundPlayer {

    public static void playSound(String musicFile){
        ResourceProvider.createAudioFile(musicFile).play();
    }

}
