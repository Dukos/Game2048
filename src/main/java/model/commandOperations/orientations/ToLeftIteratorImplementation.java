package main.java.model.commandOperations.orientations;

import java.awt.Dimension;
import java.awt.Point;

public class ToLeftIteratorImplementation implements IterarorImplementation {

	@Override
	public boolean isIndexInRange(Point index, Dimension dimensions) {
		return index.x >= 0;
	}

	@Override
	public void updateIndexToNext(Point index) {
		index.x--;
	}

	@Override
	public void resetIndex(Point p, Dimension dimensions) {
		p.x = dimensions.width - 1;
	}
}