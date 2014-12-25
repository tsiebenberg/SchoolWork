package buildingHierarchy;

import java.awt.Image;

public class Dungeon extends Building {

	public Dungeon(int x, int y) {
		super(x, y);
		setBuildingName("Dungeon");
		setDimensions(7, 3);
		setDoorLocation(xLocation + 2, yLocation + 3);
		spriteLocation = new Image[width][height];
	}

	public void setDoorLocation(int x, int y) {
		doorX = x;
		doorY = y;
	}

	@Override
	public String toString() {
		return "C";
	}

}