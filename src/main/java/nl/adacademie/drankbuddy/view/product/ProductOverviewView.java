package nl.adacademie.drankbuddy.view.product;

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
import nl.adacademie.drankbuddy.entity.Product;
import nl.adacademie.drankbuddy.repository.dao.ProductDaoImpl;
import nl.adacademie.drankbuddy.repository.interfaces.ProductDaoInterface;
import nl.adacademie.drankbuddy.view.component.SidebarComponent;
import nl.adacademie.drankbuddy.view.type.MenuPage;

import java.util.List;
import java.util.stream.Stream;

public class ProductOverviewView extends BorderPane {

    public ProductOverviewView() {
        getStylesheets().add(getClass().getResource("/css/products_overview.css").toExternalForm()); // CSS toevoegen.
        setLeft(new SidebarComponent(MenuPage.PRODUCTS)); // Sidebar toevoegen.

        VBox root = new VBox(20);
        root.setPadding(new Insets(20, 80, 20, 80));

        root.getChildren().addAll(
            createHeading(),
            createBox()
        );

        setCenter(root);
    }

    private VBox createHeading() {
        VBox root = new VBox(5); // Root node maken.

        // Producten titel
        Label title = new Label("Producten");
        title.getStyleClass().add("heading-title");

        // Producten subtitel
        Label subtitle = new Label("Beheer alle producten van uw drankwinkel");
        subtitle.getStyleClass().add("heading-subtitle");

        root.getChildren().addAll(title, subtitle); // Alle onderdelen toevoegen aan de root node.

        return root;
    }

    private VBox createBox() {
        VBox root = new VBox(30); // Root node maken.
        root.getStyleClass().add("box");

        ScrollPane scrollPane = new ScrollPane(createProductList());
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
        Label headingTitle = new Label("Overzicht producten");
        headingTitle.getStyleClass().add("box-heading-title");

        // Tweede titel maken.
        Label headingSubtitle = new Label("Alle producten per categorie met voorraadstatus");
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

        addButton.getChildren().add(new Label("Product toevoegen"));
        root.getChildren().add(addButton);

        return root;
    }

    private VBox createProductList() {

        VBox root = new VBox(); // Root node maken.

        root.getChildren().add(createListHeading()); // De tabel headers toevoegen.

        ProductDaoInterface productDao = new ProductDaoImpl();
        List<Product> products = productDao.findAllByAccount(DrankBuddy.loggedInAccount);
        products.forEach(product -> root.getChildren().add(createProductRow(product)));

        if (products.isEmpty()) {
            HBox wrapper = new HBox();
            wrapper.setAlignment(Pos.CENTER);
            wrapper.setPadding(new Insets(20));
            wrapper.getChildren().add(new Label("Er zijn nog geen producten toegevoegd."));
            root.getChildren().add(wrapper);
        }

        return root;
    }

    private HBox createListHeading() {
        HBox root = new HBox();
        root.getStyleClass().addAll("list-row", "list-heading-row");

        Label productName = new Label("Productnaam");
        Label productCategory = new Label("Categorie");
        Label productStock = new Label("Huidige voorraad");
        productStock.setAlignment(Pos.CENTER_RIGHT);
        Label productActions = new Label("Acties");
        productActions.setAlignment(Pos.CENTER_RIGHT);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        root.getChildren().addAll(productName, productCategory, spacer, productStock, productActions);
        Stream.of(productName, productCategory, productStock, productActions).forEach(label -> label.setPrefWidth(200));

        return root;
    }

    private HBox createProductRow(Product product) {
        HBox root = new HBox();
        root.getStyleClass().addAll("list-row");

        Label productName = new Label(product.getName());
        Label productCategory = new Label(product.getCategory().getName());
        Label productStock = new Label(product.getCurrentStock() + " stuks");
        productStock.setAlignment(Pos.CENTER_RIGHT);
        HBox productActions = new HBox(5);

        ImageView editIcon = new ImageView(getClass().getResource("/media/edit_icon.png").toExternalForm());
        editIcon.setFitWidth(20);
        editIcon.setPreserveRatio(true);
        BorderPane editButton = new BorderPane(editIcon);
        editButton.getStyleClass().add("list-action-button");
        productActions.getChildren().add(editButton);

        ImageView deleteIcon = new ImageView(getClass().getResource("/media/delete_icon.png").toExternalForm());
        deleteIcon.setFitWidth(20);
        deleteIcon.setPreserveRatio(true);
        BorderPane deleteButton = new BorderPane(deleteIcon);
        deleteButton.getStyleClass().add("list-action-button");
        productActions.getChildren().add(deleteButton);

        productActions.setAlignment(Pos.CENTER_RIGHT);

        productName.setPrefWidth(200);
        productCategory.setPrefWidth(200);
        productStock.setPrefWidth(200);
        productActions.setPrefWidth(200);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        root.getChildren().addAll(productName, productCategory, spacer, productStock, productActions);

        return root;
    }

}
