package finalProject;

import java.util.Random;

// Just and example of how the Connect4 class can be used
// Also meant for testing methods if anybody want to edit the code
public class Connect4BoardExample {
	public static void main(String[] args) {
		Connect4 c4 = new Connect4();		
		
//		placeLeftDiagonal(c4);
//		placeRightDiagonal(c4);
//		placeVertLine(c4);
//		placeHorizLine(c4);
		randomGame(c4);
		
		c4.displayBoard();
		
		int winner = c4.checkWinner();
		if (winner == c4.YELLOW) {
			System.out.println("Yellow Wins!!!");
		} else if (winner == c4.RED) {
			System.out.println("Red Wins!!!");
		} else if (winner == 0) {
			System.out.println("Tie");
		} else {
			System.out.println("Nobody won");
		}
	}
	
	// Plays a game by placing chips in random columns. Stops when someone wins
	public static void randomGame(Connect4 c4) {
		while (c4.checkWinner() == -1) {
			c4.placeRandomChip();
		}
	}
	
	// Places a pattern of chips in a left diagonal
	public static void placeLeftDiagonal(Connect4 c4) {
		c4.placeChip(2, Connect4.YELLOW);
		c4.placeChip(1, Connect4.YELLOW);
		c4.placeChip(1, Connect4.YELLOW);
		c4.placeChip(0, Connect4.YELLOW);
		c4.placeChip(0, Connect4.YELLOW);
		c4.placeChip(0, Connect4.YELLOW);
		c4.placeChip(0, Connect4.RED);
		c4.placeChip(1, Connect4.RED);
		c4.placeChip(2, Connect4.RED);
		c4.placeChip(3, Connect4.RED);
	}
	
	// Places a pattern of chips in a right diagonal
	public static void placeRightDiagonal(Connect4 c4) {
		c4.placeChip(1, Connect4.YELLOW);
		c4.placeChip(2, Connect4.YELLOW);
		c4.placeChip(2, Connect4.YELLOW);
		c4.placeChip(3, Connect4.YELLOW);
		c4.placeChip(3, Connect4.YELLOW);
		c4.placeChip(3, Connect4.YELLOW);
		c4.placeChip(0, Connect4.RED);
		c4.placeChip(1, Connect4.RED);
		c4.placeChip(2, Connect4.RED);
		c4.placeChip(3, Connect4.RED);
	}
	
	// Places a pattern of chips in a vertical line
	public static void placeVertLine(Connect4 c4) {
		c4.placeChip(0, Connect4.RED);
		c4.placeChip(0, Connect4.RED);
		c4.placeChip(0, Connect4.RED);
		c4.placeChip(0, Connect4.RED);
	}
	
	// Places a pattern of chips in a horizontal line
	public static void placeHorizLine(Connect4 c4) {
		c4.placeChip(0, Connect4.RED);
		c4.placeChip(1, Connect4.RED);
		c4.placeChip(2, Connect4.RED);
		c4.placeChip(3, Connect4.RED);
	}
}
