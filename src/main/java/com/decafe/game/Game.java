package com.decafe.game;

import java.io.FileNotFoundException;

import com.decafe.game.entities.*;
import com.decafe.resources.ResourceProvider;
import com.decafe.resources.files.ImageFiles;

import javafx.scene.image.ImageView;

//Class that is used mainly to control certain assets of the Game like Machines, Upgrades and the Coin Score
public class Game {
    public Machine coffeeMachine = null;
    public Machine cakeMachine = null;
    public Upgrade coffeeUpgrade = null;
    public Upgrade cakeUpgrade = null;
    public Upgrade playerUpgrade = null;
    public int coinsEarned;

    public static final int UPGRADED_MACHINE_DURATION = 2;
    public static final int MACHINE_DURATION = 5;
    public static final int UPGRADED_MOVEMENT_SPEED = 6;

    public static final int UPGRADE_COST_MACHINE = 20;
    public static final int UPGRADE_COST_PLAYER = 40;

    public static final String UPGRADE_TYPE_COFFEE = "coffee";
    public static final String UPGRADE_TYPE_CAKE = "cake";
    public static final String UPGRADE_TYPE_PLAYER = "player";

    public static final int COIN_REWARD_GREEN = 7;
    public static final int COIN_REWARD_YELLOW = 5;
    public static final int COIN_REWARD_RED = 3;

    public Game(){

    }

    public Game(ImageView upgradeCoffee, ImageView upgradeCake, ImageView upgradePlayer){
        this.coffeeMachine = new Machine(MACHINE_DURATION, ImageFiles.COFFEE_MACHINE_WITH_COFFEE, ImageFiles.COFFEE_MACHINE, ProductType.COFFEE);
        this.cakeMachine = new Machine(MACHINE_DURATION, ImageFiles.KITCHEN_AID_USED, ImageFiles.KITCHEN_AID, ProductType.CAKE);
        this.coffeeUpgrade = new Upgrade(UPGRADE_COST_MACHINE, false, ImageFiles.COFFEE_UPGRADE, ImageFiles.COFFEE_USED, upgradeCoffee);
        this.cakeUpgrade = new Upgrade(UPGRADE_COST_MACHINE, false, ImageFiles.CAKE_UPGRADE, ImageFiles.CAKE_USED, upgradeCake);
        this.playerUpgrade = new Upgrade(UPGRADE_COST_PLAYER, false, ImageFiles.UPGRADE_SKATES, ImageFiles.UPGRADE_SKATES_USED,  upgradePlayer);
        this.coinsEarned = 0;

    }

    public Machine getCakeMachine() {
        return cakeMachine;
    }

    public Machine getCoffeeMachine() {
        return coffeeMachine;
    }

    public Upgrade getCakeUpgrade() {
        return cakeUpgrade;
    }

    public Upgrade getCoffeeUpgrade() {
        return coffeeUpgrade;
    }

    public Upgrade getPlayerUpgrade() {
        return playerUpgrade;
    }

    public int getCoinsEarned() {
        return coinsEarned;
    }


    public void checkUpgradePossible(Upgrade upgrade) throws FileNotFoundException {
        if (!upgrade.isAlreadyUsedOnce() && this.coinsEarned >= upgrade.getCoinsNeeded()){
            upgrade.getUpgradeImageView().setDisable(false);
            upgrade.getUpgradeImageView().setImage(ResourceProvider.createImage(upgrade.getFilenameUpgradeNotUsed()));
        } else {
            upgrade.getUpgradeImageView().setDisable(true);
            upgrade.getUpgradeImageView().setImage(ResourceProvider.createImage(upgrade.getFilenameUpgradeUsed()));
        }
    }

    public void doUpgrade(String type, Player cofiBrew) throws FileNotFoundException {
        switch (type) {
            case UPGRADE_TYPE_COFFEE -> {
                coinsEarned = coffeeUpgrade.doUpgrade(coinsEarned);
                coffeeMachine.setProductionDuration(UPGRADED_MACHINE_DURATION);
            }
            case UPGRADE_TYPE_CAKE -> {
                coinsEarned = cakeUpgrade.doUpgrade(coinsEarned);
                cakeMachine.setProductionDuration(UPGRADED_MACHINE_DURATION);
            }
            case UPGRADE_TYPE_PLAYER -> {
                coinsEarned = playerUpgrade.doUpgrade(coinsEarned);
                cofiBrew.setMovement(UPGRADED_MOVEMENT_SPEED);
            }
        }
    }

    public void setCoinsEarned(Customer customer){
        if (customer.isGreen()){
            this.coinsEarned += COIN_REWARD_GREEN;
        } else if (customer.isYellow()){
            this.coinsEarned += COIN_REWARD_YELLOW;
        }else if (customer.isRed()){
            this.coinsEarned += COIN_REWARD_RED;
        }
    }
}
