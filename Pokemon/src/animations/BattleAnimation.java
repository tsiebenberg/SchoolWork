package animations;

import itemHierarchy.Item;
import itemHierarchy.Pokeball;
import itemHierarchy.Potion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import model.PokemonModel;
import model.SpriteManager;
import playerHierarchy.ComputerPlayer;
import playerHierarchy.HumanPlayer;
import playerHierarchy.Player;
import pokemonHierarchy.IMove;
import pokemonHierarchy.Pokemon;
import view.EndGameView;
import battle.Battle;

@SuppressWarnings("serial")
public class BattleAnimation extends JFrame {

	// Actual Data
	Battle battle;
	private HumanPlayer player;
	private ComputerPlayer computerPlayer;

	
	private SpriteManager spriteManager;
	Graphics2D g2;
	private boolean drawingActive;

	private Pokemon defPokemon;
	private Pokemon attPokemon;
	private boolean trainer;

	// JComponents
	private JButton select;
	private JButton moveButton;
	private JButton itemButton;
	private JButton pokeButton;
	private JButton move1, move2, move3, move4;
	private JButton potion, pokeball;
	private TableModel playerModel;
	private JTable playerTable;
	private JScrollPane playerScrollPane;
	private Drawings draw = new Drawings();
	private PokemonModel model;

	public BattleAnimation(Player p1, Player p2, PokemonModel model) {
		player = (HumanPlayer) p1;
		attPokemon = player.getPokemon().get(0);
		computerPlayer = (ComputerPlayer) p2;
		defPokemon = p2.getStrategy().getAttacker();
		trainer = true;
		this.model = model;
		initializeVars();
		setProperties();
		registerListeners();
		layoutSelectionPanel();

	}

	public BattleAnimation(Player p1, Pokemon wildPokemon, PokemonModel model) {
		player = (HumanPlayer) p1;

		attPokemon = player.getPokemon().get(0);
		defPokemon = wildPokemon;
		trainer = false;
		this.model = model;
		initializeVars();
		setProperties();
		registerListeners();
		layoutSelectionPanel();

	}

	private void initializeVars() {

		battle = new Battle(model);
		spriteManager = new SpriteManager();
		drawingActive = false;

		// JList
		playerModel = player;
		playerTable = new JTable(playerModel);
		playerScrollPane = new JScrollPane(playerTable);

		// JComponents
		select = new JButton();
		moveButton = new JButton();
		itemButton = new JButton();
		pokeButton = new JButton();

		move1 = new JButton();
		move2 = new JButton();
		move3 = new JButton();
		move4 = new JButton();

		potion = new JButton();
		pokeball = new JButton();

	}

