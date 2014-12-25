package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpriteManager {
	
	private BufferedImage playerSheet, pokemonSheet, trainerSheet, trainerSheetLarge, groundSheet,
			buildingSheet, pokeCenterSheet, gymTile, worldTrainers, oldMan, developers;

	public HashMap<String, BufferedImage> trainerImages;
	ArrayList<BufferedImage> pokemonImages;
	ArrayList<BufferedImage> groundImages;

	public SpriteManager() {
		pokemonImages = new ArrayList<BufferedImage>();
		groundImages = new ArrayList<BufferedImage>();
		loadImages();
		fetchPokemonImages();
		fetchGroundImages();
	}

	public HashMap<String, BufferedImage> getImages() {
		// add other sheets too if needed.
		return trainerImages;
	}

	private void loadImages() {
		try {
			playerSheet = ImageIO.read(new File("Sprites" + File.separator + "AshSprites.png"));
			pokemonSheet = ImageIO.read(new File("Sprites" + File.separator + "PokemonSprites.png"));
			trainerSheet = ImageIO.read(new File("Sprites" + File.separator + "TrainerSprites.png"));
			trainerSheetLarge = ImageIO.read(new File("Sprites" + File.separator + "TrainerSpritesBIG.png"));
			groundSheet = ImageIO.read(new File("Sprites"+File.separator + "GroundSprites.png"));
			buildingSheet = ImageIO.read(new File("Sprites" + File.separator + "BuildingSprites.png"));
			pokeCenterSheet = ImageIO.read(new File("Sprites" + File.separator + "PokeCenterSprites.png"));
			gymTile = ImageIO.read(new File("Sprites" + File.separator + "gymTile.png"));
			worldTrainers = ImageIO.read(new File("Sprites"+ File.separator +"worldTrainersSprites.png"));
			oldMan = ImageIO.read(new File("Sprites"+ File.separator +"oldman.png"));
			developers = ImageIO.read(new File("Sprites"+ File.separator +"developers.png"));


		} catch (IOException e) {
			System.out.println("Could not find file.");
		}
	}

	/**
	 * Gets the images from the player spritesheet
	 * 
	 * "Magic Numbers" The upper left starting position of all the subimages of
	 * the player
	 */

	private static final int WIDTH = 50, HEIGHT = 50;

	private static final int WALKING_UP_X = 0, WALKING_UP_Y = 0;
	private static final int WALKING_UPLEFT_X = WIDTH, WALKING_UPLEFT_Y = 0;
	private static final int WALKING_UPRIGHT_X = WIDTH * 2, WALKING_UPRIGHT_Y = 0;

	private static final int WALKING_DOWN_X = 0, WALKING_DOWN_Y = 50;
	private static final int WALKING_DOWNLEFT_X = WIDTH * 2, WALKING_DOWNLEFT_Y = HEIGHT;
	private static final int WALKING_DOWNRIGHT_X = WIDTH, WALKING_DOWNRIGHT_Y = HEIGHT;

	private static final int WALKING_LEFT_X = 0, WALKING_LEFT_Y = HEIGHT * 2;
	private static final int WALKING_LEFTLEFT_X = WIDTH, WALKING_LEFTLEFT_Y = HEIGHT * 2;
	private static final int WALKING_LEFTRIGHT_X = WIDTH * 2, WALKING_LEFTRIGHT_Y = HEIGHT * 2;

	private static final int WALKING_RIGHT_X = 0, WALKING_RIGHT_Y = HEIGHT * 3;
	private static final int WALKING_RIGHTLEFT_X = WIDTH * 2, WALKING_RIGHTLEFT_Y = HEIGHT * 3;
	private static final int WALKING_RIGHTRIGHT_X = WIDTH, WALKING_RIGHTRIGHT_Y = HEIGHT * 3;

	// Player up images
	public BufferedImage getWalkingUp() {
		return playerSheet.getSubimage(WALKING_UP_X, WALKING_UP_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getWalkingUpLeft() {
		return playerSheet.getSubimage(WALKING_UPLEFT_X, WALKING_UPLEFT_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getWalkingUpRight() {
		return playerSheet.getSubimage(WALKING_UPRIGHT_X, WALKING_UPRIGHT_Y, WIDTH, HEIGHT);
	}

	// Player down images
	public BufferedImage getWalkingDown() {
		return playerSheet.getSubimage(WALKING_DOWN_X, WALKING_DOWN_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getWalkingDownLeft() {
		return playerSheet.getSubimage(WALKING_DOWNLEFT_X, WALKING_DOWNLEFT_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getWalkingDownRight() {
		return playerSheet.getSubimage(WALKING_DOWNRIGHT_X, WALKING_DOWNRIGHT_Y, WIDTH, HEIGHT);
	}

	// Player left images
	public BufferedImage getWalkingLeft() {
		return playerSheet.getSubimage(WALKING_LEFT_X, WALKING_LEFT_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getWalkingLeftLeft() {
		return playerSheet.getSubimage(WALKING_LEFTLEFT_X, WALKING_LEFTLEFT_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getWalkingLeftRight() {
		return playerSheet.getSubimage(WALKING_LEFTRIGHT_X, WALKING_LEFTRIGHT_Y, WIDTH, HEIGHT);
	}

	// Player right images
	public BufferedImage getWalkingRight() {
		return playerSheet.getSubimage(WALKING_RIGHT_X, WALKING_RIGHT_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getWalkingRightLeft() {
		return playerSheet.getSubimage(WALKING_RIGHTLEFT_X, WALKING_RIGHTLEFT_Y, WIDTH, HEIGHT);
	}

	public BufferedImage getWalkingRightRight() {
		return playerSheet.getSubimage(WALKING_RIGHTRIGHT_X, WALKING_RIGHTRIGHT_Y, WIDTH, HEIGHT);
	}

	/**
	 * Gets the images from the trainer spritesheet
	 * 
	 * "Magic Numbers" The upper left starting position of all the subimages of
	 * the trainer
	 */

	private static final int GARY_X = 4, GARY_Y = 5;

	public BufferedImage getGARY() {
		return trainerSheet.getSubimage(GARY_X * WIDTH, GARY_Y * HEIGHT, WIDTH, HEIGHT);
	}

	public BufferedImage getBigAsh() {
		return trainerSheetLarge.getSubimage(GARY_X * WIDTH, GARY_Y * HEIGHT, 250, 250);
	}

	/**
	 * Gets the images from the pokemon spritesheet
	 * 
	 * "Magic Numbers" The upper left starting position of all the subimages of
	 * the pokemon
	 */

	private void fetchPokemonImages() {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 10; j++)
				pokemonImages.add(pokemonSheet.getSubimage(j * 240, i * 240, 240, 240));
		}
	}

	public BufferedImage getPokemonImage(int id) {
		return pokemonImages.get(id-1);
	}

	/**
	 * Gets the images from the world spritesheet
	 * 
	 * "Magic Numbers" The upper left starting position of all the subimages of
	 * the pokemon
	 */

	private void fetchGroundImages() {
		for (int i = 0; i < 5; i++)
			groundImages.add(groundSheet.getSubimage(i * 50, 0, 50, 50));
	}

	public BufferedImage getRock() {
		return groundImages.get(3);
	}

	public BufferedImage getPath1() {
		return groundImages.get(2);
	}

	public BufferedImage getPath2() {
		return groundImages.get(0);
	}

	public BufferedImage getGrass() {
		return groundImages.get(1);
	}

	public BufferedImage getWater() {
		return groundImages.get(4);
	}

	public BufferedImage getcaveEntrance() {
		return buildingSheet.getSubimage(0, 0, 350, 150);
	}

	public BufferedImage getPokeCenter() {
		return buildingSheet.getSubimage(700, 0, 150, 150);
	}

	public BufferedImage getGym() {
		return buildingSheet.getSubimage(350, 0, 350, 150);
	}

	/**
	 * Gets the images from the poke center spritesheet
	 * 
	 * "Magic Numbers" The upper left starting position of all the subimages of
	 * the pokemon
	 */

	private static final int pc_x = 0, pc_y = 0, pc_width = 26, pc_height = 50;
	private static final int tile_x = 27, tile_y = 0, tile_width = 50, tile_height = 50;
	private static final int blackTile_x = 77, blackTile_y = 0, blackTile_width = 50,
			blackTile_height = 50;
	private static final int nurseJoy_x = 127, nurseJoy_y = 0, nurseJoy_width = 50,
			nurseJoy_height = 50;
	private static final int sideDesk_x = 177, sideDesk_y = 0, sideDesk_width = 26,
			sideDesk_height = 50;
	private static final int desk_x = 0, desk_y = 50, desk_width = 150, desk_height = 50;
	private static final int doormat_x = 0, doormat_y = 100, doormat_width = 22,
			doormat_height = 13;
	private static final int healingSta_x = 50, healingSta_y = 100, healingSta_width = 50,
			healingSta_height = 50;
	private static final int mart_x = 100, mart_y = 100, mart_width = 50, mart_height = 50;

	public BufferedImage getPc() {
		return pokeCenterSheet.getSubimage(pc_x, pc_y, pc_width, pc_height);
	}

	public BufferedImage getTile() {
		return pokeCenterSheet.getSubimage(tile_x, tile_y, tile_width, tile_height);
	}

	public BufferedImage getBlackTile() {
		return pokeCenterSheet.getSubimage(blackTile_x, blackTile_y, blackTile_width, blackTile_height);
	}

	public BufferedImage getNurseJoy() {
		return pokeCenterSheet.getSubimage(nurseJoy_x, nurseJoy_y, nurseJoy_width, nurseJoy_height);
	}

	public BufferedImage getSideDesk() {
		return pokeCenterSheet.getSubimage(sideDesk_x, sideDesk_y, sideDesk_width, sideDesk_height);
	}

	public BufferedImage getDesk() {
		return pokeCenterSheet.getSubimage(desk_x, desk_y, desk_width, desk_height);
	}

	public BufferedImage getDoorMat() {
		return pokeCenterSheet.getSubimage(doormat_x, doormat_y, doormat_width, doormat_height);
	}

	public BufferedImage getHealingSta() {
		return pokeCenterSheet.getSubimage(healingSta_x, healingSta_y, healingSta_width, healingSta_height);
	}

	public BufferedImage getMart() {
		return pokeCenterSheet.getSubimage(mart_x, mart_y, mart_width, mart_height);
	}
	public BufferedImage getGymTile(){
		return gymTile;
	}

	
	
	
	/**
	 * Gets the images from the world trainers spritesheet
	 * 
	 * "Magic Numbers" The upper left starting position of all the subimages of
	 * the trainers
	 */
	
	private static final int bossBack_x = 0, bossBack_y = 0;
	private static final int bossForward_x = 50, bossForward_y = 0;
	private static final int bossLeft_x = 100, bossLeft_y = 0;
	private static final int bossRight_x = 0, bossRight_y = 50;
	private static final int trainer1_Forward_x = 150, trainer1_Forward_y = 0;
	private static final int trainer1_Left_x = 150, trainer1_Left_y = 50;
	private static final int trainer1_Back_x = 100, trainer1_Back_y = 100;
	private static final int trainer1_Right_x = 150, trainer1_Right_y = 100;
	private static final int trainer2_Forward_x = 100, trainer2_Forward_y = 50;
	private static final int trainer2_Back_x = 50, trainer2_Back_y = 50;
	private static final int trainer2_Right_x = 50, trainer2_Right_y = 100;
	private static final int trainer2_Left_x = 0, trainer2_Left_y = 100;

	
	
	public BufferedImage getBossBack(){
		return worldTrainers.getSubimage(bossBack_x, bossBack_y, WIDTH, HEIGHT);
	}
	public BufferedImage getBossForward(){
		return worldTrainers.getSubimage(bossForward_x, bossForward_y, WIDTH, HEIGHT);
	}
	public BufferedImage getBossLeft(){
		return worldTrainers.getSubimage(bossLeft_x, bossLeft_y, WIDTH, HEIGHT);
	}
	public BufferedImage getBossRight(){
		return worldTrainers.getSubimage(bossRight_x, bossRight_y, WIDTH, HEIGHT);
	}
	
	
	public BufferedImage getTrainer1_Forward(){
		return worldTrainers.getSubimage(trainer1_Forward_x, trainer1_Forward_y, WIDTH, HEIGHT);
	}
	public BufferedImage getTrainer1_Left(){
		return worldTrainers.getSubimage(trainer1_Left_x, trainer1_Left_y, WIDTH, HEIGHT);
	}
	public BufferedImage getTrainer1_Back(){
		return worldTrainers.getSubimage(trainer1_Back_x, trainer1_Back_y, WIDTH, HEIGHT);
	}
	public BufferedImage getTrainer1_Right(){
		return worldTrainers.getSubimage(trainer1_Right_x, trainer1_Right_y, WIDTH, HEIGHT);
	}

	public BufferedImage getTrainer2_Forward(){
		return worldTrainers.getSubimage(trainer2_Forward_x, trainer2_Forward_y, WIDTH, HEIGHT);
	}
	public BufferedImage getTrainer2_Left(){
		return worldTrainers.getSubimage(trainer2_Left_x, trainer2_Left_y, WIDTH, HEIGHT);
	}
	public BufferedImage getTrainer2_Back(){
		return worldTrainers.getSubimage(trainer2_Back_x, trainer2_Back_y, WIDTH, HEIGHT);
	}
	public BufferedImage getTrainer2_Right(){
		return worldTrainers.getSubimage(trainer2_Right_x, trainer2_Right_y, WIDTH, HEIGHT);
	}
	
	//Oldman sprite getter
	
	public BufferedImage getOldMan(){
		return oldMan;
	}
	
	public BufferedImage getDevelopers(){
		return developers;
	}










	
}
