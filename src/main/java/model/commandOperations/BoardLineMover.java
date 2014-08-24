package main.java.model.commandOperations;

import java.awt.Point;
import java.util.Iterator;

import main.java.model.boards.BoardArea;
import main.java.model.commandOperations.orientations.PointIteratorsCreator;

public class BoardLineMover {
	
	private BoardLineUtils utils;

	public BoardLineMover(BoardArea area) {
		utils = createUtils(area);
	}

	public void moveElementsTowardsWall(Point selectedLine, PointIteratorsCreator lineIteratorCreator) {
		Iterator<Point> iter = lineIteratorCreator.iterator(lineIteratorCreator.firstElement(selectedLine));
		assert(iter.hasNext());
		iter.next();
		while(iter.hasNext())
				moveElementsInLineTowardsWall(iter.next(), lineIteratorCreator);
	}

	private void moveElementsInLineTowardsWall(Point elementToMove, PointIteratorsCreator lineIterator) {
		if (utils.isFieldEmpty(elementToMove))
			return;

		Iterator<Point> iter = lineIterator.iterator(lineIterator.firstElement(elementToMove));
		Point firstFreeField = utils.findFirstFreeFieldBeforeElement(iter, elementToMove);
		if (firstFreeField != null)
			utils.swapValues(firstFreeField, elementToMove);
	}
	
	protected BoardLineUtils createUtils(BoardArea area)
	{
		return new BoardLineUtils(area);
	}
}
