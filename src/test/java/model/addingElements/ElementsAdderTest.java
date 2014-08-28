package test.java.model.addingElements;

import static org.mockito.Mockito.verify;

import java.awt.Point;

import static org.mockito.Mockito.when;


import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

import main.java.model.addingElements.ElementsAdder;
import main.java.model.addingElements.FreeFieldFinder;
import main.java.model.addingElements.Randomizer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ElementsAdderTest {
	
	private static final Point RANDOM_FIELD = new Point(2, 3);
	private static final Point FIRST_FREE_FIELD = new Point(2, 3);
	
	@Mock
	private Randomizer randomizer;
	
	@Mock
	private FreeFieldFinder freeFieldFinder;
	
	@InjectMocks
	private ElementsAdder testObject;

	@Test
	public void tryAddNewElement_NoSetup_getRandomFieldCalled() {
		testObject.tryAddNewElement();
		
		verify(randomizer).getRandomField();
	}

	@Test
	public void tryAddNewElement_NoSetup_findFirstFreeFieldCalledForRandomField() {
		when(randomizer.getRandomField()).thenReturn(RANDOM_FIELD);
		testObject.tryAddNewElement();
		
		verify(freeFieldFinder).findFirstFreeField(RANDOM_FIELD);
	}
	
	@Test
	public void tryAddNewElement_NoFreeFieldFound_returnsFalse() {
		//just to be explicit
		when(freeFieldFinder.findFirstFreeField(any(Point.class))).thenReturn(null);
		
		final boolean result = testObject.tryAddNewElement();
		
		assertEquals(result, false);
	}
	
	@Test
	public void tryAddNewElement_FreeFieldFounded_returnsTrue() {
		when(freeFieldFinder.findFirstFreeField(any(Point.class))).thenReturn(FIRST_FREE_FIELD);
		
		final boolean result = testObject.tryAddNewElement();
		
		assertEquals(result, true);
	}
	
	@Test
	public void tryAddNewElement_FreeFieldFounded_setRandomFieldValueCalledForFirstFreeField() {
		when(freeFieldFinder.findFirstFreeField(any(Point.class))).thenReturn(FIRST_FREE_FIELD);
		
		testObject.tryAddNewElement();
		
		verify(randomizer).setRandomFieldValue(FIRST_FREE_FIELD);
	}
}
