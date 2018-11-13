
public class Sudoku {
	private int[][] grid;
	
	public Sudoku () {
		grid = new int[9][9];
	}
	
	public void setField (int x, int y, int value) {
		grid[x][y] = value;
	}
	
	public int getField (int x, int y) {
		return grid[x][y];
	}
}
