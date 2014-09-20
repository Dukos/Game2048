package main.java.model.boards;

import main.java.model.commandOperations.BoardLineMerger;
import main.java.model.commandOperations.BoardLineMover;
import main.java.model.commandOperations.BoardLineUtils;
import main.java.model.commandOperations.CommandListenerImpl;
import main.java.model.commandOperations.MoveEntireBoardAlgorithm;
import main.java.model.commandOperations.orientations.WallOrientationFactory;

public class CommandListererImplPartsProvider {
	
	public BoardArea createBoardArea()
	{
		return new BoardArea();
	}
	
	public BoardLineUtils createBoardLineUtils(BoardArea area)
	{
		return new BoardLineUtils(area);
	}
	
	public BoardLineMover createBoardLineMover(BoardLineUtils utils)
	{
		return new BoardLineMover(utils);
	}
	
	public BoardLineMerger createBoardAreaMerger(BoardArea area, BoardLineUtils utils)
	{
		return new BoardLineMerger(area, utils);
	}
	
	public MoveEntireBoardAlgorithm createMoveEntireBoardAlgorithm(BoardLineMover mover, BoardLineMerger merger)
	{
		return new MoveEntireBoardAlgorithm(mover, merger); 
	}
	
	public WallOrientationFactory createWallOrientationFactory(Dimension dimensions)
	{
		return new WallOrientationFactory(dimensions);
	}
	
	public CommandListenerImpl createCommandListener(MoveEntireBoardAlgorithm algorithm, WallOrientationFactory factory)
	{
		return new CommandListenerImpl(algorithm, factory);
	}
}
