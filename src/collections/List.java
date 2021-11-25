package collections;

public interface List<T> extends Collection<T>
{
	T get(int index);

	T remove(int index);

	int indexOf(Object value);
}
