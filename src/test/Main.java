package test;

import collections.*;

public class Main
{
	static class ListNode
	{
		int value;
		ListNode prev, next;

		public ListNode(ListNode prev, int value, ListNode next)
		{
			this.value = value;
			this.prev = prev;
			this.next = next;
		}
	}

	public static void toString(ListNode list)
	{
		ListNode iter = list;

		while(iter != null)
		{
			System.out.println(iter.value);
			iter = iter.next;
		}
	}

	private static void remove()
	{
		ListNode head = new ListNode(null, 0, null);
		ListNode n1 = new ListNode(head, 1, null);
		head.next = n1;
		ListNode n2 = new ListNode(n1, 2, null);
		n1.next = n2;

		toString(head);

		head.next = head.next.next;
		head.next.prev = head;

		System.out.println();
		toString(head);
	}

	public static void main(String[] args)
	{
		Trie trie = new Trie();
		trie.add("adam");
		trie.add("adi");
		trie.add("adir");
		trie.add("aids");
		trie.add("air");
		trie.add("airsoft");
		trie.add("airspin");
		trie.add("airkick");

		HashSet<String> set = new HashSet<>();
		set.add("adam");
		set.add("adi");
		set.add("adir");
		set.add("aids");
		set.add("air");
		set.add("airsoft");
		set.add("airspin");
		set.add("airkick");

		long start = System.nanoTime();
		boolean result = set.contains("air");
		long end = System.nanoTime();

		System.out.println(result);
		System.out.println(end - start);
	}

	private static void maps()
	{
		//ArrayList<Integer> list = new ArrayList<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		java.util.HashMap<Integer, Integer> jmap = new java.util.HashMap<>();

		int iter = 500;

		long jstart = System.nanoTime();
		for(int i = 0; i < iter; i++)
		{
			jmap.put(i, 1000);
		}
		long jbench = System.nanoTime() - jstart;

		long start = System.nanoTime();
		for(int i = 0; i < iter; i++)
		{
			map.add(i, 1000 + i);
		}
		long bench = System.nanoTime() - start;


		//long lstart = System.nanoTime();
		//boolean lcontains = list.contains(val);
		//long lbench = System.nanoTime() - lstart;

		//System.out.println("List: " + lcontains + "; " + lbench);
		System.out.println("JMap: " + "; " + jbench);
		System.out.println("Map:  " + map.size() + "; " + bench);
	}

	private static void linekdList()
	{
		LinkedList<Integer> list = new LinkedList<>();

		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);

		System.out.println(list);

		list.remove(Integer.valueOf(2));
		list.remove(Integer.valueOf(1));
		//list.remove(Integer.valueOf(0));
		//list.remove(Integer.valueOf(3));

		System.out.println(list);

		System.out.println(list.get(0));
	}

	private static void arrayList()
	{
		ArrayList<Integer> list = new ArrayList<>();

		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);

		System.out.println(list);

		list.remove(2);

		System.out.println(list);

		list.add(6);

		System.out.println(list);

		list.remove(5);
		list.remove(4);
		list.remove(3);

		System.out.println(list);

		System.out.println(list);

		list.remove(Integer.valueOf(5));

		System.out.println(list);
	}
}
