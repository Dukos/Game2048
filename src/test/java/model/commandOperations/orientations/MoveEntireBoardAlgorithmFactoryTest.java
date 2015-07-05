package test.java.model.commandOperations.orientations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static test.java.model.commandOperations.orientations.PointIteratorTypeChecker.isHorizontalIterator;
import static test.java.model.commandOperations.orientations.PointIteratorTypeChecker.isToBottomIterator;
import static test.java.model.commandOperations.orientations.PointIteratorTypeChecker.isToLeftIterator;
import static test.java.model.commandOperations.orientations.PointIteratorTypeChecker.isToRightIterator;
import static test.java.model.commandOperations.orientations.PointIteratorTypeChecker.isToTopIterator;
import static test.java.model.commandOperations.orientations.PointIteratorTypeChecker.isVerticalIterator;
import main.java.model.boards.Dimension;
import main.java.model.commandOperations.BoardLineMerger;
import main.java.model.commandOperations.BoardLineMover;
import main.java.model.commandOperations.MoveEntireBoardAlgorithm;
import main.java.model.commandOperations.orientations.MoveEntireBoardAlgorithmFactory;
import main.java.model.commandOperations.orientations.PointIteratorsCreator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MoveEntireBoardAlgorithmFactoryTest {
	
	@Mock
	private Dimension dimensions;
	
	@Mock
	private BoardLineMover mover;
	
	@Mock
	private BoardLineMerger merger;
	
	@Mock
	private MoveEntireBoardAlgorithm result;

	@InjectMocks
	@Spy
	private MoveEntireBoardAlgorithmFactory testObject;
	
	@Captor
	ArgumentCaptor<PointIteratorsCreator> lineSelector;
	
	@Captor
	ArgumentCaptor<PointIteratorsCreator> lineIterator;
	
    @Before
    public void init(){
		doReturn( result )
	    .when( testObject )
	    .createAlgorithm( eq(mover), eq(merger), lineSelector.capture(), lineIterator.capture());
    }

	@Test
	public void forLeftCommand_noAdditionalSetup_createsCorrectalg() {
		MoveEntireBoardAlgorithm alg = testObject.forLeftCommand();

		assertEquals(alg, result);
		assertTrue(isHorizontalIterator(lineSelector.getValue()));
		assertTrue(isToRightIterator(lineIterator.getValue()));
	}

	@Test
	public void forRightCommand_noAdditionalSetup_createsCorrectalg() {
		MoveEntireBoardAlgorithm alg = testObject.forRightCommand();
		
		assertEquals(alg, result);
		assertTrue(isHorizontalIterator(lineSelector.getValue()));
		assertTrue(isToLeftIterator(lineIterator.getValue()));
	}

	@Test
	public void forDownCommand_noAdditionalSetup_createsCorrectalg() {
		MoveEntireBoardAlgorithm alg = testObject.forDownCommand();
		
		assertEquals(alg, result);
		assertTrue(isVerticalIterator(lineSelector.getValue()));
		assertTrue(isToBottomIterator(lineIterator.getValue()));
	}

	@Test
	public void forUpCommand_noAdditionalSetup_createsCorrectalg() {
		MoveEntireBoardAlgorithm alg = testObject.forUpCommand();
		
		assertEquals(alg, result);
		assertTrue(isVerticalIterator(lineSelector.getValue()));
		assertTrue(isToTopIterator(lineIterator.getValue()));
	}
}
