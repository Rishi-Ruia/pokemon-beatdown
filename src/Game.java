import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class Game {
	protected static String [] name;
	protected static String [] type2;
	protected static String [] type1;
	protected static int [] attack;
	protected static int [] speed;
	protected static int [] defense;
	protected static int [] hp;
	protected static Pokemon[] poke = new Pokemon[801];
	public static void main(String[] args) throws IOException {
		setup();
		GUI game = new GUI();

	}
	public static void setup() throws IOException{
		Scanner scan = new Scanner(System.in);
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
		name = new String[pokemons.length];
		type2 = new String[pokemons.length];
		type1 = new String[pokemons.length];
		attack = new int[pokemons.length];
		speed = new int[pokemons.length];
		defense = new int[pokemons.length];
		hp = new int[pokemons.length];
		for(i = 1; i < pokemons.length; i++) {
			for(int j =0; j < pokemons[i].length; j++) {
				pokemons[i][j] = pokemons[i][j].substring(1, pokemons[i][j].length()-1);				
			}
			name[i] = pokemons[i][1];
			type2[i] = pokemons[i][3];
			hp[i] = Integer.parseInt(pokemons[i][4]);
			type1[i] = pokemons[i][5];
			attack[i] = Integer.parseInt(pokemons[i][6]);
			defense[i] = Integer.parseInt(pokemons[i][7]);
			speed[i] = Integer.parseInt(pokemons[i][10]);
			System.out.println(name[i]);
			poke[i]= (new Pokemon(name[i], hp[i], attack[i], defense[i], speed[i], type1[i], type2[i]));
		}
	}
	public static void attack(Pokemon attacker, String attack, Pokemon attacked) {
		double stab =1;
		if(attacker.getType1().equals(attack) || attacker.getType2().equals(attack)) stab = 1.5;
		double random = stab *(Math.random()*0.15 +0.85) ; 
		int damage = (int) (((((((2*100)/5)+2)*attacker.getAttack()
				*Integer.MIN_VALUE)/attacked.getDefense())/50)+2);
		damage = (int) Math.floor(damage *random);
		attacked.setHp(damage);
	}
}