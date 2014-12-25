package gameObjects;

/**
 * 
 * @author Nick Gizzi
 * @author Jake Hewitt
 * @author Darshan Kothari
 * @author Taylor Siebenberg
 * 
 */
public class Wumpus extends GameObject {

	private static final char gameObjectChar = 'W';

	public Wumpus() {
		super();
	}

	/**
	 * Returns the graphical representation of the Wumpus GameObject in a char
	 * 
	 * @return - The character representation of the Wumpus GameObject
	 */
	@Override
	public char getChar() {
		return gameObjectChar;
	}

}
