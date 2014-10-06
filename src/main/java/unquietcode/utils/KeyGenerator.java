package unquietcode.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @author Ben Fagin
 */
public interface KeyGenerator {
	String generateKey();

	/**
	 * {@link unquietcode.utils.KeyGenerator} implementation which generates a random UUID as a key.
	 */
	public static final class KEYGEN_128 implements KeyGenerator {
		public @Override String generateKey() {
			return UUID.randomUUID().toString();
		}
	}

	/**
	 * {@link unquietcode.utils.KeyGenerator} implementation which generates a random 64 bit key.
	 * The key will always be in the range of [1000, MAX_LONG].
	 */
	public static final class KEYGEN_64 implements KeyGenerator {
		private final Random random = new Random();

		public @Override String generateKey() {
			long id;

			do {
				id = random.nextLong();
			} while (id < 1000);

			return Long.toString(id);
		}
	}
}
