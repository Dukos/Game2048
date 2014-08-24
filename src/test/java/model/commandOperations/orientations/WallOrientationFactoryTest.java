package test.java.model.commandOperations.orientations;

import static org.junit.Assert.assertTrue;

import java.awt.Dimension;

import main.java.model.commandOperations.orientations.IterarorImplementation;
import main.java.model.commandOperations.orientations.PointIteratorsCreator;
import main.java.model.commandOperations.orientations.ToBottomIteratorImplementation;
import main.java.model.commandOperations.orientations.ToLeftIteratorImplementation;
import main.java.model.commandOperations.orientations.ToRightIteratorImplementation;
import main.java.model.commandOperations.orientations.ToTopIteratorImplementation;
import main.java.model.commandOperations.orientations.WallOrientation;
import main.java.model.commandOperations.orientations.WallOrientationFactory;

import org.junit.Before;
import org.junit.Test;

class WallOrientationFactoryTest {

	private final static Dimension DIMENSIONS = new Dimension();
	
	WallOrientationFactory testObject;
	
	@Before
	public void setUp() throws Exception {
		testObject = new WallOrientationFactory(DIMENSIONS);
	}

	@Test
	public void forLeftCommand_noAdditionalSetup_createsCorrectWallOrientation() {
		WallOrientation wallOrientation = testObject.forLeftCommand();
		PointIteratorsCreator groupIterator = wallOrientation.getLineSelectorIteratorsCreator();
		PointIteratorsCreator lineIterator = wallOrientation.getLineIteratorsCreator();
		
		assertTrue(isHorizontalIterator(groupIterator));
		assertTrue(isRightIterator(lineIterator));
	}
	
	@Test
	public void forRightCommand_noAdditionalSetup_createsCorrectWallOrientation() {
		WallOrientation wallOrientation = testObject.forRightCommand();
		PointIteratorsCreator groupIterator = wallOrientation.getLineSelectorIteratorsCreator();
		PointIteratorsCreator lineIterator = wallOrientation.getLineIteratorsCreator();
		
		assertTrue(isHorizontalIterator(groupIterator));
		assertTrue(isLeftIterator(lineIterator));
	}
	
	@Test
	public void forDownCommand_noAdditionalSetup_createsCorrectWallOrientation() {
		WallOrientation wallOrientation = testObject.forDownCommand();
		PointIteratorsCreator groupIterator = wallOrientation.getLineSelectorIteratorsCreator();
		PointIteratorsCreator lineIterator = wallOrientation.getLineIteratorsCreator();
		
		assertTrue(isVerticalIterator(groupIterator));
		assertTrue(isTopIterator(lineIterator));
	}
	
	@Test
	public void forUpCommand_noAdditionalSetup_createsCorrectWallOrientation() {
		WallOrientation wallOrientation = testObject.forUpCommand();
		PointIteratorsCreator groupIterator = wallOrientation.getLineSelectorIteratorsCreator();
		PointIteratorsCreator lineIterator = wallOrientation.getLineIteratorsCreator();
		
		assertTrue(isVerticalIterator(groupIterator));
		assertTrue(isBottomIterator(lineIterator));
	}
	
	private boolean isHorizontalIterator(PointIteratorsCreator iterator)
	{
		return isBottomIterator(iterator) || isTopIterator(iterator);
	}
	
	private boolean isBottomIterator(PointIteratorsCreator iterator)
	{
		return isIteratorOfType(iterator, ToBottomIteratorImplementation.class);
	}
	
	private boolean isTopIterator(PointIteratorsCreator iterator)
	{
		return isIteratorOfType(iterator, ToTopIteratorImplementation.class);
	}
	
	private boolean isVerticalIterator(PointIteratorsCreator iterator)
	{
		return isLeftIterator(iterator) || isRightIterator(iterator);
	}
	
	private boolean isLeftIterator(PointIteratorsCreator iterator)
	{
		return isIteratorOfType(iterator, ToLeftIteratorImplementation.class);
	}
	
	private boolean isRightIterator(PointIteratorsCreator iterator)
	{
		return isIteratorOfType(iterator, ToRightIteratorImplementation.class);
	}
	
	private boolean isIteratorOfType(PointIteratorsCreator groupIterator, Class<? extends IterarorImplementation> iterarorImplementationClass) {
		return groupIterator.getIteratorImplementation().getClass().isAssignableFrom(iterarorImplementationClass);
	}
}
