package main.java.model.commandOperations;

import java.awt.Point;
import java.util.Iterator;

import main.java.model.boards.BoardArea;

public class BoardLineUtils {

	private final BoardArea area;

	public BoardLineUtils(BoardArea area) {
		this.area = area;
	}

	public void swapValues(Point p1, Point p2) {
		int firstValue = area.getFieldValue(p1.x, p1.y);
		int secondValue = area.getFieldValue(p2.x, p2.y);

		area.setFieldValue(p1.x, p1.y, secondValue);
		area.setFieldValue(p2.x, p2.y, firstValue);
	}

	public Point findFirstFreeFieldBeforeElement(Iterator<Point> iter,
			Point endPoint) {
		Point p = null;

		while (iter.hasNext()) {
			p = iter.next();
			if (area.getFieldValue(p.x, p.y) == 0)
				return p;
			if (p.equals(endPoint))
				return null;
		}
		return null;
	}

	public boolean isFieldEmpty(Point p) {
		return area.getFieldValue(p.x, p.y) == 0;
	}

	public void swiftMovedElementsInLineTowardsWall(Iterator<Point> iter) {

		Point next = iter.next();
		Point current = null;

		while (iter.hasNext()) {
			current = next;
			next = iter.next();

			if (area.getFieldValue(current.x, current.y) == 0)
				break;

			area.setFieldValue(current.x, current.y,
					area.getFieldValue(next.x, next.y));
		}
		area.setFieldValue(current.x, current.y, 0);
	}
}
