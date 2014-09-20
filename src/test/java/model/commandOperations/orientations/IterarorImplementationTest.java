package test.java.model.commandOperations.orientations;

import static org.junit.Assert.assertEquals;

import main.java.model.boards.Dimension;
import java.awt.Point;
import java.util.Arrays;
import java.util.Collection;

import main.java.model.commandOperations.orientations.IterarorImplementation;
import main.java.model.commandOperations.orientations.ToBottomIteratorImplementation;
import main.java.model.commandOperations.orientations.ToLeftIteratorImplementation;
import main.java.model.commandOperations.orientations.ToRightIteratorImplementation;
import main.java.model.commandOperations.orientations.ToTopIteratorImplementation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(value = Parameterized.class)
public class IterarorImplementationTest {

	static class ExpectedResultsForPositions {
		private final boolean left;
		private final boolean right;
		private final boolean bottom;
		private final boolean upper;

		private final Point movedPoint;

		private final Point resettedPoint;

		private ExpectedResultsForPositions(boolean left, boolean right,
				boolean bottom, boolean upper, Point movedPoint,
				Point resettedPoint) {
			this.left = left;
			this.right = right;
			this.bottom = bottom;
			this.upper = upper;
			this.movedPoint = movedPoint;
			this.resettedPoint = resettedPoint;
		}
	}

	private final static Dimension dimensions = new Dimension(6, 3);

	private final static Point LEFT_POSITION_CROSSED = new Point(-1,
			dimensions.height / 2);
	private final static Point RIGHT_POSITION_CROSSED = new Point(
			dimensions.width, dimensions.height / 2);
	private final static Point BOTTOM_POSITION_CROSSED = new Point(
			dimensions.width / 2, dimensions.height);
	private final static Point UPPER_POSITION_CROSSED = new Point(
			dimensions.width / 2, -1);

	private final static Point POINT_TO_INCREMENT = new Point(3, 2);

	private IterarorImplementation testObject;

	private ExpectedResultsForPositions expectedResults;

	public IterarorImplementationTest(IterarorImplementation testObject,
			ExpectedResultsForPositions expectedResult) {
		this.testObject = testObject;
		this.expectedResults = expectedResult;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> createParameters() {
		return Arrays.asList(new Object[][] {
				{
						new ToRightIteratorImplementation(),
						new ExpectedResultsForPositions(true, false, true,
								true, new Point(4, 2), new Point(0, 2)) },
				{
						new ToLeftIteratorImplementation(),
						new ExpectedResultsForPositions(false, true, true,
								true, new Point(2, 2), new Point(5, 2)) },
				{
						new ToBottomIteratorImplementation(),
						new ExpectedResultsForPositions(true, true, false,
								true, new Point(3, 3), new Point(3, 0)) },
				{
						new ToTopIteratorImplementation(),
						new ExpectedResultsForPositions(true, true, true,
								false, new Point(3, 1), new Point(3, 2)) }, });
	}

	@Test
	public void isIndexInRange_leftPositionCrossed_returnsExpectedValue() {
		final boolean result = testObject.isIndexInRange(LEFT_POSITION_CROSSED,
				dimensions);
		assertEquals(result, expectedResults.left);
	}

	@Test
	public void isIdexInRange_rightPositionCrossed_returnsExpectedValue() {
		final boolean result = testObject.isIndexInRange(
				RIGHT_POSITION_CROSSED, dimensions);
		assertEquals(result, expectedResults.right);
	}

	@Test
	public void isIndexInRange_upperPositionCrossed_returnsExpectedValue() {
		final boolean result = testObject.isIndexInRange(
				UPPER_POSITION_CROSSED, dimensions);
		assertEquals(result, expectedResults.upper);
	}

	@Test
	public void isIndexInRange_bottomPositionCrossed_returnsExpectedValue() {
		final boolean result = testObject.isIndexInRange(
				BOTTOM_POSITION_CROSSED, dimensions);
		assertEquals(result, expectedResults.bottom);
	}

	@Test
	public void isIndexInRange_pointObjectPassed_dimensionsLeftUnchanged() {
		final Dimension passedDimensions = new Dimension(dimensions);
		testObject.isIndexInRange(new Point(), passedDimensions);
		assertEquals(passedDimensions, dimensions);
	}

	@Test
	public void updateIndexToNext_centerPosition_updatesIndex() {
		Point p = (Point) POINT_TO_INCREMENT.clone();
		testObject.updateIndexToNext(p);
		assertEquals(p, expectedResults.movedPoint);
	}

	@Test
	public void resetIndex_centerPosition_resetsIndex() {
		Point p = (Point) POINT_TO_INCREMENT.clone();
		testObject.resetIndex(p, dimensions);
		assertEquals(p, expectedResults.resettedPoint);
	}

	@Test
	public void resetIndex_pointObjectPassed_dimensionsLeftUnchanged() {
		final Dimension passedDimensions = new Dimension(dimensions);
		testObject.resetIndex(new Point(), passedDimensions);
		assertEquals(passedDimensions, dimensions);
	}

}
