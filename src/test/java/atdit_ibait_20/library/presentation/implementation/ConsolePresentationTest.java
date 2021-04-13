package atdit_ibait_20.library.presentation.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConsolePresentationTest
{
	@Test
	void ifUserEntersE_thenExitProgram()
	{
		ConsolePresentation p = new ConsolePresentation();
		p.consoleInputRequester = new ConsolePresentation.ConsoleInputRequester()
		{
			private int numberOfRequests = 0;

			@Override
			public String requestInput()
			{
				if(numberOfRequests == 0)
				{
					numberOfRequests++;
					return "E";
				}
				else
				{
					throw new RuntimeException("");
				}
			}
		};

		try
		{
			p.start();
		}
		catch(RuntimeException e)
		{
			Assertions.fail("Program did not exit");
		}
	}
}
