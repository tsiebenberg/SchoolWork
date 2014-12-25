package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.DatabaseReader;

import org.junit.Test;

import playerHierarchy.HumanPlayer;
import buildingHierarchy.HealStation;

public class TestHealthStation {

	@Test
	public void testHealPokemon() {
		HumanPlayer player = new HumanPlayer(2,2);
		HealStation healStation = new HealStation();
		assertEquals(0, player.getPokemon().size());
		player.addPokemon(new DatabaseReader().getPokemonByID(1, 5));
		assertEquals(1, player.getPokemon().size());
		assertFalse(player.getPokemon().get(0).isPokemonDead());
		assertEquals(64, player.getPokemon().get(0).getMaxHp());
		assertEquals(0, player.getPokemon().get(0).getDamage());
		player.getPokemon().get(0).setDamage(64);
		assertEquals(64, player.getPokemon().get(0).getDamage());
		assertTrue(player.getPokemon().get(0).isPokemonDead());
		healStation.healPokemon(player);
		assertFalse(player.getPokemon().get(0).isPokemonDead());
		assertEquals(0, player.getPokemon().get(0).getDamage());
		assertEquals(64, player.getPokemon().get(0).getMaxHp());
	}

}
