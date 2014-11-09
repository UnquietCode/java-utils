package unquietcode.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Ben Fagin
 */
public class EnumComparator<T extends Enum<T>> implements Comparator<T> {
	private final List<T> elements;

	public EnumComparator(Class<T> clazz) {
		List<T> enums = Arrays.asList(clazz.getEnumConstants());

		Collections.sort(enums, new Comparator<T>() {
			public int compare(T o1, T o2) {
				return Integer.valueOf(o1.ordinal()).compareTo(o2.ordinal());
			}
		});

		this.elements = enums;
	}

	public static <T extends Enum<T>> Comparator<String> stringComparator(final Class<T> clazz) {
		final EnumComparator<T> enumComparator = new EnumComparator<T>(clazz);

		return new Comparator<String>() {
			public int compare(String o1, String o2) {
				T e1 = Enum.valueOf(clazz, o1);
				T e2 = Enum.valueOf(clazz, o2);
				return enumComparator.compare(e1, e2);
			}
		};
	}

	// ordered list of enums
	public EnumComparator(T...elements) {
		this.elements = Arrays.asList(elements);
	}

	@Override
	public int compare(T o1, T o2) {
		int i1 = elements.indexOf(o1);
		int i2 = elements.indexOf(o2);

		if (i1 == -1 && i2 == -1) {
			return 0;
		}

		if (i1 == -1 ^ i2 == -1) {
			throw new IllegalStateException("not enough ordering information");
		}

		return Integer.valueOf(i1).compareTo(i2);
	}
}
