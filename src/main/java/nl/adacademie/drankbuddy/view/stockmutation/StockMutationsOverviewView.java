package nl.adacademie.drankbuddy.view.stockmutation;

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
import nl.adacademie.drankbuddy.entity.mutation.StockMutation;
import nl.adacademie.drankbuddy.repository.dao.StockMutationDaoImpl;
import nl.adacademie.drankbuddy.repository.interfaces.StockMutationDaoInterface;
import nl.adacademie.drankbuddy.view.component.SidebarComponent;
import nl.adacademie.drankbuddy.view.type.AddStockMutationPageStatus;
import nl.adacademie.drankbuddy.view.type.MenuPage;
import nl.adacademie.drankbuddy.view.type.StockMutationsOverviewPageStatus;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

public class StockMutationsOverviewView extends BorderPane {

    private final StockMutationsOverviewPageStatus stockMutationsOverviewPageStatus;

    public StockMutationsOverviewView(StockMutationsOverviewPageStatus stockMutationsOverviewPageStatus) {
        this.stockMutationsOverviewPageStatus = stockMutationsOverviewPageStatus;

        getStylesheets().add(getClass().getResource("/css/overview.css").toExternalForm()); // CSS toevoegen.
        getStylesheets().add(getClass().getResource("/css/stock_mutation.css").toExternalForm()); // CSS toevoegen.
        setLeft(new SidebarComponent(MenuPage.STOCK_MUTATIONS)); // Sidebar toevoegen.

        VBox root = new VBox(20);
        root.setPadding(new Insets(20, 80, 20, 80));

        root.getChildren().add(createHeading());

        if (stockMutationsOverviewPageStatus != StockMutationsOverviewPageStatus.NONE) {
            root.getChildren().add(createSuccessMessage());
        }

        root.getChildren().add(createBox());

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
        String message = "Gelukt! Uw voorraadmutatie is succesvol toegevoegd.";

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

        // Producten titel
        Label title = new Label("Voorraadmutaties");
        title.getStyleClass().add("heading-title");

        // Producten subtitel
        Label subtitle = new Label("Registreer en bekijk voorraadwijzigingen");
        subtitle.getStyleClass().add("heading-subtitle");

        root.getChildren().addAll(title, subtitle); // Alle onderdelen toevoegen aan de root node.

        return root;
    }

    private VBox createBox() {
        VBox root = new VBox(30); // Root node maken.
        root.getStyleClass().add("box");

        ScrollPane scrollPane = new ScrollPane(createMutationList());
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
        Label headingTitle = new Label("Recente mutaties");
        headingTitle.getStyleClass().add("box-heading-title");

        // Tweede titel maken.
        Label headingSubtitle = new Label("Overzicht van alle voorraadwijzigingen");
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

        addButton.getChildren().add(new Label("Mutatie toevoegen"));

        addButton.setOnMouseClicked(_ -> DrankBuddy.changeView(new AddStockMutationView(AddStockMutationPageStatus.NONE)));

        root.getChildren().add(addButton);

        return root;
    }

    private VBox createMutationList() {

        VBox root = new VBox(); // Root node maken.

        root.getChildren().add(createListHeading()); // De tabel headers toevoegen.

        StockMutationDaoInterface stockMutationDao = new StockMutationDaoImpl();
        List<StockMutation> stockMutations = stockMutationDao.findAllByAccount(DrankBuddy.loggedInAccount);
        stockMutations.forEach(product -> root.getChildren().add(createMutationRow(product)));

        if (stockMutations.isEmpty()) {
            HBox wrapper = new HBox();
            wrapper.setAlignment(Pos.CENTER);
            wrapper.setPadding(new Insets(20));
            wrapper.getChildren().add(new Label("Er zijn nog geen mutaties toegevoegd."));
            root.getChildren().add(wrapper);
        }

        return root;
    }

    private HBox createListHeading() {
        HBox root = new HBox();
        root.getStyleClass().addAll("list-row", "list-heading-row");

        Label productName = new Label("Tijdstip");
        Label productCategory = new Label("Product");
        Label productStock = new Label("Type");
        productStock.setAlignment(Pos.CENTER_RIGHT);
        Label productActions = new Label("Wijziging");
        productActions.setAlignment(Pos.CENTER_RIGHT);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        root.getChildren().addAll(productName, productCategory, spacer, productStock, productActions);
        Stream.of(productName, productCategory, productStock, productActions).forEach(label -> label.setPrefWidth(300));

        return root;
    }

    private HBox createMutationRow(StockMutation stockMutation) {
        HBox root = new HBox();
        root.getStyleClass().addAll("list-row");

        Label mutationTimestamp = new Label(stockMutation.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm:ss")));
        Label mutationProductName = new Label(stockMutation.getProduct().getName());
        Label mutationType = new Label(stockMutation.getType().getTitle());
        mutationType.setAlignment(Pos.CENTER_RIGHT);
        HBox mutationStockChange = new HBox(
            createStockChange(stockMutation.getStockChange())
        );
        mutationStockChange.setAlignment(Pos.CENTER_RIGHT);

        mutationTimestamp.setPrefWidth(300);
        mutationProductName.setPrefWidth(300);
        mutationType.setPrefWidth(300);
        mutationStockChange.setPrefWidth(300);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        root.getChildren().addAll(mutationTimestamp, mutationProductName, spacer, mutationType, mutationStockChange);

        return root;
    }

    private HBox createStockChange(int stockChange) {
        HBox root = new HBox(10);
        root.getStyleClass().add("stock-change");
        root.getStyleClass().add(stockChange > 0 ? "stock-change-positive" : "stock-change-negative");
        root.setAlignment(Pos.CENTER_RIGHT);
        root.setPadding(new Insets(5, 10, 5, 10));
        boolean isPositive = stockChange > 0;

        ImageView trendingIcon = new ImageView(getClass().getResource("/media/trending_" + (isPositive ? "up" : "down") + ".png").toExternalForm()); // Dynamisch bepalen welke icon we moeten pakken.
        trendingIcon.setFitWidth(12); // Grootte instellen.
        trendingIcon.setPreserveRatio(true);
        root.getChildren().add(trendingIcon);

        Label stockChangeLabel = new Label(
            stockChange > 0 ? "+" + stockChange : stockChange + "" // Een plus symbol ervoor doen als het positief is.
        );
        root.getChildren().add(stockChangeLabel);

        return root;
    }

}
