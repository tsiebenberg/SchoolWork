package battle;

import java.util.ArrayList;

import playerHierarchy.Player;
import pokemonHierarchy.IMove;
import pokemonHierarchy.Pokemon;

public interface IStrategy {

	public void setPlayer(Player trainer);

	public ArrayList<Pokemon> getTrainerPokemon();

	public Pokemon getAttacker();

	public void setDefender(Pokemon p);
	
	public void setPokemon(Pokemon selected);
	
	public IMove pickMove();
	
	public void setThrowPokeball(boolean poke);
	
	public void reset();

	public void pickMoveGUI(IMove select);


}
