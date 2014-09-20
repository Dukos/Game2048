package main.java.model.commandOperations.orientations;

import java.awt.Point;
import java.util.Iterator;

import main.java.model.boards.Dimension;

public class PointIteratorsCreator {

	private final IterarorImplementation iteratorImplementation;

	private final Dimension dimensions;

	public PointIteratorsCreator(IterarorImplementation iteratorImplementation,
			Dimension dimensions) {
		this.iteratorImplementation = iteratorImplementation;
		this.dimensions = dimensions;
	}

	public Point firstElement(Point p) {
		Point p2 = (Point) p.clone();
		iteratorImplementation.resetIndex(p2, dimensions);
		return p2;
	}

	public IterarorImplementation getIteratorImplementation() {
		return iteratorImplementation;
	}

	public Iterator<Point> iterator(final Point first) {

		return new Iterator<Point>() {

			private Point currentIndex = new Point(first.x, first.y);

			@Override
			public boolean hasNext() {
				return iteratorImplementation.isIndexInRange(currentIndex,
						dimensions);
			}

			@Override
			public Point next() {
				Point oldPoint = (Point) currentIndex.clone();
				iteratorImplementation.updateIndexToNext(currentIndex);
				return oldPoint;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}
