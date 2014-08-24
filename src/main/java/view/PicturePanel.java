package main.java.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PicturePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private int currentPictureVersion = 0;
	
	private CardLayout layout = null;
	
	public PicturePanel(PictureResourceSet pictureResourceSet)
	{
		this(pictureResourceSet, new CardLayout());
	}
	
	public PicturePanel(PictureResourceSet pictureResourceSet, CardLayout layout)
	{
		super();
		this.layout = layout;
		setLayout(layout);
		createSubLabels(pictureResourceSet);
	}

	private void createSubLabels(PictureResourceSet pictureResourceSet) {
		List<ImageIcon> icons = pictureResourceSet.getIconsList();
		for(int i = 0; i < icons.size(); i++)
			addToLayoutComponent(createLabel(icons.get(i)), i);
	}

	private void addToLayoutComponent(JLabel createdLabel, int position) {
		layout.addLayoutComponent(createdLabel, Integer.toString(position));
		add(createdLabel);
	}

	public int getPictureVersion() {
		return currentPictureVersion;
	}

	public void setPictureVersion(int pictureVersion) {
		currentPictureVersion = pictureVersion;
		layout.show(this, Integer.toString(pictureVersion));
	}
	
	private JLabel createLabel(ImageIcon icon) {
		JLabel label = new JLabel(icon);
		label.setPreferredSize(new Dimension(100, 100));

		return label;
	}


}
