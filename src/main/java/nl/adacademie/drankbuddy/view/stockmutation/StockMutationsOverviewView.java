package nl.adacademie.drankbuddy.view.stockmutation;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import nl.adacademie.drankbuddy.view.component.SidebarComponent;
import nl.adacademie.drankbuddy.view.type.MenuPage;

public class StockMutationsOverviewView extends BorderPane {

    public StockMutationsOverviewView() {
        setLeft(new SidebarComponent(MenuPage.STOCK_MUTATIONS));
        setCenter(new Label("Stock mutations overview page"));
    }

}
