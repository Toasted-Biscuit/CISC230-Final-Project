package finalProject;

public class Connect4GameLogic {
	private int[][] GameBoard;
	private int a;

	
	public void setUpGrid() {
		GameBoard = new int[7][7];
		for(int x = 0; x < GameBoard.length;x++) {
			for(int y = 0; y < 7; y++) {
				GameBoard[x][y] = 1;
			}
		}
		
		for(int x = 0; x < GameBoard.length;x++) {
			for(int y = 0; y < 7; y++) {
				System.out.print(GameBoard[x][y]); 
				}
			System.out.println();		
			}
	}
	
	public int[][] getGrid(){
		return GameBoard;
	}
	
}
