package test.java.model.commandOperations;

import static org.junit.Assert.assertEquals;

import java.awt.Dimension;

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
public class MoveLeftAlgorithmTest {

	private final FixedValue fixedValue1 = new FixedValue(4, 1, 5);
	private final FixedValue fixedValue2 = new FixedValue(4, 3, 1);
	
	private MoveEntireBoardAlgorithm testObject;
	
	private BoardArea area;
	
	private WallOrientation orientation;
	
	@Before
	public void setUp() throws Exception {
		area = new BoardArea();
		Dimension dimensions = new Dimension(area.getRows(), area.getColumns());
		orientation = new WallOrientationFactory(dimensions).forLeftCommand();
		testObject = new MoveEntireBoardAlgorithm(area);
	}
	
	@Test
	public void makeMove_twoSameItemsInSameRowOnBoardArea_bothItemsMovedToTheLeftAndMerged() {
		area.setFieldValue(fixedValue2.row, fixedValue2.column, fixedValue2.value);
		area.setFieldValue(fixedValue2.row, fixedValue2.column + 1, fixedValue2.value);
		
		testObject.makeMove(orientation);
		
		assertEquals(area.getFieldValue(fixedValue2.row, fixedValue2.column), 0);
		assertEquals(area.getFieldValue(fixedValue2.row, fixedValue2.column + 1), 0);
		
		assertEquals(area.getFieldValue(fixedValue2.row, 0), fixedValue2.value + 1);
	}
	
	@Test
	public void makeMove_threeSameItemsInSameRowOnBoardArea_allItemsMovedToTheLeftAndOnePairMerged() {
		area.setFieldValue(fixedValue2.row, fixedValue2.column, fixedValue2.value);
		area.setFieldValue(fixedValue2.row, fixedValue2.column + 1, fixedValue2.value);
		area.setFieldValue(fixedValue2.row, fixedValue2.column - 2, fixedValue2.value);
		
		testObject.makeMove(orientation);
		
		assertEquals(area.getFieldValue(fixedValue2.row, fixedValue2.column), 0);
		assertEquals(area.getFieldValue(fixedValue2.row, fixedValue2.column + 1), 0);
		
		assertEquals(area.getFieldValue(fixedValue2.row, 0), fixedValue2.value + 1);
		assertEquals(area.getFieldValue(fixedValue2.row, 1), fixedValue2.value);
	}
}
