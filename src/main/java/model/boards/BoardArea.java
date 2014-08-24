package main.java.model.boards;

public class BoardArea implements ReadOnlyBoardArea {

	private static final int ROWS = 5;
	private static final int COLUMNS = 5;
	
	private int[][] area = new int[ROWS][COLUMNS];
	
	@Override
	public int getFieldValue(int row, int column) {
		return area[row][column];
	}
	
	@Override
	public int getRows() {
		return ROWS;
	}

	@Override
	public int getColumns() {
		return COLUMNS;
	}
	
	public void setFieldValue(int row, int column, int version) {
		area[row][column] = version;
	}
}
