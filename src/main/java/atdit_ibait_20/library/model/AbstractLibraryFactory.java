package atdit_ibait_20.library.model;

public abstract class AbstractLibraryFactory
{
	public static AbstractLibraryFactory defaultInstance;

	public static AbstractLibraryFactory get()
	{
		if(defaultInstance == null)
			throw new RuntimeException("Default AbstractLibraryFactory was not instantiated");
		return defaultInstance;
	}

	public abstract Book makeBook(String title, Author author, String description);
	public abstract Catalog makeCatalog();
	public abstract Author makeAuthor(String name);
}
