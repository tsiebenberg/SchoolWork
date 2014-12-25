package mapHierarchy;

import java.util.ArrayList;

import playerHierarchy.Player;
import elements.Ground;
import elements.Rock;

public abstract class Zone {

	protected Square[][] squareArray;
	int width, height;
	protected ArrayList<Player> trainers;
	protected ArrayList<Player> bossGuards;

	public Zone(int x, int y) {
		width = y;
		height = x;
		trainers = null;
		bossGuards = new ArrayList<Player>();
		squareArray = new Square[x][y];
		for (int i = 0; i < squareArray.length; i++) {
			for (int j = 0; j < squareArray[0].length; j++) {
				squareArray[i][j] = new Square();
			}
		}
		setMapBorder();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public ArrayList<Player> getTrainers() {
		return trainers;
	}
	
	public ArrayList<Player> getBossGuards() {
		return bossGuards;
	}
	

	private void setMapBorder() {
		for (int i = 0; i < squareArray[0].length; i++) {
			squareArray[0][i].addObject(new Rock());
			squareArray[0][i].setIsWalkable(false);
		}
		for (int i = 1; i < squareArray.length - 1; i++) {
			for (int j = 0; j < squareArray[0].length; j++) {
				if (j == 0 || j == squareArray[0].length - 1) {
					squareArray[i][j].addObject(new Rock());
					squareArray[i][j].setIsWalkable(false);
				} else
					squareArray[i][j].addObject(new Ground());
			}
		}
		for (int i = 0; i < squareArray[0].length; i++) {
			squareArray[squareArray.length - 1][i].addObject(new Rock());
			squareArray[squareArray.length - 1][i].setIsWalkable(false);
		}
	}

	public Square getSquare(int x, int y) {
		return squareArray[x][y];
	}

	public abstract void setMap();

	public abstract void setElements(); // setting all the elements in the Map

	public abstract void setComputerPlayers(); // sets Computer Players and Human Player

	@Override
	public abstract String toString();

}
