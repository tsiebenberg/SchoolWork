package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

import pokemonHierarchy.ElementType;
import pokemonHierarchy.Move;

public class MoveDatabase {

	private Scanner file;
	private static HashMap<Integer, Move> moves = new HashMap<Integer, Move>();

	public MoveDatabase() {
		try {
			file = new Scanner(new FileReader("Moves.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Failed to load file");
		}
		buildDataBase();
	}

	private void buildDataBase() {
		String name;
		int id, baseDamage;
		ElementType elementType;
		// Skip the Label row
		//file.nextLine();
		while (file.hasNext()) {
			id = file.nextInt();
			name = file.next();
			elementType = ElementType.valueOf(file.next());
			baseDamage = file.nextInt();
			Move move = new Move(name, id, baseDamage, elementType);
			moves.put(id, move);
		}
	}

	public Move getMoveByID(int key) {
		return moves.get(key);
	}
	
	public String toString(){
		String result = "";		
		for (int i = 1; i <= moves.size(); i++){
			result += "(" + moves.get(i).getMOVE_ID() + "=" + moves.get(i).getMOVE_NAME() + "," + moves.get(i).getELEMENT_TYPE() + ")\n";
		}		
		return result;
	}
}
