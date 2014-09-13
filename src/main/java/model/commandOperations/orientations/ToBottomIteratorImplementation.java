package main.java.model.commandOperations.orientations;

import main.java.model.boards.Dimension;
import java.awt.Point;

public class ToBottomIteratorImplementation implements IterarorImplementation {

	@Override
	public boolean isIndexInRange(Point index, Dimension dimensions) {
		return index.y < dimensions.height;
	}

	@Override
	public void updateIndexToNext(Point index) {
		index.y++;
	}

	@Override
	public void resetIndex(Point p, Dimension dimensions) {
		p.y = 0;
	}
}