package test.java.model.addingElements;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Point;

import main.java.model.addingElements.ElementsAdder;
import main.java.model.addingElements.FreeFieldFinder;
import main.java.model.addingElements.Randomizer;
import main.java.model.boards.BoardArea;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ElementsAdderTest {

	private static final Point RANDOM_FIELD = new Point(2, 3);
	private static final Point FIRST_FREE_FIELD = new Point(2, 3);
	private static final int RANDOM_FIELD_VALUE = 3;
	
	@Mock
	private BoardArea boardArea;
	
	@Mock
	private Randomizer randomizer;

	@Mock
	private FreeFieldFinder freeFieldFinder;

	@InjectMocks
	private ElementsAdder testObject;

	@Test
	public void tryAddNewElement_freeFieldExists_setsRandomEmptyFieldWithRandomValueAndReturnsTrue() {
		when(randomizer.generateRandomField()).thenReturn(RANDOM_FIELD);
		when(freeFieldFinder.findFirstFreeField(RANDOM_FIELD)).thenReturn(FIRST_FREE_FIELD);
		when(randomizer.generateRandomFieldValue()).thenReturn(RANDOM_FIELD_VALUE);
		
		boolean result = testObject.tryAddNewElement();

		assertEquals(result, true);
		verify(boardArea).setFieldValue(RANDOM_FIELD.x, RANDOM_FIELD.y, RANDOM_FIELD_VALUE);
	}
	
	@Test
	public void tryAddNewElement_freeFieldNotExists_returnsFalse() {
		when(randomizer.generateRandomField()).thenReturn(RANDOM_FIELD);
		when(freeFieldFinder.findFirstFreeField(RANDOM_FIELD)).thenReturn(null);
		
		boolean result = testObject.tryAddNewElement();

		assertEquals(result, false);
	}
}
