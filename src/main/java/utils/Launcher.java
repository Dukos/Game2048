package main.java.utils;

import main.java.model.commandOperations.CommandListenerImpl;
import main.java.model.commandOperations.CommandsListener;
import main.java.view.MainFrameCreator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value={"src.main.java"})
class Config
{
	@Bean
	MainFrameCreator getMainFrameCreator()
	{
		return new MainFrameCreator();
	}

}

public class Launcher {
	
	public static void main(String[] args) {
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
