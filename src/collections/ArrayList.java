package collections;

public class ArrayList<T> implements List<T>
{
	private static final int DEFAULT_INIT_CAPACITY = 20;
	private static final int INVALID_INDEX = -1;

	private T[] values;
	private int nextIndex;

	public ArrayList(int initCap)
	{
		this.values = createArray(initCap);
		this.nextIndex = 0;
	}

	public ArrayList()
	{
		this(DEFAULT_INIT_CAPACITY);
	}

	@Override
	public int size()
	{
		return nextIndex;
	}

	@Override
	public boolean isEmpty()
	{
		return size() == 0;
	}

	@Override
	public boolean contains(Object value)
	{
		return indexOf(value) != INVALID_INDEX;
	}

	@Override
	public boolean containsAll(Collection<Object> values)
	{
		for(Iterator<Object> iterator = values.iterator(); iterator().isValid(); iterator.next())
		{
			if(!contains(iterator.value()))
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public void add(T value)
	{
		ensureCapacity(size() + 1);

		values[nextIndex++] = value;
	}

	@Override
	public void addAll(Collection<T> values)
	{
		ensureCapacity(size() + values.size());

		for(Iterator<T> iterator = values.iterator(); iterator().isValid(); iterator.next())
		{
			this.values[nextIndex++] = iterator.value();
		}
	}

	@Override
	public boolean remove(Object value)
	{
		int index = indexOf(value);

		if(index == INVALID_INDEX) return false;

		shiftLeft(index, 1);

		return true;
	}

	@Override
	public T get(int index)
	{
		if(index >= size()) return null;

		return values[index];
	}

	@Override
	public int indexOf(Object value)
	{
		for(int i = 0; i < size(); i++)
		{
			if(values[i].equals(value)) return i;
		}

		return INVALID_INDEX;
	}

	@Override
	public T remove(int index)
	{
		if(index >= size())
		{
			return null;
		}

		T value = values[index];

		shiftLeft(index, 1);

		return value;
	}

	@Override
	public boolean removeAll(Collection<Object> values)
	{
		boolean hasAll = true;

		for(Iterator<Object> iterator = values.iterator(); iterator.isValid(); iterator.next())
		{
			if(!remove(iterator.value()))
			{
				hasAll = false;
			}
		}

		return hasAll;
	}

	@Override
	public void clear()
	{
		this.values = createArray(DEFAULT_INIT_CAPACITY);
		this.nextIndex = 0;
	}

	private class ArrayListIterator implements Iterator<T>
	{
		int index;

		ArrayListIterator(int index)
		{
			this.index = index;
		}

		@Override
		public T value()
		{
			return isValid() ? get(index) : null;
		}

		@Override
		public void next()
		{
			index++;
		}

		@Override
		public boolean isValid()
		{
			return index < size();
		}
	}

	@Override
	public Iterator<T> iterator()
	{
		return new ArrayListIterator(0);
	}

	@Override
	public String toString()
	{
		String repr = "[";

		for(int i = 0; i < size(); i++)
		{
			repr += values[i].toString();

			if(i < size() - 1)
			{
				repr += ", ";
			}
		}

		return repr + "]";
	}

	private void shiftRight(int start, int offset)
	{
		ensureCapacity(size() + offset);

		for(int i = size() - 1; i >= start; i--)
		{
			values[i + offset] = values[i];
		}

		nextIndex += offset;
	}

	private void shiftLeft(int start, int offset)
	{
		for(int i = start; i < size() - offset; i++)
		{
			values[i] = values[i + offset];
		}

		for(int i = size() - offset; i < size(); i++)
		{
			values[i] = null;
		}

		nextIndex -= offset;
	}

	private void ensureCapacity(int reqCap)
	{
		if(reqCap >= values.length)
		{
			resizeArray(extendCapacity(reqCap));
		}
	}

	private void resizeArray(int newCap)
	{
		T[] newValues = createArray(newCap);

		for(int i = 0; i < size(); i++)
		{
			newValues[i] = values[i];
		}

		values = newValues;
	}

	private int extendCapacity(int reqCap)
	{
		return Math.max(reqCap, 2 * values.length);
	}

	private T[] createArray(int length)
	{
		return (T[]) new Object[length];
	}
}
