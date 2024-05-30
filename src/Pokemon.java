import java.util.ArrayList; //from https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
public class Pokemon {
	// instance data
	protected int attack;
	protected int spAttack;
	protected int spDefense;
	protected int defense;
	protected int speed;
	protected int hp;
	protected String name;
	protected String type1;
	protected String type2;
	protected int dex;
	protected int maxHp;
	protected int baseStats;
	protected ArrayList<Move> moves = new ArrayList<Move>();
	
	//default constructor 
	public Pokemon() {

	}
	/**
	 * constructs the pokemon objects
	 * @param name the name of the pokemon 
	 * @param hp the hp of the pokemon 
	 * @param attack the attack stat of the pokemon 
	 * @param spAttack the special attack stat of the pokemon
	 * @param defense the defense of the pokemon 
	 * @param spDefense the special defense of the pokemon 
	 * @param speed the speed of the pokemon 
	 * @param type1 the primary type of the pokemon 
	 * @param type2 the secondary type of the pokemon 
	 */
	public Pokemon(String name, int hp, int attack, int spAttack, int defense, int spDefense, int speed,
			String type1, String type2, int dex, int base ) {
		// stat calculations scraped from Pokemon database
		this.attack = 2 * attack + 57;
		this.spAttack = 2 * spAttack + 57;
		this.defense = 2 * defense + 57;
		this.spDefense = 2 * spDefense + 57;
		this.speed = 2 * speed + 57;
		this.hp = 2 * hp + 162;
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;	
		this.dex = dex;
		this.baseStats = base;
		maxHp = this.hp;
		Move j = Move.getMove((int) (Math.random()* (Move.moveLength())));
		if(type1.equals("Normal")) {
			if(this.attack >= this.spAttack) moves.add( Move.bodySlam);
			else  moves.add(  Move.recover);
		}
		if(type1.equals("Fighting")) {
			if(this.attack >= this.spAttack) moves.add(  Move.closeCombat);
			else moves.add(  Move.auraSphere);
		}

		if(type1.equals("Flying")) {
			if(this.attack <= this.spAttack)moves.add(  Move.airSlash);
			else moves.add(  Move.acrobatics);
		}
		if(type1.equals("Poison")) {
			if(this.attack >= this.spAttack) moves.add( Move.gunkShot);
			else moves.add(  Move.sludgeWave);
		}
		if(type1.equals("Ground")) {
			if(this.attack >= this.spAttack) moves.add(  Move.earthquake);
			else moves.add(  Move.earthPower);
		}
		if(type1.equals("Rock")) {
			if(this.attack >= this.spAttack) moves.add(  Move.rockSlide);
			else moves.add(  Move.powerGem);
		}
		if(type1.equals("Bug")) moves.add(  Move.uTurn);
		if(type1.equals("Ghost")) {
			if(this.attack <= this.spAttack) moves.add(  Move.shadowBall);
			else moves.add(  Move.poltergeist);
		}
		if(type1.equals("Steel")) {
			if(this.attack >= this.spAttack) moves.add(  Move.ironHead);
			else moves.add(  Move.flashCannon);
		}
		if(type1.equals("Fire")) {
			if(this.attack <= this.spAttack) moves.add(  Move.flamethrower);
			else moves.add(  Move.fireLash);
		}
		if(type1.equals("Water")) {
			if(this.attack >= this.spAttack) moves.add(  Move.liquidation);
			else moves.add(  Move.hydroPump);
		}
		if(type1.equals("Grass")) {
			if(this.attack >= this.spAttack) moves.add(  Move.leafBlade);
			else moves.add(  Move.energyBall);
		}
		if(type1.equals("Electric")) {
			moves.add(  Move.thunderbolt);
		}
		if(type1.equals("Psychic")) moves.add(  Move.psybeam);
		if(type1.equals("Ice")) {
			if(this.attack <= this.spAttack) moves.add( Move.icebeam);
			else moves.add(  Move.icebeam);
		}
		if(type1.equals("Dragon")) {
			if(this.attack >= this.spAttack) moves.add(  Move.outrage);
			else moves.add(  Move.dragonPulse);
		}
		if(type1.equals("Dark")) {
			if(this.attack >= this.spAttack) moves.add(  Move.crunch);
			else moves.add(  Move.darkPulse);
		}
		if(type1.equals("Fairy")) {
			if(this.attack <= this.spAttack) moves.add(  Move.moonBlast);
			else moves.add(  Move.playRough);
		}
		if(type2.length() < 2) {
			while(j.equals(moves.get(0))) {
				j = Move.getMove((int) (Math.random()* (Move.moveLength())));
			}
			moves.add(j);
			while(j.equals(moves.get(0))|| j.equals(moves.get(1))) {
				j = Move.getMove((int) (Math.random()* (Move.moveLength())));
			}
			moves.add(j);
			while(j.equals(moves.get(0))|| j.equals(moves.get(1))
					|| j.equals(moves.get(2))) {
				j = Move.getMove((int) (Math.random()* (Move.moveLength())));
			}
			moves.add(j);
		}

		else {	
			if(type2.equals("Normal")) {
				if(this.attack <= this.spAttack) moves.add(  Move.bodySlam);
				else  moves.add(   Move.recover);
			}
			if(type2.equals("Fighting")) {
				if(this.attack >= this.spAttack) moves.add(   Move.closeCombat);
				else moves.add(   Move.auraSphere);
			}

			if(type2.equals("Flying")) {
				if(this.attack <= this.spAttack)moves.add(   Move.airSlash);
				else moves.add(   Move.acrobatics);
			}
			if(type2.equals("Poison")) {
				if(this.attack >= this.spAttack) moves.add(   Move.gunkShot);
				else moves.add(   Move.sludgeWave);
			}
			if(type2.equals("Ground")) {
				if(this.attack >= this.spAttack) moves.add(  Move.earthquake);
				else moves.add(   Move.earthPower);
			}
			if(type2.equals("Rock")) {
				if(this.attack >= this.spAttack) moves.add(  Move.rockSlide);
				else moves.add(   Move.powerGem);
			}
			if(type2.equals("Bug")) moves.add(   Move.uTurn);
			if(type2.equals("Ghost")) {
				if(this.attack <= this.spAttack) moves.add(   Move.shadowBall);
				else moves.add(   Move.poltergeist);
			}
			if(type2.equals("Steel")) {
				if(this.attack >= this.spAttack) moves.add(   Move.ironHead);
				else moves.add(   Move.flashCannon);
			}
			if(type2.equals("Fire")) {
				if(this.attack <= this.spAttack) moves.add(   Move.flamethrower);
				else moves.add(   Move.fireLash);
			}
			if(type2.equals("Water")) {
				if(this.attack >= this.spAttack) moves.add(   Move.liquidation);
				else moves.add(  Move.hydroPump);
			}
			if(type2.equals("Grass")) {
				if(this.attack >= this.spAttack) moves.add(   Move.leafBlade);
				else moves.add(   Move.energyBall);
			}
			if(type2.equals("Electric")) {
				moves.add(   Move.thunderbolt);
			}
			if(type2.equals("Psychic")) moves.add(   Move.psybeam);
			if(type2.equals("Ice")) {
				if(this.attack <= this.spAttack) moves.add(   Move.icebeam);
				else moves.add(   Move.icicleCrash);
			}
			if(type2.equals("Dragon")) {
				if(this.attack >= this.spAttack) moves.add(   Move.outrage);
				else moves.add(   Move.dragonPulse);
			}
			if(type2.equals("Dark")) {
				if(this.attack >= this.spAttack) moves.add(   Move.crunch);
				else moves.add(   Move.darkPulse);
			}
			if(type2.equals("Fairy")) {
				if(this.attack <= this.spAttack) moves.add(   Move.moonBlast);
				else moves.add(   Move.playRough);
			}
			while(j.equals(moves.get(0))|| j.equals(moves.get(1))
					) {
				j = Move.getMove((int) (Math.random()* (Move.moveLength())));
			}
			moves.add(j);
			while(j.equals(moves.get(0))|| j.equals(moves.get(1))
					|| j.equals(moves.get(2))) {
				j = Move.getMove((int) (Math.random()* (Move.moveLength())));
			}
			moves.add(j);

		}
	}
	// instance methods
	public String getType1() {return type1;}
	public String getType2() {return type2;	}
	public int setHp(int damage) {
		if(damage == -2) {
			hp = maxHp;
			return maxHp;
		}
		hp -= damage;
		if(hp < 0) hp = 0;
		return hp;}
	public int getAttack() {return attack;}
	public int getSPAttack() {return spAttack;}
	public int getDefense() {return defense;}
	public int getSPDefense() {return spDefense;}
	public int getSpeed() {return speed;}
	public int getHp() {return hp;}
	public int getDex() {return dex;}
	public int getMaxHp() { return maxHp;}
	public int getBase() { return baseStats;}
	public String getName() {
		if (name.contains("Mega ")) {
			return name.substring(name.indexOf("Mega "));
		}
		return name;
	}
	public String getRawName() {
		return name.toLowerCase();
	}
	public Move getMove(int i) {return moves.get(i);}
}
