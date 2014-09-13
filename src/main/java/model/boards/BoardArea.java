package main.java.model.boards;

import main.java.model.boards.Dimension;

public class BoardArea implements ReadOnlyBoardArea {

	
	private static final Dimension dimensions = new Dimension(5, 5);
	
	private int[][] area = new int[dimensions.width][dimensions.height];
	
	@Override
	public int getFieldValue(int x, int y) {
		return area[x][y];
	}
	
	@Override
	public Dimension getDimensions() {
		return dimensions;
	}
	
	public void setFieldValue(int x, int y, int version) {
		if(version < 0)
			throw new IllegalArgumentException("Field cannot have negative field value");
		area[x][y] = version;
	}
}
