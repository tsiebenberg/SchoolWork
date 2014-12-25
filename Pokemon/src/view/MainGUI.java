package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mapHierarchy.DungeonZone;
import mapHierarchy.GymZone;
import mapHierarchy.MainZone;
import mapHierarchy.PokeCenterZone;
import model.PokemonModel;
import animations.BattleAnimation;
import animations.PCPanel;
import animations.StorePanel;

@SuppressWarnings("serial")
public class MainGUI extends JFrame implements Serializable {

	PokemonModel model;
//	PokeCenterZone pc;
//	DungeonZone dz;
//	GymZone gz;

	// Panels
	MainZoneView mainZoneView;
	DungeonView dungeonView;
	PokeCenterView pokecenterView;
	GymView gymView;
	PCPanel pcView;
	StorePanel sp;
	EndGameView endView;

	BattleAnimation ba;

	// Menu Bar
	JMenuBar menuBar;

	// file menu items
	JMenu fileMenu;
	JMenu inventory;
	JMenu aboutMenu;

	// File menu items
	JMenuItem newGame;
	JMenuItem loadGame;
	JMenuItem save;
	JMenuItem exit;

	// About menu items
	JMenuItem controls;
	JMenuItem items;
	JMenuItem pokemon;
	JMenuItem about;
	static MainGUI window;
	private String basedir = "./Pokemon/savedGame";

	boolean zoneChange;

	boolean traded;

	public static void main(String[] args) {
		window = new MainGUI();
		window.setVisible(true);

	}

	/**
	 * The constructor, responsible for initializing all the necessary variables
	 * and setting the initial view
	 */
	public MainGUI() {
		initializeGlobalVar();
		setProperties();

		addMenuItems();
		setJMenuBar(menuBar);
		registerListeners();
		setGameView();
	}

	private void addMenuItems() {
		menuBar.add(fileMenu);
		fileMenu.add(newGame);
		fileMenu.add(loadGame);
		fileMenu.add(save);
		fileMenu.add(exit);

		menuBar.add(inventory);
		inventory.add(items);
		inventory.add(pokemon);

		menuBar.add(aboutMenu);
		aboutMenu.add(controls);
		aboutMenu.add(about);
	}

