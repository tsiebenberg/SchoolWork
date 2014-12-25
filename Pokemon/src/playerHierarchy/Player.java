package playerHierarchy;

import itemHierarchy.Item;

import java.util.ArrayList;

import pokemonHierarchy.IPokemon;
import pokemonHierarchy.Pokemon;
import battle.IStrategy;

public abstract class Player {

	protected ArrayList<Item> itemList;
	protected ArrayList<Pokemon> pokemonList;
	protected static final int pokemonMax = 6;
	protected int xPosition, yPosition;
	protected boolean trainerOutOfPokemon;
	protected int money;
	boolean battled = true;
	

	public Player(int x, int y) {
		xPosition = x;
		yPosition = y;
		pokemonList = new ArrayList<Pokemon>();
		itemList = new ArrayList<Item>();
		trainerOutOfPokemon = false;
		
	}
	
	public int getMoney() {
		return money;
	}
	
	public void setMoney(int someMoney) {
		money = money + someMoney;
	}
	
	public abstract int giveMoney();
	
	public ArrayList<Item> getItems() {
		return itemList;
	}
	
	public void killBoss() {
		for(int i = 0; i < pokemonList.size(); i++) {
			pokemonList.get(i).setDamage(100000);
		}
	}
	
	public boolean istrainerOutOfPokemon(){
		for(int i = 0; i < pokemonList.size(); i++){
			if(pokemonList.get(i).isPokemonDead() == false){
				return false;
			}
		}
		return true;
	}

	public boolean addPokemon(Pokemon aPokemon) {
		if (pokemonList.size() < pokemonMax) {
			pokemonList.add(aPokemon);
			return true;
		}else {
			
		}
		return false;
	}
	
	public void removeItem(Item anItem) {
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getClass().equals(anItem.getClass())) {
				itemList.remove(i);
				return;
			}
		}
	}
	
	public ArrayList<Pokemon> getPokemon(){
		return this.pokemonList;
	}

	public boolean removePokemon(IPokemon aPokemon) {
		for (int i = 0; i < pokemonList.size(); i++) {
			if (pokemonList.get(i).equals(aPokemon)) {
				pokemonList.remove(aPokemon);
				return true;
			}
		}
		return false;
	}

	public int getXPosition() {
		return xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}
	
	@Override
	public abstract String toString();

	public abstract boolean addItem(Item anItem);

	public abstract void battle();
	
	public abstract IStrategy getStrategy();
	
	public abstract boolean isBoss();
	
//	public abstract Image getImage();
	
	public void toggleBattle(){
		battled = !battled;
	}
	
	public boolean getBattle(){
		return battled;
	}

}
