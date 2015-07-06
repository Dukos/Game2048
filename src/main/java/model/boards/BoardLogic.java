package main.java.model.boards;

import main.java.model.addingElements.ElementsAdder;
import main.java.model.commandOperations.CommandsListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BoardLogic {
	
	private ElementsAdder elementsAdder;

	private CommandsListener commandListener;

	public void applyRandomObject() {
		if (elementsAdder.tryAddNewElement())
			gameOver();
	}

	private void gameOver() {
	}

	@Autowired
	public BoardLogic(ElementsAdder elementsAdder) {
		this.elementsAdder = elementsAdder;
	}

}
