package gameMap;

import gameObjects.Blood;
import gameObjects.Goop;
import gameObjects.Hunter;
import gameObjects.Pit;
import gameObjects.Slime;
import gameObjects.Wumpus;

import java.util.Random;

/**
 * 
 * @author Nick Gizzi
 * @author Jake Hewitt
 * @author Darshan Kothari
 * @author Taylor Siebenberg
 * 
 */
public class GameMap {

	private Room[][] mapArray;
	private int numberOfPits;
	private Random rand = new Random();
	private int hunterX, hunterY;
	private int wumpusX, wumpusY;
	private Hunter hunter;

	/**
	 * Initialize the GameMap to have the given number of Pits.
	 * 
	 * @param pits
	 *            - number of pits to be placed
	 */
	public GameMap(int pits) {
		mapArray = new Room[10][10];
		this.numberOfPits = pits;
		hunter = new Hunter();
		newGame();
	}

	/**
	 * Sets all every Room element in the array to a new Room object.
	 */
	public void initializeGame() {
		for (int i = 0; i < mapArray.length; i++) {
			for (int j = 0; j < mapArray[i].length; j++) {
				mapArray[i][j] = new Room();
			}
		}
	}

	/**
	 * Resets all the Rooms then Replaces all the objects
	 */
	private void newGame() {
		initializeGame();
		setObjects();
	}

	/**
	 * Places all of the GameObjects, order is crucial. Sets the Pits first,
	 * followed by the Wumpus, then the flags (ie: Blood, Slime, and Goop), and
	 * lastly the Hunter
	 */
	private void setObjects() {
		placePits();
		placeWumpus();
		setFlags();
		safePlaceHunter();
	}

	/**
	 * Places the pits to random empty locations
	 */
	private void placePits() {
		int row = 0;
		int col = 0;
		for (int i = 0; i < numberOfPits; i++) {
			row = rand.nextInt(10);
			col = rand.nextInt(10);
			if (mapArray[row][col].isEmpty())
				mapArray[row][col].addGameObject(new Pit());
			else
				i--;
		}
	}

	/**
	 * Places the wumpus to a random empty location then sets the Wumpus X and Y
	 * coordinates
	 */
	private void placeWumpus() {

		int row = rand.nextInt(10);
		int col = rand.nextInt(10);

		while (!mapArray[row][col].isEmpty()) {
			row = rand.nextInt(10);
			col = rand.nextInt(10);
		}

		mapArray[row][col].addGameObject(new Wumpus());
		wumpusX = row;
		wumpusY = col;

	}

	/**
	 * Finds the first random safe place to put the hunter then sets the Hunter
	 * X and Y coordinates and places the Hunter
	 */
	private void safePlaceHunter() {
		int row = rand.nextInt(10);
		int col = rand.nextInt(10);
		while (!mapArray[row][col].isEmpty()) {
			row = rand.nextInt(10);
			col = rand.nextInt(10);
		}
		setHunterRow(row);
		setHunterCol(col);
		placeHunter();
	}

	/**
	 * Sets all the flag objects (ie : Blood, Slime, and Goop)
	 */
	private void setFlags() {
		// Set the Pits flags
		for (int i = 0; i < mapArray.length; i++) {
			for (int j = 0; j < mapArray[0].length; j++) {
				setPitFlags(i, j);
			}
		}
		// Sets the Wumpus flags
		for (int i = 0; i < mapArray.length; i++) {
			for (int j = 0; j < mapArray[0].length; j++) {
				setWumpusFlags(i, j);
			}
		}
	}

	/**
	 * Helper method for setFlags. If the position in the GampMap is a Pit, set
	 * adjacent rooms to Slime
	 * 
	 * @param row
	 *            - Row on GameBoard
	 * @param col
	 *            - Column on GameBoard
	 */
	private void setPitFlags(int row, int col) {
		if (mapArray[row][col].getRoomChar() == 'P') {
			for (int i = row - 1; i < row + 2; i++) {
				for (int j = col - 1; j < col + 2; j++) {
					if (i == row || j == col) {
						Room roomCheck = mapArray[(i + mapArray.length)
								% mapArray.length][(j + mapArray[0].length)
								% mapArray[0].length];
						if (roomCheck.isEmpty())
							mapArray[(i + mapArray.length) % mapArray.length][(j + mapArray[0].length)
									% mapArray[0].length]
									.addGameObject(new Slime());
					}
				}
			}
		}
	}

