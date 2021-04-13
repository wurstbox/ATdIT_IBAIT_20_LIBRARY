package atdit_ibait_20.library.model.implementation;

import atdit_ibait_20.library.model.Author;

import java.util.Objects;

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

	@Override
	public String toString()
	{
		return name;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		BasicAuthor that = (BasicAuthor) o;
		return name.equals(that.name);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(name);
	}
}
