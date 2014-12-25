package gameObjects;

/**
 * 
 * @author Nick Gizzi
 * @author Jake Hewitt
 * @author Darshan Kothari
 * @author Taylor Siebenberg
 * 
 */
public abstract class GameObject {

	// 0 is the ascii Null character
	private static final char gameObjectChar = 0;

	public GameObject() {

	}

	/**
	 * Returns the graphical representation of the GameObject in a char
	 * 
	 * @return - The character representation of the GameObject
	 */
	public char getChar() {
		return gameObjectChar;
	}

}
