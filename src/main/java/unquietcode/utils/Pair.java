package unquietcode.utils;

import java.util.Comparator;

/**
 * @author  Benjamin Fagin
 */
public class Pair<A, B> {
	public A first;
	public B second;


	public Pair(A first, B second) {
		this.first = first;
		this.second = second;
	}

	// JavaBeans properties
	public A getFirst() {
		return first;
	}

	public void setFirst(A value) {
		first = value;
	}

	public B getSecond() {
		return second;
	}

	public void setSecond(B value) {
		second = value;
	}

	// comparators //
	public static class Comparator1to1<T extends Comparable<T>> implements Comparator<Pair<T,?>> {
		public int compare(Pair<T, ?> p1, Pair<T, ?> p2) {
			return p1.first.compareTo(p2.first);
		}
	}

	public static class Comparator2to2<T extends Comparable<T>> implements Comparator<Pair<?,T>> {
		public int compare(Pair<?, T> p1, Pair<?, T> p2) {
			return p1.second.compareTo(p2.second);
		}
	}

	// named static classes //
	public static class NameValue<C, D> {
		public C name;
		public D value;

		public NameValue(C name, D value) {
			this.name = name;
			this.value = value;
		}
	}

	public static class Final<C, D> {
		public final C first;
		public final D second;

		public Final(C first, D second) {
			this.first = first;
			this.second = second;
		}
	}
}