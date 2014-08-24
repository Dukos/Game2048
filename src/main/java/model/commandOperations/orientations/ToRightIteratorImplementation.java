package main.java.model.commandOperations.orientations;

import java.awt.Dimension;
import java.awt.Point;

public class ToRightIteratorImplementation implements IterarorImplementation {

	@Override
	public boolean isIndexInRange(Point index, Dimension dimensions) {
		return index.x < dimensions.width;
	}

	@Override
	public void updateIndexToNext(Point index) {
		index.x++;
	}

	@Override
	public void resetIndex(Point p, Dimension dimensions) {
		p.x = 0;
	}
}