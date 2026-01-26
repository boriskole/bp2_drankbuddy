module nl.adacademie.drankbuddy {
    requires javafx.controls;
    requires javafx.fxml;


    opens nl.adacademie.drankbuddy to javafx.fxml;
    exports nl.adacademie.drankbuddy;
}