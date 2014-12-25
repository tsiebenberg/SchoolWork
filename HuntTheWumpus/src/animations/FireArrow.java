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

public class FireArrow extends JPanel implements IGamePanel {

	private static final long serialVersionUID = 2025320554271157416L;
	private BufferedImage arrow1, arrow2, arrow3, arrow4, arrow5, arrow6,
			arrow7, arrow8, arrow9, arrow10, arrow11, arrow12, arrow13,
			arrow14, arrow15, arrow16, arrow17, arrow18, arrow19;
	private BufferedImage[] arrowPhotos;
	private BufferedImage currentImage;
	private int imageAt;
	private Graphics2D g2;
	private Timer timer;

	public FireArrow() {
		arrowPhotos = new BufferedImage[19];
		imageAt = 0;
		loadFiles();
		setImageArray();
		currentImage = arrowPhotos[0];
		startAnimation();
	}

	private void loadFiles() {
		try {
			arrow1 = ImageIO.read(new File("WumpusSprite/ArrowFire_1.png"));
			arrow2 = ImageIO.read(new File("WumpusSprite/ArrowFire_2.png"));
			arrow3 = ImageIO.read(new File("WumpusSprite/ArrowFire_3.png"));
			arrow4 = ImageIO.read(new File("WumpusSprite/ArrowFire_4.png"));
			arrow5 = ImageIO.read(new File("WumpusSprite/ArrowFire_5.png"));
			arrow6 = ImageIO.read(new File("WumpusSprite/ArrowFire_6.png"));
			arrow7 = ImageIO.read(new File("WumpusSprite/ArrowFire_7.png"));
			arrow8 = ImageIO.read(new File("WumpusSprite/ArrowFire_8.png"));
			arrow9 = ImageIO.read(new File("WumpusSprite/ArrowFire_9.png"));
			arrow10 = ImageIO.read(new File("WumpusSprite/ArrowFire_10.png"));
			arrow11 = ImageIO.read(new File("WumpusSprite/ArrowFire_11.png"));
			arrow12 = ImageIO.read(new File("WumpusSprite/ArrowFire_12.png"));
			arrow13 = ImageIO.read(new File("WumpusSprite/ArrowFire_13.png"));
			arrow14 = ImageIO.read(new File("WumpusSprite/ArrowFire_14.png"));
			arrow15 = ImageIO.read(new File("WumpusSprite/ArrowFire_15.png"));
			arrow16 = ImageIO.read(new File("WumpusSprite/ArrowFire_16.png"));
			arrow17 = ImageIO.read(new File("WumpusSprite/ArrowFire_17.png"));
			arrow18 = ImageIO.read(new File("WumpusSprite/ArrowFire_18.png"));
			arrow19 = ImageIO.read(new File("WumpusSprite/ArrowFire_19.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setImageArray() {
		arrowPhotos[0] = arrow1;
		arrowPhotos[1] = arrow2;
		arrowPhotos[2] = arrow3;
		arrowPhotos[3] = arrow4;
		arrowPhotos[4] = arrow5;
		arrowPhotos[5] = arrow6;
		arrowPhotos[6] = arrow7;
		arrowPhotos[7] = arrow8;
		arrowPhotos[8] = arrow9;
		arrowPhotos[9] = arrow10;
		arrowPhotos[10] = arrow11;
		arrowPhotos[11] = arrow12;
		arrowPhotos[12] = arrow13;
		arrowPhotos[13] = arrow14;
		arrowPhotos[14] = arrow15;
		arrowPhotos[15] = arrow16;
		arrowPhotos[16] = arrow17;
		arrowPhotos[17] = arrow18;
		arrowPhotos[18] = arrow19;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;

		g2.drawImage(currentImage,
				(this.getWidth() - currentImage.getWidth()) / 2,
				(this.getHeight() - currentImage.getHeight()) / 2, null);

	}

	private void startAnimation() {
		timer = new Timer(80, new ArrowShotAnimation());
		timer.start();
	}

	private class ArrowShotAnimation implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			currentImage = arrowPhotos[imageAt];
			imageAt++;
			repaint();
			if (imageAt == 19) {
				timer.stop();
			}
		}
	}

	@Override
	public void passKey(int keyCode) {
		// TODO Auto-generated method stub

	}

}