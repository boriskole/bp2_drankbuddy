package nl.adacademie.drankbuddy.view.dashboard;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nl.adacademie.drankbuddy.DrankBuddy;
import nl.adacademie.drankbuddy.view.security.LoginPageView;
import nl.adacademie.drankbuddy.view.status.LoginPageStatus;

public class ProductOverviewView extends Pane {

    public ProductOverviewView() {
        // Stylesheets en fonts inladen.
        getStylesheets().add("https://fonts.googleapis.com/css2?family=Geist:wght@100..900&display=swap");
        getStylesheets().add(getClass().getResource("/css/products-overview-page.css").toExternalForm());

        createSidebar(this);
    }

    private void createSidebar(Pane pane) {
        VBox sidebar = new VBox(); // Root node maken.
        sidebar.setPrefSize(300, DrankBuddy.APPLICATION_WINDOW_SIZE[1]); // De grootte instellen.
        sidebar.getStyleClass().add("sidebar");

        HBox headerSection = new HBox();
        headerSection.getStyleClass().add("header-section");

        VBox logoutSection = new VBox(10); // Een wrapper maken voor de login knop
        logoutSection.setPrefSize(sidebar.getPrefWidth(), 75); // De grootte instellen.
        logoutSection.getStyleClass().add("logout-section");
        logoutSection.setLayoutY(sidebar.getPrefHeight() - 75); // De sectie aan de onderkant van het scherm instellen.
        logoutSection.setAlignment(Pos.CENTER); // De content van de sectie centreren.
        logoutSection.setPadding(new Insets(0, 20, 0, 20)); // Padding instellen.

        ImageView logoutIcon = new ImageView(getClass().getResource("/media/log-out.png").toExternalForm()); // Een nieuwe image view maken voor het uitloggen icoon.
        logoutIcon.setFitWidth(25); // De gewenste grootte instellen.
        logoutIcon.setPreserveRatio(true); // De aspect ratio behouden.
        Label logoutLabel = new Label("Uitloggen"); // Label maken.

        HBox logoutButton = new HBox(10, logoutIcon, logoutLabel); // De icon en de label samenvoegen in een box.
        logoutButton.getStyleClass().add("logout-button");
        logoutButton.setAlignment(Pos.CENTER_LEFT); // De knop midden-links instellen.

        logoutButton.setOnMouseClicked(_ -> { // Wanneer er op de knop wordt geklikt.
            DrankBuddy.loggedInAccount = null; // De ingelogde account leegmaken.
            DrankBuddy.changeView(new LoginPageView(LoginPageStatus.NONE)); // De loginpagina weergeven.
        });

        logoutSection.getChildren().add(logoutButton); // De knop toevoegen aan de sectie.

        pane.getChildren().addAll(sidebar, logoutSection); // Alle onderdelen toevoegen aan de root pane.
    }

}
