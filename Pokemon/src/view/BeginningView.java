package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.PokemonModel;

public class BeginningView extends JPanel {

	JButton continueGame, newGame, instructions;
	JTextArea instructionsText;
	JButton back;

	public BeginningView() {
		initializeVariables();
		layoutThePanel();
		registerListeners();
	}

	private void layoutThePanel() {
		// TODO Auto-generated method stub
		this.setLayout(new GridLayout(3, 1, 20, 20));
		this.add(continueGame);
		this.add(newGame);
		this.add(instructions);

	}

	private void initializeVariables() {
		// TODO Auto-generated method stub
		this.continueGame = new JButton("Continue");
		this.newGame = new JButton("New Game");
		this.instructions = new JButton("Instructions");

	}

	private void registerListeners() {
		// TODO Auto-generated method stub
		AddButtonListener bv = new AddButtonListener();
		continueGame.addActionListener(bv);
		newGame.addActionListener(bv);
		instructions.addActionListener(bv);

	}

	public void InstructionsPanel() {
		initializeVariables2();
		layoutThePanel2();
		this.setVisible(true);
	}

	private void layoutThePanel2() {
		// TODO Auto-generated method stub
		AddButtonListener bv = new AddButtonListener();
		this.setLayout(new GridLayout(2, 1, 20, 20));
		this.add(instructionsText);
		this.add(back);
		back.addActionListener(bv);
	}

	private void initializeVariables2() {
		// TODO Auto-generated method stub
		instructionsText = new JTextArea(
				"Instructions: There are two ways of winning this game. The first way is to capture all 64 pokemon. The second way is to simply "
						+ "just beat the boss. Otherwise, this game is played to same way as Pokemon. If you have not played pokemon before, its simple! Pokemon are"
						+ " creatures that live in the pokemon realm that have special abilities. Basically, you fight a pokemon with another pokemon. If your pokemon "
						+ "die, then you can heal them at the Pokemon Center. In this game, you start off with Pikachu. \n \n Controls: In order to move arround "
						+ "the Pokemon realm, use the WASD keys. To select your Pokemon use your mouse to click. \n \n Let your adventure Begin!!!!!! :)");
		instructionsText.setLineWrap(true);
		instructionsText.setWrapStyleWord(true);
		back = new JButton("Back");
	}

	private class AddButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent source) {
			// TODO Auto-generated method stub

			// TODO Need to add listeners to swith out panels.

			if (source.getSource().equals(newGame)) {

			}

			if (source.getSource().equals(continueGame)) {

				try {
					FileInputStream inFile = new FileInputStream("./savedGame/"
							+ "game");
					ObjectInputStream inputStream = new ObjectInputStream(
							inFile);
					PokemonModel prev = (PokemonModel) inputStream.readObject();

					// /pass in prev

					inputStream.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			if (source.getSource().equals(instructions)) {
				removeAll();
				InstructionsPanel();
				revalidate();
				repaint();
			}

			if (source.getSource().equals(back)) {
				removeAll();
				initializeVariables();
				layoutThePanel();
				registerListeners();
				revalidate();
				repaint();
			}
		}
	}

}
