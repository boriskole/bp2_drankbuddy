package nl.adacademie.drankbuddy.view.dashboard;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import nl.adacademie.drankbuddy.entity.Product;
import nl.adacademie.drankbuddy.view.type.MenuPage;

public class ProductOverviewView extends BorderPane {

    public ProductOverviewView() {
        setLeft(new SidebarComponent(MenuPage.PRODUCTS)); //

        VBox root = new VBox(20);
        root.setPadding(new Insets(20, 80, 20, 80));

        Label title = new Label("Producten");
        Label subtitle = new Label("Beheer alle producten van uw drankwinkel");

        root.getChildren().addAll(title, subtitle, new TableView<Product>());
        setCenter(root);
    }

}
