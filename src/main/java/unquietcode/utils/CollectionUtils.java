package unquietcode.utils;

import java.util.*;

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

	public static <T> Set<T> randomSubset(Set<T> set, int count, Set<T> target) {
		List<T> list = new LinkedList<>(set);
		Collections.shuffle(list);

		int end = count <= list.size() ? count : list.size();
		target.addAll(list.subList(0, end));

		return target;
	}

	/**
 	* null-safe collection reference
 	*/
	public static <T> Collection<T> safe(Collection<T> collection) {
		return collection != null ? collection : Collections.<T>emptyList();
	}

	public static <T> List<T> safe(List<T> list) {
		return list != null ? list : Collections.<T>emptyList();
	}

	public static <T> List<T> safe(T[] list) {
		return list != null ? Arrays.asList(list) : Collections.<T>emptyList();
	}
}