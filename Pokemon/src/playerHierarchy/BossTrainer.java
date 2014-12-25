package playerHierarchy;

import itemHierarchy.Item;
import battle.BossStrategy;
import battle.IStrategy;

public class BossTrainer extends ComputerPlayer {

	public static final int itemMax = 3;
	private IStrategy strategy;
	

	public BossTrainer(int x, int y, Direction dir) {
		super(x, y, dir);
		money = 2000;
		money = money - moneyGenerator.nextInt(1000);
		this.strategy = new BossStrategy();
		this.strategy.setPlayer(this);
	}
	
	@Override
	public void setRange() {
		if( direction == Direction.LEFT)
			range = yPosition - 1;
		else if( direction == Direction.RIGHT)
			range = yPosition + 1;
		else if(direction == Direction.UP )
			range = xPosition - 1;
		else 
			range = xPosition + 1;
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
		return "B";
	}
	
	@Override
	public boolean isBoss() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public IStrategy getStrategy() {
		// TODO Auto-generated method stub
		return strategy;
	}
	

}
