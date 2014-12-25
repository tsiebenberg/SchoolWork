package view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.HuntTheWumpusModel;

/**
 * 
 * @author Nick Gizzi
 * @author Jake Hewitt
 * @author Darshan Kothari
 * @author Taylor Siebenberg
 * 
 */
public class TextView extends JPanel implements IGamePanel {

	private static final long serialVersionUID = 4220793134257418415L;
	private HuntTheWumpusModel model;
	private boolean fireMode;
	private JTextArea textArea;

	public TextView(HuntTheWumpusModel model) {
		this.model = model;
		textArea = new JTextArea();
		setTextAreaPropeties();
		fireMode = false;
		layoutPanel();
	}

	public void setDifficulty(int Difficulty) {
		model.newGame(Difficulty);
		clearWindow();
		layoutPanel();
	}

	private void setTextAreaPropeties() {
		textArea.setPreferredSize(new Dimension(420, 500));
		textArea.setBackground(this.getBackground());
		textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
	}

	public void layoutPanel() {
		layoutPanel(model.checkWarning());
	}

	private void layoutPanel(String warning) {
		clearWindow();
		textArea.setText("");
		textArea.append(model.toString());
		textArea.append("\n");
		textArea.append(warning);
		this.add(textArea);
	}

	private void clearWindow() {
		this.removeAll();
		this.revalidate();
		this.repaint();
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
		String warning = "";
		if (model.gameOver()) {
			JOptionPane.showMessageDialog(null,
					"Press enter to start a new game.");
			return;
		}
		if (fireMode) {
			warning += model.fireUp();
			model.showMap();
		} else {
			warning += model.moveUp();
			if (model.checkDeath() || model.deathByPit())
				model.showMap();
		}
		layoutPanel(warning);
	}

	private void downKey() {
		String warning = "";
		if (model.gameOver()) {
			JOptionPane.showMessageDialog(null,
					"Press enter to start a new game.");
			return;
		}
		if (fireMode) {
			warning += model.fireDown();
			model.showMap();
		} else {
			warning += model.moveDown();
			if (model.checkDeath() || model.deathByPit())
				model.showMap();
		}
		layoutPanel(warning);
	}

	private void leftKey() {
		String warning = "";
		if (model.gameOver()) {
			JOptionPane.showMessageDialog(null,
					"Press enter to start a new game.");
			return;
		}
		if (fireMode) {
			warning += model.fireLeft();
			model.showMap();
		} else {
			warning += model.moveLeft();
			if (model.deathByWumpus() || model.deathByPit())
				model.showMap();
		}
		layoutPanel(warning);
	}

	private void rightKey() {
		String warning = "";
		if (model.gameOver()) {
			JOptionPane.showMessageDialog(null,
					"Press enter to start a new game.");
			return;
		}
		if (fireMode) {
			warning += model.fireRight();
			model.showMap();
		} else {
			warning += model.moveRight();
			if (model.checkDeath() || model.deathByPit())
				model.showMap();
		}
		layoutPanel(warning);
	}

}
