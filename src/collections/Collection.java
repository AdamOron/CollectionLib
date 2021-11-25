package collections;

public interface Collection<T> extends Iterable<T>
{
	int size();

	boolean isEmpty();

	boolean contains(Object value);
	boolean containsAll(Collection<Object> values);

	void add(T value);
	void addAll(Collection<T> values);

	boolean remove(Object value);
	boolean removeAll(Collection<Object> values);

	void clear();

	@Override
	String toString();
}
