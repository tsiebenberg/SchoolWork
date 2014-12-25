package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import itemHierarchy.Pokeball;

import org.junit.Test;

import playerHierarchy.HumanPlayer;
import buildingHierarchy.Store;

public class TestStore {

	@Test
	public void testBuyingItem() {
		HumanPlayer player = new HumanPlayer(2, 2);
		Store store = new Store();
		Pokeball pokeBall = new Pokeball();
		assertEquals(2000, player.getMoney());
		assertEquals(0, player.getItems().size());
		assertTrue(store.buyItem(player, pokeBall));  //Item 1
		assertEquals(1, player.getItems().size());
		assertEquals(1800, player.getMoney());
		assertTrue(store.buyItem(player, pokeBall));  //Item 2
		assertEquals(2, player.getItems().size());
		assertEquals(1600, player.getMoney());
		assertTrue(store.buyItem(player, pokeBall));  //Item 3
		assertEquals(3, player.getItems().size());
		assertEquals(1400, player.getMoney());
		assertTrue(store.buyItem(player, pokeBall));  //Item 4
		assertEquals(4, player.getItems().size());
		assertEquals(1200, player.getMoney());
		for(int i = 0; i < player.getItems().size(); i++) {
			System.out.println(player.getItems().get(i).toString());
		}
	}
	
	@Test
	public void testBuyItemWhenOutOfMoney() {
		HumanPlayer player = new HumanPlayer(2, 2);
		Store store = new Store();
		Pokeball pokeBall = new Pokeball();
		assertEquals(2000, player.getMoney());
		player.setMoney(player.getMoney() * -1);
		assertEquals(0, player.getMoney());
		assertEquals(0, player.getItems().size());
		assertFalse(store.buyItem(player, pokeBall));
		assertEquals(0, player.getMoney());
		assertEquals(0, player.getItems().size());
	}
	
	@Test
	public void testSellItem() {
		HumanPlayer player = new HumanPlayer(2, 2);
		Store store = new Store();
		Pokeball pokeBall = new Pokeball();
		player.addItem(pokeBall);
		assertEquals(2000, player.getMoney());
		assertEquals(1, player.getItems().size());
		assertTrue(store.sellItem(player, pokeBall));
		assertEquals(2050, player.getMoney());
		assertEquals(0, player.getItems().size());
		assertFalse(store.sellItem(player, pokeBall));
		assertEquals(2050, player.getMoney());
		assertEquals(0, player.getItems().size());
	}
	
	@Test
	public void testBuyAndSell() {
		HumanPlayer player = new HumanPlayer(2, 2);
		Store store = new Store();
		Pokeball pokeBall = new Pokeball();
		assertEquals(2000, player.getMoney());
		assertEquals(0, player.getItems().size());
		assertTrue(store.buyItem(player, pokeBall));
		assertEquals(1800, player.getMoney());
		assertEquals(1, player.getItems().size());
		assertTrue(store.buyItem(player, pokeBall));
		assertEquals(1600, player.getMoney());
		assertEquals(2, player.getItems().size());
		assertTrue(store.sellItem(player, pokeBall));
		assertEquals(1650, player.getMoney());
		assertEquals(1, player.getItems().size());
		assertTrue(store.sellItem(player, pokeBall));
		assertEquals(1700, player.getMoney());
		assertEquals(0, player.getItems().size());
		assertFalse(store.sellItem(player, pokeBall));
	}
	
	@Test
	public void testBuyAndSellAgain() {
		HumanPlayer player = new HumanPlayer(2, 2);
		Store store = new Store();
		Pokeball pokeBall = new Pokeball();
		// Buy 3 Pokeballs
		assertEquals(2000, player.getMoney());
		assertEquals(0, player.getItems().size());
		assertTrue(store.buyItem(player, pokeBall));
		assertTrue(store.buyItem(player, pokeBall));
		assertTrue(store.buyItem(player, pokeBall));
		assertEquals(1400, player.getMoney());
		assertEquals(3, player.getItems().size());
		// Sell 3 Pokeballs
		assertTrue(store.sellItem(player, pokeBall));
		assertEquals(1450, player.getMoney());
		assertEquals(2, player.getItems().size());
		assertTrue(store.sellItem(player, pokeBall));
		assertEquals(1500, player.getMoney());
		assertEquals(1, player.getItems().size());
		assertTrue(store.sellItem(player, pokeBall));
		assertEquals(1550, player.getMoney());
		assertEquals(0, player.getItems().size());

		


	}
	
}
