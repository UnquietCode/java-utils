package unquietcode.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Ben Fagin
 */
public final class TimeUtils {
	private TimeUtils() { }

	private static final TimeZone UTC = TimeZone.getTimeZone("UTC");
	private static final DateFormat ISO_8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); static {
		ISO_8601.setTimeZone(UTC);
	}

	public static String iso8601(long time) {
		return iso8601(new Date(time));
	}

	public static String iso8601(Date date) {
		return ISO_8601.format(Objects.requireNonNull(date));
	}

	public static long getCurrentTimeToTheMinute() {
		Calendar time = GregorianCalendar.getInstance();
		time.set(Calendar.MILLISECOND, 0);
		time.set(Calendar.SECOND, 0);

		return time.getTimeInMillis();
	}

	public static long getCurrentTimeToTheSecond() {
		Calendar time = GregorianCalendar.getInstance();
		time.set(Calendar.MILLISECOND, 0);

		return time.getTimeInMillis();
	}

	public static long getTimeToTheSecond_div10(long time) {
		Calendar working = GregorianCalendar.getInstance();
		working.setTimeInMillis(time);
		working.set(Calendar.MILLISECOND, 0);

		int second = working.get(Calendar.SECOND);
		second = (int) ((second / 10d) * 10);
		working.set(Calendar.SECOND, second);

		return working.getTimeInMillis();
	}
}