package buildingHierarchy;

import java.awt.Image;

public abstract class Building {

	protected Image[][] spriteLocation;
	protected int width, height;
	protected String buildingName;
	protected int xLocation;
	protected int yLocation;
	protected char buildingChar;
	protected int doorX;
	protected int doorY;

	public Building(int x, int y) {
		xLocation = x;
		yLocation = y;
	}
	
	public int getDoorX() {
		return doorX;
	}
	
	public int getDoorY() {
		return doorY;
	}

	public void setBuildingName(String name) {
		buildingName = name;
	}

	public void setDimensions(int x, int y) {
		width = x;
		height = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public abstract String toString();

}
