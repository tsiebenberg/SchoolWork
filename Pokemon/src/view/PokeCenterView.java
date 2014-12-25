package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import playerHierarchy.Direction;
import model.PokemonModel;
import model.SpriteManager;

public class PokeCenterView extends JPanel {
	
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

	public PokeCenterView(PokemonModel model) {
		this.model = model;
		this.setLayout(null);
		imgGrab = new SpriteManager();
		iteration = 0;
		currentFrameX = 3 * 50;
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
		//paints base layer
		for (int i = 0; i < model.getPlayerZone().getHeight(); i++) {
			for (int j = 0; j < model.getPlayerZone().getWidth(); j++) {
				g2.drawImage(imgGrab.getTile(), j*50 + map_Offset_X + currentFrameX, i*50 + map_Offset_Y + currentFrameY, null);
			}
		}

		boolean pokeCenterDrawn = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 10; j++) {
				if (model.getPlayerZone().getSquare(i, j).getRecentObject().toString().equals("X")) {
					g2.drawImage(imgGrab.getBlackTile(), j*50 + map_Offset_X + currentFrameX, i*50 + map_Offset_Y + currentFrameY, null);

				}
				if (model.getPlayerZone().getSquare(i, j).getRecentObject().toString().equals("S")) {
					g2.drawImage(imgGrab.getMart(), j*50 + map_Offset_X + currentFrameX, i*50 + map_Offset_Y + currentFrameY, null);

				}
				if (model.getPlayerZone().getSquare(i, j).getRecentObject().toString().equals("T")) {
					g2.drawImage(imgGrab.getNurseJoy(), j*50 + map_Offset_X + currentFrameX, i*50 + map_Offset_Y + currentFrameY, null);

				}
				if (model.getPlayerZone().getSquare(i, j).getRecentObject().toString().equals("L")) {
					g2.drawImage(imgGrab.getSideDesk(), j*50 + map_Offset_X + currentFrameX, i*50 + map_Offset_Y + currentFrameY, null);

				}
				if (model.getPlayerZone().getSquare(i, j).getRecentObject().toString().equals("R")) {
					g2.drawImage(imgGrab.getSideDesk(), (j*50 + map_Offset_X + currentFrameX)+25, i*50 + map_Offset_Y + currentFrameY, null);

				}
				if (model.getPlayerZone().getSquare(i, j).getRecentObject().toString().equals("F") && pokeCenterDrawn==false) {
					g2.drawImage(imgGrab.getDesk(), j*50 + map_Offset_X + currentFrameX, i*50 + map_Offset_Y + currentFrameY, null);
					pokeCenterDrawn=true;

				}
				if (model.getPlayerZone().getSquare(i, j).getRecentObject().toString().equals("C")) {
					g2.drawImage(imgGrab.getPc(), (j*50 + map_Offset_X + currentFrameX)+12, i*50 + map_Offset_Y + currentFrameY, null);

				}
				if (model.getPlayerZone().getSquare(i, j).getRecentObject().toString().equals("P")) {
					g2.drawImage(imgGrab.getHealingSta(), j*50 + map_Offset_X + currentFrameX, i*50 + map_Offset_Y + currentFrameY, null);

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
		}
		else if (key == 's') {
			facing = Direction.DOWN;
			moveDownAnimation();
			map_Offset_X = 0;
			map_Offset_Y = -5;
		}
		else if (key == 'a') {
			facing = Direction.LEFT;
			moveLeftAnimation();
			map_Offset_X = 5;
			map_Offset_Y = 0;
		}
		else if (key == 'd') {
			facing = Direction.RIGHT;
			moveRightAnimation();
			map_Offset_X = -5;
			map_Offset_Y = 0;
		}
		else 
			return;
		if (real && !model.move(key + "")){
	 		map_Offset_X = 0;
	 		map_Offset_Y = 0;
	 	}			
		running = true;		
		startPlayerAnimation();
//		System.out.println(model.getPlayerZone());
		return;
	}
	
	public void actionKey(KeyEvent key){
		

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
		timer = new Timer(1000/32, new PlayerAnimation());
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
				System.out.println("(" + model.getPlayer().getXPosition() + ", " + model.getPlayer().getYPosition() + ")");
			}			
		}
	}

}
