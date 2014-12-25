package animations;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import model.PokemonModel;
import playerHierarchy.HumanPlayer;
import buildingHierarchy.PC;

public class PCPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel panelName;
	private HumanPlayer player = new HumanPlayer(1, 1);
	private PC pc;
	private JButton storePokemon;
	private JButton withdrawPokemon;
	private TableModel pcModel;
	private JTable pcTable;
	private TableModel playerModel;
	private JTable playerTable;
	private JLabel playerPokemon;
	private PokemonModel model;

	public PCPanel(PokemonModel model) {
		this.model = model;
		this.player = model.getPlayer();
		initializeVariables();
		layoutThePanel();
		registerListeners();
	}

	private void initializeVariables() {
		pc = new PC();
		panelName = new JLabel("PC Pokemon");
		storePokemon = new JButton("Store Pokemon");
		withdrawPokemon = new JButton("Withdraw Pokemon");
		playerPokemon = new JLabel("Player's Pokemon");
	}

	private void layoutThePanel() {

		setLayout(new GridLayout(4, 2, 20, 20));
		panelName.setPreferredSize(new Dimension(200, 50));
		this.setSize(820, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		pcModel = pc;
		pcTable = new JTable(pcModel);
		JScrollPane pcScrollPane = new JScrollPane(pcTable);

		playerModel = player;
		playerTable = new JTable(playerModel);
		JScrollPane playerScrollPane = new JScrollPane(playerTable);
				
		this.add(panelName);			// 0, 0
		this.add(new JPanel());			// 0, 1
		this.add(pcScrollPane);			// 1, 0
		this.add(withdrawPokemon);		// 1, 1
		this.add(playerPokemon);		// 2, 0
		this.add(new JPanel());			// 2, 1
		this.add(playerScrollPane);   	// 3, 0 	
		this.add(storePokemon);			// 3, 1
		
	}

	private void registerListeners() {
		storePokemon.addActionListener(new StorePokemon());
		withdrawPokemon.addActionListener(new WithdrawPokemon());
	}

	private class StorePokemon implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			int viewRow = playerTable.getSelectedRow();

			if (viewRow < 0 || viewRow >= player.getPokemon().size())
				JOptionPane.showMessageDialog(null, "You have not selected a pokemon or have selected a pokemon in the PC");
			else if (player.getPokemon().size() == 0) {
				JOptionPane.showMessageDialog(null, "You have no pokemon to store");
			} else {
				pc.storePokemon(player, player.getPokemon().get(viewRow));
				playerTable.revalidate();
				playerTable.repaint();
				pcTable.revalidate();
				pcTable.repaint();
			}
		}
	}

	private class WithdrawPokemon implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			int viewRow = pcTable.getSelectedRow();

			if (viewRow < 0 || viewRow >= pc.getPokemon().size()) {
				JOptionPane.showMessageDialog(null, "You have not selected a pokemon or have selected a pokemon form your inventory");
			} else if (pc.getPokemon().size() == 0) {
				JOptionPane.showMessageDialog(null, "The PC is empty");
			} else if (player.getPokemon().size() == 6) {
				JOptionPane.showMessageDialog(null, "You are full on pokemon");
			} else {
				pc.withdrawPokemon(player, pc.getPokemon().get(viewRow));
				playerTable.revalidate();
				playerTable.repaint();
				pcTable.revalidate();
				pcTable.repaint();
			}
		}
	}

}
