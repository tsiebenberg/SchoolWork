package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import mapHierarchy.DungeonZone;
import mapHierarchy.GymZone;
import mapHierarchy.MainZone;
import mapHierarchy.PokeCenterZone;
import mapHierarchy.Zone;
import playerHierarchy.AI;
import playerHierarchy.ComputerPlayer;
import playerHierarchy.Direction;
import playerHierarchy.HumanPlayer;
import playerHierarchy.Player;
import pokemonHierarchy.Pokemon;
import animations.BattleAnimation;
import battle.Battle;
import buildingHierarchy.PC;

@SuppressWarnings("serial")
public class PokemonModel implements Serializable {

	private Zone playerZone;
	private MainZone mainZone;

	private GymZone gymZone;

	private PokeCenterZone pokeCenterZone;
	private DungeonZone dungeonZone;
	private PC pc;
	public HumanPlayer player;
	private boolean won;

	private String basedir;
	private AI trader;
	BattleAnimation ba;

	public PokemonModel() {
		player = new HumanPlayer(7, 7);
		mainZone = new MainZone();
		gymZone = new GymZone();
		pokeCenterZone = new PokeCenterZone();
		dungeonZone = new DungeonZone();
		won = false;
		playerZone = mainZone;
		setPlayer(7, 7);
		player.addPokemon(new DatabaseReader().getPokemonByID(15, 99)); ///Change starting pokemon level (id, level);
		trader = new AI(8, 12, Direction.DOWN);
		this.mainZone.addAIs(trader);
		trader.setHumanPlayer(player);
		pc = new PC();

		this.basedir = "./savedGame/";
	}

	public HumanPlayer getPlayer() {
		return player;
	}

	public AI getAI() {
		return trader;
	}

	private void setPlayer(int x, int y) {
		player.movePlayer(x, y);
		playerZone.getSquare(x, y).addObject(player);
	}

	public boolean move(String str) {
		if (!str.isEmpty()) {
			str.trim();
			String dir = str.substring(0, 1);
			if (dir.equals("d"))
				return move(Direction.RIGHT);
			else if (dir.equals("a"))
				return move(Direction.LEFT);
			else if (dir.equals("w"))
				return move(Direction.UP);
			else if (dir.equals("s"))
				return move(Direction.DOWN);
			else
				return false;
		}
		return false;
	}

	public boolean move(Direction dir) {
		if (dir == Direction.UP)
			return moveUp();
		else if (dir == Direction.DOWN)
			return moveDown();
		else if (dir == Direction.LEFT)
			return moveLeft();
		else if (dir == Direction.RIGHT)
			return moveRight();
		else
			return false;
	}

	// geters for all the zones
	public MainZone getMainZone() {
		return mainZone;
	}

	public GymZone getGymZone() {
		return gymZone;
	}

	public DungeonZone getDungeonZone() {
		return dungeonZone;
	}

	public PokeCenterZone getPokeCenterZone() {
		return pokeCenterZone;
	}

	public PC getPC() {
		return pc;
	}

	public boolean moveUp() {
		int x, newX, y;
		x = player.getXPosition();
		newX = player.getXPosition() - 1;
		y = player.getYPosition();

		if (playerZone.getSquare(newX, y).IsWalkable()) {
			mainZone.setPokemon();
			dungeonZone.setPokemon();
			playerZone.getSquare(x, y).removeObject(player);
			movePlayer(newX, y);
			if (PlayerOnDoorOrExit()) {
				goInDoorOrExit();
			}
			checkBattles();

//			if (checkBattles() == false && player.canFight() == false)
//				System.out.println("You are out of usable Pokemon");
			return true;
		} else
			return false;
	}

	public boolean moveDown() {
		int x, newX, y;
		x = player.getXPosition();
		newX = player.getXPosition() + 1;
		y = player.getYPosition();

		if (playerZone.getSquare(newX, y).IsWalkable()) {
			mainZone.setPokemon();
			dungeonZone.setPokemon();
			playerZone.getSquare(x, y).removeObject(player);
			movePlayer(newX, y);
			if (PlayerOnDoorOrExit()) {
				goInDoorOrExit();
			}
			checkBattles();

//			if (checkBattles() == true && player.canFight() == false)
//				System.out.println("You are out of usable Pokemon");
			return true;
		} else
			return false;
	}

