package test.java.model.addingElements;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Random;

import main.java.model.addingElements.ElementsAdder;
import main.java.model.boards.BoardArea;
import main.java.model.commandOperations.orientations.PointIteratorsCreator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RandomizerTest {

	private static int FIXED_ROW = 5;
	private static int FIXED_COLUMN = 1;
	private static int FIXED_VALUE = 2;
	
	private static final int MAX_VALUE_FOR_CREATED_ELEMENT = 2;
	private static final int FREE_FIELD = 0;
	private static final int RESERVED_FIELD = 1;
	
	private static int COLUMNS = 5;
	private static int ROWS = 6;
	
	@Mock
	BoardArea area;
	
	@Mock
	private Random random;
	
	@Mock
	private PointIteratorsCreator verticalIteratorsCreator;
	
	@Mock
	private PointIteratorsCreator horizontalIteratorsCreator;
	
	@InjectMocks
	ElementsAdder testObject;
	
	@Test
	public void addNewElement_emptyArea_elementAdded() {
		when(area.getFieldValue(anyInt(), anyInt())).thenReturn(FREE_FIELD);
		when(area.getColumns()).thenReturn(COLUMNS);
		when(area.getRows()).thenReturn(ROWS);
		
		when(random.nextInt(COLUMNS)).thenReturn(FIXED_COLUMN);
		when(random.nextInt(ROWS)).thenReturn(FIXED_ROW);
		when(random.nextInt(MAX_VALUE_FOR_CREATED_ELEMENT)).thenReturn(FIXED_VALUE);
		
		testObject.tryAddNewElement();
		
		verify(area).setFieldValue(FIXED_ROW, FIXED_COLUMN, FIXED_VALUE);
	}
	
	@Test
	public void addNewElement_conflictingPlaceInArea_elementAddedInNextFreePlace() {
		when(area.getFieldValue(anyInt(), anyInt())).thenReturn(FREE_FIELD);
		when(area.getFieldValue(FIXED_ROW, FIXED_COLUMN)).thenReturn(RESERVED_FIELD);
		
		when(area.getColumns()).thenReturn(COLUMNS);
		when(area.getRows()).thenReturn(ROWS);
		
		//ArrayList<Point> points = createPointsForEachElementInRow(firstRow, fixedColumn, numberOfPoints);
		//ArrayList<Point> points = {new Point(1,2)}.asA;
		//Iterator<Point> iteratorFirst = mockIterator(points);
		//when(lineIteratorCreator.firstElement(any(Point.class))).thenReturn(firstElement);
		//when(lineIteratorCreator.iterator(firstElement)).thenReturn(iteratorFirst);
		
		//testObject.addNewElement();
		
		verify(area).setFieldValue(FIXED_ROW + 1, FIXED_COLUMN, FIXED_VALUE);
	}
	
	private void configureRandomMock()
	{
		when(random.nextInt(COLUMNS)).thenReturn(FIXED_COLUMN);
	}

}
