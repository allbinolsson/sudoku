import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

public class SukdokuTest {

	private Sudoku sudoku;

	@Before
	public void setUp() {
		sudoku = new Sudoku();
	}

	@After
	public void tearDown() {
		sudoku.clear();
	}

	@Test
	public void testSolveEmpty() {
		assertTrue("Can't solve empty sudoku", sudoku.solveSudoku());
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertTrue("Board isn't filled", sudoku.getField(i, j) != 0);
			}
		}
	}

	@Test
	public void testUnsolveable () {
		sudoku.setField(0, 0, 1);	// This will add two 1's in first row
		sudoku.setField(0, 1, 1);
		assertFalse("Unsolvable sudoku was 'solved'", sudoku.solveSudoku());
		sudoku.clear();
		
		sudoku.setField(0, 0, 1);	// This will add two 1's in first column
		sudoku.setField(1, 0, 1);
		assertFalse("Unsolvable sudoku was 'solved'", sudoku.solveSudoku());
		sudoku.clear();
		
		sudoku.setField(0, 0, 1);	// This will add two 1's in first area
		sudoku.setField(2, 2, 1);
		assertFalse("Unsolvable sudoku was 'solved'", sudoku.solveSudoku());
		sudoku.clear();
	}

	@Test
	public void testSolve() {
		sudoku.setField(0, 0, 1);
		sudoku.setField(0, 1, 4);
		sudoku.setField(8, 8, 1);
		
		assertTrue("Sudoku can't be solved", sudoku.solveSudoku());
	}

	@Test
	public void testSpecificUnsolvable () {
		sudoku.setField(0, 0, 1);
		sudoku.setField(0, 1, 2);
		sudoku.setField(0, 2, 3);
		sudoku.setField(1, 0, 4);
		sudoku.setField(1, 1, 5);
		sudoku.setField(1, 2, 6);
		sudoku.setField(2, 3, 7);
		
		assertFalse ("Specific unsolvable sudoku was 'solved'", sudoku.solveSudoku());
	}
	
	@Test
	public void testSpecificSolve () {
		sudoku.setField(0, 2, 8);
		sudoku.setField(0, 5, 9);
		sudoku.setField(0, 7, 6);
		sudoku.setField(0, 8, 2);
		
		sudoku.setField(1, 8, 5);
		
		sudoku.setField(2, 0, 1);
		sudoku.setField(2, 2, 2);
		sudoku.setField(2, 3, 5);
		
		sudoku.setField(3, 3, 2);
		sudoku.setField(3, 4, 1);
		sudoku.setField(3, 7, 9);
		
		sudoku.setField(4, 1, 5);
		sudoku.setField(4, 6, 6);
		
		sudoku.setField(5, 0, 6);
		sudoku.setField(5, 7, 2);
		sudoku.setField(5, 8, 8);
		
		sudoku.setField(6, 0, 4);
		sudoku.setField(6, 1, 1);
		sudoku.setField(6, 3, 6);
		sudoku.setField(6, 5, 8);
		
		sudoku.setField(7, 0, 8);
		sudoku.setField(7, 1, 6);
		sudoku.setField(7, 4, 3);
		sudoku.setField(7, 6, 1);
		
		sudoku.setField(8, 6, 4);
		
		assertTrue("Specific solveable sudoku wasn't solved", sudoku.solveSudoku());
	}
	
	@Test
	public void testClear() {
		
		sudoku.setField(0, 0, 1);
		sudoku.setField(0, 8, 9);
		sudoku.setField(4, 5, 3);
		sudoku.setField(2, 7, 8);
		sudoku.setField(1, 8, 2);
		
		sudoku.clear();
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertTrue("Doesn't clear properly", sudoku.getField(i, j) == 0);
			}
		}
	}

	@Test
	public void testField() {
		assertTrue("Can't get field", sudoku.getField(0, 0) == 0);
		sudoku.setField(0, 0, 1);
		assertTrue("Can't set field properly", sudoku.getField(0, 0) == 1);
	}

	@Test
	public void testRulesRow() {
		sudoku.setField(0, 0, 1);
		assertFalse("Row rulecheck failed", sudoku.ruleCheck(0, 7, 1));
	}

	@Test
	public void testRulesCol() {
		sudoku.setField(0, 0, 1);
		assertFalse("Column rulecheck failed", sudoku.ruleCheck(7, 0, 1));
	}

	@Test
	public void testRulesArea() {
		sudoku.setField(0, 0, 1);
		assertFalse("Area rulecheck failed", sudoku.ruleCheck(2, 2, 1));
	}
}
