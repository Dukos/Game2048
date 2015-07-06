package main.java.model.addingElements;

import java.awt.Point;
import java.util.Random;

import main.java.model.boards.BoardArea;
import main.java.model.boards.Dimension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Randomizer {

	private static final int MAX_VALUE_FOR_CREATED_ELEMENT = 2;

	private final Dimension dimension;

	private final Random random;

	@Autowired
	public Randomizer(Dimension dimension, Random random) {
		this.dimension = dimension;
		this.random = random;
	}

	public Point generateRandomField() {

		final int selectedRow = random.nextInt(dimension.width);
		final int selectedColumn = random.nextInt(dimension.height);

		return new Point(selectedRow, selectedColumn);
	}

	public int generateRandomFieldValue() {
		return random.nextInt(MAX_VALUE_FOR_CREATED_ELEMENT - 1) + 1;
	}
}
