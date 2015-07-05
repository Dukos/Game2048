package main.java.model.commandOperations;

import java.awt.Point;
import java.util.Iterator;

import main.java.model.commandOperations.orientations.PointIteratorsCreator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BoardLineMover {
	
	private BoardLineUtils utils;

	@Autowired
	public BoardLineMover(BoardLineUtils utils) {
		this.utils = utils;
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
}
