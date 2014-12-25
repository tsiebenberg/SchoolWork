package test;

import javax.swing.JFrame;

import animations.PCPanel;

public class TestPCPanel extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new TestPCPanel().setVisible(true);
	}

	public TestPCPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		//add(new PCPanel());
//		this.setContentPane(new PCPanel());
	}
}
