package mapHierarchy;

import java.util.ArrayList;

import playerHierarchy.ComputerPlayer;
import playerHierarchy.Player;
import pokemonHierarchy.Pokemon;
import elements.Grass;
import elements.Water;

public class Square {
	private ArrayList<Object> objects;
	private boolean isWalkable;

	public Square() {
		objects = new ArrayList<Object>();
		isWalkable = true;
	}

	public void addObject(Object p) {
		objects.add(p);
	}

	public void removeObject(Object obj) {
		objects.remove(obj);
	}
	
	public ComputerPlayer getTrainer(){
		for(Object s: objects){
			if(s instanceof Player)
				return (ComputerPlayer) s;
		}
		return null;
	}

	public Object getRecentObject() {
		for(Object s: objects){
			if(s instanceof Player)
				return s;
		}
		if(objects.size()>2){
			return objects.get(1);
		}
		return objects.get(objects.size()-1);
	}
	
	public ArrayList<Object> getObjects(){
		return this.objects;
	}
	
	public void removePokemon(){
		for(int i =0; i<objects.size(); i++){
			if(objects.get(i) instanceof Pokemon){
				objects.remove(i);
			}
		}
	}
	
	public void addPokemon(Pokemon p){
		removePokemon();
		objects.add(p);
	}
	
	public boolean containsWaterOrGrass(){
		for(Object s: objects){
			if(s instanceof Grass || s instanceof Water){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasGrass(){
		for(Object s: objects){
			if(s instanceof Grass){
				return true;
			}
		}
		return false;
	}

	public void setIsWalkable(boolean aValue) {
		isWalkable = aValue;
	}

	public boolean IsWalkable() {
		return isWalkable;
	}

}
