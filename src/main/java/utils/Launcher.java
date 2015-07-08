package main.java.utils;

import main.java.view.MainFrameCreator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Launcher {
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = 
	          new AnnotationConfigApplicationContext(Config.class);
	      MainFrameCreator mainFrameCreator = context.getBean(MainFrameCreator.class);
	      
	      new Launcher().launch(mainFrameCreator);
	  }
	
	public void launch(final MainFrameCreator frameCreator)
	{
		invokeLater(new Runnable() {
			public void run() {
				frameCreator.createConfiguredMainFrame();
			}
		});
	}
	
	protected void invokeLater(Runnable runnable)
	{
		javax.swing.SwingUtilities.invokeLater(runnable);
	}
	
}
