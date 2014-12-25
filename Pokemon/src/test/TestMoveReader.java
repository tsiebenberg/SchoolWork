package test;

import static org.junit.Assert.assertEquals;
import model.MoveDatabase;

import org.junit.Test;

import pokemonHierarchy.ElementType;
import pokemonHierarchy.Move;

public class TestMoveReader {

	@Test
	public void testMoveReader() {
		MoveDatabase moveData = new MoveDatabase();
		Move move;
		
		move = moveData.getMoveByID(5);
		assertEquals(move.getMOVE_ID(), 5);
		assertEquals(move.getMOVE_NAME(), "Gust");
		assertEquals(move.getBASE_DAMAGE(), 40);
		assertEquals(move.getELEMENT_TYPE(), ElementType.Normal);
		
		move = moveData.getMoveByID(20);
		assertEquals(move.getMOVE_ID(), 20);
		assertEquals(move.getMOVE_NAME(), "Dream_Eater");
		assertEquals(move.getBASE_DAMAGE(), 100);
		assertEquals(move.getELEMENT_TYPE(), ElementType.Psychic);
		
		move = moveData.getMoveByID(24);
		assertEquals(move.getMOVE_ID(), 24);
		assertEquals(move.getMOVE_NAME(), "Bone_Club");
		assertEquals(move.getBASE_DAMAGE(), 65);
		assertEquals(move.getELEMENT_TYPE(), ElementType.Rock);
		
		move = moveData.getMoveByID(28);
		assertEquals(move.getMOVE_ID(), 28);
		assertEquals(move.getMOVE_NAME(), "Blizzard");
		assertEquals(move.getBASE_DAMAGE(), 110);
		assertEquals(move.getELEMENT_TYPE(), ElementType.Water);
		
		move = moveData.getMoveByID(38);
		assertEquals(move.getMOVE_ID(), 38);
		assertEquals(move.getMOVE_NAME(), "Thunder");
		assertEquals(move.getBASE_DAMAGE(), 110);
		assertEquals(move.getELEMENT_TYPE(), ElementType.Electric);
		
		move = moveData.getMoveByID(42);
		assertEquals(move.getMOVE_ID(), 42);
		assertEquals(move.getMOVE_NAME(), "Ember");
		assertEquals(move.getBASE_DAMAGE(), 40);
		assertEquals(move.getELEMENT_TYPE(), ElementType.Fire);
		
		move = moveData.getMoveByID(46);
		assertEquals(move.getMOVE_ID(), 46);
		assertEquals(move.getMOVE_NAME(), "Egg_Bomb");
		assertEquals(move.getBASE_DAMAGE(), 100);
		assertEquals(move.getELEMENT_TYPE(), ElementType.Grass);
		
	}
	
	
}
