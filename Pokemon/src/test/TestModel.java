package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.DatabaseReader;
import model.PokemonModel;

public class TestModel {

	@Test
	public void testGameOver() {
		PokemonModel model = new PokemonModel();
		for(int i = 1; i <= 62; i++) {
			model.getPC().getPokemon().add(new DatabaseReader().getPokemonByID(i, 1));
		}
		assertFalse(model.gameOver());
		model.getPC().getPokemon().add(new DatabaseReader().getPokemonByID(63, 1));
		assertTrue(model.gameOver());
	}
	
	@Test
	public void testGameOverShit() {
		PokemonModel model = new PokemonModel();
		for(int i = 1; i <= 57 ; i++) {
			model.getPC().getPokemon().add(new DatabaseReader().getPokemonByID(i, 1));
			assertFalse(model.gameOver());
		}
		model.getPlayer().addPokemon(new DatabaseReader().getPokemonByID(58, 1));
		assertFalse(model.gameOver());
		model.getPlayer().addPokemon(new DatabaseReader().getPokemonByID(59, 1));
		assertFalse(model.gameOver());
		model.getPlayer().addPokemon(new DatabaseReader().getPokemonByID(60, 1));
		assertFalse(model.gameOver());
		model.getPlayer().addPokemon(new DatabaseReader().getPokemonByID(61, 1));
		assertFalse(model.gameOver());
		model.getPlayer().addPokemon(new DatabaseReader().getPokemonByID(62, 1));
		assertFalse(model.gameOver());
		model.getPlayer().addPokemon(new DatabaseReader().getPokemonByID(63, 1));
		assertTrue(model.gameOver());
	}
	
	@Test
	public void testGameOverAgain() {
		PokemonModel model = new PokemonModel();
		for(int i = 1; i <= 59; i++) {
			model.getPC().getPokemon().add(new DatabaseReader().getPokemonByID(i, 1));
		}
		model.getPlayer().addPokemon(new DatabaseReader().getPokemonByID(60, 1));
		model.getPlayer().addPokemon(new DatabaseReader().getPokemonByID(61, 1));
		model.getPlayer().addPokemon(new DatabaseReader().getPokemonByID(62, 1));
		assertFalse(model.gameOver());
		model.getPlayer().addPokemon(new DatabaseReader().getPokemonByID(62, 1));
		assertFalse(model.gameOver());
		model.getPlayer().addPokemon(new DatabaseReader().getPokemonByID(63, 1));
		assertTrue(model.gameOver());
	}
	
	@Test
	public void testGameOverDeadBoss() {
		PokemonModel model = new PokemonModel();
		for(int i = 1; i <= 59; i++) {
			model.getPC().getPokemon().add(new DatabaseReader().getPokemonByID(i, 1));
		}
		assertFalse(model.gameOver());
		model.getGymZone().getBoss().killBoss();
		for(int i = 0; i < model.getGymZone().getBoss().getPokemon().size(); i++) {
			System.out.println(model.getGymZone().getBoss().getPokemon().get(i).getMaxHp() - model.getGymZone().getBoss().getPokemon().get(i).getDamage() );
		}
		assertTrue(model.getGymZone().getBoss().istrainerOutOfPokemon());
		assertTrue(model.gameOver());

	}

}