	public boolean moveLeft() {
		int y, newY, x;
		y = player.getYPosition();
		newY = player.getYPosition() - 1;
		x = player.getXPosition();

		if (playerZone.getSquare(x, newY).IsWalkable()) {
			mainZone.setPokemon();
			dungeonZone.setPokemon();
			playerZone.getSquare(x, y).removeObject(player);
			movePlayer(x, newY);
			if (PlayerOnDoorOrExit()) {
				goInDoorOrExit();
			}
			checkBattles();

//			if (checkBattles() == true && player.canFight() == false)
//				System.out.println("You are out of usable Pokemon");
			return true;
		} else
			return false;
	}

	public boolean moveRight() {
		int y, newY, x;
		y = player.getYPosition();
		newY = player.getYPosition() + 1;
		x = player.getXPosition();

		if (playerZone.getSquare(x, newY).IsWalkable()) {
			mainZone.setPokemon();
			dungeonZone.setPokemon();
			playerZone.getSquare(x, y).removeObject(player);
			movePlayer(x, newY);
			if (PlayerOnDoorOrExit()) {
				goInDoorOrExit();
			}
//			if (checkBattles() == true && player.canFight() == false)
//				System.out.println("You are out of usable Pokemon");
			return true;
		} else
			return false;
	}

	public void checkWildPokemonBattle() {
		int x = player.getXPosition();
		int y = player.getYPosition();
		ArrayList<Object> k = playerZone.getSquare(x, y).getObjects();
		for (Object z : k) {
			if (z instanceof Pokemon) {
				ba = new BattleAnimation(player, (Pokemon) z, this);
				ba.setVisible(true);
			}
		}
	}

	public void movePlayer(int x, int y) {
		player.movePlayer(x, y);
		playerZone.getSquare(x, y).addObject(player);
	}

	public void setPlayerZone(Zone aZone) {
		playerZone = aZone;
	}

	public Zone getPlayerZone() {
		return playerZone;
	}

	private void goInDoorOrExit() {
		if (playerZone.equals(mainZone)) {
			int gymDoorX, gymDoorY;
			int pokeCenterDoorX, pokeCenterDoorY;
			int dungeonDoorX, dungeonDoorY;
			gymDoorX = ((MainZone) playerZone).getGym().getDoorX();
			gymDoorY = ((MainZone) playerZone).getGym().getDoorY();
			pokeCenterDoorX = ((MainZone) playerZone).getPokeCenter().getDoorX();
			pokeCenterDoorY = ((MainZone) playerZone).getPokeCenter().getDoorY();
			dungeonDoorX = ((MainZone) playerZone).getDungeon().getDoorX();
			dungeonDoorY = ((MainZone) playerZone).getDungeon().getDoorY();

			if (player.getXPosition() == gymDoorX && player.getYPosition() == gymDoorY) {
				playerZone.getSquare(gymDoorX, gymDoorY).removeObject(player);
				setPlayerZone(gymZone);
				setPlayer(gymZone.getSpawnX() - 1, gymZone.getSpawnY());
			} else if (player.getXPosition() == pokeCenterDoorX && player.getYPosition() == pokeCenterDoorY) {
				playerZone.getSquare(pokeCenterDoorX, pokeCenterDoorY).removeObject(player);
				setPlayerZone(pokeCenterZone);
				setPlayer(pokeCenterZone.getSpawnX() - 1, pokeCenterZone.getSpawnY());
			} else if (player.getXPosition() == dungeonDoorX && player.getYPosition() == dungeonDoorY) {
				playerZone.getSquare(dungeonDoorX, dungeonDoorY).removeObject(player);
				setPlayerZone(dungeonZone);
				setPlayer(dungeonZone.getSpawnX() - 1, dungeonZone.getSpawnY());
			}
		} else if (playerZone.equals(gymZone)) {
			int exitX, exitY;
			exitX = ((GymZone) playerZone).getSpawnX();
			exitY = ((GymZone) playerZone).getSpawnY();

			if (player.getXPosition() == exitX && player.getYPosition() == exitY) {
				playerZone.getSquare(exitX, exitY).removeObject(player);
				setPlayerZone(mainZone);
				setPlayer(mainZone.getGymSpawnX(), mainZone.getGymSpawnY());
			}
		} else if (playerZone.equals(pokeCenterZone)) {
			int exitX, exitY;
			exitX = ((PokeCenterZone) playerZone).getSpawnX();
			exitY = ((PokeCenterZone) playerZone).getSpawnY();

			if (player.getXPosition() == exitX && player.getYPosition() == exitY) {
				playerZone.getSquare(exitX, exitY).removeObject(player);
				setPlayerZone(mainZone);
				setPlayer(mainZone.getPokeCenterSpawnX(), mainZone.getPokeCenterSpawnY());
			}
		} else if (playerZone.equals(dungeonZone)) {
			int exitX, exitY;
			exitX = ((DungeonZone) playerZone).getSpawnX();
			exitY = ((DungeonZone) playerZone).getSpawnY();

			if (player.getXPosition() == exitX && player.getYPosition() == exitY) {
				playerZone.getSquare(exitX, exitY).removeObject(player);
				setPlayerZone(mainZone);
				setPlayer(mainZone.getDungeonSpawnX(), mainZone.getDungeonSpawnY());
			}
		}
	}

