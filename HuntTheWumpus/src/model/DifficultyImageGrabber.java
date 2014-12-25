/**
 * @author Jake Hewitt
 */
package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DifficultyImageGrabber {

	private BufferedImage sheet, titleImg;

	public DifficultyImageGrabber() {
		loadImages();
	}

	private void loadImages() {
		try {
			sheet = ImageIO.read(new File("WumpusSprite" + File.separator
					+ "difficultySprites.png"));
			titleImg = ImageIO.read(new File("WumpusSprite" + File.separator
					+ "title.png"));
		} catch (IOException e) {
			System.out.println("Could not find file");
		}
	}

	/*
	 * "Magic Numbers" The upper left starting position of all the subimages
	 */
	private static final int EASY_X = 0, EASY_Y = 120;
	private static final int EASYSELECTED_X = 0, EASYSELECTED_Y = 180;
	private static final int MEDIUM_X = 245, MEDIUM_Y = 60;
	private static final int MEDIUMSELECTED_X = 245, MEDIUMSELECTED_Y = 120;
	private static final int HARD_X = 0, HARD_Y = 240;
	private static final int HARDSELECTED_X = 245, HARDSELECTED_Y = 0;
	private static final int ASIAN_X = 0, ASIAN_Y = 0;
	private static final int ASIANSELECTED_X = 0, ASIANSELECTED_Y = 60;

	private static final int WIDTH = 245, HEIGHT = 60;

	public BufferedImage getEasyImage() {
		return sheet.getSubimage(EASY_X, EASY_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getEasySelectedImage() {
		return sheet.getSubimage(EASYSELECTED_X, EASYSELECTED_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getMediumImage() {
		return sheet.getSubimage(MEDIUM_X, MEDIUM_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getMediumSelectedImage() {
		return sheet.getSubimage(MEDIUMSELECTED_X, MEDIUMSELECTED_Y, WIDTH,
				HEIGHT);
	}

	public BufferedImage getHardImage() {
		return sheet.getSubimage(HARD_X, HARD_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getHardSelectedImage() {
		return sheet.getSubimage(HARDSELECTED_X, HARDSELECTED_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getAsianImage() {
		return sheet.getSubimage(ASIAN_X, ASIAN_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getAsianSelectedImage() {
		return sheet.getSubimage(ASIANSELECTED_X, ASIANSELECTED_Y, WIDTH,
				HEIGHT);
	}

	public BufferedImage getTitleImage() {
		return titleImg;
	}
}
