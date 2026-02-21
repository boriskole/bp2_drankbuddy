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
import nl.adacademie.drankbuddy.view.component.SidebarComponent;
import nl.adacademie.drankbuddy.view.type.MenuPage;

public class AddCategoryView extends BorderPane {

    public AddCategoryView() {
        getStylesheets().add(getClass().getResource("/css/overview.css").toExternalForm()); // CSS toevoegen.
        getStylesheets().add(getClass().getResource("/css/add_form.css").toExternalForm()); // CSS toevoegen.
        setLeft(new SidebarComponent(MenuPage.CATEGORIES)); // Sidebar toevoegen.

        VBox root = new VBox(30);
        root.setPadding(new Insets(20, 200, 20, 200));

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
        root.setAlignment(Pos.CENTER_LEFT);

        // Icoon maken.
        ImageView backIcon = new ImageView(getClass().getResource("/media/back_button_icon.png").toExternalForm()); // Icoon inladen.
        backIcon.setFitWidth(20); // Grootte instellen.
        backIcon.setPreserveRatio(true); // Zorgen dat de hoogte mee schaalt met de fit width.
        root.getChildren().add(backIcon);

        // Label maken.
        root.getChildren().add(new Label("Terug naar categorieën"));

        root.setOnMouseClicked(_ -> DrankBuddy.changeView(new CategoryOverviewView())); // Wanneer er op de knop wordt geklikt de categorie overview view inladen.

        return root;
    }

    private VBox createHeading() {
        VBox root = new VBox(5); // Root node maken.

        // Categorieen titel
        Label title = new Label("Categorie toevoegen");
        title.getStyleClass().add("heading-title");

        // Categorieen subtitel
        Label subtitle = new Label("Voer een naam in voor de nieuwe productcategorie");
        subtitle.getStyleClass().add("heading-subtitle");

        root.getChildren().addAll(title, subtitle); // Alle onderdelen toevoegen aan de root node.

        return root;
    }

    private VBox createBox() {
        VBox root = new VBox(30); // Root node maken.
        root.getStyleClass().add("box");

        // Alle onderdelen toevoegen aan de root node.
        root.getChildren().addAll(
            createBoxHeadingSection(),
            createForm()
        );

        return root;
    }

    private VBox createBoxHeadingSection() {
        VBox root = new VBox(); // Root node maken.

        // Eerste titel maken.
        Label headingTitle = new Label("Nieuwe categorie");
        headingTitle.getStyleClass().add("box-heading-title");

        // Tweede titel maken.
        Label headingSubtitle = new Label("Vul onderstaand formulier in om een categorie aan te maken");
        headingSubtitle.getStyleClass().add("box-heading-subtitle");

        // Titels toevoegen aan de root node.
        root.getChildren().addAll(
            headingTitle,
            headingSubtitle
        );

        return root;
    }

    private VBox createForm() {
        VBox root = new VBox(30);

        VBox nameField = new VBox(5);
        nameField.getStyleClass().add("form-field");

        Label nameLabel = new Label("Categorienaam *");
        nameField.getChildren().add(nameLabel);

        TextField nameFieldInput = new TextField();
        nameFieldInput.setPromptText("Bijv. Spirits, Bier, Wijn...");
        nameField.getChildren().add(nameFieldInput);

        root.getChildren().add(nameField);

        Button submitButton = new Button("Toevoegen");
        submitButton.getStyleClass().add("submit-button");

        root.getChildren().add(submitButton);

        return root;
    }

}
