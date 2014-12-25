package mapHierarchy;

import java.util.ArrayList;
import java.util.Random;

import model.DatabaseReader;
import playerHierarchy.AI;
import playerHierarchy.Direction;
import playerHierarchy.Player;
import playerHierarchy.Trainer;
import buildingHierarchy.Building;
import buildingHierarchy.Dungeon;
import buildingHierarchy.Gym;
import buildingHierarchy.PokeCenter;
import elements.Grass;
import elements.Rock;
import elements.Water;

public class MainZone extends Zone {

	private int gymX, gymY, gymSpawnX, gymSpawnY;
	private int pokeX, pokeY, pokeCenterSpawnX, pokeCenterSpawnY;
	private int dungeonX, dungeonY, dungeonSpawnX, dungeonSpawnY;
	private Building gym, pokeCenter, dungeon;
	private DatabaseReader db;

	public MainZone() {
		super(25, 25);
		db = new DatabaseReader();
		setMap();
	}

	@Override
	public void setMap() {
		setBuildingProperties();
		setBuildings();
		setElements();
		setComputerPlayers();
	}

	private void setBuildingProperties() {
		gymX = 1;
		gymY = 1;
		gymSpawnX = 3 + 1;
		gymSpawnY = 4;
		pokeX = 6;
		pokeY = 9;
		pokeCenterSpawnX = 9;
		pokeCenterSpawnY = 10;
		dungeonX = 19;
		dungeonY = 1;
		dungeonSpawnX = 22;
		dungeonSpawnY = 4;
	}

	public Gym getGym() {
		return (Gym) gym;
	}

	public int getGymSpawnX() {
		return gymSpawnX;
	}

	public int getGymSpawnY() {
		return gymSpawnY;
	}

	public PokeCenter getPokeCenter() {
		return (PokeCenter) pokeCenter;
	}

	public int getPokeCenterSpawnX() {
		return pokeCenterSpawnX;
	}

	public int getPokeCenterSpawnY() {
		return pokeCenterSpawnY;
	}

	public Dungeon getDungeon() {
		return (Dungeon) dungeon;
	}

	public int getDungeonSpawnX() {
		return dungeonSpawnX;
	}

	public int getDungeonSpawnY() {
		return dungeonSpawnY;
	}

	public void setBuildings() {
		setGym(gymX, gymY);
		setPokeCenter(pokeX, pokeY);
		setDungeon(dungeonX, dungeonY);
	}

	@Override
	public void setElements() {
		setRocks();
		setGrass();
		setWater();
		setPokemon();
	}

	@Override
	public void setComputerPlayers() {
		setTrainers();
	}

	private void setGym(int gymX, int gymY) {
		gym = new Gym(gymX, gymY);
		for (int i = gymX; i < gymX + gym.getHeight(); i++) {
			for (int j = gymY; j < gymY + gym.getWidth(); j++) {
				squareArray[i][j].addObject(gym);
				if (i == gym.getDoorX() && j == gym.getDoorY()) {
					squareArray[i][j].addObject('D');
				} else {
					squareArray[i][j].setIsWalkable(false);
				}
			}
		}
	}

	private void setDungeon(int dungeonX, int dungeonY) {
		dungeon = new Dungeon(dungeonX, dungeonY);
		for (int i = dungeonX; i < dungeonX + dungeon.getHeight(); i++) {
			for (int j = dungeonY; j < dungeonY + dungeon.getWidth(); j++) {
				squareArray[i][j].addObject(dungeon);
				if (i == dungeon.getDoorX() && j == dungeon.getDoorY()) {
					squareArray[i][j].addObject('D');
				} else {
					squareArray[i][j].setIsWalkable(false);
				}
			}
		}
	}

	private void setPokeCenter(int pokeX, int pokeY) {
		pokeCenter = new PokeCenter(pokeX, pokeY);
		for (int i = pokeX; i < pokeX + pokeCenter.getHeight(); i++) {
			for (int j = pokeY; j < pokeY + pokeCenter.getWidth(); j++) {
				squareArray[i][j].addObject(pokeCenter);
				if (i == pokeCenter.getDoorX() && j == pokeCenter.getDoorY()) {
					squareArray[i][j].addObject('D');
				} else {
					squareArray[i][j].setIsWalkable(false);
				}
			}
		}
	}

	private void setTrainers() {
		trainers = new ArrayList<Player>();
		addPlayer(trainers, new Trainer(3, 19, Direction.LEFT));
		addPlayer(trainers, new Trainer(9, 22, Direction.LEFT));
		addPlayer(trainers, new Trainer(13, 11, Direction.DOWN));
		addPlayer(trainers, new Trainer(19, 19, Direction.UP));
		addPlayer(trainers, new Trainer(23, 11, Direction.UP));
	}

