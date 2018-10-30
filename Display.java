package intelliGreen;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Display extends Application {
	
	/*
	 * This class creates the user Display using JavaFX
	 */
	
	 @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
	Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("IntelliGarden Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 public static void main(String[] args) {
        launch(args);
    }
}
