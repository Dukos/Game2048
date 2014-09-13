package main.java.model.commandOperations;

import main.java.model.commandOperations.orientations.WallOrientation;
import main.java.model.commandOperations.orientations.WallOrientationFactory;

public class CommandListenerImpl implements CommandsListener {
	
	private final MoveEntireBoardAlgorithm moveAlgorithm;
	
	private WallOrientation leftCommandOrientation;
	private WallOrientation rightCommandOrientation;
	private WallOrientation upCommandOrientation;
	private WallOrientation downCommandOrientation;

	public CommandListenerImpl(MoveEntireBoardAlgorithm moveAlgorithm, WallOrientationFactory factory) {
		this.moveAlgorithm = moveAlgorithm;
		setUpOrientations(factory);
	}

	private void setUpOrientations(WallOrientationFactory factory) {
		leftCommandOrientation = factory.forLeftCommand();
		rightCommandOrientation = factory.forRightCommand();
		upCommandOrientation = factory.forUpCommand();
		downCommandOrientation = factory.forDownCommand();
	}
	
	@Override
	public void left() {
		moveAlgorithm.makeMove(leftCommandOrientation);
	}

	@Override
	public void right() {
		moveAlgorithm.makeMove(rightCommandOrientation);
	}
	
	@Override
	public void up() {
		moveAlgorithm.makeMove(upCommandOrientation);
	}
	
	@Override
	public void down() {
		moveAlgorithm.makeMove(downCommandOrientation);
	}
}