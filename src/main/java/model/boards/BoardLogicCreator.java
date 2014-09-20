package main.java.model.boards;

import java.util.Random;

import main.java.model.addingElements.ElementsAdder;
import main.java.model.addingElements.FreeFieldFinder;
import main.java.model.addingElements.Randomizer;
import main.java.model.commandOperations.BoardLineMerger;
import main.java.model.commandOperations.BoardLineMover;
import main.java.model.commandOperations.BoardLineUtils;
import main.java.model.commandOperations.CommandsListener;
import main.java.model.commandOperations.MoveEntireBoardAlgorithm;
import main.java.model.commandOperations.orientations.WallOrientationFactory;

public class BoardLogicCreator {
	
	private CommandListererImplPartsProvider commandListenerProvider = new CommandListererImplPartsProvider();
	
	private ElementsAdderPartsProvider elementsAdderProvider = new ElementsAdderPartsProvider();
	
	public CommandsListener createCommandListener(BoardArea area)
	{
		final BoardLineUtils utils = commandListenerProvider.createBoardLineUtils(area);
		
		final BoardLineMover mover = commandListenerProvider.createBoardLineMover(utils);
		final BoardLineMerger merger = commandListenerProvider.createBoardAreaMerger(area, utils);
		
		final MoveEntireBoardAlgorithm algorithm = commandListenerProvider.createMoveEntireBoardAlgorithm(mover, merger);
		final WallOrientationFactory factory = commandListenerProvider.createWallOrientationFactory(area.getDimensions());
		
		return commandListenerProvider.createCommandListener(algorithm, factory);
	}
	
	public ElementsAdder createElementsAdder(BoardArea area)
	{
		final Random random = elementsAdderProvider.createRandom();
		final Randomizer randomizer = elementsAdderProvider.createRandomizer(area, random);
		
		final FreeFieldFinder finder = elementsAdderProvider.createFreeFieldFinder(area);
		
		return elementsAdderProvider.createElementsAdder(randomizer, finder);
	}
}
