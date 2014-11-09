package unquietcode.utils;

import java.util.Arrays;
import java.util.Collection;

public final class CollectionUtils {
	private CollectionUtils() { }


	public static String print(Collection<?> values) {
		return print("[", "]", ", ", values);
	}

	public static String print(Object...values) {
		return print("[", "]", ", ", values);
	}

	public static String print(String delimiter, Collection<?> values) {
		return print("", "", delimiter, values);
	}

	public static String print(String delimiter, Object...values) {
		return print("", "", delimiter, values);
	}

	public static String print(String prefix, String suffix, String delimiter, Object...values) {
		return print(prefix, suffix, delimiter, Arrays.asList(values));
	}

	public static String print(String prefix, String suffix, String delimiter, Collection<?> values) {
		StringBuilder sb = new StringBuilder();
		sb.append(prefix);

		boolean first = true;
		for (Object value : values) {
			if (first) {
				first = false;
			} else {
				sb.append(delimiter);
			}

			sb.append(value);
		}

		sb.append(suffix);
		return sb.toString();
	}
}