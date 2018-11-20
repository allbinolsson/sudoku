
public class Sudoku {
	private int[][] grid;
	private int index;

	public static void main(String[] args) {
		Sudoku s = new Sudoku();
		System.out.println(s.getField(0, 0));
	}

	public Sudoku() {
		grid = new int[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				grid[i][j] = 0;
			}
		}

		index = 0;
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
	public boolean setField(int r, int c, int value) {
		if (getField(r, c) != 0) {
			return false;
		} else {
			grid[r][c] = value;
			return true;
		}
	}

	public int getField(int r, int c) {
		return grid[r][c];
	}

	public void clear() {
		grid = new int[9][9];
	}

	public boolean solve() {
		return solve(0, 0);
	}

	private void print(int r, int c, int value) {
		System.out.println(r + ", " + c + ": " + value);
	}

	private boolean solve(int r, int c) {

		return true;	// Temporary

		// if (r == 8 && c == 8) { // If we reach the lower right corner of the board
		// the sudoku is solved.
		// return true;
		// } else {
		// for (int i = 1; i <= 9; i++) { // Loops through values 1-9 to insert in to
		// the sudoku
		//
		// if (grid[r][c] != 0 && ruleCheck(r, c, grid[r][c])) {
		// if (c < 8) {
		// return solve(r, c + 1);
		// } else if (r < 8) {
		// return solve (r + 1, c);
		// }
		//
		// } else if (grid[r][c] == 0 && ruleCheck(r, c, i)) { // Makes sure the value
		// is valid for the specified field
		// if (c < 8) {
		// grid[r][c] = i; // If the value is valid and we are inside the field, add
		// value i.
		// return solve(r, c + 1); // Increase column value until we reach 8, at which
		// point we reset to one and increase row.
		// } else if (r < 8) {
		// grid[r][c] = i; //If the value is valid and we are inside field, add value i.
		// return solve(r + 1, 0); // Increase row value until we reach 8
		// }
		// }
		// }
		//
		// if (grid[r][c] == 0) { // If the field contains 0, no number fit the rules
		// return false; // Thus, we return false
		// }
		// }
		//
		// return false; // If no call for solve returns true we can't find a solution
	}

	// Checks all three rules
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

		// grid[row][col] = value; // Otherwise, assign the value and return true
		return true;
	}

	// This will return true if the specified value fits to the column
	private boolean checkColumn(int row, int col, int value) {
		for (int i = 0; i < 9; i++) { // Loops through the row
			if (row != i && getField(i, col) == value) { // Doesn't check itself
				return false; // Checks if the value is in the row
			}
		}

		// grid[row][col] = value; // Otherwise, assign the value and return true
		return true;
	}

	// This will return true if the specified value fits to the current area
	private boolean checkArea(int row, int col, int value) {
		int rowMin = row - (row % 3); // 0 <= (row % 3) <= 2
		int rowMax = rowMin + 3;
		int colMin = col - (col % 3); // 0 <= (col % 3) <= 2
		int colMax = colMin + 3;

		for (int i = rowMin; i < rowMax; i++) { // Loops within row-area
			for (int j = colMin; i < colMax; i++) { // Loops within column-area
				if (row != i && col != j && grid[i][j] == value) { // Checks for values and coordinates
					return false;
				}
			}
		}

		return true;
	}
}
