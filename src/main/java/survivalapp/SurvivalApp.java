package survivalapp;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SurvivalApp extends Application{

    public void start(Stage primaryStage) {
        Controller controller = new Controller();
        controller.run();

        // This creates an invisible JavaFX stage
        // We have to do this to stop the JFX process dying when
        // videos are removed from the view
        primaryStage.setWidth(0);
        primaryStage.setHeight(0);
        primaryStage.setX(Double.MAX_VALUE);
        primaryStage.setY(Double.MAX_VALUE);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();
    }
}
