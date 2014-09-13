package main.java.model.commandOperations.orientations;

import java.awt.Point;
import java.util.Iterator;

public class WallOrientation {

	private PointIteratorsCreator lineSelectorIterator;

	private PointIteratorsCreator lineIterator;

	public WallOrientation(PointIteratorsCreator lineSelector, PointIteratorsCreator lineIterator) {
		this.lineSelectorIterator = lineSelector;
		this.lineIterator = lineIterator;
	}

	public Iterator<Point> getLineIterator(Point firstPoint) {
		return lineIterator.iterator(firstPoint);
	}

	public Iterator<Point> getLineSelectorIterator() {
		Point firstElement = lineSelectorIterator.firstElement(new Point());
		return lineSelectorIterator.iterator(firstElement);
	}
	
	public PointIteratorsCreator getLineSelectorIteratorsCreator()
	{
		return lineSelectorIterator;
	}
	
	public PointIteratorsCreator getLineIteratorsCreator()
	{
		return lineIterator;
	}
}
