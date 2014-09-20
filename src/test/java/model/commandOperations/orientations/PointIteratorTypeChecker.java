package test.java.model.commandOperations.orientations;

import main.java.model.commandOperations.orientations.IterarorImplementation;
import main.java.model.commandOperations.orientations.PointIteratorsCreator;
import main.java.model.commandOperations.orientations.ToBottomIteratorImplementation;
import main.java.model.commandOperations.orientations.ToLeftIteratorImplementation;
import main.java.model.commandOperations.orientations.ToRightIteratorImplementation;
import main.java.model.commandOperations.orientations.ToTopIteratorImplementation;

public class PointIteratorTypeChecker {
	public boolean isHorizontalIterator(PointIteratorsCreator iterator) {
		return isToBottomIterator(iterator) || isToTopIterator(iterator);
	}

	public boolean isToBottomIterator(PointIteratorsCreator iterator) {
		return isIteratorOfType(iterator, ToBottomIteratorImplementation.class);
	}

	public boolean isToTopIterator(PointIteratorsCreator iterator) {
		return isIteratorOfType(iterator, ToTopIteratorImplementation.class);
	}

	public boolean isVerticalIterator(PointIteratorsCreator iterator) {
		return isToLeftIterator(iterator) || isToRightIterator(iterator);
	}

	public boolean isToLeftIterator(PointIteratorsCreator iterator) {
		return isIteratorOfType(iterator, ToLeftIteratorImplementation.class);
	}

	public boolean isToRightIterator(PointIteratorsCreator iterator) {
		return isIteratorOfType(iterator, ToRightIteratorImplementation.class);
	}

	public boolean isIteratorOfType(PointIteratorsCreator groupIterator,
			Class<? extends IterarorImplementation> iterarorImplementationClass) {
		return groupIterator.getIteratorImplementation().getClass()
				.isAssignableFrom(iterarorImplementationClass);
	}
}
