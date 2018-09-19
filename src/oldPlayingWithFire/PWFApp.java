package oldPlayingWithFire;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PWFApp extends Application {
	@FXML AnchorPane window;
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(PWFApp.class.getResource("PWFBoard.fxml"));
		window = fxmlLoader.load();
	    Scene scene = new Scene(window);
	    PWFController controller = fxmlLoader.getController();
	    scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> controller.keyDown(event));
	    scene.addEventFilter(KeyEvent.KEY_RELEASED, event -> controller.keyUp(event));
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    
	}
	public static void main(String[] args) {
		launch(PWFApp.class,args);
	}
}
