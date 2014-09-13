package integration.java.model.commandOperations.orientations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Iterator;

import main.java.model.commandOperations.orientations.PointIteratorsCreator;
import main.java.model.commandOperations.orientations.ToBottomIteratorImplementation;

import org.junit.Before;
import org.junit.Test;

public class PointIteratorsCreatorIntegrationTest {

	private static final Dimension DIMENSIONS = new Dimension(5, 3);
	
	private PointIteratorsCreator testObject;
	
	@Before
	public void setUp() throws Exception {
		testObject = new PointIteratorsCreator(new ToBottomIteratorImplementation(), DIMENSIONS);
	}
	
	@Test
	public void iteration_startPointIsSet0x0_returnsCorrectPoints() {

		Iterator<Point> iterator = testObject.iterator(new Point(0, 0));
		
		assertTrue(iterator.hasNext());
		assertEquals(iterator.next(), new Point(0, 0));
		
		assertTrue(iterator.hasNext());
		assertEquals(iterator.next(), new Point(0, 1));
		
		assertTrue(iterator.hasNext());
		assertEquals(iterator.next(), new Point(0, 2));
		
		assertFalse(iterator.hasNext());
	}
	
	@Test
	public void iteration_startPointIsSet2x0_returnsCorrectPoints() {
		Iterator<Point> iterator = testObject.iterator(new Point(2, 0));
		
		assertTrue(iterator.hasNext());
		assertEquals(iterator.next(), new Point(2, 0));
		
		assertTrue(iterator.hasNext());
		assertEquals(iterator.next(), new Point(2, 1));
		
		assertTrue(iterator.hasNext());
		assertEquals(iterator.next(), new Point(2, 2));
		
		assertFalse(iterator.hasNext());
	}
	
	@Test
	public void iteration_startPointIsSet2x1_returnsCorrectPoints() {
		Iterator<Point> iterator = testObject.iterator(new Point(2, 1));
		
		assertTrue(iterator.hasNext());
		assertEquals(iterator.next(), new Point(2, 1));
		
		assertTrue(iterator.hasNext());
		assertEquals(iterator.next(), new Point(2, 2));
		
		assertFalse(iterator.hasNext());
	}
}
