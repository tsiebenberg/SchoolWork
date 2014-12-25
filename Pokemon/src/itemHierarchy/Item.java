package itemHierarchy;

public abstract class Item {
	
	protected int price;
	
	public Item() {
		
	}
	
	public int getPrice() {
		return price;
	}
	
	public int sellPrice() {
		return price / 4;
	}
	
	public abstract String toString();
}
