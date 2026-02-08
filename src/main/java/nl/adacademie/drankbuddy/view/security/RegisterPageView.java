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
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import nl.adacademie.drankbuddy.DrankBuddy;
import nl.adacademie.drankbuddy.controller.RegisterAccountController;
import nl.adacademie.drankbuddy.dto.RegisterRequestDto;
import nl.adacademie.drankbuddy.repository.dao.AccountDaoImpl;
import nl.adacademie.drankbuddy.view.status.LoginPageStatus;
import nl.adacademie.drankbuddy.view.status.RegisterPageStatus;

public class RegisterPageView extends BorderPane {

    public RegisterPageView(RegisterPageStatus registerPageStatus) {
        // Stylesheets en fonts inladen.
        getStylesheets().add("https://fonts.googleapis.com/css2?family=Geist:wght@100..900&display=swap");
        getStylesheets().add(getClass().getResource("/css/register-page.css").toExternalForm());

        VBox root = new VBox(30); // Root node maken
        root.getStyleClass().add("register-pane");

        // Losse components toevoegen.
        root.getChildren().add(createHeading());

        if (registerPageStatus != RegisterPageStatus.NONE) { // Alleen laten zien als er iets anders dan NONE is weggegeven.
            root.getChildren().add(createErrorMessage(registerPageStatus));
        }

        root.getChildren().add(createRegisterForm());
        root.getChildren().add(createRegisterFormText());

        root.setPadding(new Insets(25));
        root.setMaxSize(400, 0); // 0 height, zodat hij automatisch de hoogte bepaald.

        // De root node toevoegen aan de pane.
        setCenter(root);
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
        Label title = new Label("Account aanmaken");
        title.getStyleClass().add("heading-title");
        Label subtitle = new Label("Maak een nieuw account aan voor DrankBuddy");
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

    private HBox createErrorMessage(RegisterPageStatus registerPageStatus) {
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
        String message = switch (registerPageStatus) {
            case EMPTY_FIELDS -> "Vul alstublieft alle velden in.";
            case USERNAME_EXISTS -> "Gebruikersnaam bestaat al. Probeer een andere.";
            case PASSWORD_MISMATCH -> "Wachtwoorden komen niet overeen.";
            default -> "Er is iets misgegaan.";
        };

        // Label maken.
        Label errorLabel = new Label(message);
        errorLabel.setWrapText(true);
        errorLabel.setMinHeight(Region.USE_PREF_SIZE);
        errorLabel.getStyleClass().add("error-text");

        // Alle onderdelen toevoegen aan de root node.
        errorBox.getChildren().addAll(errorIcon, errorLabel);
        return errorBox;
    }

    private VBox createRegisterForm() {
        // Root node maken.
        VBox root = new VBox(20);
        root.getStyleClass().add("register-form");

        VBox nameBox = new VBox(5); // Box maken voor de naam.
        Label nameLabel = new Label("Naam");
        TextField nameField = new TextField();
        nameField.setPromptText("Kies een naam..."); // De prompt tekst instellen
        nameBox.getChildren().addAll(nameLabel, nameField); // Alle gebruikersnaam onderdelen toevoegen.

        VBox usernameBox = new VBox(5); // Box maken voor de gebruikersnaam.
        Label usernameLabel = new Label("Gebruikersnaam");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Kies een gebruikersnaam..."); // De prompt tekst instellen
        usernameBox.getChildren().addAll(usernameLabel, usernameField); // Alle gebruikersnaam onderdelen toevoegen.

        VBox passwordBox = new VBox(5); // Box maken voor het wachtwoord.
        Label passwordLabel = new Label("Wachtwoord");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Kies een wachtwoord..."); // Prompt tekst instellen.
        passwordBox.getChildren().addAll(passwordLabel, passwordField); // Alle wachtwoord onderdelen toevoegen.

        VBox passwordConfirmBox = new VBox(5); // Box maken voor het wachtwoord.
        Label passwordConfirmLabel = new Label("Bevestig wachtwoord");
        PasswordField passwordConfirmField = new PasswordField();
        passwordConfirmField.setPromptText("Bevestig het wachtwoord..."); // Prompt tekst instellen.
        passwordConfirmBox.getChildren().addAll(passwordConfirmLabel, passwordConfirmField); // Alle wachtwoord onderdelen toevoegen.

        Button registerButton = new Button("Registreren"); // Submit knop maken.
        registerButton.setMaxWidth(Double.MAX_VALUE); // De breedte van de knop vergroten.
        registerButton.setOnMouseClicked(_ -> {
            RegisterAccountController registerAccountController = new RegisterAccountController(new AccountDaoImpl());
            RegisterPageStatus registerPageStatus = registerAccountController.registerAccount(new RegisterRequestDto(
                nameField.getText(),
                usernameField.getText(),
                passwordField.getText(),
                passwordConfirmField.getText()
            ));

            switch (registerPageStatus) {
                case EMPTY_FIELDS -> DrankBuddy.changeView(new RegisterPageView(RegisterPageStatus.EMPTY_FIELDS));
                case USERNAME_EXISTS -> DrankBuddy.changeView(new RegisterPageView(RegisterPageStatus.USERNAME_EXISTS));
                case PASSWORD_MISMATCH -> DrankBuddy.changeView(new RegisterPageView(RegisterPageStatus.PASSWORD_MISMATCH));
                case REGISTER_SUCCESS -> DrankBuddy.changeView(new LoginPageView(LoginPageStatus.REGISTER_SUCCESS));
            }
        });

        // De onderdelen toevoegen aan de root node.
        root.getChildren().addAll(
            nameBox,
            usernameBox,
            passwordBox,
            passwordConfirmBox,
            registerButton
        );

        return root;
    }

    private HBox createRegisterFormText() {
        HBox root = new HBox(4);
        root.setAlignment(Pos.CENTER);

        Label firstPart = new Label("Al een account?");
        firstPart.getStyleClass().add("register-first-part");

        Label secondPart = new Label("Log hier in");
        secondPart.getStyleClass().add("register-second-part");

        // De view veranderen als de gebruiker op de tweede label klikt.
        secondPart.setOnMouseClicked(_ -> DrankBuddy.changeView(
            new LoginPageView(LoginPageStatus.NONE)
        ));

        root.getChildren().addAll(firstPart, secondPart);
        return root;
    }

}
