package test;

import javax.swing.JFrame;

import animations.BlackOutPanel;

public class TestBlackOutPanel extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new TestBlackOutPanel().setVisible(true);
	}

	public TestBlackOutPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		add(new BlackOutPanel());
	}
}
