package main.java.model.boards;

import main.java.model.boards.Dimension;
import java.util.Random;

import main.java.model.addingElements.ElementsAdder;
import main.java.model.addingElements.FreeFieldFinder;
import main.java.model.addingElements.Randomizer;
import main.java.model.commandOperations.MoveEntireBoardAlgorithm;
import main.java.model.commandOperations.orientations.WallOrientation;
import main.java.model.commandOperations.orientations.WallOrientationFactory;

public class BoardLogic {

	private BoardArea area = new BoardArea();
	
	private ElementsAdder elementsAdder;
	
	private MoveEntireBoardAlgorithm moveLeftAlgorithm = new MoveEntireBoardAlgorithm(area);
	
	private WallOrientation leftCommandOrientation;
	
	private WallOrientation rightCommandOrientation;
	
	private WallOrientation upCommandOrientation;
	
	private WallOrientation downCommandOrientation;
	
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
		WallOrientationFactory factory = new WallOrientationFactory(area.getDimensions());
		leftCommandOrientation = factory.forLeftCommand();
		rightCommandOrientation = factory.forRightCommand();
		upCommandOrientation = factory.forUpCommand();
		downCommandOrientation = factory.forDownCommand();
	}
	
	public void commandLeft() {
		moveLeftAlgorithm.makeMove(leftCommandOrientation);
	}

	public void commandRight() {
		moveLeftAlgorithm.makeMove(rightCommandOrientation);
	}
	
	public void commandUp() {
		moveLeftAlgorithm.makeMove(upCommandOrientation);
	}
	
	public void commandDown() {
		moveLeftAlgorithm.makeMove(downCommandOrientation);
	}
	
}
