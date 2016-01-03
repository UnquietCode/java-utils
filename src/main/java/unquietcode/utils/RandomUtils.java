package unquietcode.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

/**
 * @author Ben Fagin
 */
public final class RandomUtils {
	private RandomUtils() { }
	private static final Random randomSource = new Random();
	private static final Random secureRandomSource = new SecureRandom();

	// org.apache.commons.codec.binary.Base64#URL_SAFE_BASE_64_TABLE
	private static final char[] URL_SAFE_BASE_64_TABLE = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'
	};

	private static final char[] SUPER_SAFE_TABLE = {
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
	};


	//---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---//

	public static <T> Iterator<T> weightedRandomSelection(Map<T, Double> mapping) {

		// create a list of entries
		final List<Map.Entry<T, Double>> entries = new ArrayList<Map.Entry<T, Double>>(mapping.size());
		for (Map.Entry<T, Double> entry : mapping.entrySet()) {
			entries.add(new AbstractMap.SimpleEntry<T, Double>(entry.getKey(), entry.getValue()));
		}

		// sort the entries by weight
		Collections.sort(entries, new Comparator<Map.Entry<T, Double>>() {
			public int compare(Map.Entry<T, Double> o1, Map.Entry<T, Double> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});

		// normalize the weights
		double total = 0.0;

		for (Map.Entry<T, Double> element : entries) {
			total += element.getValue();
			element.setValue(total);
		}

		return new Iterator<T>() {
			public @Override boolean hasNext() {
				return true;
			}

			public @Override T next() {
				// select an entry matching the weight
				double random = randomSource.nextDouble();

				for (Map.Entry<T, Double> entry : entries) {
					if (random <= entry.getValue()) {
						return entry.getKey();
					}
				}

				// and just in case of floating point nonsense...
				return entries.get(entries.size()-1).getKey();
			}

			public @Override void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	// Return a 96 bit pseudorandom key, comprised of a 64 bit timestamp and a 32 bit pseudorandom number.
	// The key is returned in the desired base, which should within [2, 16].
	public static String getRandomKey(int base) {
		BigInteger randomPart = BigInteger.valueOf(secureRandomSource.nextInt(Integer.MAX_VALUE));
		BigInteger timePart = BigInteger.valueOf(System.currentTimeMillis());

		BigInteger value = timePart.shiftLeft(32);
		value = value.or(randomPart);
		return value.toString(base).toUpperCase();
	}

	// generates a new random string comprised of base64 characters
	public static String getRandomBase64Key(int length) {
		StringBuilder sb = new StringBuilder();

		for (int i=0; i < length; ++i) {
			int index = secureRandomSource.nextInt(URL_SAFE_BASE_64_TABLE.length);
			sb.append(URL_SAFE_BASE_64_TABLE[index]);
		}

		return sb.toString();
	}

	// generates a new random string comprised of ultra-safe lowercase letters only
	public static String getRandomSafeKey(int length) {
		StringBuilder sb = new StringBuilder();

		for (int i=0; i < length; ++i) {
			int index = secureRandomSource.nextInt(SUPER_SAFE_TABLE.length);
			sb.append(SUPER_SAFE_TABLE[index]);
		}

		return sb.toString();
	}

	public static <K,V> Map<K, V> randomize(Map<K, V> map) {
		return randomize(map, map.size());
	}

	public static <K,V> Map<K, V> randomize(Map<K, V> map, int size) {
		if (size == map.size()) {
			return new HashMap<K,V>(map);
		} else if (size > map.size()) {
			throw new RuntimeException("Not enough elements.");
		}

		ArrayList<Map.Entry<K,V>> entries = new ArrayList<Map.Entry<K, V>>(map.entrySet());
		Map<K,V> retval = new HashMap<K, V>();

		while (retval.size() < size) {
			Map.Entry<K,V> entry = entries.get(randomSource.nextInt(entries.size()));

			if (retval.containsKey(entry.getKey())) {
				continue;
			}

			retval.put(entry.getKey(), entry.getValue());
		}

		return retval;
	}

	public static <T> List<T> randomize(List<T> list) {
		list = new ArrayList<T>(list); // create a working copy
		List<T> randomized = new ArrayList<>(list.size());

		while (!list.isEmpty()) {
			int idx = randomSource.nextInt(list.size());
			T element = list.remove(idx);
			randomized.add(element);
		}

		return randomized;
	}
}