package test;

import javax.swing.JFrame;

import playerHierarchy.HumanPlayer;
import animations.StorePanel;

public class TestStorePanel extends JFrame {
	
	private static final long serialVersionUID = 1L;
	HumanPlayer player = new HumanPlayer(1,1);

	public static void main(String[] args) {
		new TestStorePanel().setVisible(true);
	}

	public TestStorePanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		add(new StorePanel(player));
	}
}
