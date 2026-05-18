package finalProject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;

// Acts as the Connect4 game logic and board
public class Connect4 extends Leaderboardable {
	
	// TODO consider making a chip class instead of numbers. Might be better or might not be
	public static final int EMPTY = 0;
	public static final int YELLOW = 1;
	public static final int RED = 2;
	
	private int turn; // 0 = Yellow, 1 = Red
	private int totalTurns;
	private int[][] board;
	
	public Connect4() {
		board = new int[6][7]; // Default values of array is 0
		turn = 1;
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
	
	// Places a chip in the given column. Chip is determined using the current turn
	public boolean placeChip(int col) {
		int row = getAvailableIndex(col);
		
		if (row != -1) {
			board[row][col] = turn;
			changeTurn();
			return true;
		} else {
			System.out.println("Attempted to place chip in full column");
			return false;
		}
	}
	
	/*
	 * Places a given chip in a given column
	 * Pass Constants YELLOW and RED for chip
	 * Not meant to be used in actual gameplay, JUST FOR TESTING
	 */
	public void placeChip(int col, int chip) {
		int row = getAvailableIndex(col);
		
		if (row != -1) {
			board[row][col] = chip;
		} else {
			System.out.println("Attempted to place chip in full column");
		}
	}
	
	// Places a random chip in an open space
	public void placeRandomChip() {
		// Didn't feel like importing these for just 1 method
		java.util.Random rand = new java.util.Random();
		java.util.ArrayList<Integer> openCols = new java.util.ArrayList<Integer>();
		
		// Gets all available columns and adds it to an ArrayList
		for (int i = 0; i < board[0].length; i++) {
			if (getAvailableIndex(i) != -1) {
				openCols.add(i);
			}
		}
		
		if (openCols.size() > 0) {
			// Picks a random open column
			placeChip(openCols.get(rand.nextInt(0, openCols.size())));			
		} else {
			System.out.println("No empty columns to add random chip to");
		}
	}
	
	// Checks board from left to right and up to down. Finds the first instance of
	// a 4 in a row pattern and returns the chip color of the winner. Returns -1 if there are no winners
	// Returns 0 if the board is full with no winners (a tie)
	public int checkWinner() {
		boolean roomOnBoard = false;
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				if (board[r][c] != EMPTY) {
					// Checks if anybody wins. If so, return that color
					int result = findWinningPattern(r, c, board[r][c], 0, 5);
					if (result != 0) {
						return result;
					}
				} else {
					roomOnBoard = true;
				}
			}
		}
		
		// If there are no 0s on the board with no winners, the game is a tie
		if (!roomOnBoard) {
			return 0;
		}
		
		// No winners and room on board still
		return -1;
	}
	
	// Sets the turn to the next player
	public void changeTurn() {
		turn = (turn == YELLOW) ? RED : YELLOW;
		totalTurns++;
	}
	
	public int getTurn() {
		return turn;
	}
	
	/* Helper method for checkWinner()
	 * Recursively attempts to find a pattern of 4 chips in a row with the same color.
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
		// Stopping conditions
		if (chipInSequence == 4) {
			return chip;
		} else if (row == board.length || row < 0 ||  col == board[row].length || col < 0) {
			return 0;
		}
		
		// Pattern checking
		if (board[row][col] == chip) {
			// Base direction, checks all other directions to see if they have a pattern
			if (direction == 5) {
				for (int i = 4; i <= 9; i++) {
					if (i != 5 && findWinningPattern(row, col, chip, chipInSequence, i) == chip) {
						return chip;
					}
				}
				
			// Checks if there are sequential chips of the same color in a direction
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
	
	/*
	 * Writes a new name to the leaderboard text file and reorders it to go from best score to worse score
	 */
	public boolean writeLeaderboard(String name) {
		try {
			// Name can't have : because it messes with some logic
			if (name.contains(":")) {
				throw new InputMismatchException();
			}
			
			File leaderFile = new File("Leaderboard.txt");
			leaderFile.createNewFile();
			Scanner scan = new Scanner(leaderFile);
			
			// Creates an arrayList and adds all current leaderboard entries
			java.util.ArrayList<String> leaderboard = new java.util.ArrayList<String>();
			while (scan.hasNextLine()) {
				leaderboard.add(scan.nextLine());	
			}
			
			// Adds newest leaderboard entry using given name
			leaderboard.add(name + ": " + totalTurns);
			
			// Creates an array of scores storing the total turns for each leaderboard entry
			int[] scores = new int[leaderboard.size()];
			for (int i = 0; i < leaderboard.size(); i++) {
				String s = leaderboard.get(i);
				scores[i] = Integer.parseInt(s.substring(s.indexOf(':') + 2));
			}
			
			// Reorders the leaderboard arraylist using the scores to go from best score to worst score
			// Bubble sort YAY!!!!
			bubbleSort(leaderboard, scores);
			
			// Writes the newly ordered leaderboard to the leaderboard file
			FileWriter fp = new FileWriter(leaderFile);
			for (String s : leaderboard) {
				fp.append(s + "\n");
			}
			fp.close();
			return true;
		// Exception handling
		} catch (NumberFormatException e) {
			System.out.println("Error: Cannot find score in leaderboard data");
		} catch (InputMismatchException e) {
			System.out.println("Error: Name cannot contain \":\"");
		} catch (IOException e) {
			System.out.println("Error: Cannot access leaderboard");
		}
		return false;
	}
	
	// Returns the board
	public int[][] getBoard() {
		return board;
	}
}
