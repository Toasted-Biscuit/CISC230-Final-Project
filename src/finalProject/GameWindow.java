package finalProject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GameWindow extends Application {
    private Circle[][] circles = new Circle[6][7];
    private Connect4 game = new Connect4();
    private Label turnLabel;
    
    public void start(Stage ps) {
        GridPane root = new GridPane();

        root.setHgap(10);
        root.setVgap(10);

        Label title = new Label("Connect 4");
        turnLabel = new Label("Yellow's Turn");
        Label messageLabel = new Label("Game messages will appear here.");

        Button startButton = new Button("Start Game");
        Button questionButton = new Button("Question");

        GridPane buttonRow = new GridPane();
        buttonRow.setHgap(20);
        
        // An array full of buttons meant to place chips when pressed
        Button[] chipButtons = new Button[6];
        for (int i = 0; i < 6; i++) {
        	chipButtons[i] = new Button("" + i);
        	chipButtons[i].setUserData(i);
        	chipButtons[i].setOnAction(this::placeChip);
        	buttonRow.add(chipButtons[i], i, 0);
        }

        GridPane boardGrid = new GridPane();
        boardGrid.setHgap(5);
        boardGrid.setVgap(5);

        for (int row = 0; row < circles.length; row++) {
            for (int col = 0; col < circles[row].length; col++) {
                Circle c = new Circle(25);
                c.setFill(Color.WHITE);
                c.setStroke(Color.BLACK);

                circles[row][col] = c;

                boardGrid.add(c, col, row);
            }
        }

        GridPane questionArea = new GridPane();
        questionArea.setHgap(10);
        questionArea.setVgap(10);

        Label questionTitle = new Label("Question Area");
        Label questionText = new Label("Question text will go here.");

        Button answerA = new Button("Answer A");
        Button answerB = new Button("Answer B");
        Button answerC = new Button("Answer C");
        Button answerD = new Button("Answer D");

        questionArea.add(questionTitle, 0, 0);
        questionArea.add(questionText, 0, 1);
        questionArea.add(answerA, 0, 2);
        questionArea.add(answerB, 1, 2);
        questionArea.add(answerC, 0, 3);
        questionArea.add(answerD, 1, 3);

        root.add(title, 0, 0);
        root.add(startButton, 1, 0);
        root.add(questionButton, 2, 0);

        root.add(turnLabel, 0, 1);
        root.add(buttonRow, 0, 2);
        root.add(boardGrid, 0, 3);
        root.add(messageLabel, 0, 4);
        root.add(questionArea, 0, 5);

        Scene scene = new Scene(root, 650, 650);

        ps.setTitle("Connect 4");
        ps.setScene(scene);
        ps.show();
    }
    
    // When the chip buttons are pressed, places a chip in the column assigned to the button
    public void placeChip(ActionEvent e) {
    	int col = (int)((Button)e.getSource()).getUserData();
    	game.placeChip(col);
    	updateBoard();
    	
    	if (game.getTurn() == 1) {
    		turnLabel.setText("Yellow's Turn");
    	} else {
    		turnLabel.setText("Red's Turn");
    	}
    	
    	int winner = game.checkWinner();
    	// TODO Replace println with gui visuals and make it stop the game
    	if (winner == Connect4.YELLOW) {
    		System.out.println("Yellow Wins!!!");
    	} else if (winner == Connect4.RED) {
    		System.out.println("Red Wins!!!");
    	}
    }
    
    // Updates the onscreen board to reflect the game board
    public void updateBoard() {
    	int[][] board = game.getBoard();
    	
    	for (int r = 0; r < board.length; r++) {
    		for (int c = 0; c < board[r].length; c++) {
    			if (board[r][c] == Connect4.YELLOW) {
    				circles[r][c].setFill(Color.YELLOW);
    			} else if (board[r][c] == Connect4.RED) {
    				circles[r][c].setFill(Color.RED);
    			} else {
    				circles[r][c].setFill(Color.WHITE);
    			}
    		}
    	}
    }

    public static void main(String[] args) {
        launch(args);
    }
}