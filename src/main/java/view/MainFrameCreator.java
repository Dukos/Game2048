package main.java.view;

import javax.swing.JFrame;

import org.springframework.stereotype.Component;

@Component
public class MainFrameCreator {

	public JFrame createConfiguredMainFrame() {
		JFrame frame = createAndSetupWindow();
		addBoardPanel(frame);
		displayWindow(frame);
		return frame;
	}

	private JFrame createAndSetupWindow() {
		final String frameLabel = "Game2048";
		JFrame frame = new JFrame(frameLabel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}

	private void addBoardPanel(JFrame frame) {
		BoardPanel board = new BoardPanel();
		frame.getContentPane().add(board);
	}

	private void displayWindow(JFrame frame) {
		frame.pack();
		frame.setVisible(true);
	}
}
