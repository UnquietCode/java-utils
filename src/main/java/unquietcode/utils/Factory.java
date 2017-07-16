package unquietcode.utils;

/**
 * Interface to make use of the object factory pattern.
 *
 * @author Ben Fagin
 */
@FunctionalInterface
public interface Factory<T> {
	T get();
}