package atdit_ibait_20.library.presentation.implementation;

import atdit_ibait_20.library.model.AbstractLibraryFactory;
import atdit_ibait_20.library.model.Author;
import atdit_ibait_20.library.model.Book;
import atdit_ibait_20.library.model.Catalog;
import atdit_ibait_20.library.persistence.BookService;
import atdit_ibait_20.library.persistence.BookServiceFactory;
import atdit_ibait_20.library.presentation.Presentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConsolePresentation implements Presentation
{
	private static final String RESOURCE_BUNDLE_PATH = "i18n/console_presentation/console_presentation"; //NON-NLS
	public static final String USER_INPUT_E = "E"; //NON-NLS
	public static final String USER_INPUT_A = "A"; //NON-NLS

	public interface ConsoleInputRequester
	{
		String requestInput();
	}

	private boolean userDecidedToEndProgram;
	private final Catalog catalog;
	ConsoleInputRequester consoleInputRequester;
	private final ResourceBundle resourceBundle;

	public ConsolePresentation()
	{
		this.userDecidedToEndProgram = false;
		this.catalog = AbstractLibraryFactory.get().makeCatalog();
		consoleInputRequester = this::requestInput;
		this.resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH);
	}

	@Override
	public void start()
	{
		while(!userDecidedToEndProgram)
		{
			System.out.println();
			this.printTime();
			this.loadBooks();
			this.displayBooks();
			this.userInteraction();
			this.updateBooks();
		}
	}

	private void printTime()
	{
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(
			FormatStyle.LONG );
		ZonedDateTime now = ZonedDateTime.now();
		String formattedNow = dateTimeFormatter.format(now);

		System.out.println(
			MessageFormat.format(
				resourceBundle.getString("current.time.0"), formattedNow));
	}

	private void updateBooks()
	{
		BookServiceFactory factory = BookServiceFactory.getInstance();
		BookService service = factory.get();

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
		System.out.println(MessageFormat.format(resourceBundle.getString("error.0"), e.getMessage()));
	}

	private void userInteraction()
	{
		printUserOptions();
		String input = readUserInput();
		reactOnInput(input);
	}

	private void reactOnInput(String input)
	{
		if(Objects.equals(input, USER_INPUT_E))
		{
			userDecidedToEndProgram();
		}
		else if(Objects.equals(input, USER_INPUT_A))
		{
			startProcessAddNewBook();
		}
		else
		{
			System.out.println(resourceBundle.getString("unknown.user.command"));
		}
	}

	private void startProcessAddNewBook()
	{
		System.out.println(resourceBundle.getString("please.enter.book.details"));
		System.out.print("\t" + resourceBundle.getString("author"));
		String author = readUserInput();
		System.out.print("\t" + resourceBundle.getString("title"));
		String title = readUserInput();
		System.out.print("\t" + resourceBundle.getString("description"));
		String description = readUserInput();

		Author authorObj = AbstractLibraryFactory.get().makeAuthor(author);
		Book book = AbstractLibraryFactory.get().makeBook(
			title,
			authorObj,
			description );

		this.catalog.add(book);
	}

	private void userDecidedToEndProgram()
	{
		userDecidedToEndProgram = true;
		System.out.println(resourceBundle.getString("good.bye"));
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
		System.out.println(resourceBundle.getString("what.do.you.want.to.do"));
		System.out.println(resourceBundle.getString("a.dd.book"));
		System.out.println(resourceBundle.getString("e.xit"));
		System.out.print(resourceBundle.getString("please.decide"));
	}

	private void displayBooks()
	{
		int counter = 0;

		System.out.println(resourceBundle.getString("catalog.contains.the.following.books"));
		for(Book book : this.catalog)
		{
			System.out.println("\t" + ++counter + ": " + book);
		}
		System.out.println();
	}

	private void loadBooks()
	{
		BookServiceFactory factory = BookServiceFactory.getInstance();
		BookService service = factory.get();

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
