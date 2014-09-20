package test.java.model.commandOperations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static test.java.model.boards.BoardAreaTestUtils.configureBoardAreaMock;
import static test.java.model.boards.BoardAreaTestUtils.createPointsForEachElementInRow;
import static test.java.model.boards.BoardAreaTestUtils.mockIterator;
import static test.java.model.boards.BoardAreaTestUtils.mockIteratorCreator;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import main.java.model.boards.BoardArea;
import main.java.model.commandOperations.BoardLineMerger;
import main.java.model.commandOperations.BoardLineUtils;
import main.java.model.commandOperations.orientations.PointIteratorsCreator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import test.java.model.boards.FixedValue;

@RunWith(MockitoJUnitRunner.class)
public class BoardLineMergerTest {

	private static final int ROWS = 5;
	private static final int COLUMNS = 10;

	private static final int numberOfPoints = 5;
	private static final int fixedColumn = 4;
	private static final int firstRow = 0;

	private static final Point selectedLine = new Point(3, fixedColumn);

	private static final ArrayList<Point> points = createPointsForEachElementInRow(
			firstRow, fixedColumn, numberOfPoints);

	@Mock
	private BoardArea area;

	@Mock
	private BoardLineUtils utils;

	private PointIteratorsCreator lineIteratorCreator = mockIteratorCreator(points);

	@InjectMocks
	private BoardLineMerger testObject;

	@Test
	public void mergeElementsTowardsWall_emptyArea_noChanges() {
		configureBoardAreaMock(area, ROWS, COLUMNS);
		when(utils.isFieldEmpty(any(Point.class))).thenReturn(true);

		testObject.mergeMovedElementsTowardsWall(selectedLine,
				lineIteratorCreator);

		verify(area, times(0)).setFieldValue(anyInt(), anyInt(), anyInt());
	}

	@Test
	public void mergeElementsTowardsWall_onePairNextToWall_newElementCreated() {
		ArrayList<Point> points = createPointsForEachElementInRow(firstRow,
				fixedColumn, numberOfPoints);
		final Point leftPointOfFirstPair = points.get(0);
		final Point rightPointOfFirstPair = points.get(1);

		final int firstPairValue = 12;
		final int mergedValue = firstPairValue + 1;

		configureBoardAreaMock(area, ROWS, COLUMNS, new FixedValue(
				leftPointOfFirstPair, firstPairValue), new FixedValue(
				rightPointOfFirstPair, firstPairValue));

		testObject.mergeMovedElementsTowardsWall(selectedLine,
				lineIteratorCreator);

		verify(area).setFieldValue(leftPointOfFirstPair.x,
				leftPointOfFirstPair.y, mergedValue);
	}

	@Test
	public void mergeElementsTowardsWall_onePairNextToWall_swiftMovedElementsInLineTowardsWallCalled() {
		ArrayList<Point> points = createPointsForEachElementInRow(firstRow,
				fixedColumn, numberOfPoints);
		final Point leftPointOfFirstPair = points.get(0);
		final Point rightPointOfFirstPair = points.get(1);

		final int firstPairValue = 12;

		configureBoardAreaMock(area, ROWS, COLUMNS, new FixedValue(
				leftPointOfFirstPair, firstPairValue), new FixedValue(
				rightPointOfFirstPair, firstPairValue));

		when(utils.isFieldEmpty(any(Point.class))).thenReturn(true);
		when(utils.isFieldEmpty(leftPointOfFirstPair)).thenReturn(false);
		when(utils.isFieldEmpty(rightPointOfFirstPair)).thenReturn(false);

		Iterator<Point> iteratorSecond = mockIterator(points);
		when(lineIteratorCreator.iterator(rightPointOfFirstPair)).thenReturn(
				iteratorSecond);

		testObject.mergeMovedElementsTowardsWall(selectedLine,
				lineIteratorCreator);

		verify(utils).swiftMovedElementsInLineTowardsWall(iteratorSecond);
	}

	@Test
	public void mergeElementsTowardsWall_onePairNextToWallOneUnpairedPoint_newElementCreated() {
		ArrayList<Point> points = createPointsForEachElementInRow(firstRow,
				fixedColumn, numberOfPoints);
		final Point leftPointOfFirstPair = points.get(0);
		final Point rightPointOfFirstPair = points.get(1);
		final Point unpairedPoint = points.get(2);

		final int firstPairValue = 12;
		final int unpairedPointValue = 15;
		final int mergedValue = firstPairValue + 1;

		configureBoardAreaMock(area, ROWS, COLUMNS, new FixedValue(
				leftPointOfFirstPair, firstPairValue), new FixedValue(
				rightPointOfFirstPair, firstPairValue), new FixedValue(
				unpairedPoint, unpairedPointValue));

		when(utils.isFieldEmpty(any(Point.class))).thenReturn(true);
		when(utils.isFieldEmpty(leftPointOfFirstPair)).thenReturn(false);
		when(utils.isFieldEmpty(rightPointOfFirstPair)).thenReturn(false);
		when(utils.isFieldEmpty(unpairedPoint)).thenReturn(false);

		testObject.mergeMovedElementsTowardsWall(selectedLine,
				lineIteratorCreator);

		verify(area).setFieldValue(leftPointOfFirstPair.x,
				leftPointOfFirstPair.y, mergedValue);
	}

}
