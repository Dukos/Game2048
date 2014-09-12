package test.java.utils;

import static org.mockito.Mockito.verify;
import main.java.utils.Launcher;
import main.java.view.MainFrameCreator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LauncherTest {
	
	@Mock
	private MainFrameCreator mainFrameCreator;
	
	private Launcher testObject = new LauncherTested();
	
	class LauncherTested extends Launcher
	{
		@Override
		protected MainFrameCreator getMainFrameCreator()
		{
			return mainFrameCreator;
		}
		
		@Override
		protected void invokeLater(Runnable runnable)
		{
			//in tests we do not want to wait for other thread
			runnable.run();
		}
	}

	@Test
	public void launch_noSetup_createConfiguredMainFrameCalled() {
		testObject.launch();
		
		verify(mainFrameCreator).createConfiguredMainFrame();
	}

}
