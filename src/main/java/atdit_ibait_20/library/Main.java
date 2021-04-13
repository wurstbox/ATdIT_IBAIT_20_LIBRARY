package atdit_ibait_20.library;

import atdit_ibait_20.library.model.AbstractLibraryFactory;
import atdit_ibait_20.library.model.implementation.BasicLibraryFactory;
import atdit_ibait_20.library.persistence.BookServiceFactory;
import atdit_ibait_20.library.persistence.implementation.MockBookServiceFactory;
import atdit_ibait_20.library.presentation.Presentation;
import atdit_ibait_20.library.presentation.implementation.ConsolePresentation;

public class Main
{
	public static void main(String[] args)
	{
		BookServiceFactory.defaultImplementation = new MockBookServiceFactory();
		AbstractLibraryFactory.defaultInstance = new BasicLibraryFactory();

		Presentation presentation;
		presentation = new ConsolePresentation();
		presentation.start();
	}
}
