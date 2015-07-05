package main.java.model.commandOperations.orientations;

import main.java.model.boards.Dimension;
import main.java.model.commandOperations.BoardLineMerger;
import main.java.model.commandOperations.BoardLineMover;
import main.java.model.commandOperations.MoveEntireBoardAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MoveEntireBoardAlgorithmFactory {

	private PointIteratorsCreator toLeftIterator;

	private PointIteratorsCreator toRightIterator;

	private PointIteratorsCreator toBottomIterator;

	private PointIteratorsCreator toTopIterator;
	
	private BoardLineMover mover;
	
	private BoardLineMerger merger;

	@Autowired
	public MoveEntireBoardAlgorithmFactory(Dimension dimensions, BoardLineMover mover, BoardLineMerger merger) {
		toLeftIterator = new PointIteratorsCreator(
				new ToLeftIteratorImplementation(), dimensions);
		toRightIterator = new PointIteratorsCreator(
				new ToRightIteratorImplementation(), dimensions);
		toBottomIterator = new PointIteratorsCreator(
				new ToBottomIteratorImplementation(), dimensions);
		toTopIterator = new PointIteratorsCreator(
				new ToTopIteratorImplementation(), dimensions);
		
		this.mover = mover;
		this.merger = merger;
	}

	public MoveEntireBoardAlgorithm forLeftCommand() {
		return createAlgorithm(mover, merger, toBottomIterator, toRightIterator);
	}

	public MoveEntireBoardAlgorithm forRightCommand() {
		return createAlgorithm(mover, merger, toBottomIterator, toLeftIterator);
	}

	public MoveEntireBoardAlgorithm forUpCommand() {
		return createAlgorithm(mover, merger, toRightIterator, toTopIterator);
	}

	public MoveEntireBoardAlgorithm forDownCommand() {
		return createAlgorithm(mover, merger, toRightIterator, toBottomIterator);
	}
	
	public MoveEntireBoardAlgorithm createAlgorithm(BoardLineMover mover, BoardLineMerger merger,
			PointIteratorsCreator lineSelector, PointIteratorsCreator lineIterator)
	{
		return new MoveEntireBoardAlgorithm(mover, merger, lineSelector, lineIterator);
	}
}
