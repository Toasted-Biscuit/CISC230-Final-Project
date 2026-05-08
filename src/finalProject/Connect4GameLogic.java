package finalProject;

public class Connect4GameLogic {
	private int[][] GameBoard;
	private int a;

	
	public void setUpGrid() {
		GameBoard = new int[6][7];
		for(int x = 0; x < GameBoard.length;x++) {
			for(int y = 0; y < 7; y++) {
				GameBoard[x][y] = 0;//I think when the game starts
				//here the grids should all be zero right first
			}
		}
		
		for(int x = 0; x < GameBoard.length;x++) {//ok here the 
			//GameBoard has 7 rows and 7 columns 
			for(int y = 0; y < 7; y++) {
				System.out.print(GameBoard[x][y]); 
				}
			System.out.println();		
			}
	}
	
	public int[][] getGrid(){
		return GameBoard;//returining the array
	}
	
}
