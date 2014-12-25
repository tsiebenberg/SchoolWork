package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import animations.BattleAnimation;
import mapHierarchy.DungeonZone;
import mapHierarchy.GymZone;
import mapHierarchy.MainZone;
import model.PokemonModel;
import model.SpriteManager;
import playerHierarchy.Direction;

public class MainZoneView extends JPanel {

	private Graphics2D g2;
	private PokemonModel model;
	private SpriteManager imgGrab;
	private int currentFrameX, currentFrameY;
	private int map_Offset_X, map_Offset_Y;
	private int iteration;
	private Timer timer;
	private boolean running;
	int counter = 0;
	BufferedImage[] trainerImages;
	Direction facing;
	BattleAnimation ba;

	public MainZoneView(PokemonModel model) {
		this.model = model;
		this.setLayout(null);
		imgGrab = new SpriteManager();
		iteration = 0;
		currentFrameX = 0;
		currentFrameY = 0;
		map_Offset_X = 0;
		map_Offset_Y = 0;
		this.setOpaque(false);
		trainerImages = new BufferedImage[3];
		moveDownAnimation();
		running = false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		drawMap();
		animatePlayer();
	}

	public void drawMap() {

		// Draws ground and border rocks
		for (int i = -8; i < model.getMainZone().getWidth() + 8; i++) {
			for (int j = -8; j < model.getMainZone().getHeight() + 8; j++) {
				g2.drawImage(imgGrab.getPath1(), j * 50 + map_Offset_X + currentFrameX, i * 50 + map_Offset_Y + currentFrameY, null);
				if ((i <= 0 || i >= model.getMainZone().getWidth()))
					g2.drawImage(imgGrab.getRock(), j * 50 + map_Offset_X + currentFrameX, i * 50 + map_Offset_Y + currentFrameY, null);
				if ((j <= 0 || j >= model.getMainZone().getHeight()))
					g2.drawImage(imgGrab.getRock(), j * 50 + map_Offset_X + currentFrameX, i * 50 + map_Offset_Y + currentFrameY, null);
			}
		}

		// drawing elements in map
		boolean gymDrawn = false;
		boolean pokeDrawn = false;
		boolean caveDrawn = false;
		for (int i = 0; i < model.getMainZone().getWidth(); i++) {
			for (int j = 0; j < model.getPlayerZone().getHeight(); j++) {

				if (model.getMainZone().getSquare(i, j).getRecentObject().toString().equals("X")) {
					g2.drawImage(imgGrab.getRock(), j * 50 + map_Offset_X + currentFrameX, i * 50 + map_Offset_Y + currentFrameY, null);
				}
				if (model.getMainZone().getSquare(i, j).hasGrass()) {
					g2.drawImage(imgGrab.getGrass(), j * 50 + map_Offset_X + currentFrameX, i * 50 + map_Offset_Y + currentFrameY, null);
				}
				if (model.getMainZone().getSquare(i, j).getRecentObject().toString().equals("A")) {
					g2.drawImage(imgGrab.getOldMan(), j * 50 + map_Offset_X + currentFrameX, i * 50 + map_Offset_Y + currentFrameY, null);
				}

				if (model.getMainZone().getSquare(i, j).getRecentObject().toString().equals("/")) {
					g2.drawImage(imgGrab.getWater(), j * 50 + map_Offset_X + currentFrameX, i * 50 + map_Offset_Y + currentFrameY, null);
				}
				if (model.getMainZone().getSquare(i, j).getRecentObject().toString().equals("T")) {
					if (model.getMainZone().getSquare(i, j).getTrainer().getDirection().equals(Direction.DOWN)) {
						g2.drawImage(imgGrab.getTrainer1_Forward(), j * 50 + map_Offset_X + currentFrameX, i * 50 + map_Offset_Y + currentFrameY, null);
					}
					if (model.getMainZone().getSquare(i, j).getTrainer().getDirection().equals(Direction.UP)) {
						g2.drawImage(imgGrab.getTrainer2_Back(), j * 50 + map_Offset_X + currentFrameX, i * 50 + map_Offset_Y + currentFrameY, null);
					}
					if (model.getMainZone().getSquare(i, j).getTrainer().getDirection().equals(Direction.RIGHT)) {
						g2.drawImage(imgGrab.getTrainer1_Right(), j * 50 + map_Offset_X + currentFrameX, i * 50 + map_Offset_Y + currentFrameY, null);
					}
					if (model.getMainZone().getSquare(i, j).getTrainer().getDirection().equals(Direction.LEFT)) {
						g2.drawImage(imgGrab.getTrainer2_Left(), j * 50 + map_Offset_X + currentFrameX, i * 50 + map_Offset_Y + currentFrameY, null);
					}

				}
				if (model.getMainZone().getSquare(i, j).getRecentObject().toString().equals("G") && gymDrawn == false) {
					g2.drawImage(imgGrab.getGym(), j * 50 + map_Offset_X + currentFrameX, i * 50 + map_Offset_Y + currentFrameY, null);
					gymDrawn = true;
				}
				if (model.getMainZone().getSquare(i, j).getRecentObject().toString().equals("P") && pokeDrawn == false) {
					g2.drawImage(imgGrab.getPokeCenter(), j * 50 + map_Offset_X + currentFrameX, i * 50 + map_Offset_Y + currentFrameY, null);
					pokeDrawn = true;
				}
				if (model.getMainZone().getSquare(i, j).getRecentObject().toString().equals("C") && caveDrawn == false) {
					g2.drawImage(imgGrab.getcaveEntrance(), j * 50 + map_Offset_X + currentFrameX, i * 50 + map_Offset_Y + currentFrameY, null);
					caveDrawn = true;
				}
			}
		}
	}

