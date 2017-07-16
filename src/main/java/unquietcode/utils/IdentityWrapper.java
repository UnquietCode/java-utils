package unquietcode.utils;

import java.util.Objects;

/**
 * Overrides equals and hashcode to
 *
 * @author Ben Fagin
 * @version 2017-07-15
 */
public class IdentityWrapper<T>  {
	private final T wrapped;

	public IdentityWrapper(T wrapped) {
		this.wrapped = Objects.requireNonNull(wrapped);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (obj instanceof IdentityWrapper) {
			IdentityWrapper other = (IdentityWrapper) obj;
			return this.wrapped == other.wrapped;
		}

		return false;
	}

	public T value() {
		return wrapped;
	}

	@Override
	public int hashCode() {
		return wrapped.hashCode();
	}
}