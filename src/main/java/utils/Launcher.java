package main.java.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.view.MainFrameCreator;


public class Launcher {
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrameCreator().createConfiguredMainFrame();
			}
		});
		instance = new Launcher(args);
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
		applicationLaunchArguments = convertLaunchArguments(args);
	}

	private List<String> convertLaunchArguments(String[] args) {
		List<String> list = new ArrayList<String>(); 
		for(String string : args)
			list.add(string);
		return list;
	}
	
	private List<String> applicationLaunchArguments;
	
	private static Launcher instance; 
}
