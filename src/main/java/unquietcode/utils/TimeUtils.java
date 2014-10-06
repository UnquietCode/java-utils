package unquietcode.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Ben Fagin
 */
public final class TimeUtils {
	private TimeUtils() { }


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
