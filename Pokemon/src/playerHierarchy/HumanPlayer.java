package playerHierarchy;

import itemHierarchy.Item;

import java.awt.Image;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import mapHierarchy.Zone;
import pokemonHierarchy.Pokemon;
import battle.HumanStrategy;
import battle.IStrategy;

public class HumanPlayer extends Player implements TableModel {

	public static final int itemMax = 10;
	private Zone zone;
	private IStrategy strategy;
	private Image image;

	public HumanPlayer(int x, int y) {
		super(x, y);
		// image = spriteManager.
		zone = null;
		money = 2000;
		strategy = new HumanStrategy();
		strategy.setPlayer(this);
	}

	public int giveMoney() {
		int moneyToGive = money / 4;
		money = money - moneyToGive;
		return moneyToGive;
	}

	public void movePlayer(int x, int y) {
		xPosition = x;
		yPosition = y;
	}

	public int getItemMax() {
		return itemMax;
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

	public Zone getCurrentZone() {
		return zone;
	}

	public void setCurrentZone(Zone aZone) {
		zone = aZone;
	}

	public boolean canFight() {
		int counter = 0;
		for (int i = 0; i < pokemonList.size(); i++) {
			if (pokemonList.get(i).isPokemonDead() != false) {
				counter++;
			}
		}
		return counter != pokemonList.size();
	}

	@Override
	public String toString() {
		return "H";
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

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?> getColumnClass(int index) {
		if (index == 0 || index == 2)
			return String.class;
		else
			return Integer.class;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int index) {
		if (index == 0)
			return "Pokemon";
		else if (index == 2)
			return "Level";
		else
			return "Type";
	}

	@Override
	public int getRowCount() {
		return pokemonList.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Pokemon temp = pokemonList.get(row);
		if (col == 0)
			return temp.getName();
		else if (col == 1)
			return temp.getLevel();
		else
			return temp.getELEMENT_TYPE().toString();
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {

	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {

	}

	public Image getImage() {
		return null;
	}
}
