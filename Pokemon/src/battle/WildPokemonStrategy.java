package battle;


import java.util.Random;

import pokemonHierarchy.IMove;
import pokemonHierarchy.Pokemon;

public class WildPokemonStrategy {
	
	Pokemon wildPokemon;


	public WildPokemonStrategy(Pokemon wildPokemon) {
		// TODO Auto-generated method stub
		this.wildPokemon = wildPokemon;
		
	}



	public IMove pickMove() {
		// TODO Auto-generated method stub
		Random generator = new Random();
		return wildPokemon.getMoveAt(generator.nextInt(4));
		
	}




}
