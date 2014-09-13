package test.java.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.swing.ImageIcon;

import main.java.view.ImageLoader;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ImageLoaderTest {

	private static final String APPLICATION_PATH = "dir";

	private static final String RELATIVE_IMAGE_PATH = "images/img01.png";

	private static final String ABSOLUTE_IMAGE_PATH = APPLICATION_PATH + "/" + RELATIVE_IMAGE_PATH;

	private ImageLoader testObject;

	@Mock
	private ImageIcon returnedImageIcon;

	private String lastRequestedFile = null;

	class ImageLoaderTested extends ImageLoader {
		@Override
		protected String getUserDir() {
			return APPLICATION_PATH;
		}

		@Override
		protected ImageIcon loadIcon(String filepath) throws IOException {
			lastRequestedFile = filepath;
			return returnedImageIcon;
		}
	}

	class ImageLoaderTestedThrowingIOExceptions extends ImageLoader {
		@Override
		protected String getUserDir() {
			return APPLICATION_PATH;
		}

		@Override
		protected ImageIcon loadIcon(String filepath) throws IOException {
			throw new IOException();
		}
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void loadImage_noExceptionsRaised_properFilePathRequested() {
		testObject = new ImageLoaderTested();
		testObject.loadImage(RELATIVE_IMAGE_PATH);
		assertEquals(ABSOLUTE_IMAGE_PATH, lastRequestedFile);
	}

	@Test
	public void loadImage_noExceptionsRaised_returnsIcon() {
		testObject = new ImageLoaderTested();
		ImageIcon icon = testObject.loadImage(RELATIVE_IMAGE_PATH);
		assertNotNull(icon);
	}

	@Test(expected = java.lang.RuntimeException.class)
	public void loadImage_IOExceptionRaised_runtimeErrorRaised() {
		testObject = new ImageLoaderTestedThrowingIOExceptions();
		testObject.loadImage(RELATIVE_IMAGE_PATH);
	}

}
