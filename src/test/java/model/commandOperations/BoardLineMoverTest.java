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

import main.java.model.commandOperations.BoardLineMover;
import main.java.model.commandOperations.BoardLineUtils;
import main.java.model.commandOperations.orientations.PointIteratorsCreator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BoardLineMoverTest {

	@Mock
	private BoardLineUtils utils;

	@Mock
	private PointIteratorsCreator lineIteratorCreator;

	@InjectMocks
	private BoardLineMover testObject;

	private Iterator<Point> iteratorSecond;

	private static final int NUMBER_OF_POINTS = 5;
	private static final int FIXED_COLUMN = 4;
	private static final int FIRST_ROW = 0;

	private static final Point SELECTED_LINE = new Point(3, FIXED_COLUMN);
	private static final Point FIRST_ELEMENT = new Point(FIRST_ROW,
			FIXED_COLUMN);
	private static final Point NONEMPTY_ELEMENT_DISTANT_1_POINT_FROM_WALL = new Point(
			FIRST_ROW + 1, FIXED_COLUMN);
	private static final Point NONEMPTY_ELEMENT_DISTANT_2_POINTS_FROM_WALL = new Point(
			FIRST_ROW + 2, FIXED_COLUMN);

	@Before
	public void setUp() throws Exception {
		mockLineIteratorsCreatorAndIteratorSecond();
	}

	@Test
	public void moveElementsTowardsWall_OneElementOnTheWall_noValuesSwapped() {
		mockIsFieldEmptyMethod(FIRST_ELEMENT);

		testObject.moveElementsTowardsWall(SELECTED_LINE, lineIteratorCreator);

		verify(utils, times(0)).swapValues(any(Point.class), any(Point.class));
	}

	@Test
	public void moveElementsTowardsWall_OneElementDistantFromTheWall_elementMovedTowardsWall() {
		mockIsFieldEmptyMethod(NONEMPTY_ELEMENT_DISTANT_2_POINTS_FROM_WALL);

		when(
				utils.findFirstFreeFieldBeforeElement(iteratorSecond,
						NONEMPTY_ELEMENT_DISTANT_2_POINTS_FROM_WALL))
				.thenReturn(FIRST_ELEMENT);

		testObject.moveElementsTowardsWall(SELECTED_LINE, lineIteratorCreator);

		verify(utils).swapValues(FIRST_ELEMENT,
				NONEMPTY_ELEMENT_DISTANT_2_POINTS_FROM_WALL);
		verify(utils).swapValues(any(Point.class), any(Point.class));
	}

	@Test
	public void moveElementsTowardsWall_TwoElementsOneDistantFromTheWall_elementMovedTowardsWall() {
		mockIsFieldEmptyMethod(NONEMPTY_ELEMENT_DISTANT_2_POINTS_FROM_WALL,
				FIRST_ELEMENT);
		when(
				utils.findFirstFreeFieldBeforeElement(iteratorSecond,
						NONEMPTY_ELEMENT_DISTANT_2_POINTS_FROM_WALL))
				.thenReturn(FIRST_ELEMENT);

		testObject.moveElementsTowardsWall(SELECTED_LINE, lineIteratorCreator);

		verify(utils).swapValues(FIRST_ELEMENT,
				NONEMPTY_ELEMENT_DISTANT_2_POINTS_FROM_WALL);
		verify(utils).swapValues(any(Point.class), any(Point.class));
	}

	@Test
	public void moveElementsTowardsWall_TwoElementsBothNearTheWall_noValuesSwapped() {
		mockIsFieldEmptyMethod(NONEMPTY_ELEMENT_DISTANT_1_POINT_FROM_WALL,
				FIRST_ELEMENT);
		when(
				utils.findFirstFreeFieldBeforeElement(iteratorSecond,
						NONEMPTY_ELEMENT_DISTANT_1_POINT_FROM_WALL))
				.thenReturn(null);

		testObject.moveElementsTowardsWall(SELECTED_LINE, lineIteratorCreator);

		verify(utils, times(0)).swapValues(any(Point.class), any(Point.class));
	}

	private void mockIsFieldEmptyMethod(Point... points) {
		when(utils.isFieldEmpty(any(Point.class))).thenReturn(true);
		for (Point p : points)
			when(utils.isFieldEmpty(p)).thenReturn(false);
	}

	@SuppressWarnings("unchecked")
	private void mockLineIteratorsCreatorAndIteratorSecond() {
		ArrayList<Point> points = createPointsForEachElementInRow(FIRST_ROW,
				FIXED_COLUMN, NUMBER_OF_POINTS);
		Iterator<Point> iteratorFirst = mockIterator(points);
		iteratorSecond = mockIterator(new ArrayList<Point>());

		when(lineIteratorCreator.firstElement(any(Point.class))).thenReturn(
				FIRST_ELEMENT);
		when(lineIteratorCreator.iterator(FIRST_ELEMENT)).thenReturn(
				iteratorFirst, iteratorSecond);
	}

}
