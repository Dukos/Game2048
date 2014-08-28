package test.java.model.commandOperations.orientations;

import java.awt.Point;
import java.util.Iterator;

import main.java.model.commandOperations.orientations.PointIteratorsCreator;
import main.java.model.commandOperations.orientations.WallOrientation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class WallOrientationTest {

	private WallOrientation testObject;
	
	@Mock
	private PointIteratorsCreator groupIterator;

	@Mock
	private PointIteratorsCreator lineIterator;
	
	@Mock
	private Iterator<Point> iterator;
	
	private final static Point START_POINT = new Point(1,1);
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		testObject = new WallOrientation(groupIterator, lineIterator);
	}
	
	@Test
	public void getLineIterator_startPointIsSet1x1_returnsLineIterator() {
	}
}
