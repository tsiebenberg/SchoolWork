package animations;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import view.IGamePanel;

/**
 * 
 * @author Nick Gizzi
 * @author Jake Hewitt
 * @author Darshan Kothari
 * @author Taylor Siebenberg
 * 
 */
public class EatenByWumpus extends JPanel implements IGamePanel {

	private static final long serialVersionUID = -1283396581240747274L;
	private BufferedImage wumpus1, wumpus2, wumpus3;
	private BufferedImage[] wumpusPhotos;
	private BufferedImage currentImage;
	private int imageAt;
	private Graphics2D g2;
	private Timer timer;

	private int end;

	public EatenByWumpus() {
		wumpusPhotos = new BufferedImage[4];
		imageAt = 0;
		end = 0;
		try {
			wumpus1 = ImageIO
					.read(new File("WumpusSprite/Wumpus1WithBoot.png"));
			wumpus2 = ImageIO
					.read(new File("WumpusSprite/Wumpus2WithBoot.png"));
			wumpus3 = ImageIO
					.read(new File("WumpusSprite/Wumpus3WithBoot.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		wumpusPhotos[0] = wumpus1;
		wumpusPhotos[1] = wumpus2;
		wumpusPhotos[2] = wumpus3;
		wumpusPhotos[3] = wumpus2;

		startAnimation();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;

		g2.drawImage(currentImage, 0, 0, null);

	}

	private void startAnimation() {
		timer = new Timer(200, new MouthAnimation());
		timer.start();
	}

	private class MouthAnimation implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (imageAt == 4) {
				imageAt = 0;
			}
			currentImage = wumpusPhotos[imageAt];
			imageAt++;
			repaint();
			end++;
			if (end == 16) {
				timer.stop();
			}
		}

	}

	@Override
	public void passKey(int keyCode) {

	}

}