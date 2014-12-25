package test;

import javax.swing.JFrame;

import model.DatabaseReader;
import playerHierarchy.HumanPlayer;
import pokemonHierarchy.Pokemon;
import animations.BattleAnimation;

public class TestBattleAnimation extends JFrame {

	private static final long serialVersionUID = 1L;
	HumanPlayer player;
	Pokemon pokemon;

	public static void main(String[] args) {
		new TestBattleAnimation().setVisible(true);
	}

	public TestBattleAnimation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(820, 800);
		this.setLocationRelativeTo(null);
		this.setResizable(true);

		player = new HumanPlayer(1, 1);
		player.addPokemon(new DatabaseReader().getPokemonByID(1, 5));
		player.addPokemon(new DatabaseReader().getPokemonByID(4, 5));
		player.addPokemon(new DatabaseReader().getPokemonByID(7, 5));

		pokemon = new DatabaseReader().getPokemonByID(15, 5);
		setSize(820, 800);

		//this.setContentPane(new BattleAnimation(player, pokemon));
	}
}
