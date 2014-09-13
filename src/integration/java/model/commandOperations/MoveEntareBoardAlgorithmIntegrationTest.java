package integration.java.model.commandOperations;

import static org.junit.Assert.assertEquals;

import java.awt.Dimension;
import java.awt.Point;

import main.java.model.boards.BoardArea;
import main.java.model.commandOperations.MoveEntireBoardAlgorithm;
import main.java.model.commandOperations.orientations.WallOrientation;
import main.java.model.commandOperations.orientations.WallOrientationFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import test.java.model.boards.FixedValue;

@RunWith(MockitoJUnitRunner.class)
public class MoveEntareBoardAlgorithmIntegrationTest {
	
	private BoardArea area = new BoardArea();
	
	private WallOrientation orientation;
	
	private MoveEntireBoardAlgorithm testObject = new MoveEntireBoardAlgorithm(area);
	
	@Before
	public void setUp() throws Exception {
		Dimension dimensions = new Dimension(area.getRows(), area.getColumns());
		orientation = new WallOrientationFactory(dimensions).forLeftCommand();
	}

	@Test
	public void makeMove_twoSameItemsInSameRowOnBoardArea_bothItemsMovedToTheLeftAndMerged() {
		final int fixedY = 4;
		final int fixedValue = 5;
		
		final Point p1 = new Point(1, fixedY);
		final Point p2 = new Point(3, fixedY);
		
		area.setFieldValue(p1.x, p1.y, fixedValue);
		area.setFieldValue(p2.x, p2.y, fixedValue);
		
		testObject.makeMove(orientation);
		
		assertEquals(area.getFieldValue(p1.x, p1.y), 0);
		assertEquals(area.getFieldValue(p2.x, p2.y), 0);
		
		final int[] expectedLastRow = {fixedValue + 1, 0, 0, 0, 0};
		
		checkRow(fixedY, expectedLastRow);
	}
	
	@Test
	public void makeMove_threeSameItemsInSameRowOnBoardArea_allItemsMovedToTheLeftAndOnePairMerged() {
		final int fixedY = 3;
		final int fixedValue = 5;
		
		final Point p1 = new Point(1, fixedY);
		final Point p2 = new Point(3, fixedY);
		final Point p3 = new Point(4, fixedY);
		
		area.setFieldValue(p1.x, p1.y, fixedValue);
		area.setFieldValue(p2.x, p2.y, fixedValue);
		area.setFieldValue(p3.x, p3.y, fixedValue);
		
		final int[] expectedLastRow = {fixedValue + 1, fixedValue, 0, 0, 0};
		
		testObject.makeMove(orientation);
		
		checkRow(fixedY, expectedLastRow);
	}

	private void checkRow(final int fixedY, final int[] expectedLastRow) {
		for(int x = 0; x < area.getColumns(); x++)
		{
			assertEquals(area.getFieldValue(x, fixedY), expectedLastRow[x]);
		}
	}
}
