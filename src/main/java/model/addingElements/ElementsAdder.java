package main.java.model.addingElements;

import java.awt.Point;

public class ElementsAdder {
	
	private final Randomizer randomizer;
	private final FreeFieldFinder freeFieldFinder;
	
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
