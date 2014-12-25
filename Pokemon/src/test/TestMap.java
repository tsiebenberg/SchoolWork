package test;

import mapHierarchy.DungeonZone;
import mapHierarchy.GymZone;
import mapHierarchy.MainZone;

import org.junit.Test;

public class TestMap {

	@Test
	public void testMainZone() {
		MainZone map = new MainZone();
		System.out.println(map.toString());
	}

	@Test
	public void testGymZone() {
		GymZone map = new GymZone();
		System.out.println(map.toString());
	}
	
	@Test
	public void testDungeonZone() {
		DungeonZone map = new DungeonZone();
		System.out.println(map.toString());
	}

}
