package collections;

public interface Iterator<T>
{
	T value();

	void next();

	boolean isValid();
}
