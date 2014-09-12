package main.java.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import main.java.view.MainFrameCreator;


public class Launcher {
	
	public static void main(String[] args) {
		instance = new Launcher(args);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrameCreator().createConfiguredMainFrame();
			}
		});
	}
	
	public Launcher getInstance()
	{
		return instance;
	}
	
	public String getApplicationPath()
	{
		return applicationLaunchArguments.get(0);
	}
	
	public List<String> getApplicationLaunchArguments() {
		return Collections.unmodifiableList(applicationLaunchArguments);
	}

	protected Launcher(String[] args) {
		applicationLaunchArguments = Arrays.asList(args);
	}
	
	private final List<String> applicationLaunchArguments;
	
	private static Launcher instance = null;
	
}
