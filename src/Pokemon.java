
public class Pokemon {
	// instance data
	protected int attack;
	protected int defense;
	protected int speed;
	protected int hp;
	protected String name;
	protected String type1;
	protected String type2;
	protected Move move1;
	protected Move move2;
	protected Move move3;
	protected Move move4;
	protected boolean hasItem;
	/** TODO UPDATE CONSTRUCTOR TO INIT NEW INSTANCE VARIABLES **/
	
	// constructors
	/**
	 * Constructs a Pokemon object
	 * @param name Name of the Pokemon
	 * @param hp of Pokemon
	 * @param attack stat
	 * @param defense of pokemon
	 * @param speed of pokemon
	 * @param primary type of pokemon
	 * @param secondary type of pokemon
	 */
	public Pokemon(String name, int hp, int attack, int defense, int speed, String type1, String type2 ) {
		this.attack = (int) ((0.01 * (2 * attack + Math.floor(0.25 * (Math.random() * 33))) * 100) + 5) ;
		this.defense = (int) ((0.01 * (2 * defense + Math.floor(0.25 * (Math.random() * 33))) * 100) + 5) ;
		this.speed = (int) ((0.01 * (2 * speed + Math.floor(0.25 * (Math.random() * 33))) * 100) + 5);
		this.hp = (int) (0.01 * (2 * hp + Math.floor(0.25 * (Math.random() * 33))) * 100) + 10;
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;		
	}
	public void removeItem() {
		hasItem = false;
	}
	// instance methods
	public String getType1() {
		return type1;
	}
	public String getType2() {
		return type2;
	}
	public int setHp(int damage) {
		hp -= damage;
		if(hp < 0) hp = 0;
		return hp;
	}
	public int getAttack() {
		return attack;
	}
	public int getDefense() {
		return defense;
	}
	public int getStamina() {
		return speed;
	}
	public int getHp() {
		return hp;
	}
	public String getName() {
		return name;
	}

}
