package com.decafe.game.entities;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.decafe.resources.ResourceProvider;
import com.decafe.resources.SoundPlayer;
import com.decafe.resources.files.ImageFiles;
import com.decafe.resources.files.MusicFiles;

import javafx.scene.image.ImageView;

public class Customer {
    private ProductType order;
    private ImageView customer;
    private ImageView orderLabel;
    private int chair;
    private Timer sixtySecondsTimer;
    private static Timer spawnTimer;
    private ImageView smiley;
    private ImageView coinImage;

    private boolean alreadyOrdered;
    private boolean green;
    private boolean yellow;
    private boolean red;

    private boolean leftUnhappy = true;

    public static List<Customer> customersInCoffeeShop = new ArrayList<>();
    public static List<Customer> allCustomers = new ArrayList<>();
    public static List<Integer> freeChairs;
    public static ImageView[] customerImages;
    private static int freeSeatChosen = 0;
    public static ImageView[] smileyImages;
    public static ImageView[] orderLabels;
    public static ImageView[] coinImages;

    private static final int SPAWN_TIMER_DELAY_DEFAULT = 1000;
    private static final int RESPWAN_TIMER_DEFAULT = 5;
    public static final int MAX_NUMBER_CUSTOMERS = 3;
    public static final long START_TIMER_DELAY_MULTIPLICATOR = 1000L;
    public static final int CUSTOMER_WATING_TIME = 60;

    public Customer(){}
    Customer(ImageView image, ImageView label, int chair, ImageView smiley, ImageView coinImage) {
        this.customer = image;
        this.orderLabel = label;
        this.alreadyOrdered = false;
        this.chair = chair;
        this.smiley = smiley;
        this.coinImage = coinImage;
        this.sixtySecondsTimer = new Timer();
    }

    public static Timer getSpawnTimer() {
        return spawnTimer;
    }

    public Timer getSixtySecondsTimer() {
        return sixtySecondsTimer;
    }

    public static void addFreeSeat(int chairLeft) { //add chair number to the list when customer has left
        Customer.freeChairs.add(chairLeft);
    }

    public boolean isGreen() { //to see if the color of the smiley
        return green;
    }

    public boolean isRed() { //to see if the color of the smiley
        return red;
    }

    public boolean isYellow() { //to see if the color of the smiley
        return yellow;
    }

    public boolean isAlreadyOrdered() { //return if the customer has already ordered or not
        return this.alreadyOrdered;
    }

    public ProductType getOrder() { //returns the order of the customer
        return order;
    }

    public int getChair() { //get the number of the chair the customer is sitting
        return chair;
    }

    public ImageView getCorrectCustomerImage() { //returns the image of the customer
        return this.customer;
    }

    public ImageView getCorrectCustomerOrderLabel() { //returns the label of the customer
        return this.orderLabel;
    }

    public ProductType getRandomOrder() { //returns random order

        Random random = new Random();
        int number = random.nextInt(2);

        switch (number) {
            case 0 -> order = ProductType.CAKE;
            case 1 -> order = ProductType.COFFEE;
        }

        return order;
    }

    public ImageView getCoinImage() { //returns the image of the coin
        return coinImage;
    }

    public void setOrder(ProductType order) { //sets the order of the customer
        this.order = order;
    }

    public static void setSpawnTimer(Timer spawnTimer) { //sets the timer
        Customer.spawnTimer = spawnTimer;
    }

    public static ImageView getCorrectCustomerImage(ImageView customer, ImageView[] searchArray ){
        ImageView wantedImage = new ImageView();

        if (customerImages[0].equals(customer)) {
            wantedImage = searchArray[0];
        } else if (customerImages[1].equals(customer)) {
            wantedImage = searchArray[1];
        } else if (customerImages[2].equals(customer)) {
            wantedImage = searchArray[2];
        } else if (customerImages[3].equals(customer)) {
            wantedImage = searchArray[3];
        } else if (customerImages[4].equals(customer)) {
            wantedImage = searchArray[4];
        } else if (customerImages[5].equals(customer)) {
            wantedImage = searchArray[5];
        } else if (customerImages[6].equals(customer)) {
            wantedImage = searchArray[6];
        }

        return wantedImage;
    }

