package collections;

public class Trie
{
	private class TrieNode
	{
		char value;
		boolean isFinal;
		HashMap<Character, TrieNode> children;

		TrieNode(char value, boolean isFinal)
		{
			this.value = value;
			this.isFinal = isFinal;
			this.children = new HashMap<>();
		}

		TrieNode(char value)
		{
			this(value, false);
		}

		TrieNode addChild(char value)
		{
			TrieNode node = new TrieNode(value);

			children.add(value, node);

			return node;
		}

		TrieNode getChild(char value)
		{
			return children.get(value);
		}

		TrieNode getOrAddChild(char value)
		{
			TrieNode child = getChild(value);

			if(child == null)
			{
				child = addChild(value);
			}

			return child;
		}

		List<String> allPossibleStrings()
		{
			List<String> strings = new LinkedList<>();

			if(isFinal)
			{
				strings.add(value + "");
			}

			for(Iterator<TrieNode> iterator = children.iterator(); iterator.isValid(); iterator.next())
			{
				List<String> subAllPossibleStrings = iterator.value().allPossibleStrings();

				for(Iterator<String> subIter = subAllPossibleStrings.iterator(); subIter.isValid(); subIter.next())
				{
					strings.add(value + subIter.value());
				}
			}

			return strings;
		}
	}

	private TrieNode root;

	public Trie()
	{
		this.root = new TrieNode('0');
	}

	public void add(String string)
	{
		TrieNode current = root;

		for(char ch : string.toCharArray())
		{
			current = current.getOrAddChild(ch);
		}

		current.isFinal = true;
	}

	public boolean contains(String string)
	{
		TrieNode current = root;

		for(char ch : string.toCharArray())
		{
			current = current.getChild(ch);

			if(current == null) return false;
		}

		return current.isFinal;
	}

	public List<String> get(String prefix)
	{
		List<String> strings = new LinkedList<>();

		String current = prefix;

		TrieNode prefixNode = getNode(prefix);

		return prefixNode.allPossibleStrings();
	}

	private TrieNode getNode(String prefix)
	{
		TrieNode current = root;

		for(char ch : prefix.toCharArray())
		{
			current = current.getChild(ch);

			if(current == null) return null;
		}

		return current;
	}
}
