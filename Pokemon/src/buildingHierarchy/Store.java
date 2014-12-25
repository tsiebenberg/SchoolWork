package buildingHierarchy;

import itemHierarchy.Item;
import playerHierarchy.HumanPlayer;
import playerHierarchy.Player;

public class Store {

	public boolean buyItem(Player player, Item anItem) {
		if( player.getMoney() >= anItem.getPrice() && ((HumanPlayer) player).getItemMax() > player.getItems().size() ) {
			player.setMoney(anItem.getPrice() * -1);
			player.addItem(anItem);
			return true;
		}
		return false;
	}
	
	public boolean sellItem(Player player, Item anItem) {
		for(int i = 0; i < player.getItems().size(); i++) {
			if( player.getItems().get(i).getClass().equals(anItem.getClass())) {
				player.removeItem(anItem);
				player.setMoney(anItem.getPrice() / 4);
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "S";
	}
}
