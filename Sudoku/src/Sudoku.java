
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
	
	/**
	 * Returns true if a value is successfully added to a field on r, c.
	 * 
	 * @param r the row to alter
	 * @param c the column to alter
	 * @param value the value to assign the chosen field
	 * @return true if a value was successfully added, false otherwise
	 */
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
		return false;
	}
	
	// Checks all three rules
	private boolean ruleCheck (int row, int col, int value) {
		return (checkRow(row, col, value) && checkColumn(row, col, value) && checkArea(row, col, value));
	}
	
	// This will return true if the specified value fits to the row.
	private boolean checkRow (int row, int col, int value) {
		for (int i = 0; i < 9; i++) {						// Loops through the row
			if (col != i && getField(row, i) == value) {	// Doesn't check itself
				return false;								// Checks if the value is in the row
			}
		}
		
		grid[row][col] = value;		// Otherwise, assign the value and return true
		return true;
	}
	
	// This will return true if the specified value fits to the column
	private boolean checkColumn (int row, int col, int value) {
		for (int i = 0; i < 9; i++) {						// Loops through the row
			if (row != i && getField(i, col) == value) {	// Doesn't check itself
				return false;								// Checks if the value is in the row
			}
		}
		
		grid[row][col] = value;		// Otherwise, assign the value and return true
		return true;
	}
	
	// This will return true if the specified value fits to the current area
	private boolean checkArea (int row, int col, int value) {
		int rowMin = row - (row % 3);	// 0 <= (row % 3) <= 2 
		int rowMax = rowMin + 3;
		int colMin = col - (col % 3);	// 0 <= (col % 3) <= 2
		int colMax = colMin + 3;
		
		for (int i = rowMin; i < rowMax; i++) {						// Loops within row-area
			for (int j = colMin; i < colMax; i++) {					// Loops within column-area
				if (row != i && col != j && grid[i][j] == value) {	// Checks for values and coordinates
					return false;
				}
			}
		}
		
		return true;
	}
}
