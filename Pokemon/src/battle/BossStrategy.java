package battle;

import itemHierarchy.Potion;

import java.util.ArrayList;

import playerHierarchy.Player;
import pokemonHierarchy.ElementType;
import pokemonHierarchy.IMove;
import pokemonHierarchy.Move;
import pokemonHierarchy.Pokemon;

public class BossStrategy implements IStrategy {
	Player trainer;
	Pokemon att;
	boolean throwPoke;
	Pokemon def;
	boolean potion;

	@Override
	public void setPlayer(Player trainer) {
		// TODO Auto-generated method stub
		potion = false;
		this.trainer = trainer;
	}

	@Override
	public ArrayList<Pokemon> getTrainerPokemon() {
		// TODO Auto-generated method stub
		return trainer.getPokemon();
	}

	@Override
	public Pokemon getAttacker() {
		// TODO Auto-generated method stub
		for(int i=0; i<trainer.getPokemon().size(); i++){
			if(trainer.getPokemon().get(i).isPokemonDead()==false){
				att = trainer.getPokemon().get(i);
				break;
			}
		}
		return att;
	}


	@Override
	public IMove pickMove() {
		// TODO Auto-generated method stub
		if(((att.getMaxHp()-att.getDamage())/att.getMaxHp())<=0.4){
			if(potion==false){
				potion = true;
				return new Potion();
			}
		}
		Move best=att.getMoveAt(0);
		for(int i=0; i<4; i++){
			if(battle(att, def, att.getMoveAt(i), null)>battle(att, def, best, null)){
				best = att.getMoveAt(i);
			}
		}
		return best;
	}

	@Override
	public void setDefender(Pokemon p) {
		// TODO Auto-generated method stub
		def = p;
	}

	@Override
	public void setThrowPokeball(boolean poke) {
		// TODO Auto-generated method stub
		throwPoke = false;
	}
	
	private int battle(Pokemon att, Pokemon def, IMove type, Player t) {
		double eff = moveEffective(def.getELEMENT_TYPE(),
				type.getELEMENT_TYPE());
		float damage = (float) (((((2 * att.getLevel() / 5 + 2)
				* att.getCurrentAttack() * type.getBASE_DAMAGE() / def
				.getCurrentDefense()) / 50) + 2) * eff);
		int roundeddamage = Math.round(damage);
		return roundeddamage;
	}

	private double moveEffective(ElementType def, ElementType type) {
		if (type.equals(ElementType.Normal))
			return 1;
		if (type.equals(ElementType.Psychic))
			return 1.5;
		int diff = def.getNum() - type.getNum();
		switch (diff) {
		case (3):
			return 0.5;
		case (-3):
			return 1.5;
		case (-2):
			return 0.5;
		case (0):
			return 0.5;
		case (1):
			return 2;
		case (2):
			return 1.5;
		case (-1):
			return 0.25;
		default:
			return 1.0;
		}
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		potion =false;
	}

	@Override
	public void setPokemon(Pokemon selected) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pickMoveGUI(IMove select) {
		// TODO Auto-generated method stub
		
	}

}
