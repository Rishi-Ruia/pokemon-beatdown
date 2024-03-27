import java.io.*; //from https://docs.oracle.com/javase/8/docs/api/java/io/package-summary.html
import java.util.*; // from https://docs.oracle.com/javase/8/docs/api/java/util/package-summary.html
public class Game {
	protected static String [] name;
	protected static String [] type2;
	protected static String [] type1;
	protected static int [] attack;
	protected static int [] speed;
	protected static int [] defense;
	protected static int [] hp;
	protected static int [] spAttack;
	protected static int [] spDefense;
	protected static Pokemon[] poke = new Pokemon[801];
	protected static GUI game;
	protected static Player user;
	public static void main(String[] args) throws IOException {
		Move.addMoves();
		setup();
		GUI game = new GUI();
		user = new Player();
		AI ai = new AI();
		System.out.print(ai.lost());
		
	}
	public static void setup() throws IOException{
		poke[0] = (new Pokemon("Raeed Rahman", 100,100,100,100, 100 , 100, "Dragon", "Fairy"));
		String line = "";
		String[][] pokemons = new String[801][13];
		BufferedReader br = new BufferedReader(new FileReader("Copy of All Pokemon Data Spreadsheet_exported.csv"));
		int i = 0;
		String[] pokemon = new String [pokemons.length];
		while((line = br.readLine()) != null) {
			pokemon = line.split(",");
			pokemons[i] = pokemon;
			i++;
		}
		br.close();
		name = new String[pokemons.length];
		type2 = new String[pokemons.length];
		type1 = new String[pokemons.length];
		attack = new int[pokemons.length];
		speed = new int[pokemons.length];
		defense = new int[pokemons.length];
		hp = new int[pokemons.length];
		spAttack = new int[pokemons.length];
		spDefense = new int[pokemons.length];
		for(i = 1; i < pokemons.length; i++) {
			for(int j =0; j < pokemons[i].length; j++) {
				pokemons[i][j] = pokemons[i][j].substring(1, pokemons[i][j].length()-1);				
			}
			name[i] = pokemons[i][1];
			type2[i] = pokemons[i][3];
			hp[i] = Integer.parseInt(pokemons[i][4]);
			type1[i] = pokemons[i][5];
			attack[i] = (Integer.parseInt(pokemons[i][6]));
			spAttack[i]=Integer.parseInt(pokemons[i][8]);
			defense[i] = (Integer.parseInt(pokemons[i][7]));
			spDefense[i] =Integer.parseInt(pokemons[i][9]);
			speed[i] = Integer.parseInt(pokemons[i][10]);
			poke[i]= (new Pokemon(name[i], hp[i], attack[i], spAttack[i], defense[i], spDefense[i], speed[i], type1[i], type2[i]));
		}
		
	}
	public static String attack(Pokemon attacker, Move attack, Pokemon attacked, boolean special) {
		if(attack.getName().equals("recover")) {
			int heal = (int)(attacker.getHp()*.25);
			attacker.setHp(-heal);
			return attacker.getName() + " healed for "+ heal + " and is now " + attacker.getHp() ;
		}
		double stab =1;
		if(attacker.getType1().equals(attack.getName()) || attacker.getType2().equals(attack.getName())) stab = 1.5;
		double random = stab *(Math.random()*0.15 +0.85);
		int damage = damageCalc( attacker,  attack,  attacked, special);
		damage = (int) Math.floor(damage *random);
		if((int) (Math.random() *101) <= attack.getAccuracy()) {
			attacked.setHp(damage);
			if(attacked.getHp() <= 0) {
				attacked.setHp(0);
				return "you used " + attack.getName() + " and " + attacked.getName() + " fainted! " ;
			}
			return "you used " + attack.getName() + " it did " + damage + " damage to " + attacked.getName();
		}
		return attack.getName() + " missed!";
	}
	public static int damageCalc(Pokemon attacker, Move attack, Pokemon attacked, boolean special) {
		if(!special)
			return  (int) ((((((((2*100)/5)+2)*attacker.getAttack()
					*attack.getPower())/attacked.getDefense())/50)+2)*Move.effective(attack, attacked));
		return  (int) ((((((((2*100)/5)+2)*attacker.getSPAttack()
				*attack.getPower())/attacked.getSPDefense())/50)+2)*Move.effective(attack, attacked));
	}
}