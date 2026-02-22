package nl.adacademie.drankbuddy.view.category;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nl.adacademie.drankbuddy.DrankBuddy;
import nl.adacademie.drankbuddy.controller.category.EditCategoryController;
import nl.adacademie.drankbuddy.dto.EditCategoryRequestDto;
import nl.adacademie.drankbuddy.entity.Category;
import nl.adacademie.drankbuddy.repository.dao.CategoryDaoImpl;
import nl.adacademie.drankbuddy.view.component.SidebarComponent;
import nl.adacademie.drankbuddy.view.type.CategoryOverviewPageStatus;
import nl.adacademie.drankbuddy.view.type.EditCategoryPageStatus;
import nl.adacademie.drankbuddy.view.type.MenuPage;

public class EditCategoryView extends BorderPane {

    private final EditCategoryPageStatus addCategoryPageStatus;
    private final Category category;

    public EditCategoryView(EditCategoryPageStatus addCategoryPageStatus, Category category) {
        this.addCategoryPageStatus = addCategoryPageStatus;
        this.category = category;

        getStylesheets().add(getClass().getResource("/css/overview.css").toExternalForm()); // CSS toevoegen.
        getStylesheets().add(getClass().getResource("/css/form.css").toExternalForm()); // CSS toevoegen.

        setLeft(new SidebarComponent(MenuPage.CATEGORIES)); // Sidebar toevoegen.

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
        root.getChildren().add(new Label("Terug naar categorieën"));

        root.setOnMouseClicked(_ -> DrankBuddy.changeView(new CategoryOverviewView(CategoryOverviewPageStatus.NONE))); // Wanneer er op de knop wordt geklikt de categorie overview view inladen.

        return root;
    }

    private VBox createHeading() {
        VBox root = new VBox(5); // Root node maken.

        // Categorieen titel
        Label title = new Label("Categorie bewerken");
        title.getStyleClass().add("heading-title");

        // Categorieen subtitel
        Label subtitle = new Label("Wijzig de gegevens de categorie");
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
        if (addCategoryPageStatus == EditCategoryPageStatus.EMPTY_FIELDS || addCategoryPageStatus == EditCategoryPageStatus.TOO_LONG_NAME) {
            root.getChildren().add(createError(addCategoryPageStatus)); // Error melding maken.
        }

        root.getChildren().add(createForm()); // Formulier toevoegen.

        return root;
    }

    private VBox createBoxHeadingSection() {
        VBox root = new VBox(); // Root node maken.

        // Eerste titel maken.
        Label headingTitle = new Label("Gegevens wijzigen");
        headingTitle.getStyleClass().add("box-heading-title");

        // Tweede titel maken.
        Label headingSubtitle = new Label(String.format("U bewerkt categorie '%s'", category.getName()));
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

        VBox nameField = new VBox(5); // Box maken voor de naam.
        nameField.getStyleClass().add("form-field");

        Label nameLabel = new Label("Categorienaam *"); // Label maken.
        nameField.getChildren().add(nameLabel);

        TextField nameFieldInput = new TextField(); // Invulveld maken.
        nameFieldInput.setText(category.getName()); // Invulveld invullen met de naam van de categorie.
        nameField.getChildren().add(nameFieldInput);

        root.getChildren().add(nameField);

        Button submitButton = new Button("Opslaan"); // Submit knop maken.
        submitButton.getStyleClass().add("submit-button");

        submitButton.setOnMouseClicked(_ -> {
            // Dto maken met de form data.
            EditCategoryRequestDto dto = new EditCategoryRequestDto(
                category.getId(),
                nameFieldInput.getText()
            );

            // Instantie maken van de controller met daarin de echte database dao versie.
            EditCategoryController editCategoryController = new EditCategoryController(new CategoryDaoImpl());

            EditCategoryPageStatus result = editCategoryController.editCategory(dto); // Controller uitvoeren.

            // Resultaat verwerken.
            switch (result) {
                case EMPTY_FIELDS -> DrankBuddy.changeView(new EditCategoryView(EditCategoryPageStatus.EMPTY_FIELDS, category));
                case TOO_LONG_NAME -> DrankBuddy.changeView(new EditCategoryView(EditCategoryPageStatus.TOO_LONG_NAME, category));
                case SUCCESS -> DrankBuddy.changeView(new CategoryOverviewView(CategoryOverviewPageStatus.EDIT_SUCCESS));
            }
        });

        root.getChildren().add(submitButton);

        return root;
    }

    private HBox createError(EditCategoryPageStatus editCategoryPageStatus) {
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
        String message = switch (editCategoryPageStatus) {
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