	/**
	 * Helper method for setFlags. If the position in the GampMap is the Wumpus,
	 * set all rooms that are two moves away to Blood, if one of those rooms is
	 * already a Slime, remove the Slime object and set it to Goop.
	 * 
	 * @param row
	 *            - Row on GameBoard
	 * @param col
	 *            - Column on GameBoard
	 */
	private void setWumpusFlags(int row, int col) {
		if (mapArray[row][col].getRoomChar() == 'W') {
			for (int i = row - 2; i <= row + 2; i++) {
				for (int j = col - 2; j <= col + 2; j++) {
					if (i == row
							|| j == col
							|| (Math.abs(row - i) == 1 && Math.abs(col - j) == 1)) {
						int formRow = (i + mapArray.length) % mapArray.length;
						int formCol = (j + mapArray[0].length)
								% mapArray[0].length;
						if (mapArray[formRow][formCol].isEmpty()) {
							mapArray[formRow][formCol]
									.addGameObject(new Blood());
						} else {
							if (mapArray[formRow][formCol].getRoomChar() == 'S') {
								mapArray[formRow][formCol]
										.removeGameObject(new Slime());
								mapArray[formRow][formCol]
										.addGameObject(new Goop());
							}
						}
					}
				}
			}
		}
	}

	// This is the code for only having adjacent squares be Blood rather than
	// two squares away
	//
	// for (int i = row - 1; i < row + 2; i++) {
	// for (int j = col - 1; j < col + 2; j++) {
	// if (i == row || j == col) {
	// int formRow = (i + mapArray.length) % mapArray.length;
	// int formCol = (j + mapArray[0].length) % mapArray[0].length;
	// if (mapArray[formRow][formCol].isEmpty()) {
	// mapArray[formRow][formCol].addGameObject(new Blood());
	// } else {
	// if (mapArray[formRow][formCol].getRoomChar() == 'S') {
	// mapArray[formRow][formCol].removeGameObject(new Slime());
	// mapArray[formRow][formCol].addGameObject(new Goop());
	// }
	// }
	//
	// }
	// }

	/**
	 * Converts the GameBoard into a String representaion
	 */
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < mapArray.length; i++) {
			for (int j = 0; j < mapArray[i].length; j++) {
				result += '[';
				if (mapArray[i][j].getRoomChar() == 'O')
					result += mapArray[i][j].getRoomChar();
				else if (!mapArray[i][j].getVisiblity())
					result += 'X';
				else if (mapArray[i][j].isEmpty())
					result += " ";
				else
					result += mapArray[i][j].getRoomChar();
				result += ']';
			}
			result += "\n";
		}
		return result;
	}

	/**
	 * Replaces the hunter. Must set the HunterRow or HunterCol first to have
	 * any effect
	 */
	public void placeHunter() {
		mapArray[hunterX][hunterY].setVisible();
		mapArray[hunterX][hunterY].addGameObject(hunter);
	}

	/**
	 * Removes the hunter from the current position
	 */
	public void removeHunter() {
		mapArray[hunterX][hunterY].removeGameObject(hunter);
	}

	/**
	 * Get the current row of the Hunter
	 * 
	 * @return int - The row of the Hunters location
	 */
	public int getHunterRow() {
		return hunterX;
	}

	/**
	 * Get the current column of the Hunter
	 * 
	 * @return int - The column of the Hunters location
	 */
	public int getHunterCol() {
		return hunterY;
	}

	/**
	 * Set the hunter row to the int passed in by the parameter
	 * 
	 * @param row
	 *            - Take in an int of the Hunters new row location
	 */
	public void setHunterRow(int row) {
		hunterX = row;
	}

	/**
	 * Set the hunter column to the int passed in by the parameter
	 * 
	 * @param col
	 *            - Take in an int of the Hunters new column location
	 */
	public void setHunterCol(int col) {
		hunterY = col;
	}

	/**
	 * Get the current row of the Wumpus
	 * 
	 * @return int - The row of the Wumpus location
	 */
	public int getWumpusRow() {
		return wumpusX;
	}

	/**
	 * Get the current column of the Wumpus
	 * 
	 * @return int - The column of the Wumpus location
	 */
	public int getWumpusCol() {
		return wumpusY;
	}

	/**
	 * Given an row and column, return the Room at the location
	 * 
	 * @param row
	 *            - int
	 * @param column
	 *            - int
	 * @return Room - The Room at the given (row, col) coordinate
	 */
	public Room getRoom(int row, int column) {
		return mapArray[row][column];
	}

	/**
	 * Gets the GampMap 2d array
	 * 
	 * @return Room[][] - A 2d array of all the rooms
	 */
	public Room[][] getMapArray() {
		return mapArray;
	}

	/**
	 * Gets the number of pits in the game
	 * 
	 * @return int - Number of Pits
	 */
	public int getNumberOfPits() {
		return numberOfPits;
	}

	/**
	 * Sets all the Rooms to visible
	 */
	public void showMap() {
		for (int i = 0; i < mapArray.length; i++) {
			for (int j = 0; j < mapArray[i].length; j++) {
				mapArray[i][j].setVisible();
			}

		}
	}
}
