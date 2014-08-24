package main.java.model.commandOperations;

import java.awt.Point;
import java.util.Iterator;

import main.java.model.boards.BoardArea;
import main.java.model.commandOperations.orientations.WallOrientation;

public class MoveEntireBoardAlgorithm {
	
	private final BoardLineMover boardLineMover;
	
	private final BoardLineMerger boardLineMerger;
	
	public MoveEntireBoardAlgorithm(BoardArea area)
	{
		boardLineMover = new BoardLineMover(area);
		boardLineMerger = new BoardLineMerger(area);
	}
	
	public void makeMove(WallOrientation moveOrientation) {
		Iterator<Point> iter = moveOrientation.getLineSelectorIterator();
		Point p = null;
		while(iter.hasNext())
		{
			p = iter.next();
			boardLineMover.moveElementsTowardsWall(p, moveOrientation.getLineIteratorsCreator());
			boardLineMerger.mergeMovedElementsTowardsWall(p, moveOrientation.getLineIteratorsCreator());
		}
	}
}