	private void setProperties() {
		this.setLayout(null);
		this.setSize(820, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		draw.setSize(800, 600);
		draw.setLocation(0, 0);

		select.setText("Select Pokemon");
		select.setSize(200, 50);
		select.setLocation(100, 325);

		moveButton.setText("Select Move");
		moveButton.setSize(150, 30);
		moveButton.setLocation(450, 650);

		itemButton.setText("Use Item");
		itemButton.setSize(150, 30);
		itemButton.setLocation(450, 700);

		pokeButton.setText("Change Pokemon");
		pokeButton.setSize(150, 30);
		pokeButton.setLocation(625, 650);

		move1.setText(attPokemon.getMoves().get(0).getMOVE_NAME());
		move1.setSize(150, 30);
		move1.setLocation(450, 650);

		move2.setText(attPokemon.getMoves().get(1).getMOVE_NAME());
		move2.setSize(150, 30);
		move2.setLocation(625, 650);

		move3.setText(attPokemon.getMoves().get(2).getMOVE_NAME());
		move3.setSize(150, 30);
		move3.setLocation(450, 700);

		move4.setText(attPokemon.getMoves().get(3).getMOVE_NAME());
		move4.setSize(150, 30);
		move4.setLocation(625, 700);

		potion.setText("Use Potion");
		potion.setSize(150, 30);
		potion.setLocation(450, 650);

		pokeball.setText("Use pokeball");
		pokeball.setSize(150, 30);
		pokeball.setLocation(625, 650);

		playerScrollPane.setSize(400, 500);
		playerScrollPane.setLocation(350, 100);
	}

	private void registerListeners() {
		ButtonListener bl = new ButtonListener();
		select.addActionListener(bl);
		moveButton.addActionListener(bl);
		itemButton.addActionListener(bl);
		pokeButton.addActionListener(bl);
		move1.addActionListener(bl);
		move2.addActionListener(bl);
		move3.addActionListener(bl);
		move4.addActionListener(bl);
		potion.addActionListener(bl);
		pokeball.addActionListener(bl);
	}

	private void layoutSelectionPanel() {
		clearPanel();

		this.add(select);
		this.add(playerScrollPane);
		drawingActive = true;
	}

	private void layoutBattlePanel() {
		if (trainer == false) {
			if (defPokemon.isPokemonDead() || battle.getCaught() == true) {
				if (defPokemon.isPokemonDead()) {
					attPokemon.increaseEXP((int) Math.ceil((defPokemon
							.getLevel() * 50) / attPokemon.getLevel()));
				}
				this.dispose();
			} else if (player.istrainerOutOfPokemon()) {
				clearPanel();
			//	this.add(new BlackOutPanel());
				//this.add(new EndGameView(model));
				this.dispose();
				return;
			} else if (attPokemon.isPokemonDead()) {
				clearPanel();
				layoutSelectionPanel();
				return;
			}

		} else {
			if (player.istrainerOutOfPokemon()) {
				clearPanel();
				player.giveMoney();
			//	this.add(new BlackOutPanel());
				this.dispose();
				return;
			}
			if (computerPlayer.istrainerOutOfPokemon()) {
				player.setMoney(computerPlayer.giveMoney());
				this.dispose();
			}
			if (attPokemon.isPokemonDead()) {
				clearPanel();
				layoutSelectionPanel();
				return;
			}
			if (defPokemon.isPokemonDead()) {
				if (defPokemon.isPokemonDead()) {
					attPokemon.increaseEXP((int) Math.ceil((defPokemon
							.getLevel() * 50) / attPokemon.getLevel()));
				}
				defPokemon = computerPlayer.getStrategy().getAttacker();
			}
		}

		clearPanel();

		this.add(draw);
		this.add(moveButton);
		this.add(itemButton);
		this.add(pokeButton);

		drawingActive = true;

	}

	private void displayMoves() {
		clearPanel();
		setProperties();

		this.add(draw);

		this.add(move1);
		this.add(move2);
		this.add(move3);
		this.add(move4);

		drawingActive = true;

	}

	private void displayItems() {
		clearPanel();

		this.add(draw);

		this.add(potion);
		this.add(pokeball);

		drawingActive = true;

	}

	private void clearPanel() {
		this.getContentPane().removeAll();
		this.getRootPane().revalidate();
		this.getContentPane().repaint();
	}

	private class Drawings extends JPanel {

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g2 = (Graphics2D) g;

			if (drawingActive) {
				layoutBattle();
			}

		}

		public void layoutBattle() {

			Font ponitficus = new Font(Font.MONOSPACED, Font.PLAIN, 16);
			g2.setFont(ponitficus);

			g2.drawImage(
					spriteManager.getPokemonImage(defPokemon.getPOKEMON_ID()),
					500, 50, null);

			float defPokeHealth = (defPokemon.getCurrentHealth() / (float) defPokemon
					.getMaxHp());
			g2.draw(new Rectangle2D.Double(500, 300, 240, 20));
			g2.setColor(Color.RED);
			g2.fill(new Rectangle2D.Double(500, 300, 240 * defPokeHealth, 20));
			g2.setColor(Color.BLACK);
			g2.drawString(
					defPokemon.getCurrentHealth() + " / "
							+ defPokemon.getMaxHp(), 575, 315);

			g2.drawImage(
					spriteManager.getPokemonImage(attPokemon.getPOKEMON_ID()),
					100, 250, null);

			float attPokeHealth = (attPokemon.getCurrentHealth() / (float) attPokemon
					.getMaxHp());
			g2.draw(new Rectangle2D.Double(100, 500, 240, 20));
			g2.setColor(Color.RED);
			g2.fill(new Rectangle2D.Double(100, 500, 240 * attPokeHealth, 20));
			g2.setColor(Color.BLACK);
			g2.drawString(
					attPokemon.getCurrentHealth() + " / "
							+ attPokemon.getMaxHp(), 175, 515);
		}
	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent buttonSource) {
			if (buttonSource.getSource() == select) {
				int viewRow = playerTable.getSelectedRow();
				if (viewRow < 0 || viewRow >= player.getPokemon().size())
					JOptionPane.showMessageDialog(null,
							"You have not selected a pokemon");
				else {
					player.getStrategy().setPokemon(
							player.getPokemon().get(viewRow));
					attPokemon = player.getPokemon().get(viewRow);
					drawingActive = true;
					layoutBattlePanel();
				}
			}
			if (buttonSource.getSource() == pokeButton) {
				layoutSelectionPanel();
			}
			if (buttonSource.getSource() == moveButton) {
				displayMoves();
			}
			if (buttonSource.getSource() == itemButton) {
				displayItems();
			}
			if (buttonSource.getSource() == move1) {
				if (trainer == true) {
					trainerBattle(attPokemon.getMoveAt(0));
				}
				if (trainer == false) {
					battle.wildBattleGUI(player, attPokemon, defPokemon,
							attPokemon.getMoveAt(0));
				}
				layoutBattlePanel();
			}
			if (buttonSource.getSource() == move2) {
				if (trainer == true) {
					trainerBattle(attPokemon.getMoveAt(1));
				}
				if (trainer == false) {
					battle.wildBattleGUI(player, attPokemon, defPokemon,
							attPokemon.getMoveAt(1));
				}
				layoutBattlePanel();
			}
			if (buttonSource.getSource() == move3) {
				if (trainer == true) {
					trainerBattle(attPokemon.getMoveAt(2));
				}
				if (trainer == false) {
					battle.wildBattleGUI(player, attPokemon, defPokemon,
							attPokemon.getMoveAt(2));
				}
				layoutBattlePanel();
			}
			if (buttonSource.getSource() == move4) {
				if (trainer == true) {
					trainerBattle(attPokemon.getMoveAt(3));
				}
				if (trainer == false) {
					battle.wildBattleGUI(player, attPokemon, defPokemon,
							attPokemon.getMoveAt(3));
				}
				layoutBattlePanel();
			}
			if (buttonSource.getSource() == potion) {
				if (trainer == true) {
					for (Item s : player.getItems()) {
						if (s instanceof Potion) {
							trainerBattle(new Potion());
							player.removeItem(s);
							layoutBattlePanel();
							return;
						}
					}
					JOptionPane.showMessageDialog(null,
							"You do not have any Potions!");
					layoutBattlePanel();
				}
				if (trainer == false) {
					for (Item s : player.getItems()) {
						if (s instanceof Potion) {
							battle.wildBattleGUI(player, attPokemon,
									defPokemon, new Potion());
							player.removeItem(s);
							layoutBattlePanel();
							return;
						}
					}
					JOptionPane.showMessageDialog(null,
							"You do not have any Potions!");
					layoutBattlePanel();

				}

			}
			if (buttonSource.getSource() == pokeball) {
				if (trainer == true) {
					JOptionPane.showMessageDialog(null,
							"Cannot use Pokeball in Trainer Battle.");
					layoutBattlePanel();

				}
				if (trainer == false) {
					for (Item s : player.getItems()) {
						if (s instanceof Pokeball) {
							battle.wildBattleGUI(player, attPokemon,
									defPokemon, new Pokeball());
							player.removeItem(s);
							layoutBattlePanel();
							return;
						}
					}

					JOptionPane.showMessageDialog(null,
							"You do not have any Pokeballs Left.");
					layoutBattlePanel();
				}

			}

		}

	}

	private void trainerBattle(IMove move) {
		if (player.istrainerOutOfPokemon()) {
			clearPanel();
		//	this.add(new BlackOutPanel());
			this.dispose();
			return;
		}
		if (computerPlayer.istrainerOutOfPokemon()) {
			this.dispose();
		}
		if (attPokemon.isPokemonDead()) {
			clearPanel();
			layoutSelectionPanel();
			return;
		}
		if (defPokemon.isPokemonDead()) {
			if (defPokemon.isPokemonDead()) {
				attPokemon
						.increaseEXP((int) Math.ceil((defPokemon.getLevel() * 50)
								/ attPokemon.getLevel()));
			}
			defPokemon = computerPlayer.getStrategy().getAttacker();
			clearPanel();
		}
		battle.trainerBattle(player, computerPlayer, attPokemon, defPokemon,
				move);
	}

	public boolean checkDefPokemonDead() {
		return defPokemon.isPokemonDead();
	}

}
