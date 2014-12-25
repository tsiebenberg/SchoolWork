package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gameMap.GameMap;
import gameMap.Room;

import org.junit.Test;

public class TestGameMap {

	@Test
	public void testGameMapConstructor() {
		GameMap game = new GameMap(10);
		assertEquals(10, game.getNumberOfPits());
		Room[][] room = game.getMapArray();
		assertEquals(10, room.length);
		assertEquals(10, room[0].length);
	}

	@Test
	public void testInitializeGame() {
		GameMap game = new GameMap(10);
		Room[][] room = game.getMapArray();
		assertEquals(10, room.length);
		assertEquals(10, room[0].length);
	}

	@Test
	public void testPlacePits() {
		int numberOfPits = 15;
		GameMap game = new GameMap(numberOfPits);
		Room[][] map = game.getMapArray();
		int count = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].getRoomChar() == 'P')
					count++;
			}
		}
		assertEquals(numberOfPits, count);
	}

	@Test
	public void testPlaceWumpus() {
		GameMap game = new GameMap(10);
		Room[][] map = game.getMapArray();
		int count = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].getRoomChar() == 'W')
					count++;
			}
		}
		assertEquals(1, count);
	}

	@Test
	public void testSetPitFlags() {
		int numberOfPits = 2;
		GameMap game = new GameMap(numberOfPits);
		Room[][] map = game.getMapArray();
		int count = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].getRoomChar() == 'S')
					count++;
			}

		}
		assertTrue(count <= numberOfPits * 4);
		assertFalse(count > numberOfPits * 4);
	}

	@Test
	public void testSetWumpusFlagsOnlyOneSpaceFromWumpus() {
		GameMap game = new GameMap(10);
		Room[][] map = game.getMapArray();
		int count = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].getRoomChar() == 'B'
						|| map[i][j].getRoomChar() == 'G')
					count++;
			}
			assertTrue(count <= 4);
			assertFalse(count > 4);
		}
	}

	// // This test works when having blood two spaces from wumpus
	// @Test
	// public void testSetWumpusFlagsTwoSpacesFromWumpus() {
	// GameMap game = new GameMap(10);
	// Room[][] map = game.getMapArray();
	// int count = 0;
	// for (int i = 0; i < map.length; i++) {
	// for (int j = 0; j < map[0].length; j++) {
	// if (map[i][j].getRoomChar() == 'B' || map[i][j].getRoomChar() == 'G')
	// count++;
	// }
	// assertTrue( count <= 12 );
	// assertFalse( count > 12 );
	// }
	// }

	@Test
	public void testSafePlaceHunter() {
		GameMap game = new GameMap(10);
		Room[][] map = game.getMapArray();
		int count = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].getRoomChar() == 'O') {
					assertEquals(1, game.getRoom(i, j).getGameObjectsInRoom()
							.size());
					count++;
				}
			}
		}
		assertEquals(1, count);
	}

	@Test
	public void testMapToString() {
		GameMap game = new GameMap(10);
		System.out.println(game.toString());
	}

	@Test
	public void testHunterGettersAndSetters() {
		GameMap game = new GameMap(10);
		Room[][] map = game.getMapArray();
		int count = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].getRoomChar() == 'O') {
					assertEquals(i, game.getHunterRow());
					assertEquals(j, game.getHunterCol());
					assertEquals(1, game.getRoom(i, j).getGameObjectsInRoom()
							.size());
					count++;
				}
			}
		}
		assertEquals(1, count);
		game.removeHunter();
		int count0 = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].getRoomChar() == 'O') {
					assertEquals(i, game.getHunterRow());
					assertEquals(j, game.getHunterCol());
					assertEquals(1, game.getRoom(i, j).getGameObjectsInRoom()
							.size());
					count0++;
				}
			}
		}
		assertEquals(0, count0);

		int count1 = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].getRoomChar() == 'O') {
					assertEquals(i, game.getHunterRow());
					assertEquals(j, game.getHunterCol());
					assertEquals(1, game.getRoom(i, j).getGameObjectsInRoom()
							.size());
					count1++;
				}
			}
		}
		assertEquals(0, count1);

		GameMap game1 = new GameMap(10);
		game1.removeHunter();
		game1.setHunterRow(3);
		game1.setHunterCol(3);
		assertEquals(3, game1.getHunterRow());
		assertEquals(3, game1.getHunterCol());
	}

	@Test
	public void testWumpusGettersandSetters() {
		GameMap game = new GameMap(10);
		Room[][] map = game.getMapArray();
		int count = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].getRoomChar() == 'W') {
					assertEquals(i, game.getWumpusRow());
					assertEquals(j, game.getWumpusCol());
					count++;
				}
			}
		}
		assertEquals(1, count);
	}

	@Test
	public void testGetNumberOfPits() {
		int numberOfPits = 5;
		GameMap game = new GameMap(numberOfPits);
		assertEquals(5, game.getNumberOfPits());
		int numberOfPits1 = 20;
		GameMap game1 = new GameMap(numberOfPits1);
		assertEquals(20, game1.getNumberOfPits());
	}

	@Test
	public void testGetMapArray() {
		GameMap game = new GameMap(10);
		assertEquals(10, game.getMapArray().length);
		assertEquals(10, game.getMapArray()[0].length);
	}

}
