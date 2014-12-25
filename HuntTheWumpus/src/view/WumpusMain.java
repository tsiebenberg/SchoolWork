package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.GameObjectImageGrabber;
import model.HuntTheWumpusModel;
import observer.Observable;
import observer.Observer;
import animations.EatenByWumpus;
import animations.FallInPit;
import animations.FireArrow;

/**
 * 
 * @author Nick Gizzi
 * @author Jake Hewitt
 * @author Darshan Kothari
 * @author Taylor Siebenberg
 * 
 */
public class WumpusMain extends JFrame implements Observer {

	private static final long serialVersionUID = -7280174508287142877L;
	//
	HuntTheWumpusModel model;

	// Panels
	IGamePanel lastPanel;
	EatenByWumpus gameOverPanel;
	StartMenu startPanel;
	GameView gameView;
	TextView textView;
	FallInPit pitPanel;
	FireArrow arrowPanel;

	//
	Timer timer;

	// Menu Bar
	JMenuBar menuBar;
	// File menu items
	JMenu fileMenu;
	JMenu optionMenu;
	JMenu aboutMenu;
	JMenu viewMenu;
	// File menu items
	JMenuItem newGame;
	JMenuItem exit;
	// Options menu items
	JMenuItem changeDifficulty;
	// View menu items
	JMenuItem graphical;
	JMenuItem textual;
	// About menu items
	JMenuItem controls;
	JMenuItem about;

	public static void main(String[] args) {
		WumpusMain window = new WumpusMain();
		window.setVisible(true);
	}

	/**
	 * The constructor, responsible for initializing all the necessary variables
	 * and setting the inital view
	 */
	public WumpusMain() {
		initializeGlobalVar();
		setProperties();
		addMenuItems();
		setJMenuBar(menuBar);
		registerListeners();
		// Sets the current view to the game over animation, obviously we will
		// change this to a different animation but for now its the only one we
		// have
		setStart();
	}

	/**
	 * Initializes the global variables
	 */
	private void initializeGlobalVar() {
		model = new HuntTheWumpusModel(1);
		timer = new Timer(4000, new AnimationHandler());

		startPanel = new StartMenu();
		gameView = new GameView(model);
		textView = new TextView(model);

		menuBar = new JMenuBar();
		// Menus
		fileMenu = new JMenu("File");
		optionMenu = new JMenu("Options");
		aboutMenu = new JMenu("About");
		viewMenu = new JMenu("View");
		// File menu items
		newGame = new JMenuItem("New Game");
		exit = new JMenuItem("Exit");
		// Options menu items
		changeDifficulty = new JMenuItem("Change Difficulty");
		// View menu items
		graphical = new JMenuItem("Graphical view");
		textual = new JMenuItem("Textual view");
		// About menu items
		controls = new JMenuItem("Controls");
		about = new JMenuItem("Hunt the Wumpus V1.0");

	}

	/**
	 * Sets the initial default properties of the JFrame
	 */
	private void setProperties() {
		// Set properties of JFrame
		this.setSize(762, 602);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Hunt The Wumpus");
		this.setResizable(false);

		this.setIconImage(new GameObjectImageGrabber().getWumpusImage());
	}

