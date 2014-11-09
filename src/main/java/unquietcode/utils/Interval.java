package unquietcode.utils;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Ben Fagin
 */
public class Interval {
	public final long value;
	public final TimeUnit unit;

	public Interval(long value, TimeUnit unit) {
		this.value = value;
		this.unit = Objects.requireNonNull(unit);
	}

	public long as(TimeUnit other) {
		return other.convert(value, unit);
	}
}