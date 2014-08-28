package test.java.model.addingElements;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static test.java.model.boards.BoardAreaTestUtils.configureBoardAreaMock;


import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.Random;

import main.java.model.addingElements.Randomizer;
import main.java.model.boards.BoardArea;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RandomizerTest {

	private static final int FIRST_NEXT_INT_RANDOM_VALUE = 3;
	private static final int SECOND_NEXT_INT_RANDOM_VALUE = 4;
	
	private static int COLUMNS = 5;
	private static int ROWS = 6;
	
	private static final int MAX_VALUE_FOR_CREATED_ELEMENT = 2;
	
	@Mock
	BoardArea area;
	
	@Mock
	private Random random;
	
	@InjectMocks
	Randomizer testObject;
	
	@Test
	public void getRandomField_noSetup_callsNextInProperRange() {
		configureBoardAreaMock(area, ROWS, COLUMNS);
		when(random.nextInt(anyInt())).thenReturn(FIRST_NEXT_INT_RANDOM_VALUE, SECOND_NEXT_INT_RANDOM_VALUE);
		
		testObject.getRandomField();
		
		verify(random).nextInt(ROWS);
		verify(random).nextInt(COLUMNS);
	}
	
	@Test
	public void getRandomField_noSetup_returnsCorrectPoint() {
		configureBoardAreaMock(area, ROWS, COLUMNS);
		when(random.nextInt(anyInt())).thenReturn(FIRST_NEXT_INT_RANDOM_VALUE, SECOND_NEXT_INT_RANDOM_VALUE);
		
		final Point point = testObject.getRandomField();
		assertEquals(point, new Point(FIRST_NEXT_INT_RANDOM_VALUE, SECOND_NEXT_INT_RANDOM_VALUE));
	}
	
	@Test
	public void setRandomFieldValue_noSetup_callsNextInProperRange() {
		configureBoardAreaMock(area, ROWS, COLUMNS);
		when(random.nextInt(anyInt())).thenReturn(FIRST_NEXT_INT_RANDOM_VALUE);
		final Point anyPoint = new Point(0, 0);
		
		testObject.setRandomFieldValue(anyPoint);
		
		verify(random).nextInt(MAX_VALUE_FOR_CREATED_ELEMENT - 1);
	}
	
	@Test
	public void setRandomFieldValue_noSetup_modifiesCorrectPoint() {
		configureBoardAreaMock(area, ROWS, COLUMNS);
		when(random.nextInt(anyInt())).thenReturn(FIRST_NEXT_INT_RANDOM_VALUE);
		final Point selectedPoint = new Point(0, 0);
		
		testObject.setRandomFieldValue(selectedPoint);
		
		verify(area).setFieldValue(selectedPoint.x, selectedPoint.y, FIRST_NEXT_INT_RANDOM_VALUE + 1);
	}
}
