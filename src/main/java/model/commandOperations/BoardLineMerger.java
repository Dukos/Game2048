package main.java.model.commandOperations;

import java.awt.Point;
import java.util.Iterator;

import main.java.model.boards.BoardArea;
import main.java.model.commandOperations.orientations.PointIteratorsCreator;

public class BoardLineMerger {

	private final BoardArea area;

	private final BoardLineUtils utils;
	
	public BoardLineMerger(BoardArea area, BoardLineUtils utils)
	{
		this.area = area;
		this.utils = utils;
	}

	public void mergeMovedElementsTowardsWall(Point p,
			PointIteratorsCreator lineIterator) {
		Iterator<Point> iter = lineIterator.iterator(lineIterator
				.firstElement(p));

		assert(iter.hasNext());
		Point next = iter.next();
		Point current = null;

		while (iter.hasNext()) {
			current = next;
			next = iter.next();

			if (utils.isFieldEmpty(next))
				break;

			tryMergePair(current, next, lineIterator);
		}
	}

	private void tryMergePair(Point first, Point second,
			PointIteratorsCreator lineIterator) {
		final int firstValue = area.getFieldValue(first.x, first.y);
		final int secondValue = area.getFieldValue(second.x, second.y);

		if (firstValue == secondValue) {
			area.setFieldValue(first.x, first.y, firstValue + 1);
			utils.swiftMovedElementsInLineTowardsWall(lineIterator
					.iterator(second));
		}
	}
}
