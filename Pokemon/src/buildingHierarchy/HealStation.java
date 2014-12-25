package buildingHierarchy;

import playerHierarchy.Player;

public class HealStation {
	
	public void healPokemon(Player player) {
		for(int i = 0; i < player.getPokemon().size(); i++) {
			player.getPokemon().get(i).setDamage(0);
		}
	}
	
	@Override
	public String toString(){
		return "P";
	}

}
