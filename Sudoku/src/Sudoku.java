
public class Sudoku {
	private int[][] grid;
	private int index;
	
	public static void main (String[] args) {
		Sudoku s = new Sudoku();
		System.out.println(s.getField(0, 0));
	}
	
	public Sudoku () {
		grid = new int[9][9];
		index = 0;
	}
	
	public boolean setField (int r, int c, int value) {
		if (getField(r, c) != 0) {
			return false;
		} else {
			grid[r][c] = value;
			return true;
		}
	}
	
	public int getField (int r, int c) {
		return grid[r][c];
	}
	
	public void clear () {
		grid = new int[9][9];
	}
	
	public boolean solve () {
		return solve(0, 0);
	}
	
	private boolean solve (int r, int c) {
		if (r == 9) {		// This is outside the board, meaning all previous ones have worked
			return true;
		} else {
			for (int col = 0; col < 9; col++) {	// Loop through the columns
				int num = 1;	// Counter 1-9
				if (getField(r, col) == 0) {	// Checks if the current field is empty
					
				}
			}
		}
		
		return false;
	}
	
	
}
