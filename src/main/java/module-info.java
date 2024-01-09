module com.decafe {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;


    opens com.decafe to javafx.fxml;
    exports com.decafe;
    exports com.decafe.game;
    opens com.decafe.game to javafx.fxml;
    exports com.decafe.game.entities;
    opens com.decafe.game.entities to javafx.fxml;
    exports com.decafe.resources;

}