package finalProject;

// Acts as the Connect4 game logic and board
public class Connect4 {
	
	// TODO consider making a chip class instead of numbers. Might be better or might not be
	public static final int EMPTY = 0;
	public static final int YELLOW = 1;
	public static final int RED = 2;
	
	private int[][] board;
	
	public Connect4() {
		board = new int[6][7];
	}
	
	// Returns the lowest EMPTY row index for the given column. Meant to aid in placing chips
	// returns -1 if the column is full
	private int getAvailableIndex(int col) {
		for (int i = board.length - 1; i >= 0; i--) {
			if (board[i][col] == EMPTY) {
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
	
	// Checks board from left to right and up to down. Finds the first instance of
	// a 4 in a row pattern and returns the chip color of the winner. Returns 0 if there are no winners
	public int checkWinner() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				if (board[r][c] != EMPTY) {
					int result = findWinningPattern(r, c, board[r][c], 0, 5);
					if (result != 0) {
						return result;
					}
				}
			}
		}
		return 0;
	}
	
	// Helper method for checkWinner()
	// takes a row, column, chip, chipInSequence, and direction.
	/*
	 * Recursively attempts to find a pattern of 4 chips in a row with the same color
	 * row: The row that the method will check
	 * col: The column that the method will check
	 * chip: Defines the chip color that we are searching for
	 * chipInSequence: The number of chips in a row that have been found. Stops after reaching 4
	 * direction: Defines the direction on the board that the program is searching. When 5 is given, checks all directions for a pattern
	 * 			  Starts at 4 because 1, 2, and 3 are not needed.
	 * 			  The value of direction can be thought of like the numbers you dial on a phone.
	 * 			  Imagine an arrow going from the middle to the number, that is the direction it searches:
	 * 			  1 2 3
	 * 			  4 * 6
	 * 			  7 8 9
	 */
	private int findWinningPattern(int row, int col, int chip, int chipInSequence, int direction) {
		if (chipInSequence == 4) {
			return chip;
		} else if (row == board.length || row < 0 ||  col == board[row].length || col < 0) {
			return 0;
		}
		
		if (board[row][col] == chip) {
			if (direction == 5) {
				for (int i = 4; i <= 9; i++) {
					if (i != 5 && findWinningPattern(row, col, chip, chipInSequence, i) == chip) {
						return chip;
					}
				}
			} else if (direction == 4) {
				return (findWinningPattern(row, --col, chip, ++chipInSequence, direction) == chip) ? chip:  0;			
			} else if (direction == 6) {
				return (findWinningPattern(row, ++col, chip, ++chipInSequence, direction) == chip) ? chip:  0;
			} else if (direction == 7) {
				return (findWinningPattern(++row, --col, chip, ++chipInSequence, direction) == chip) ? chip:  0;
			} else if (direction == 8) {
				return (findWinningPattern(++row, col, chip, ++chipInSequence, direction) == chip) ? chip:  0;
			} else if (direction == 9) {
				return (findWinningPattern(++row, ++col, chip, ++chipInSequence, direction) == chip) ? chip:  0;
			}			
		}
		
		return 0;
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
