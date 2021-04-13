package atdit_ibait_20.library.model.implementation;

import atdit_ibait_20.library.model.Author;

public class BasicAuthor implements Author
{
	private final String name;

	public BasicAuthor(String name)
	{
		this.name = name;
	}

	@Override
	public String getName()
	{
		return this.name;
	}
}
