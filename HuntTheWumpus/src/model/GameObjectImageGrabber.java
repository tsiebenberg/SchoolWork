/**
 * @author Jake Hewitt
 */
package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameObjectImageGrabber {

	private BufferedImage sheet;

	public GameObjectImageGrabber() {
		loadImages();
	}

	private void loadImages() {
		try {
			sheet = ImageIO.read(new File("WumpusSprite" + File.separator
					+ "gameObjectSpritesheet.png"));

		} catch (IOException e) {
			System.out.println("Could not find 'gameObjectSpritesheet.png'");
		}
	}

	/*
	 * "Magic Numbers" The upper left starting position of all the subimages
	 */
	private static final int BLOOD_X = 0, BLOOD_Y = 0;
	private static final int GOOP_X = 50, GOOP_Y = 0;
	private static final int GROUND_X = 0, GROUND_Y = 50;
	private static final int HIDDEN_GROUND_X = 50, HIDDEN_GROUND_Y = 100;
	private static final int SLIME_X = 50, SLIME_Y = 50;
	private static final int PIT_X = 100, PIT_Y = 0;
	private static final int HUNTER_X = 100, HUNTER_Y = 50;
	private static final int WUMPUS_X = 0, WUMPUS_Y = 100;
	private static final int WIDTH = 50, HEIGHT = 50;

	public BufferedImage getBloodImage() {
		return sheet.getSubimage(BLOOD_X, BLOOD_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getGoopImage() {
		return sheet.getSubimage(GOOP_X, GOOP_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getGroundImage() {
		return sheet.getSubimage(GROUND_X, GROUND_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getHiddenGroundImage() {
		return sheet.getSubimage(HIDDEN_GROUND_X, HIDDEN_GROUND_Y, WIDTH,
				HEIGHT);
	}

	public BufferedImage getSlimeImage() {
		return sheet.getSubimage(SLIME_X, SLIME_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getPitImage() {
		return sheet.getSubimage(PIT_X, PIT_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getHunterImage() {
		return sheet.getSubimage(HUNTER_X, HUNTER_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getWumpusImage() {
		return sheet.getSubimage(WUMPUS_X, WUMPUS_Y, WIDTH, HEIGHT);
	}

}
