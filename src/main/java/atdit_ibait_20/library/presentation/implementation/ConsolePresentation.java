package atdit_ibait_20.library.presentation.implementation;

import atdit_ibait_20.library.model.Book;
import atdit_ibait_20.library.model.Catalog;
import atdit_ibait_20.library.model.implementation.BasicAuthor;
import atdit_ibait_20.library.model.implementation.BasicBook;
import atdit_ibait_20.library.model.implementation.BasicCatalog;
import atdit_ibait_20.library.persistence.BookService;
import atdit_ibait_20.library.persistence.implementation.MockBookService;
import atdit_ibait_20.library.presentation.Presentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class ConsolePresentation implements Presentation
{
	public interface ConsoleInputRequester
	{
		String requestInput();
	}

	private boolean userDecidedToEndProgram;
	private final Catalog catalog;
	ConsoleInputRequester consoleInputRequester;

	public ConsolePresentation()
	{
		this.userDecidedToEndProgram = false;
		this.catalog = new BasicCatalog();
		consoleInputRequester = this::requestInput;
	}

	@Override
	public void start()
	{
		while(!userDecidedToEndProgram)
		{
			this.loadBooks();
			this.displayBooks();
			this.userInteraction();
			this.updateBooks();
		}
	}

	private void updateBooks()
	{
		BookService service = new MockBookService();
		try
		{
			service.postBooks(this.catalog);
		}
		catch(BookService.BookServiceException e)
		{
			printError(e);
		}
	}

	private void printError(BookService.BookServiceException e)
	{
		System.out.println("error" + ": " + e.getMessage());
	}

	private void userInteraction()
	{
		printUserOptions();
		String input = readUserInput();
		reactOnInput(input);
	}

	private void reactOnInput(String input)
	{
		if(Objects.equals(input, "E"))
		{
			userDecidedToEndProgram();
		}
		else if(Objects.equals(input, "A"))
		{
			startProcessAddNewBook();
		}
		else
		{
			System.out.println("Unknown user command");
		}
	}

	private void startProcessAddNewBook()
	{
		System.out.println("Please enter book details: ");
		System.out.print("\t" + "Author: ");
		String author = readUserInput();
		System.out.print("\t" + "Title: ");
		String title = readUserInput();
		System.out.print("\t" + "Description: ");
		String description = readUserInput();

		Book book = new BasicBook(
			description,
			title,
  		new BasicAuthor(author) );

		this.catalog.add(book);
	}

	private void userDecidedToEndProgram()
	{
		userDecidedToEndProgram = true;
		System.out.println("Good bye");
	}

	private String readUserInput()
	{
		return consoleInputRequester.requestInput();
	}

	private String requestInput()
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try
		{
			return br.readLine();
		}
		catch(IOException e)
		{
			throw new RuntimeException("critical error, program halts", e);
		}
	}

	private void printUserOptions()
	{
		System.out.println("What do you want to do?");
		System.out.println("\t" + "(A)dd Book");
		System.out.println("\t" + "(E)xit");
		System.out.print("Please decide: ");
	}

	private void displayBooks()
	{
		int counter = 0;

		System.out.println("catalog contains the following books");
		for(Book book : this.catalog)
		{
			System.out.println("\t" + ++counter + ": " + book);
		}
		System.out.println();
	}

	private void loadBooks()
	{
		BookService service = new MockBookService();
		this.catalog.clear();
		try
		{
			this.catalog.addAll(service.getBooks());
		}
		catch(BookService.BookServiceException e)
		{
			printError(e);
		}
	}
}
