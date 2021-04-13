package atdit_ibait_20.library.persistence;

import atdit_ibait_20.library.persistence.implementation.MockBookServiceFactory;

public abstract class BookServiceFactory
{
	public static BookServiceFactory defaultImplementation = null;

	public static BookServiceFactory getInstance()
	{
		if(defaultImplementation == null)
			throw new RuntimeException("Default BookServiceFactory was not instantiated");

		return defaultImplementation;
	}


	public abstract BookService get();
}
