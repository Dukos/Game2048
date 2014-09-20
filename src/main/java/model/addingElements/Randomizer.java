package main.java.model.addingElements;

import java.awt.Point;
import java.util.Random;

import main.java.model.boards.BoardArea;
import main.java.model.boards.Dimension;

public class Randomizer {

	private static final int MAX_VALUE_FOR_CREATED_ELEMENT = 2;

	private final BoardArea area;

	private final Random random;

	public Randomizer(BoardArea area, Random random) {
		this.area = area;
		this.random = random;
	}

	public Point getRandomField() {
		final Dimension dimensions = area.getDimensions();

		final int selectedRow = random.nextInt(dimensions.width);
		final int selectedColumn = random.nextInt(dimensions.height);

		return new Point(selectedRow, selectedColumn);
	}

	public void setRandomFieldValue(Point currentPoint) {
		final int selectedValue = random
				.nextInt(MAX_VALUE_FOR_CREATED_ELEMENT - 1) + 1;
		area.setFieldValue(currentPoint.x, currentPoint.y, selectedValue);
	}
}
