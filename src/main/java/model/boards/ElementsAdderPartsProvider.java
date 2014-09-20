package main.java.model.boards;

import java.util.Random;

import main.java.model.addingElements.ElementsAdder;
import main.java.model.addingElements.FreeFieldFinder;
import main.java.model.addingElements.Randomizer;

public class ElementsAdderPartsProvider {
	public ElementsAdder createElementsAdder(Randomizer randomizer,
			FreeFieldFinder finder) {
		return new ElementsAdder(randomizer, finder);
	}

	public FreeFieldFinder createFreeFieldFinder(BoardArea area) {
		return new FreeFieldFinder(area);
	}

	public Randomizer createRandomizer(BoardArea area, Random random) {
		return new Randomizer(area, random);
	}

	public Random createRandom() {
		return new Random();
	}
}
