package test;

import javax.swing.JFrame;

import animations.FallInPit;

public class PitFallAnimation extends JFrame {

	private static final long serialVersionUID = -7008951739775891666L;

	public static void main(String[] args) {
		new PitFallAnimation().setVisible(true);
	}

	public PitFallAnimation() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(772, 582);
		FallInPit fp = new FallInPit();
		this.setContentPane(fp);
	}

}
