package gameObjects;

/**
 * 
 * @author Nick Gizzi
 * @author Jake Hewitt
 * @author Darshan Kothari
 * @author Taylor Siebenberg
 * 
 */
public class Slime extends GameObject {

	private static final char gameObjectChar = 'S';

	public Slime() {
		super();
	}

	/**
	 * Returns the graphical representation of the Slime GameObject in a char
	 * 
	 * @return - The character representation of the Slime GameObject
	 */
	@Override
	public char getChar() {
		return gameObjectChar;
	}

}