	private boolean PlayerOnDoorOrExit() {
		if (playerZone.equals(mainZone)) {
			if (player.getXPosition() == ((MainZone) playerZone).getGym().getDoorX() && player.getYPosition() == ((MainZone) playerZone).getGym().getDoorY()) {
				return true;
			} else if (player.getXPosition() == ((MainZone) playerZone).getPokeCenter().getDoorX() && player.getYPosition() == ((MainZone) playerZone).getPokeCenter().getDoorY()) {
				return true;
			} else if (player.getXPosition() == ((MainZone) playerZone).getDungeon().getDoorX() && player.getYPosition() == ((MainZone) playerZone).getDungeon().getDoorY()) {
				return true;
			}
		} else if (playerZone.equals(gymZone)) {
			if (player.getXPosition() == ((GymZone) playerZone).getSpawnX() && player.getYPosition() == ((GymZone) playerZone).getSpawnY()) {
				return true;
			}
		} else if (playerZone.equals(pokeCenterZone)) {
			if (player.getXPosition() == ((PokeCenterZone) playerZone).getSpawnX() && player.getYPosition() == ((PokeCenterZone) playerZone).getSpawnY()) {
				return true;
			}
		} else if (playerZone.equals(dungeonZone)) {
			if (player.getXPosition() == ((DungeonZone) playerZone).getSpawnX() && player.getYPosition() == ((DungeonZone) playerZone).getSpawnY()) {
				return true;
			}
		}
		return false;
	}

	public boolean checkBattles() {
		if (player.canFight() == false) {
			//spawnAtPokeCenter(player.getXPosition(), player.getYPosition());
			JOptionPane.showMessageDialog(null, "You have been healed!");
			pokeCenterZone.healPokemon(player);
		} else {
			if (playerZone.equals(mainZone)) {
				return checkBattles(playerZone.getTrainers(), null);
			} else if (playerZone.equals(dungeonZone)) {
				return checkBattles(playerZone.getTrainers(), ((DungeonZone) playerZone).getBoss());
			} else if (playerZone.equals(gymZone)) { // GYM
				return checkBattles(playerZone.getBossGuards(), ((GymZone) playerZone).getBoss());
			} else
				return false;
		}
		return false;
	}

	public Player getPlayerToBattle() {
		if (playerZone.equals(mainZone)) {
			return getPlayerToBattle(playerZone.getTrainers(), null);
		} else if (playerZone.equals(dungeonZone)) {
			return getPlayerToBattle(playerZone.getTrainers(), ((DungeonZone) playerZone).getBoss());
		} else if (playerZone.equals(gymZone)) { // GYM
			return getPlayerToBattle(playerZone.getBossGuards(), ((GymZone) playerZone).getBoss());
		} else
			return null;
	}

	private void spawnAtPokeCenter(int playerX, int playerY) {
		playerZone.getSquare(playerX, playerY).removeObject(player);
		setPlayerZone(mainZone);
		setPlayer(9, 10);
		System.out.println("You are out of usable Pokemon");
	}

