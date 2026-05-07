package finalProject;

// Acts as the Connect4 game logic and board
public class Connect4 {
	
	// TODO consider making a chip class instead of numbers. Might be better or might not be
	public final int EMPTY = 0;
	public final int YELLOW = 1;
	public final int RED = 2;
	
	private int[][] board;
	
	public Connect4() {
		board = new int[6][7];
	}
	
	// Returns the lowest EMPTY row index for the given column. Meant to aid in placing chips
	// returns -1 if the column is full
	private int getAvailableIndex(int col) {
		for (int i = board.length - 1; i >= 0; i--) {
			if (board[i][col] == 0) {
				return i;
			}
		}
		return -1;
	}
	
	// Places a given chip color in a given column
	// pass constants YELLOW and RED for chip
	public void placeChip(int col, int chip) {
		int row = getAvailableIndex(col);
		
		if (row != -1) {
			board[row][col] = chip;
		} else {
			System.out.println("Attempted to place chip in full column");
		}
	}
	
	// Displays the game board in the console. Meant for testing and debugging
	public void displayBoard() {
		System.out.println("Current Board:");
		
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				System.out.print(board[r][c] + " ");
			}
			System.out.println();
		}
	}
	
}
