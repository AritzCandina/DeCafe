package com.decafe.game;

import java.io.FileNotFoundException;

import com.decafe.game.entities.Customer;
import com.decafe.game.entities.Machine;
import com.decafe.game.entities.Player;
import com.decafe.game.entities.Upgrade;
import com.decafe.resources.ResourceProvider;
import com.decafe.resources.files.ImageFiles;

import javafx.scene.image.ImageView;

//Class that is used mainly to control certain assets of the Game like Machines, Upgrades and the Coin Score
public class Game {
    private final Machine coffeeMachine;
    private final Machine cakeMachine;
    private final Upgrade coffeeUpgrade;
    private final Upgrade cakeUpgrade;
    private final Upgrade playerUpgrade;
    private int coinsEarned;

    private static final int UPGRADED_MACHINE_DURATION = 2;
    private static final int MACHINE_DURATION = 5;
    private static final int UPGRADED_MOVEMENT_SPEED = 6;

    private static final int UPGRADE_COST_MACHINE = 20;
    private static final int UPGRADE_COST_PLAYER = 40;

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


    public int getCoinsEarned() { return coinsEarned; }



    public void checkUpgradePossible(Upgrade upgrade) throws FileNotFoundException {
        if (!upgrade.isAlreadyUsedOnce() && this.coinsEarned >= upgrade.getCoinsNeeded()){
            upgrade.getUpgradeImageView().setDisable(false);
            upgrade.getUpgradeImageView().setImage(ResourceProvider.createImage(upgrade.getFilenameUpgradeNotUsed()));
        } else {
            upgrade.getUpgradeImageView().setDisable(true);
            upgrade.getUpgradeImageView().setImage(ResourceProvider.createImage(upgrade.getFilenameUpgradeUsed()));
        }
    }

    public void doUpgrade(String type, Player CofiBrew) throws FileNotFoundException {
        switch (type) {
            case "coffee" -> {
                coinsEarned = coffeeUpgrade.doUpgrade(coinsEarned);
                coffeeMachine.setProductionDuration(UPGRADED_MACHINE_DURATION);
            }
            case "cake" -> {
                coinsEarned = cakeUpgrade.doUpgrade(coinsEarned);
                cakeMachine.setProductionDuration(UPGRADED_MACHINE_DURATION);
            }
            case "player" -> {
                coinsEarned = playerUpgrade.doUpgrade(coinsEarned);
                CofiBrew.setMovement(UPGRADED_MOVEMENT_SPEED);
            }
        }
    }

    public void setCoinsEarned(Customer customer){
        if (customer.isGreen()){
            this.coinsEarned += 7;
        } else if (customer.isYellow()){
            this.coinsEarned += 5;
        }else if (customer.isRed()){
            this.coinsEarned += 3;
        }
    }
}
