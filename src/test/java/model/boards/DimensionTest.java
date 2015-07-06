package test.java.model.boards;

import main.java.model.boards.Dimension;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

public class DimensionTest {

	@Test
	public void equalsContract()
	{
	    EqualsVerifier.forClass(Dimension.class).usingGetClass().verify();
	}
}
