package buildingHierarchy;

import java.awt.Image;

public class PokeCenter extends Building {

	private Store store;

	public PokeCenter(int x, int y) {
		super(x, y);
		setBuildingName("Gym");
		setDimensions(3, 3);
		setDoorLocation(xLocation + 2, yLocation + 1);
		spriteLocation = new Image[width][height];

		store = new Store();
	}

	public void setDoorLocation(int x, int y) {
		doorX = x;
		doorY = y;
	}

	@Override
	public String toString() {
		return "P";
	}

}
