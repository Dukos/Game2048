package main.java.model.addingElements;

import java.awt.Point;

import main.java.model.boards.BoardArea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ElementsAdder {

	@Autowired
	private BoardArea boardArea;
	@Autowired
	private Randomizer randomizer;
	@Autowired
	private FreeFieldFinder freeFieldFinder;

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