	private void setProperties() {
		// Set properties of JFrame
		this.setSize(820, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		// this.setIconImage(new SpriteManager().getPokemonImage(15));
		this.addKeyListener(new KeyListen());
	}

	private void initializeGlobalVar() {
		model = new PokemonModel();
//		pc = new PokeCenterZone();
//		dz = new DungeonZone();
//		gz = new GymZone();

		mainZoneView = new MainZoneView(model);
		dungeonView = new DungeonView(model);
		gymView = new GymView(model);
		pokecenterView = new PokeCenterView(model);
		endView = new EndGameView(model);

		pcView = new PCPanel(model);
		sp = new StorePanel(model.getPlayer());

		traded = false;

		menuBar = new JMenuBar();
		// Menus
		fileMenu = new JMenu("File");
		aboutMenu = new JMenu("About");
		inventory = new JMenu("Inventory");
		// File menu items
		newGame = new JMenuItem("New Game");
		loadGame = new JMenuItem("Load Game");
		save = new JMenuItem("Save");
		exit = new JMenuItem("Exit");
		// About menu items
		controls = new JMenuItem("Controls");
		pokemon = new JMenuItem("Pokemon");
		items = new JMenuItem("Items");
		about = new JMenuItem("About Pokemon V1.0");
	}

	public JPanel getPokeCenterView() {
		return pokecenterView;
	}

	public void setGameView() {

		switchPanel(mainZoneView);

	}

	public void switchPanel(JPanel gamePanel) {
		this.getContentPane().removeAll();
		this.getRootPane().revalidate();
		this.setContentPane(gamePanel);
		this.getContentPane().repaint();

	}

	private void registerListeners() {
		MenuListener ml = new MenuListener();
		newGame.addActionListener(ml);
		save.addActionListener(ml);
		loadGame.addActionListener(ml);
		exit.addActionListener(ml);
		controls.addActionListener(ml);
		items.addActionListener(ml);
		pokemon.addActionListener(ml);
		about.addActionListener(ml);
	}

	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent source) {
			if (source.getSource() == save) {
				

			}
			if (source.getSource() == loadGame) {
				

			}
			if (source.getSource() == newGame) {
				
			}
			if (source.getSource() == exit)
				System.exit(0);

			if (source.getSource() == about) {
				JOptionPane.showMessageDialog(null, "Authors:\n\n"
						+ "Nick Gizzi\n" + "Jake Hewitt\n"
						+ "Darshan Kothari\n" + "Taylor Siebenberg");
			}
			if (source.getSource() == controls) {
				JOptionPane.showMessageDialog(null, "Controls:\n"
						+ "To move: WASD or directional keys\n"
						+ "To interact with enviroment: Enter\n"
						+ "To exit computer or store: ESC\n");
			}

			if (source.getSource() == items) {
				String itemString = "";
				itemString += "Items:\n\n";
				for (int i = 0; i < model.getPlayer().getItems().size(); i++) {
					itemString += ""
							+ model.getPlayer().getItems().get(i).toString()
							+ "\n";
				}
				JOptionPane.showMessageDialog(null, itemString);
			}
			if (source.getSource() == pokemon) {
				String pokemonString = "";
				pokemonString += "Pokemon:\n\n";
				for (int i = 0; i < model.getPlayer().getPokemon().size(); i++) {
					pokemonString += ""
							+ model.getPlayer().getPokemon().get(i).getName()
							+ " Level: "
							+ model.getPlayer().getPokemon().get(i).getLevel()
							+ "\n";
				}
				JOptionPane.showMessageDialog(null, pokemonString);
			}

		}

	}

	private class KeyListen extends KeyAdapter {

		@Override
		public void keyTyped(KeyEvent key) {
			if (model.getPlayerZone() instanceof MainZone) {
				switchPanel(mainZoneView);
				mainZoneView.move(key.getKeyChar(), true);
				if (model.getPlayerZone() instanceof GymZone) {
					gymView.move(key.getKeyChar(), false);
					switchPanel(gymView);
				}
				if (model.getPlayerZone() instanceof PokeCenterZone) {
					pokecenterView.move(key.getKeyChar(), false);
					switchPanel(pokecenterView);
				}
				if (model.getPlayerZone() instanceof DungeonZone) {
					dungeonView.move(key.getKeyChar(), false);
					switchPanel(dungeonView);
				}

			}
			if (model.getPlayerZone() instanceof GymZone) {
				switchPanel(gymView);
				gymView.move(key.getKeyChar(), true);
				if (model.getPlayerZone() instanceof MainZone) {
					mainZoneView.move(key.getKeyChar(), false);
					switchPanel(mainZoneView);
				}
			}
			if (model.getPlayerZone() instanceof DungeonZone) {
				switchPanel(dungeonView);
				dungeonView.move(key.getKeyChar(), true);
				if (model.getPlayerZone() instanceof MainZone) {
					mainZoneView.move(key.getKeyChar(), false);
					switchPanel(mainZoneView);
				}
			}
			if (model.getPlayerZone() instanceof PokeCenterZone) {
				switchPanel(pokecenterView);
				pokecenterView.move(key.getKeyChar(), true);
				if (model.getPlayerZone() instanceof MainZone) {
					mainZoneView.move(key.getKeyChar(), false);
					switchPanel(mainZoneView);
				}
			}
			if (model.gameOver() == true) {
				switchPanel(new EndGameView(model));
			}
		}

		@Override
		public void keyPressed(KeyEvent key) {

		}

		private void passKey(int keyCode) {

		}

		@Override
		public void keyReleased(KeyEvent key) {
			if (model.getPlayerZone() instanceof PokeCenterZone) {
				if (key.getKeyCode() == KeyEvent.VK_ENTER) {
					if (model.getPlayer().getXPosition() == 2
							&& model.getPlayer().getYPosition() == 8) {
						pcView.setVisible(true);

						JOptionPane
								.showMessageDialog(null,
										"Store or withdraw a pokemon, click x in upper left corner to exit");
					}
					if (model.getPlayer().getXPosition() == 2
							&& model.getPlayer().getYPosition() == 2) {

						sp.setVisible(true);

						JOptionPane
								.showMessageDialog(null,
										"Buy or Sell Items, click x in upper left corner to exit");

					}
					if (model.getPlayer().getXPosition() == 4
							&& model.getPlayer().getYPosition() == 5) {
						model.getPokeCenterZone()
								.healPokemon(model.getPlayer());
						JOptionPane.showMessageDialog(null,
								"Your pokemon were healed");
					}

				}
				if (key.getKeyCode() == KeyEvent.VK_ESCAPE)
					switchPanel(pokecenterView);
			}

			if (model.getPlayerZone() instanceof MainZone) {
				if (key.getKeyCode() == KeyEvent.VK_ENTER) {
					if (model.getPlayer().getXPosition() == 9
							&& model.getPlayer().getYPosition() == 12) {
						if (model.getAI().getTraded() == false) {
							if (model.getAI().cantrade() == true) {
								int yesOrNo = JOptionPane
										.showConfirmDialog(
												null,
												"Do You Wish to Trade Your Pikachu for Mew?",
												"Trader",
												JOptionPane.YES_NO_OPTION);
								if (yesOrNo == 0) {
									model.getAI().trade();
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"You do not have Pikachu!");
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"You have already traded with me!");
						}
					}
				}
			}
		}
	} // end of private class

	public void exitPC() {
		switchPanel(pokecenterView);
		pokecenterView.move('s', false);
	}

}