    public static ImageView getCorrectCustomerOrderLabel(ImageView customer) {

        ImageView customerOrder = new ImageView();

        if (customerImages[0].equals(customer)) {
            customerOrder = orderLabels[0];
        } else if (customerImages[1].equals(customer)) {
            customerOrder = orderLabels[1];
        } else if (customerImages[2].equals(customer)) {
            customerOrder = orderLabels[2];
        } else if (customerImages[3].equals(customer)) {
            customerOrder = orderLabels[3];
        } else if (customerImages[4].equals(customer)) {
            customerOrder = orderLabels[4];
        } else if (customerImages[5].equals(customer)) {
            customerOrder = orderLabels[5];
        } else if (customerImages[6].equals(customer)) {
            customerOrder = orderLabels[6];
        }

        return customerOrder;

    }

    public static ImageView getRandomCustomerPicture(){
        Random random = new Random();
        int index = freeChairs.get(random.nextInt(freeChairs.size()));
        freeSeatChosen = index;

        if (!freeChairs.contains(index)) {
            getRandomCustomerPicture();
        }

        freeChairs.remove(Integer.valueOf(index)); //remove the number from the number list of chairs so there are no duplicates

        return customerImages[index];
    }


    public static void spawnCustomers(){
        if (customersInCoffeeShop.size() < MAX_NUMBER_CUSTOMERS && !freeChairs.isEmpty()) { //spawn a new customer this when under 3 customers are in the café
            ImageView customerImage = getRandomCustomerPicture();
            customerImage.setVisible(true);

            ImageView order = getCorrectCustomerOrderLabel(customerImage);
            ImageView smiley = getCorrectCustomerImage(customerImage, smileyImages);
            ImageView coin = getCorrectCustomerImage(customerImage, coinImages);

            Customer customer = new Customer(customerImage, order, freeSeatChosen, smiley, coin);
            customersInCoffeeShop.add(customer);
            allCustomers.add(customer);

            SoundPlayer.playSound(MusicFiles.DOOR_BELL);
            customer.waitingTime(); //place customer in the waitingTime of  60 seconds
        }
    }

