package nl.adacademie.drankbuddy.view.dashboard;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import nl.adacademie.drankbuddy.view.type.MenuPage;

public class ProductOverviewView extends BorderPane {

    public ProductOverviewView() {
        setLeft(new DashboardComponent(MenuPage.PRODUCTS));
        setCenter(new Label("Product overview page"));
    }

}
