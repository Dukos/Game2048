package test.java.view;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.CardLayout;
import java.util.List;

import javax.swing.ImageIcon;

import main.java.view.PicturePanel;
import main.java.view.PictureResourceSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PicturePanelTest {

	private static final int SECOND_PICTURE_VERSION = 1;
	
	@Mock
	private PictureResourceSet pictureResourceSet;

	@Mock
	private List<ImageIcon> returnedImages;
	
	@Mock
	private CardLayout layout;
	
	@InjectMocks
	private PicturePanel testObject;

	@Before
	public void setUp() throws Exception {
		when(pictureResourceSet.getIconsList()).thenReturn(returnedImages);
	}

	@Test
	public void constructor_noSetup_showsFirstPictureVersion() {
		int pictureVersion = testObject.getPictureVersion();
		assertEquals(pictureVersion, 0);
	}

	@Test
	public void setPictureVersion_selectedSecondPictureVersion_returnsSecondPictureVersionAsSelected() {
		testObject.setPictureVersion(SECOND_PICTURE_VERSION);
		int pictureVersion = testObject.getPictureVersion();
		assertEquals(pictureVersion, SECOND_PICTURE_VERSION);
	}

	@Test
	public void setPictureVersion_selectedSecondPictureVersion_drawsSecondPictureVersion() {
		testObject.setPictureVersion(SECOND_PICTURE_VERSION);
		verify(layout).show(testObject, Integer.toString(SECOND_PICTURE_VERSION));
	}

}
