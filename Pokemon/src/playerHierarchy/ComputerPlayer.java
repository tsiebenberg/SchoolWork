package playerHierarchy;

import itemHierarchy.Item;

import java.awt.Image;
import java.util.Random;

import battle.IStrategy;
import battle.RandomStrategy;

public class ComputerPlayer extends Player {

	protected Direction direction;
	protected int range;
	private IStrategy strategy;
	Random moneyGenerator;
	private Image image;

	public ComputerPlayer(int x, int y, Direction dir) {
		super(x, y);
		direction = dir;
		moneyGenerator = new Random();
		setRange();
		strategy = new RandomStrategy();
		strategy.setPlayer(this);
	}
	
	public int giveMoney() {
		int moneyToGive = money;
		money = 0;
		return moneyToGive;
	}

	protected void setRange() {
		if( direction == Direction.LEFT)
			range = yPosition - 3;
		else if( direction == Direction.RIGHT)
			range = yPosition + 3;
		else if(direction == Direction.UP )
			range = xPosition - 3;
		else 
			range = xPosition + 3;
	}
	
	public boolean hasBattled() {
		for(int i = 0; i < pokemonList.size(); i++) {
			if(pokemonList.get(i).isPokemonDead()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addItem(Item anItem) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int getRange() {
		return range;
	}
	
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void battle() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public IStrategy getStrategy() {
		// TODO Auto-generated method stub
		return strategy;
	}

	@Override
	public boolean isBoss() {
		// TODO Auto-generated method stub
		return false;
	}
	


}
