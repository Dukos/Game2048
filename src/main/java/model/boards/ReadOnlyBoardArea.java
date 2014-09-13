package main.java.model.boards;

import main.java.model.boards.Dimension;


public interface ReadOnlyBoardArea {
	int getFieldValue(int row, int column);
	
	Dimension getDimensions();
}
