package unquietcode.utils;

import java.util.function.Consumer;

/**
 * @author Ben Fagin
 */
public class StringUtils {

	public static String reverse(String string) {
		StringBuilder sb = new StringBuilder(string);
		sb = sb.reverse();
		return sb.toString();
	}

	public static boolean startsWithAny(String string, String...prefixes) {
		for (String prefix : prefixes)
			if (string.startsWith(prefix))
				return true;

		return false;
	}

	public static boolean startsWithAnyCI(String string, String...prefixes) {
		string = string.toLowerCase();

		for (String prefix : prefixes)
			if (string.startsWith(prefix.toLowerCase()))
				return true;

		return false;
	}

	public static boolean endsWithAny(String string, String...suffixes) {
		for (String suffix : suffixes)
			if (string.endsWith(suffix))
				return true;

		return false;
	}

	public static boolean endsWithAnyCI(String string, String...suffixes) {
		string = string.toLowerCase();

		for (String suffix : suffixes)
			if (string.endsWith(suffix.toLowerCase()))
				return true;

		return false;
	}

	public static boolean equalsAny(String string, String...others) {
		return Conditionals.equalsAny(string, others);
	}

	public static boolean equalsAnyCI(String string, String...others) {
		string = string.toLowerCase();

		for (String other : others)
			if (string.equals(other.toLowerCase()))
				return true;

		return false;
	}

	public static boolean hasText(String string) {
		return string != null  &&  !string.trim().isEmpty();
	}

	public static void match(String text, String prefix, String suffix, Consumer<String> consumer) {
		final int prefixLength = prefix.length();
		final int suffixLength = suffix.length();
		int current = 0;

		while (true) {
			final int prefixStart = text.indexOf(prefix, current);

			// found a prefix
			foundPrefix: if (prefixStart > -1) {

				// make sure it's not escaped
				if (prefixStart != 0 && text.charAt(prefixStart-1) == '\\') {
					current = prefixStart+prefixLength;
					break foundPrefix;
				}

				final int suffixStart = text.indexOf(suffix, prefixStart+prefixLength);

				// found a suffix
				foundSuffix: if (suffixStart > -1) {

					// make sure it's not escaped
					if (suffixStart != 0 && text.charAt(suffixStart-1) == '\\') {
						current = suffixStart+suffixLength;
						break foundSuffix;
					}

					final int matchStart = prefixStart+prefixLength;
					String substring = text.substring(matchStart, suffixStart);

					// replace backslashes before handing off
					substring = substring.replace("\\", "");

					consumer.accept(substring);
					current = suffixStart+suffixLength;
				}

				// no suffix, skip past this one
				else {
					current = prefixStart+prefixLength;
				}

			// there are no more prefixes
			} else {
				break;
			}
		}
	}
}