package test;

import static org.junit.Assert.assertEquals;
import gameObjects.Pit;

import org.junit.Test;

public class TestPit {

	@Test
	public void testGetChar() {
		Pit pit = new Pit();
		Pit pit1 = new Pit();
		assertEquals('P', pit.getChar());
		assertEquals('P', pit1.getChar());
	}

}
