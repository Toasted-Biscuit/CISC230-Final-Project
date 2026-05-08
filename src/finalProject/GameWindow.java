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

	//this is for the players to interact with dropping chip for the game
    private int currentPlayer = Connect4.YELLOW;
    private Connect4 game = new Connect4();
    private Label turnLabel = new Label("Yellow's Turn!");
    private Circle[][] circles = new Circle[6][7];

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
		
		// Testing Connect4 methods
		Connect4 c4 = new Connect4();
		c4.displayBoard();
		c4.placeChip(0, c4.YELLOW);
		c4.placeChip(0, c4.RED);
		c4.displayBoard();
		
		
		
		
		Pane pane = new Pane(title, button,gridy);
		Scene scene = new Scene(pane, 500, 500);
		
		ps.setScene(scene);
		ps.setTitle("Game!!!");
		ps.show();
		
	}
	
	public void startGame(ActionEvent event) {
		test = new Connect4GameLogic();
		test.setUpGrid();//this goes back to the the specified class
		//calling the setUpGrid method

		idk = test.getGrid();//idk takes whatever there is from the
		//GameBoard array and idk is itself an array now with 
		//7 rows and 7 columns
		
		for(int x = 0; x < idk.length;x++) {
			for(int y = 0; y < 7; y++) {
				Circle c = new Circle(35);
				if(idk[x][y] == 0) {
					c.setFill(Color.RED);//ok this is setting up the 
					//color red value in the first row firt col basically
					//the first circle
					gridy.add(c, x, y );//this is also affected by the
					//if statement
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
