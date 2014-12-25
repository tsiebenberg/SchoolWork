package animations;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;
import javax.swing.Timer;

import view.IGamePanel;

public class FallInPit extends JPanel implements IGamePanel {

	private static final long serialVersionUID = 192393335661121463L;
	private int iteration, circleXPos, circleYPos, circleRadius;
	private Graphics2D g2;
	private Timer timer;

	public FallInPit() {
		this.setBackground(Color.BLACK);
		iteration = 0;
		startAnimation();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		setRadius();
		drawFont();
		drawCircle();
	}

	private void setRadius() {
		if (iteration == 0 || iteration == 1)
			circleRadius = this.getHeight() * 3 / 4;
	}

	private void drawFont() {
		// Our message
		String message = "Game Over";

		// Custom font, monospace font, increases with each iteration
		Font ponitficus = new Font(Font.MONOSPACED, Font.PLAIN, iteration);
		g2.setFont(ponitficus);
		g2.setColor(Color.WHITE);

		// Computes where the font should be drawn dependant upon its size
		int fontXOffest = (int) g2.getFontMetrics()
				.getStringBounds(message, g2).getWidth();
		int fontYOffest = (int) g2.getFontMetrics()
				.getStringBounds(message, g2).getHeight();
		g2.drawString(message, (this.getWidth() - fontXOffest) / 2,
				(20 + fontYOffest) / 2);
	}

	private void drawCircle() {
		// Calculates a dynamic x,y position based upon the size of the window
		// and size of the circle
		circleXPos = (this.getWidth() - circleRadius + iteration * 4) / 2;
		circleYPos = (this.getHeight() - circleRadius + iteration * 4) / 2;

		// Color and draw!
		g2.setColor(Color.LIGHT_GRAY);
		g2.fill(new Ellipse2D.Double(circleXPos, circleYPos, circleRadius
				- iteration * 4, circleRadius - iteration * 4));
	}

	private void startAnimation() {
		timer = new Timer(1000 / 32, new FallingAnimation());
		timer.start();
	}

	private class FallingAnimation implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			iteration++;
			repaint();
			if (iteration == (circleRadius / 5)) {
				timer.stop();
			}
		}
	}

	@Override
	public void passKey(int keyCode) {

	}

}