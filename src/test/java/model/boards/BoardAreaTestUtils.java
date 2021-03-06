package test.java.model.boards;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import main.java.model.boards.Dimension;
import main.java.model.boards.ReadOnlyBoardArea;
import main.java.model.commandOperations.orientations.PointIteratorsCreator;

import org.mockito.internal.stubbing.answers.ReturnsElementsOf;

public class BoardAreaTestUtils {
	public static void configureBoardAreaMock(ReadOnlyBoardArea area, int rows,
			int columns, FixedValue... fixedValues) {

		when(area.getDimensions()).thenReturn(new Dimension(rows, columns));

		when(area.getFieldValue(anyInt(), anyInt())).thenReturn(0);
		for (FixedValue fv : fixedValues)
			when(area.getFieldValue(fv.x, fv.y)).thenReturn(fv.value);
	}
	
	public static void configureBoardAreaMock2(ReadOnlyBoardArea area, int rows,
			int columns, int... values)
	{
		assert(values.length == rows * columns);
		when(area.getDimensions()).thenReturn(new Dimension(rows, columns));
		for (int pos = 0; pos < values.length; ++pos)
			when(area.getFieldValue(pos % columns, pos / columns)).thenReturn(values[pos]);
	}

	public static Iterator<Point> mockIterator(ArrayList<Point> nextResponses) {

		@SuppressWarnings("unchecked")
		Iterator<Point> iterator = mock(Iterator.class);

		final ArrayList<Boolean> hasNextResponses = new ArrayList<Boolean>();
		for (int i = 0; i < nextResponses.size(); i++)
			hasNextResponses.add(true);
		hasNextResponses.add(false);

		when(iterator.hasNext()).then(new ReturnsElementsOf(hasNextResponses));
		when(iterator.next()).then(new ReturnsElementsOf(nextResponses));

		return iterator;
	}

	public static PointIteratorsCreator mockIteratorCreator(
			ArrayList<Point> nextResponses) {
		PointIteratorsCreator iteratorsCreator = mock(PointIteratorsCreator.class);
		Point firstElement = nextResponses.get(0);

		Iterator<Point> iterator = mockIterator(nextResponses);
		when(iteratorsCreator.firstElement(any(Point.class))).thenReturn(
				firstElement);
		when(iteratorsCreator.iterator(any(Point.class))).thenReturn(iterator);

		return iteratorsCreator;
	}

	public static ArrayList<Point> createPointsForEachElementInRow(
			int firstRow, int fixedColumn, int numberOfPoints) {
		final ArrayList<Point> nextResponses = new ArrayList<Point>();
		for (int i = firstRow; i < numberOfPoints; i++)
			nextResponses.add(new Point(i, fixedColumn));

		return nextResponses;
	}

	public static ArrayList<Point> createPointsForEachElementInColumn(
			int firstColumn, int fixedRow, int numberOfPoints) {
		final ArrayList<Point> nextResponses = new ArrayList<Point>();
		for (int i = firstColumn; i < numberOfPoints; i++)
			nextResponses.add(new Point(fixedRow, i));

		return nextResponses;
	}
}
