package test.java.model.addingElements;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.awt.Point;
import java.util.Random;

import main.java.model.addingElements.Randomizer;
import main.java.model.boards.Dimension;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RandomizerTest {

	private static final int FIRST_NEXT_INT_RANDOM_VALUE = 3;
	private static final int SECOND_NEXT_INT_RANDOM_VALUE = 4;

	@Spy
	private Dimension dimension = new Dimension(4,5);
	
	@Mock
	private Random random;

	@InjectMocks
	private Randomizer testObject;
	
	@Before
	public void init()
	{
		when(random.nextInt(anyInt())).thenReturn(FIRST_NEXT_INT_RANDOM_VALUE,
				SECOND_NEXT_INT_RANDOM_VALUE);		
	}

	@Test
	public void generateRandomField_noSetup_createsPointWithRandomValues() {
		final Point point = testObject.generateRandomField();
		assertEquals(point, new Point(FIRST_NEXT_INT_RANDOM_VALUE,
				SECOND_NEXT_INT_RANDOM_VALUE));
	}

	@Test
	public void generateRandomFieldValue_emptyBoard_modifiesSelectedPointWithCorrectRandomValue() {
		final int v = testObject.generateRandomFieldValue();
		assertEquals(v, FIRST_NEXT_INT_RANDOM_VALUE + 1);
	}
}
