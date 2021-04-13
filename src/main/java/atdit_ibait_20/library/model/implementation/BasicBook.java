package atdit_ibait_20.library.model.implementation;

import atdit_ibait_20.library.model.Author;
import atdit_ibait_20.library.model.Book;

public class BasicBook implements Book
{
	private final String description;
	private final String title;
	private final Author author;

	public BasicBook(String description, String title, Author author)
	{
		this.description = description;
		this.title = title;
		this.author = author;
	}

	@Override
	public String getTitle()
	{
		return this.title;
	}

	@Override
	public String getAuthor()
	{
		return this.author.getName();
	}

	@Override
	public String getDescription()
	{
		return this.description;
	}

	@Override
	public String toString()
	{
		return title + " von " + author;
	}
}
