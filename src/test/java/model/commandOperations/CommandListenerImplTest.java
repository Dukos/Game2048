package test.java.model.commandOperations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import main.java.model.commandOperations.CommandListenerImpl;
import main.java.model.commandOperations.MoveEntireBoardAlgorithm;
import main.java.model.commandOperations.orientations.WallOrientation;
import main.java.model.commandOperations.orientations.WallOrientationFactory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CommandListenerImplTest {

	@Mock
	private MoveEntireBoardAlgorithm moveAlgorithm;
	
	@Mock
	private WallOrientationFactory factory;
	
	@Mock
	private WallOrientation orientationLeft;
	
	@Mock
	private WallOrientation orientationRight;
	
	@Mock
	private WallOrientation orientationUp;
	
	@Mock
	private WallOrientation orientationDown;
	
	
	private CommandListenerImpl testObject;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(factory.forLeftCommand()).thenReturn(orientationLeft);
		when(factory.forRightCommand()).thenReturn(orientationRight);
		when(factory.forUpCommand()).thenReturn(orientationUp);
		when(factory.forDownCommand()).thenReturn(orientationDown);
		
		testObject = new CommandListenerImpl(moveAlgorithm, factory);
	}

	@Test
	public void left_noSetup_moveMade() {
		testObject.left();
		verify(moveAlgorithm).makeMove(orientationLeft);
	}
	
	@Test
	public void right_noSetup_moveMade() {
		testObject.right();
		verify(moveAlgorithm).makeMove(orientationRight);
	}
	
	@Test
	public void up_noSetup_moveMade() {
		testObject.up();
		verify(moveAlgorithm).makeMove(orientationUp);
	}
	
	@Test
	public void down_noSetup_moveMade() {
		testObject.down();
		verify(moveAlgorithm).makeMove(orientationDown);
	}

}
