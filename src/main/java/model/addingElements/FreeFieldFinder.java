package main.java.model.addingElements;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Iterator;

import main.java.model.boards.BoardArea;
import main.java.model.commandOperations.orientations.PointIteratorsCreator;
import main.java.model.commandOperations.orientations.ToBottomIteratorImplementation;
import main.java.model.commandOperations.orientations.ToRightIteratorImplementation;

public class FreeFieldFinder {

	private BoardArea area;

	private PointIteratorsCreator verticalIteratorsCreator;
	private PointIteratorsCreator horizontalIteratorsCreator;

	private Point currentPoint;
	private Iterator<Point> currentVerticalIterator;
	private Iterator<Point> currentHorizontalIterator;

	private static enum ProgressStatus {
		MOVED_TO_NEXT, RESTARTED
	};

	public FreeFieldFinder(BoardArea area) {
		this(area,
				new PointIteratorsCreator(
						new ToBottomIteratorImplementation(),
						new Dimension(area.getRows(), area.getColumns())),
				new PointIteratorsCreator(
						new ToRightIteratorImplementation(),
						new Dimension(area.getRows(), area.getColumns())));
	}

	public FreeFieldFinder(BoardArea area,
			PointIteratorsCreator verticalIteratorsCreator,
			PointIteratorsCreator horizontalIteratorsCreator) {
		this.area = area;
		this.verticalIteratorsCreator = verticalIteratorsCreator;
		this.horizontalIteratorsCreator = horizontalIteratorsCreator;
	}

	public Point findFirstFreeField(final Point startPoint) {
		currentPoint = startPoint;
		currentVerticalIterator = verticalIteratorsCreator
				.iterator(currentPoint);
		currentHorizontalIterator = horizontalIteratorsCreator
				.iterator(currentPoint);

		while (!isFieldFree(currentPoint)) {
			if (advanceToNextPoint(currentVerticalIterator,
					verticalIteratorsCreator) == ProgressStatus.MOVED_TO_NEXT)
				continue;

			if (advanceToNextPoint(currentHorizontalIterator,
					horizontalIteratorsCreator) == ProgressStatus.MOVED_TO_NEXT)
				continue;

			if (isCycleDetected(startPoint))
				return null;
		}

		return currentPoint;
	}

	private boolean isCycleDetected(final Point startPoint) {
		return startPoint.equals(currentPoint) ? true : false;

	}

	private ProgressStatus advanceToNextPoint(Iterator<Point> currentIterator,
			PointIteratorsCreator iteratorsCreator) {
		if (currentIterator.hasNext()) {
			currentPoint = currentIterator.next();
			return ProgressStatus.MOVED_TO_NEXT;
		} else {
			currentVerticalIterator = resetIterator(iteratorsCreator);
			currentPoint = currentIterator.next();
			return ProgressStatus.RESTARTED;
		}
	}

	private Iterator<Point> resetIterator(PointIteratorsCreator creator) {
		return creator.iterator(creator.firstElement(currentPoint));
	}

	private boolean isFieldFree(Point p) {
		return area.getFieldValue(p.x, p.y) == 0;
	}
}
