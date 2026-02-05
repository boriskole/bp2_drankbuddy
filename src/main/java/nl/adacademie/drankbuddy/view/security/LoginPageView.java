package nl.adacademie.drankbuddy.view.security;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import nl.adacademie.drankbuddy.DrankBuddy;
import nl.adacademie.drankbuddy.controller.LoginController;
import nl.adacademie.drankbuddy.dto.LoginRequestDto;
import nl.adacademie.drankbuddy.repository.dao.AccountDaoImpl;
import nl.adacademie.drankbuddy.view.dashboard.ProductOverviewView;
import nl.adacademie.drankbuddy.view.status.LoginPageStatus;
import nl.adacademie.drankbuddy.view.status.RegisterPageStatus;

public class LoginPageView extends BorderPane {

    public LoginPageView(LoginPageStatus loginPageStatus) {
        // Stylesheets en fonts inladen.
        getStylesheets().add("https://fonts.googleapis.com/css2?family=Geist:wght@100..900&display=swap");
        getStylesheets().add(getClass().getResource("/css/login-page.css").toExternalForm());

        VBox root = new VBox(30); // Root node maken
        root.getStyleClass().add("login-pane");

        // Losse components toevoegen.
        root.getChildren().add(createHeading());

        if (loginPageStatus == LoginPageStatus.REGISTER_SUCCESS) {
            root.getChildren().add(createSuccessMessage());
        } else if (loginPageStatus != LoginPageStatus.NONE) { // Alleen laten zien als er iets anders dan NONE is weggegeven.
            root.getChildren().add(createError(loginPageStatus));
        }

        root.getChildren().add(createLoginForm());
        root.getChildren().add(createRegisterFormText());

        root.setPadding(new Insets(25));
        root.setMaxSize(400, 0); // 0 height, zodat hij automatisch de hoogte bepaald.

        // De root node toevoegen aan de pane.
        setCenter(root);
    }

    private HBox createSuccessMessage() {
        HBox successBox = new HBox(10);
        successBox.getStyleClass().add("success-box"); // Gebruik een success-class voor groene styling
        successBox.setAlignment(Pos.CENTER_LEFT);
        successBox.setPadding(new Insets(10));

        // Icon maken
        ImageView successIcon = new ImageView(getClass().getResource("/media/circle_check.png").toExternalForm());
        successIcon.setFitWidth(25);
        successIcon.setPreserveRatio(true);
        successIcon.setSmooth(true);

        // Label maken
        Label successLabel = new Label("Registratie geslaagd! Je kunt nu inloggen.");
        successLabel.getStyleClass().add("success-text");

        // Zorg dat de tekst netjes wrapt
        successLabel.setWrapText(true);
        successLabel.setMinWidth(0);
        HBox.setHgrow(successLabel, Priority.ALWAYS);

        successBox.getChildren().addAll(successIcon, successLabel);
        return successBox;
    }

    private VBox createHeading() {
        VBox root = new VBox(20); // Root node maken.

        ImageView logo = new ImageView(getClass().getResource("/media/logo.png").toExternalForm()); // Logo inladen.
        logo.setFitWidth(55);
        logo.setPreserveRatio(true); // Zorgen dat horizontaal en verticaal overeenkomt met de grootte van de pane.

        StackPane logoPane = new StackPane(logo); // Een pane maken om het logo in te sluiten.
        logoPane.setPadding(new Insets(10));
        logoPane.getStyleClass().add("logo-pane");

        // Labels maken.
        Label title = new Label("Welkom bij DrankBuddy");
        title.getStyleClass().add("heading-title");
        Label subtitle = new Label("Login om door de gaan naar het voorraadsysteem");
        subtitle.getStyleClass().add("heading-subtitle");

        VBox titles = new VBox(10, title, subtitle);

        // Allebei centreren.
        titles.setAlignment(Pos.CENTER);
        root.setAlignment(Pos.CENTER);

        // Alle onderdelen toevoegen aan de root node.
        root.getChildren().addAll(
            logoPane,
            titles
        );

        return root;
    }

