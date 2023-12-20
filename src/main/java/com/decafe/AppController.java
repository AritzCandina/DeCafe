package com.decafe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.decafe.game.*;
import com.decafe.game.entities.Customer;
import com.decafe.game.entities.Player;
import com.decafe.game.entities.Upgrade;
import com.decafe.resources.ResourceProvider;
import com.decafe.resources.SoundPlayer;
import com.decafe.resources.files.ImageFiles;
import com.decafe.resources.files.MusicFiles;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;

// Class that is responsible for every action taken in JavaFX GUI (connected via fxml files)
public class AppController implements Initializable {

    // Assets of the Start Screen
    public ImageView startButton;
    public ImageView startQuitButton;

    //Assets of the Game Screen
    // Image of the waiter
    public ImageView waiterImageView;

    // Label that shows the current amount of coins earned
    public Label coinsEarnedLabel;
    // Used for controlling the movement of the Player
    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();
    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);

    // Images of the Object the Player can interact with
    public ImageView coffeeMachineImageView;
    public ImageView cakeMachineImageView;
    public ImageView trashcanImageView;

    // Progress bars used to show Production Progress
    public ProgressBar progressBarCoffee;
    public ProgressBar progressBarCake;

    // Images used to control Upgrades
    public ImageView upgradeCoffeeImageView;
    public ImageView upgradeCakeImageView;
    public ImageView upgradePlayerImageView;

    // Labels used for collision detection management
    public Label table1;
    public Label table2;
    public Label table3;
    public Label table4;
    public Label plantsAbove;
    public Label countRight;
    public Label countBelow;
    public Label customerTop1;
    public Label customerTop2;
    public Label customerTop3;
    public Label customerTop4;
    public Label customerBot1;
    public Label customerBot2;
    public Label customerBot3;
    public Label plant;
    public Label edgeBot;
    public Label edgeTop;
    public Label edgeLeft;
    public Label edgeRight;

    //for the customers
    //smiley images
    public ImageView smileyFirst;
    public ImageView smileySecond;
    public ImageView smileyThird;
    public ImageView smileyFourth;
    public ImageView smileyFifth;
    public ImageView smileySixth;
    public ImageView smileySeventh;

    //coin images
    public ImageView coinFirst;
    public ImageView coinSecond;
    public ImageView coinThird;
    public ImageView coinFourth;
    public ImageView coinFifth;
    public ImageView coinSixth;
    public ImageView coinSeventh;

    //order labels
    public ImageView orderlabel1 = new ImageView();
    public ImageView orderlabel2 = new ImageView();
    public ImageView orderlabel3 = new ImageView();
    public ImageView orderlabel4 = new ImageView();
    public ImageView orderlabel5 = new ImageView();
    public ImageView orderlabel6 = new ImageView();
    public ImageView orderlabel7 = new ImageView();

    //customer images
    public ImageView first;
    public ImageView second;
    public ImageView third;
    public ImageView fourth;
    public ImageView fifth;
    public ImageView sixth;
    public ImageView seventh;

    // for end screen
    public ImageView gameStartButton;
    public ImageView cofiBrewImage;
    public ImageView playAgainImage;
    public ImageView backToStartImage;
    public Label labelCredits;
    public ImageView endScreenBackground;
    public ImageView quitEndScreenImage;

    private final int durationForStartTimer1 = 1;
    private final int durationForStartTimer5 = 5;
    private final int durationForStartTimer10 = 10;

    // Player object created to change Images and movement Speed
    public Player player = new Player();
    // Game object used to control main methods
    public Game game;
    // Label array used for collision detection management
    private Label[] collisions;
    // Timer used to spawn customers or make them leave
    public Timer controllerTimer = new Timer();

    public AudioClip backgroundMusic = ResourceProvider.createAudioFile(MusicFiles.BACKGROUNDMUSIC);


    // Method used to load a certain scene according to the name of the fxml file
    public void loadScene(String sceneName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource(sceneName));
        Scene scene = new Scene(fxmlLoader.load());
        ApplicationMain.stage.setScene(scene);
        ApplicationMain.stage.show();
    }

    // jump to end screen
    public void switchToEndScreen() throws IOException {
        backgroundMusic.stop();
        loadScene("endScreen.fxml");
    }

    // jump to start screen
    public void switchToStartScreen() throws IOException { // if button BACK TO START MENU is pressed
        loadScene("startScreen.fxml");
    }

    // jump from start screen to game screen
    public void switchToGameScreen() throws IOException { // if button PLAY AGAIN is pressed
        loadScene("gameScreen.fxml");
        if (Customer.customerImages[0] != null) {
            Customer customer = new Customer();
            customer.startTimerSpawn(durationForStartTimer1, Customer.getControllerTimer());
            customer.startTimerSpawn(durationForStartTimer5, Customer.getControllerTimer());
            customer.startTimerSpawn(durationForStartTimer10, Customer.getControllerTimer());
            Customer.allCustomers.add(customer);
        }

        backgroundMusic.setCycleCount(AudioClip.INDEFINITE);
        backgroundMusic.play();
    }

    // jump to instructions
    public void switchToInstructions() throws IOException {
        loadScene("Instructions.fxml");
    }

    // key events if wasd-keys are pressed
    @FXML
    public void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case W -> wPressed.set(true);
            case A -> aPressed.set(true);
            case S -> sPressed.set(true);
            case D -> dPressed.set(true);
        }
    }

    // key events if wasd-keys are released
    @FXML
    public void keyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case W -> wPressed.set(false);
            case A -> aPressed.set(false);
            case S -> sPressed.set(false);
            case D -> dPressed.set(false);
        }
    }

    // for smoother motion
    AnimationTimer animationTimer = new AnimationTimer() {

        @Override
        public void handle(long timestamp) {
            double moveDistance = determineMoveDistance();
            MovementVector movementVector = determineNewMovementVector(moveDistance);
            determinePlayerMovementDirection(movementVector);
            updatePlayerPosition(movementVector);

            if (!checkForCollision(waiterImageView)) {
                updatePlayerImage(player.getMovementDirection());
            }
        }
    };



    private void updatePlayerPosition(MovementVector movementVector) {
        // set x and y coordinates of waiter
        waiterImageView.setLayoutX(waiterImageView.getLayoutX() + movementVector.getX());
        waiterImageView.setLayoutY(waiterImageView.getLayoutY() + movementVector.getY());

        // if collision is detected, set x and y coordinates back to where no collision occurred
        if (checkForCollision(waiterImageView)) {
            waiterImageView.setLayoutX(waiterImageView.getLayoutX() - movementVector.getX());
            waiterImageView.setLayoutY(waiterImageView.getLayoutY() - movementVector.getY());
        }
    }

    private MovementVector determineNewMovementVector(double moveDistance) {
        MovementVector movementVector = new MovementVector();
        if (wPressed.get()) {
            movementVector.setY(-moveDistance);
        }
        if (sPressed.get()) {
            movementVector.setY(moveDistance);
        }
        if (aPressed.get()) {
            movementVector.setX(-moveDistance);
        }
        if (dPressed.get()) {
            movementVector.setX(moveDistance);
        }
        return movementVector;
    }

    private void determinePlayerMovementDirection(MovementVector movementVector){
        if(movementVector.getX()<0){
            player.setMovementDirection(MovementDirection.LEFT);
        }
        if(movementVector.getX()>0){
            player.setMovementDirection(MovementDirection.RIGHT);
        }
        if(movementVector.getY()<0){
            player.setMovementDirection(MovementDirection.UP);
        }
        if(movementVector.getY()>0){
            player.setMovementDirection(MovementDirection.DOWN);
        }
    }

    private double determineMoveDistance() {
        int playerMovementSpeed = player.getMovementSpeed();

        double moveDistance = playerMovementSpeed;
        if (isDiagonalMovementTriggered()) {
            moveDistance -= playerMovementSpeed - Math.sqrt(Math.pow(playerMovementSpeed, 2) / 2);
        }
        return moveDistance;
    }

    private boolean isDiagonalMovementTriggered() {
        return wPressed.get() && aPressed.get() || wPressed.get() && dPressed.get() ||
                sPressed.get() && aPressed.get() || sPressed.get() && dPressed.get();
    }

    private void updatePlayerImage(MovementDirection movementDirection) {
        try {
            if (player.getProductInHand().equals(ProductType.NONE)) {
                waiterImageView.setImage(ResourceProvider.createImage(movementDirection.getCofiBrewImage()));
            } else if (player.getProductInHand().equals(ProductType.CAKE)) {
                waiterImageView.setImage(ResourceProvider.createImage(movementDirection.getCofiBrewCakeImage()));
            } else if (player.getProductInHand().equals(ProductType.COFFEE)) {
                waiterImageView.setImage(ResourceProvider.createImage(movementDirection.getCofiBrewCoffeeImage()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupKeyPressedListener();
        initializeCollisions();
        initializeCustomerComponents();
        initializeGame();
    }

    private void setupKeyPressedListener() {
        keyPressed.addListener((observableValue, aBoolean, t1) -> {
            if (!aBoolean) {
                animationTimer.start();
            } else {
                animationTimer.stop();
            }
        });
    }

    private void initializeCollisions() {
        collisions = new Label[]{
                plant, plantsAbove, customerBot1, customerBot2, customerBot3,
                customerTop1, customerTop2, customerTop3, customerTop4,
                table1, table2, table3, table4, edgeBot, edgeLeft,
                edgeRight, edgeTop, countRight, countBelow
        };
    }

    private void initializeCustomerComponents() {
        Customer.customerImages = new ImageView[]{
                first, second, third, fourth, fifth, sixth, seventh
        };
        Customer.smileyImages = new ImageView[]{
                smileyFirst, smileySecond, smileyThird, smileyFourth,
                smileyFifth, smileySixth, smileySeventh
        };
        Customer.orderLabels = new ImageView[]{
                orderlabel1, orderlabel2, orderlabel3, orderlabel4,
                orderlabel5, orderlabel6, orderlabel7
        };
        Customer.coinImages = new ImageView[]{
                coinFirst, coinSecond, coinThird, coinFourth,
                coinFifth, coinSixth, coinSeventh
        };
        Customer.freeChairs = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        Customer.setControllerTimer(controllerTimer);
    }

    private void initializeGame() {
        game = new Game(upgradeCoffeeImageView, upgradeCakeImageView, upgradePlayerImageView);
    }


    // start screen - change start button on mouse entered
    public void changeStartCoffeeImage() throws FileNotFoundException {
        startButton.setImage(ResourceProvider.createImage(ImageFiles.START_COFFEE_HOT));
    }

    // start screen - change coffee button on mouse exited
    public void changeStartCoffeeImageBack() throws FileNotFoundException {
        startButton.setImage(ResourceProvider.createImage(ImageFiles.START_COFFEE));
    }

    // start screen - change Quit Button on mouse entered
    public void changeQuitStartScreen() throws FileNotFoundException {
        startQuitButton.setImage(ResourceProvider.createImage(ImageFiles.QUITE_END_SCREEN_BRIGHTER));
    }

    // start screen - change Quit Button when mouse exited
    public void changeQuitStartScreenBack() throws FileNotFoundException {
        startQuitButton.setImage(ResourceProvider.createImage(ImageFiles.QUIT_END_SCREEN));
    }

    // instructions - change GOT IT! on mouse entered
    public void changeStartImage() throws FileNotFoundException {
        gameStartButton.setImage(ResourceProvider.createImage(ImageFiles.INSTRUCTIONS_GOT_IT));
    }

    // instructions - change GOT IT! on mouse exited
    public void changeStartImageBack() throws FileNotFoundException {
        gameStartButton.setImage(ResourceProvider.createImage(ImageFiles.INSTRUCTIONS_GOT_IT_BRIGHTER));
    }

    // end screen - change PlayAgain Button when mouse entered
    public void changePlayAgain() throws FileNotFoundException {
        playAgainImage.setImage(ResourceProvider.createImage(ImageFiles.PLAY_AGAIN_BRIGHTER));
    }

    // end screen - change PlayAgain Button when mouse exited
    public void changePlayAgainBack() throws FileNotFoundException {
        playAgainImage.setImage(ResourceProvider.createImage(ImageFiles.PLAY_AGAIN));
    }

    // end screen - change BackToStartMenu Button when mouse entered
    public void changeBackToStartMenu() throws FileNotFoundException {
        backToStartImage.setImage(ResourceProvider.createImage(ImageFiles.BACK_TO_START_MENU_BRIGHTER));
    }

    // end screen - change BackToStartMenu Button when mouse exited
    public void changeBackToStartMenuBack() throws FileNotFoundException {
        backToStartImage.setImage(ResourceProvider.createImage(ImageFiles.BACK_TO_START_MENU));
    }

    // end screen - change Quit Button when mouse entered
    public void changeQuitEndScreen() throws FileNotFoundException {
        quitEndScreenImage.setImage(ResourceProvider.createImage(ImageFiles.QUITE_END_SCREEN_BRIGHTER));
    }

    // end screen - change Quit Button when mouse exited
    public void changeQuitEndScreenBack() throws FileNotFoundException {
        quitEndScreenImage.setImage(ResourceProvider.createImage(ImageFiles.QUIT_END_SCREEN));
    }

    // if waiter is near coffee machine, change appearance when clicked
    public void showCoffee() throws FileNotFoundException {
        if (waiterImageView.getBoundsInParent().intersects(coffeeMachineImageView.getBoundsInParent())) {
            game.getCoffeeMachine().displayProduct(waiterImageView, coffeeMachineImageView, player, progressBarCoffee);
            SoundPlayer.playSound(MusicFiles.TEST_SOUND);
        }
    }

    // if waiter is near cake machine, change appearance when clicked
    public void showCake() throws FileNotFoundException {
        if (waiterImageView.getBoundsInParent().intersects(cakeMachineImageView.getBoundsInParent())) {
            game.getCakeMachine().displayProduct(waiterImageView, cakeMachineImageView, player, progressBarCake);
            SoundPlayer.playSound(MusicFiles.TEST_SOUND);
        }
    }

    // if no product is held by waiter
    public void noProduct() throws FileNotFoundException {
        if (player.getProductInHand().equals(ProductType.COFFEE) || player.getProductInHand().equals(ProductType.CAKE)) {
            SoundPlayer.playSound(MusicFiles.TRASH_SOUND);
            waiterImageView.setImage(ResourceProvider.createImage(player.getFilenameImageWithoutProduct()));
            player.setProductInHand(ProductType.NONE);
        }
    }

    // find the customer in the customerList and return it
    public Customer findCustomer(List<Customer> customerList, ImageView customerImageView) {
        for (Customer customer : customerList) {
            if (customer.getImage().equals(customerImageView)) {
                return customer;
            }
        }
        return null; // if not found return null
    }

    // Method used to display a customer, check if and order was right and set Images for coin ImagesViews
    public void handlePersonClick(MouseEvent event) throws FileNotFoundException {
        ImageView customerImageView = (ImageView) event.getSource();
        Customer customer = findCustomer(Customer.customersInCoffeeShop, customerImageView);

        if (!customer.isAlreadyOrdered()) {
            customer.displayOrder(customer.getLabel());
        }

        if (!isWaiterNearCustomer(customerImageView)) {
            return;
        }

        spawnNewCustomerIfPossible(customer);

        if (!customer.checkOrder(player, customer, waiterImageView)) {
            return;
        }

        setCoinImage(customer);
        setupCoinClickEvent(customer);
    }

    private boolean isWaiterNearCustomer(ImageView customerImageView) {
        return customerImageView.getBoundsInParent().intersects(waiterImageView.getBoundsInParent());
    }

    private void spawnNewCustomerIfPossible(Customer customer) {
        try {
            customer.startTimerSpawn(durationForStartTimer5, Customer.getControllerTimer());
        } catch (NullPointerException e) {
            safelySwitchToEndScreen();
        }
    }

    private void setCoinImage(Customer customer) throws FileNotFoundException {
        String moneyImage = determineMoneyImage(customer);
        customer.getCoinImage().setImage(ResourceProvider.createImage(moneyImage));
    }

    private String determineMoneyImage(Customer customer) {
        if (customer.isGreen()) {
            return ImageFiles.FIVE_COINS;
        } else if (customer.isYellow()) {
            return ImageFiles.FOUR_COINS;
        } else if (customer.isRed()) {
            return ImageFiles.THREE_COINS;
        }
        return "";
    }

    private void setupCoinClickEvent(Customer customer) {
        customer.getCoinImage().setOnMouseClicked(event -> {
            try {
                getMoney(event, customer);
            } catch (IOException e) {
                safelySwitchToEndScreen();
            }
        });
    }

    private void safelySwitchToEndScreen() {
        try {
            switchToEndScreen();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Method to check if an Upgrade can be made (check if player has earned enough coins and if it was already used or not)
    public void checkUpgradePossible(Upgrade upgrade) throws FileNotFoundException {
        game.checkUpgradePossible(upgrade);
    }

    // Method to actively use an Upgrade
    public void doUpgrade(MouseEvent e) throws FileNotFoundException {
        // activate the Upgrade (according to whatever ImageView was chosen)
        game.doUpgrade(((ImageView) e.getSource()).getId(), player);
        // set the coin label to the correct amount of coins (coins earned - upgrade costs)
        coinsEarnedLabel.setText(String.valueOf(game.getCoinsEarned()));

        SoundPlayer.playSound(MusicFiles.UPGRADE_SOUND);

        // check if other upgrades are still possible or if they need to be "deactivated"
        checkUpgradePossible(game.getCoffeeUpgrade());
        checkUpgradePossible(game.getCakeUpgrade());
        checkUpgradePossible(game.getPlayerUpgrade());
    }

    // check if collisions occur
    public boolean checkForCollision(ImageView waiter) {
        for (Label collision : collisions) { // iterate through labels
            if (waiter.getBoundsInParent().intersects(collision.getBoundsInParent())) { // if waiter would collide with a label
                return true; // return true
            }
        }
        return false;
    }

    // Method used when coin Image is clicked on
    public void getMoney(MouseEvent e, Customer customer) throws FileNotFoundException {
        SoundPlayer.playSound(MusicFiles.COINS_SOUND);
        Customer.addFreeSeat(customer.getChair());
        updateGameCoinsEarned(customer);
        hideCoinImage(e);

        if (isGameCompletionCriteriaMet()) {
            completeGame();
        } else {
            updateGameUI();
            spawnNewCustomer(customer);
        }
    }

    private void updateGameCoinsEarned(Customer customer) {
        game.setCoinsEarned(customer);
    }

    private void hideCoinImage(MouseEvent e) {
        ImageView coinImage = (ImageView) e.getSource();
        coinImage.setVisible(false);
        coinImage.setDisable(true);
    }

    private boolean isGameCompletionCriteriaMet() {
        return game.getCoinsEarned() >= 80;
    }

    private void updateGameUI() throws FileNotFoundException {
        checkUpgradePossible(game.getCoffeeUpgrade());
        checkUpgradePossible(game.getCakeUpgrade());
        checkUpgradePossible(game.getPlayerUpgrade());
        coinsEarnedLabel.setText(String.valueOf(game.getCoinsEarned()));
    }

    private void spawnNewCustomer(Customer customer) {
        try {
            customer.startTimerSpawn(durationForStartTimer5, Customer.getControllerTimer());
        } catch (NullPointerException y) {
            safelySwitchToEndScreen();
        }
    }

    private void completeGame() {
        stopTimers();
        safelySwitchToEndScreen();
    }


    // Method used to stop all the timers activated by spawning customers
    public void stopTimers() {
        Customer.allCustomers.stream()
                .map(Customer::getSixtySecondsTimer)
                .filter(Objects::nonNull)
                .forEach(Timer::cancel);

        Customer.allCustomers.clear(); // clear all Lists created by spawning/despawning customer
        Customer.customersInCoffeeShop.clear();
        Customer.freeChairs.clear();
        player.setProductInHand(ProductType.NONE); // clear Cofis hand
        controllerTimer.cancel(); // cancel spawn/leaving timer
        Customer.getControllerTimer().cancel();
    }

    // end game (called when exit clicked) - in Game Screen
    public void endGameQuick() {
        stopTimers();
        backgroundMusic.stop();
        Platform.exit();
        System.exit(0);
    }

    // end game (called when quit button is clicked) - in End Screen and Start Screen
    public void endGame() {
        Platform.exit();
        backgroundMusic.stop();
        System.exit(0);
    }
}
