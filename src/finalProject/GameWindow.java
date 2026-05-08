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

public class GameWindow extends Application{
	private int[][] board;
	private GridPane gridy;

	//this is for the players to interact with dropping chip for the game
    private int currentPlayer = Connect4.YELLOW;
    private Connect4 game = new Connect4();
    private Circle[][] circles = new Circle[6][7];
    private Label turnLabel = new Label("Yellow's Turn!");

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
		board = game.getBoard();//board takes whatever there is from the
		//GameBoard array and board is itself an array now with 
		//6 rows and 7 columns
		
		for(int x = 0; x < board.length;x++) {
			for(int y = 0; y < 7; y++) {
				Circle c = new Circle(35);
				if(board[x][y] == 0) {
					c.setFill(Color.RED);//ok this is setting up the 
					//color red value in the first row firt col basically
					//the first circle
					gridy.add(c, x, y );//this is also affected by the
					//if statement
				}else if(board[x][y] == 1) {
					c.setFill(Color.YELLOW);
					gridy.add(c, x, y );
				}
			}
		}
	}
	
	// checks if someone won and updates the label
	public void checkWin() {
	    int winner = game.checkWinner();
	    if (winner == Connect4.YELLOW) {
	        turnLabel.setText("Yellow Wins!");
	        
	    } else if (winner == Connect4.RED) {
	        turnLabel.setText("Red Wins!");
	    }
	}

	public static void main(String[] args) {
		launch(args);
	}
}
