package collections;

public class HashMap<K, V> implements Map<K, V>
{
	private class HashMapIterator implements Iterator<V>
	{
		int index;
		Entry<K, V> entry;
		int iterated;

		public HashMapIterator(int index)
		{
			this.index = index;
			this.entry = values[index];
			this.iterated = entry == null ? 0 : 1;
		}

		@Override
		public V value()
		{
			return isValid() ? entry.value : null;
		}

		@Override
		public void next()
		{
			if(iterated >= size())
			{
				entry = null;
				return;
			}

			if(isValid())
			{
				entry = entry.next;
			}

			while(entry == null)
			{
				entry = values[++index];
			}

			iterated++;
		}

		@Override
		public boolean isValid()
		{
			return entry != null;
		}
	}

	@Override
	public Iterator<V> iterator()
	{
		Iterator<V> iterator = new HashMapIterator(0);
		if(!iterator.isValid()) iterator.next();
		return iterator;
	}

	/**
	 * @author AdamOron
	 *
	 * @param <K> the HashMap's keys' type.
	 * @param <V> the HashMap's values' type.
	 *
	 * Represents a single entry (key & value pair) in the HashMap.
	 * Keys with the same hash code are stacked together as a LinkedList.
	 */
	private class Entry<K, V>
	{
		/* Key & value pair */
		K key;
		V value;
		/* Point to next Entry */
		Entry<K, V> next;

		/**
		 * @param key the Entry's key.
		 * @param value the Entry's value.
		 * @param next the next Entry in the LinkedList.
		 */
		Entry(K key, V value, Entry<K, V> next)
		{
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	/** Default initial capacity for Entry array */
	private static final int DEFAULT_INIT_CAPACITY = 20;
	/**
	 * Constant coefficient for calculating threshold.
	 * @see HashMap#threshold
	 */
	private static final float THRESHOLD_COEFF = 0.75f;

	/** Array of Entries where each cell is a LinkedList */
	private Entry<K, V>[] values;
	/**
	 * A HashMap should have a certain threshold for its size, such that whenever the size
	 * exceeds the threshold the HashMap should be resized & all values should be reinserted.
	 * This is done to ensure that values are spread evenly within the Entry lists.
	 */
	private int threshold;
	/** The amount of values stored in this HashMap */
	private int size;

	/**
	 * @param initCapacity the initial capacity of the HashMap's values array.
	 *
	 * Construct HashMap through helper function.
	 * @see HashMap#init
	 */
	public HashMap(int initCapacity)
	{
		init(initCapacity);
	}

	/**
	 * Construct HashMap with the default initial capacity.
	 */
	public HashMap()
	{
		this(DEFAULT_INIT_CAPACITY);
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public boolean isEmpty()
	{
		return size() == 0;
	}

	@Override
	public void add(K key, V value)
	{
		int index = getIndex(key);

		add(index, key, value);
	}

	@Override
	public V get(Object key)
	{
		Entry<K, V> entry = getEntry(key);

		if(entry == null)
		{
			return null;
		}

		return entry.value;
	}

	public long bench;

	@Override
	public boolean contains(Object key)
	{
		return get(key) != null;
	}

	@Override
	public boolean remove(Object key)
	{
		int index = getIndex(key);

		if(values[index] == null)
		{
			return false;
		}

		if(values[index].key.equals(key))
		{
			values[index] = values[index].next;

			return true;
		}

		Entry<K, V> prev = values[index];

		while(prev.next != null)
		{
			if(prev.next.key.equals(key))
			{
				prev.next = prev.next.next;

				return true;
			}

			prev = prev.next;
		}

		return false;
	}

	@Override
	public void clear()
	{
		init(DEFAULT_INIT_CAPACITY);
	}

	private void add(int index, K key, V value)
	{
		Entry<K, V> current = values[index];

		while(current != null)
		{
			if(current.key.equals(key))
			{
				current.value = value;
				return;
			}

			current = current.next;
		}

		if(size() + 1 >= threshold)
		{
			resize(values.length * 2);

			int newIndex = getIndex(key);
			values[newIndex] = new Entry<>(key, value, values[newIndex]);
		}
		else
		{
			values[index] = new Entry<>(key, value, values[index]);
		}

		size++;
	}

	private void resize(int newCapacity)
	{
		Entry<K, V>[] oldValues = values;

		init(newCapacity);

		for(int i = 0; i < oldValues.length; i++)
		{
			Entry<K, V> current = oldValues[i];

			while(current != null)
			{
				add(current.key, current.value);

				current = current.next;
			}
		}
	}

	private Entry<K, V> getEntry(Object key)
	{
		int index = getIndex(key);

		Entry<K, V> current = values[index];

		while(current != null)
		{
			if(current.key.equals(key))
			{
				return current;
			}

			current = current.next;
		}

		return null;
	}

	private int getIndex(Object key)
	{
		return Math.abs(key.hashCode() % values.length);
	}

	private void init(int newCapacity)
	{
		values = createArray(newCapacity);
		threshold = (int) (values.length * THRESHOLD_COEFF);
		size = 0;
	}

	private Entry<K, V>[] createArray(int capacity)
	{
		return (Entry<K, V>[]) new Entry[capacity];
	}
}