	public boolean gameOver() {
		ArrayList<Pokemon> pcList = pc.getPokemon();
		ArrayList<Pokemon> playerList = player.getPokemon();
		ArrayList<Integer> checkWinList = new ArrayList<Integer>();
		for (int i = 0; i < pcList.size(); i++) {
			if (checkWinList.contains(pcList.get(i).getPOKEMON_ID()) == false) {
				checkWinList.add(pcList.get(i).getPOKEMON_ID());
			}
		}
		for (int j = 0; j < playerList.size(); j++) {
			if (checkWinList.contains(playerList.get(j).getPOKEMON_ID()) == false) {
				checkWinList.add(playerList.get(j).getPOKEMON_ID());
			}
		}
		System.out.println(checkWinList.size());
		if (checkWinList.size() == 63 || bossDead())
			return true;
		else
			return false;
	}

	public boolean bossDead() {
		return gymZone.getBoss().istrainerOutOfPokemon();
	}

	private Player getPlayerToBattle(ArrayList<Player> trainers, Player boss) {

		for (int i = 0; i < trainers.size(); i++) {
			if (((ComputerPlayer) trainers.get(i)).getDirection().equals(Direction.LEFT)) {
				Player bossGuard = trainers.get(i);
				if (bossGuard.getXPosition() == player.getXPosition() && player.getYPosition() < bossGuard.getYPosition() && player.getYPosition() >= ((ComputerPlayer) bossGuard).getRange()) {
					return trainers.get(i);
				}
			} else if (((ComputerPlayer) trainers.get(i)).getDirection().equals(Direction.RIGHT)) {
				Player bossGuard = trainers.get(i);
				if (bossGuard.getXPosition() == player.getXPosition() && player.getYPosition() > bossGuard.getYPosition() && player.getYPosition() <= ((ComputerPlayer) bossGuard).getRange()) {
					return trainers.get(i);
				}

			} else if (((ComputerPlayer) trainers.get(i)).getDirection().equals(Direction.DOWN)) {
				Player bossGuard = trainers.get(i);
				if (bossGuard.getYPosition() == player.getYPosition() && player.getXPosition() > bossGuard.getXPosition() && player.getXPosition() <= ((ComputerPlayer) bossGuard).getRange()) {
					return trainers.get(i);
				}
			} else {
				Player bossGuard = trainers.get(i);
				if (bossGuard.getYPosition() == player.getYPosition() && player.getXPosition() < bossGuard.getXPosition() && player.getXPosition() >= ((ComputerPlayer) bossGuard).getRange()) {
					return trainers.get(i);
				}
			}
			if (boss != null) {
				if (((ComputerPlayer) boss).getDirection().equals(Direction.LEFT)) {
					if (boss.getXPosition() == player.getXPosition() && player.getYPosition() < boss.getYPosition() && player.getYPosition() >= ((ComputerPlayer) boss).getRange()) {
						return boss;
					}
				} else if (((ComputerPlayer) boss).getDirection().equals(Direction.RIGHT)) {
					if (boss.getXPosition() == player.getXPosition() && player.getYPosition() > boss.getYPosition() && player.getYPosition() <= ((ComputerPlayer) boss).getRange()) {
						return boss;
					}

				} else if (((ComputerPlayer) boss).getDirection().equals(Direction.DOWN)) {
					if (boss.getYPosition() == player.getYPosition() && player.getXPosition() > boss.getXPosition() && player.getXPosition() <= ((ComputerPlayer) boss).getRange()) {
						return boss;
					}
				} else {
					if (boss.getYPosition() == player.getYPosition() && player.getXPosition() < boss.getXPosition() && player.getXPosition() >= ((ComputerPlayer) boss).getRange()) {
						return boss;
					}
				}
			}
		}
		return null;
	}

