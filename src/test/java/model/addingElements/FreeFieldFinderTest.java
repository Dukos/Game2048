package test.java.model.addingElements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static test.java.model.boards.BoardAreaTestUtils.configureBoardAreaMock;

import java.awt.Point;

import main.java.model.addingElements.FreeFieldFinder;
import main.java.model.boards.BoardArea;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import test.java.model.boards.FixedValue;

@RunWith(MockitoJUnitRunner.class)
public class FreeFieldFinderTest {

	private static final int ROWS = 5;
	private static final int COLUMNS = 5;

	private static final int FIRST_ROW = 0;
	private static final int FIRST_COLUMN = 0;
	private static final int LAST_ROW = ROWS - 1;
	private static final int LAST_COLUMN = COLUMNS - 1;

	private static final Point TOP_LEFT_POINT = new Point(FIRST_ROW,
			FIRST_COLUMN);
	private static final Point BOTTOM_RIGHT_POINT = new Point(LAST_ROW,
			LAST_COLUMN);

	private static final int VAULE_RESERVED = 2;

	@Mock
	private BoardArea area;

	@InjectMocks
	private FreeFieldFinder testObject;

	@Test
	public void findFirstFreeField_startPoint0x0AndEmptyArea_returnsPoint0x0() {
		configureBoardAreaMock(area, ROWS, COLUMNS);

		Point result = testObject.findFirstFreeField(TOP_LEFT_POINT);
		assertEquals(result, TOP_LEFT_POINT);
	}

	@Test
	public void findFirstFreeField_startPoint4x4AndEmptyArea_returnsPoint4x4() {
		configureBoardAreaMock(area, ROWS, COLUMNS);

		Point result = testObject.findFirstFreeField(BOTTOM_RIGHT_POINT);
		assertEquals(result, BOTTOM_RIGHT_POINT);
	}

	@Test
	public void findFirstFreeField_startPoint0x0AndPoint0x0Reserved_returnsPoint0x1() {
		FixedValue[] fixedValues = { new FixedValue(0, 0, VAULE_RESERVED) };
		configureBoardAreaMock(area, ROWS, COLUMNS, fixedValues);

		Point result = testObject.findFirstFreeField(TOP_LEFT_POINT);
		assertEquals(result, new Point(TOP_LEFT_POINT.x, TOP_LEFT_POINT.y + 1));
	}

	@Test
	public void findFirstFreeField_startPoint0x0AndPointsInFirstColumnReserved_returnsPoint1x0() {
		FixedValue[] fixedValues = {
				new FixedValue(0, 0, VAULE_RESERVED),
				new FixedValue(0, 1, VAULE_RESERVED),
				new FixedValue(0, 2, VAULE_RESERVED),
				new FixedValue(0, 3, VAULE_RESERVED),
				new FixedValue(0, 4, VAULE_RESERVED) };
		configureBoardAreaMock(area, ROWS, COLUMNS, fixedValues);

		Point result = testObject.findFirstFreeField(TOP_LEFT_POINT);
		assertEquals(result, new Point(TOP_LEFT_POINT.x + 1, TOP_LEFT_POINT.y));
	}

	@Test
	public void findFirstFreeField_startPoint0x0AndnoEmptyFields_returnsNull() {
		configureBoardAreaMock(area, ROWS, COLUMNS);
		when(area.getFieldValue(anyInt(), anyInt())).thenReturn(VAULE_RESERVED);

		Point result = testObject.findFirstFreeField(TOP_LEFT_POINT);
		assertTrue(result == null);
	}

	@Test
	public void findFirstFreeField_startPoint4x4AndPoint4x4IsReserved_returnsPoint0x0() {
		configureBoardAreaMock(area, ROWS, COLUMNS, new FixedValue(
				BOTTOM_RIGHT_POINT.x, BOTTOM_RIGHT_POINT.y, VAULE_RESERVED));

		Point result = testObject.findFirstFreeField(BOTTOM_RIGHT_POINT);
		assertEquals(result, TOP_LEFT_POINT);
	}

}
