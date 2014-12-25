package pokemonHierarchy;

public class Move implements IMove {

	private int MOVE_ID;
	private int BASE_DAMAGE;
	private String MOVE_NAME;
	private ElementType ELEMENT_TYPE;
	
	public Move(String name, int id, int baseDamage, ElementType elementType){
		this.MOVE_NAME = name;
		this.MOVE_ID = id;
		this.BASE_DAMAGE = baseDamage;
		this.ELEMENT_TYPE = elementType;
	}

	@Override
	public int getMOVE_ID() {
		return MOVE_ID;
	}

	@Override
	public int getBASE_DAMAGE() {
		return BASE_DAMAGE;
	}

	@Override
	public String getMOVE_NAME() {
		if (MOVE_NAME.indexOf('_') >= 0)
			return MOVE_NAME.replace('_', ' ');
		return MOVE_NAME;
	}

	@Override
	public ElementType getELEMENT_TYPE() {
		return ELEMENT_TYPE;
	}
	
	
	public String toString(){
		if (MOVE_NAME.indexOf('_') >= 0)
			return MOVE_NAME.replace('_', ' ');
		return MOVE_NAME;
		
	}

}
