package com.decafe.game;

public class Player {
    private final String filenameImageWithoutProduct;
    private final String filenameImageWithCoffee;
    private final String filenameImageWithCake;
    private String productInHand; // The type of product the waiter holds in his hands (Coffee or Cake)
    private int movementSpeed;

    private PlayerMovementDirection playerMovementDirection;

    // Constructor
    public Player(String filenameImageWithoutProduct, String filenameImageWithCake, String filenameImageWithCoffee, int movement) {
        this.filenameImageWithoutProduct = filenameImageWithoutProduct;
        this.filenameImageWithCake =  filenameImageWithCake;
        this.filenameImageWithCoffee = filenameImageWithCoffee;
        this.productInHand = "none";
        this.movementSpeed = movement;
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

