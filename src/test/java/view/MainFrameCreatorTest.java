package test.java.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Component;

import javax.swing.JFrame;

import main.java.view.BoardPanel;
import main.java.view.MainFrameCreator;

import org.junit.Before;
import org.junit.Test;

public class MainFrameCreatorTest {

	private JFrame frame;
	
	@Before
	public void setUp() throws Exception {
		frame = new MainFrameCreator().createConfiguredMainFrame();
	}
	
	private BoardPanel findBoardPanel()
	{
		Component[] components = frame.getContentPane().getComponents();
		for(Component component : components)
			if(component instanceof BoardPanel)
			{
				return (BoardPanel) component;
			}
		return null;
	}
	
	@Test
	public void createStandaloneFrame_noSetup_returnsFrameObject() {
		assertNotNull(frame);
	}

	@Test
	public void createStandaloneFrame_noSetup_frameIsVisible() {
		assertTrue(frame.isVisible());
	}

	@Test
	public void createStandaloneFrame_noSetup_frameCloseOperationIsDefinedAsApplicationExit() {
		assertEquals(frame.getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
	}

	@Test
	public void createStandaloneFrame_noSetup_frameisDisplayable() {
		assertTrue(frame.isDisplayable());
	}
	
	@Test
	public void createStandaloneFrame_noSetup_frameTitleIsSetToGame2048() {
		assertEquals(frame.getTitle(), "Game2048");
	}
	
	@Test
	public void createStandaloneFrame_noSetup_boardPanelIsAdded() {
		assertNotNull(findBoardPanel());
	}

}
