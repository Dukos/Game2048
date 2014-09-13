package main.java.model.boards;

import java.util.Random;

import main.java.model.addingElements.ElementsAdder;
import main.java.model.addingElements.FreeFieldFinder;
import main.java.model.addingElements.Randomizer;
import main.java.model.commandOperations.BoardLineMerger;
import main.java.model.commandOperations.BoardLineMover;
import main.java.model.commandOperations.BoardLineUtils;
import main.java.model.commandOperations.CommandListenerImpl;
import main.java.model.commandOperations.CommandsListener;
import main.java.model.commandOperations.MoveEntireBoardAlgorithm;
import main.java.model.commandOperations.orientations.WallOrientationFactory;

public class BoardLogicCreator {
	public CommandsListener createCommandListener(BoardArea area)
	{
		final BoardLineUtils utils = new BoardLineUtils(area);
		
		final BoardLineMover mover = new BoardLineMover(utils);
		final BoardLineMerger merger = new BoardLineMerger(area, utils);
		
		final MoveEntireBoardAlgorithm algorithm = new MoveEntireBoardAlgorithm(mover, merger);
		final WallOrientationFactory factory = new WallOrientationFactory(area.getDimensions());
		
		return new CommandListenerImpl(algorithm, factory);
	}
	
	public ElementsAdder createElementsAdder(BoardArea area)
	{
		final Random random = new Random();
		final Randomizer randomizer = new Randomizer(area, random);
		
		final FreeFieldFinder finder = new FreeFieldFinder(area);
		
		return new ElementsAdder(randomizer, finder);
	}
	
	public BoardLogic createBoardLogic()
	{
		BoardArea area = new BoardArea();
		return new BoardLogic(createElementsAdder(area), createCommandListener(area));
	}
}
