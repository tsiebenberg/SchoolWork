package test;

import static org.junit.Assert.assertEquals;
import gameObjects.Blood;

import org.junit.Test;

public class TestBlood {

	@Test
	public void testGetChar() {
		Blood blood = new Blood();
		Blood blood1 = new Blood();
		assertEquals('B', blood.getChar());
		assertEquals('B', blood1.getChar());
	}

}
