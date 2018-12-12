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
		assertTrue("Can't solve empty sudoku", sudoku.solve());
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertTrue("Board isn't filled", sudoku.getField(i, j) != 0);
			}
		}
	}

//	@Test
//	public void testUnsolvable() {
//		sudoku.setField(0, 0, 1);
//		sudoku.setField(0, 1, 1);
//		assertFalse("Impossible sudoku was 'solved'", sudoku.solve());
//	}

	@Test
	public void testSolve() {
		sudoku.setField(0, 0, 1);
		sudoku.setField(0, 1, 4);
		sudoku.setField(8, 8, 1);
		
		assertTrue("Sudoku can't be solved", sudoku.solve());
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