	private boolean checkBattles(ArrayList<Player> trainers, Player boss) {

		for (int i = 0; i < trainers.size(); i++) {
			if (((ComputerPlayer) trainers.get(i)).hasBattled() == false) {

				if (((ComputerPlayer) trainers.get(i)).getDirection().equals(Direction.LEFT)) {

					Player trainer = trainers.get(i);
					if (trainer.getXPosition() == player.getXPosition() && player.getYPosition() < trainer.getYPosition() && player.getYPosition() >= ((ComputerPlayer) trainer).getRange()) {
						// playerZone.getSquare(player.getXPosition(),
						// player.getYPosition()).removeObject(player);
						// movePlayer(player.getXPosition(),
						// trainer.getYPosition() - 1);
						// return trainer.getBattle();
						return true;
					}
				} else if (((ComputerPlayer) trainers.get(i)).getDirection().equals(Direction.RIGHT)) {
					Player trainer = trainers.get(i);
					if (trainer.getXPosition() == player.getXPosition() && player.getYPosition() > trainer.getYPosition() && player.getYPosition() <= ((ComputerPlayer) trainer).getRange()) {
						// playerZone.getSquare(player.getXPosition(),
						// player.getYPosition()).removeObject(player);
						// movePlayer(player.getXPosition(),
						// trainer.getYPosition() + 1);
						// return trainer.getBattle();
						return true;
					}

				} else if (((ComputerPlayer) trainers.get(i)).getDirection().equals(Direction.DOWN)) {
					Player trainer = trainers.get(i);
					if (trainer.getYPosition() == player.getYPosition() && player.getXPosition() > trainer.getXPosition() && player.getXPosition() <= ((ComputerPlayer) trainer).getRange()) {
						// playerZone.getSquare(player.getXPosition(),
						// player.getYPosition()).removeObject(player);
						// movePlayer(trainer.getXPosition() + 1,
						// player.getYPosition());
						// return trainer.getBattle();
						return true;
					}
				} else {
					Player trainer = trainers.get(i);
					if (trainer.getYPosition() == player.getYPosition() && player.getXPosition() < trainer.getXPosition() && player.getXPosition() >= ((ComputerPlayer) trainer).getRange()) {
						// playerZone.getSquare(player.getXPosition(),
						// player.getYPosition()).removeObject(player);
						// movePlayer(trainer.getXPosition() - 1,
						// player.getYPosition());
						// return trainer.getBattle();
						return true;
					}
				}
			}
		}

		if (boss != null) {

			if (((ComputerPlayer) boss).hasBattled() == false) {

				if (((ComputerPlayer) boss).getDirection().equals(Direction.LEFT)) {
					if (boss.getXPosition() == player.getXPosition() && player.getYPosition() < boss.getYPosition() && player.getYPosition() >= ((ComputerPlayer) boss).getRange()) {
						// playerZone.getSquare(player.getXPosition(),
						// player.getYPosition()).removeObject(player);
						// movePlayer(player.getXPosition(),
						// trainer.getYPosition() - 1);
						// return trainer.getBattle();
						return true;
					}
				} else if (((ComputerPlayer) boss).getDirection().equals(Direction.RIGHT)) {
					if (boss.getXPosition() == player.getXPosition() && player.getYPosition() > boss.getYPosition() && player.getYPosition() <= ((ComputerPlayer) boss).getRange()) {
						// playerZone.getSquare(player.getXPosition(),
						// player.getYPosition()).removeObject(player);
						// movePlayer(player.getXPosition(),
						// trainer.getYPosition() + 1);
						// return trainer.getBattle();
						return true;
					}

				} else if (((ComputerPlayer) boss).getDirection().equals(Direction.DOWN)) {
					if (boss.getYPosition() == player.getYPosition() && player.getXPosition() > boss.getXPosition() && player.getXPosition() <= ((ComputerPlayer) boss).getRange()) {
						// playerZone.getSquare(player.getXPosition(),
						// player.getYPosition()).removeObject(player);
						// movePlayer(trainer.getXPosition() + 1,
						// player.getYPosition());
						// return trainer.getBattle();
						return true;
					}
				} else {
					if (boss.getYPosition() == player.getYPosition() && player.getXPosition() < boss.getXPosition() && player.getXPosition() >= ((ComputerPlayer) boss).getRange()) {
						// playerZone.getSquare(player.getXPosition(),
						// player.getYPosition()).removeObject(player);
						// movePlayer(trainer.getXPosition() - 1,
						// player.getYPosition());
						// return trainer.getBattle();
						return true;
					}
				}
			}
		}

		return false;
	}

	public void saveGame() {
		try {
			FileOutputStream outFile = new FileOutputStream(basedir + "game");
			ObjectOutputStream outputStream = new ObjectOutputStream(outFile);
			outputStream.writeObject(this);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < playerZone.getHeight(); i++) {
			for (int j = 0; j < playerZone.getWidth(); j++) {
				try {
					result = result + playerZone.getSquare(i, j).getRecentObject().toString() + "  ";
				} catch (ArrayIndexOutOfBoundsException e) {
					result = result + " ";
				}
			}
			result = result + "\n";
		}
		// if (checkBattles()) {
		// result = result + battle() + "\n";
		// }
		return result;
	}

}
