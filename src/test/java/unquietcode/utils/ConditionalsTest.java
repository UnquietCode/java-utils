package unquietcode.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author  Ben Fagin
 */
public class ConditionalsTest {

	@Test
	public void test_not() {
		assertTrue(Conditionals.not(false));
		assertTrue(Conditionals.not(null));
		assertFalse(Conditionals.not(true));
	}

	@Test
	public void test_any() {
		assertTrue(Conditionals.any(true, false));
		assertTrue(Conditionals.any(null, true));
		assertFalse(Conditionals.any(false));
		assertFalse(Conditionals.any(false, null));
		assertFalse(Conditionals.any(null));
	}
}