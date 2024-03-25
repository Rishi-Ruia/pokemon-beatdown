
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
		this(type1, type2);
		this.attack = (int) ((0.01 * (2 * attack + Math.floor(0.25 * (Math.random() * 33))) * 100) + 5) ;
		this.defense = (int) ((0.01 * (2 * defense + Math.floor(0.25 * (Math.random() * 33))) * 100) + 5) ;
		this.speed = (int) ((0.01 * (2 * speed + Math.floor(0.25 * (Math.random() * 33))) * 100) + 5);
		this.hp = (int) (0.01 * (2 * hp + Math.floor(0.25 * (Math.random() * 33))) * 100) + 10;
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;	

	}
	private Pokemon (String type1, String type2) {
		Move j = Move.getMove((int) (Math.random()* (Move.moveLength())));
		if(type1.equals("Normal")) move1 = Move.bodySlam; 
		if(type1.equals("Fighting"))move1 = Move.auraSphere;
		if(type1.equals("Flying")) move1 = Move.airSlash;
		if(type1.equals("Poison")) move1 = Move.sludgeWave;
		if(type1.equals("Ground")) move1 = Move.earthquake;
		if(type1.equals("Rock")) move1 = Move.rockSlide;
		if(type1.equals("Bug")) move1 = Move.uTurn;
		if(type1.equals("Ghost")) move1 = Move.shadowBall;
		if(type1.equals("Steel")) move1 = Move.ironHead;
		if(type1.equals("Fire")) move1 = Move.flamethrower;
		if(type1.equals("Water")) move1 = Move.scald;
		if(type1.equals("Grass")) move1 = Move.leafBlade;
		if(type1.equals("Electric")) move1 = Move.thunderbolt;
		if(type1.equals("Psychic")) move1 = Move.psybeam;
		if(type1.equals("Ice")) move1 = Move.icebeam;
		if(type1.equals("Dragon")) move1 = Move.outrage;
		if(type1.equals("Dark")) move1 = Move.crunch;
		if(type1.equals("Fairy")) move1 = Move.moonBlast;
		if(type2.length() < 2) {
			while(j.equals(move1)|| j.equals(move2)
					|| j.equals(move3)) {
				j = Move.getMove((int) (Math.random()* (Move.moveLength())));
			}
			move2 = j;
			while(j.equals(move1)|| j.equals(move2)
					|| j.equals(move3)) {
				j = Move.getMove((int) (Math.random()* (Move.moveLength())));
			}
			move3 = j;
			while(j.equals(move1)|| j.equals(move2)
					|| j.equals(move3)) {
				j = Move.getMove((int) (Math.random()* (Move.moveLength())));
			}
			move4 = j;
		}
		else {
			if(type2.equals("Normal")) move2 = Move.bodySlam; 
			if(type2.equals("Fighting"))move2 = Move.auraSphere;
			if(type2.equals("Flying")) move2 = Move.airSlash;
			if(type2.equals("Poison")) move2 = Move.sludgeWave;
			if(type2.equals("Ground")) move2 = Move.earthquake;
			if(type2.equals("Rock")) move2 = Move.rockSlide;
			if(type2.equals("Bug")) move2 = Move.uTurn;
			if(type2.equals("Ghost")) move2 = Move.shadowBall;
			if(type2.equals("Steel")) move2 = Move.ironHead;
			if(type2.equals("Fire")) move2 = Move.flamethrower;
			if(type2.equals("Water")) move2 = Move.scald;
			if(type2.equals("Grass")) move2 = Move.leafBlade;
			if(type2.equals("Electric")) move2 = Move.thunderbolt;
			if(type2.equals("Psychic")) move2 = Move.psybeam;
			if(type2.equals("Ice")) move2 = Move.icebeam;
			if(type2.equals("Dragon")) move2 = Move.outrage;
			if(type2.equals("Dark")) move2 = Move.crunch;
			if(type2.equals("Fairy")) move2 = Move.moonBlast;
			while(j.equals(move1)|| j.equals(move2)
					|| j.equals(move3)) {
				j = Move.getMove((int) (Math.random()* (Move.moveLength())));
			}
			move3 = j;
			while(j.equals(move1)|| j.equals(move2)
					|| j.equals(move3)) {
				j = Move.getMove((int) (Math.random()* (Move.moveLength())));
			}
			move4 = j;
		}
	}
	// instance methods
	public String getType1() {return type1;}
	public String getType2() {return type2;	}
	public int setHp(int damage) {
		hp -= damage;
		if(hp < 0) hp = 0;
		return hp;
	}
	public int getAttack() {return attack;}
	public int getDefense() {return defense;}
	public int getStamina() {return speed;}
	public int getHp() {return hp;}
	public String getName() {return name;}
	public Move getMove1() { return move1;}
	public Move getMove2() { return move2;}
	public Move getMove3() { return move3;}
	public Move getMove4() { return move4;}
}
