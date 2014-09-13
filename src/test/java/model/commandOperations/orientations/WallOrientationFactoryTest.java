package test.java.model.commandOperations.orientations;

import static org.junit.Assert.assertTrue;

import java.awt.Dimension;

import main.java.model.commandOperations.orientations.PointIteratorsCreator;
import main.java.model.commandOperations.orientations.WallOrientation;
import main.java.model.commandOperations.orientations.WallOrientationFactory;

import org.junit.Before;
import org.junit.Test;

public class WallOrientationFactoryTest {

	private final static Dimension DIMENSIONS = new Dimension();
	
	private WallOrientationFactory testObject;
	
	private PointIteratorTypeChecker checker = new PointIteratorTypeChecker();
	
	@Before
	public void setUp() throws Exception {
		testObject = new WallOrientationFactory(DIMENSIONS);
	}

	@Test
	public void forLeftCommand_noAdditionalSetup_createsCorrectWallOrientation() {
		WallOrientation wallOrientation = testObject.forLeftCommand();
		PointIteratorsCreator groupIterator = wallOrientation.getLineSelectorIteratorsCreator();
		PointIteratorsCreator lineIterator = wallOrientation.getLineIteratorsCreator();
		
		assertTrue(checker.isHorizontalIterator(groupIterator));
		assertTrue(checker.isToLeftIterator(lineIterator));
	}
	
	@Test
	public void forRightCommand_noAdditionalSetup_createsCorrectWallOrientation() {
		WallOrientation wallOrientation = testObject.forRightCommand();
		PointIteratorsCreator groupIterator = wallOrientation.getLineSelectorIteratorsCreator();
		PointIteratorsCreator lineIterator = wallOrientation.getLineIteratorsCreator();
		
		assertTrue(checker.isHorizontalIterator(groupIterator));
		assertTrue(checker.isToRightIterator(lineIterator));
	}
	
	@Test
	public void forDownCommand_noAdditionalSetup_createsCorrectWallOrientation() {
		WallOrientation wallOrientation = testObject.forDownCommand();
		PointIteratorsCreator groupIterator = wallOrientation.getLineSelectorIteratorsCreator();
		PointIteratorsCreator lineIterator = wallOrientation.getLineIteratorsCreator();
		
		assertTrue(checker.isVerticalIterator(groupIterator));
		assertTrue(checker.isToBottomIterator(lineIterator));
	}
	
	@Test
	public void forUpCommand_noAdditionalSetup_createsCorrectWallOrientation() {
		WallOrientation wallOrientation = testObject.forUpCommand();
		PointIteratorsCreator groupIterator = wallOrientation.getLineSelectorIteratorsCreator();
		PointIteratorsCreator lineIterator = wallOrientation.getLineIteratorsCreator();
		
		assertTrue(checker.isVerticalIterator(groupIterator));
		assertTrue(checker.isToTopIterator(lineIterator));
	}
}
