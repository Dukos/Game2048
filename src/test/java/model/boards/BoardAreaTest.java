package test.java.model.boards;

import static org.junit.Assert.assertEquals;
import main.java.model.boards.BoardArea;
import main.java.model.boards.Dimension;

import org.junit.Before;
import org.junit.Test;

public class BoardAreaTest {

	private static final Dimension AREA = new Dimension(5, 5);

	private static final int SAMPLE_CORRECT_X = 1;
	private static final int SAMPLE_CORRECT_Y = 3;
	private static final int SAMPLE_CORRECT_FIELD_VALUE = 3;

	private static final int SAMPLE_NEGATIVE_VALUE = -2;

	private static final int EMPTY_FIELD = 0;

	private BoardArea testObject;

	@Before
	public void setUp() throws Exception {
		testObject = new BoardArea();
	}

	@Test
	public void getDimensions_noSetup_returns5x5Dimension() {
		final Dimension dimension = testObject.getDimensions();
		assertEquals(AREA, dimension);
	}

	@Test
	public void getFieldValue_noSetup_returnsZero() {
		final int sampleValue = testObject.getFieldValue(SAMPLE_CORRECT_X,
				SAMPLE_CORRECT_Y);
		assertEquals(EMPTY_FIELD, sampleValue);
	}

	@Test
	public void setFieldValue_threePositiveValuesPassed_fieldValueChanged() {
		testObject.setFieldValue(SAMPLE_CORRECT_X, SAMPLE_CORRECT_Y,
				SAMPLE_CORRECT_FIELD_VALUE);
		final int fieldValue = testObject.getFieldValue(SAMPLE_CORRECT_X,
				SAMPLE_CORRECT_Y);
		assertEquals(SAMPLE_CORRECT_FIELD_VALUE, fieldValue);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void setFieldValue_xValueIsNegative_IndexOutOfBoundsExceptionThrown() {
		testObject.setFieldValue(SAMPLE_NEGATIVE_VALUE, SAMPLE_CORRECT_Y,
				SAMPLE_CORRECT_FIELD_VALUE);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void setFieldValue_yValueIsNegative_IndexOutOfBoundsExceptionThrown() {
		testObject.setFieldValue(SAMPLE_CORRECT_X, SAMPLE_NEGATIVE_VALUE,
				SAMPLE_CORRECT_FIELD_VALUE);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void setFieldValue_xValueIsEqualWidth_IndexOutOfBoundsExceptionThrown() {
		final int tooBigValue = AREA.width + 1;
		testObject.setFieldValue(tooBigValue, SAMPLE_CORRECT_Y,
				SAMPLE_CORRECT_FIELD_VALUE);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void setFieldValue_yValueIsEqualHeight_IndexOutOfBoundsExceptionThrown() {
		final int tooBigValue = AREA.height + 1;
		testObject.setFieldValue(SAMPLE_CORRECT_X, tooBigValue,
				SAMPLE_CORRECT_FIELD_VALUE);
	}

}
