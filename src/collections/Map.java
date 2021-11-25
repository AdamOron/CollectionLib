package collections;

public interface Map<K, V> extends Iterable<V>
{
	int size();

	boolean isEmpty();

	void add(K key, V value);

	V get(K key);

	boolean contains(K key);

	boolean remove(K key);

	void clear();
}
