package gameObjects;

/**
 * 
 * @author Nick Gizzi
 * @author Jake Hewitt
 * @author Darshan Kothari
 * @author Taylor Siebenberg
 * 
 */
public class Goop extends GameObject {

	private static final char gameObjectChar = 'G';

	public Goop() {
		super();
	}

	/**
	 * Returns the graphical representation of the Goop GameObject in a char
	 * 
	 * @return - The character representation of the Goop GameObject
	 */
	@Override
	public char getChar() {
		return gameObjectChar;
	}

}
