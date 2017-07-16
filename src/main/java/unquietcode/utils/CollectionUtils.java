package unquietcode.utils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public final class CollectionUtils {
	private CollectionUtils() { }


	@SafeVarargs
	public static <T> T[] array(T...values) {
		return values;
	}

	@SafeVarargs
	public static <T> T[] prepend(T[] array, T...newValues) {
		return append(newValues, array);
	}

	@SafeVarargs
	public static <T> T[] append(T[] array, T...newValues) {
		T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length+newValues.length);
		System.arraycopy(array, 0, newArray, 0, array.length);
		System.arraycopy(newValues, 0, newArray, array.length, newValues.length);

		return newArray;
	}

	@SafeVarargs
	public static <T> List<T> list(T...values) {
		return new ArrayList<>(Arrays.asList(values));
	}

	@SafeVarargs
	public static <T> Set<T> hashSet(T...values) {
		return new HashSet<>(list(values));
	}

	public static String print(Collection<?> values) {
		return print("[", "]", ", ", values);
	}

	public static String print(Map<?, ?> values) {
		Set<String> set = new HashSet<>();

		for (Map.Entry<?, ?> entry : values.entrySet()) {
			String string = String.valueOf(entry.getKey()) + " => " + String.valueOf(entry.getValue());
			set.add(string);
		}

		return print(set);
	}

	public static String print(Object...values) {
		return print("[", "]", ", ", values);
	}

	public static String print(String delimiter, Collection<?> values) {
		return print("", "", delimiter, values);
	}

	public static String print(String delimiter, Object...values) {
		return print("", "", delimiter, values);
	}

	public static String print(String prefix, String suffix, String delimiter, Object...values) {
		return print(prefix, suffix, delimiter, Arrays.asList(values));
	}

	public static String print(String prefix, String suffix, String delimiter, Collection<?> values) {
		StringBuilder sb = new StringBuilder();
		sb.append(prefix);

		boolean first = true;

		for (Object value : values) {
			if (first) {
				first = false;
			} else {
				sb.append(delimiter);
			}

			sb.append(value);
		}

		sb.append(suffix);
		return sb.toString();
	}

	public static <T> Set<T> randomSubset(Set<T> set, int count, Set<T> target) {
		List<T> list = new LinkedList<>(set);
		Collections.shuffle(list);

		int end = count <= list.size() ? count : list.size();
		target.addAll(list.subList(0, end));

		return target;
	}

	public static <K, V> V synchronized_get_or_set(Map<K, V> map, K key, Supplier<V> valueProvider) {

		// check once
		if (map.containsKey(key)) {
			return map.get(key);
		}

		// lock
		synchronized (map) {

			// check again
			if (map.containsKey(key)) {
				return map.get(key);
			}

			// store new value, and return it
			V value = valueProvider.get();
			map.put(key, value);
			return value;
		}
	}

	public static <T> T first(List<T> list) {
		return list.get(0);
	}

	public static <T> T last(List<T> list) {
		return list.get(list.size()-1);
	}


	/**
 	 * null-safe collection reference
 	 */
	public static <T> Collection<T> safe(Collection<T> collection) {
		return collection != null ? collection : Collections.<T>emptyList();
	}

	public static <T> List<T> safe(List<T> list) {
		return list != null ? list : Collections.<T>emptyList();
	}

	public static <T> List<T> safe(T[] list) {
		return list != null ? Arrays.asList(list) : Collections.<T>emptyList();
	}


	// empty / not empty

	public static <T> boolean isEmpty(Collection<T> collection) {
		return collection == null  ||  collection.isEmpty();
	}

	public static <T> boolean isNotEmpty(Collection<T> collection) {
		return !isEmpty(collection);
	}

	public static <T> boolean isEmpty(T[] array) {
		return array == null  ||  array.length == 0;
	}

	public static <T> boolean isNotEmpty(T[] array)	{
		return !isEmpty(array);
	}


	// collection modifiers

	public static <K, V> void addToArrayList(Map<K, List<V>> map, K key, V value) {
		addToMap(map, key, value, ArrayList::new);
	}

	public static <K, V> void addToHashSet(Map<K, Set<V>> map, K key, V value) {
		addToMap(map, key, value, HashSet::new);
	}

	public static <K, V> void addToIdentityHashSet(Map<K, Set<V>> map, K key, V value) {
		addToMap(map, key, value, CollectionUtils::newIdentityHashSet);
	}

	public static <K, V, T extends Collection<V>> void addToMap(Map<K, T> map, K key, V value, Factory<T> factory) {
		if (map.containsKey(key)) {
			map.get(key).add(value);
		} else {
			T collection = factory.get();
			collection.add(value);
			map.put(key, collection);
		}
	}

	public static <_Key1, _Key2, _Value> void addToHashMap(
		Map<_Key1, Map<_Key2, _Value>> map,
		_Key1 outerKey, _Key2 innerKey, _Value value
	){
		addToNestedMap(map, outerKey, innerKey, value, HashMap::new);
	}

	public static <_Key1, _Key2, _Value> void addToIdentityHashMap(
		Map<_Key1, Map<_Key2, _Value>> map,
		_Key1 outerKey, _Key2 innerKey, _Value value
	){
		addToNestedMap(map, outerKey, innerKey, value, IdentityHashMap::new);
	}

	public static <_Key1, _Key2, _Value> void addToNestedMap(
		Map<_Key1, Map<_Key2, _Value>> map,
		_Key1 outerKey, _Key2 innerKey, _Value value, Factory<Map<_Key2, _Value>> factory
	){
		Map<_Key2, _Value> innerMap = map.get(outerKey);

		if (innerMap == null) {
			innerMap = factory.get();
			map.put(outerKey, innerMap);
		}

		innerMap.put(innerKey, value);
	}

	public static <T> Set<T> newIdentityHashSet() {
		return Collections.newSetFromMap(new IdentityHashMap<T, Boolean>());
	}

	public static <T> List<T> flatten(Collection<? extends Collection<T>> items) {
		return items.stream()
			.flatMap(Collection::stream)
			.collect(Collectors.toList())
		;
	}
}