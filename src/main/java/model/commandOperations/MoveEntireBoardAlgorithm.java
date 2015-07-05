package main.java.model.commandOperations;

import java.awt.Point;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import main.java.model.commandOperations.orientations.WallOrientation;

public class MoveEntireBoardAlgorithm {

	private final BoardLineMover boardLineMover;

	private final BoardLineMerger boardLineMerger;

	@Autowired
	public MoveEntireBoardAlgorithm(BoardLineMover boardLineMover,
			BoardLineMerger boardLineMerger) {
		this.boardLineMover = boardLineMover;
		this.boardLineMerger = boardLineMerger;
	}

	public void makeMove(WallOrientation moveOrientation) {
		Iterator<Point> iter = moveOrientation.getLineSelectorIterator();
		Point p = null;
		while (iter.hasNext()) {
			p = iter.next();
			boardLineMover.moveElementsTowardsWall(p,
					moveOrientation.getLineIteratorsCreator());
			boardLineMerger.mergeMovedElementsTowardsWall(p,
					moveOrientation.getLineIteratorsCreator());
		}
	}
}
