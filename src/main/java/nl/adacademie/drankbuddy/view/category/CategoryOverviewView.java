package nl.adacademie.drankbuddy.view.category;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import nl.adacademie.drankbuddy.DrankBuddy;
import nl.adacademie.drankbuddy.entity.Category;
import nl.adacademie.drankbuddy.repository.dao.CategoryDaoImpl;
import nl.adacademie.drankbuddy.repository.interfaces.CategoryDaoInterface;
import nl.adacademie.drankbuddy.view.component.SidebarComponent;
import nl.adacademie.drankbuddy.view.type.AddCategoryPageStatus;
import nl.adacademie.drankbuddy.view.type.CategoryOverviewPageStatus;
import nl.adacademie.drankbuddy.view.type.EditCategoryPageStatus;
import nl.adacademie.drankbuddy.view.type.MenuPage;

import java.util.List;

public class CategoryOverviewView extends BorderPane {

    private final CategoryOverviewPageStatus categoryOverviewPageStatus;

    public CategoryOverviewView(CategoryOverviewPageStatus categoryOverviewPageStatus) {
        this.categoryOverviewPageStatus = categoryOverviewPageStatus;

        getStylesheets().add(getClass().getResource("/css/overview.css").toExternalForm()); // CSS toevoegen.
        setLeft(new SidebarComponent(MenuPage.CATEGORIES)); // Sidebar toevoegen.

        VBox root = new VBox(20);
        root.setPadding(new Insets(20, 80, 20, 80));

        root.getChildren().add(createHeading());

        if (categoryOverviewPageStatus != CategoryOverviewPageStatus.NONE) {
            root.getChildren().add(createSuccessMessage(categoryOverviewPageStatus));
        }

        root.getChildren().add(createBox());

        setCenter(root);
    }

    private HBox createSuccessMessage(CategoryOverviewPageStatus categoryOverviewPageStatus) {
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
        String message = switch (categoryOverviewPageStatus) {
            case ADD_SUCCESS -> "Gelukt! Uw categorie is succesvol toegevoegd.";
            case EDIT_SUCCESS -> "Gelukt! Uw categorie is succesvol gewijzigd.";
            default -> "Gelukt!";
        };

        Label successLabel = new Label(message);
        successLabel.getStyleClass().add("success-text");

        // Zorg dat de tekst netjes wrapt
        successLabel.setWrapText(true);
        successLabel.setMinWidth(0);
        HBox.setHgrow(successLabel, Priority.ALWAYS);

        successBox.getChildren().addAll(successIcon, successLabel);
        return successBox;
    }

    private VBox createHeading() {
        VBox root = new VBox(5); // Root node maken.

        // Categorieen titel
        Label title = new Label("Categorieën");
        title.getStyleClass().add("heading-title");

        // Categorieen subtitel
        Label subtitle = new Label("Beheer productcategorieën voor uw drankwinkel");
        subtitle.getStyleClass().add("heading-subtitle");

        root.getChildren().addAll(title, subtitle); // Alle onderdelen toevoegen aan de root node.

        return root;
    }

    private VBox createBox() {
        VBox root = new VBox(30); // Root node maken.
        root.getStyleClass().add("box");

        ScrollPane scrollPane = new ScrollPane(createCategoryList());
        scrollPane.setFitToWidth(true);

        // Alle onderdelen toevoegen aan de root node.
        root.getChildren().addAll(
            createBoxHeadingSection(),
            scrollPane
        );

        return root;
    }

    private HBox createBoxHeadingSection() {
        HBox root = new HBox(); // Root node maken.

        // Eerste titel maken.
        Label headingTitle = new Label("Overzicht categorieën");
        headingTitle.getStyleClass().add("box-heading-title");

        // Tweede titel maken.
        Label headingSubtitle = new Label("Alle beschikbare productcategorieën");
        headingSubtitle.getStyleClass().add("box-heading-subtitle");

        // Titels toevoegen aan de root node.
        root.getChildren().add(new VBox(
            headingTitle,
            headingSubtitle
        ));

        // Een spacer maken om de toevoegen-knop helemaal rechts te hebben.
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS); // Horizontaal laten groeien.
        root.getChildren().add(spacer);

        HBox addButton = new HBox(15); // De wrapper maken voor de knop.
        addButton.getStyleClass().add("box-add-button");
        addButton.setAlignment(Pos.CENTER_LEFT); // Links-midden positioneren.

        ImageView imageView = new ImageView(getClass().getResource("/media/plus_icon.png").toExternalForm()); // Plus icoon inladen.
        imageView.setFitWidth(20); // Grootte instellen.
        imageView.setPreserveRatio(true); // Laten mee schalen.
        addButton.getChildren().add(imageView);

