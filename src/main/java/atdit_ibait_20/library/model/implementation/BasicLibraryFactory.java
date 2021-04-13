package atdit_ibait_20.library.model.implementation;

import atdit_ibait_20.library.model.AbstractLibraryFactory;
import atdit_ibait_20.library.model.Author;
import atdit_ibait_20.library.model.Book;
import atdit_ibait_20.library.model.Catalog;

import java.util.Objects;

public class BasicLibraryFactory extends AbstractLibraryFactory
{
	@Override
	public Book makeBook(String title, Author author, String description)
	{
		Objects.requireNonNull(author);
		return new BasicBook(description, title, author);
	}

	@Override
	public Catalog makeCatalog()
	{
		return new BasicCatalog();
	}

	@Override
	public Author makeAuthor(String name)
	{
		Objects.requireNonNull(name);
		return new BasicAuthor(name);
	}
}
