package main.java.utils;

import main.java.view.MainFrameCreator;


public class Launcher {
	
	public static void main(String[] args) {
		new Launcher().launch();
	}
	
	public void launch()
	{
		invokeLater(new Runnable() {
			public void run() {
				getMainFrameCreator().createConfiguredMainFrame();
			}
		});
	}
	
	protected void invokeLater(Runnable runnable)
	{
		javax.swing.SwingUtilities.invokeLater(runnable);
	}
	
	protected MainFrameCreator getMainFrameCreator()
	{
		return new MainFrameCreator();
	}

	protected Launcher() {
	}
	
}
