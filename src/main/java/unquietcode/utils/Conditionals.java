package unquietcode.utils;

/**
 * @author Ben Fagin
 */
public class Conditionals {

	public static boolean any(Boolean...booleans) {
		if (booleans == null) { return false; }

		for (Boolean bool : booleans) {
			if (bool != null && bool) {
				return true;
			}
		}

		return false;
	}

	public static boolean none(Boolean...booleans) {
		return !any(booleans);
	}

	public static boolean all(Boolean...booleans) {
		if (booleans == null) { return false; }

		for (Boolean bool : booleans) {
			if (bool == null || !bool) {
				return false;
			}
		}

		return true;
	}

	public static <T> boolean equalsAll(T value, T...tests) {
		if (value == null) {
			for (T test : tests) {
				if (test != null) {
					return false;
				}
			}
		} else {
			for (T test : tests) {
				if (!value.equals(test)) {
					return false;
				}
			}
		}

		return true;
	}

	public static <T> boolean equalsAny(T value, T...tests) {
		if (value == null) {
			for (T test : tests) {
				if (test == null) {
					return true;
				}
			}
		} else {
			for (T test : tests) {
				if (value.equals(test)) {
					return true;
				}
			}
		}

		return false;
	}

	public static <T> boolean equalsNone(T value, T...tests) {
		return !equalsAny(value, tests);
	}

	public static boolean isInstanceOf(Object obj, Class<?> clazz) {
		return obj != null && clazz.isAssignableFrom(obj.getClass());
	}

	public static <T> boolean notEquals(T o1, T o2) {
		return !equals(o1, o2);
	}

	// simple equality check
	public static <T> boolean equals(T o1, T o2) {

		// both are null
		if (o1 == null && o2 == null) {
			return true;
		}

		// one is null
		if (o1 == null ^ o2 == null) {
			return false;
		}

		// neither are null, check equality
		return o1.equals(o2);
	}

	public static boolean not(Boolean test) {
		return !(test != null && test);
	}
}