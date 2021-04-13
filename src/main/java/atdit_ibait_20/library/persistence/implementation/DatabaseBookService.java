package atdit_ibait_20.library.persistence.implementation;

import atdit_ibait_20.library.model.Book;
import atdit_ibait_20.library.persistence.BookService;

import java.util.List;

public class DatabaseBookService implements BookService
{
	@Override
	public List<Book> getBooks() throws BookServiceException
	{
		throw new BookServiceException("database not connected");
	}

	@Override
	public void postBooks(List<Book> books) throws BookServiceException
	{
		throw new BookServiceException("database not connected");
	}
}