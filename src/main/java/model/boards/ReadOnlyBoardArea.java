package main.java.model.boards;


public interface ReadOnlyBoardArea {
	int getFieldValue(int row, int column);
	
	int getRows();
	
	int getColumns();
}
