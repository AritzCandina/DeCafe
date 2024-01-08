package com.decafe.game.entities;

import java.io.FileNotFoundException;

import com.decafe.resources.ResourceProvider;

import javafx.scene.image.ImageView;

public class Upgrade {
    private final int coinsNeeded;
    private boolean alreadyUsedOnce;
    private final String filenameUpgradeNotUsed;
    private final String filenameUpgradeUsed;
    private final ImageView upgradeImageView;

    public Upgrade(int coinsNeeded, boolean alreadyUsedOnce, String filenameUpgradeNotUsed, String filenameUpgradeUsed, ImageView upgradeImageView){
        this.coinsNeeded = coinsNeeded;
        this.alreadyUsedOnce = alreadyUsedOnce;
        this.filenameUpgradeNotUsed = filenameUpgradeNotUsed;
        this.filenameUpgradeUsed = filenameUpgradeUsed;
        this.upgradeImageView = upgradeImageView;
    }

    public boolean isAlreadyUsedOnce() {
        return alreadyUsedOnce;
    }

    public int getCoinsNeeded() {
        return coinsNeeded;
    }

    public String getFilenameUpgradeUsed() {
        return filenameUpgradeUsed;
    }

    public String getFilenameUpgradeNotUsed() {
        return filenameUpgradeNotUsed;
    }

    public ImageView getUpgradeImageView() { return upgradeImageView; }

    public void setAlreadyUsedOnce(boolean alreadyUsedOnce) {
        this.alreadyUsedOnce = alreadyUsedOnce;
    }

    public int doUpgrade(int coin) throws FileNotFoundException {
        this.upgradeImageView.setImage(ResourceProvider.createImage(this.filenameUpgradeUsed));
        this.upgradeImageView.setDisable(true);
        this.setAlreadyUsedOnce(true);
        coin -= this.getCoinsNeeded();
        return coin;
    }

    public boolean isDisabled() {

        return false;
    }

    public void setDisabled(boolean b) {
    }
}
