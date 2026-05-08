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
		randomGame(c4, 20);
		
		c4.displayBoard();
		
		System.out.println(c4.checkWinner());
		
	}
	
	// Plays a game by placing chips in random columns. Stops when someone wins
	public static void randomGame(Connect4 c4, int rounds) {
		Random rand = new Random();
		int turn = Connect4.YELLOW;
		while (c4.checkWinner() == 0 && rounds > 0) {
			c4.placeChip(rand.nextInt(0, 7), turn);
			
			if (turn == Connect4.YELLOW) {
				turn = Connect4.RED;
			} else {
				turn = Connect4.YELLOW;
			}
			rounds--;
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
