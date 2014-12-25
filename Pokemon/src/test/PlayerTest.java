/**
 * @author Jake Hewitt
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.DatabaseReader;

import org.junit.Test;

import playerHierarchy.HumanPlayer;
import pokemonHierarchy.Pokemon;

public class PlayerTest {

	@Test
	public void testAddPokemon() {
		HumanPlayer ply = new HumanPlayer(5, 4);

		assertTrue(ply.addPokemon(new DatabaseReader().getPokemonByID(1, 5)));
		assertEquals(ply.getPokemon().size(), 1);
		assertTrue(ply.addPokemon(new DatabaseReader().getPokemonByID(2, 5)));
		assertEquals(ply.getPokemon().size(), 2);
		assertTrue(ply.addPokemon(new DatabaseReader().getPokemonByID(3, 5)));
		assertEquals(ply.getPokemon().size(), 3);
		assertTrue(ply.addPokemon(new DatabaseReader().getPokemonByID(4, 5)));
		assertEquals(ply.getPokemon().size(), 4);
		assertTrue(ply.addPokemon(new DatabaseReader().getPokemonByID(5, 5)));
		assertEquals(ply.getPokemon().size(), 5);
		assertTrue(ply.addPokemon(new DatabaseReader().getPokemonByID(6, 5)));
		assertEquals(ply.getPokemon().size(), 6);
		assertFalse(ply.addPokemon(new DatabaseReader().getPokemonByID(6, 5)));
		assertEquals(ply.getPokemon().size(), 6);
	}

	@Test
	public void testRemovePokemon() {
		HumanPlayer ply = new HumanPlayer(5, 4);
		Pokemon bulb = new DatabaseReader().getPokemonByID(1, 5);
		Pokemon ivy = new DatabaseReader().getPokemonByID(2, 5);
		Pokemon vena = new DatabaseReader().getPokemonByID(3, 5);
		Pokemon charmander = new DatabaseReader().getPokemonByID(4, 5);
		Pokemon charmelean = new DatabaseReader().getPokemonByID(5, 5);
		Pokemon charzard = new DatabaseReader().getPokemonByID(6, 5);

		assertTrue(ply.addPokemon(bulb));
		assertEquals(ply.getPokemon().size(), 1);
		assertTrue(ply.addPokemon(ivy));
		assertEquals(ply.getPokemon().size(), 2);
		assertTrue(ply.addPokemon(vena));
		assertEquals(ply.getPokemon().size(), 3);
		assertTrue(ply.addPokemon(charmander));
		assertEquals(ply.getPokemon().size(), 4);
		assertTrue(ply.addPokemon(charmelean));
		assertEquals(ply.getPokemon().size(), 5);
		assertTrue(ply.addPokemon(charzard));
		assertEquals(ply.getPokemon().size(), 6);
		
		assertTrue(ply.removePokemon(bulb));
		assertEquals(ply.getPokemon().size(), 5);
		assertFalse(ply.removePokemon(new DatabaseReader().getPokemonByID(12, 5)));
		assertEquals(ply.getPokemon().size(), 5);
		assertTrue(ply.removePokemon(ivy));
		assertEquals(ply.getPokemon().size(), 4);
		assertTrue(ply.removePokemon(vena));
		assertEquals(ply.getPokemon().size(), 3);
		assertTrue(ply.removePokemon(charmander));
		assertEquals(ply.getPokemon().size(), 2);
		assertTrue(ply.removePokemon(charmelean));
		assertEquals(ply.getPokemon().size(), 1);
		assertTrue(ply.removePokemon(charzard));
		assertEquals(ply.getPokemon().size(), 0);
		assertFalse(ply.removePokemon(vena));
		assertEquals(ply.getPokemon().size(), 0);		
	}

	@Test
	public void testAddAndRemovePokemon() {
		HumanPlayer player = new HumanPlayer(1,1);
		Pokemon bulb = new DatabaseReader().getPokemonByID(1, 5);
		Pokemon ivy = new DatabaseReader().getPokemonByID(2, 5);
		Pokemon vena = new DatabaseReader().getPokemonByID(3, 5);
		Pokemon charmander = new DatabaseReader().getPokemonByID(4, 5);
		Pokemon charmelean = new DatabaseReader().getPokemonByID(5, 5);
		Pokemon charzard = new DatabaseReader().getPokemonByID(6, 5);
		
		assertTrue(player.addPokemon(bulb));
		assertEquals(player.getPokemon().size(), 1);
		assertTrue(player.addPokemon(bulb));
		assertEquals(player.getPokemon().size(), 2);
		assertTrue(player.removePokemon(bulb));
		assertEquals(player.getPokemon().size(), 1);
		assertFalse(player.removePokemon(vena));
		assertTrue(player.removePokemon(bulb));
		assertEquals(player.getPokemon().size(), 0);
	}
}
