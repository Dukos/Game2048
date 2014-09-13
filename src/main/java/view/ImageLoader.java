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
		
		try {
			return loadIcon(APPLICATION_PATH + "/" + relativePath);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	
	protected String getUserDir()
	{
		return System.getProperty("user.dir");
	}
	
	protected ImageIcon loadIcon(String filePath) throws IOException
	{
		BufferedImage img = ImageIO.read(new File(filePath));
		return new ImageIcon(img);
	}

}
