package main.java.model.commandOperations;

import java.awt.Point;
import java.util.Iterator;

import main.java.model.commandOperations.orientations.PointIteratorsCreator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class MoveEntireBoardAlgorithm {

	private final BoardLineMover boardLineMover;

	private final BoardLineMerger boardLineMerger;
	
	private final PointIteratorsCreator lineSelector;
	
	private final PointIteratorsCreator lineIterator;

	@Autowired
	public MoveEntireBoardAlgorithm(BoardLineMover boardLineMover,
			BoardLineMerger boardLineMerger,
			PointIteratorsCreator lineSelector,
			PointIteratorsCreator lineIterator) {
		this.boardLineMover = boardLineMover;
		this.boardLineMerger = boardLineMerger;
		this.lineSelector = lineSelector;
		this.lineIterator = lineIterator;
	}

	public void makeMove() {
		Iterator<Point> iter = lineSelector.iterator(new Point());
		Point p = null;
		while (iter.hasNext()) {
			p = iter.next();
			boardLineMover.moveElementsTowardsWall(p, lineIterator);
			boardLineMerger.mergeMovedElementsTowardsWall(p, lineIterator);
		}
	}
}
