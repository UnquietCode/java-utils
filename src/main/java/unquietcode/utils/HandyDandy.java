package unquietcode.utils;

/**
 * @author Ben Fagin
 * @version 02-16-2013
 */
public final class HandyDandy {
	private HandyDandy() { }

	public static String print(String[] strings) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		boolean first = true;
		for (String string : strings) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}

			sb.append(string);
		}

		sb.append("]");
		return sb.toString();
	}
}
