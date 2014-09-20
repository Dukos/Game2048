package main.java.model.boards;

import main.java.model.addingElements.ElementsAdder;
import main.java.model.commandOperations.CommandsListener;

public class BoardLogic {
	private ElementsAdder elementsAdder;

	private CommandsListener commandListener;

	public void applyRandomObject() {
		if (elementsAdder.tryAddNewElement())
			gameOver();
	}

	private void gameOver() {
	}

	public BoardLogic(ElementsAdder elementsAdder) {
		this.elementsAdder = elementsAdder;
	}

}
