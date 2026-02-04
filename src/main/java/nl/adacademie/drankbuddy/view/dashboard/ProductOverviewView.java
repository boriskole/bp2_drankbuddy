package nl.adacademie.drankbuddy.view.dashboard;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import nl.adacademie.drankbuddy.DrankBuddy;
import nl.adacademie.drankbuddy.entity.Account;

public class ProductOverviewView extends Pane {

    private final Account account;

    public ProductOverviewView() {
        this.account = DrankBuddy.loggedInAccount;

        getChildren().add(new Label(String.format("Hallo, %s!", account.getName())));
    }

}
