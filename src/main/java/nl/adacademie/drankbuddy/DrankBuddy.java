package nl.adacademie.drankbuddy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import nl.adacademie.drankbuddy.view.RegisterPageView;
import nl.adacademie.drankbuddy.view.status.RegisterPageStatus;

public class DrankBuddy extends Application {

    public static final int[] APPLICATION_WINDOW_SIZE = {1500, 900};
    private static Stage stage;

    public static void changeView(Pane view) {
        stage.setScene(new Scene(view, APPLICATION_WINDOW_SIZE[0], APPLICATION_WINDOW_SIZE[1]));
    }

    @Override
    public void start(Stage stage) {
        DrankBuddy.stage = stage;

        Scene scene = new Scene(new RegisterPageView(RegisterPageStatus.NONE), APPLICATION_WINDOW_SIZE[0], APPLICATION_WINDOW_SIZE[1]);
        stage.setTitle("DrankBuddy V1");
        stage.setScene(scene);
        stage.show();
    }

}
