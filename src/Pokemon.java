
public class Pokemon {
	// instance data
	protected int attack;
	protected int defense;
	protected int speed;
	protected int hp;
	protected String name;
	protected String type1;
	protected String type2;
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
	public Pokemon(String name, int hp, int attack, int defense, int speed, String type1, String type2) {
		this.attack = attack +  ((int)(Math.random() *17));
		this.defense = defense + ((int)(Math.random() *17));
		this.speed = speed + ((int)(Math.random() *17));
		this.hp = hp + ((int) Math.random()*17);
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;
	}
	/**
	 * Constructs a Pokemon object with 1 type overrides the 2 type pokemon constructor
	 * @param name Name of the Pokemon
	 * @param hp of Pokemon
	 * @param attack stat
	 * @param defense of pokemon
	 * @param speed of pokemon
	 * @param primary type of pokemon
	 */
	
	public Pokemon(String name, int hp, int attack, int defense, int speed, String type1) {
		this.attack = attack +  ((int)(Math.random() *17));
		this.defense = defense + ((int)(Math.random() *17));
		this.speed = speed + ((int)(Math.random() *17));
		this.hp = hp + ((int) Math.random()*17);
		this.name = name;
		this.type1 = type1;
		this.type2 = null;
	}

	// instance methods
	public int attack(Pokemon attacker, String attack, Pokemon attacked) {
		double stab =1;
		if(attacker.getType1().equals(attack) || attacker.getType2().equals(attack)) stab = 1.5;
		double random = stab *(Math.random()*0.15 +0.85) ; 
		int damage = (int) Math.floor((((((((2*100)/5)+2)*attacker.getAttack()*Integer.MIN_VALUE)/attacked.getDefense())/50)+2));
		damage = (int) Math.floor(damage *random);
		return attacked.setHp(damage);
	}
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
