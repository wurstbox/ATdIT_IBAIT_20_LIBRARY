package atdit_ibait_20.library.model.implementation;

import atdit_ibait_20.library.model.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class BasicAuthorTest
{
	@Test
	void ifAuthorsHaveIdenticalName_thenEqualsMustReturnTrue()
	{
//  assemble
		Author author1 = new BasicAuthor("Hans");
		Author author2 = new BasicAuthor("Hans");

//  act
		boolean equal = Objects.equals(author1, author2);

//  assert
		Assertions.assertTrue(equal);
	}

	@Test
	void identicalAuthorsMustProduceIdenticalHashCode()
	{
		Author author1 = new BasicAuthor("Hans");
		Author author2 = new BasicAuthor("Hans");

		int hash1 = author1.hashCode();
		int hash2 = author2.hashCode();

		Assertions.assertEquals(hash1, hash2);
	}
}
