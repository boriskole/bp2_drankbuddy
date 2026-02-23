package nl.adacademie.drankbuddy.view.stockmutation;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nl.adacademie.drankbuddy.DrankBuddy;
import nl.adacademie.drankbuddy.controller.stockmutation.AddStockMutationController;
import nl.adacademie.drankbuddy.dto.AddStockMutationRequestDto;
import nl.adacademie.drankbuddy.entity.Product;
import nl.adacademie.drankbuddy.entity.mutation.StockMutationType;
import nl.adacademie.drankbuddy.repository.dao.ProductDaoImpl;
import nl.adacademie.drankbuddy.repository.dao.StockMutationDaoImpl;
import nl.adacademie.drankbuddy.repository.interfaces.ProductDaoInterface;
import nl.adacademie.drankbuddy.view.component.SidebarComponent;
import nl.adacademie.drankbuddy.view.type.AddStockMutationPageStatus;
import nl.adacademie.drankbuddy.view.type.MenuPage;
import nl.adacademie.drankbuddy.view.type.StockMutationsOverviewPageStatus;

import java.util.List;

public class AddStockMutationView extends BorderPane {

    private final AddStockMutationPageStatus addStockMutationPageStatus;

    public AddStockMutationView(AddStockMutationPageStatus addStockMutationPageStatus) {
        this.addStockMutationPageStatus = addStockMutationPageStatus;

        getStylesheets().add(getClass().getResource("/css/overview.css").toExternalForm()); // CSS toevoegen.
        getStylesheets().add(getClass().getResource("/css/form.css").toExternalForm()); // CSS toevoegen.

        setLeft(new SidebarComponent(MenuPage.STOCK_MUTATIONS)); // Sidebar toevoegen.

        VBox root = new VBox(30);
        root.setPadding(new Insets(20, 200, 20, 200)); // Ruimte geven aan onderdelen

        // Losse components toevoegen.
        root.getChildren().addAll(
            createBackButton(),
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
        root.getChildren().add(new Label("Terug naar voorraadmutaties"));

        root.setOnMouseClicked(_ -> DrankBuddy.changeView(new StockMutationsOverviewView(StockMutationsOverviewPageStatus.NONE))); // Wanneer er op de knop wordt geklikt de voorraadmutatie overview view inladen.

        return root;
    }

    private VBox createBox() {
        VBox root = new VBox(15); // Root node maken.
        root.getStyleClass().add("box");

        // Alle onderdelen toevoegen aan de root node.
        root.getChildren().add(createBoxHeadingSection());

        // Alleen als de status empty fields is of too long name.
        if (addStockMutationPageStatus == AddStockMutationPageStatus.EMPTY_FIELDS || addStockMutationPageStatus == AddStockMutationPageStatus.NO_ZERO) {
            root.getChildren().add(createError(addStockMutationPageStatus)); // Error melding maken.
        }

        root.getChildren().add(createForm()); // Formulier toevoegen.

        return root;
    }

    private VBox createBoxHeadingSection() {
        VBox root = new VBox(); // Root node maken.

        // Eerste titel maken.
        Label headingTitle = new Label("Voorraadmutatie registreren");
        headingTitle.getStyleClass().add("box-heading-title");

        // Tweede titel maken.
        Label headingSubtitle = new Label("Selecteer een product, mutatie-type en voer het aantal in");
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

        VBox productField = new VBox(5); // Box maken voor het product.
        productField.getStyleClass().add("form-field");

        Label productLabel = new Label("Product *"); // Label maken.
        productField.getChildren().add(productLabel);

        ProductDaoInterface productDaoInterface = new ProductDaoImpl();
        List<Product> products = productDaoInterface.findAllByAccount(DrankBuddy.loggedInAccount); // Producten ophalen.
        ComboBox<Product> productComboBox = new ComboBox<>(FXCollections.observableArrayList(products)); // Combo box maken met de opgehaalde producten.
        productComboBox.getSelectionModel().selectFirst(); // Alvast eentje selecteren.
        productField.getChildren().add(productComboBox);

        root.getChildren().add(productField);

        VBox typeField = new VBox(5); // Box maken voor het type.
        typeField.getStyleClass().add("form-field");

        Label typeLabel = new Label("Mutatie type *");
        typeField.getChildren().add(typeLabel);

        ComboBox<StockMutationType> typeComboBox = new ComboBox<>(FXCollections.observableArrayList(StockMutationType.values()));
        typeComboBox.getSelectionModel().selectFirst(); // Alvast eentje selecteren.
        typeField.getChildren().add(typeComboBox);

        root.getChildren().add(typeField);

        VBox amountField = new VBox(5);
        amountField.getStyleClass().add("form-field");

        Label amountLabel = new Label("Aantal *");
        amountField.getChildren().add(amountLabel);

        TextField amountFieldInput = new TextField();
        amountFieldInput.setPromptText("Bijv. 5, -10, 25");

        TextFormatter<Integer> formatter = new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("-?\\d*")) { // Alleen cijfers toegestaan, geen letters.
                return change;
            }
            return null;
        });

        amountFieldInput.setTextFormatter(formatter); // De text formatter instellen op het invoerveld.

        amountField.getChildren().add(amountFieldInput);

        root.getChildren().add(amountField);

        Button submitButton = new Button("Registreren"); // Submit knop maken.
        submitButton.getStyleClass().add("submit-button");

        submitButton.setOnMouseClicked(_ -> {
            // Dto maken met de form data.
            AddStockMutationRequestDto addStockMutationRequestDto = new AddStockMutationRequestDto(
                productComboBox.getValue(),
                typeComboBox.getValue(),
                Integer.parseInt(amountFieldInput.getText().isBlank() ? "0" : amountFieldInput.getText()) // Is veilig, want de textformatter verbied het invoeren van letters.
            );

            // Instantie maken van de controller met daarin de echte database dao versie.
            AddStockMutationController addStockMutationController = new AddStockMutationController(new StockMutationDaoImpl());
            AddStockMutationPageStatus result = addStockMutationController.addStockMutation(addStockMutationRequestDto);

            // Resultaat verwerken.
            switch (result) {
                case EMPTY_FIELDS -> DrankBuddy.changeView(new AddStockMutationView(AddStockMutationPageStatus.EMPTY_FIELDS));
                case NO_ZERO -> DrankBuddy.changeView(new AddStockMutationView(AddStockMutationPageStatus.NO_ZERO));
                case UNDER_ZERO -> DrankBuddy.changeView(new AddStockMutationView(AddStockMutationPageStatus.UNDER_ZERO));
                case SUCCESS -> DrankBuddy.changeView(new StockMutationsOverviewView(StockMutationsOverviewPageStatus.ADD_SUCCESS));
            }
        });

        root.getChildren().add(submitButton);

        return root;
    }

    private HBox createError(AddStockMutationPageStatus addStockMutationPageStatus) {
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
        String message = switch (addStockMutationPageStatus) {
            case EMPTY_FIELDS -> "Vul alstublieft alle velden in.";
            case NO_ZERO -> "Je kan geen nul invoeren.";
            case UNDER_ZERO -> "Je kan geen negatieve voorraad hebben.";
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
