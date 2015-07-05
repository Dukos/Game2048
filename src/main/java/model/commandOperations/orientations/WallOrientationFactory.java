package main.java.model.commandOperations.orientations;

import main.java.model.boards.Dimension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WallOrientationFactory {

	private PointIteratorsCreator toLeftIterator;

	private PointIteratorsCreator toRightIterator;

	private PointIteratorsCreator toBottomIterator;

	private PointIteratorsCreator toTopIterator;

	@Autowired
	public WallOrientationFactory(Dimension dimensions) {
		toLeftIterator = new PointIteratorsCreator(
				new ToLeftIteratorImplementation(), dimensions);
		toRightIterator = new PointIteratorsCreator(
				new ToRightIteratorImplementation(), dimensions);
		toBottomIterator = new PointIteratorsCreator(
				new ToBottomIteratorImplementation(), dimensions);
		toTopIterator = new PointIteratorsCreator(
				new ToTopIteratorImplementation(), dimensions);
	}

	public WallOrientation forLeftCommand() {
		return new WallOrientation(toBottomIterator, toRightIterator);
	}

	public WallOrientation forRightCommand() {
		return new WallOrientation(toBottomIterator, toLeftIterator);
	}

	public WallOrientation forUpCommand() {
		return new WallOrientation(toRightIterator, toTopIterator);
	}

	public WallOrientation forDownCommand() {
		return new WallOrientation(toRightIterator, toBottomIterator);
	}
}
