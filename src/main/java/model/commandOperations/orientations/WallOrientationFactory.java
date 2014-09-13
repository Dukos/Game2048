package main.java.model.commandOperations.orientations;

import java.awt.Dimension;

public class WallOrientationFactory {
	
	private PointIteratorsCreator toLeftIterator;
	
	private PointIteratorsCreator toRightIterator; 

	private PointIteratorsCreator toBottomIterator; 
	
	private PointIteratorsCreator toTopIterator; 
	
	public WallOrientationFactory(Dimension dimensions)
	{
		toLeftIterator = new PointIteratorsCreator(new ToLeftIteratorImplementation(), dimensions);
		toRightIterator = new PointIteratorsCreator(new ToRightIteratorImplementation(), dimensions);
		toBottomIterator = new PointIteratorsCreator(new ToBottomIteratorImplementation(), dimensions);
		toTopIterator = new PointIteratorsCreator(new ToTopIteratorImplementation(), dimensions);
	}
	
	public WallOrientation forLeftCommand()
	{
		return new WallOrientation(toBottomIterator, toLeftIterator);
	}
	
	public WallOrientation forRightCommand()
	{
		return new WallOrientation(toBottomIterator, toRightIterator);
	}
	
	public WallOrientation forUpCommand()
	{
		return new WallOrientation(toRightIterator, toTopIterator);
	}
	
	public WallOrientation forDownCommand()
	{
		return new WallOrientation(toRightIterator, toBottomIterator);
	}
}
