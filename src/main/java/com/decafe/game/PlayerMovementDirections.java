package com.decafe.game;

import com.decafe.resources.files.ImageFiles;

public enum PlayerMovementDirections {
    LEFT(ImageFiles.COFI_BREW_LEFT, ImageFiles.COFI_BREW_CAKE_LEFT, ImageFiles.COFI_BREW_COFFEE_LEFT),
    RIGHT(ImageFiles.COFI_BREW_RIGHT, ImageFiles.COFI_BREW_CAKE_RIGHT, ImageFiles.COFI_BREW_COFFEE_RIGHT),
    UP(ImageFiles.COFI_BREW_UP, ImageFiles.COFI_BREW_CAKE_UP, ImageFiles.COFI_BREW_COFFEE_UP),
    DOWN(ImageFiles.COFI_BREW_DOWN, ImageFiles.COFI_BREW_CAKE_DOWN, ImageFiles.COFI_BREW_COFFEE_DOWN),
    NONE();

    private String cofiBrewImage;
    private String cofiBrewCakeImage;
    private String cofiBrewCoffeeImage;

    PlayerMovementDirections(String cofiBrewImage, String cofiBrewCakeImage, String cofiBrewCoffeeImage){
        this.cofiBrewImage = cofiBrewImage;
        this.cofiBrewCakeImage = cofiBrewCakeImage;
        this.cofiBrewCoffeeImage = cofiBrewCoffeeImage;
    }

    PlayerMovementDirections(){
    }

    public String getCofiBrewImage() {
        return cofiBrewImage;
    }

    public String getCofiBrewCakeImage() {
        return cofiBrewCakeImage;
    }

    public String getCofiBrewCoffeeImage() {
        return cofiBrewCoffeeImage;
    }

}
