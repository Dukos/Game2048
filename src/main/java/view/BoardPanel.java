package main.java.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import main.java.model.boards.ReadOnlyBoardArea;

public class BoardPanel extends JPanel {

	private static final Dimension PANEL_DIMENSION = new Dimension(500, 500);

	private static final int ROWS = 5;
	private static final int COLUMNS = 5;

	private static final long serialVersionUID = 1L;

	final GridLayout layout = new GridLayout(ROWS, COLUMNS);
	
	private ArrayList< ArrayList<PicturePanel> > picturePanels = null;

	public BoardPanel() {
		super();
		setPreferredSize(PANEL_DIMENSION);
		setLayout(layout);
		populate();
		setVisible(true);
	}
	
	private PictureResourceSet pictureResourceSet = new PictureResourceSet();
	
	private void populate() {
		pictureResourceSet.loadImagesFromDirectory("images");
		
		picturePanels = new ArrayList< ArrayList<PicturePanel> >();
		for(int x = 0; x < ROWS; x++)
			picturePanels.add(populateRow(pictureResourceSet));
	}
	
	private ArrayList<PicturePanel> populateRow(PictureResourceSet pictureResourceSet)
	{
		ArrayList<PicturePanel> list = new ArrayList<PicturePanel>();
		for(int y = 0; y < COLUMNS; y++)
		{
			list.add(new PicturePanel(pictureResourceSet));
			add(list.get(y));
		}
		return list;
	}
	
	public void updatePicturesVersions(ReadOnlyBoardArea area)
	{
		for(int x = 0; x < ROWS; x++)
			for(int y = 0; y < COLUMNS; y++)
				picturePanels.get(x).get(y).setPictureVersion(area.getFieldValue(x, y));
	}

	public PicturePanel getPicture(int row, int column) {
		return picturePanels.get(row).get(column);
	}
}
