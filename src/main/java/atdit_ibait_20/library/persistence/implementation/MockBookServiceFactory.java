package atdit_ibait_20.library.persistence.implementation;

import atdit_ibait_20.library.persistence.BookService;
import atdit_ibait_20.library.persistence.BookServiceFactory;

public class MockBookServiceFactory extends BookServiceFactory
{
	@Override
	public BookService get()
	{
		return new MockBookService();
	}
}
