package gameObjects;

/**
 * 
 * @author Nick Gizzi
 * @author Jake Hewitt
 * @author Darshan Kothari
 * @author Taylor Siebenberg
 * 
 */
public class Blood extends GameObject {

	private static final char gameObjectChar = 'B';

	public Blood() {
		super();
	}

	/**
	 * Returns the graphical representation of the Blood GameObject in a char
	 * 
	 * @return - The character representation of the Blood GameObject
	 */
	@Override
	public char getChar() {
		return gameObjectChar;
	}

}
