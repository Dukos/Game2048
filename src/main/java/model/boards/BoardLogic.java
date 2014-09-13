package main.java.model.boards;

import java.util.Random;

import main.java.model.addingElements.ElementsAdder;
import main.java.model.addingElements.FreeFieldFinder;
import main.java.model.addingElements.Randomizer;
import main.java.model.commandOperations.CommandListenerImpl;
import main.java.model.commandOperations.MoveEntireBoardAlgorithm;
import main.java.model.commandOperations.orientations.WallOrientationFactory;

public class BoardLogic {

	private BoardArea area = new BoardArea();
	
	private ElementsAdder elementsAdder;
	
	private CommandListenerImpl commandListener = new CommandListenerImpl(new MoveEntireBoardAlgorithm(area), new WallOrientationFactory(area.getDimensions()));

	public void applyRandomObject() {
		if(elementsAdder.tryAddNewElement())
			gameOver();
	}

	private void gameOver() {
		//TODO
	}

	public BoardLogic()
	{
		elementsAdder = new ElementsAdder(new Randomizer(area, new Random()), new FreeFieldFinder(area));
	}
	
}
