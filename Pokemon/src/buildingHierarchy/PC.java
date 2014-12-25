package buildingHierarchy;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.DatabaseReader;
import playerHierarchy.Player;
import pokemonHierarchy.Pokemon;

public class PC implements TableModel {

	private ArrayList<Pokemon> pokeList;

	public PC() {
		pokeList = new ArrayList<Pokemon>();
		//////////////////////////////////////////////////
//		pokeList.add(new DatabaseReader().getPokemonByID(13, 5));
//		pokeList.add(new DatabaseReader().getPokemonByID(14, 6));
//		pokeList.add(new DatabaseReader().getPokemonByID(15, 7));
//		pokeList.add(new DatabaseReader().getPokemonByID(16, 8));
//		pokeList.add(new DatabaseReader().getPokemonByID(17, 9));
//		pokeList.add(new DatabaseReader().getPokemonByID(18, 10));
//		pokeList.add(new DatabaseReader().getPokemonByID(7, 11));
//		pokeList.add(new DatabaseReader().getPokemonByID(8, 12));
//		pokeList.add(new DatabaseReader().getPokemonByID(9, 13));
//		pokeList.add(new DatabaseReader().getPokemonByID(10, 14));
//		pokeList.add(new DatabaseReader().getPokemonByID(11, 15));
//		pokeList.add(new DatabaseReader().getPokemonByID(12, 16));
		///////////////////////////////////////////////////
	}
	
	public ArrayList<Pokemon> getPokemon() {
		return pokeList;
	}

	public boolean storePokemon(Player player, Pokemon aPokemon) {
		for(int i = 0; i < player.getPokemon().size(); i++) {
			if(player.getPokemon().get(i).getPOKEMON_ID() == aPokemon.getPOKEMON_ID()) {
				player.removePokemon(aPokemon);
				pokeList.add(aPokemon);
				return true;
			}
		}
		return false;
	}
	
	public boolean withdrawPokemon(Player player, Pokemon aPokemon) {
		for(int i = 0; i < pokeList.size(); i++) {
			if( pokeList.get(i).getPOKEMON_ID() == aPokemon.getPOKEMON_ID() && player.addPokemon(aPokemon)) {
				pokeList.remove(i);
				//player.addPokemon(aPokemon);
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return "C";
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getColumnClass(int index) {
		if( index == 0 || index == 2)
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
		if( index == 0)
			return "Pokemon";
		else if( index == 2)
			return "Level";	
		else
			return "Type";
	}

	@Override
	public int getRowCount() {
		return pokeList.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Pokemon temp = pokeList.get(row);
		if(col == 0) 
			return temp.getName();
		else if(col == 1)
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


}
