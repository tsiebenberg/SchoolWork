package battle;

import java.util.ArrayList;
import java.util.Random;

import playerHierarchy.Player;
import pokemonHierarchy.IMove;
import pokemonHierarchy.Move;
import pokemonHierarchy.Pokemon;

public class RandomStrategy implements IStrategy {

	Player trainer;
	Pokemon currentPokemon;
	ArrayList<Pokemon> pokemonList;
	boolean throwPoke;

	@Override
	public void setPlayer(Player trainer) {
		// TODO Auto-generated method stub
		this.trainer = trainer;

	}

	@Override
	public ArrayList<Pokemon> getTrainerPokemon() {
		// TODO Auto-generated method stub
		this.pokemonList = trainer.getPokemon();
		return pokemonList;
	}

	@Override
	public Pokemon getAttacker() {
		// TODO Auto-generated method stub
		for (int i = 0; i < trainer.getPokemon().size(); i++) {
			if (trainer.getPokemon().get(i).isPokemonDead() == false) {
				currentPokemon = trainer.getPokemon().get(i);
				break;
			}
		}
		return currentPokemon;
	}

	@Override
	public Move pickMove() {
		// TODO Auto-generated method stub
		Random generator = new Random();
		return currentPokemon.getMoveAt(generator.nextInt(4));

	}

	@Override
	public void setDefender(Pokemon p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setThrowPokeball(boolean poke) {
		// TODO Auto-generated method stub
		throwPoke = false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPokemon(Pokemon selected) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pickMoveGUI(IMove select) {
		
		// TODO Auto-generated method stub

	}

}
