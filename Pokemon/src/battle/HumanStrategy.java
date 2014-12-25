package battle;

import itemHierarchy.Pokeball;
import itemHierarchy.Potion;

import java.util.ArrayList;
import java.util.Scanner;

import playerHierarchy.Player;
import pokemonHierarchy.IMove;
import pokemonHierarchy.Move;
import pokemonHierarchy.Pokemon;

public class HumanStrategy implements IStrategy {

	private Pokemon attacker;
	private IMove chosenMove;
	Scanner scan = new Scanner(System.in);
	private Player player;
	private boolean throwPoke;
	private Pokemon attPokemon;
	
	

	@Override
	public void setPlayer(Player trainer) {
		// TODO Auto-generated method stub
		player = trainer;
	}

	@Override
	public ArrayList<Pokemon> getTrainerPokemon() {
		return player.getPokemon();
	}

	@Override
	public Pokemon getAttacker() {

		return attPokemon;
	}

	public void setPokemon(Pokemon selected) {
		if (selected == null) {
			attPokemon = setPokemon();
		} else {
			attPokemon = selected;
		}
	}

	public Pokemon setPokemon() {
		System.out.println("Which Pokemon do you want to use? ");
		for (int i = 0; i < player.getPokemon().size(); i++) {
			System.out.print((player.getPokemon().get(i).getName()) + " ");
		}
		String input = scan.nextLine();
		System.out.println();
		boolean found = false;
		for (int i = 0; i < player.getPokemon().size(); i++) {
			if (player.getPokemon().get(i).getName()
					.equalsIgnoreCase(input.trim())) {
				attacker = player.getPokemon().get(i);
				found = true;
			}
		}

		while (found == false) {
			System.out.println("Which Pokemon do you want to use? ");
			for (int i = 0; i < player.getPokemon().size(); i++) {
				System.out.print((player.getPokemon().get(i).getName()) + " ");
			}
			input = scan.nextLine();
			for (int i = 0; i < player.getPokemon().size(); i++) {
				if (player.getPokemon().get(i).getName()
						.equalsIgnoreCase(input.trim())) {
					attacker = player.getPokemon().get(i);
					found = true;
				}
			}
		}
		return attacker;
	}

	@Override
	public IMove pickMove() {
		while(chosenMove==null){
			
		}
		return chosenMove;
//		if (throwPoke == true) {
//			System.out
//					.println("Choose move or type 'potion' to heal or Choose move or type 'throw Pokeball': ");
//		} else {
//			System.out.println("Choose move or type 'potion' to heal: ");
//		}
//		for (int i = 0; i < attacker.getMoves().size(); i++) {
//			System.out.print(attacker.getMoves().get(i).getMOVE_NAME() + " ");
//		}
//		String input = scan.nextLine();
//		if (input.trim().equalsIgnoreCase("potion"))
//			return new Potion();
//		if (input.trim().equalsIgnoreCase("Pokeball")) {
//			if (throwPoke == false) {
//				System.out.println("Cannot steal trainer's Pokemon!");
//			} else {
//				return new Pokeball();
//			}
//		}
//		boolean found = false;
//		for (int i = 0; i < attacker.getMoves().size(); i++) {
//			if (attacker.getMoves().get(i).getMOVE_NAME().equals(input.trim())) {
//				chosenMove = attacker.getMoves().get(i);
//				found = true;
//			}
//		}
//		while (found == false) {
//			if (throwPoke == true) {
//				System.out
//						.println("Choose move or type 'potion' to heal or Choose move or type 'throw Pokeball': ");
//			} else {
//				System.out.println("Choose move or type 'potion' to heal: ");
//			}
//			for (int i = 0; i < attacker.getMoves().size(); i++) {
//				System.out.print(attacker.getMoves().get(i).getMOVE_NAME()
//						+ " ");
//			}
//			input = scan.nextLine();
//			if (input.trim().equalsIgnoreCase("potion"))
//				return new Potion();
//			if (input.trim().equalsIgnoreCase("Pokeball")) {
//				return new Pokeball();
//			}
//			for (int i = 0; i < attacker.getMoves().size(); i++) {
//				if (attacker.getMoves().get(i).getMOVE_NAME()
//						.equals(input.trim())) {
//					chosenMove = attacker.getMoves().get(i);
//					found = true;
//				}
//			}
//		}
//		System.out.println();
//		return chosenMove;
	}
	
	public void pickMoveGUI(IMove select){
		 chosenMove= select;
	}

	@Override
	public void setDefender(Pokemon p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setThrowPokeball(boolean poke) {
		// TODO Auto-generated method stub
		this.throwPoke = poke;

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
