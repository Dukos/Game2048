package test.java.view;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import main.java.view.ImageLoader;
import main.java.view.PictureResourceSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PictureResourceSetTest {

	private static final String IMAGES_DIRECTORY_PATH = "images";
	@Mock
	ImageLoader imageLoader;

	@InjectMocks
	PictureResourceSet testObject;

	@Test
	public void loadImagesFromDirectory_directoryExists_imagesRequestedToLoad() {
		when(imageLoader.loadImage(anyString())).thenReturn(new ImageIcon());
		testObject.loadImagesFromDirectory(IMAGES_DIRECTORY_PATH);
		for (int version = 0; version < 9; ++version)
			Mockito.verify(imageLoader).loadImage(
					"images/img0" + version + ".png");
	}

	@Test
	public void loadImagesFromDirectory_directoryExists_imagesAccessible() {
		List<ImageIcon> images = new ArrayList<ImageIcon>();
		for (int version = 0; version < 9; version++) {
			ImageIcon icon = new ImageIcon();
			images.add(icon);
			when(imageLoader.loadImage("images/img0" + version + ".png"))
					.thenReturn(icon);
		}
		testObject.loadImagesFromDirectory(IMAGES_DIRECTORY_PATH);

		List<ImageIcon> returnedImages = testObject.getIconsList();
		assertEquals(images, returnedImages);
	}
	/*
	 * @Test public void loadImage_noExceptionsRaised_labelSizeIs100x100() {
	 * testObject = new ImageLoaderTested(); JLabel label =
	 * testObject.loadImage(RELATIVE_IMAGE_PATH);
	 * assertEquals(label.getPreferredSize(), new Dimension(100, 100)); }
	 */

}
