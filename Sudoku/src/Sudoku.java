
public class Sudoku {
	private int[][] grid;
	private int index;

	public static void main(String[] args) {
		Sudoku s = new Sudoku();
		s.solve();
		
//		for (int i = 0; i < 9; i++) {
//			for (int j = 0; j < 9; j++) {
//				s.print(i, j, s.getField(i, j));
//			}
//		}
		s.displaySudoku();
	}

	public Sudoku() {
		grid = new int[9][9];
		index = 0;
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				setField(i, j, 0);
			}
		}
	}

	/**
	 * Returns true if a value is successfully added to a field on r, c.
	 * 
	 * @param r
	 *            the row to alter
	 * @param c
	 *            the column to alter
	 * @param value
	 *            the value to assign the chosen field
	 * @return true if a value was successfully added, false otherwise
	 */
	public void setField(int r, int c, int value) {
			grid[r][c] = value;
	}

	public int getField(int r, int c) {
		return grid[r][c];
	}

	public void clear() {
		grid = new int[9][9];
	}

//	public boolean solve() {
//		return solve(0, 0);
//	}

	private void print(int r, int c, int value) {
		System.out.println(r + ", " + c + " : " + value);
	}
	
	public void displaySudoku() {
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
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				if(getField(row, col) == 0) {
					for(int number = 1; number <= 9; number++) {
						if(ruleCheck(row, col, number)) {
							setField(row, col, number);
							if(solve()) {
								return true;
							} else {
								setField(row, col, 0);
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	// Checks all three rules
	public boolean ruleCheck(int row, int col, int value) {
		return (checkRow(row, col, value) && checkColumn(row, col, value) && checkArea(row, col, value));
	}

	// This will return true if the specified value fits to the row.
	private boolean checkRow(int row, int col, int value) {
		for (int i = 0; i < 9; i++) { // Loops through the row
			if (getField(row, i) == value) { // Doesn't check itself
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
			if (getField(i, col) == value) { // Doesn't check itself
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
