package collections;

public class LinkedList<T> implements List<T>
{
	private class ListNode
	{
		T value;
		ListNode prev, next;

		ListNode(ListNode prev, T value, ListNode next)
		{
			this.value = value;
			this.prev = prev;
			this.next = next;
		}

		ListNode(T value)
		{
			this(null, value, null);
		}
	}

	private static final int INVALID_INDEX = -1;

	private ListNode head, tail;
	private int size;

	public LinkedList()
	{
		this.head = null;
		this.tail = null;
		this.size = 0;
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
	public boolean contains(Object value)
	{
		ListNode current = head;

		while(current != null)
		{
			if(current.value.equals(value))
			{
				return true;
			}

			current = current.next;
		}

		return false;
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
		if(isEmpty())
		{
			head = new ListNode(value);
			tail = head;
		}
		else
		{
			tail.next = new ListNode(tail, value, null);
			tail = tail.next;
		}

		size++;
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
		if(isEmpty()) return false;

		if(head.value.equals(value))
		{
			head = head.next;

			if(head != null)
			{
				head.prev = null;
			}

			size--;
			return true;
		}

		if(tail.value.equals(value))
		{
			tail = tail.prev;
			tail.next = null; // nullpointerexception?

			size--;
			return true;
		}

		ListNode prev = head;

		while(prev.next != null)
		{
			ListNode current = prev.next;

			if(current.value.equals(value))
			{
				removeNext(prev);

				size--;
				return true;
			}

			prev = prev.next;
		}

		return false;
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
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	private class LinkedListIterator implements Iterator<T>
	{
		ListNode node;

		LinkedListIterator(ListNode node)
		{
			this.node = node;
		}

		@Override
		public T value()
		{
			return isValid() ? node.value : null;
		}

		@Override
		public void next()
		{
			node = node.next;
		}

		@Override
		public boolean isValid()
		{
			return node != null;
		}
	}

	@Override
	public Iterator<T> iterator()
	{
		return new LinkedListIterator(head);
	}

	@Override
	public T get(int index)
	{
		ListNode node = getNode(index);

		if(node == null) return null;

		return node.value;
	}

	@Override
	public T remove(int index)
	{
		if(isEmpty()) return null;
		if(index >= size()) return null;

		if(index == 0)
		{
			T value = head.value;

			head = head.next;

			if(head != null)
			{
				head.prev = null;
			}

			size--;
			return value;
		}

		if(index == size() - 1)
		{
			T value = tail.value;

			tail = tail.prev;
			tail.next = null; // nullpointerexception?

			size--;
			return value;
		}

		ListNode targetPrev = getNode(index - 1);

		T value = targetPrev.next.value;

		removeNext(targetPrev);

		return value;
	}

	@Override
	public int indexOf(Object value)
	{
		if(isEmpty()) return INVALID_INDEX;

		if(tail.value.equals(value))
		{
			return size() - 1;
		}

		ListNode current = head;
		int index = 0;

		while(current != null)
		{
			if(current.value.equals(value))
			{
				return index;
			}

			current = current.next;
			index++;
		}

		return INVALID_INDEX;
	}

	@Override
	public String toString()
	{
		String repr = "[";

		ListNode current = head;

		for(int i = 0; i < size(); i++)
		{
			repr += current.value.toString();

			if(i < size() - 1)
			{
				repr += ", ";
			}

			current = current.next;
		}

		return repr + "]";
	}

	private void removeNext(ListNode targetPrev)
	{
		ListNode target = targetPrev.next;
		ListNode targetNext = target.next;

		targetPrev.next = targetNext;

		if(targetNext != null)
		{
			targetNext.prev = targetPrev;
		}
	}

	private ListNode getNode(int index)
	{
		if(isEmpty()) return null;
		if(index >= size()) return null;

		if(index == 0)
		{
			return head;
		}

		if(index == size - 1)
		{
			return tail;
		}

		ListNode current = head;

		for(int i = 0; i < index; i++)
		{
			if(current == null) return null;

			current = current.next;
		}

		return current;
	}
}
