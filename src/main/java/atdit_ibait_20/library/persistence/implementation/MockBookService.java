package atdit_ibait_20.library.persistence.implementation;

import atdit_ibait_20.library.model.Book;
import atdit_ibait_20.library.persistence.BookService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MockBookService implements BookService
{
	private static final Set<Book> books = new HashSet<>();

	@Override
	public List<Book> getBooks() throws BookServiceException
	{
		return new ArrayList<>(MockBookService.books);
	}

	@Override
	public void postBooks(List<Book> books) throws BookServiceException
	{
		MockBookService.books.clear();
		MockBookService.books.addAll(books);
	}
}
