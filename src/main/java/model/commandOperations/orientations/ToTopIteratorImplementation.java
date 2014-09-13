package main.java.model.commandOperations.orientations;

import main.java.model.boards.Dimension;
import java.awt.Point;

public class ToTopIteratorImplementation implements IterarorImplementation {

	@Override
	public boolean isIndexInRange(Point index, Dimension dimensions) {
		return index.y >= 0;
	}

	@Override
	public void updateIndexToNext(Point index) {
		index.y--;
	}

	@Override
	public void resetIndex(Point p, Dimension dimensions) {
		p.y = dimensions.height - 1;
	}
}