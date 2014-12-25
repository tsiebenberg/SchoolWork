package pokemonHierarchy;

public interface IMove {

	public abstract int getMOVE_ID();

	public abstract int getBASE_DAMAGE();

	public abstract String getMOVE_NAME();

	public abstract ElementType getELEMENT_TYPE();

}