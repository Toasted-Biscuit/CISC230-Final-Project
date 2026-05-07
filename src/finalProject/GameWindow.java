package finalProject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.*;
import java.util.Scanner;


public class GameWindow extends Application{
	Connect4GameLogic test;
	private int[][] idk;
	private GridPane gridy;


	public void start(Stage ps) {
		
		
		Label title = new Label("Game and stuff");
		Button button = new Button("Click Me!");
		button.setOnAction(this::startGame);
		Button nw = new Button("Hello");
		System.out.println("hello");
		System.out.println("hi");
		button.setLayoutX(200);
		button.setLayoutY(200);
		gridy = new GridPane();
		
		
		
		
		Pane pane = new Pane(title, button,gridy);
		Scene scene = new Scene(pane, 500, 500);
		
		ps.setScene(scene);
		ps.setTitle("Game!!!");
		ps.show();
		
	}
	
	public void startGame(ActionEvent event) {
		test = new Connect4GameLogic();
		test.setUpGrid();

		idk = test.getGrid();
		
		for(int x = 0; x < idk.length;x++) {
			for(int y = 0; y < 7; y++) {
				Circle c = new Circle(35);
				if(idk[x][y] == 0) {
					c.setFill(Color.RED);
					gridy.add(c, x, y );
				}else if(idk[x][y] == 1) {
					c.setFill(Color.YELLOW);
					gridy.add(c, x, y );
				}
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
		
		
	}
}
