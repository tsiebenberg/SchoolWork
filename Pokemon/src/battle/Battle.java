package battle;

import itemHierarchy.Item;
import itemHierarchy.Key;
import itemHierarchy.Pokeball;
import itemHierarchy.Potion;
import model.PokemonModel;
import playerHierarchy.HumanPlayer;
import playerHierarchy.Player;
import pokemonHierarchy.ElementType;
import pokemonHierarchy.IMove;
import pokemonHierarchy.Pokemon;

public class Battle {

	private boolean caught = false;
	private PokemonModel model;

	public Battle(PokemonModel model) {
		this.model = model;
	}

	public void wildBattleGUI(Player t1, Pokemon att, Pokemon wild, IMove move) {
		WildPokemonStrategy k = new WildPokemonStrategy(wild);
		if (att.getCurrentSpeed() < wild.getCurrentSpeed()) {
			battle(wild, att, k.pickMove(), t1);
			battle(att, wild, move, t1);
		} else {
			battle(att, wild, move, t1);
			battle(wild, att, k.pickMove(), t1);
		}
	}

	public void trainerBattle(Player t1, Player t2, Pokemon att, Pokemon def, IMove move) {
		t2.getStrategy().setDefender(def);
		if (att.getCurrentSpeed() < def.getCurrentSpeed()) {
			battle(def, att, t2.getStrategy().pickMove(), t1);
			battle(att, def, move, t1);
		} else {
			battle(att, def, move, t1);
			battle(def, att, t2.getStrategy().pickMove(), t1);
		}
	}

	public void pokemonBattle(Player t1, Pokemon p2) {
		Pokemon p1 = t1.getStrategy().getAttacker();
		t1.getStrategy().setThrowPokeball(true);
		WildPokemonStrategy k = new WildPokemonStrategy(p2);
		int p1Damage = (p1.getMaxHp() - p1.getDamage());
		int p2Damage = (p2.getMaxHp() - p2.getDamage());
		while (t1.istrainerOutOfPokemon() != true) {
			if (caught == true) {
				break;
			}
			if (p1.isPokemonDead() == true) {
				p1 = t1.getStrategy().getAttacker();
				t1.getStrategy().setDefender(p2);

			}
			if (p1.getCurrentSpeed() >= p2.getCurrentSpeed() && caught == false) {
				battle(p1, p2, t1.getStrategy().pickMove(), t1);
				p2Damage = (p2.getMaxHp() - p2.getDamage());
				System.out.println(p2.getName() + " HP: " + p2Damage);
				if (p2.isPokemonDead() == false && caught == false) {
					battle(p2, p1, k.pickMove(), null);
					p1Damage = (p1.getMaxHp() - p1.getDamage());
					System.out.println(p1.getName() + " HP: " + p1Damage);
				}
			} else if (p2.isPokemonDead() == false && caught == false) {
				battle(p2, p1, k.pickMove(), null);
				if (p1.isPokemonDead() == false && caught == false) {
					battle(p1, p2, t1.getStrategy().pickMove(), t1);
					p1Damage = (p1.getMaxHp() - p1.getDamage());
					System.out.println(p1.getName() + " HP: " + p1Damage);
					p2Damage = (p2.getMaxHp() - p2.getDamage());
					System.out.println(p2.getName() + " HP: " + p2Damage);
				}
			}
			if (p2.isPokemonDead() == true && caught == false) {
				if (t1 instanceof HumanPlayer) {
					System.out.println((int) Math.ceil((p2.getLevel() * 50) / p1.getLevel()));
					p1.increaseEXP((int) Math.ceil((p2.getLevel() * 50) / p1.getLevel()));
					break;
				}
			}
		}

		if (p2.isPokemonDead() == true) {
			System.out.println("You defeated: " + p2.getName());
		}
		if (caught == true) {
			System.out.println("You have caught: " + p2.getName());
		}

	}

