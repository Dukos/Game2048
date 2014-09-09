package test.java.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.awt.Dimension;
import java.awt.GridLayout;

import main.java.model.boards.ReadOnlyBoardArea;
import main.java.view.BoardPanel;
import main.java.view.PicturePanel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BoardPanelTest {
	
	private static final int FIRST_ROW_INDEX = 0;
	private static final int FIRST_COLUMN_INDEX = 0;

	private static final int COLUMNS = 5;
	private static final int ROWS = 5;

	private static final int LAST_COLUMN_INDEX = COLUMNS - 1;
	private static final int LAST_ROW_INDEX = ROWS - 1;
	
	private static final int CONFIGURED_VERSION = 2;
	
	@InjectMocks
	private BoardPanel testObject;
	
	@Mock
	private ReadOnlyBoardArea area;

	@Test
	public void constructor_noSetup_sizeIs500x500() {
		assertEquals(testObject.getPreferredSize(), new Dimension(500, 500));
	}
	
	@Test
	public void constructor_noSetup_containsGridLayout5x5()
	{
		GridLayout layout = null;
		if(testObject.getLayout() instanceof GridLayout)
			layout = (GridLayout) testObject.getLayout();
		assertNotNull(layout);
		assertEquals(layout.getRows(), ROWS);
		assertEquals(layout.getColumns(), COLUMNS);
	}

	@Test(expected = java.lang.IndexOutOfBoundsException.class)
	public void getPicture_componentOutsideOfRowsRange_outOfBoundExceptionRaised() {
		testObject.getPicture(LAST_ROW_INDEX + 1, FIRST_COLUMN_INDEX);
	}

	@Test(expected = java.lang.IndexOutOfBoundsException.class)
	public void getPictureVersion_componentOutsideOfColumnsRange_outOfBoundExceptionRaised() {
		testObject.getPicture(FIRST_ROW_INDEX, LAST_COLUMN_INDEX + 1);
	}

	@Test
	public void getPicture_noSetup_zeroVersionOfReturnedPicture() {
		int version = testObject
				.getPicture(FIRST_ROW_INDEX, FIRST_COLUMN_INDEX)
				.getPictureVersion();
		assertEquals(version, 0);
	}

	@Test
	public void getPicture_noSetup_topLeftPicturePanelWasInitialized() {
		PicturePanel picture = testObject.getPicture(FIRST_ROW_INDEX,
				FIRST_COLUMN_INDEX);
		assertNotNull(picture);
	}

	@Test
	public void populateWithPictures_noSetup_bottomRightPicturePanelWasInitialized() {
		PicturePanel picture = testObject.getPicture(LAST_ROW_INDEX,
				LAST_COLUMN_INDEX);
		assertNotNull(picture);
	}
	
	@Test
	public void updatePanel_bottomRightComponentVersionUpdated_componentUpdated() {
		when(area.getFieldValue(anyInt(), anyInt())).thenReturn(0);
		when(area.getFieldValue(LAST_ROW_INDEX, LAST_COLUMN_INDEX)).thenReturn(CONFIGURED_VERSION);
		
		testObject.updatePicturesVersions(area);
		assertEquals(testObject.getPicture(LAST_ROW_INDEX, LAST_COLUMN_INDEX)
				.getPictureVersion(), CONFIGURED_VERSION);
	}
}
