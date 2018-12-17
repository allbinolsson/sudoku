import javafx.scene.control.TextField;

public class Sudoku {
	SudokuViewer viewer = new SudokuViewer();
	private int[][] grid;
	private int index;

	// public static void main(String[] args) {
	// Sudoku s = new Sudoku();
	// s.solve();
	//
	// s.displaySudoku();
	// }

	/**
	 * Creates an empty sudoku board.
	 */
	public Sudoku() {
		grid = new int[9][9];
		index = 0;

		clear();
	}

	/**
	 * Enters a value at a specific field in the board.
	 * 
	 * @param r
	 *            specifies the row
	 * @param c
	 *            specifies the column
	 * @param value
	 *            determines the value to enter in field
	 */
	public void setField(int r, int c, int value) {
		grid[r][c] = value;
	}

	/**
	 * Gets the value entered at a specific field in board. 0 if the field is
	 * empty.
	 * 
	 * @param r
	 *            the row in board
	 * @param c
	 *            the column in board
	 * @return the value belonging to field at (r, c)
	 */
	public int getField(int r, int c) {
		return grid[r][c];
	}

	/**
	 * Clears the board of all entered values.
	 */
	public void clear() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				setField(i, j, index);
			}
		}
	}

	/**
	 * Tries to solve the sudoku based on the already entered values (if there
	 * are any).
	 * 
	 * @return true if sudoku was solved successfully, otherwise false.
	 */
	public boolean solveSudoku() {
		for (int i = 0; i < 9; i++) { // Loops through board
			for (int j = 0; j < 9; j++) {
				if (getField(i, j) != index && !ruleCheck(i, j, getField(i, j))) {
					System.out.println("The sudoku is unsolvable!");
					System.out.println("Field: " + i + ":" + j + "=" + getField(i, j));
					return false;
				}
			}
		}

		return solve(0, 0);
	}

	// This was used for debugging
	private void displaySudoku() {
		for (int i = 0; i < 9; i++) {
			if (i % 3 == 0 && i != 0) {
				System.out.println("-------------------------------\n");
			}
			for (int j = 0; j < 9; j++) {
				if (j % 3 == 0 && j != 0) {
					System.out.print(" | ");
				}
				System.out.print(" " + getField(i, j) + " ");
			}
			System.out.println();
		}
		System.out.println("\n\n______________________________________\n\n");
	}

	private boolean solve(int r, int c) {
		// for(int row = 0; row < 9; row++) { // Loop through rows
		// for(int col = 0; col < 9; col++) { // Loop through columns
		// if(getField(row, col) == 0) { // Checks if field is empty
		// for(int number = 1; number <= 9; number++) { // Loops through all the
		// valid numbers
		// if(ruleCheck(row, col, number)) { // Checks the rules for specified
		// number
		// setField(row, col, number); // If valid: Enter value to field.
		// if(solve()) { // Try to solve for next field that isn't filled
		// return true; // Return true if possible
		// } else {
		// setField(row, col, 0); // Else: Reset field and try another number.
		// }
		// }
		// }
		// return false; // If no number works: return false;
		// }
		//
		// }
		// }
		// return true; // All the fields are filled: return true!

		TextField[][] refBoard = viewer.getFields();
		int newR;
		int newC;

		if (c != 8) {
			newR = r;
			newC = c++;
		} else {
			newR = r++;
			newC = 0;
		}

		if (r < 9) {
			if (getField(r, c) == 0) { // Checks if field is empty
				for (int number = 1; number <= 9; number++) { // Loops through
																// all
																// the valid
																// numbers
					if (ruleCheck(r, c, number)) { // Checks the rules for
													// specified number
						setField(r, c, number); // If valid: Enter value to
												// field.
						if (solve(newR, newC)) { // Try to solve for next field
													// that isn't
							// filled
							return true; // Return true if possible
						} else {
							setField(r, c, 0); // Else: Reset field and try
												// another number.
						}
					}
				}
				return false; // If no number works: return false;
			}
		}

		return solve(newR, newC);
	}

	/**
	 * Checks all the rules of sudoku for a specified field in the board.
	 * 
	 * @param row
	 *            the row to check.
	 * @param col
	 *            the column to check
	 * @param value
	 *            the value to check for.
	 * @return true if the value is valid in specified field, otherwise false.
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