	public void TrainerBattle(Player t1, Player t2) {

		t1.getStrategy().reset();
		t1.getStrategy().reset();
		Pokemon p1 = t1.getStrategy().getAttacker();
		Pokemon p2 = t2.getStrategy().getAttacker();
		t1.getStrategy().setThrowPokeball(false);
		t2.getStrategy().setThrowPokeball(false);
		t1.getStrategy().setDefender(p2);
		t2.getStrategy().setDefender(p1);
		int p1Damage = (p1.getMaxHp() - p1.getDamage());
		int p2Damage = (p2.getMaxHp() - p2.getDamage());
		System.out.println(p1.getName() + " HP: " + p1Damage + " Lv: " + p1.getLevel());
		System.out.println(p2.getName() + " HP: " + p2Damage + " Lv: " + p2.getLevel());

		while (t1.istrainerOutOfPokemon() != true && t2.istrainerOutOfPokemon() != true) {
			if (p1.isPokemonDead() == true || p2.isPokemonDead() == true) {
				p1 = t1.getStrategy().getAttacker();
				p2 = t2.getStrategy().getAttacker();
				t1.getStrategy().setDefender(p2);
				t2.getStrategy().setDefender(p1);
			}

			if (p1.getCurrentSpeed() >= p2.getCurrentSpeed()) {
				battle(p1, p2, t1.getStrategy().pickMove(), t1);
				p2Damage = (p2.getMaxHp() - p2.getDamage());
				System.out.println(p2.getName() + " HP: " + p2Damage);
				if (p2.isPokemonDead() == false) {
					battle(p2, p1, t2.getStrategy().pickMove(), t2);
					p1Damage = (p1.getMaxHp() - p1.getDamage());
					System.out.println(p1.getName() + " HP: " + p1Damage);
				}
			} else if (p2.isPokemonDead() == false) {
				battle(p2, p1, t2.getStrategy().pickMove(), t2);
				if (p1.isPokemonDead() == false) {
					battle(p1, p2, t1.getStrategy().pickMove(), t1);
					p1Damage = (p1.getMaxHp() - p1.getDamage());
					System.out.println(p1.getName() + " HP: " + p1Damage);
					p2Damage = (p2.getMaxHp() - p2.getDamage());
					System.out.println(p2.getName() + " HP: " + p2Damage);
				}
			}
			if (t1 instanceof HumanPlayer && p2.isPokemonDead() == true) {
				System.out.println((int) Math.ceil((p2.getLevel() * 50) / p1.getLevel()));
				p1.increaseEXP((int) Math.ceil((p2.getLevel() * 50) / p1.getLevel()));
			} else if (t2 instanceof HumanPlayer && p1.isPokemonDead() == true) {
				System.out.println((int) Math.ceil((p1.getLevel() * 50) / p2.getLevel()));
				p2.increaseEXP((int) Math.ceil((p1.getLevel() * 50) / p2.getLevel()));
			}

		}

		System.out.println();
		if (t1.istrainerOutOfPokemon() == true && t2.istrainerOutOfPokemon() == false) { // t2
																							// wins
			int giveMoney = t1.giveMoney();
			t2.setMoney(giveMoney);
			if (t1 instanceof HumanPlayer) {
				for (Item s : t2.getItems()) {
					if (s instanceof Key) {
						t1.addItem(new Key());
						System.out.println("You got the key to the Gym!");
					}
				}
				System.out.println("Ash lost $" + giveMoney + " to the trainer");
			}
			if (t2 instanceof HumanPlayer) {
				for (Item s : t1.getItems()) {
					if (s instanceof Key) {
						t2.addItem(new Key());
						System.out.println("You got the key to the Gym!");
					}
					System.out.println("Ash gained $" + giveMoney + " from the trainer");
				}
			}
		}
		if (t1.istrainerOutOfPokemon() == false && t2.istrainerOutOfPokemon() == true) { // t1
																							// wins
			int giveMoney = t2.giveMoney();
			t1.setMoney(giveMoney);
			if (t1 instanceof HumanPlayer) {
				for (Item s : t2.getItems()) {
					if (s instanceof Key) {
						t1.addItem(new Key());
						System.out.println("You got the key to the Gym!");
					}
				}
				System.out.println("Ash gained $" + giveMoney + " from the trainer");
			}
			if (t2 instanceof HumanPlayer) {
				for (Item s : t1.getItems()) {
					if (s instanceof Key) {
						t2.addItem(new Key());
						System.out.println("You got the key to the Gym!");
					}
				}
				System.out.println("Ash lost $" + giveMoney + " to the trainer");
			}
		}

	}

	public void battle(Pokemon att, Pokemon def, IMove type, Player t) {
		if (type instanceof Potion) {
			new Potion().heal(att);
			return;
		}
		if (type instanceof Pokeball) {
			boolean caught = new Pokeball().catchPokemon(def);
			if (caught == true) {
				if (t.getPokemon().size() == 6) {
					model.getPC().storePokemon(t, def);
				} else {
					t.addPokemon(def);
				}
				this.caught = true;
			}
			return;
		}
		double eff = moveEffective(def.getELEMENT_TYPE(), type.getELEMENT_TYPE());
		float damage = (float) (((((2 * att.getLevel() / 5 + 2) * att.getCurrentAttack() * type.getBASE_DAMAGE() / def.getCurrentDefense()) / 50) + 2) * eff);
		int roundeddamage = Math.round(damage);
		def.setDamage(def.getDamage() + roundeddamage);
		System.out.println(att.getName() + " did " + roundeddamage + " damage!");
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

	public boolean getCaught() {
		return caught;
	}
}
