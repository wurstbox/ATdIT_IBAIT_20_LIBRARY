package atdit_ibait_20.library.persistence;

import atdit_ibait_20.library.model.Book;

import java.util.List;

public interface BookService
{
	class BookServiceException extends Exception
	{
		public BookServiceException(String message)
		{
			super(message);
		}

		public BookServiceException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	List<Book> getBooks() throws BookServiceException;
	void postBooks(List<Book> books) throws BookServiceException;
}
