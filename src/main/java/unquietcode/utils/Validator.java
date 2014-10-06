package unquietcode.utils;

import java.util.List;

/**
 * @author Ben Fagin
 */
public interface Validator<T> {

	/**
	 * Validate an object, return errors.
	 *
	 * @param target the target object to validate
	 * @return a list of errors
	 */
	List<String> validate(T target);

	/**
	 * Whether this validator supports the object.
	 *
	 * @param clazz class to check
	 * @return true if supported
	 */
	boolean supports(Class<?> clazz);
}
