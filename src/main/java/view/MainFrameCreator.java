package main.java.view;

import javax.swing.JFrame;

public class MainFrameCreator {

	private static final String FRAME_LABEL = "Game2048";

	public JFrame createConfiguredMainFrame() {
		JFrame frame = createAndSetupWindow();
		addBoardPanel(frame);
		displayWindow(frame);
		return frame;
	}

	private JFrame createAndSetupWindow() {
		JFrame frame = new JFrame(FRAME_LABEL);
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
