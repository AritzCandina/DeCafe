package com.decafe.tests;

import com.decafe.resources.ResourceProvider;
import com.decafe.resources.files.ImageFiles;
import com.decafe.resources.files.MusicFiles;
import javafx.scene.media.AudioClip;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ResourceProviderTest {


    @Test
    public void createImage_ValidFilename_ReturnsImage() throws IOException {

        javafx.scene.image.Image result = ResourceProvider.createImage(ImageFiles.COFI_BREW_CAKE_UP);

        assertNotNull(result);
    }

    @Test
    public void createAudioFile_ValidFilename_ReturnsAudioClip() {

        AudioClip result = ResourceProvider.createAudioFile(MusicFiles.DOOR_BELL);

        assertNotNull(result);
    }

}
