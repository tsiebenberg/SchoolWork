package view;

import java.util.Scanner;

import model.PokemonModel;
import battle.Battle;

public class PokemonConsole {

	public static void main(String[] args) {

		PokemonModel model = new PokemonModel();
		Battle b = new Battle(model);
		@SuppressWarnings("resource")
		Scanner userInput = new Scanner(System.in);
		System.out.println(model.toString());
		while (!model.gameOver()) {
			System.out.print("Enter a command to move: ");
			String input = userInput.nextLine().toLowerCase();
			System.out.println();
			model.move(input);

			if (model.checkBattles()) {
				System.out.println(model.toString());
				b.TrainerBattle(model.getPlayer(), model.getPlayerToBattle());
				if (model.getPlayerToBattle().isBoss()) {
					if (model.getPlayerToBattle().istrainerOutOfPokemon() != true)
						model.getPlayerToBattle().toggleBattle();
					System.out.println("You are a champion!!");
					System.exit(1);
				}
			}
			System.out.println(model.toString());
			model.checkWildPokemonBattle();
			// Doesn't work
			// if (model.bossDead()){
			// System.out.println("You are a champion!!");
			// }
		}
	}
}