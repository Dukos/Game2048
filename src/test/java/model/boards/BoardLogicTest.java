package test.java.model.boards;

import main.java.model.addingElements.ElementsAdder;
import main.java.model.commandOperations.CommandsListener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BoardLogicTest {

	private static final int FIXED_NUMBER = 1;
	private static final int FIXED_COLUMN_VALUE = 3;
	private static final int FIXED_ROW_VALUE = 3;

	@InjectMocks
	private CommandsListener testObject;

	@Mock
	private ElementsAdder randomizer;

	@Test
	public void applyRandomObject_cleanBoardArea_addedRandomObject() {
		// when(randomizer.findRandomEmptyField(any(ReadOnlyBoardArea.class))).thenReturn(new
		// Dimension(FIXED_ROW_VALUE, FIXED_COLUMN_VALUE));
		// (randomizer.createRandomValue()).thenReturn(FIXED_NUMBER);
		// testObject.applyRandomObject();
		// int value = testObject.getFieldValue(FIXED_ROW_VALUE,
		// FIXED_COLUMN_VALUE);
		// assertEquals(value, FIXED_NUMBER);
	}

	@Test
	public void commandLeft_cleanBoardArea_nothingHappens() {
		testObject.left();
	}
}
