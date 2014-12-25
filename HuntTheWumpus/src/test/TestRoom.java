package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gameMap.Room;
import gameObjects.Blood;
import gameObjects.Goop;
import gameObjects.Hunter;
import gameObjects.Pit;
import gameObjects.Slime;
import gameObjects.Wumpus;

import org.junit.Test;

public class TestRoom {

	@Test
	public void testVisibility() {
		Room room = new Room();
		assertFalse(room.getVisiblity());
		room.setVisible();
		assertTrue(room.getVisiblity());
		room.toggleVisibility();
		assertFalse(room.getVisiblity());
		room.toggleVisibility();
		assertTrue(room.getVisiblity());
	}

	@Test
	public void testgetAddAndRemoveGameObjectsInRoom() {
		Room room = new Room();
		Pit pit = new Pit();
		Hunter hunter = new Hunter();
		Wumpus wumpus = new Wumpus();

		assertEquals(0, room.getGameObjectsInRoom().size());
		room.addGameObject(pit);
		assertEquals(1, room.getGameObjectsInRoom().size());
		assertEquals(pit, room.getGameObjectsInRoom().get(0));
		room.addGameObject(hunter);
		assertEquals(pit, room.getGameObjectsInRoom().get(0));
		assertEquals(hunter, room.getGameObjectsInRoom().get(1));
		room.removeGameObject(pit);
		room.removeGameObject(wumpus);
		assertEquals(1, room.getGameObjectsInRoom().size());
		assertEquals(hunter, room.getGameObjectsInRoom().get(0));
		room.removeGameObject(hunter);
		assertEquals(0, room.getGameObjectsInRoom().size());
	}

	@Test
	public void testIsEmpty() {
		Room room = new Room();
		assertTrue(room.isEmpty());
		room.addGameObject(new Pit());
		assertFalse(room.isEmpty());
	}

	@Test
	public void testGetRoomChar() {
		Room room = new Room();
		Pit pit = new Pit();
		Slime slime = new Slime();
		Hunter hunter = new Hunter();
		Goop goop = new Goop();
		Wumpus wumpus = new Wumpus();
		Blood blood = new Blood();

		assertEquals(' ', room.getRoomChar());
		room.addGameObject(slime);
		assertEquals('S', room.getRoomChar());
		room.addGameObject(hunter);
		assertEquals('O', room.getRoomChar());
		room.removeGameObject(slime);
		room.removeGameObject(hunter);
		room.addGameObject(pit);
		assertEquals('P', room.getRoomChar());
		room.removeGameObject(pit);
		room.addGameObject(goop);
		assertEquals('G', room.getRoomChar());
		room.removeGameObject(goop);
		assertTrue(room.isEmpty());
		room.addGameObject(wumpus);
		assertEquals('W', room.getRoomChar());
		room.removeGameObject(wumpus);
		assertTrue(room.isEmpty());
		room.addGameObject(blood);
		assertEquals('B', room.getRoomChar());
		room.removeGameObject(blood);
		assertTrue(room.isEmpty());
		room.addGameObject(hunter);
		assertEquals('O', room.getRoomChar());
		room.removeGameObject(hunter);
		assertTrue(room.isEmpty());
	}

}
