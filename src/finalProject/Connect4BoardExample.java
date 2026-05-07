package finalProject;

import java.util.Random;
// Just and example of how the Connect4 class can be used
// Also meant for testing methods if anybody want to edit the code
public class Connect4BoardExample {
	public static void main(String[] args) {
		Random rand = new Random();
		Connect4 c4 = new Connect4();
		c4.displayBoard();
		c4.placeChip(0, c4.YELLOW);
		c4.placeChip(0, c4.RED);
		c4.displayBoard();
		
		for (int i = 0; i < 20; i++) {
			c4.placeChip(rand.nextInt(0, 7), rand.nextInt(1, 3));
		}
		
		c4.displayBoard();
	}
}
