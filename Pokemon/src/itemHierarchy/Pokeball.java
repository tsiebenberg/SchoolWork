package itemHierarchy;

import java.util.Random;

import pokemonHierarchy.ElementType;
import pokemonHierarchy.IMove;
import pokemonHierarchy.Pokemon;

public class Pokeball extends Item implements IMove {
	
	public Pokeball() {
		price = 200;
	}
	
	public boolean catchPokemon(Pokemon x) {
		Random generator = new Random();
		float percent = generator.nextInt(100);
		System.out.println(percent);
		if (percent >= 50) {
			return true;
		} else
			return false;
	}

	@Override
	public int getMOVE_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBASE_DAMAGE() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getMOVE_NAME() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ElementType getELEMENT_TYPE() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "Pokeball";
	}
}
