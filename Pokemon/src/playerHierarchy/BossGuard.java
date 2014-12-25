package playerHierarchy;

import itemHierarchy.Item;

public class BossGuard extends ComputerPlayer {

	public static final int itemMax = 2;

	public BossGuard(int x, int y, Direction dir) {
		super(x, y, dir);
		money = 1000;
		money = money - moneyGenerator.nextInt(750);
	}

	@Override
	public boolean addItem(Item anItem) {
		if (itemList.size() != itemMax) {
			itemList.add(anItem);
			return true;
		}
		return false;
	}

	@Override
	public void battle() {

	}

	@Override
	public String toString() {
		return "G";
	}
	
	

}
