package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gameMap.Room;
import model.HuntTheWumpusModel;

import org.junit.Test;

public class TestHuntTheWumpusModel {

	@Test
	public void testMoveWithAllMovesNotOnBorder() {
		HuntTheWumpusModel model = new HuntTheWumpusModel(10);
		model.getGameMap().removeHunter();
		model.getGameMap().setHunterRow(5);
		model.getGameMap().setHunterCol(5);
		model.moveUp();
		assertEquals(4, model.getGameMap().getHunterRow());
		assertEquals(5, model.getGameMap().getHunterCol());
		model.moveLeft();
		assertEquals(4, model.getGameMap().getHunterRow());
		assertEquals(4, model.getGameMap().getHunterCol());
		model.moveDown();
		assertEquals(5, model.getGameMap().getHunterRow());
		assertEquals(4, model.getGameMap().getHunterCol());
		model.moveRight();
		assertEquals(5, model.getGameMap().getHunterRow());
		assertEquals(5, model.getGameMap().getHunterCol());
		model.moveRight();
		assertEquals(5, model.getGameMap().getHunterRow());
		assertEquals(6, model.getGameMap().getHunterCol());
	}

	@Test
	public void testMoveUpWhenHunterIsAtRowZero() {
		HuntTheWumpusModel model = new HuntTheWumpusModel(10);
		model.getGameMap().removeHunter();
		model.getGameMap().setHunterRow(0);
		model.getGameMap().setHunterCol(5);
		model.moveUp();
		assertEquals(9, model.getGameMap().getHunterRow());
		assertEquals(5, model.getGameMap().getHunterCol());
	}

	@Test
	public void testMoveDownWhenHunterIsAtRowNine() {
		HuntTheWumpusModel model = new HuntTheWumpusModel(10);
		model.getGameMap().removeHunter();
		model.getGameMap().setHunterRow(9);
		model.getGameMap().setHunterCol(4);
		model.moveDown();
		assertEquals(0, model.getGameMap().getHunterRow());
		assertEquals(4, model.getGameMap().getHunterCol());
	}

	@Test
	public void testMoveRightWhenHunterIsAtColNine() {
		HuntTheWumpusModel model = new HuntTheWumpusModel(10);
		model.getGameMap().removeHunter();
		model.getGameMap().setHunterRow(3);
		model.getGameMap().setHunterCol(9);
		model.moveRight();
		assertEquals(3, model.getGameMap().getHunterRow());
		assertEquals(0, model.getGameMap().getHunterCol());
	}

	@Test
	public void testMoveLeftWhenHunterIsAtColZero() {
		HuntTheWumpusModel model = new HuntTheWumpusModel(10);
		model.getGameMap().removeHunter();
		model.getGameMap().setHunterRow(7);
		model.getGameMap().setHunterCol(0);
		model.moveLeft();
		assertEquals(7, model.getGameMap().getHunterRow());
		assertEquals(9, model.getGameMap().getHunterCol());
	}

	@Test
	public void testCheckWarningForMoveDown() {
		HuntTheWumpusModel model = new HuntTheWumpusModel(10);
		Room[][] map = model.getGameMap().getMapArray();
		model.moveDown();
		int hunterX = model.getGameMap().getHunterRow();
		int hunterY = model.getGameMap().getHunterCol();
		if (map[hunterX][hunterY].getRoomChar() == 'G')
			assertEquals("You've stepped in some sort of goop, disgusting!",
					model.checkWarning());
		else if (map[hunterX][hunterY].getRoomChar() == 'B')
			assertEquals("You see blood on your boots!", model.checkWarning());
		else if (map[hunterX][hunterY].getRoomChar() == 'S')
			assertEquals("You are covered in slime!", model.checkWarning());
		else
			assertEquals("You see nothing!", model.checkWarning());
	}

	@Test
	public void testCheckWarningForMoveUp() {
		HuntTheWumpusModel model = new HuntTheWumpusModel(10);
		Room[][] map = model.getGameMap().getMapArray();
		model.moveUp();
		int hunterX = model.getGameMap().getHunterRow();
		int hunterY = model.getGameMap().getHunterCol();
		if (map[hunterX][hunterY].getRoomChar() == 'G')
			assertEquals("You've stepped in some sort of goop, disgusting!",
					model.checkWarning());
		else if (map[hunterX][hunterY].getRoomChar() == 'B')
			assertEquals("You see blood on your boots!", model.checkWarning());
		else if (map[hunterX][hunterY].getRoomChar() == 'S')
			assertEquals("You are covered in slime!", model.checkWarning());
		else
			assertEquals("You see nothing!", model.checkWarning());
	}

