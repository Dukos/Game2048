package test.java.model.commandOperations;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalMatchers.gt;
import static org.mockito.AdditionalMatchers.leq;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import main.java.model.boards.BoardArea;
import main.java.model.commandOperations.BoardLineUtils;

import static test.java.model.boards.BoardAreaTestUtils.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.stubbing.answers.ReturnsElementsOf;
import org.mockito.runners.MockitoJUnitRunner;

import test.java.model.boards.FixedValue;

@RunWith(MockitoJUnitRunner.class)
public class BoardLineUtilsTest {

	private final FixedValue fixedValue1 = new FixedValue(4, 1, 5);
	private final FixedValue fixedValue2 = new FixedValue(4, 3, 1);
	
	@InjectMocks
	private BoardLineUtils testObject;
	
	@Mock
	private BoardArea area;
	
	private final int numberOfPoints = 5;
	private final int fixedColumn = 4;
	private final int firstRow = 0;

	@Test
	public void swapValues_twoDifferentPointsWithDifferentValues_valuesAreSwapped() {
		when(area.getFieldValue(fixedValue1.x, fixedValue1.y))
				.thenReturn(fixedValue1.value);
		when(area.getFieldValue(fixedValue2.x, fixedValue2.y))
				.thenReturn(fixedValue2.value);

		final Point p1 = new Point(fixedValue1.x, fixedValue1.y);
		final Point p2 = new Point(fixedValue2.x, fixedValue2.y);

		testObject.swapValues(p1, p2);

		verify(area).setFieldValue(fixedValue1.x, fixedValue1.y,
				fixedValue2.value);
		verify(area).setFieldValue(fixedValue2.x, fixedValue2.y,
				fixedValue1.value);
	}

	@Test
	public void findFirstFreeFieldBeforeElement_emptyArea_returnsFirstPoint() {
		ArrayList<Point> points = createPointsForEachElementInRow(firstRow, fixedColumn, numberOfPoints);
		Iterator<Point> iterator = mockIterator(points);
		
		when(area.getFieldValue(anyInt(), eq(fixedColumn))).thenReturn(0);

		Point endPoint = points.get(points.size() - 1);
		final Point result = testObject.findFirstFreeFieldBeforeElement(iterator, endPoint);
		final Point expectedResult = points.get(0);  
		
		assertEquals(result, expectedResult);
	}
	
	@Test
	public void findFirstFreeFieldBeforeElement_firstPointNonEmpty_returnsSecondPoint() {
		ArrayList<Point> points = createPointsForEachElementInRow(firstRow, fixedColumn, numberOfPoints);
		Iterator<Point> iterator = mockIterator(points);
		
		when(area.getFieldValue(anyInt(), eq(fixedColumn))).thenReturn(0);
		when(area.getFieldValue(points.get(0).x, points.get(0).y)).thenReturn(5);

		Point endPoint = points.get(points.size() - 1);
		final Point result = testObject.findFirstFreeFieldBeforeElement(iterator, endPoint);
		final Point expectedResult = points.get(1);  
		
		assertEquals(result, expectedResult);
	}
	
	@Test
	public void findFirstFreeFieldBeforeElement_allFieldsNonEmpty_returnsNull() {
		ArrayList<Point> points = createPointsForEachElementInRow(firstRow, fixedColumn, numberOfPoints);
		Iterator<Point> iterator = mockIterator(points);
		
		when(area.getFieldValue(anyInt(), eq(fixedColumn))).thenReturn(5);

		Point endPoint = points.get(points.size() - 1);
		final Point result = testObject.findFirstFreeFieldBeforeElement(iterator, endPoint);
		final Point expectedResult = null;  
		
		assertEquals(result, expectedResult);
	}
	
	@Test
	public void findFirstFreeFieldBeforeElement_allButLastFieldsNonEmptyEndPointOneBeforeLast_returnsNull() {
		ArrayList<Point> points = createPointsForEachElementInRow(firstRow, fixedColumn, numberOfPoints);
		Iterator<Point> iterator = mockIterator(points);
		
		when(area.getFieldValue(leq(3), eq(fixedColumn))).thenReturn(5);
		when(area.getFieldValue(gt(3), eq(fixedColumn))).thenReturn(0);

		Point endPoint = new Point(3, fixedColumn);
		final Point result = testObject.findFirstFreeFieldBeforeElement(iterator, endPoint);
		final Point expectedResult = null;  
		
		assertEquals(result, expectedResult);
	}
	
	@Test
	public void swiftMovedElementsInLineTowardsWall_startPoint0x4_valuesSwifted()
	{
		ArrayList<Point> points = createPointsForEachElementInRow(firstRow, fixedColumn, numberOfPoints);
		Iterator<Point> iterator = mockIterator(points);
		
		for(int i = 0; i < points.size(); i++)
			when(area.getFieldValue(i, fixedColumn)).thenReturn(i + 1);
		
		testObject.swiftMovedElementsInLineTowardsWall(iterator);
		
		for(int i = 0; i < points.size() - 1; i++)
		{
			Point current = points.get(i);
			int newValueForCurrent = firstRow + i + 2;
			verify(area).setFieldValue(current.x, current.y, newValueForCurrent);
		}
	}
	
	@Test
	public void swiftMovedElementsInLineTowardsWall_startPoint0x4AllFieldsWithDifferentValues_lastValueIsZero()
	{
		ArrayList<Point> points = createPointsForEachElementInRow(firstRow, fixedColumn, numberOfPoints);
		Iterator<Point> iterator = mockIterator(points);
		
		for(int i = 0; i < points.size(); i++)
			when(area.getFieldValue(i, fixedColumn)).thenReturn(i + 1);
		
		testObject.swiftMovedElementsInLineTowardsWall(iterator);
		
		final Point lastChangedPoint = points.get(points.size() - 1);
		final int expectedValueForLastPoint = 0;
		verify(area).setFieldValue(lastChangedPoint.x, lastChangedPoint.y, expectedValueForLastPoint);
	}
	
	@Test
	public void swiftMovedElementsInLineTowardsWall_startPoint0x4EmptyPoint1x4_noValuesCheckedOutsideOfEmptyPoint()
	{
		ArrayList<Point> points = createPointsForEachElementInRow(firstRow, fixedColumn, numberOfPoints);
		Iterator<Point> iterator = mockIterator(points);
		
		final int emptyElementRow = firstRow + 2;
		final int nextRowAfterEmptyElement = emptyElementRow + 1;
		
		for(int i = 0; i < points.size(); i++)
			when(area.getFieldValue(i, fixedColumn)).thenReturn(i + 1);
		//override
		when(area.getFieldValue(emptyElementRow, fixedColumn)).thenReturn(0);
		
		testObject.swiftMovedElementsInLineTowardsWall(iterator);
		
		verify(area, times(0)).getFieldValue(nextRowAfterEmptyElement, fixedColumn);
	}

}
