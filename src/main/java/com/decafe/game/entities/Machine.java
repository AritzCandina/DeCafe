package com.decafe.game.entities;

import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

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

    public static final int PROGRESSBAR_KEYFRAME_DURATION = 300;
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


    public void doProgressBarAnimation(Timer productionTimer, ImageView machineImageView, ProgressBar machineProgressBar, Image imageProductProduced) {
        prepareMachineForProduction(machineImageView, machineProgressBar);
        Timeline progressBarAnimation = createProgressBarAnimation(machineProgressBar);
        Timeline statusAnimation = createStatusAnimation(machineProgressBar);
        scheduleProductionCompletion(productionTimer, machineImageView, progressBarAnimation, statusAnimation, imageProductProduced);
    }

    public void prepareMachineForProduction(ImageView machineImageView, ProgressBar machineProgressBar) {
        machineImageView.setDisable(true);
        machineProgressBar.setVisible(true);
    }

    public Timeline createProgressBarAnimation(ProgressBar progressBar) {
        Timeline task = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progressBar.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(this.getProductionDuration()), new KeyValue(progressBar.progressProperty(), 1))
        );
        task.playFromStart();
        return task;
    }

    public Timeline createStatusAnimation(ProgressBar progressBar) {
        IntegerProperty statusCountProperty = new SimpleIntegerProperty(1);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(PROGRESSBAR_KEYFRAME_DURATION), new KeyValue(statusCountProperty, PROGRESSBAR_MAX_STATUS))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        statusCountProperty.addListener((ov, oldStatus, newStatus) -> updateProgressBarStatus(progressBar, oldStatus.intValue(), newStatus.intValue()));
        return timeline;
    }

    public void updateProgressBarStatus(ProgressBar progressBar, int oldStatus, int newStatus) {
        progressBar.pseudoClassStateChanged(PseudoClass.getPseudoClass("status" + oldStatus), false);
        progressBar.pseudoClassStateChanged(PseudoClass.getPseudoClass("status" + newStatus), true);
    }

    public void scheduleProductionCompletion(Timer timer, ImageView machineImageView, Timeline progressBarAnimation, Timeline statusAnimation, Image productImage) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                completeProduction(machineImageView, progressBarAnimation, statusAnimation, productImage);
            }
        }, this.productionDuration * PRODUCTION_DURATION_MULTIPLICATOR);
    }

    public void completeProduction(ImageView machineImageView, Timeline progressBarAnimation, Timeline statusAnimation, Image productImage) {
        machineImageView.setImage(productImage);
        machineImageView.setDisable(false);
        progressBarAnimation.stop();
        statusAnimation.stop();
    }


    public void displayProduct(ImageView waiterImageView, ImageView machineImageView, Player cofiBrew, ProgressBar machineProgressBar) throws FileNotFoundException {
        updateProductionState(cofiBrew);
        String imageCofi = getImageCofi(cofiBrew);
        String imageMachine = getProduced() ? filenameImageMachineWithProduct : filenameImageMachineWithoutProduct;

        waiterImageView.setImage(ResourceProvider.createImage(imageCofi));
        updateMachineView(machineImageView, machineProgressBar, imageMachine);
    }

    private void updateProductionState(Player cofiBrew) {
        ProductType productInHand = cofiBrew.getProductInHand();
        boolean noProductInHand = productInHand.equals(ProductType.NONE);
        boolean isCoffeeOrCake = productInHand.equals(ProductType.COFFEE) || productInHand.equals(ProductType.CAKE);

        if (!getProduced() && (noProductInHand || isCoffeeOrCake)) {
            setProduced(true);
        } else if (noProductInHand) {
            setProduced(false);
            cofiBrew.setProductInHand(this.productType);
        }
    }

    private String getImageCofi(Player cofiBrew) {
        return switch (cofiBrew.getProductInHand()) {
            case COFFEE -> cofiBrew.getFilenameImageWithCoffee();
            case CAKE -> cofiBrew.getFilenameImageWithCake();
            default -> cofiBrew.getFilenameImageWithoutProduct();
        };
    }

    public void updateMachineView(ImageView machineImageView, ProgressBar machineProgressBar, String imageMachine) throws FileNotFoundException {
        if (getProduced()) {
            Timer productionTimer = new Timer();
            doProgressBarAnimation(productionTimer, machineImageView, machineProgressBar, ResourceProvider.createImage(imageMachine));
        } else {
            machineProgressBar.setVisible(false);
            machineImageView.setImage(ResourceProvider.createImage(imageMachine));
        }
    }
}
