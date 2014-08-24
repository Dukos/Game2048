package test.java.model.commandOperations;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;


import main.java.model.boards.BoardArea;
import main.java.model.commandOperations.BoardLineMerger;
import main.java.model.commandOperations.BoardLineUtils;
import main.java.model.commandOperations.orientations.PointIteratorsCreator;

import static test.java.model.boards.BoardAreaTestUtils.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BoardLineMergerTest {

	final int numberOfPoints = 5;
	final int fixedColumn = 4;
	final int firstRow = 0;
	
	final int emptyField = 0;
	
	final Point selectedLine = new Point(3,fixedColumn);
	final Point firstElement = new Point(firstRow, fixedColumn);
	
	@InjectMocks
	BoardLineMerger testObject;
	
	@Mock
	private BoardArea area;
	
	@Mock
	private BoardLineUtils utils;
	
	@Mock
	private PointIteratorsCreator lineIteratorCreator;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void mergeElementsTowardsWall_emptyArea_noChanges() {
		when(area.getFieldValue(anyInt(), anyInt())).thenReturn(emptyField);
		mockLineIteratorCreator();
		
		testObject.mergeMovedElementsTowardsWall(selectedLine, lineIteratorCreator);
		
		verify(area, times(0)).setFieldValue(anyInt(), anyInt(), anyInt());
	}
	
	@Test
	public void mergeElementsTowardsWall_onePairNextToWall_newElementCreated() {
		ArrayList<Point> points = createPointsForEachElementInRow(firstRow, fixedColumn, numberOfPoints);
		final Point leftPointOfFirstPair = points.get(0);
		final Point rightPointOfFirstPair = points.get(1);
		
		final int firstPairValue = 12;
		final int mergedValue = firstPairValue + 1;
		
		when(area.getFieldValue(anyInt(), anyInt())).thenReturn(emptyField);
		when(area.getFieldValue(leftPointOfFirstPair.x, leftPointOfFirstPair.y)).thenReturn(firstPairValue);
		when(area.getFieldValue(rightPointOfFirstPair.x, rightPointOfFirstPair.y)).thenReturn(firstPairValue);
		
		mockLineIteratorCreator();
		
		testObject.mergeMovedElementsTowardsWall(selectedLine, lineIteratorCreator);
		
		verify(area).setFieldValue(leftPointOfFirstPair.x, leftPointOfFirstPair.y, mergedValue);
	}
	
	@Test
	public void mergeElementsTowardsWall_onePairNextToWall_EmptyElementPutInSecondPointPlace() {
		ArrayList<Point> points = createPointsForEachElementInRow(firstRow, fixedColumn, numberOfPoints);
		final Point leftPointOfFirstPair = points.get(0);
		final Point rightPointOfFirstPair = points.get(1);
		
		final int firstPairValue = 12;
		
		when(area.getFieldValue(anyInt(), anyInt())).thenReturn(emptyField);
		when(area.getFieldValue(leftPointOfFirstPair.x, leftPointOfFirstPair.y)).thenReturn(firstPairValue);
		when(area.getFieldValue(rightPointOfFirstPair.x, rightPointOfFirstPair.y)).thenReturn(firstPairValue);
		
		mockLineIteratorCreator();
		
		testObject.mergeMovedElementsTowardsWall(selectedLine, lineIteratorCreator);
		
		verify(area).setFieldValue(rightPointOfFirstPair.x, rightPointOfFirstPair.y, emptyField);
	}
	
	@Test
	public void mergeElementsTowardsWall_onePairNextToWallOneUnpairedPoint_newElementCreated() {
		ArrayList<Point> points = createPointsForEachElementInRow(firstRow, fixedColumn, numberOfPoints);
		final Point leftPointOfFirstPair = points.get(0);
		final Point rightPointOfFirstPair = points.get(1);
		final Point unpairedPoint = points.get(2);
		
		final int firstPairValue = 12;
		final int unpairedPointValue = 15;
		final int mergedValue = firstPairValue + 1;
		
		when(area.getFieldValue(anyInt(), anyInt())).thenReturn(emptyField);
		when(area.getFieldValue(leftPointOfFirstPair.x, leftPointOfFirstPair.y)).thenReturn(firstPairValue);
		when(area.getFieldValue(rightPointOfFirstPair.x, rightPointOfFirstPair.y)).thenReturn(firstPairValue);
		when(area.getFieldValue(unpairedPoint.x, unpairedPoint.y)).thenReturn(unpairedPointValue);
		
		mockLineIteratorCreator();
		
		testObject.mergeMovedElementsTowardsWall(selectedLine, lineIteratorCreator);
		
		verify(area).setFieldValue(leftPointOfFirstPair.x, leftPointOfFirstPair.y, mergedValue);
	}

	private void mockLineIteratorCreator() {
		ArrayList<Point> points = createPointsForEachElementInRow(firstRow, fixedColumn, numberOfPoints);
		Iterator<Point> iteratorFirst = mockIterator(points);
		when(lineIteratorCreator.firstElement(any(Point.class))).thenReturn(firstElement);
		when(lineIteratorCreator.iterator(firstElement)).thenReturn(iteratorFirst);
	}

}
