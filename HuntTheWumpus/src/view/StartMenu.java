package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import model.DifficultyImageGrabber;

/**
 * 
 * @author Nick Gizzi
 * @author Jake Hewitt
 * @author Darshan Kothari
 * @author Taylor Siebenberg
 * 
 */
public class StartMenu extends JPanel implements IGamePanel {

	private static final long serialVersionUID = 8575794755260983766L;

	private Graphics2D g2;
	private DifficultyImageGrabber imgGrab = new DifficultyImageGrabber();
	private BufferedImage title, easy, medium, hard, asian;
	private int currentDifficulty;

	public StartMenu() {
		title = imgGrab.getTitleImage();
		easy = imgGrab.getEasyImage();
		medium = imgGrab.getMediumImage();
		hard = imgGrab.getHardImage();
		asian = imgGrab.getAsianImage();
		currentDifficulty = 0;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;

		g2.drawImage(title, (this.getWidth() - title.getWidth()) / 2, 30, null);
		g2.drawImage(easy, (this.getWidth() - easy.getWidth()) / 2, 200, null);
		g2.drawImage(medium, (this.getWidth() - medium.getWidth()) / 2, 275,
				null);
		g2.drawImage(hard, (this.getWidth() - hard.getWidth()) / 2, 350, null);
		g2.drawImage(asian, (this.getWidth() - asian.getWidth()) / 2, 425, null);

	}

	@Override
	public void passKey(int keyCode) {
		switch (keyCode) {
		// Up
		case 38:
		case 119:
			upKey();
			break;
		// Down
		case 40:
		case 115:
			downKey();
			break;
		// Left
		case 37:
		case 97:
			leftKey();
			break;
		// Right
		case 39:
		case 100:
			rightKey();
			break;
		}
	}

	private void upKey() {
		selectPrevious();
	}

	private void downKey() {
		selectNext();
	}

	private void leftKey() {
		selectPrevious();
	}

	private void rightKey() {
		selectNext();
	}

	private void selectNext() {
		switch (currentDifficulty) {
		case 0:
			easy = imgGrab.getEasySelectedImage();
			currentDifficulty++;
			repaint();
			break;
		case 1:
			easy = imgGrab.getEasyImage();
			medium = imgGrab.getMediumSelectedImage();
			currentDifficulty++;
			repaint();
			break;
		case 2:
			medium = imgGrab.getMediumImage();
			hard = imgGrab.getHardSelectedImage();
			currentDifficulty++;
			repaint();
			break;
		case 3:
			hard = imgGrab.getHardImage();
			asian = imgGrab.getAsianSelectedImage();
			currentDifficulty++;
			repaint();
			break;
		case 4:
			asian = imgGrab.getAsianImage();
			easy = imgGrab.getEasySelectedImage();
			currentDifficulty = 1;
			repaint();
			break;
		}
	}

	private void selectPrevious() {
		switch (currentDifficulty) {
		case 0:
			asian = imgGrab.getAsianSelectedImage();
			currentDifficulty = 4;
			repaint();
			break;
		case 1:
			easy = imgGrab.getEasyImage();
			asian = imgGrab.getAsianSelectedImage();
			currentDifficulty = 4;
			repaint();
			break;
		case 2:
			medium = imgGrab.getMediumImage();
			easy = imgGrab.getEasySelectedImage();
			currentDifficulty--;
			repaint();
			break;
		case 3:
			hard = imgGrab.getHardImage();
			medium = imgGrab.getMediumSelectedImage();
			currentDifficulty--;
			repaint();
			break;
		case 4:
			asian = imgGrab.getAsianImage();
			hard = imgGrab.getHardSelectedImage();
			currentDifficulty--;
			repaint();
			break;
		}
	}

	public int getDifficulty() {
		// Medium difficulty
		if (currentDifficulty == 2)
			return 5;
		// Hard difficulty
		if (currentDifficulty == 3)
			return 10;
		// Asian difficulty
		if (currentDifficulty == 4)
			return 20;
		// 0 or Easy difficulty
		return 1;

	}

}
