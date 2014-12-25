package gameObjects;

/**
 * 
 * @author Nick Gizzi
 * @author Jake Hewitt
 * @author Darshan Kothari
 * @author Taylor Siebenberg
 * 
 */
public class Hunter extends GameObject {

	private static final char gameObjectChar = 'O';

	public Hunter() {
		super();
	}

	/**
	 * Returns the graphical representation of the Hunter GameObject in a char
	 * 
	 * @return - The character representation of the Hunter GameObject
	 */
	@Override
	public char getChar() {
		return gameObjectChar;
	}

}
