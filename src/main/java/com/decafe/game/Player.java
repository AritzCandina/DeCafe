package com.decafe.game;

import com.decafe.resources.files.ImageFiles;

public class Player {
    private final int DEFAULT_MOVEMENT_SPEED = 4;
    private final String filenameImageWithoutProduct;
    private final String filenameImageWithCoffee;
    private final String filenameImageWithCake;
    private String productInHand; // The type of product the waiter holds in his hands (Coffee or Cake)
    private int movementSpeed;

    private PlayerMovementDirection playerMovementDirection;

    // Constructor
    public Player() {
        this.filenameImageWithoutProduct = ImageFiles.COFI_BREW_UP;
        this.filenameImageWithCake =  ImageFiles.COFI_BREW_CAKE_UP;
        this.filenameImageWithCoffee = ImageFiles.COFI_BREW_COFFEE_UP;
        this.productInHand = "none";
        this.movementSpeed = DEFAULT_MOVEMENT_SPEED;
        this.playerMovementDirection = PlayerMovementDirection.NONE;
    }

    //Getter
    public String getProductInHand() {
        return productInHand;
    }

    public String getFilenameImageWithoutProduct() {
        return filenameImageWithoutProduct;
    }

    public String getFilenameImageWithCake() {
        return filenameImageWithCake;
    }

    public String getFilenameImageWithCoffee() {
        return filenameImageWithCoffee;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    //Setter
    public void setProductInHand(String productInHand) {
        this.productInHand = productInHand;
    }

    public void setMovement(int movement) {
        this.movementSpeed = movement;
    }

    public PlayerMovementDirection getPlayerMovementDirection() {
        return playerMovementDirection;
    }

    public void setPlayerMovementDirection(PlayerMovementDirection playerMovementDirection) {
        this.playerMovementDirection = playerMovementDirection;
    }
}
