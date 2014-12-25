package animations;

import itemHierarchy.Item;
import itemHierarchy.Pokeball;
import itemHierarchy.Potion;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import playerHierarchy.HumanPlayer;
import buildingHierarchy.Store;

public class StorePanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int numberOfItems = 2;
	private JLabel panelName;
	private JLabel playerMoney;
	private HumanPlayer player;
	private Store store;
	private JButton buyPotion;
	private JButton sellPotion;
	private JButton buyPokeBall;
	private JButton sellPokeBall;

	public StorePanel(HumanPlayer player) {
		this.player = player;
		initializeVariables();
		layoutThePanel();
		registerListeners();

	}

	private void initializeVariables() {
		store = new Store();
		panelName = new JLabel("Store");
		buyPotion = new JButton("Buy Potion (Cost: $" + new Potion().getPrice()
				+ ")");
		sellPotion = new JButton("Sell Potion (Price: $"
				+ new Potion().sellPrice() + ")");
		buyPokeBall = new JButton("Buy PokeBall (Cost: $"
				+ new Pokeball().getPrice() + ")");
		sellPokeBall = new JButton("Sell PokeBall (Price: $"
				+ new Pokeball().sellPrice() + ")");
		playerMoney = new JLabel("Player Money: $" + player.getMoney());
	}

	private void layoutThePanel() {
		setLayout(new GridLayout(numberOfItems + 2, 2, 20, 20));

		panelName.setPreferredSize(new Dimension(200, 50));
		this.setSize(820, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//this.setOpacity(false);
		this.add(panelName); // Must add first
		this.add(new JPanel()); // Blank panel
		this.add(buyPotion);
		this.add(sellPotion);
		this.add(buyPokeBall);
		this.add(sellPokeBall);
		this.add(playerMoney);

	}

	private void registerListeners() {
		buyPotion.addActionListener(new BuyItem());
		buyPokeBall.addActionListener(new BuyItem());
		sellPotion.addActionListener(new SellItem());
		sellPokeBall.addActionListener(new SellItem());

	}

	private class BuyItem implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			JButton source = (JButton) arg0.getSource();
			Item item;
			if (source.equals(buyPotion)) {
				item = new Potion();
				if (store.buyItem(player, item)) {
					playerMoney.setText("Player Money: $" + player.getMoney());
				} else {
					if (player.getMoney() < item.getPrice()) {
						JOptionPane.showMessageDialog(null,
								"You do not have enough money");
					} else {
						JOptionPane.showMessageDialog(null,
								"You are maxed out on items");
					}
				}
			}
			if (source.equals(buyPokeBall)) {
				item = new Pokeball();
				if (store.buyItem(player, item)) {
					playerMoney.setText("Player Money: $" + player.getMoney());
				} else {
					if (player.getMoney() < item.getPrice()) {
						JOptionPane.showMessageDialog(null,
								"You do not have enough money");
					} else {
						JOptionPane.showMessageDialog(null,
								"You are maxed out on items");
					}
				}
			}

		}

	}

	private class SellItem implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			Item item;
			if (source.equals(sellPotion)) {
				item = new Potion();
				if (store.sellItem(player, item)) {
					playerMoney.setText("Player Money: $" + player.getMoney());
				} else {
					JOptionPane.showMessageDialog(null,
							"You do not own that item");
				}

			}
			if (source.equals(sellPokeBall)) {
				item = new Pokeball();
				if (store.sellItem(player, item)) {
					playerMoney.setText("Player Money: $" + player.getMoney());
				} else {
					JOptionPane.showMessageDialog(null,
							"You do not own that item");
				}
			}
		}

	}

}
