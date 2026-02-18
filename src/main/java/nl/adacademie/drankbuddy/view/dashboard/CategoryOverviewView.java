package nl.adacademie.drankbuddy.view.dashboard;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import nl.adacademie.drankbuddy.view.type.MenuPage;

public class CategoryOverviewView extends BorderPane {

    public CategoryOverviewView() {
        setLeft(new SidebarComponent(MenuPage.CATEGORIES));
        setCenter(new Label("Category overview page"));
    }

}
