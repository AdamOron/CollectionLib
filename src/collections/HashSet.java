package collections;

public class HashSet<T> implements Set<T>
{
	private HashMap<T, Boolean> values;

	public HashSet()
	{
		this.values = new HashMap<>();
	}

	@Override
	public int size()
	{
		return values.size();
	}

	@Override
	public boolean isEmpty()
	{
		return size() == 0;
	}

	@Override
	public boolean contains(Object value)
	{
		return values.contains(value);
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
		values.add(value, true);
	}

	@Override
	public void addAll(Collection<T> values)
	{
		for(Iterator<T> iterator = values.iterator(); iterator.isValid(); iterator.next())
		{
			add(iterator.value());
		}
	}

	@Override
	public boolean remove(Object value)
	{
		return values.remove(value);
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
		this.values = new HashMap<>();
	}

	@Override
	public Iterator<T> iterator()
	{
		return null;
	}
}