        addButton.getChildren().add(new Label("Categorie toevoegen"));

        addButton.setOnMouseClicked(_ -> DrankBuddy.changeView(new AddCategoryView(AddCategoryPageStatus.NONE))); // Wanneer er op de knop wordt geklikt de add category view inladen.

        root.getChildren().add(addButton);

        return root;
    }

    private VBox createCategoryList() {

        VBox root = new VBox(); // Root node maken.

        root.getChildren().add(createListHeading()); // De tabel headers toevoegen.

        CategoryDaoInterface categoryDao = new CategoryDaoImpl(); // Dao maken.
        List<Category> categories = categoryDao.findAllByAccount(DrankBuddy.loggedInAccount); // Alle categorieen ophalen.
        categories.forEach(category -> root.getChildren().add(createCategoryRow(category))); // Voor elke categorie een rij toevoegen.

        if (categories.isEmpty()) { // Als er geen categorieen zijn.
            HBox wrapper = new HBox(); // Wrapper maken.
            wrapper.setAlignment(Pos.CENTER); // Midden positioneren.
            wrapper.setPadding(new Insets(20));
            wrapper.getChildren().add(new Label("Er zijn nog geen categorieën toegevoegd."));
            root.getChildren().add(wrapper);
        }

        return root;
    }

    private HBox createListHeading() {
        HBox root = new HBox(); // Root node maken.
        root.getStyleClass().addAll("list-row", "list-heading-row");

        // Labels maken.
        Label categoryId = new Label("ID");
        Label categoryName = new Label("Categorienaam");
        Label categoryAmountOfProducts = new Label("Aantal producten");
        categoryAmountOfProducts.setAlignment(Pos.CENTER_RIGHT);
        Label categoryActions = new Label("Acties");
        categoryActions.setAlignment(Pos.CENTER_RIGHT);

        Region spacer = new Region(); // Een spacer maken om de laatste twee kolommen aan de rechterkant te stoppen.
        HBox.setHgrow(spacer, Priority.ALWAYS); // Horizontaal laten groeien.

        // De grootte van alle labels instellen.
        categoryId.setPrefWidth(200);
        categoryName.setPrefWidth(200);
        categoryAmountOfProducts.setPrefWidth(200);
        categoryActions.setPrefWidth(200);

        root.getChildren().addAll(categoryId, categoryName, spacer, categoryAmountOfProducts, categoryActions);

        return root;
    }

    private HBox createCategoryRow(Category category) {
        HBox root = new HBox(); // Root node maken.
        root.getStyleClass().addAll("list-row");

        // Labels maken.
        Label categoryId = new Label(category.getId() + "");
        Label categoryName = new Label(category.getName());
        Label categoryAmountOfProducts = new Label(category.getAmountOfProducts() + " product(en)");
        categoryAmountOfProducts.setAlignment(Pos.CENTER_RIGHT);
        HBox categoryActions = new HBox(5);

        // Bewerken knop:
        ImageView editIcon = new ImageView(getClass().getResource("/media/edit_icon.png").toExternalForm()); // Icoon inladen.
        editIcon.setFitWidth(20); // Grootte instellen.
        editIcon.setPreserveRatio(true);
        BorderPane editButton = new BorderPane(editIcon);
        editButton.getStyleClass().add("list-action-button");
        categoryActions.getChildren().add(editButton);

        editButton.setOnMouseClicked(_ -> {
            DrankBuddy.changeView(new EditCategoryView(EditCategoryPageStatus.NONE, category));
        });

        // Verwijderen knop:
        ImageView deleteIcon = new ImageView(getClass().getResource("/media/delete_icon.png").toExternalForm()); // Icoon inladen.
        deleteIcon.setFitWidth(20); // Grootte instellen.
        deleteIcon.setPreserveRatio(true);
        BorderPane deleteButton = new BorderPane(deleteIcon);
        deleteButton.getStyleClass().add("list-action-button");
        categoryActions.getChildren().add(deleteButton);

        categoryActions.setAlignment(Pos.CENTER_RIGHT);

        // De grootte van alle labels instellen.
        categoryId.setPrefWidth(200);
        categoryName.setPrefWidth(200);
        categoryAmountOfProducts.setPrefWidth(200);
        categoryActions.setPrefWidth(200);

        Region spacer = new Region(); // Een spacer maken om de laatste twee kolommen aan de rechterkant te stoppen.
        HBox.setHgrow(spacer, Priority.ALWAYS); // Horizontaal laten groeien.

        root.getChildren().addAll(categoryId, categoryName, spacer, categoryAmountOfProducts, categoryActions);

        return root;
    }

}
