package pokemonHierarchy;

import java.util.ArrayList;

public interface IPokemon {

	public abstract String getName();

	public abstract int getLevel();

	public abstract ElementType getELEMENT_TYPE();

	public abstract int getPOKEMON_ID();

	public abstract int getBASE_HP();

	public abstract int getBASE_ATTACK();

	public abstract int getBASE_DEFENSE();

	public abstract int getBASE_SPEED();

	public abstract int getEVOLVES_AT();

	public abstract ArrayList<Move> getMoves();
	
	public abstract boolean isPokemonDead();

}