package test;

import static org.junit.Assert.assertEquals;
import gameObjects.Goop;

import org.junit.Test;

public class TestGoop {

	@Test
	public void testGetChar() {
		Goop goop = new Goop();
		Goop goop1 = new Goop();
		assertEquals('G', goop.getChar());
		assertEquals('G', goop1.getChar());
	}

}
