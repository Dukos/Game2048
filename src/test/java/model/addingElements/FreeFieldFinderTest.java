package test.java.model.addingElements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static test.java.model.boards.BoardAreaTestUtils.configureBoardAreaMock2;

import java.awt.Point;

import main.java.model.addingElements.FreeFieldFinder;
import main.java.model.boards.BoardArea;
import main.java.model.boards.Dimension;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FreeFieldFinderTest {

	private static final int ROWS = 5;
	private static final int COLUMNS = 5;

	private static final Point TOP_LEFT_POINT = new Point(0, 0);
	private static final Point BOTTOM_RIGHT_POINT = new Point(ROWS - 1, COLUMNS - 1);

	private static final int __FREE__ = 0;
	private static final int RESERVED = getAnyNonZeroValue();

	@Mock
	private BoardArea area;

	@InjectMocks
	private FreeFieldFinder testObject;

	@Test
	public void findFirstFreeField_entireBoardIsReserved_returnsNull() {

		when(area.getDimensions()).thenReturn(new Dimension(ROWS, COLUMNS));
		when(area.getFieldValue(anyInt(), anyInt())).thenReturn(RESERVED);

		Point result = testObject.findFirstFreeField(TOP_LEFT_POINT);
		assertTrue(result == null);
	}

	@Test
	public void findFirstFreeField_startingPointIsLastPointAndIsReserved_returnsFirstFreePoint() {
		configureBoardAreaMock2(area, ROWS, COLUMNS,
				RESERVED, __FREE__, __FREE__, __FREE__, __FREE__,
				RESERVED, __FREE__, __FREE__, __FREE__, __FREE__,
				RESERVED, __FREE__, __FREE__, __FREE__, __FREE__,
				RESERVED, __FREE__, __FREE__, __FREE__, __FREE__,
				RESERVED, __FREE__, __FREE__, __FREE__, RESERVED
				);

		Point freePoint = new Point(1, 1);
		Point result1 = testObject.findFirstFreeField(freePoint);
		assertEquals(result1, freePoint);

		Point result2 = testObject.findFirstFreeField(BOTTOM_RIGHT_POINT);
		assertEquals(result2, new Point(TOP_LEFT_POINT.x + 1, TOP_LEFT_POINT.y));
	}

	static int getAnyNonZeroValue() {
		return 2;
	}
}