    private HBox createError(LoginPageStatus loginPageStatus) {
        HBox errorBox = new HBox(10); // Root node maken.
        errorBox.getStyleClass().add("error-box"); // CSS-class toevoegen.
        errorBox.setAlignment(Pos.CENTER_LEFT); // Links-midden positioneren.
        errorBox.setPadding(new Insets(10)); // Padding toevoegen aan alle kanten.

        // Icon maken.
        ImageView errorIcon = new ImageView(getClass().getResource("/media/circle_alert.png").toExternalForm());
        errorIcon.setFitWidth(25);
        errorIcon.setPreserveRatio(true);
        errorIcon.setSmooth(true);

        // De error message bepalen op basis van de error enum.
        String message = switch (loginPageStatus) {
            case EMPTY_FIELDS -> "Vul alstublieft alle velden in.";
            case INVALID_CREDENTIALS -> "Gebruikersnaam of wachtwoord is onjuist.";
            default -> "Er is iets misgegaan.";
        };

        // Label maken.
        Label errorLabel = new Label(message);
        errorLabel.getStyleClass().add("error-text");

        // Alle onderdelen toevoegen aan de root node.
        errorBox.getChildren().addAll(errorIcon, errorLabel);
        return errorBox;
    }

    private VBox createLoginForm() {
        // Root node maken.
        VBox root = new VBox(20);
        root.getStyleClass().add("login-form");

        VBox usernameBox = new VBox(5); // Box maken voor de gebruikersnaam.
        Label usernameLabel = new Label("Gebruikersnaam");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Voer gebruikersnaam in..."); // De prompt tekst instellen
        usernameBox.getChildren().addAll(usernameLabel, usernameField); // Alle gebruikersnaam onderdelen toevoegen.

        VBox passwordBox = new VBox(5); // Box maken voor het wachtwoord.
        Label passwordLabel = new Label("Wachtwoord");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Voer wachtwoord in..."); // Prompt tekst instellen.
        passwordBox.getChildren().addAll(passwordLabel, passwordField); // Alle wachtwoord onderdelen toevoegen.

        Button loginButton = new Button("Inloggen"); // Submit knop maken.
        loginButton.setMaxWidth(Double.MAX_VALUE); // De breedte van de knop vergroten.

        loginButton.setOnMouseClicked(_ -> {
            LoginController loginController = new LoginController(new AccountDaoImpl());
            LoginPageStatus loginPageStatus = loginController.login(new LoginRequestDto(
                usernameField.getText(),
                passwordField.getText()
            ));

            switch (loginPageStatus) {
                case EMPTY_FIELDS -> DrankBuddy.changeView(new LoginPageView(LoginPageStatus.EMPTY_FIELDS));
                case INVALID_CREDENTIALS -> DrankBuddy.changeView(new LoginPageView(LoginPageStatus.INVALID_CREDENTIALS));
                case LOGIN_SUCCESS -> DrankBuddy.changeView(new ProductOverviewView());
            }
        });

        // De onderdelen toevoegen aan de root node.
        root.getChildren().addAll(
            usernameBox,
            passwordBox,
            loginButton
        );

        return root;
    }

    private HBox createRegisterFormText() {
        HBox root = new HBox(4);
        root.setAlignment(Pos.CENTER);

        Label firstPart = new Label("Nog geen account?");
        firstPart.getStyleClass().add("register-first-part");

        Label secondPart = new Label("Registeer hier");
        secondPart.getStyleClass().add("register-second-part");

        // De view veranderen als de gebruiker op de tweede label klikt.
        secondPart.setOnMouseClicked(_ -> DrankBuddy.changeView(
            new RegisterPageView(RegisterPageStatus.NONE)
        ));

        root.getChildren().addAll(firstPart, secondPart);
        return root;
    }

}
