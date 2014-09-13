package test.java.model.boards;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import main.java.model.boards.Dimension;

import org.junit.Test;

public class DimensionTest {

	private final static int SAMPLE_CORRECT_WIDTH = 4;
	private final static int SAMPLE_CORRECT_HEIGHT = 6;
	
	private final static int OTHER_SAMPLE_CORRECT_WIDTH = SAMPLE_CORRECT_WIDTH + 1;
	private final static int OTHER_SAMPLE_CORRECT_HEIGHT = SAMPLE_CORRECT_HEIGHT + 1;
	
	private Dimension testObject;
	
	private final static Dimension SAMPLE_OBJECT = new Dimension(SAMPLE_CORRECT_WIDTH, SAMPLE_CORRECT_HEIGHT);
	
	@Test
	public void publicFinalFieldWidth_constructedWithWidthAndHeightParameters_fieldWidthPreserved() {
		testObject = new Dimension(SAMPLE_CORRECT_WIDTH, SAMPLE_CORRECT_HEIGHT);
		assertEquals(SAMPLE_CORRECT_WIDTH, testObject.width);
	}

	@Test
	public void publicFinalFieldHeight_constructedWithWidthAndHeightParameters_fieldHeightPreserved() {
		testObject = new Dimension(SAMPLE_CORRECT_WIDTH, SAMPLE_CORRECT_HEIGHT);
		assertEquals(SAMPLE_CORRECT_HEIGHT, testObject.height);
	}
	
	@Test
	public void copyConstructor_otherDimensionObjectPassed_fieldWidthPreserved() {	
		testObject = new Dimension(SAMPLE_OBJECT);
		assertEquals(SAMPLE_OBJECT.width, testObject.width);
	}
	
	@Test
	public void copyConstructor_otherDimensionObjectPassed_fieldHeightPreserved() {
		testObject = new Dimension(SAMPLE_OBJECT);
		assertEquals(SAMPLE_OBJECT.height, testObject.height);
	}
	
	@Test
	public void equals_nullObjectPassed_returnsFalse() {	
		testObject = new Dimension(SAMPLE_CORRECT_WIDTH, SAMPLE_CORRECT_HEIGHT);
		final Object objectToCompare = null;
		assertFalse(testObject.equals(objectToCompare));
	}
	
	@Test
	public void equals_passedItself_returnsTrue() {	
		testObject = new Dimension(SAMPLE_CORRECT_WIDTH, SAMPLE_CORRECT_HEIGHT);
		final Object objectToCompare = testObject;
		assertTrue(testObject.equals(objectToCompare));
	}
	
	@Test
	public void equals_passedOtherClass_returnsfalse() {	
		testObject = new Dimension(SAMPLE_CORRECT_WIDTH, SAMPLE_CORRECT_HEIGHT);
		final Object objectToCompare = new Object();
		assertFalse(testObject.equals(objectToCompare));
	}
	
	@Test
	public void equals_passedDimensionObjectWithDifferentWidth_returnsfalse() {	
		testObject = new Dimension(SAMPLE_CORRECT_WIDTH, SAMPLE_CORRECT_HEIGHT);
		final Object objectToCompare = new Dimension(OTHER_SAMPLE_CORRECT_WIDTH, SAMPLE_CORRECT_HEIGHT);
		assertFalse(testObject.equals(objectToCompare));
	}
	
	@Test
	public void equals_passedDimensionObjectWithDifferentHeight_returnsfalse() {	
		testObject = new Dimension(SAMPLE_CORRECT_WIDTH, SAMPLE_CORRECT_HEIGHT);
		final Object objectToCompare = new Dimension(SAMPLE_CORRECT_WIDTH, OTHER_SAMPLE_CORRECT_HEIGHT);
		assertFalse(testObject.equals(objectToCompare));
	}
	
	@Test
	public void equals_passedDimensionObjectWithSameParameters_returnsTrue() {	
		testObject = new Dimension(SAMPLE_CORRECT_WIDTH, SAMPLE_CORRECT_HEIGHT);
		final Object objectToCompare = new Dimension(SAMPLE_CORRECT_WIDTH, SAMPLE_CORRECT_HEIGHT);
		assertTrue(testObject.equals(objectToCompare));
	}
	
	@Test
	public void hashCode_comparedWithDimensionObjectWithSameParameters_theSameCodeIsGenerated() {	
		final int hashCode1 = new Dimension(SAMPLE_CORRECT_WIDTH, SAMPLE_CORRECT_HEIGHT).hashCode();
		final int hashCode2 = new Dimension(SAMPLE_CORRECT_WIDTH, SAMPLE_CORRECT_HEIGHT).hashCode();
		
		assertEquals(hashCode1, hashCode2);
	}
	
	@Test
	public void hashCode_comparedTwoDimensionObjectWithDiffererentWidth_differentCodeIsGenerated() {
		final int hashCode1 = new Dimension(SAMPLE_CORRECT_WIDTH, SAMPLE_CORRECT_HEIGHT).hashCode();
		final int hashCode2 = new Dimension(OTHER_SAMPLE_CORRECT_WIDTH, SAMPLE_CORRECT_HEIGHT).hashCode();
		
		//yes, conflict is possible, but test is good enough for practical use.
		assertNotEquals(hashCode1, hashCode2);
	}
	
	@Test
	public void hashCode_comparedTwoDimensionObjectWithDiffererentHeight_differentCodeIsGenerated() {
		final int hashCode1 = new Dimension(SAMPLE_CORRECT_WIDTH, SAMPLE_CORRECT_HEIGHT).hashCode();
		final int hashCode2 = new Dimension(SAMPLE_CORRECT_WIDTH, OTHER_SAMPLE_CORRECT_HEIGHT).hashCode();
		
		//yes, conflict is possible, but test is good enough for practical use.
		assertNotEquals(hashCode1, hashCode2);
	}
}
