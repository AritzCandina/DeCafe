package com.decafe.game.entities;

import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

import com.decafe.game.ProductType;
import com.decafe.resources.ResourceProvider;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.css.PseudoClass;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Machine {
    private int productionDuration;
    private Boolean produced;
    private final String filenameImageMachineWithoutProduct;
    private final String filenameImageMachineWithProduct;
    private final ProductType productType;

    private static final int PROGRESSBAR_KEYFRAME_DURATION = 300;
    private static final long PRODUCTION_DURATION_MULTIPLICATOR = 1000;
    public static final int PROGRESSBAR_MAX_STATUS = 12;

    public Machine(int duration, String filenameImageMachineWithProduct, String filenameImageMachineWithoutProduct, ProductType productType){
        this.productionDuration = duration;
        this.produced = false;
        this.filenameImageMachineWithProduct = filenameImageMachineWithProduct;
        this.filenameImageMachineWithoutProduct = filenameImageMachineWithoutProduct;
        this.productType = productType;
    }

    public int getProductionDuration() { return productionDuration; }

    public Boolean getProduced() { return produced; }

    public void setProductionDuration(int productionDuration) { this.productionDuration = productionDuration; }

    public void setProduced(Boolean produced){ this.produced = produced; }


    public void doProgressBarAnimation(Timer productionTimer, ImageView machineImageView, ProgressBar machineProgressBar, Image imageProductProduced){
        machineImageView.setDisable(true);
        machineProgressBar.setVisible(true);

        // source: https://stackoverflow.com/questions/18539642/progressbar-animated-javafx
        Timeline task = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(machineProgressBar.progressProperty(), 0)
                ),
                new KeyFrame(
                        // Set the duration of the progressbar animation
                        Duration.seconds(this.getProductionDuration()),
                        new KeyValue(machineProgressBar.progressProperty(), 1)
                )
        );

        IntegerProperty statusCountProperty = new SimpleIntegerProperty(1);
        Timeline timelineBar = new Timeline(
                new KeyFrame(
                        // Set this value for the speed of the animation
                        Duration.millis(PROGRESSBAR_KEYFRAME_DURATION),
                        new KeyValue(statusCountProperty, PROGRESSBAR_MAX_STATUS)
                )
        );

        timelineBar.setCycleCount(Timeline.INDEFINITE);
        timelineBar.play();
        statusCountProperty.addListener((ov, statusOld, statusNewNumber) -> {
            int statusNew = statusNewNumber.intValue();
            machineProgressBar.pseudoClassStateChanged(PseudoClass.getPseudoClass("status" + statusOld.intValue()), false);
            machineProgressBar.pseudoClassStateChanged(PseudoClass.getPseudoClass("status" + statusNew), true);
        });
        task.playFromStart();

        // source: https://stackoverflow.com/questions/2258066/run-a-java-function-after-a-specific-number-of-seconds
        productionTimer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        // After a certain time was reached change the Image of the Machine
                        machineImageView.setImage(imageProductProduced);
                        // And make the Machine clickable again
                        machineImageView.setDisable(false);
                        // And stop all timers
                        task.stop();
                        timelineBar.stop();
                        productionTimer.cancel();
                    }
                },
                this.productionDuration * PRODUCTION_DURATION_MULTIPLICATOR
        );
    }

    public void displayProduct (ImageView waiterImageView, ImageView machineImageView, Player cofiBrew, ProgressBar machineProgressBar) throws FileNotFoundException {

        Timer productionTimer = new Timer();
        String imageMachine = this.filenameImageMachineWithProduct;
        String imageCofi = cofiBrew.getFilenameImageWithoutProduct();

        boolean gotProduced = false;

        if (!this.produced && cofiBrew.getProductInHand().equals(ProductType.NONE)) {
            this.setProduced(true);
            gotProduced = true;
        } else if (!this.produced && cofiBrew.getProductInHand().equals(ProductType.COFFEE)) {
            this.setProduced(true);
            gotProduced = true;
            imageCofi = cofiBrew.getFilenameImageWithCoffee();
        } else if (!this.produced && cofiBrew.getProductInHand().equals(ProductType.CAKE)) {
            this.setProduced(true);
            gotProduced = true;
            imageCofi = cofiBrew.getFilenameImageWithCake();
        } else {
            if (cofiBrew.getProductInHand().equals(ProductType.NONE)){
                this.setProduced(false);
                imageMachine = this.filenameImageMachineWithoutProduct;
                cofiBrew.setProductInHand(this.productType);
                if (this.productType.equals(ProductType.COFFEE)){
                    imageCofi = cofiBrew.getFilenameImageWithCoffee();
                } else {
                    imageCofi = cofiBrew.getFilenameImageWithCake();
                }
            } else {
                    if (cofiBrew.getProductInHand().equals(ProductType.COFFEE)){
                        imageCofi = cofiBrew.getFilenameImageWithCoffee();
                    } else {
                        imageCofi = cofiBrew.getFilenameImageWithCake();
                    }
            }
        }

        waiterImageView.setImage(ResourceProvider.createImage((imageCofi)));

        if (gotProduced) {
            doProgressBarAnimation(productionTimer, machineImageView, machineProgressBar, ResourceProvider.createImage((imageMachine)));
        } else {
            machineProgressBar.setVisible(this.getProduced());
            machineImageView.setImage(ResourceProvider.createImage(imageMachine));
        }
    }

}
