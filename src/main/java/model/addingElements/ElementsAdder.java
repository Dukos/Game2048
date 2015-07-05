package main.java.model.addingElements;

import java.awt.Point;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ElementsAdder {

	private final Randomizer randomizer;
	private final FreeFieldFinder freeFieldFinder;

	@Autowired
	public ElementsAdder(Randomizer randomizer, FreeFieldFinder finder) {
		this.randomizer = randomizer;
		this.freeFieldFinder = finder;
	}

	public boolean tryAddNewElement() {
		Point currentPoint = randomizer.getRandomField();
		currentPoint = freeFieldFinder.findFirstFreeField(currentPoint);
		if (currentPoint == null)
			return false;
		randomizer.setRandomFieldValue(currentPoint);
		return true;
	}

}
