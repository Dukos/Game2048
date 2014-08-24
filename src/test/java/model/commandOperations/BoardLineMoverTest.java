package test.java.model.commandOperations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static test.java.model.boards.BoardAreaTestUtils.createPointsForEachElementInRow;
import static test.java.model.boards.BoardAreaTestUtils.mockIterator;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import main.java.model.boards.BoardArea;
import main.java.model.commandOperations.BoardLineMover;
import main.java.model.commandOperations.BoardLineUtils;
import main.java.model.commandOperations.orientations.PointIteratorsCreator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BoardLineMoverTest {

	class BoardLineMoverTested extends BoardLineMover
	{
		BoardLineMoverTested()
		{
			super(null);
		}
		@Override
		protected BoardLineUtils createUtils(BoardArea area)
		{
			return utils;
		}
	}
	private BoardLineMover testObject;
	
	@Mock
	private BoardLineUtils utils;

	@Mock
	private PointIteratorsCreator lineIteratorCreator;
	
	private Iterator<Point> iteratorSecond;
	
	final int numberOfPoints = 5;
	final int fixedColumn = 4;
	final int firstRow = 0;
	
	final Point selectedLine = new Point(3,fixedColumn);
	final Point firstElement = new Point(firstRow, fixedColumn);
	final Point nonEmptyElementDistant1PointFromWall = new Point(firstRow + 1, fixedColumn);
	final Point nonEmptyElementDistant2PointsFromWall = new Point(firstRow + 2, fixedColumn);
	
	@Before
	public void setUp()throws Exception {
		MockitoAnnotations.initMocks(this);
		testObject = new BoardLineMoverTested();
		mockLineIteratorsCreatorAndIteratorSecond();
	}

	@Test
	public void moveElementsTowardsWall_OneElementOnTheWall_noValuesSwapped()
	{
		mockIsFieldEmptyMethod(firstElement);
		
		testObject.moveElementsTowardsWall(selectedLine, lineIteratorCreator);	
		
		verify(utils, times(0)).swapValues(any(Point.class), any(Point.class));
	}
	
	@Test
	public void moveElementsTowardsWall_OneElementDistantFromTheWall_elementMovedTowardsWall()
	{	
		mockIsFieldEmptyMethod(nonEmptyElementDistant2PointsFromWall);
		
		when(utils.findFirstFreeFieldBeforeElement(iteratorSecond, nonEmptyElementDistant2PointsFromWall)).thenReturn(firstElement);
		
		testObject.moveElementsTowardsWall(selectedLine, lineIteratorCreator);	
		
		verify(utils).swapValues(firstElement, nonEmptyElementDistant2PointsFromWall);
		verify(utils).swapValues(any(Point.class), any(Point.class));
	}
	
	@Test
	public void moveElementsTowardsWall_TwoElementsOneDistantFromTheWall_elementMovedTowardsWall()
	{	
		mockIsFieldEmptyMethod(nonEmptyElementDistant2PointsFromWall, firstElement);
		when(utils.findFirstFreeFieldBeforeElement(iteratorSecond, nonEmptyElementDistant2PointsFromWall)).thenReturn(firstElement);
		
		testObject.moveElementsTowardsWall(selectedLine, lineIteratorCreator);	
		
		verify(utils).swapValues(firstElement, nonEmptyElementDistant2PointsFromWall);
		verify(utils).swapValues(any(Point.class), any(Point.class));
	}
	
	@Test
	public void moveElementsTowardsWall_TwoElementsBothNearTheWall_noValuesSwapped()
	{
		mockIsFieldEmptyMethod(nonEmptyElementDistant1PointFromWall, firstElement);
		when(utils.findFirstFreeFieldBeforeElement(iteratorSecond, nonEmptyElementDistant1PointFromWall)).thenReturn(null);
		
		testObject.moveElementsTowardsWall(selectedLine, lineIteratorCreator);	
		
		verify(utils, times(0)).swapValues(any(Point.class), any(Point.class));
	}
	
	private void mockIsFieldEmptyMethod(Point...points) {
		when(utils.isFieldEmpty(any(Point.class))).thenReturn(true);
		for(Point p : points)
			when(utils.isFieldEmpty(p)).thenReturn(false);
	}

	@SuppressWarnings("unchecked")
	private void mockLineIteratorsCreatorAndIteratorSecond()
	{
		ArrayList<Point> points = createPointsForEachElementInRow(firstRow, fixedColumn, numberOfPoints);
		Iterator<Point> iteratorFirst = mockIterator(points);
		iteratorSecond = mockIterator(new ArrayList<Point>());
		
		when(lineIteratorCreator.firstElement(any(Point.class))).thenReturn(firstElement);
		when(lineIteratorCreator.iterator(firstElement)).thenReturn(iteratorFirst, iteratorSecond);
	}

}
