package mapHierarchy;

import playerHierarchy.Player;
import buildingHierarchy.HealStation;
import buildingHierarchy.PC;
import buildingHierarchy.Store;
import elements.Rock;

public class PokeCenterZone extends Zone {

	private final int spawnX = 7, spawnY = 4;
	private Store store;
	private HealStation healStation;
	private PC pc;

	public PokeCenterZone() {
		super(8, 10);
		healStation = new HealStation();
		store = new Store();
		pc = new PC();
		setMap();
	}
	
	public void healPokemon(Player player) {
		healStation.healPokemon(player);
	}

	public int getSpawnX() {
		return spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	@Override
	public void setMap() {
		setDoor();
		setElements();
	}

	private void setDoor() {
		squareArray[7][4].removeObject(Rock.class);
		squareArray[7][4].addObject('D');
		squareArray[7][4].setIsWalkable(true);
	}

	@Override
	public void setElements() {
		setDeskArea();
		setStoreAndHealStation();
	}

	private void setDeskArea() {


		squareArray[1][4].addObject('L');
		squareArray[1][4].setIsWalkable(false);
		squareArray[2][4].addObject('L');
		squareArray[2][4].setIsWalkable(false);
		
		squareArray[1][6].addObject('R');
		squareArray[1][6].setIsWalkable(false);		
		squareArray[2][6].addObject('R');
		squareArray[2][6].setIsWalkable(false);	
		
		squareArray[3][4].addObject('F');
		squareArray[3][4].setIsWalkable(false);
		squareArray[3][5].addObject('F');
		squareArray[3][5].setIsWalkable(false);
		squareArray[3][6].addObject('F');
		squareArray[3][6].setIsWalkable(false);
		
		squareArray[2][5].addObject('T');
		squareArray[2][5].setIsWalkable(false);
		

	}


	private void setStoreAndHealStation() {
		squareArray[1][5].addObject(healStation);
		squareArray[1][5].setIsWalkable(false);
		squareArray[1][2].addObject(store);
		squareArray[1][2].setIsWalkable(false);
		squareArray[1][8].addObject(pc);
		squareArray[1][8].setIsWalkable(false);
	}

	@Override
	public void setComputerPlayers() {
	}

	@Override
	public String toString() {
		return "PokeCenterZone";
	}

}
