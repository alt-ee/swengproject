package survivalapp;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainAppTest extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		//new ControllerManualTest().runTest();
		new StartupManualTest().runTest();

		primaryStage.setWidth(0);
		primaryStage.setHeight(0);
		primaryStage.setX(Double.MAX_VALUE);
		primaryStage.setY(Double.MAX_VALUE);
		primaryStage.initStyle(StageStyle.UTILITY);
		primaryStage.show();
        
	}
	
	
}
