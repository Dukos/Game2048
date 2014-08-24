package main.java.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;

public class PictureResourceSet {

	private static final int IMAGE_VERSIONS = 9;
	
	private List<ImageIcon> icons = new ArrayList<ImageIcon>();
	
	private ImageLoader imageLoader = new ImageLoader();

	public void loadImagesFromDirectory(String relativePathToDirectory) {
		for (int version = 0; version < IMAGE_VERSIONS; version++)
			icons.add(loadIcon(imageLoader, relativePathToDirectory, version));
	}

	public List<ImageIcon> getIconsList() {
		return Collections.unmodifiableList(icons);
	}

	private ImageIcon loadIcon(ImageLoader imageLoader, String path, int version) {
		String relativePathToSpecyficIconVersion = path + "/img0" + version
				+ ".png";
		return imageLoader.loadImage(relativePathToSpecyficIconVersion);
	}
}
