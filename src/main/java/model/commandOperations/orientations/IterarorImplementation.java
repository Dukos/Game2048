package main.java.model.commandOperations.orientations;

import main.java.model.boards.Dimension;
import java.awt.Point;

public interface IterarorImplementation {
	
	boolean isIndexInRange(Point index, Dimension dimensions);

	void updateIndexToNext(Point index);
	
	void resetIndex(Point p, Dimension dimensions);
}