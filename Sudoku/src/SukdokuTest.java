import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

public class SukdokuTest {

	private Sudoku sudoku;
	
	@Before
	public void setUp () {
		sudoku = new Sudoku();
	}
	
	@After
	public void tearDown () {
		
	}
	
	@Test
	public void testSolveEmpty () {
		assertTrue("Can't solve empty sudoku", sudoku.solve());
	}
	
	@Test
	public void testUnsolvable () {
		
	}
	
	@Test
	public void testSolve () {
		
	}
	
	@Test
	public void testClear () {
		boolean equals = true;
		Sudoku s = new Sudoku();
		s.setField(0, 0, 5);
		s.clear();
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (sudoku.getField(i, j) != 0 || s.getField(i, j) != 0) {
					equals = false;
				}
			}
		}
		
		assertTrue ("Doesn't clear sudoku properly", equals);
	}
	
	@Test
	public void testField () {
		assertTrue("Can't enter value to field", sudoku.setField(0, 0, 5));
		assertTrue("Can't get value from field", sudoku.getField(0, 0) == 5);
	}
	
	@Test
	public void testRulesRow () {
		sudoku.setField(0, 0, 1);
		assertFalse("Row rulecheck failed", sudoku.ruleCheck(0, 7, 1));
	}
	
	@Test
	public void testRulesCol () {
		sudoku.setField(0, 0, 1);
		assertFalse("Column rulecheck failed", sudoku.ruleCheck(7, 0, 1));
	}
	
	@Test public void testRulesArea () {
		sudoku.setField(0, 0, 1);
		assertFalse("Area rulecheck failed", sudoku.ruleCheck(2, 2, 1));
	}
}
