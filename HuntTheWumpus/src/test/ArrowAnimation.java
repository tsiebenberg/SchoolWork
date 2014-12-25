package test;

import javax.swing.JFrame;

import animations.FireArrow;

public class ArrowAnimation extends JFrame {

	private static final long serialVersionUID = 5304359854570019332L;

	public static void main(String[] args) {
		new ArrowAnimation().setVisible(true);
	}

	public ArrowAnimation() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(530, 552);
		add(new FireArrow());
	}

}