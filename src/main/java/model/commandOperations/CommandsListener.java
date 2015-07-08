package main.java.model.commandOperations;

import main.java.model.commandOperations.orientations.MoveEntireBoardAlgorithmFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandsListener {

	private MoveEntireBoardAlgorithm leftAlgorithm;
	private MoveEntireBoardAlgorithm rightAlgorithm;
	private MoveEntireBoardAlgorithm upAlgorithm;
	private MoveEntireBoardAlgorithm downAlgorithm;

	@Autowired
	public CommandsListener (MoveEntireBoardAlgorithmFactory factory) {
		leftAlgorithm = factory.forLeftCommand();
		rightAlgorithm = factory.forRightCommand();
		upAlgorithm = factory.forUpCommand();
		downAlgorithm = factory.forDownCommand();
	}

	public void left() {
		leftAlgorithm.makeMove();
	}

	public void right() {
		rightAlgorithm.makeMove();
	}

	public void up() {
		upAlgorithm.makeMove();
	}

	public void down() {
		downAlgorithm.makeMove();
	}
}