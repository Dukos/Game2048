package main.java.utils;

import java.util.Random;

import main.java.model.addingElements.ElementsAdder;
import main.java.model.addingElements.FreeFieldFinder;
import main.java.model.addingElements.Randomizer;
import main.java.model.boards.BoardArea;
import main.java.model.boards.BoardLogic;
import main.java.model.boards.Dimension;
import main.java.model.commandOperations.BoardLineMerger;
import main.java.model.commandOperations.BoardLineMover;
import main.java.model.commandOperations.BoardLineUtils;
import main.java.model.commandOperations.CommandsListener;
import main.java.model.commandOperations.orientations.MoveEntireBoardAlgorithmFactory;
import main.java.view.MainFrameCreator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("main.java")
public class Config
{
	@Autowired Dimension dimension;
	@Autowired BoardArea boardArea;
	
	@Autowired FreeFieldFinder freeFieldFinder;
	@Bean Random getRandom() { return new Random(); }
	@Autowired Randomizer randomizer;
	@Autowired ElementsAdder elementsAdder;
	
	@Autowired BoardLineUtils boardLineUtils;
	@Autowired BoardLineMover boardLineMover;
	@Autowired BoardLineMerger boardLineMerger;
	@Autowired MoveEntireBoardAlgorithmFactory moveEntireBoardAlgorithmFactory;
	@Autowired CommandsListener commandsListener;
	
	@Autowired BoardLogic boardLogic;
	
	@Autowired MainFrameCreator mainFrameCreator;
}