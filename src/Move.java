import java.util.*; //from https://docs.oracle.com/javase/8/docs/api/java/util/package-summary.html
public class Move {
	//instance data
	private String name;
	private String type;
	private int power;
	private int accuracy;
	private boolean special;
	//list of every move that is possible for a pokemon to have
	private static ArrayList<Move> moves = new ArrayList<Move>();
	private static ArrayList<Move> P = new ArrayList<Move>();
	private static ArrayList<Move> S = new ArrayList<Move>();
	public final static Move thunderbolt = new Move("Thunderbolt", "Electric", 90, 100, true);
	public final static Move flamethrower = new Move("Flamethrower", "Fire", 90, 100, true);
	public final static Move icebeam = new Move("Ice Beam", "Ice", 90, 100, true);
	public final static Move energyBall = new Move("Energy Ball", "Grass", 80, 100, true);
	public final static Move psybeam = new Move("Psybeam", "Psychic", 65, 100, true);
	public final static Move focusBlast = new Move("Focus Blast", "Fighting", 120, 70, true);
	public final static Move auraSphere = new Move("Aura Sphere", "Fighting", 80, 100, true);
	public final static Move thunder = new Move("Thunder", "Electric", 110, 70, true);
	public final static Move bodySlam = new Move("Body Slam", "Normal", 80, 100, false);
	public final static Move airSlash = new Move("Air Slash", "Flying", 75, 95, true);
	public final static Move huricane = new Move("Hurricane", "Flying", 110, 70, true);
	public final static Move sludgeWave = new Move("Sludge Wave", "Poison", 95, 100, true);
	public final static Move gunkShot = new Move("Gunk Shot", "Poison", 120, 80, false);
	public final static Move earthquake = new Move("Earthquake", "Ground", 100, 100, false);
	public final static Move rockSlide = new Move("Rock Slide", "Rock", 75, 90, false);
	public final static Move shadowBall = new Move("Shadow Ball", "Ghost", 80, 100, true);
	public final static Move uTurn = new Move("U-Turn", "Bug", 70, 100, false);
	public final static Move ironHead = new Move("Iron Head", "Steel", 80, 100, false);
	public final static Move fireBlast = new Move("Fire Blast", "Fire", 110, 85, true);
	public final static Move liquidation = new Move("Liquidation", "Water", 85, 100, false);
	public final static Move hydroPump = new Move("Hydro Pump", "Water", 110, 80, true);
	public final static Move leafBlade = new Move("Leaf Blade", "Grass", 80, 100, false);
	public final static Move icicleCrash = new Move("Icicle Crash", "Ice", 85, 90, false);
	public final static Move outrage = new Move("Outrage", "Dragon", 110, 100, false);
	public final static Move crunch = new Move("Crunch", "Dark", 80, 100, false);
	public final static Move moonBlast = new Move("Moon Blast", "Fairy", 95, 100, true);
	public final static Move recover = new Move("Recover", "Normal", 0, 100, true);
	public final static Move earthPower = new Move("Earth Power", "Ground", 90, 100, true);
	public final static Move flashCannon = new Move("Flash Cannon", "Steel", 80, 100, true);
	public final static Move fireLash = new Move("Fire Lash", "Fire", 90, 100, false);
	public final static Move poltergeist = new Move("Poltergeist", "Ghost", 110, 90, false);
	public final static Move dragonPulse = new Move("Dragon Pulse", "Dragon", 80, 100, true);
	public final static Move powerGem = new Move("Power Gem", "Rock", 80, 100, true);
	public final static Move darkPulse = new Move("Dark Pulse", "Dark", 80, 100, true);
	public final static Move playRough = new Move("Play Rough", "Fairy", 90, 90, false);
	public final static Move acrobatics = new Move("Acrobatics", "Flying", 110, 100, false);
	public final static Move closeCombat = new Move("Close Combat", "Fighting", 120, 100, false);
	protected static String [][] NORMAL = { {},
			{"Rock", "Steel"},
			{"Ghost"}};
	protected static String [][] FIGHTING = {{"Normal", "Rock", "Steel", "Ice", "Dark"},
			{"Flying", "Poison", "Bug", "Psychic", "Fairy"},
			{"Ghost"}};
	protected static String [][] FLYING ={{"Fighting", "Grass"},
			{"Rock", "Steel", "Electric"},
			{}};
	protected static String [][] POISON = {{"Grass"},
			new String[]{"Poison", "Ground", "Rock", "Bug", "Fairy"},
			new String[]{"Steel"}};
	protected static String [][] GROUND = {(new String[]{"Poison", "Rock", "Steel", "Fire", "Electric"}),
			new String[]{"Bug", "Grass"},
			new String[]{"Flying"}};
	protected static String [][] ROCK = { new String[]{"Flying", "Bug", "Fire"},
			new String[]{"Fighting", "Ground", "Steel"},
			new String[]{}};
	protected static String [][] BUG = {(new String[]{"Grass", "Psychic", "Dark"}),
			new String[]{"Fighting", "Flying", "Poison", "Ghost", "Steel", "Fire", "Fairy"},
			new String[]{}};
	protected static String [][] GHOST = {new String[]{"Ghost", "Psychic"},
			new String[]{"Steel", "Dark"},
			new String[]{"Normal"}};
	protected static String [][] STEEL = {new String[]{"Rock", "Ice", "Fairy"},
			new String[]{"Steel", "Fire", "Water", "Electric", "Fairy"},
			new String[]{}};
	protected static String [][] FIRE =  {new String[]{"Grass", "Ice", "Bug"},
			new String[]{"Fire", "Water", "Rock", "Dragon"},
			new String[]{}};
	protected static String [][] WATER = {new String[]{"Fire", "Rock", "Ground"},
			new String[]{"Water", "Grass", "Dragon"},
			new String[]{}};
	protected static String [][] GRASS = {new String[]{"Water", "Ground", "Rock"},
			new String[]{"Fire", "Grass", "Poison", "Flying", "Bug", "Dragon", "Steel"},
			new String[]{}};
	protected static String [][] ELECTRIC = {new String[]{"Water", "Flying"},
			new String[]{"Grass", "Electric", "Dragon"},
			new String[]{"Ground"}};
	protected static String [][] PSYCHIC = {new String[]{"Fighting", "Poison"},
			new String[]{"Psychic", "Steel"},
			new String[]{"Dark"}};
	protected static String [][] ICE =  {new String[]{"Grass", "Ground", "Flying", "Dragon"},
			new String[]{"Fire", "Water", "Ice", "Steel"},
			new String[]{}};
	protected static String [][] DRAGON = {new String[]{"Dragon"},
			new String[]{"Steel"},
			new String[]{"Fairy"}};
	protected static String [][] DARK = {new String[]{"Psychic", "Ghost"},
			new String[]{"Fighting", "Dark", "Steel", "Fairy"},
			new String[]{}};
	protected static String [][] FAIRY = {new String[]{"Dark", "Dragon", "Fighting"}, 
			new String[] {"Fire", "Steel", "poison"},
			new String[] {}};
	//adds moves to the array list
	public static void addMoves() {
		moves.add(auraSphere); S.add(auraSphere);
		moves.add(earthquake); P.add(earthquake);
		moves.add(energyBall); S.add(energyBall);
		moves.add(fireBlast); S.add(fireBlast);
		moves.add(flamethrower); S.add(flamethrower);
		moves.add(huricane); S.add(huricane);
		moves.add(icebeam); S.add(icebeam);
		moves.add(icicleCrash); P.add(icicleCrash);
		moves.add(ironHead); P.add(ironHead);
		moves.add(leafBlade); P.add(leafBlade);
		moves.add(outrage); P.add(outrage);
		moves.add(psybeam); S.add(psybeam);
		moves.add(recover); S.add(recover); P.add(recover);
		moves.add(rockSlide); P.add(rockSlide);
		moves.add(sludgeWave); S.add(sludgeWave);
		moves.add(thunder); S.add(thunder);
		moves.add(thunderbolt); S.add(thunderbolt);
		moves.add(airSlash); S.add(airSlash);
		moves.add(bodySlam); P.add(bodySlam);
		moves.add(crunch); P.add(crunch);
		moves.add(closeCombat); P.add(closeCombat);
		moves.add(focusBlast); S.add(focusBlast);
		moves.add(gunkShot); P.add(gunkShot);
		moves.add(hydroPump); S.add(hydroPump);
		moves.add(moonBlast); S.add(moonBlast);
		moves.add(liquidation); P.add(liquidation);
		moves.add(shadowBall); S.add(shadowBall);
		moves.add(uTurn); P.add(uTurn);
		moves.add(flashCannon); S.add(flashCannon);
		moves.add(fireLash); P.add(fireLash);
		moves.add(poltergeist); P.add(poltergeist);
		moves.add(dragonPulse); S.add(dragonPulse);
		moves.add(powerGem); S.add(powerGem);
		moves.add(acrobatics); P.add(acrobatics);
		moves.add(darkPulse); S.add(darkPulse);
		moves.add(playRough); P.add(playRough);
	}
	//returns the size of the array list of moves
	public static int moveLength() {return moves.size();}
	public static int PmoveLength() {return P.size();}
	public static int SmoveLength() {return S.size();}
	//returns a specific move at the position j
	public static Move getMove(int j) { return moves.get(j);}
	public static Move getPMove(int j) { return P.get(j);}
	public static Move getSMove(int j) { return S.get(j);}
	//constructor to create moves
	public Move(String name, String type, int power, int accuracy, boolean special) {
		this.name = name;
		this.type = type;
		this.power = power;
		this.accuracy = accuracy;
		this.special = special;
	}
	//deafault constructor
	public Move() {
		
	}
	/*
	 * calculates the effectiveness of the move against the attacker pokemon uses a dictionary with a bunch of 2d arrays 
	 * to find if a type is supper effective or not returns a double value with the effectiveness	
	*/
	public static double effective(Move attack, Pokemon attacked) {
		String moveType = attack.getType();
		String attackedType1 = attacked.getType1();
		String attackedType2 = attacked.getType2();
		Dictionary<String, String[][]> types= new Hashtable<>();
		types.put("Normal",NORMAL );
		types.put("Fighting",FIGHTING );
		types.put("Flying",FLYING );
		types.put("Poison",POISON );
		types.put("Ground",GROUND );
		types.put("Rock",ROCK );
		types.put("Bug",BUG );
		types.put("Ghost",GHOST );
		types.put("Steel",STEEL );
		types.put("Fire",FIRE );
		types.put("Water",WATER );
		types.put("Grass",GRASS );
		types.put("Electric",ELECTRIC );
		types.put("Psychic",PSYCHIC );
		types.put("Ice",ICE );
		types.put("Dragon",DRAGON );
		types.put("Dark",DARK );
		types.put("Fairy",FAIRY );
		if(attackedType2.length() <=2) {
			if(Arrays.asList(types.get(moveType)[0])
					.contains(attackedType1)) return 2;
			else if(Arrays.asList(types.get(moveType)[1])
					.contains(attackedType1)) return 0.5;
			else if(Arrays.asList(types.get(moveType)[2])
					.contains(attackedType1)) return 0;
			else return 1;
		}
		else {
			double effective;
			if(Arrays.asList(types.get(moveType)[0])
					.contains(attackedType1)) effective = 2;
			else if(Arrays.asList(types.get(moveType)[1])
					.contains(attackedType1)) effective = 0.5;
			else if(Arrays.asList(types.get(moveType)[2])
					.contains(attackedType1)) effective = 0;
			else effective = 1;
			if(Arrays.asList(types.get(moveType)[0])
					.contains(attackedType2)) effective *= 2;
			else if(Arrays.asList(types.get(moveType)[1])
					.contains(attackedType2)) effective *= 0.5;
			else if(Arrays.asList(types.get(moveType)[2])
					.contains(attackedType2)) effective *= 0;
			else effective *= 1;
			return effective;
		}
		
	}
	//helper methods
	public String getName() {return name;}
	public String getType() {return type;}
	public int getPower() {return power;}
	public int getAccuracy() {return accuracy;}
	public boolean isSpecial() {return special;}
}

