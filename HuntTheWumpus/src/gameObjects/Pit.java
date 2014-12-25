package gameObjects;

/**
 * 
 * @author Nick Gizzi
 * @author Jake Hewitt
 * @author Darshan Kothari
 * @author Taylor Siebenberg
 * 
 */
public class Pit extends GameObject {

	private static final char gameObjectChar = 'P';

	public Pit() {
		super();
	}

	/**
	 * Returns the graphical representation of the Pit GameObject in a char
	 * 
	 * @return - The character representation of the Pit GameObject
	 */
	@Override
	public char getChar() {
		return gameObjectChar;
	}

}
