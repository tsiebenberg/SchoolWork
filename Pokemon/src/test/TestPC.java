package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.DatabaseReader;

import org.junit.Test;

import playerHierarchy.HumanPlayer;
import pokemonHierarchy.Pokemon;
import buildingHierarchy.PC;

public class TestPC {

	@Test
	public void testStorePokemon() {
		HumanPlayer ply = new HumanPlayer(5, 4);
		PC pc = new PC();
		Pokemon bulb = new DatabaseReader().getPokemonByID(1, 5);
		Pokemon ivy = new DatabaseReader().getPokemonByID(2, 5);
		Pokemon vena = new DatabaseReader().getPokemonByID(3, 5);
		Pokemon charmander = new DatabaseReader().getPokemonByID(4, 5);
		Pokemon charmelean = new DatabaseReader().getPokemonByID(5, 5);
		Pokemon charzard = new DatabaseReader().getPokemonByID(6, 5);
		
		assertTrue(ply.addPokemon(bulb));
		assertTrue(ply.addPokemon(ivy));
		assertTrue(ply.addPokemon(vena));
		assertTrue(ply.addPokemon(charmander));
		assertTrue(ply.addPokemon(charmelean));
		assertTrue(ply.addPokemon(charzard));
		assertEquals(6, ply.getPokemon().size());
		
		assertTrue(pc.storePokemon(ply, bulb));					// Store a pokemon
		assertEquals(5, ply.getPokemon().size());
		assertEquals(1, pc.getPokemon().size());
		
		assertFalse(pc.storePokemon(ply, bulb));
		
		assertTrue(pc.storePokemon(ply, ivy));					// Store a pokemon
		assertEquals(4, ply.getPokemon().size());
		assertEquals(2, pc.getPokemon().size());
		
		assertTrue(pc.storePokemon(ply, vena));					// Store a pokemon
		assertEquals(3, ply.getPokemon().size());
		assertEquals(3, pc.getPokemon().size());
		
		assertTrue(pc.storePokemon(ply, charmander));			// Store a pokemon
		assertEquals(2, ply.getPokemon().size());
		assertEquals(4, pc.getPokemon().size());
		
		assertTrue(pc.storePokemon(ply, charmelean));			// Store a pokemon
		assertEquals(1, ply.getPokemon().size());
		assertEquals(5, pc.getPokemon().size());
		
		assertTrue(pc.storePokemon(ply, charzard));				// Store a pokemon
		assertEquals(0, ply.getPokemon().size());
		assertEquals(6, pc.getPokemon().size());
		
		assertFalse(pc.storePokemon(ply, bulb));
		
		assertTrue(ply.addPokemon(bulb));
		assertEquals(1, ply.getPokemon().size());
		assertEquals(6, pc.getPokemon().size());
		
		assertTrue(pc.storePokemon(ply, bulb));					// Store a pokemon
		assertEquals(0, ply.getPokemon().size());
		assertEquals(7, pc.getPokemon().size());
	}
	
	@Test
	public void testWithdrawPokemon() {
		HumanPlayer ply = new HumanPlayer(5, 4);
		PC pc = new PC();
		Pokemon bulb = new DatabaseReader().getPokemonByID(1, 5);
		Pokemon ivy = new DatabaseReader().getPokemonByID(2, 5);
		Pokemon vena = new DatabaseReader().getPokemonByID(3, 5);
		Pokemon charmander = new DatabaseReader().getPokemonByID(4, 5);
		Pokemon charmelean = new DatabaseReader().getPokemonByID(5, 5);
		Pokemon charzard = new DatabaseReader().getPokemonByID(6, 5);
		
		assertTrue(ply.addPokemon(bulb));
		assertTrue(ply.addPokemon(ivy));
		assertTrue(ply.addPokemon(vena));
		assertTrue(ply.addPokemon(charmander));
		assertTrue(ply.addPokemon(charmelean));
		
		assertEquals(5, ply.getPokemon().size());
		assertEquals(0, pc.getPokemon().size());
		
		assertTrue(pc.storePokemon(ply, bulb));					// Store a pokemon
		assertTrue(pc.storePokemon(ply, ivy));					// Store a pokemon
		assertTrue(pc.storePokemon(ply, vena));					// Store a pokemon
		assertTrue(pc.storePokemon(ply, charmander));			// Store a pokemon
		assertTrue(pc.storePokemon(ply, charmelean));			// Store a pokemon
		
		assertEquals(0, ply.getPokemon().size());
		assertEquals(5, pc.getPokemon().size());
		
		assertTrue(pc.withdrawPokemon(ply, bulb));						// Withdraw a pokemon
		assertEquals(1, ply.getPokemon().size());
		assertEquals(4, pc.getPokemon().size());
		
		assertFalse(pc.withdrawPokemon(ply, bulb));						// Withdraw a pokemon
		assertEquals(1, ply.getPokemon().size());
		assertEquals(4, pc.getPokemon().size());
		
		assertTrue(pc.withdrawPokemon(ply, ivy));						// Withdraw a pokemon
		assertEquals(2, ply.getPokemon().size());
		assertEquals(3, pc.getPokemon().size());
		
		assertTrue(pc.withdrawPokemon(ply, vena));						// Withdraw a pokemon
		assertEquals(3, ply.getPokemon().size());
		assertEquals(2, pc.getPokemon().size());
		
		assertTrue(pc.withdrawPokemon(ply, charmander));				// Withdraw a pokemon
		assertEquals(4, ply.getPokemon().size());
		assertEquals(1, pc.getPokemon().size());
		
		assertTrue(pc.withdrawPokemon(ply, charmelean));				// Withdraw a pokemon
		assertEquals(5, ply.getPokemon().size());
		assertEquals(0, pc.getPokemon().size());
		
		assertFalse(pc.withdrawPokemon(ply, ivy));						// Withdraw a pokemon
		assertEquals(5, ply.getPokemon().size());
		assertEquals(0, pc.getPokemon().size());
	}
	
	@Test
	public void testSomeStuff() {
		PC pc = new PC();
		assertEquals(0, pc.getRowCount());
	}

}
