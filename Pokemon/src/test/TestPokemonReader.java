package test;

import static org.junit.Assert.assertEquals;
import model.DatabaseReader;

import org.junit.Test;

import pokemonHierarchy.ElementType;
import pokemonHierarchy.Pokemon;

public class TestPokemonReader {
	
	@Test
	public void testPokemonReader() {
		DatabaseReader pd = new DatabaseReader();
		
		Pokemon testPokemonOne = pd.getPokemonByID(1, 1);		

		assertEquals(testPokemonOne.getPOKEMON_ID(), 1);
		assertEquals(testPokemonOne.getName(), "Bulbasaur");
		assertEquals(testPokemonOne.getBASE_HP(), 45);
		assertEquals(testPokemonOne.getBASE_ATTACK(), 49);
		assertEquals(testPokemonOne.getBASE_DEFENSE(), 49);
		assertEquals(testPokemonOne.getBASE_SPEED(), 45);
		assertEquals(testPokemonOne.getEVOLVES_AT(), 16);
		assertEquals(testPokemonOne.getLevel(), 1);
		assertEquals(testPokemonOne.getELEMENT_TYPE(), ElementType.Grass);	
		assertEquals(testPokemonOne.getMoveAt(0).getELEMENT_TYPE(), ElementType.Normal);	
		assertEquals(testPokemonOne.getMoveAt(1).getELEMENT_TYPE(), ElementType.Normal);	
		assertEquals(testPokemonOne.getMoveAt(2).getELEMENT_TYPE(), ElementType.Grass);	
		assertEquals(testPokemonOne.getMoveAt(3).getELEMENT_TYPE(), ElementType.Grass);	
		System.out.println(testPokemonOne.toString());
		System.out.println("Moves: \n" + testPokemonOne.getMoves());

	}
}
