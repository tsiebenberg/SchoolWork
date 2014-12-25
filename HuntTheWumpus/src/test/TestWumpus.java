package test;

import static org.junit.Assert.assertEquals;
import gameObjects.Wumpus;

import org.junit.Test;

public class TestWumpus {

	@Test
	public void testGetChar() {
		Wumpus wumpus = new Wumpus();
		Wumpus wumpus1 = new Wumpus();
		assertEquals('W', wumpus.getChar());
		assertEquals('W', wumpus1.getChar());
	}

}
