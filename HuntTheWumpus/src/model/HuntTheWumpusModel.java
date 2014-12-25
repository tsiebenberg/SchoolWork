package model;

import gameMap.GameMap;
import observer.Observable;

/**
 * 
 * @author Nick Gizzi
 * @author Jake Hewitt
 * @author Darshan Kothari
 * @author Taylor Siebenberg
 * 
 */
public class HuntTheWumpusModel extends Observable {

	private boolean won, wumpusDeath, pitDeath;
	private GameMap gm;

	/**
	 * Construct a GameMap with the given number of Pits (In iteration 2 this
	 * will allow difficulty to be changed)
	 * 
	 * @param pits
	 *            - The int number of pits to be used
	 */
	public HuntTheWumpusModel(int pits) {
		pitDeath = false;
		gm = new GameMap(pits);
	}

	public void newGame(int pits) {
		won = false;
		wumpusDeath = false;
		pitDeath = false;
		gm = new GameMap(pits);
	}

	/**
	 * Accepts a string and moves the Hunter in the appropriate direction
	 * 
	 * @param dir
	 *            - The direction string
	 * @return An empty string if no move was performed
	 */
	public String move(String dir) {
		if (!dir.isEmpty()) {
			dir = dir.trim();
			dir = dir.substring(0, 1);
			if (dir.equalsIgnoreCase("l")) {
				return moveLeft();
			} else if (dir.equalsIgnoreCase("r")) {
				return moveRight();
			} else if (dir.equalsIgnoreCase("u")) {
				return moveUp();
			} else if (dir.equalsIgnoreCase("d")) {
				return moveDown();
			}
		}
		return "";
	}

	/**
	 * Moves the Hunter up one space
	 * 
	 * @return Either an empty string, a warning or Death
	 */
	public String moveUp() {
		gm.removeHunter();
		gm.setHunterRow((gm.getHunterRow() + 9) % 10);
		String warning = checkWarning();
		if (deathSequence())
			return "You died!";
		gm.placeHunter();
		return warning;
	}

	/**
	 * Moves the Hunter down one space
	 * 
	 * @return Either an empty string, a warning or Death
	 */
	public String moveDown() {
		gm.removeHunter();
		gm.setHunterRow((gm.getHunterRow() + 11) % 10);
		String warning = checkWarning();
		if (deathSequence())
			return "You died!";
		gm.placeHunter();
		return warning;
	}

	/**
	 * Moves the Hunter left one space
	 * 
	 * @return Either an empty string, a warning or Death
	 */
	public String moveLeft() {
		gm.removeHunter();
		gm.setHunterCol((gm.getHunterCol() + 9) % 10);
		String warning = checkWarning();
		if (deathSequence())
			return "You died!";
		gm.placeHunter();
		return warning;
	}

	/**
	 * Moves the Hunter right one space
	 * 
	 * @return Either an empty string, a warning or Death
	 */
	public String moveRight() {
		gm.removeHunter();
		gm.setHunterCol((gm.getHunterCol() + 11) % 10);
		String warning = checkWarning();
		if (deathSequence())
			return "You died!";
		gm.placeHunter();
		return warning;
	}

	private boolean deathSequence() {
		if (checkDeath()) {
			won = false;
			wumpusDeath = true;
			notifyObservers();
			return true;
		} else if (killedByPit()) {
			won = false;
			pitDeath = true;
			notifyObservers();
			return true;
		}
		return false;
	}

	/**
	 * Sends a warning is the Hunter landed on a flagged Room
	 * 
	 * @return A warning message
	 */
	public String checkWarning() {
		if (gm.getRoom(gm.getHunterRow(), gm.getHunterCol()).getRoomChar() == 'G')
			return "You've stepped in some sort of goop, disgusting!";
		else if (gm.getRoom(gm.getHunterRow(), gm.getHunterCol()).getRoomChar() == 'B')
			return "You see blood on your boots!";
		else if (gm.getRoom(gm.getHunterRow(), gm.getHunterCol()).getRoomChar() == 'S')
			return "You are covered in slime!";
		else
			return "You see nothing!";
	}