	private void setRocks() {
		for (int i = 1; i < 15; i++) {
			if (i != 7) {
				squareArray[11][i].addObject(new Rock());
				squareArray[11][i].setIsWalkable(false);
			}

		}
		for (int i = 1; i < 12; i++) {
			if (i != 3 && i != 4) {
				squareArray[i][14].addObject(new Rock());
				squareArray[i][14].setIsWalkable(false);
			}
		}
		for (int i = 1; i < 9; i++) {
			squareArray[18][i].addObject(new Rock());
			squareArray[18][i].setIsWalkable(false);

		}
		for (int i = 18; i < 22; i++) {
			squareArray[i][8].addObject(new Rock());
			squareArray[i][8].setIsWalkable(false);
		}
		for (int i = 20; i < 24; i++) {
			squareArray[i][14].addObject(new Rock());
			squareArray[i][14].setIsWalkable(false);
		}
		for (int i = 20; i < 24; i++) {
			squareArray[12][i].addObject(new Rock());
			squareArray[12][i].setIsWalkable(false);
		}
	}

	private void setGrass() {
		for (int i = 1; i < 3; i++) {
			for (int j = 15; j < 24; j++) {
				squareArray[i][j].addObject(new Grass());
			}
		}
		for (int i = 3; i < 12; i++) {
			for (int j = 20; j < 24; j++) {
				squareArray[i][j].addObject(new Grass());
			}
		}
		for (int i = 5; i < 18; i++) {
			for (int j = 15; j < 18; j++) {
				squareArray[i][j].addObject(new Grass());
			}
		}
		for (int i = 12; i < 16; i++) {
			for (int j = 8; j < 18; j++) {
				squareArray[i][j].addObject(new Grass());
			}
		}
		for (int i = 16; i < 18; i++) {
			for (int j = 12; j < 15; j++) {
				squareArray[i][j].addObject(new Grass());
			}
		}
		for (int i = 14; i < 16; i++) {
			for (int j = 3; j < 8; j++) {
				squareArray[i][j].addObject(new Grass());
			}
		}
		for (int i = 20; i < 24; i++) {
			for (int j = 12; j < 14; j++) {
				squareArray[i][j].addObject(new Grass());
			}
		}
		for (int i = 1; i < 11; i++) {
			squareArray[17][i].addObject(new Grass());
		}
		for (int i = 1; i < 7; i++) {
			squareArray[12][i].addObject(new Grass());
		}
		for (int i = 13; i < 17; i++) {
			squareArray[i][1].addObject(new Grass());
		}
		for (int i = 18; i < 22; i++) {
			squareArray[i][9].addObject(new Grass());
		}
	}

	private void setWater() {
		for (int i = 20; i < 24; i++) {
			for (int j = 15; j < 24; j++) {
				squareArray[i][j].addObject(new Water());
				squareArray[i][j].setIsWalkable(false);
			}
		}
		for (int i = 13; i < 20; i++) {
			for (int j = 20; j < 24; j++) {
				squareArray[i][j].addObject(new Water());
				squareArray[i][j].setIsWalkable(false);
			}
		}
	}
	public void addAIs(AI ai){
		squareArray[ai.getXPosition()][ai.getYPosition()].addObject(ai);
		squareArray[ai.getXPosition()][ai.getYPosition()]
				.setIsWalkable(false);
	}
	private void addPlayer(ArrayList<Player> trainers, Player player) {
		if (trainers != null) {
			trainers.add(player);
		}
		squareArray[player.getXPosition()][player.getYPosition()]
				.addObject(player);
		squareArray[player.getXPosition()][player.getYPosition()]
				.setIsWalkable(false);
		switch (trainers.size()) {
		case (1): {
			player.addPokemon(db.getPokemonByID(13, 3));
			player.addPokemon(db.getPokemonByID(10, 3));
			player.addPokemon(db.getPokemonByID(10, 4));
			break;
		}
		case (2): {
			player.addPokemon(db.getPokemonByID(17, 4));
			player.addPokemon(db.getPokemonByID(20, 4));
			break;
		}
		case (3): {
			player.addPokemon(db.getPokemonByID(25, 3));
			player.addPokemon(db.getPokemonByID(50, 4));
			player.addPokemon(db.getPokemonByID(33, 7));
			break;
		}

		case (4): {
			player.addPokemon(db.getPokemonByID(57, 7));
			player.addPokemon(db.getPokemonByID(57, 10));
			player.addPokemon(db.getPokemonByID(58, 15));
			break;
		}
		case (5): {
			player.addPokemon(db.getPokemonByID(39, 15));
			player.addPokemon(db.getPokemonByID(42, 15));
			player.addPokemon(db.getPokemonByID(44, 15));
			break;
		}
		default:
			player.addPokemon(new DatabaseReader().getPokemonByID(
					new Random().nextInt(59) + 1, 1));
		}

	}

	@Override
	public String toString() {

		String result = "";
		for (int i = 0; i < squareArray.length; i++) {
			for (int j = 0; j < squareArray[0].length; j++) {
				try {
					result = result
							+ squareArray[i][j].getRecentObject().toString()
							+ "  ";
				} catch (ArrayIndexOutOfBoundsException e) {
					result = result + " ";
				}
			}
			result = result + "\n";
		}
		return result;

//		String result = "";
//		for (int i = 0; i < squareArray.length; i++) {
//			for (int j = 0; j < squareArray[0].length; j++) {
//				try {
//					result = result + squareArray[i][j].getRecentObject().toString() + "  ";
//				} catch (ArrayIndexOutOfBoundsException e) {
//					result = result + " ";
//				}
//			}
//			result = result + "\n";
//		}
//		return result;

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
												new Random().nextInt(63) + 1, 1));
					}
				}
			}
		}

	}

}