	private void registerListeners() {
		this.addKeyListener(new KeyListen());
		model.addObserver(this);

		MenuListener ml = new MenuListener();
		newGame.addActionListener(ml);
		exit.addActionListener(ml);
		changeDifficulty.addActionListener(ml);
		controls.addActionListener(ml);
		about.addActionListener(ml);
		graphical.addActionListener(ml);
		textual.addActionListener(ml);
	}

	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent source) {
			if (source.getSource() == newGame) {
				startGame();
				setGameView();
			}
			if (source.getSource() == exit)
				System.exit(0);
			if (source.getSource() == changeDifficulty) {
				gameView = new GameView(model);
				textView = new TextView(model);
				setStart();
			}
			if (source.getSource() == about) {
				JOptionPane.showMessageDialog(null, "Hunt the Wumpus V1.0\n\n"
						+ "Authors:\n" + "Nick Gizzi\n" + "Jake Hewitt\n"
						+ "Darshan Kothari\n" + "Taylor Siebenberg");
			}
			if (source.getSource() == controls) {
				JOptionPane.showMessageDialog(null, "Controls:\n"
						+ "To move: wasd or directional keys"
						+ "To enter/exit fire mode: f");
			}
			if (source.getSource() == graphical) {
				setGameView();
			}
			if (source.getSource() == textual) {
				setTextView();
			}
		}

	}

	/**
	 * Add menu bar items to the menuBar
	 */
	private void addMenuItems() {
		menuBar.add(fileMenu);
		fileMenu.add(newGame);
		fileMenu.add(exit);

		menuBar.add(optionMenu);
		optionMenu.add(changeDifficulty);

		menuBar.add(viewMenu);
		viewMenu.add(graphical);
		viewMenu.add(textual);

		menuBar.add(aboutMenu);
		aboutMenu.add(about);
		aboutMenu.add(controls);
	}

	/**
	 * Set the current view to the gameOver animation
	 */
	private void setGameOverLose() {
		switchPanel(gameOverPanel);
	}

	private void setPitPanel() {
		switchPanel(pitPanel);
	}

	private void setArrowPanel() {
		switchPanel(arrowPanel);
	}

	private void setTextView() {
		textView.layoutPanel();
		lastPanel = textView;
		switchPanel(textView);
	}

	/**
	 * Set the current view to the start screen animation
	 */
	private void setStart() {
		switchPanel(startPanel);
	}

	/**
	 * Set the current view to the view of the game board
	 */
	public void setGameView() {
		lastPanel = gameView;
		switchPanel(gameView);
	}

	private void switchPanel(JPanel gamePanel) {
		this.getContentPane().removeAll();
		this.getContentPane().revalidate();
		this.setContentPane(gamePanel);
		this.getContentPane().repaint();
	}

	private class KeyListen implements KeyListener {

		@Override
		public void keyTyped(KeyEvent key) {
			if (key.getKeyChar() == 'w') {
				passKey(key.getKeyChar());
			}
			if (key.getKeyChar() == 's') {
				passKey(key.getKeyChar());
			}
			if (key.getKeyChar() == 'a') {
				passKey(key.getKeyChar());
			}
			if (key.getKeyChar() == 'd') {
				passKey(key.getKeyChar());
			}
			if (key.getKeyChar() == 'f') {
				passKey(key.getKeyChar());
			}

		}

		@Override
		public void keyPressed(KeyEvent key) {
			if (key.getKeyCode() == KeyEvent.VK_UP) {
				passKey(key.getKeyCode());
			}
			if (key.getKeyCode() == KeyEvent.VK_DOWN) {
				passKey(key.getKeyCode());
			}
			if (key.getKeyCode() == KeyEvent.VK_LEFT) {
				passKey(key.getKeyCode());
			}
			if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
				passKey(key.getKeyCode());
			}
			if (key.getKeyCode() == KeyEvent.VK_ENTER) {
				if (getContentPane() == startPanel)
					startGame();
				if (getContentPane() == gameView
						|| getContentPane() == textView) {
					if (model.gameOver())
						startGame();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent key) {

		}

	}

	private void passKey(int keyCode) {
		((IGamePanel) this.getContentPane()).passKey(keyCode);
	}

	private void startGame() {
		model.newGame(startPanel.getDifficulty());
		gameView = new GameView(model);
		textView = new TextView(model);
		if (lastPanel == textView)
			setTextView();
		else
			setGameView();
	}

	@Override
	public void update(Observable ob) {
		if (model.didWin()) {
			arrowPanel = new FireArrow();
			startAnimation();
			this.setArrowPanel();
		}
		if (model.checkDeath() == true || !model.didWin()) {
			gameOverPanel = new EatenByWumpus();
			startAnimation();
			this.setGameOverLose();
		}
		if (model.deathByPit() == true) {
			pitPanel = new FallInPit();
			startAnimation();
			this.setPitPanel();
		}
	}

	private void startAnimation() {
		timer.start();
	}

	private class AnimationHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			timer.stop();
			if (lastPanel == textView)
				setTextView();
			else
				setGameView();
			repaint();
		}
	}

}