	/**
	 * Checks to see if the Hunter landed on the Wumpus or a Pit
	 * 
	 * @return boolean - Whether or not the Hunter is still alive
	 */
	public boolean checkDeath() {
		if (gm.getRoom(gm.getHunterRow(), gm.getHunterCol()).getRoomChar() == 'W')
			return true;
		else
			return false;
	}

	public boolean killedByPit() {
		if (gm.getRoom(gm.getHunterRow(), gm.getHunterCol()).getRoomChar() == 'P')
			return true;
		else
			return false;
	}

	/**
	 * Fires the arrow towards a given direction
	 * 
	 * @param dir
	 *            - String of the direction to shoot the arrow
	 * @return String - Win or Lose message
	 */
	public String fireArrow(String dir) {
		if (!dir.isEmpty()) {
			dir = dir.trim();
			dir = dir.substring(0, 1);
			if (dir.equalsIgnoreCase("l"))
				return fireLeft();
			else if (dir.equalsIgnoreCase("r"))
				return fireRight();
			else if (dir.equalsIgnoreCase("u"))
				return fireUp();
			else if (dir.equalsIgnoreCase("d"))
				return fireDown();

		}
		return "Not a valid direction.";
	}

	/**
	 * Fires the arrow left
	 * 
	 * @return String - Win or Lose message
	 */
	public String fireLeft() {
		if (gm.getHunterRow() == gm.getWumpusRow()
				&& gm.getHunterCol() > gm.getWumpusCol()) {
			won = true;
			wumpusDeath = false;
			notifyObservers();
			return "You win!";
		} else {
			won = false;
			wumpusDeath = true;
			notifyObservers();
			return "You missed and the wumpus found you! \nGame Over";
		}
	}

	/**
	 * Fires the arrow right
	 * 
	 * @return String - Win or Lose message
	 */
	public String fireRight() {
		if (gm.getHunterRow() == gm.getWumpusRow()
				&& gm.getHunterCol() < gm.getWumpusCol()) {
			won = true;
			wumpusDeath = false;
			notifyObservers();
			return "You win!";
		} else {
			won = false;
			wumpusDeath = true;
			notifyObservers();
			return "You missed and the wumpus found you! \nGame Over";
		}
	}

	/**
	 * Fires the arrow up
	 * 
	 * @return String - Win or Lose message
	 */
	public String fireUp() {
		if (gm.getHunterCol() == gm.getWumpusCol()
				&& gm.getHunterRow() > gm.getWumpusRow()) {
			won = true;
			wumpusDeath = false;
			notifyObservers();
			return "You win!";
		} else {
			won = false;
			wumpusDeath = true;
			notifyObservers();
			return "You missed and the wumpus found you! \nGame Over";
		}
	}

	/**
	 * Fires the arrow down
	 * 
	 * @return String - Win or Lose message
	 */
	public String fireDown() {
		if (gm.getHunterCol() == gm.getWumpusCol()
				&& gm.getHunterRow() < gm.getWumpusRow()) {
			won = true;
			wumpusDeath = false;
			notifyObservers();
			return "You win!";
		} else {
			won = false;
			wumpusDeath = true;
			notifyObservers();
			return "You missed and the wumpus found you! \nGame Over";
		}
	}

	/**
	 * Check if the game is over
	 * 
	 * @return boolean - the current state of the game
	 */
	public boolean deathByWumpus() {
		return wumpusDeath;
	}

	public boolean deathByPit() {
		return pitDeath;
	}

	/**
	 * Gets the toString from GameMap
	 * 
	 * @return String - toString of the GameMap
	 */
	@Override
	public String toString() {
		return gm.toString();
	}

	/**
	 * Gets the GameMap
	 * 
	 * @return GameMap - returns the GameMap object
	 */
	public GameMap getGameMap() {
		return gm;
	}

	/**
	 * Calls the GameMap to show all of the Rooms
	 */
	public void showMap() {
		gm.showMap();
	}

	public boolean didWin() {
		return won;
	}

	public boolean gameOver() {
		return (deathByWumpus() || deathByPit() || didWin());
	}

}
