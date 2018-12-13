
public class Sudoku {
	private int[][] grid;
	private int index;

	public static void main(String[] args) {
		Sudoku s = new Sudoku();
		s.solve();
		
		s.displaySudoku();
	}

	/**
	 * 
	 */
	public Sudoku() {
		grid = new int[9][9];
		index = 0;
		
		clear();
	}

	/**
	 * 
	 * @param r
	 * @param c
	 * @param value
	 */
	public void setField(int r, int c, int value) {
			grid[r][c] = value;
	}
	
	/**
	 * 
	 * @param r
	 * @param c
	 * @return
	 */
	public int getField(int r, int c) {
		return grid[r][c];
	}

	/**
	 * 
	 */
	public void clear() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				setField(i, j, index);
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean solveSudoku() {
		for (int i = 0; i < 9; i++) {		// Loops through board
			for (int j = 0; j < 9; j++) {
				if (getField(i, j) != index && !ruleCheck(i, j, getField(i, j))) {
					System.out.println("The sudoku is unsolvable!");
					System.out.println("Field: " + i + ":" + j + "=" + getField(i, j));
					return false;
				}
			}
		}
		
		return solve();
	}

	// This was used for debugging
	private void displaySudoku() {
		for(int i = 0; i < 9; i++) {
			if(i % 3 == 0 && i != 0) {
				System.out.println("-------------------------------\n");
			}
			for(int j = 0; j < 9; j++) {
				if(j % 3 == 0 && j != 0) {
					System.out.print(" | ");
				}
				System.out.print(" " + getField(i, j) + " ");
			}
			System.out.println();
		}
		System.out.println("\n\n______________________________________\n\n");
	}

	private boolean solve() {
		for(int row = 0; row < 9; row++) {				// Loop through rows
			for(int col = 0; col < 9; col++) {			// Loop through columns
				if(getField(row, col) == 0) {			// Checks if field is empty
					for(int number = 1; number <= 9; number++) {	// Loops through all the valid numbers
						if(ruleCheck(row, col, number)) {			// Checks the rules for specified number
							setField(row, col, number);				// If valid: Enter value to field.
							if(solve()) {				// Try to solve for next field that isn't filled
								return true;			// Return true if possible
							} else {
								setField(row, col, 0);	// Else: Reset field and try another number.
							}
						}
					}
					return false;		// If no number works: return false;
				}

			}
		}
		return true;		// All the fields are filled: return true!
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @param value
	 * @return
	 */
	public boolean ruleCheck(int row, int col, int value) {
		return (checkRow(row, col, value) && checkColumn(row, col, value) && checkArea(row, col, value));
	}

	// This will return true if the specified value fits to the row.
	private boolean checkRow(int row, int col, int value) {
		for (int i = 0; i < 9; i++) { // Loops through the row
			if (col != i && getField(row, i) == value) { // Doesn't check itself
				return false; // Checks if the value is in the row
			}
		}

		// grid[row][col] = value; // Otherwise, assign the value and return
		// true
		return true;
	}

	// This will return true if the specified value fits to the column
	private boolean checkColumn(int row, int col, int value) {
		for (int i = 0; i < 9; i++) { // Loops through the row
			if (row != i && getField(i, col) == value) { // Doesn't check itself
				return false; // Checks if the value is in the row
			}
		}

		// grid[row][col] = value; // Otherwise, assign the value and return
		// true
		return true;
	}

	// This will return true if the specified value fits to the current area
	private boolean checkArea(int row, int col, int value) {
		int rowMin = row - (row % 3); // 0 <= (row % 3) <= 2
		int rowMax = rowMin + 3;
		int colMin = col - (col % 3); // 0 <= (col % 3) <= 2
		int colMax = colMin + 3;

		for (int i = rowMin; i < rowMax; i++) { // Loops within row-area
			for (int j = colMin; j < colMax; j++) { // Loops within column-area

				if (row != i && col != j && grid[i][j] == value) { // Checks for
																	// values
																	// and
																	// coordinates
					return false;
				}
			}
		}

		return true;
	}
}
