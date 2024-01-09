import com.decafe.game.entities.Machine;
import com.decafe.game.entities.Player;
import com.decafe.game.entities.ProductType;
import com.decafe.resources.files.ImageFiles;
import javafx.animation.Timeline;
import javafx.css.PseudoClass;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class MachineTest {

    private Machine machine;
    private ImageView machineImageView;
    private ProgressBar machineProgressBar;

    @BeforeEach
    void setUp()  {
        machineImageView = new ImageView();
        machineProgressBar = new ProgressBar();
        machine = new Machine(2, ImageFiles.COFFEE_MACHINE_WITH_COFFEE, ImageFiles.COFFEE_MACHINE, ProductType.COFFEE);
    }

    @Test
    void testGetProductionDuration() {
        assertEquals(2, machine.getProductionDuration());
    }

    @Test
    void testGetProduced() {
        assertFalse(machine.getProduced());
    }

    @Test
    void testSetProductionDuration() {
        machine.setProductionDuration(3);
        assertEquals(3, machine.getProductionDuration());
    }

    @Test
    void testSetProduced() {
        machine.setProduced(true);
        assertTrue(machine.getProduced());
    }

    @Test
    void testPrepareMachineForProduction() {
        machine.prepareMachineForProduction(machineImageView, machineProgressBar);
        assertTrue(machineProgressBar.isVisible());
        assertTrue(machineImageView.isDisabled());
    }

    @Test
    void testCreateProgressBarAnimation() {
        machine.setProductionDuration(1);
        Timeline timeline = machine.createProgressBarAnimation(machineProgressBar);
        assertEquals(2, timeline.getKeyFrames().size());
    }

    @Test
    void testCreateStatusAnimation() {
        Timeline timeline = machine.createStatusAnimation(machineProgressBar);
        assertEquals(1, timeline.getKeyFrames().size());
        assertEquals(Timeline.INDEFINITE, timeline.getCycleCount());
    }

    @Test
    void testUpdateProgressBarStatus() {
        machine.createStatusAnimation(machineProgressBar);
        machine.updateProgressBarStatus(machineProgressBar, 1, 2);
        assertFalse(machineProgressBar.getPseudoClassStates().contains(PseudoClass.getPseudoClass("status1")));
        assertTrue(machineProgressBar.getPseudoClassStates().contains(PseudoClass.getPseudoClass("status2")));
    }


    @Test
    void testDisplayProduct() throws FileNotFoundException {
        Player cofiBrew = new Player();
        cofiBrew.setProductInHand(ProductType.COFFEE);
        ImageView waiterImageView = mock(ImageView.class);

        machine.displayProduct(waiterImageView, machineImageView, cofiBrew, machineProgressBar);
        assertTrue(machine.getProduced());
        assertEquals(ProductType.COFFEE, cofiBrew.getProductInHand());
    }

    @Test
    void testUpdateMachineViewProduced() throws FileNotFoundException {
        machine.updateMachineView(machineImageView, machineProgressBar, ImageFiles.COFFEE_MACHINE_WITH_COFFEE);
        assertFalse(machineProgressBar.isVisible());
    }


}
