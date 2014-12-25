package test;

import static org.junit.Assert.assertEquals;
import gameObjects.Hunter;

import org.junit.Test;

public class TestHunter {

	@Test
	public void testGetChar() {
		Hunter hunter = new Hunter();
		Hunter hunter1 = new Hunter();
		assertEquals('O', hunter.getChar());
		assertEquals('O', hunter1.getChar());
	}

}
