package finalProject;

import java.util.ArrayList;

// Abstract class meant to have methods pertaining to writing a leaderboard
public abstract class Leaderboardable {

	public abstract boolean writeLeaderboard(String name);
	
	// Sorts all values of a leaderboard ArrayList using the corresponding scores array.
	// Sorts from lowest to highest
	protected void bubbleSort(ArrayList<String> leaderboard, int[] scores) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < scores.length - 1; i++) {
            	if (scores[i] > scores[i + 1]) {
            		swap(i, i+1, leaderboard, scores);
            		isSorted = false;
            	}
            }
        }
    }
	
	// Swaps 2 values of a given leaderboard and corresponding scores array
	private void swap(int index1, int index2, ArrayList<String> leaderboard, int[] scores) {
		String sTemp1 = leaderboard.get(index1);
		String sTemp2 = leaderboard.set(index2, sTemp1);
		leaderboard.set(index1, sTemp2);
		
		int iTemp1 = scores[index1];
		int iTemp2 = scores[index2];
		scores[index1] = iTemp2;
		scores[index2] = iTemp1;
		
	}
}
