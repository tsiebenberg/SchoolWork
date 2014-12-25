package mapHierarchy;

import itemHierarchy.Key;

import java.util.ArrayList;
import java.util.Random;

import model.DatabaseReader;
import playerHierarchy.BossTrainer;
import playerHierarchy.Direction;
import playerHierarchy.Player;
import playerHierarchy.Trainer;
import elements.Grass;
import elements.Rock;

public class DungeonZone extends Zone {

	protected Player boss;
	private final int spawnX = 24, spawnY = 8;
	private DatabaseReader db;

	public DungeonZone() {
		super(25, 39);
		db = new DatabaseReader();
		setMap();
	}

	public int getSpawnX() {
		return spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	@Override
	public void setMap() {
		setDoor();
		setElements();
		setComputerPlayers();
	}

	private void setDoor() {
		squareArray[24][8].removeObject(Rock.class);
		squareArray[24][8].addObject('D');
		squareArray[24][8].setIsWalkable(true);
	}

	@Override
	public void setElements() {
		setRocks();
		setGrass();
		setPokemon();
	}

	public void setPokemon() {
		Random rand = new Random();
		for (int i = 0; i < squareArray.length; i++) {
			for (int j = 0; j < squareArray[i].length; j++) {
				squareArray[i][j].removePokemon();
				if (squareArray[i][j].containsWaterOrGrass()) {
					if (rand.nextInt(100) >= 80) {
						squareArray[i][j]
								.addPokemon(new DatabaseReader()
										.getPokemonByID(
												new Random().nextInt(59) + 1, 5));
					}
				}
			}
		}

	}

	private void setGrass() {
		for (int i = 16; i < 24; i++) {
			for (int j = 1; j < 8; j++) {
				squareArray[i][j].addObject(new Grass());
			}
		}
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 8; j++) {
				squareArray[i][j].addObject(new Grass());
			}
		}
		for (int i = 5; i < 7; i++) {
			for (int j = 1; j < 4; j++) {
				squareArray[i][j].addObject(new Grass());
			}
		}
		for (int i = 1; i < 3; i++) {
			for (int j = 8; j < 25; j++) {
				squareArray[i][j].addObject(new Grass());
			}
		}
		for (int i = 11; i < 17; i++) {
			for (int j = 27; j < 33; j++) {
				squareArray[i][j].addObject(new Grass());
			}
		}
		for (int i = 9; i < 19; i++) {
			for (int j = 35; j < 38; j++) {
				squareArray[i][j].addObject(new Grass());
			}
		}
	}

	private void setRocks() {
		squareArray[14][14].addObject(new Rock());
		squareArray[14][14].setIsWalkable(false);
		squareArray[20][14].addObject(new Rock());
		squareArray[20][14].setIsWalkable(false);
		squareArray[19][36].addObject(new Rock());
		squareArray[19][36].setIsWalkable(false);
		squareArray[19][37].addObject(new Rock());
		squareArray[19][37].setIsWalkable(false);
		squareArray[21][35].addObject(new Rock());
		squareArray[21][35].setIsWalkable(false);
		squareArray[21][36].addObject(new Rock());
		squareArray[21][36].setIsWalkable(false);
		squareArray[20][22].addObject(new Rock());
		squareArray[20][22].setIsWalkable(false);
		squareArray[20][23].addObject(new Rock());
		squareArray[20][23].setIsWalkable(false);
		squareArray[20][15].addObject(new Rock());
		squareArray[20][15].setIsWalkable(false);
		squareArray[18][16].addObject(new Rock());
		squareArray[18][16].setIsWalkable(false);
		squareArray[20][16].addObject(new Rock());
		squareArray[20][16].setIsWalkable(false);
		squareArray[21][37].addObject(new Rock());
		squareArray[21][37].setIsWalkable(false);
		squareArray[23][37].addObject(new Rock());
		squareArray[23][37].setIsWalkable(false);
		for (int i = 5; i < 23; i++) {
			if (i != 7 && i != 8 && i != 13 && i != 14) {
				squareArray[i][18].addObject(new Rock());
				squareArray[i][18].setIsWalkable(false);
			}
		}
		for (int i = 7; i < 16; i++) {
			squareArray[i][7].addObject(new Rock());
			squareArray[i][7].setIsWalkable(false);
		}
		for (int i = 11; i < 14; i++) {
			squareArray[21][i].addObject(new Rock());
			squareArray[21][i].setIsWalkable(false);
		}
		for (int i = 11; i < 16; i++) {
			squareArray[18][i].addObject(new Rock());
			squareArray[18][i].setIsWalkable(false);
		}
		for (int i = 2; i < 9; i++) {
			squareArray[i][28].addObject(new Rock());
			squareArray[i][28].setIsWalkable(false);
		}
		for (int i = 9; i < 24; i++) {
			if (i != 19 && i != 20) {
				squareArray[i][10].addObject(new Rock());
				squareArray[i][10].setIsWalkable(false);
			}
		}
		for (int i = 5; i < 7; i++) {
			for (int j = 10; j < 19; j++) {
				squareArray[i][j].addObject(new Rock());
				squareArray[i][j].setIsWalkable(false);
			}
		}
		for (int i = 5; i < 9; i++) {
			for (int j = 25; j < 28; j++) {
				squareArray[i][j].addObject(new Rock());
				squareArray[i][j].setIsWalkable(false);
			}
		}
		for (int i = 1; i < 4; i++) {
			for (int j = 25; j < 29; j++) {
				squareArray[i][j].addObject(new Rock());
				squareArray[i][j].setIsWalkable(false);
			}
		}
		for (int i = 1; i < 3; i++) {
			for (int j = 29; j < 33; j++) {
				squareArray[i][j].addObject(new Rock());
				squareArray[i][j].setIsWalkable(false);
			}
		}
		for (int i = 5; i < 9; i++) {
			for (int j = 32; j < 38; j++) {
				squareArray[i][j].addObject(new Rock());
				squareArray[i][j].setIsWalkable(false);
			}
		}
		for (int i = 7; i < 16; i++) {
			for (int j = 1; j < 7; j++) {
				squareArray[i][j].addObject(new Rock());
				squareArray[i][j].setIsWalkable(false);
			}
		}
		for (int i = 15; i < 18; i++) {
			for (int j = 14; j < 18; j++) {
				squareArray[i][j].addObject(new Rock());
				squareArray[i][j].setIsWalkable(false);
			}
		}
		for (int i = 19; i < 21; i++) {
			for (int j = 25; j < 29; j++) {
				squareArray[i][j].addObject(new Rock());
				squareArray[i][j].setIsWalkable(false);
			}
		}
		for (int i = 5; i < 9; i++) {
			squareArray[i][31].addObject(new Rock());
			squareArray[i][31].setIsWalkable(false);
		}
		for (int i = 14; i < 18; i++) {
			squareArray[21][i].addObject(new Rock());
			squareArray[21][i].setIsWalkable(false);
		}
		for (int i = 18; i < 21; i++) {
			squareArray[i][17].addObject(new Rock());
			squareArray[i][17].setIsWalkable(false);
		}
		for (int i = 36; i < 38; i++) {
			squareArray[20][i].addObject(new Rock());
			squareArray[20][i].setIsWalkable(false);
		}
		for (int i = 11; i < 18; i++) {
			squareArray[9][i].addObject(new Rock());
			squareArray[9][i].setIsWalkable(false);
		}
		for (int i = 1; i < 6; i++) {
			squareArray[i][36].addObject(new Rock());
			squareArray[i][36].setIsWalkable(false);
		}
		for (int i = 1; i < 5; i++) {
			squareArray[i][37].addObject(new Rock());
			squareArray[i][37].setIsWalkable(false);
		}
		for (int i = 19; i < 37; i++) {
			squareArray[23][i].addObject(new Rock());
			squareArray[23][i].setIsWalkable(false);
		}
		for (int i = 9; i < 24; i++) {
			if (i != 13 && i != 14) {
				squareArray[i][18].addObject(new Rock());
				squareArray[i][18].setIsWalkable(false);
			}
		}
		for (int i = 19; i < 21; i++) {
			for (int j = 31; j < 36; j++) {
				squareArray[i][j].addObject(new Rock());
				squareArray[i][j].setIsWalkable(false);
			}
		}
		for (int i = 22; i < 24; i++) {
			for (int j = 11; j < 18; j++) {
				squareArray[i][j].addObject(new Rock());
				squareArray[i][j].setIsWalkable(false);
			}
		}
		for (int i = 9; i < 19; i++) {
			for (int j = 11; j < 14; j++) {
				squareArray[i][j].addObject(new Rock());
				squareArray[i][j].setIsWalkable(false);
			}
		}
		for (int i = 5; i < 21; i++) {
			for (int j = 21; j < 25; j++) {
				squareArray[i][j].addObject(new Rock());
				squareArray[i][j].setIsWalkable(false);
			}
		}
		for (int i = 10; i < 13; i++) {
			for (int j = 14; j < 18; j++) {
				squareArray[i][j].addObject(new Rock());
				squareArray[i][j].setIsWalkable(false);
			}
		}
	}

	@Override
	public void setComputerPlayers() {
		setTrainers();
		setBoss();
	}
	
	public BossTrainer getBoss() {
		return (BossTrainer) boss;
	}

	private void setBoss() {
		boss = new BossTrainer(4, 32, Direction.LEFT);
		addPlayer(null, boss);
	}

	private void setTrainers() {
		trainers = new ArrayList<Player>();
		addPlayer(trainers, new Trainer(23, 1, Direction.RIGHT));
		addPlayer(trainers, new Trainer(16, 1, Direction.RIGHT));
		addPlayer(trainers, new Trainer(19, 16, Direction.LEFT));
		addPlayer(trainers, new Trainer(10, 9, Direction.DOWN));
		addPlayer(trainers, new Trainer(6, 1, Direction.RIGHT));
		
		addPlayer(trainers, new Trainer(8, 14, Direction.LEFT));
		addPlayer(trainers, new Trainer(3, 14, Direction.LEFT));
		addPlayer(trainers, new Trainer(8, 17, Direction.RIGHT));
		addPlayer(trainers, new Trainer(4, 27, Direction.LEFT));
		addPlayer(trainers, new Trainer(13, 19, Direction.UP));
		
		addPlayer(trainers, new Trainer(13, 14, Direction.RIGHT));
		addPlayer(trainers, new Trainer(22, 20, Direction.UP));
		addPlayer(trainers, new Trainer(21, 25, Direction.LEFT));
		addPlayer(trainers, new Trainer(22, 37, Direction.LEFT));
		addPlayer(trainers, new Trainer(18, 27, Direction.RIGHT));
		
		addPlayer(trainers, new Trainer(17, 32, Direction.LEFT));
		addPlayer(trainers, new Trainer(13, 26, Direction.DOWN));
		addPlayer(trainers, new Trainer(13, 29, Direction.UP));
		addPlayer(trainers, new Trainer(13, 33, Direction.DOWN));
		addPlayer(trainers, new Trainer(9, 25, Direction.DOWN));
		
		addPlayer(trainers, new Trainer(9, 34, Direction.DOWN));
		
		addPlayer(trainers, new Trainer(6, 30, Direction.DOWN));
	}

	private void addPlayer(ArrayList<Player> trainers, Player player) {
		if (this.trainers != null) {
			this.trainers.add(player);
		}
		squareArray[player.getXPosition()][player.getYPosition()]
				.addObject(player);
		squareArray[player.getXPosition()][player.getYPosition()]
				.setIsWalkable(false);
		switch (this.trainers.size()) {
		case (1): {
			player.addPokemon(db.getPokemonByID(39, 15));
			player.addPokemon(db.getPokemonByID(39, 15));
			player.addPokemon(db.getPokemonByID(39, 15));
			break;
		}
		case (2): {
			player.addPokemon(db.getPokemonByID(42, 15));
			player.addPokemon(db.getPokemonByID(42, 16));
			break;
		}
		case (3): {
			player.addPokemon(db.getPokemonByID(47, 17));
			
			break;
		}

		case (4): {
			
			player.addPokemon(db.getPokemonByID(58, 16));
			break;
		}
		case (5): {
			
			player.addPokemon(db.getPokemonByID(42, 16));
			player.addPokemon(db.getPokemonByID(44, 16));
			break;
		}
		case (6): {
			player.addPokemon(db.getPokemonByID(39, 17));
			player.addPokemon(db.getPokemonByID(39, 17));
			player.addPokemon(db.getPokemonByID(39, 17));
			break;
		}
		case (7): {
			player.addPokemon(db.getPokemonByID(42, 17));
			player.addPokemon(db.getPokemonByID(42, 18));
			break;
		}
		case (8): {
			player.addPokemon(db.getPokemonByID(47, 19));
			player.addPokemon(db.getPokemonByID(47, 19));
			
			break;
		}

		case (9): {
			
			player.addPokemon(db.getPokemonByID(58, 20));
			break;
		}
		case (10): {
			player.addPokemon(db.getPokemonByID(39, 18));
			player.addPokemon(db.getPokemonByID(42, 19));
			player.addPokemon(db.getPokemonByID(44, 20));
			break;
		}
		
		case (11): {
			player.addPokemon(db.getPokemonByID(39, 21));
			
			break;
		}
		case (12): {
			player.addPokemon(db.getPokemonByID(42, 17));
			player.addPokemon(db.getPokemonByID(42, 17));
			break;
		}
		case (13): {
			player.addPokemon(db.getPokemonByID(47, 15));
			player.addPokemon(db.getPokemonByID(47, 17));
			player.addPokemon(db.getPokemonByID(47, 17));
			break;
		}

		case (14): {
			player.addPokemon(db.getPokemonByID(57, 17));
			player.addPokemon(db.getPokemonByID(57, 17));
			player.addPokemon(db.getPokemonByID(58, 15));
			break;
		}
		case (15): {
			player.addPokemon(db.getPokemonByID(39, 15));
			player.addPokemon(db.getPokemonByID(42, 15));
			player.addPokemon(db.getPokemonByID(44, 15));
			break;
		}
		
		case (16): {
			player.addPokemon(db.getPokemonByID(39, 15));
			player.addPokemon(db.getPokemonByID(39, 15));
			player.addPokemon(db.getPokemonByID(39, 15));
			break;
		}
		case (17): {
			player.addPokemon(db.getPokemonByID(42, 17));
			player.addPokemon(db.getPokemonByID(42, 17));
			break;
		}
		case (18): {
			player.addPokemon(db.getPokemonByID(47, 20));
			player.addPokemon(db.getPokemonByID(47, 20));
			player.addPokemon(db.getPokemonByID(47, 20));
			break;
		}

		case (19): {
			player.addPokemon(db.getPokemonByID(55, 22));
			player.addPokemon(db.getPokemonByID(52, 22));
			player.addPokemon(db.getPokemonByID(50, 22));
			break;
		}
		case (20): {
			player.addPokemon(db.getPokemonByID(48, 25));
			player.addPokemon(db.getPokemonByID(58, 27));
			break;
		}
		case(21): {
			player.addPokemon(db.getPokemonByID(48, 27));
			player.addPokemon(db.getPokemonByID(58, 27));
			break;
		}
		
		
		default:{
			player.addPokemon(new DatabaseReader().getPokemonByID(
					new Random().nextInt(59) + 1, 27));
			player.addPokemon(new DatabaseReader().getPokemonByID(
					new Random().nextInt(59) + 1, 28));
			player.addPokemon(new DatabaseReader().getPokemonByID(
					new Random().nextInt(59) + 1, 30));
			player.addPokemon(new DatabaseReader().getPokemonByID(
					new Random().nextInt(59) + 1, 32));
			player.addItem(new Key());
		}
		}
	}

	@Override
	public String toString() {
		return "Dungeon Zone";
//		String result = "";
//		for (int i = 0; i < squareArray.length; i++) {
//			for (int j = 0; j < squareArray[0].length; j++) {
//				try {
//					result = result
//							+ squareArray[i][j].getRecentObject().toString()
//							+ "  ";
//				} catch (ArrayIndexOutOfBoundsException e) {
//					result = result + " ";
//				}
//
//			}
//			result = result + "\n";
//		}
//		return result;
	}

}
