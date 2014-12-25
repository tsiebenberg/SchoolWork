package test;

import javax.swing.JFrame;

import animations.EatenByWumpus;

public class WumpusAnimation extends JFrame {

	private static final long serialVersionUID = -2417844982990076038L;

	public static void main(String[] args) {
		new WumpusAnimation().setVisible(true);
	}

	public WumpusAnimation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(772, 582);
		add(new EatenByWumpus());
	}
}
