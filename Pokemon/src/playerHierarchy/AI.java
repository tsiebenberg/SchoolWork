package playerHierarchy;

import model.DatabaseReader;
import pokemonHierarchy.Pokemon;

public class AI extends ComputerPlayer {

	private HumanPlayer player;

	private boolean traded;

	public AI(int x, int y, Direction dir) {
		super(x, y, dir);
		traded = false;

	}

	public boolean getTraded() {
		return traded;
	}

	public void trade() {
		for (Pokemon x : this.player.getPokemon()) {
			if (x.getPOKEMON_ID() == 15) {
				this.player.removePokemon(x);
				this.player.addPokemon(new DatabaseReader().getPokemonByID(63,
						1));
				traded = true;
			}
		}

	}

	public void setHumanPlayer(HumanPlayer player) {
		this.player = player;
	}

	public boolean cantrade() {
		for (Pokemon x : this.player.getPokemon()) {
			if (x.getPOKEMON_ID() == 15) {
				return true;
			}
		}
		return false;

	}

	public String toString() {
		return "A";
	}

}
