package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import playerHierarchy.Direction;
import model.PokemonModel;
import model.SpriteManager;

public class EndGameView extends JPanel {


	private Graphics2D g2;
	private PokemonModel model;
	private SpriteManager imgGrab;


	public EndGameView(PokemonModel model){
		this.model = model;
		this.setLayout(null);
		imgGrab = new SpriteManager();

	}
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		drawMap();

	}
	public void drawMap(){
		g2.drawImage(imgGrab.getDevelopers(),200,100, null);
	}
}

