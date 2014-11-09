package unquietcode.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Ben Fagin
 * @version 2014-10-10
 */
public class StringUtilsTest {


	@Test
	public void testMatcher() {
		assertMatches("{{a}}", "a");
		assertMatches("{{a}} blah {{ b}}", "a", " b");
		assertMatches("\\{{a}}");
		assertMatches("{{a\\}}");
		assertMatches("\\{{a}}");
		assertMatches("{{a \\{{b}}", "a {{b");
		assertMatches("what {{a}} the {{b}} c}}", "a", "b");
		assertMatches("{{");
		assertMatches("}}");
		assertMatches("{{}}", "");
		assertMatches(" {{}} ", "");

		// nested is not supported
		//assertMatches("{{how {{do {{you}}}} do}}", "how {{do {{you}}}} do");
	}

	private static void assertMatches(String text, String...expected) {
		List<String> matches = match(text);
		assertEquals(expected.length, matches.size());

		for (int i = 0; i < expected.length; i++) {
			String s = expected[i];
			assertEquals(s, matches.get(i));
		}
	}

	private static List<String> match(String match) {
		List<String> matches = new ArrayList<>();
		StringUtils.match(match, "{{", "}}", matches::add);
		return matches;
	}
}
