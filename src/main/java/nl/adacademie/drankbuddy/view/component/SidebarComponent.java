package nl.adacademie.drankbuddy.view.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import nl.adacademie.drankbuddy.DrankBuddy;
import nl.adacademie.drankbuddy.view.category.CategoryOverviewView;
import nl.adacademie.drankbuddy.view.product.ProductOverviewView;
import nl.adacademie.drankbuddy.view.security.LoginPageView;
import nl.adacademie.drankbuddy.view.stockmutation.StockMutationsOverviewView;
import nl.adacademie.drankbuddy.view.type.LoginPageStatus;
import nl.adacademie.drankbuddy.view.type.MenuPage;

/**
 * Een losse component om de sidebar te maken.
 */
public class SidebarComponent extends VBox {

    /**
     * @param activePage De pagina die momenteel actief is.
     */
    public SidebarComponent(MenuPage activePage) {
        // Stylesheets laden
        getStylesheets().add("https://fonts.googleapis.com/css2?family=Geist:wght@100..900&display=swap");
        getStylesheets().add(getClass().getResource("/css/sidebar.css").toExternalForm());

        // Sidebar maken
        setPrefSize(300, DrankBuddy.APPLICATION_WINDOW_SIZE[1]);
        getStyleClass().add("sidebar");

        // Een soort spacer maken die de login knop naar beneden duwt.
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // Onderdelen toevoegen aan sidebar
        getChildren().addAll(
            createSidebarHeader(),
            createNavigationMenu(activePage),
            spacer,
            createLogoutSection()
        );
    }

    private HBox createSidebarHeader() {
        HBox root = new HBox(); // Root node maken
        root.getStyleClass().add("header-section");

        HBox headerWrapper = new HBox(15); // Wrapper maken voor logo en titels
        headerWrapper.setAlignment(Pos.CENTER_LEFT);
        headerWrapper.setPadding(new Insets(20));

        ImageView logo = new ImageView(getClass().getResource("/media/logo.png").toExternalForm()); // DrankBuddy logo inladen.
        logo.setFitWidth(50); // Zorgen dat de foto wat kleiner wordt.
        logo.setLayoutX(5); // De foto horizontaal centreren in de logoPane.
        logo.setLayoutY(5); // De foto verticaal centreren in de logoPane.
        logo.setPreserveRatio(true); // Zorgen dat de foto mee schaalt met de fit width.

        Pane logoPane = new Pane(logo); // Dit is voor de achtergrond van het logo, omdat je geen background-color kunt toewijzen aan een ImageView.
        logoPane.setPadding(new Insets(10)); // Beetje ruimte in de pane maken.
        logoPane.getStyleClass().add("logo-pane");

        VBox headerTitles = new VBox(5); // Wrapper maken voor de titels
        headerTitles.setAlignment(Pos.CENTER_LEFT); // Links-midden positioneren
        Label title = new Label("DrankBuddy"); // De hoofdtitel maken.
        title.getStyleClass().add("header-title");
        Label subtitle = new Label("Voorraadbeheer"); // De subtitel maken.
        subtitle.getStyleClass().add("header-subtitle");
        headerTitles.getChildren().addAll(title, subtitle); // De titels toevoegen aan de wrapper.

        headerWrapper.getChildren().addAll(logoPane, headerTitles); // De logo en titels toevoegen aan de wrapper.
        root.getChildren().add(headerWrapper);

        return root;
    }

    private VBox createNavigationMenu(MenuPage activePage) {
        VBox root = new VBox(5); // Root node maken
        root.setPadding(new Insets(20)); // Ruimte geven aan onderdelen

        // Alle producten pagina:
        root.getChildren().add(
            createNavLink(
                "/media/product_icon.png",
                "Producten",
                activePage == MenuPage.PRODUCTS,
                ProductOverviewView.class
            )
        );

        // Alle categorieen pagina:
        root.getChildren().add(
            createNavLink(
                "/media/category_icon.png",
                "Categorieën",
                activePage == MenuPage.CATEGORIES,
                CategoryOverviewView.class
            )
        );

        // Alle voorraadmutaties pagina:
        root.getChildren().add(
            createNavLink(
                "/media/stock_mutation_icon.png",
                "Voorraadmutaties",
                activePage == MenuPage.STOCK_MUTATIONS,
                StockMutationsOverviewView.class
            )
        );

        return root;
    }

    private HBox createNavLink(String iconPath, String labelText, boolean isActive, Class<? extends BorderPane> navigationTarget) {
        HBox root = new HBox(10); // Root node maken
        root.getStyleClass().add("nav-link");

        root.setOnMouseClicked(_ -> { // Wanneer er geklikt wordt op de root node.
            try {
                DrankBuddy.changeView(navigationTarget.getDeclaredConstructor().newInstance()); // Proberen een nieuwe instantie maken van de gegeven pagina.
            } catch (Exception exception) {
                throw new RuntimeException("Er is een fout opgetreden bij het aanmaken van een nieuwe dashboard view.", exception); // Kan in principe nooit gebeuren.
            }
        });

        if (isActive) { // Als de link active moet zijn dan de style class toevoegen.
            root.getStyleClass().add("active-link");
        }

        ImageView icon = new ImageView(getClass().getResource(iconPath).toExternalForm()); // Nieuwe image view maken met het gegeven icon path.
        icon.setFitWidth(20); // De grootte instellen.
        icon.setPreserveRatio(true); // De hoogte mee scalen met de fit width.
        root.getChildren().addAll(icon, new Label(labelText)); // Label aanmaken en toevoegen aan de root node.
        root.setAlignment(Pos.CENTER_LEFT); // Links-midden positioneren.

        return root;
    }

    private VBox createLogoutSection() {
        VBox root = new VBox(10); // Root node maken
        root.getStyleClass().add("logout-section");
        root.setPrefSize(300, 75); // De grootte instellen.

        root.setAlignment(Pos.CENTER); // De inhoud centreren.
        root.setPadding(new Insets(0, 20, 0, 20)); // Beetje ruimte geven aan de linker en rechterkant.

        ImageView logoutIcon = new ImageView(getClass().getResource("/media/log-out.png").toExternalForm()); // Icon inladen.
        logoutIcon.setFitWidth(25); // Grootte instellen.
        logoutIcon.setPreserveRatio(true); // Zorgen dat de hoogte mee schaalt met de fit width.
        Label logoutLabel = new Label("Uitloggen"); // Label maken.

        HBox logoutButton = new HBox(10, logoutIcon, logoutLabel); // De icon en label samenvoegen in een wrapper.
        logoutButton.getStyleClass().add("logout-button");
        logoutButton.setAlignment(Pos.CENTER_LEFT); // Links-midden positioneren.

        logoutButton.setOnMouseClicked(_ -> { // Wanneer er op de knop wordt geklikt.
            DrankBuddy.loggedInAccount = null; // De ingelogde account instellen op null.
            DrankBuddy.changeView(new LoginPageView(LoginPageStatus.NONE)); // View veranderen naar de login pagina.
        });

        root.getChildren().add(logoutButton);

        return root;
    }
}