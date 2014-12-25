package gameMap;

import gameObjects.GameObject;

import java.util.ArrayList;

/**
 * 
 * @author Nick Gizzi
 * @author Jake Hewitt
 * @author Darshan Kothari
 * @author Taylor Siebenberg
 * 
 */
public class Room {

	ArrayList<GameObject> gameObjectsInRoom;
	boolean visibility;

	public Room() {
		this.gameObjectsInRoom = new ArrayList<GameObject>();
		visibility = false;
	}

	/**
	 * Gets the visibility of the room
	 * 
	 * @return boolean - Return whether or not this room is to be visible on the
	 *         map
	 */
	public boolean getVisiblity() {
		return visibility;
	}

	/**
	 * Changes the visibility of the room to true
	 */
	public void setVisible() {
		visibility = true;
	}

	/**
	 * Changes the visibility of the room to the opposite of what it was
	 */
	public void toggleVisibility() {
		visibility = !visibility;
	}

	/**
	 * Gets an ArrayList of all the GameObjects that are present in the room
	 * 
	 * @return ArrayList<GameObject> - Returns an ArrayList of all the
	 *         GameObjects that are present in the room
	 */
	public ArrayList<GameObject> getGameObjectsInRoom() {
		return gameObjectsInRoom;
	}

	/**
	 * Adds a GameObject to the ArrayList<GameObject> of all objects in the room
	 * 
	 * @param gameObject
	 *            - A GameObject to be added to the room
	 */
	public void addGameObject(GameObject gameObject) {
		this.gameObjectsInRoom.add(gameObject);
	}

	/**
	 * Removes all GameObjects in the ArrayList<GameObject> that are of the same
	 * class as the parameter
	 * 
	 * @param gameObject
	 *            - A GameObject to be removed from the ArrayList
	 */
	public void removeGameObject(GameObject gameObject) {
		for (int i = 0; i < gameObjectsInRoom.size(); i++) {
			if (gameObjectsInRoom.get(i).getClass() == gameObject.getClass())
				gameObjectsInRoom.remove(i);
		}
	}

	/**
	 * Returns whether or not the room is empty
	 * 
	 * @return boolean - Return whether or not there exists an object in the
	 *         Room
	 */
	public boolean isEmpty() {
		return gameObjectsInRoom.size() == 0;
	}

	/**
	 * Returns the dominant character of the Room.
	 * 
	 * @return char - The character beloning to the dominant GameObject in the
	 *         room.
	 */
	public char getRoomChar() {
		if (gameObjectsInRoom.isEmpty())
			return ' ';
		else if (gameObjectsInRoom.size() == 1)
			return gameObjectsInRoom.get(0).getChar();
		else
			return 'O';
	}

	public char getFlags() {
		if (gameObjectsInRoom.isEmpty())
			return ' ';
		else if (gameObjectsInRoom.size() == 1)
			return gameObjectsInRoom.get(0).getChar();
		else
			for (GameObject gameObject : gameObjectsInRoom) {
				if (gameObject.getChar() != 'O')
					return gameObject.getChar();
			}
		return gameObjectsInRoom.get(0).getChar();
	}
}
