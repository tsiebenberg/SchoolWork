package itemHierarchy;

import pokemonHierarchy.ElementType;
import pokemonHierarchy.IMove;
import pokemonHierarchy.Pokemon;

public class Potion extends Item implements IMove{
	
	public Potion() {
		price = 200;
	}
	
	public boolean heal(Pokemon x) {
		if (x.getDamage() == 0) {
			return false;
		}
		if (x.getDamage() < 20) {
			x.setDamage(0);
			return true;
		} else {
			x.setDamage(x.getDamage() - 20);
			return true;
		}
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
		return "Potion";
	}
}
