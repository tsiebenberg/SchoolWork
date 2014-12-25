package mapHierarchy;

import java.util.ArrayList;
import java.util.Random;

import model.DatabaseReader;
import playerHierarchy.BossGuard;
import playerHierarchy.BossTrainer;
import playerHierarchy.Direction;
import playerHierarchy.Player;
import elements.Rock;

public class GymZone extends Zone {

	protected Player boss;
	private DatabaseReader db;
	private final int spawnX = 12, spawnY = 7;
	int counter =0;

	public GymZone() {
		super(13, 16);
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
		squareArray[12][7].removeObject(Rock.class);
		squareArray[12][7].addObject('D');
		squareArray[12][7].setIsWalkable(true);
	}

	@Override
	public void setElements() {
		setRocks();
	}

	@Override
	public void setComputerPlayers() {
		setBossGuards();
		setBoss();
	}

	private void setRocks() {
		for (int i = 1; i < 12; i++) {
			squareArray[i][1].addObject('K');
			squareArray[i][1].setIsWalkable(false);
		}
		for (int i = 2; i < 8; i++) {
			squareArray[1][i].addObject('K');
			squareArray[1][i].setIsWalkable(false);

		}
		for (int i = 3; i < 7; i++) {
			for (int j = 3; j < 5; j++) {
				squareArray[i][j].addObject('K');
				squareArray[i][j].setIsWalkable(false);
			}
		}
		for (int i = 5; i < 13; i++) {
			squareArray[6][i].addObject('K');
			squareArray[6][i].setIsWalkable(false);
		}
		for (int i = 7; i < 10; i++) {
			squareArray[i][3].addObject('K');
			squareArray[i][3].setIsWalkable(false);
		}
		for (int i = 4; i < 14; i++) {
			if (i != 7) {
				squareArray[9][i].addObject('K');
				squareArray[9][i].setIsWalkable(false);
			}
		}
		for (int i = 6; i < 10; i++) {
			squareArray[4][i].addObject('K');
			squareArray[4][i].setIsWalkable(false);
		}
		for (int i = 2; i < 4; i++) {
			squareArray[i][9].addObject('K');
			squareArray[i][9].setIsWalkable(false);
		}
		for (int i = 2; i < 5; i++) {
			squareArray[i][11].addObject('K');
			squareArray[i][11].setIsWalkable(false);
		}
		for (int i = 2; i < 14; i++) {
			if (i != 7) {
				squareArray[11][i].addObject('K');
				squareArray[11][i].setIsWalkable(false);
			}
		}
		for (int i = 6; i < 8; i++) {
			squareArray[3][6].addObject('K');
			squareArray[3][6].setIsWalkable(false);
		}
		for (int i = 6; i < 9; i++) {
			if (i != 7) {
				squareArray[8][i].addObject('K');
				squareArray[8][i].setIsWalkable(false);
			}
		}
		squareArray[3][7].addObject('K');
		squareArray[3][7].setIsWalkable(false);
		squareArray[4][12].addObject('K');
		squareArray[4][12].setIsWalkable(false);
	}

	private void setBossGuards() {
		bossGuards = new ArrayList<Player>();
		addPlayer(bossGuards, new BossGuard(10, 14, Direction.LEFT));
		addPlayer(bossGuards, new BossGuard(7, 7, Direction.DOWN));
		addPlayer(bossGuards, new BossGuard(5, 13, Direction.LEFT));
		addPlayer(bossGuards, new BossGuard(2, 8, Direction.LEFT));
	}

	public BossTrainer getBoss() {
		return (BossTrainer) boss;
	}

	private void setBoss() {
		boss = new BossTrainer(1, 10, Direction.DOWN);
		addPlayer(null, boss);
	}

	private void addPlayer(ArrayList<Player> trainers, Player player) {
		if (trainers != null) {
			trainers.add(player);
			counter++;
		}
		squareArray[player.getXPosition()][player.getYPosition()]
				.addObject(player);
		squareArray[player.getXPosition()][player.getYPosition()]
				.setIsWalkable(false);
		switch (counter) {
		case (1): {
			player.addPokemon(db.getPokemonByID(43, 33));
			player.addPokemon(db.getPokemonByID(54, 34));
			player.addPokemon(db.getPokemonByID(35, 35));
			break;
		}
		case (2): {
			player.addPokemon(db.getPokemonByID(43, 35));
			player.addPokemon(db.getPokemonByID(46, 35));
			break;
		}
		case (3): {
			player.addPokemon(db.getPokemonByID(52, 40));
			break;
		}

		case (4): {
			player.addPokemon(db.getPokemonByID(55, 38));
			player.addPokemon(db.getPokemonByID(58, 38));
			break;
		}
		case (5): {

			player.addPokemon(db.getPokemonByID(59, 40));
			player.addPokemon(db.getPokemonByID(60, 40));
			player.addPokemon(db.getPokemonByID(61, 40));
			player.addPokemon(db.getPokemonByID(62, 40));
			player.addPokemon(db.getPokemonByID(63, 40));
			break;
		}
		default: {
			player.addPokemon(new DatabaseReader().getPokemonByID(
					new Random().nextInt(63) + 1, 1));
			}
		}
	}

	@Override
	public String toString() {
		return "Gym Zone";
		// String result = "";
		// for (int i = 0; i < squareArray.length; i++) {
		// for (int j = 0; j < squareArray[0].length; j++) {
		// try {
		// result = result
		// + squareArray[i][j].getRecentObject().toString()
		// + "  ";
		// } catch (ArrayIndexOutOfBoundsException e) {
		// result = result + " ";
		// }
		//
		// }
		// result = result + "\n";
		// }
		// return result;
	}

}
