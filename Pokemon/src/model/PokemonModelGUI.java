package model;

import java.awt.Point;
import java.util.Observable;

public class PokemonModelGUI extends Observable {

	public static final int X_START = 220, Y_START = 300;
	public static final int MOVEMENT_PIXELS = 5;
	public int PLANE_HEIGHT = 950;
	public  int PLANE_WIDTH = 950;

	private int x, y;
	private double xV, yV;

	private int direction;

	public PokemonModelGUI(int height, int width) {
		direction = -1;
		this.PLANE_HEIGHT= height;
		this.PLANE_WIDTH = width;
	}
	
	

	public void setStartX(int x) {
		this.x = x;
		xV = 1;
	}

	public void setStartY(int y) {
		this.y = y;
		yV = 1;
	}

	public Point getLocation() {
		return new Point(x, y);
	}

	public void setDirection(int dir) {
		direction = dir;
	}

	public int getDirection() {
		return direction;
	}

	public void update() {
		System.out.println("(" + x + "," + y + ")");

		super.setChanged();
		super.notifyObservers();
	}

	public void moveUp() {
		if ((y - (MOVEMENT_PIXELS * yV)) < 0) {
			// yV = -yV;
		} else {
			y -= MOVEMENT_PIXELS * yV;
		}
		update();
	}

	public void moveLeft() {
		// TODO Auto-generated method stub
		if ((x - (MOVEMENT_PIXELS * xV)) < 0) {
			// xV = -xV;
		} else {
			x -= MOVEMENT_PIXELS * xV;
		}
		update();
	}

	public void moveRight() {
		// TODO Auto-generated method stub
		if ((x + (MOVEMENT_PIXELS * xV)) > PLANE_WIDTH) {
			// xV = -xV;
		} else {
			x += MOVEMENT_PIXELS * xV;
		}
		update();
	}

	public void moveDown() {
		if ((y + (MOVEMENT_PIXELS * yV)) > PLANE_HEIGHT) {
			// yV = -yV;
		} else {
			y += MOVEMENT_PIXELS * yV;
		}
		update();
	}

}
