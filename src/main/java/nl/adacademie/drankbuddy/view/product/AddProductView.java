package nl.adacademie.drankbuddy.view.product;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nl.adacademie.drankbuddy.DrankBuddy;
import nl.adacademie.drankbuddy.controller.product.AddProductController;
import nl.adacademie.drankbuddy.dto.AddProductRequestDto;
import nl.adacademie.drankbuddy.entity.Category;
import nl.adacademie.drankbuddy.repository.dao.CategoryDaoImpl;
import nl.adacademie.drankbuddy.repository.dao.ProductDaoImpl;
import nl.adacademie.drankbuddy.repository.interfaces.CategoryDaoInterface;
import nl.adacademie.drankbuddy.view.component.SidebarComponent;
import nl.adacademie.drankbuddy.view.type.AddProductPageStatus;
import nl.adacademie.drankbuddy.view.type.MenuPage;
import nl.adacademie.drankbuddy.view.type.ProductOverviewPageStatus;

import java.util.List;

public class AddProductView extends BorderPane {

    private final AddProductPageStatus addProductPageStatus;

    public AddProductView(AddProductPageStatus addProductPageStatus) {
        this.addProductPageStatus = addProductPageStatus;

        getStylesheets().add(getClass().getResource("/css/overview.css").toExternalForm()); // CSS toevoegen.
        getStylesheets().add(getClass().getResource("/css/form.css").toExternalForm()); // CSS toevoegen.

        setLeft(new SidebarComponent(MenuPage.PRODUCTS)); // Sidebar toevoegen.

        VBox root = new VBox(30);
        root.setPadding(new Insets(20, 200, 20, 200)); // Ruimte geven aan onderdelen

        // Losse components toevoegen.
        root.getChildren().addAll(
            createBackButton(),
            createHeading(),
            createBox()
        );

        setCenter(root);
    }

    private HBox createBackButton() {
        HBox root = new HBox(10); // Root node maken.
        root.getStyleClass().add("back-button");
        root.setAlignment(Pos.CENTER_LEFT); // Links-midden positioneren.

        // Icoon maken.
        ImageView backIcon = new ImageView(getClass().getResource("/media/back_button_icon.png").toExternalForm()); // Icoon inladen.
        backIcon.setFitWidth(20); // Grootte instellen.
        backIcon.setPreserveRatio(true); // Zorgen dat de hoogte mee schaalt met de fit width.
        root.getChildren().add(backIcon);

        // Label maken.
        root.getChildren().add(new Label("Terug naar producten"));

        root.setOnMouseClicked(_ -> DrankBuddy.changeView(new ProductOverviewView(ProductOverviewPageStatus.NONE))); // Wanneer er op de knop wordt geklikt de product overview view inladen.

        return root;
    }

    private VBox createHeading() {
        VBox root = new VBox(5); // Root node maken.

        // Producten titel
        Label title = new Label("Product toevoegen");
        title.getStyleClass().add("heading-title");

        // Producten subtitel
        Label subtitle = new Label("Voer de gegevens in voor het nieuwe product");
        subtitle.getStyleClass().add("heading-subtitle");

        root.getChildren().addAll(title, subtitle); // Alle onderdelen toevoegen aan de root node.

        return root;
    }

    private VBox createBox() {
        VBox root = new VBox(15); // Root node maken.
        root.getStyleClass().add("box");

        // Alle onderdelen toevoegen aan de root node.
        root.getChildren().add(createBoxHeadingSection());

        // Alleen als de status empty fields is of too long name.
        if (addProductPageStatus == AddProductPageStatus.EMPTY_FIELDS || addProductPageStatus == AddProductPageStatus.TOO_LONG_NAME) {
            root.getChildren().add(createError(addProductPageStatus)); // Error melding maken.
        }

        root.getChildren().add(createForm()); // Formulier toevoegen.

        return root;
    }

    private VBox createBoxHeadingSection() {
        VBox root = new VBox(); // Root node maken.

        // Eerste titel maken.
        Label headingTitle = new Label("Nieuw product");
        headingTitle.getStyleClass().add("box-heading-title");

        // Tweede titel maken.
        Label headingSubtitle = new Label("Vul onderstaand formulier in om een product aan te maken");
        headingSubtitle.getStyleClass().add("box-heading-subtitle");

        // Titels toevoegen aan de root node.
        root.getChildren().addAll(
            headingTitle,
            headingSubtitle
        );

        return root;
    }

    private VBox createForm() {
        VBox root = new VBox(30); // Root node maken.

        VBox categoryField = new VBox(5); // Box maken voor de categorie.
        categoryField.getStyleClass().add("form-field");

        Label categoryLabel = new Label("Categorie *"); // Label maken.
        categoryField.getChildren().add(categoryLabel);

        CategoryDaoInterface categoryDao = new CategoryDaoImpl(); // Dao instantie maken.
        List<Category> categories = categoryDao.findAllByAccount(DrankBuddy.loggedInAccount); // Alle categorieen ophalen.
        ComboBox<Category> categoryComboBox = new ComboBox<>(FXCollections.observableArrayList(categories)); // De categorieen in een combobox zetten.
        categoryComboBox.setPromptText("Selecteer een categorie...");

        categoryField.getChildren().add(categoryComboBox);

        VBox nameField = new VBox(5); // Box maken voor de naam.
        nameField.getStyleClass().add("form-field");

        Label nameLabel = new Label("Productnaam *"); // Label maken.
        nameField.getChildren().add(nameLabel);

        TextField nameFieldInput = new TextField(); // Invulveld maken.
        nameFieldInput.setPromptText("Bijv. Jupiler Pils, Grey Goose Vodka..."); // Placeholder tekst instellen.
        nameField.getChildren().add(nameFieldInput);

        root.getChildren().addAll(categoryField, nameField);

        Button submitButton = new Button("Toevoegen"); // Submit knop maken.
        submitButton.getStyleClass().add("submit-button");

        submitButton.setOnMouseClicked(_ -> {
            // Dto maken met de form data.
            AddProductRequestDto dto = new AddProductRequestDto(
                categoryComboBox.getValue(),
                nameFieldInput.getText()
            );

            // Instantie maken van de controller met daarin de echte database dao versie.
            AddProductController addProductController = new AddProductController(new ProductDaoImpl());

            AddProductPageStatus result = addProductController.addProduct(dto); // Controller uitvoeren.

            // Resultaat verwerken.
            switch (result) {
                case EMPTY_FIELDS -> DrankBuddy.changeView(new AddProductView(AddProductPageStatus.EMPTY_FIELDS));
                case TOO_LONG_NAME -> DrankBuddy.changeView(new AddProductView(AddProductPageStatus.TOO_LONG_NAME));
                case SUCCESS -> DrankBuddy.changeView(new ProductOverviewView(ProductOverviewPageStatus.ADD_SUCCESS));
            }
        });

        root.getChildren().add(submitButton);

        return root;
    }

    private HBox createError(AddProductPageStatus addProductPageStatus) {
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
        String message = switch (addProductPageStatus) {
            case EMPTY_FIELDS -> "Vul alstublieft alle velden in.";
            case TOO_LONG_NAME -> "De naam is te lang. Maximaal 50 karakters zijn toegestaan.";
            default -> "Er is iets misgegaan.";
        };

        // Label maken.
        Label errorLabel = new Label(message);
        errorLabel.getStyleClass().add("error-text");

        // Alle onderdelen toevoegen aan de root node.
        errorBox.getChildren().addAll(errorIcon, errorLabel);
        return errorBox;
    }

}
