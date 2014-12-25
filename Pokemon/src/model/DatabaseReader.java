package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import pokemonHierarchy.ElementType;
import pokemonHierarchy.Move;
import pokemonHierarchy.Pokemon;

public class DatabaseReader {

	private Scanner file;
	private MoveDatabase moveData = new MoveDatabase();
	private static HashMap<Integer, Pokemon> hm = new HashMap<Integer, Pokemon>();

	public DatabaseReader() {
		try {
			file = new Scanner(new FileReader("PokemonData.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Failed to load file");
		}
		buildDataBase();
	}

	private void buildDataBase() {
		Pokemon basePokemon;
		String name;
		int id, hp, at, df, sp, ev;
		ElementType type;
		// Skip the Label row
		file.nextLine();
		while (file.hasNext()) {
			id = file.nextInt();
			name = file.next();
			hp = file.nextInt();
			at = file.nextInt();
			df = file.nextInt();
			sp = file.nextInt();
			type = ElementType.valueOf(file.next());
			ev = file.nextInt();			
			basePokemon = new Pokemon(name, id, hp, at, df, sp, ev, type, compileMoves(type));
			hm.put(id, basePokemon);
		}
	}
	
	private ArrayList<Move> compileMoves(ElementType elementType){
		ArrayList<Move> moves = new ArrayList<Move>();
		Random rand = new Random();
		
		// Adds two normal type moves to list
		int randomId = 0;
		randomId = rand.nextInt(19)+1;
		moves.add(moveData.getMoveByID(randomId));
		
		int nextId = rand.nextInt(19)+1;
		while (randomId == nextId)
			nextId = rand.nextInt(19)+1;
		moves.add(moveData.getMoveByID(nextId));
		
		int elementRange;
		int elementRangeOffset;
		if (elementType == ElementType.Psychic){
			elementRange = 4;
			elementRangeOffset = 20;
		}
		else if (elementType == ElementType.Rock){
			elementRange = 4;
			elementRangeOffset = 24;
		}
		else if (elementType == ElementType.Water){
			elementRange = 10;
			elementRangeOffset = 28;
		}
		else if (elementType == ElementType.Electric){
			elementRange = 4;
			elementRangeOffset = 38;
		}
		else if (elementType == ElementType.Fire){
			elementRange = 4;
			elementRangeOffset = 42;
		}
		else if (elementType == ElementType.Grass){
			elementRange = 4;
			elementRangeOffset = 46;
		}
		else{
			elementRange = 19;
			elementRangeOffset = 1;
		}
		randomId = 0;
		randomId = rand.nextInt(elementRange)+elementRangeOffset;
		
		nextId = rand.nextInt(elementRange)+elementRangeOffset;
		while (randomId == nextId)
			nextId = rand.nextInt(elementRange)+elementRangeOffset;	
		
		moves.add(moveData.getMoveByID(randomId));
		moves.add(moveData.getMoveByID(nextId));
		
		return moves;
	}

	public Pokemon getPokemonByID(int id, int level) {
		Pokemon clone = clone(hm.get(id));
		clone.setLevel(level);
		return clone;
	}

	private Pokemon clone(Pokemon poke) {
		Pokemon newPoke = new Pokemon(poke.getName(), poke.getPOKEMON_ID(),
				poke.getBASE_HP(), poke.getBASE_ATTACK(),
				poke.getBASE_DEFENSE(), poke.getBASE_SPEED(),
				poke.getEVOLVES_AT(), poke.getELEMENT_TYPE(), compileMoves(poke.getELEMENT_TYPE()));
		return newPoke;
	}

	// NONE of these below are acutally being used

	public String getLine(int line) {
		for (int i = 1; file.hasNextLine(); i++) {
			if (i == line) {
				return file.nextLine();
			} else
				file.nextLine();
		}
		return "";
	}

	public void printData() {
		for (int i = 1; i <= hm.size(); i++)
			System.out.println(hm.get(i).toString());
	}

	public String toString() {
		String result = "";
		for (int i = 1; i <= hm.size(); i++)
			result += hm.get(i).toString();
		return result;
	}

}
