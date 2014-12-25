package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.GameObjectImageGrabber;
import model.HuntTheWumpusModel;

/**
 * 
 * @author Nick Gizzi
 * @author Jake Hewitt
 * @author Darshan Kothari
 * @author Taylor Siebenberg
 * 
 */
public class GameView extends JPanel implements IGamePanel {

	private static final long serialVersionUID = -5577251065693704337L;
	private Graphics2D g2;
	private HuntTheWumpusModel model;
	private GameObjectImageGrabber imgGrab;
	private boolean fireMode;

	public GameView(HuntTheWumpusModel model) {
		this.model = model;
		this.setLayout(null);
		imgGrab = new GameObjectImageGrabber();
		fireMode = false;
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
		case 102:
			fireMode = !fireMode;
			break;
		}
	}

	private void upKey() {
		if (model.gameOver()) {
			JOptionPane.showMessageDialog(null,
					"Press enter to start a new game.");
			return;
		}
		if (fireMode) {
			model.fireUp();
			model.showMap();
		} else {
			model.moveUp();
			if (model.checkDeath() || model.deathByPit())
				model.showMap();
			repaint();
		}
	}

	private void downKey() {
		if (model.gameOver()) {
			JOptionPane.showMessageDialog(null,
					"Press enter to start a new game.");
			return;
		}
		if (fireMode) {
			model.fireDown();
			model.showMap();
		} else {
			model.moveDown();
			if (model.checkDeath() || model.deathByPit())
				model.showMap();
			repaint();
		}
	}

	private void leftKey() {
		if (model.gameOver()) {
			JOptionPane.showMessageDialog(null,
					"Press enter to start a new game.");
			return;
		}
		if (fireMode) {
			model.fireLeft();
			model.showMap();
		} else {
			model.moveLeft();
			if (model.deathByWumpus() || model.deathByPit())
				model.showMap();
			repaint();
		}
	}

	private void rightKey() {
		if (model.gameOver()) {
			JOptionPane.showMessageDialog(null,
					"Press enter to start a new game.");
			return;
		}
		if (fireMode) {
			model.fireRight();
			model.showMap();
		} else {
			model.moveRight();
			if (model.checkDeath() || model.deathByPit())
				model.showMap();
			repaint();
		}
	}

	public void setDifficulty(int Difficulty) {
		model.newGame(Difficulty);
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		int XLocation = 0, YLocation = 0;
		int XOffset = (this.getWidth() - 500) / 2, YOffset = (this.getHeight() - 500) / 2;
		// Draw ground base first
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				g2.drawImage(imgGrab.getGroundImage(), XLocation + XOffset,
						YLocation + YOffset, null);
				XLocation += 50;
			}
			XLocation = 0;
			YLocation += 50;
		}
		XLocation = 0;
		YLocation = 0;
		// Draw flags or hidden second
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				g2.drawImage(getCorrectImg(XLocation / 50, YLocation / 50),
						XLocation + XOffset, YLocation + YOffset, null);
				XLocation += 50;
			}
			XLocation = 0;
			YLocation += 50;
		}
		XLocation = 0;
		YLocation = 0;
		// Draw Hunter
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (model.getGameMap().getRoom(i, j).getRoomChar() == 'O')
					g2.drawImage(imgGrab.getHunterImage(), XLocation + XOffset,
							YLocation + YOffset, null);
				XLocation += 50;
			}
			XLocation = 0;
			YLocation += 50;
		}
		XLocation = 0;
		YLocation = 0;
	}

	private BufferedImage getCorrectImg(int col, int row) {
		char roomObject = model.getGameMap().getRoom(row, col).getFlags();
		if (!model.getGameMap().getRoom(row, col).getVisiblity()) {
			return imgGrab.getHiddenGroundImage();
		}
		switch (roomObject) {
		case 'O':
			return imgGrab.getHunterImage();
		case 'W':
			return imgGrab.getWumpusImage();
		case 'S':
			return imgGrab.getSlimeImage();
		case 'G':
			return imgGrab.getGoopImage();
		case 'B':
			return imgGrab.getBloodImage();
		case 'P':
			return imgGrab.getPitImage();
		case ' ':
			return imgGrab.getGroundImage();
		default:
			return imgGrab.getHiddenGroundImage();
		}

	}

}
