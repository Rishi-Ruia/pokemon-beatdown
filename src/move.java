import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Arrays;
public class move {
	private String name;
	private String type;
	private int power;
	private int accuracy;
	public final static move thunderbolt = new move("thunderbolt", "Electric", 90, 100);
	public final static move flamethrower = new move("flamethrower", "Fire", 90, 100);
	public final static move icebeam = new move("ice beam", "Ice", 90, 100);
	public final static move ember = new move("ember", "Fire", 40, 100);
	public final static move pysbeam = new move("pysbeam", "Psychic", 65, 100);
	public final static move focusBlast = new move("focus blast", "Fighting", 120, 70);
	public final static move thunder = new move("thunder", "Electric", 110, 70);
	public final static move bodySlam = new move("body slam", "Normal", 80, 100);
	public final static move airSlash = new move("air slash", "Flying", 75, 95);
	public final static move huricane = new move("huricane", "Flying", 110, 70);
	public final static move sludgeWave = new move("sludge wave", "Poison", 95, 100);
	public final static move gunkShot = new move("gunk shot", "Poison", 120, 80);
	public final static move earthquake = new move("earthquake", "Ground", 100, 100);
	public final static move rockSlide = new move("rock Slide", "Rock", 75, 90);
	public final static move shadowBall = new move("shadow ball", "Ghost", 80, 100);
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
	public move(String name, String type, int power, int accuracy) {
		this.name = name;
		this.type = type;
		this.power = power;
		this.accuracy = accuracy;
	}
	public static double effective(move attack, Pokemon attacked) {
		String moveType = attack.getType();
		String attackedType1 = attacked.getType1();
		String attackedType2 = attacked.getType1();
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
	public String getName() {return name;}
	public String getType() {return type;}
	public int getPower() {return power;}
	public int getAccuracy() {return accuracy;}
}

