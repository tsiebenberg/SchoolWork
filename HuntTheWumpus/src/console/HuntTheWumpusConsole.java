package console;

import java.util.Scanner;

import model.HuntTheWumpusModel;

/**
 * 
 * @author Nick Gizzi
 * @author Jake Hewitt
 * @author Darshan Kothari
 * @author Taylor Siebenberg
 * 
 */
public class HuntTheWumpusConsole {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		HuntTheWumpusModel model = new HuntTheWumpusModel(1);
		Scanner userInput = new Scanner(System.in);

		System.out.println("" + "Welcome to Hunt the Wumpus!\n" + "Controls:\n"
				+ "Up: U or Up.\n" + "Down: D or Down.\n"
				+ "Left: L or Left.\n" + "Right: R of Right.\n"
				+ "To enter Fire mode: F or Fire.\n"
				+ "To get out of Fire mode, enter nothing.\n"
				+ "To start a new game: New Game.\n");

		System.out.println(model.toString());

		while (!model.gameOver()) {
			System.out.print("Enter a command to move: ");
			String input = userInput.nextLine().toLowerCase();
			System.out.println();
			if (input.equalsIgnoreCase("new game")) {
				model = new HuntTheWumpusModel(1);
				System.out.println(model.toString());
			} else {
				String warning = "";

				if (!input.isEmpty() && input.trim().charAt(0) == 'f') {
					System.out.print("Enter direction to fire: ");
					input = userInput.nextLine();
					System.out.println(model.fireArrow(input));
				} else {
					warning = (model.move(input));
					System.out.println(model.toString());
					System.out.println(warning);
				}
			}
		}
		model.showMap();
		System.out.println();
		System.out.println(model.toString());
	}

}
