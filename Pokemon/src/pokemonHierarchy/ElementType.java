package pokemonHierarchy;

public enum ElementType {
	Electric(1), Fire(3), Grass(4), Normal(0), Psychic(6), Rock(5), Water(2), NULL(-1);

	private int num;
	 ElementType(int num){
		this.num = num;
	}
	 
	 public int getNum(){
		 return num;
	 }
}
