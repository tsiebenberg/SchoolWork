package test;

import static org.junit.Assert.assertEquals;
import gameObjects.Slime;

import org.junit.Test;

public class TestSlime {

	@Test
	public void testGetChar() {
		Slime slime = new Slime();
		Slime slime1 = new Slime();
		assertEquals('S', slime.getChar());
		assertEquals('S', slime1.getChar());
	}

}
