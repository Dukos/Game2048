package main.java.model.addingElements;

import java.awt.Point;

import org.springframework.beans.factory.annotation.Autowired;

import main.java.model.boards.BoardArea;
import main.java.model.boards.Dimension;

public class FreeFieldFinder {

	private BoardArea area;

	@Autowired
	public FreeFieldFinder(BoardArea area) {
		this.area = area;
	}

	public Point findFirstFreeField(final Point startPoint) {
		final Dimension dimension = area.getDimensions();

		int x = startPoint.x;
		int y = startPoint.y;

		while (!isFieldFree(x, y)) {
			y = circularAdd(y, dimension.width);
			if (y == 0)
				x = circularAdd(x, dimension.height);
			if (isCycleDetected(startPoint, x, y))
				return null;
		}

		return new Point(x, y);
	}

	private int circularAdd(int value, int maxValue) {
		value++;
		if (value == maxValue)
			value = 0;
		return value;
	}

	private boolean isCycleDetected(final Point startPoint, int currentX,
			int currentY) {
		if (startPoint.x != currentX)
			return false;
		if (startPoint.y != currentY)
			return false;

		return true;
	}

	private boolean isFieldFree(int x, int y) {
		return area.getFieldValue(x, y) == 0;
	}
}
