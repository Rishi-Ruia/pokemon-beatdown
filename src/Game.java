import java.awt.FontFormatException;
import java.io.*; //from https://docs.oracle.com/javase/8/docs/api/java/io/package-summary.html
import javax.sound.sampled.*; //from https://docs.oracle.com/javase/8/docs/api/javax/sound/sampled/package-summary.html
public class Game {
	//global static variables used to create the Pokemon objects
	protected static String [] name;
	protected static String [] type2;
	protected static String [] type1;
	protected static int [] attack;
	protected static int [] speed;
	protected static int [] defense;
	protected static int [] hp;
	protected static int [] spAttack;
	protected static int [] spDefense;
	protected static Pokemon[] poke = new Pokemon[719];
	protected static GUI game;
	protected static Player user;
	protected static AI ai;
	//main method used to start and set up game
	public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, FontFormatException {
		String fileName = "diamondAndPearlBattleTheme.wav";
		// 50% chance of being Blunder theme
		if (Math.random() <= 0.5) {
			fileName = "blunder theme.wav";
		}
		File file = new File(fileName); // From https://www.youtube.com/watch?v=0_SeDY8Y3g8
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
		// For adjusting volume
		FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		// The parameter of setValue is how many decibels you want to adjust the volume by
		volume.setValue(-12.0f);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		Move.addMoves();
		setup();
		user = new Player();
		AI ai = new AI();
		game = new GUI(user, ai);
	}
	//method to create the Pokemon objects
	public static void setup() throws IOException{
		//poke[0] is named Raeed Rahman as an easter egg of my friend who game me the idea to make this
		poke[0] = (new Pokemon("Raeed Rahman", 120,120,120,120,120,120, "Dragon", "Fairy"));
		String line = "";
		String[][] pokemons = new String[719][13];
		BufferedReader br = new BufferedReader(new FileReader("Gen5Pokedex.csv"));
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
			poke[i]= (new Pokemon(name[i], hp[i], attack[i], spAttack[i], defense[i],
					spDefense[i], speed[i], type1[i], type2[i]));
		}
		
	}
	//method for attacking another pokemon
	public static String attack(Pokemon attacker, Move attack,
			Pokemon attacked, boolean special) {
		if(attack.getName().equals("Recover")) {
			int heal = (int)(attacker.getHp()*.25);
			attacker.setHp(-heal);
			return attacker.getName() + " healed for "+ heal +
					" and is now " + attacker.getHp() + " HP !";
		}
		double stab =1;
		if(attacker.getType1().equals(attack.getType())
				|| attacker.getType2().equals(attack.getType())) stab = 1.5;
		double random = stab *(Math.random()*0.15 +0.85);
		int damage = damageCalc( attacker,  attack,  attacked, special, random);
		if((int) (Math.random() *101) <= attack.getAccuracy()) {
			attacked.setHp(damage);
			if(attacked.getHp() <= 0) {
				attacked.setHp(0);
				return attacker.getName() + " used " + attack.getName() +
						" and " + attacked.getName() + " fainted!" ;
			}
			return attacker.getName() + " used " + attack.getName() +
					" and it did " + damage + " damage to " + attacked.getName() + "!";
		}
		return attack.getName() + " missed!";
	}
	//method that calculates the damage that the move does called by the damage method
	public static int damageCalc(Pokemon attacker, Move attack,
			Pokemon attacked, boolean special, double random) {
		if(!special)
			return  (int) ((((((((2*100)/5)+2)*attacker.getAttack()
					*attack.getPower())/attacked.getDefense())/50)+2)
					*Move.effective(attack, attacked)*random);
		return  (int) ((((((((2*100)/5)+2)*attacker.getSPAttack()
				*attack.getPower())/attacked.getSPDefense())/50)+2)
				*Move.effective(attack, attacked)*random);
	}
}