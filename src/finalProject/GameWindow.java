package finalProject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameWindow extends Application{

	public void start(Stage ps) {
		Label title = new Label("Game and stuff");
		
		Pane pane = new Pane(title);
		Scene scene = new Scene(pane, 400, 400);
		
		ps.setScene(scene);
		ps.setTitle("Game!!!");
		ps.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
