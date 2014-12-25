package playerHierarchy;

import itemHierarchy.Item;

import java.awt.Image;

public class Trainer extends ComputerPlayer {

	public static final int itemMax = 1;
	private Image image;

	public Trainer(int x, int y, Direction dir) {
		super(x, y, dir);
		money = 750;
		money = money - moneyGenerator.nextInt(300);
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
		return "T";
	}
}
