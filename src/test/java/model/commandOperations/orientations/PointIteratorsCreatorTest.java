package test.java.model.commandOperations.orientations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import main.java.model.boards.Dimension;
import java.awt.Point;
import java.util.Iterator;

import main.java.model.commandOperations.orientations.IterarorImplementation;
import main.java.model.commandOperations.orientations.PointIteratorsCreator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@RunWith(MockitoJUnitRunner.class)
public class PointIteratorsCreatorTest {

	private static final boolean IS_INDEX_IN_RANGE_FALSE = false;
	private static final boolean IS_INDEX_IN_RANGE_TRUE = true;
	
	private static final Point START_POINT = new Point(1,1);
	
	@Mock
	private IterarorImplementation iteratorImplementation;
	
	private static final Dimension DIMENSIONS = new Dimension(4,5);
	
	private PointIteratorsCreator testObject;
	
	@Before
	public void setUp() throws Exception {
		testObject = new PointIteratorsCreator(iteratorImplementation, DIMENSIONS);
	}
	
	@Test
	public void iterator_startPointSetTo1x1_returnsIterator() {
		final Iterator<Point>  iterator = testObject.iterator(START_POINT);
		
		assertNotNull(iterator);
	}
	
	@Test
	public void firstElement_noSetup_returnsAlteredPoint() {
		final Point originalPoint = new Point(1,1);
		final Point alteredPoint = new Point(2,2);
		
		doAnswer(new Answer<Void>() {
	        public Void answer(InvocationOnMock invocation) {
	            Object[] args = invocation.getArguments();
	            Point point = (Point) args[0];
	            point.x = alteredPoint.x;
	            point.y = alteredPoint.y;
	            return null;
	        }
	    }).when(iteratorImplementation).resetIndex(originalPoint, DIMENSIONS);

		final Point result = testObject.firstElement(originalPoint);
		assertEquals(alteredPoint, result);
	}
	
	@Test
	public void firstElement_noSetup_originalPointLeftUnchanged() {
		final Point originalPoint = new Point(1,1);
		final Point passedPoint = (Point) originalPoint.clone();

		//by default resetIndex will return null
		testObject.firstElement(passedPoint);
		assertEquals(passedPoint, originalPoint);
	}
	
	@Test
	public void iteratorHasNext_startPointSetTo1x1AndIteratorIsInRange_returnsTrue() {
		when(iteratorImplementation.isIndexInRange(any(Point.class), any(Dimension.class))).thenReturn(IS_INDEX_IN_RANGE_TRUE);
		
		Iterator<Point> iterator = testObject.iterator(START_POINT);
		boolean result = iterator.hasNext();
		
		assertEquals(result, IS_INDEX_IN_RANGE_TRUE);
	}
	
	@Test
	public void iteratorHasNext_startPointSetTo1x1AndIteratorIsNOTInRange_returnsFalse() {
		when(iteratorImplementation.isIndexInRange(any(Point.class), any(Dimension.class))).thenReturn(IS_INDEX_IN_RANGE_FALSE);
		
		Iterator<Point> iterator = testObject.iterator(START_POINT);
		boolean result = iterator.hasNext();
		
		assertEquals(result, IS_INDEX_IN_RANGE_FALSE);
	}
	
	@Test
	public void iteratorNext_startPointSetTo1x1_returnsPoint1x1() {
		
		Iterator<Point> iterator = testObject.iterator(START_POINT);
		final Point result = iterator.next();
		
		assertEquals(result, START_POINT);
	}
	
	@Test
	public void iteratorNextCalledSecondTime_startPointSetTo1x1_returnsAlteredPoint() {
		final Point originalPoint = new Point(1,1);
		final Point alteredPoint = new Point(2,2);
		
		doAnswer(new Answer<Void>() {
	        public Void answer(InvocationOnMock invocation) {
	            Object[] args = invocation.getArguments();
	            Point point = (Point) args[0];
	            point.x = alteredPoint.x;
	            point.y = alteredPoint.y;
	            return null;
	        }
	    }).when(iteratorImplementation).updateIndexToNext(originalPoint);
		
		
		Iterator<Point> iterator = testObject.iterator(originalPoint);
		iterator.next();
		final Point result = iterator.next();
		
		assertEquals(result, alteredPoint);
	}
	
	@Test
	public void iteratorNextCalledSecondTime_startPointSetTo1x1_originalPointLeftUnchanged() {
		final Point originalPoint = new Point(1,1);
		final Point passedPoint = (Point) originalPoint.clone();
		
		//updateIndexToNext will return by default null
		Iterator<Point> iterator = testObject.iterator(originalPoint);
		iterator.next();
		iterator.next();
		
		assertEquals(originalPoint, passedPoint);
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void iteratorRemove_startPointSetTo1x1_raisesUnsupportedOperationException() {
		Iterator<Point> iterator = testObject.iterator(START_POINT);
		iterator.remove();
	}
}