	@Test
	public void testCheckWarningForMoveRight() {
		HuntTheWumpusModel model = new HuntTheWumpusModel(10);
		Room[][] map = model.getGameMap().getMapArray();
		model.moveRight();
		int hunterX = model.getGameMap().getHunterRow();
		int hunterY = model.getGameMap().getHunterCol();
		if (map[hunterX][hunterY].getRoomChar() == 'G')
			assertEquals("You've stepped in some sort of goop, disgusting!",
					model.checkWarning());
		else if (map[hunterX][hunterY].getRoomChar() == 'B')
			assertEquals("You see blood on your boots!", model.checkWarning());
		else if (map[hunterX][hunterY].getRoomChar() == 'S')
			assertEquals("You are covered in slime!", model.checkWarning());
		else
			assertEquals("You see nothing!", model.checkWarning());
	}

	@Test
	public void testCheckWarningForMoveLeft() {
		HuntTheWumpusModel model = new HuntTheWumpusModel(10);
		Room[][] map = model.getGameMap().getMapArray();
		model.moveLeft();
		int hunterX = model.getGameMap().getHunterRow();
		int hunterY = model.getGameMap().getHunterCol();
		if (map[hunterX][hunterY].getRoomChar() == 'G')
			assertEquals("You've stepped in some sort of goop, disgusting!",
					model.checkWarning());
		else if (map[hunterX][hunterY].getRoomChar() == 'B')
			assertEquals("You see blood on your boots!", model.checkWarning());
		else if (map[hunterX][hunterY].getRoomChar() == 'S')
			assertEquals("You are covered in slime!", model.checkWarning());
		else
			assertEquals("You see nothing!", model.checkWarning());
	}

	@Test
	public void testFireArrowLeft() {
		HuntTheWumpusModel model = new HuntTheWumpusModel(10);
		Room[][] map = model.getGameMap().getMapArray();
		int wumpusX = -1;
		int wumpusY = -1;
		model.getGameMap().removeHunter();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (model.getGameMap().getRoom(i, j).getRoomChar() == 'W') {
					wumpusX = i;
					wumpusY = j;
				}
			}
		}
		model.getGameMap().setHunterRow(wumpusX);
		if (wumpusY != 5) {
			model.getGameMap().setHunterCol(5);
		} else {
			model.getGameMap().setHunterCol(4);

		}
		model.fireArrow("left");
		assertTrue(model.deathByWumpus());
	}

	@Test
	public void testFireArrowRight() {
		HuntTheWumpusModel model = new HuntTheWumpusModel(10);
		Room[][] map = model.getGameMap().getMapArray();
		int wumpusX = -1;
		int wumpusY = -1;
		model.getGameMap().removeHunter();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (model.getGameMap().getRoom(i, j).getRoomChar() == 'W') {
					wumpusX = i;
					wumpusY = j;
				}
			}
		}
		model.getGameMap().setHunterRow(wumpusX);
		if (wumpusY != 5) {
			model.getGameMap().setHunterCol(5);
		} else {
			model.getGameMap().setHunterCol(4);

		}
		model.fireArrow("right");

		assertTrue(model.deathByWumpus());
	}

	@Test
	public void testFireArrowUp() {
		HuntTheWumpusModel model = new HuntTheWumpusModel(10);
		Room[][] map = model.getGameMap().getMapArray();
		int wumpusX = -1;
		int wumpusY = -1;
		model.getGameMap().removeHunter();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (model.getGameMap().getRoom(i, j).getRoomChar() == 'W') {
					wumpusX = i;
					wumpusY = j;
				}
			}
		}
		model.getGameMap().setHunterCol(wumpusY);
		if (wumpusX != 5) {
			model.getGameMap().setHunterRow(5);
		} else {
			model.getGameMap().setHunterRow(4);

		}
		model.fireArrow("up");
		assertTrue(model.deathByWumpus());
	}

	@Test
	public void testFireArrowDown() {
		HuntTheWumpusModel model = new HuntTheWumpusModel(10);
		Room[][] map = model.getGameMap().getMapArray();
		int wumpusX = -1;
		int wumpusY = -1;
		model.getGameMap().removeHunter();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (model.getGameMap().getRoom(i, j).getRoomChar() == 'W') {
					wumpusX = i;
					wumpusY = j;
				}
			}
		}
		model.getGameMap().setHunterCol(wumpusY);
		if (wumpusX != 5) {
			model.getGameMap().setHunterRow(5);
		} else {
			model.getGameMap().setHunterRow(4);

		}
		model.fireArrow("Down");
		assertTrue(model.deathByWumpus());
	}

	@Test
	public void testIsGameOver() {
		HuntTheWumpusModel model = new HuntTheWumpusModel(10);
		assertFalse(model.deathByWumpus());

	}

	@Test
	public void testToString() {
		HuntTheWumpusModel model = new HuntTheWumpusModel(10);
		System.out.println(model.toString());
	}

}
