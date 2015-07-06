package main.java.model.addingElements;

import java.awt.Point;

import main.java.model.boards.BoardArea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ElementsAdder {

	private final BoardArea boardArea;
	private final Randomizer randomizer;
	private final FreeFieldFinder freeFieldFinder;

	@Autowired
	public ElementsAdder(BoardArea boardArea, Randomizer randomizer, FreeFieldFinder finder) {
		this.boardArea = boardArea;
		this.randomizer = randomizer;
		this.freeFieldFinder = finder;
	}

	public boolean tryAddNewElement() {
		Point p = randomizer.generateRandomField();
		p = freeFieldFinder.findFirstFreeField(p);
		if (p == null)
			return false;
		int v = randomizer.generateRandomFieldValue();
		boardArea.setFieldValue(p.x, p.y, v);
		return true;
	}

}