	public void animatePlayer() {
		if (iteration == 10 || iteration == 0) {
			g2.drawImage(trainerImages[2], 350, 350, null);
			iteration = 0;
		} else
			g2.drawImage(trainerImages[iteration % 2], 350, 350, null);
	}

	public void move(char key, boolean real) {
		if (running)
			return;
		if (key == 'w') {
			facing = Direction.UP;
			moveUpAnimation();
			map_Offset_X = 0;
			map_Offset_Y = 5;
		} else if (key == 's') {
			facing = Direction.DOWN;
			moveDownAnimation();
			map_Offset_X = 0;
			map_Offset_Y = -5;
		} else if (key == 'a') {
			facing = Direction.LEFT;
			moveLeftAnimation();
			map_Offset_X = 5;
			map_Offset_Y = 0;
		} else if (key == 'd') {
			System.out.println("balls");
			facing = Direction.RIGHT;
			moveRightAnimation();
			map_Offset_X = -5;
			map_Offset_Y = 0;
		} else
			return;
		if (real && !model.move(key + "")) {
			map_Offset_X = 0;
			map_Offset_Y = 0;
		}
		if (model.checkBattles() == true && model.player.canFight() == true) {
			ba = new BattleAnimation(model.getPlayer(), model.getPlayerToBattle(), model);
			ba.setVisible(true);
//		} else {
//			model.checkWildPokemonBattle();
		}
		running = true;
		startPlayerAnimation();
		// System.out.println(model.getPlayerZone());
		return;
	}

	public void moveUpAnimation() {
		trainerImages[2] = imgGrab.getWalkingUp();
		trainerImages[1] = imgGrab.getWalkingUpLeft();
		trainerImages[0] = imgGrab.getWalkingUpRight();
	}

	public void moveLeftAnimation() {
		trainerImages[2] = imgGrab.getWalkingLeft();
		trainerImages[1] = imgGrab.getWalkingLeftLeft();
		trainerImages[0] = imgGrab.getWalkingLeftRight();
	}

	public void moveRightAnimation() {
		trainerImages[2] = imgGrab.getWalkingRight();
		trainerImages[1] = imgGrab.getWalkingRightLeft();
		trainerImages[0] = imgGrab.getWalkingRightRight();
	}

	public void moveDownAnimation() {
		trainerImages[2] = imgGrab.getWalkingDown();
		trainerImages[1] = imgGrab.getWalkingDownLeft();
		trainerImages[0] = imgGrab.getWalkingDownRight();
	}

	private void startPlayerAnimation() {
		timer = new Timer(1000 / 32, new PlayerAnimation());
		timer.start();
	}

	private class PlayerAnimation implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			iteration++;
			currentFrameX += map_Offset_X;
			currentFrameY += map_Offset_Y;
			repaint();
			if (iteration == 10) {
				timer.stop();
				running = false;
				map_Offset_X = 0;
				map_Offset_X = 0;
				model.checkWildPokemonBattle();
				// System.out.println("(" + model.getPlayer().getXPosition() +
				// ", " + model.getPlayer().getYPosition() + ")");
			}
		}
	}
}
