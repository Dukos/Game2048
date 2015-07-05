package main.java.model.commandOperations;

import main.java.model.commandOperations.orientations.MoveEntireBoardAlgorithmFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandListenerImpl implements CommandsListener {

	private MoveEntireBoardAlgorithm leftAlgorithm;
	private MoveEntireBoardAlgorithm rightAlgorithm;
	private MoveEntireBoardAlgorithm upAlgorithm;
	private MoveEntireBoardAlgorithm downAlgorithm;

	@Autowired
	public CommandListenerImpl(MoveEntireBoardAlgorithmFactory factory) {
		setUpAlgorithms(factory);
	}

	private void setUpAlgorithms(MoveEntireBoardAlgorithmFactory factory) {
		leftAlgorithm = factory.forLeftCommand();
		rightAlgorithm = factory.forRightCommand();
		upAlgorithm = factory.forUpCommand();
		downAlgorithm = factory.forDownCommand();
	}

	@Override
	public void left() {
		leftAlgorithm.makeMove();
	}

	@Override
	public void right() {
		rightAlgorithm.makeMove();
	}

	@Override
	public void up() {
		upAlgorithm.makeMove();
	}

	@Override
	public void down() {
		downAlgorithm.makeMove();
	}
}