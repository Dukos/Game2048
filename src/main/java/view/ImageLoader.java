package main.java.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageLoader {

	private static String APPLICATION_PATH = null;
	
	public ImageIcon loadImage(String relativePath) {
		if(APPLICATION_PATH == null)
			APPLICATION_PATH = getUserDir();
		
		BufferedImage myPicture;
		try {
			myPicture = loadBufferedImage(new File(APPLICATION_PATH + "/" + relativePath));
			return new ImageIcon(myPicture);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	
	protected String getUserDir()
	{
		return System.getProperty("user.dir");
	}
	
	protected BufferedImage loadBufferedImage(File file) throws IOException
	{
		return ImageIO.read(file);
	}

}
