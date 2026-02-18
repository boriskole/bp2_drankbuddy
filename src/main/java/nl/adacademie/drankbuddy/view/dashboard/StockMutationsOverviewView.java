package nl.adacademie.drankbuddy.view.dashboard;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import nl.adacademie.drankbuddy.view.type.MenuPage;

public class StockMutationsOverviewView extends BorderPane {

    public StockMutationsOverviewView() {
        setLeft(new DashboardComponent(MenuPage.STOCK_MUTATIONS));
        setCenter(new Label("Stock mutations overview page"));
    }

}
