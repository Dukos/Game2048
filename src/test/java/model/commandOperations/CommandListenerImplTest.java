package test.java.model.commandOperations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import main.java.model.commandOperations.CommandListenerImpl;
import main.java.model.commandOperations.MoveEntireBoardAlgorithm;
import main.java.model.commandOperations.orientations.MoveEntireBoardAlgorithmFactory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CommandListenerImplTest {

	@Mock
	private MoveEntireBoardAlgorithmFactory factory;

	@Mock
	private MoveEntireBoardAlgorithm algorithmLeft;

	@Mock
	private MoveEntireBoardAlgorithm algorithmRight;

	@Mock
	private MoveEntireBoardAlgorithm algorithmUp;

	@Mock
	private MoveEntireBoardAlgorithm algorithmDown;

	private CommandListenerImpl testObject;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(factory.forLeftCommand()).thenReturn(algorithmLeft);
		when(factory.forRightCommand()).thenReturn(algorithmRight);
		when(factory.forUpCommand()).thenReturn(algorithmUp);
		when(factory.forDownCommand()).thenReturn(algorithmDown);

		testObject = new CommandListenerImpl(factory);
	}

	@Test
	public void left_noSetup_moveMade() {
		when(factory.forLeftCommand()).thenReturn(algorithmLeft);
		testObject.left();
		verify(algorithmLeft).makeMove();
	}

	@Test
	public void right_noSetup_moveMade() {
		testObject.right();
		verify(algorithmRight).makeMove();
	}

	@Test
	public void up_noSetup_moveMade() {
		testObject.up();
		verify(algorithmUp).makeMove();
	}

	@Test
	public void down_noSetup_moveMade() {
		testObject.down();
		verify(algorithmDown).makeMove();
	}

}