    public void startTimerSpawn(int duration, Timer spawnTimer){
        spawnTimer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        Customer.spawnCustomers();
                        spawnTimer.purge();
                    }
                },
                duration * START_TIMER_DELAY_MULTIPLICATOR
        );
    }


    public void startTimerLeave (Customer customer){
        this.orderLabel.setVisible(false);
        this.smiley.setVisible(false);
        spawnTimer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            leave(customer.getCorrectCustomerImage());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        spawnTimer.purge();
                    }
                },
                SPAWN_TIMER_DELAY_DEFAULT
        );
        this.sixtySecondsTimer.cancel();
    }


    public void waitingTime()  {
        Customer customer = this;
        TimerTask timerTask = new TimerTask() {
            int seconds = CUSTOMER_WATING_TIME;
            @Override
            public void run() {
                seconds --;
                if (seconds == CUSTOMER_WATING_TIME - 1){ //set green smiley when the customer has just spawned
                    smiley.setVisible(true);
                    try {
                        smiley.setImage(ResourceProvider.createImage(ImageFiles.SIMLEY_GREEN));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    green = true;
                    yellow = false;
                    red = false;
                }else if (seconds == CUSTOMER_WATING_TIME / 2){ //set yellow smiley when the customer has just spawned
                    smiley.setVisible(true);
                    try {
                        smiley.setImage(ResourceProvider.createImage(ImageFiles.SMILEY_YELLOW));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    green = false;
                    yellow = true;
                    red = false;
                }else if (seconds == CUSTOMER_WATING_TIME / 4){ //set red smiley when the customer has just spawned
                    smiley.setVisible(true);
                    try {
                        smiley.setImage(ResourceProvider.createImage(ImageFiles.SMILEY_RED));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    green = false;
                    yellow = false;
                    red = true;
                }
                else if (seconds == 0){ //when the timer has finished - customer leaves
                    startTimerLeave(customer);
                }
            }
        };

        this.sixtySecondsTimer.schedule(timerTask, 0, 1000);//it should call this methode every second

    }

    public void displayOrder(ImageView orderlabel) throws FileNotFoundException {
        this.order = getRandomOrder();
        setOrder(order);
        if(order.equals(ProductType.CAKE)) {
            if (chair == 0 || chair == 1 || chair == 4 || chair == 6) {
                orderlabel.setVisible(true);
                orderlabel.setImage(ResourceProvider.createImage(ImageFiles.BUBBLE_CAKE_TOP_LEFT));
            } else if(chair == 2 || chair == 3){
                orderlabel.setVisible(true);
                orderlabel.setImage(ResourceProvider.createImage(ImageFiles.BUBBLE_CAKE_TOP_RIGHT));
            } else if(chair == 5) {
                orderlabel.setVisible(true);
                orderlabel.setImage(ResourceProvider.createImage(ImageFiles.BUBBLE_CAKE_BOTTOM_RIGHT));
            }
        } else if(order.equals(ProductType.COFFEE)){
            if (chair == 0 || chair == 1 || chair == 4 || chair == 6) {
                orderlabel.setVisible(true);
                orderlabel.setImage(ResourceProvider.createImage(ImageFiles.BUBBLE_COFFEE_TOP_LEFT));
            } else if(chair == 2 || chair == 3){
                orderlabel.setVisible(true);
                orderlabel.setImage(ResourceProvider.createImage(ImageFiles.BUBBLE_COFFEE_TOP_RIGHT));
            } else if(chair == 5){
                orderlabel.setVisible(true);
                orderlabel.setImage(ResourceProvider.createImage(ImageFiles.BUBBLE_COFFEE_BOTTOM_RIGHT));
            }
        }
        this.alreadyOrdered = true;
    }


    public boolean checkOrder(Player CofiBrew, Customer customer, ImageView waiterImage) throws FileNotFoundException{
        waiterImage.setImage(ResourceProvider.createImage(CofiBrew.getFilenameImageWithoutProduct()));
        if (CofiBrew.getProductInHand().equals(customer.getOrder())) {
            CofiBrew.setProductInHand(ProductType.NONE);
            this.leftUnhappy = false;
            startTimerLeave(this);
            return true;
        } else {
            CofiBrew.setProductInHand(ProductType.NONE);
            startTimerLeave(this);
            return false;
        }
    }

    public static void noMoneySpent(Customer customer) throws FileNotFoundException {
        customer.coinImage.setVisible(false);
        customer.coinImage.setDisable(true);
        freeChairs.add(customer.getChair());
        customer.startTimerSpawn(RESPWAN_TIMER_DEFAULT, spawnTimer);
    }

    public void leave (ImageView customerImage) throws FileNotFoundException {
        customerImage.setVisible(false);
        customersInCoffeeShop.removeIf(customer -> customer.getCorrectCustomerImage().equals(customerImage));
        this.coinImage.setVisible(true);
        this.coinImage.setDisable(false);
        if (this.leftUnhappy){
            SoundPlayer.playSound(MusicFiles.WRONG_CHOICE);
            this.coinImage.setImage(ResourceProvider.createImage(ImageFiles.COIN));
            this.coinImage.setOnMouseClicked(event1 -> {
                try {
                    noMoneySpent(this);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
        } else {
            SoundPlayer.playSound(MusicFiles.RIGHT_CHOICE);
        }
    }
}

