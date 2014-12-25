package test;

import model.HuntTheWumpusModel;

import org.junit.Test;

/**
 * 
 * @author Nick Gizzi
 * @author Jake Hewitt
 * @author Darshan Kothari
 * @author Taylor Siebenberg
 * 
 */
public class testMap {

	@Test
	public void testMapToString() {
		HuntTheWumpusModel hm = new HuntTheWumpusModel(10);
		System.out.println(hm.toString());
	}

}
