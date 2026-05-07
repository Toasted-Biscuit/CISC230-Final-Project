package finalProject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class GameWindow extends Application{

	public void start(Stage ps) {
		Label title = new Label("Game and stuff");
		Button button = new Button("Click Me!");
		Button nw = new Button("Hello");
		button.setLayoutX(200);
		button.setLayoutY(200);
		
		Pane pane = new Pane(title, button);
		Scene scene = new Scene(pane, 600, 600);
		
		ps.setScene(scene);
		ps.setTitle("Game!!!");
		ps.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
