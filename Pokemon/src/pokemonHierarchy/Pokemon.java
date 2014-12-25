package pokemonHierarchy;

import java.util.ArrayList;

import model.DatabaseReader;

public class Pokemon implements IPokemon {
	
	private String name;
	private int level;
	private ElementType ELEMENT_TYPE;
	private int POKEMON_ID;
	private int BASE_HP, BASE_ATTACK, BASE_DEFENSE, BASE_SPEED, EVOLVES_AT,
			MAX_HP, CURR_ATTACK, CURR_DEFENSE, CURR_SPEED;
	private ArrayList<Move> moves;
	private int Damage;
	
	private int EXP_To_Level;
	private int curr_EXP;

	public Pokemon(String name, int id, int hp, int at, int df, int sp, int ev,
			ElementType type, ArrayList<Move> moves) {
		this.name = name;
		this.POKEMON_ID = id;
		this.BASE_HP = hp;
		this.BASE_ATTACK = at;
		this.BASE_DEFENSE = df;
		this.BASE_SPEED = sp;
		this.EVOLVES_AT = ev;
		this.ELEMENT_TYPE = type;
		this.Damage = 0;
		this.moves = moves;
		this.curr_EXP = 0;
		this.EXP_To_Level = 10;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public ElementType getELEMENT_TYPE() {
		return ELEMENT_TYPE;
	}

	@Override
	public int getPOKEMON_ID() {
		return POKEMON_ID;
	}

	@Override
	public int getBASE_HP() {
		return BASE_HP;
	}

	@Override
	public int getBASE_ATTACK() {
		return BASE_ATTACK;
	}

	@Override
	public int getBASE_DEFENSE() {
		return BASE_DEFENSE;
	}

	@Override
	public int getBASE_SPEED() {
		return BASE_SPEED;
	}

	@Override
	public int getEVOLVES_AT() {
		return EVOLVES_AT;
	}

	@Override
	public ArrayList<Move> getMoves() {
		return moves;
	}
	
	public Move getMoveAt(int moveIndex){
		return moves.get(moveIndex);
	}

	public int getCurrentAttack() {
		return this.CURR_ATTACK;
	}

	public int getCurrentDefense() {
		return this.CURR_DEFENSE;
	}

	public int getMaxHp() {
		return this.MAX_HP;
	}

	public int getCurrentSpeed() {
		return this.CURR_SPEED;
	}

	public String toString() {
		return "\nID:" + POKEMON_ID + "\nName:" + name + "\nBase HP:" + BASE_HP
				+ "\nBase Attack:" + BASE_ATTACK + "\nBase Defense:"
				+ BASE_DEFENSE + "\nBase Speed:" + BASE_SPEED + "\nType 1:"
				+ ELEMENT_TYPE + "\nLevel:" + level + "\nEvolves at level:"
				+ EVOLVES_AT;
	}

	public void levelUp() {
		level++;
		if (level == EVOLVES_AT)
			evolve();
		else
			levelUpStats();
	}

	private void levelUpStats() {
		this.MAX_HP = this.MAX_HP + ((2 * this.BASE_HP) * this.level / 100)
				+ 10 + this.level;
		this.CURR_ATTACK = this.CURR_ATTACK
				+ ((2 * this.BASE_ATTACK) * this.level / 100) + 10 + this.level;
		this.CURR_DEFENSE = this.CURR_DEFENSE
				+ ((2 * this.BASE_DEFENSE) * this.level / 100) + 10
				+ this.level;
		this.CURR_SPEED = this.CURR_SPEED
				+ ((2 * this.BASE_SPEED) * this.level / 100) + 10 + this.level;
		this.EXP_To_Level = (int) ((this.EXP_To_Level*level)/Math.ceil(level/2.0));
	}

	private void evolve() {
		// TODO Need to add functionality
		DatabaseReader db = new DatabaseReader();
		Pokemon temp = db.getPokemonByID(this.POKEMON_ID + 1, this.level);
		this.POKEMON_ID = this.POKEMON_ID + 1;
		this.BASE_HP = temp.getBASE_HP();
		this.BASE_ATTACK = temp.getBASE_ATTACK();
		this.BASE_SPEED = temp.getBASE_SPEED();
		this.BASE_DEFENSE = temp.getBASE_DEFENSE();
		this.MAX_HP = temp.getMaxHp();
		this.CURR_SPEED = temp.getCurrentSpeed();
		this.CURR_ATTACK = temp.getCurrentAttack();
		this.CURR_DEFENSE = temp.getCurrentDefense();
	}

	public void setLevel(int level) {
		
		this.level = level;
		this.MAX_HP = this.BASE_HP + ((2 * this.BASE_HP) * this.level / 100)
				+ 10 + this.level;
		this.CURR_ATTACK = this.BASE_ATTACK
				+ ((2 * this.BASE_ATTACK) * this.level / 100) + 10 + this.level;
		this.CURR_DEFENSE = this.BASE_DEFENSE
				+ ((2 * this.BASE_DEFENSE) * this.level / 100) + 10
				+ this.level;
		this.CURR_SPEED = this.BASE_SPEED
				+ ((2 * this.BASE_SPEED) * this.level / 100) + 10 + this.level;
		this.EXP_To_Level = (this.EXP_To_Level*level)/2;
	}
	
	public int getCurrentHealth() {
		return MAX_HP - Damage;
	}

	public int getDamage() {
		return this.Damage;
	}

	public void setDamage(int k) {
		this.Damage = k;
	}

	@Override
	public boolean isPokemonDead() {
		// TODO Auto-generated method stub
		return this.Damage>=this.MAX_HP;
	}
	
	public int getEXP(){
		return this.curr_EXP;
	}
	
	public void increaseEXP(int inc){
		this.curr_EXP= this.curr_EXP+inc;
		if(curr_EXP>=this.EXP_To_Level){
			levelUp();
			System.out.printf("%s is now %d\n", this.name, this.level);
		}
		this.curr_EXP=0;
	}

}
