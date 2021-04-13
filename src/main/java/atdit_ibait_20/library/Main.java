package atdit_ibait_20.library;

import atdit_ibait_20.library.presentation.Presentation;
import atdit_ibait_20.library.presentation.implementation.ConsolePresentation;
import atdit_ibait_20.library.presentation.implementation.JavaFXPresentation;

public class Main
{
	public static void main(String[] args)
	{
		Presentation presentation;
		presentation = new ConsolePresentation();
		presentation.start();
	}
}
