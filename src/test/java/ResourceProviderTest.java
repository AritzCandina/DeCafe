import com.decafe.resources.ResourceProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ResourceProvider.class, FileInputStream.class})
public class ResourceProviderTest {

    @Mock
    private FileInputStream mockFileInputStream;

    @InjectMocks
    private ResourceProvider resourceProvider;

    @Test
    public void createImage_validFilename_returnsImage() throws Exception {
        // Mocking static method
        mockStatic(ResourceProvider.class);
        when(ResourceProvider.createFileInputStream(anyString())).thenReturn(mockFileInputStream);

        // Mocking constructor
        whenNew(Image.class).withAnyArguments().thenReturn(null); // You may need to adjust this based on the actual Image class

        String validFilename = "testImage.png";
        Image result = resourceProvider.createImage(validFilename);

        // Add assertions or verifications based on your actual implementation
    }

    @Test
    public void createAudioFile_validFilename_returnsAudioClip() throws Exception {
        // Mocking static method
        mockStatic(ResourceProvider.class);
        when(ResourceProvider.createFileInputStream(anyString())).thenReturn(mockFileInputStream);

        // Mocking constructor
        whenNew(AudioClip.class).withAnyArguments().thenReturn(null); // You may need to adjust this based on the actual AudioClip class

        String validFilename = "testAudio.mp3";
        AudioClip result = resourceProvider.createAudioFile(validFilename);

        // Add assertions or verifications based on your actual implementation
    }
}
