import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

class SukdokuTest {

	private Sudoku sudoku;
	
	@BeforeEach
	public void setUp () {
		sudoku = new Sudoku();
	}
	
	@AfterEach
	public void tearDown () {
		
	}
	
	@Test
	public void testSolveEmpty () {
		assertTrue(sudoku.solve(), "Can't slve empty sudoku");
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
		
		assertTrue (equals, "Doesn't clear sudoku properly");
	}
	
	@Test
	public void testSetField () {
		assertTrue(sudoku.setField(0, 0, 5));
		assertTrue(sudoku.getField(0, 0) == 5);
	}
	
	@Test
	public void testRulesRow () {
		sudoku.setField(0, 0, 1);
		assertFalse(sudoku.ruleCheck(0, 7, 1), "Row rulecheck failed");
	}
	
	@Test
	public void testRulesCol () {
		sudoku.setField(0, 0, 1);
		assertFalse(sudoku.ruleCheck(7, 0, 1), "Column rulecheck failed");
	}
	
	@Test public void testRulesArea () {
		sudoku.setField(0, 0, 1);
		assertFalse(sudoku.ruleCheck(2, 2, 1), "Area rulecheck failed");
	}
}
