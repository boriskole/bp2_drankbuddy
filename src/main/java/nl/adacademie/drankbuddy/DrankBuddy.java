package nl.adacademie.drankbuddy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import nl.adacademie.drankbuddy.entity.Account;
import nl.adacademie.drankbuddy.repository.dao.AccountDaoImpl;
import nl.adacademie.drankbuddy.view.dashboard.ProductOverviewView;

public class DrankBuddy extends Application {

    public static final int[] APPLICATION_WINDOW_SIZE = {1500, 900};
    public static Account loggedInAccount;
    private static Stage stage;

    public static void changeView(Pane view) {
        stage.setScene(new Scene(view, APPLICATION_WINDOW_SIZE[0], APPLICATION_WINDOW_SIZE[1]));
    }

    @Override
    public void start(Stage stage) {
        DrankBuddy.stage = stage;

        loggedInAccount = new AccountDaoImpl().findByUsername("boriskole").get();

//        Scene scene = new Scene(new LoginPageView(LoginPageStatus.NONE), APPLICATION_WINDOW_SIZE[0], APPLICATION_WINDOW_SIZE[1]);
        Scene scene = new Scene(new ProductOverviewView(), APPLICATION_WINDOW_SIZE[0], APPLICATION_WINDOW_SIZE[1]);
        stage.setTitle("DrankBuddy V1");
        stage.setScene(scene);
        stage.show();
    }

}
