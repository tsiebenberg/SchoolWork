package itemHierarchy;

import pokemonHierarchy.Pokemon;

public class RareCandy extends Item {
	public boolean level(Pokemon x){
		if (x.getLevel()==99){
			return false;
		}
		else{
			x.levelUp();
			return true;
			
		}
	}

	@Override
	public String toString() {
		return "Rare Candy";
	}
}
