package main.java.model.addingElements;

import java.awt.Point;

import main.java.model.boards.BoardArea;

public class ElementsAdder {
	
	private Randomizer randomizer;
	private FreeFieldFinder freeFieldFinder;

	public ElementsAdder(BoardArea area)
	{
		randomizer = new Randomizer(area);
		freeFieldFinder = new FreeFieldFinder(area);
	}
	
	public ElementsAdder(Randomizer randomizer, FreeFieldFinder finder)
	{
		this.randomizer = randomizer;
		this.freeFieldFinder = finder;
	}
	
	public boolean tryAddNewElement() {
		Point currentPoint = randomizer.getRandomField();
		currentPoint = freeFieldFinder.findFirstFreeField(currentPoint);
		if(currentPoint == null)
			return false;
		randomizer.setRandomFieldValue(currentPoint);
		return true;
	}

}
