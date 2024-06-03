import java.awt.FontFormatException;
import java.io.*; //from https://docs.oracle.com/javase/8/docs/api/java/io/package-summary.html
import javax.sound.sampled.*; //from https://docs.oracle.com/javase/8/docs/api/javax/sound/sampled/package-summary.html

public class Game {
	// global static variables used to create the Pokemon objects
	protected static String[] name;
	protected static String[] type2;
	protected static String[] type1;
	protected static int[] attack;
	protected static int[] speed;
	protected static int[] defense;
	protected static int[] hp;
	protected static int[] spAttack;
	protected static int[] spDefense;
	protected static int[] dex;
	protected static int[] base;
	public static Pokemon[] poke = new Pokemon[651];
	public static Pokemon[] pokeAI = new Pokemon[651];
	protected static GUI game;
	protected static Player user;
	protected static AI ai;
	protected static FloatControl volume;

	// main method used to start and set up game
	public static void main(String[] args)
			throws IOException, UnsupportedAudioFileException, LineUnavailableException, FontFormatException {
		// Equal chance of all songs
		String[] songs = {"diamondAndPearlBattleTheme.wav", 
				"blackAndWhiteBattleTheme.wav", "blunder theme.wav","blunder theme.wav",
				"blunder theme.wav"};
		int songIndex = (int) (Math.random() * songs.length);
		String fileName = songs[songIndex];
		File file = new File(fileName); 
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		if (fileName.equals("blunder theme.wav"))
			audioStream.skip(2282000);
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
		volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(-12.0f);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		Move.addMoves();
		setup();
		user = new Player();
		AI ai = new AI();
		game = new GUI(user, ai, poke);
	}

	// method to create the Pokemon objects
	public static void setup() throws IOException {
		String line = "";
		String[][] pokemons = new String[651][13];
		BufferedReader br = new BufferedReader(new FileReader("Gen5Pokedex.csv"));
		int i = 0;
		String[] pokemon = new String[pokemons.length];
		while ((line = br.readLine()) != null) {
			pokemon = line.split(",");
			pokemons[i] = pokemon;
			i++;
		}
		br.close();
		base = new int[pokemons.length];
		dex = new int[pokemons.length];
		name = new String[pokemons.length];
		type2 = new String[pokemons.length];
		type1 = new String[pokemons.length];
		attack = new int[pokemons.length];
		speed = new int[pokemons.length];
		defense = new int[pokemons.length];
		hp = new int[pokemons.length];
		spAttack = new int[pokemons.length];
		spDefense = new int[pokemons.length];
		for (i = 0; i < pokemons.length; i++) {
			dex[i] = Integer.parseInt(pokemons[i][0]);
			name[i] = pokemons[i][1];
			base[i] = Integer.parseInt(pokemons[i][2]);
			type2[i] = pokemons[i][3];
			hp[i] = Integer.parseInt(pokemons[i][4]);
			type1[i] = pokemons[i][5];
			attack[i] = (Integer.parseInt(pokemons[i][6]));
			spAttack[i] = Integer.parseInt(pokemons[i][8]);
			defense[i] = (Integer.parseInt(pokemons[i][7]));
			spDefense[i] = Integer.parseInt(pokemons[i][9]);
			speed[i] = Integer.parseInt(pokemons[i][10]);
			poke[i] = (new Pokemon(name[i], hp[i], attack[i], spAttack[i], defense[i], spDefense[i], speed[i], type1[i],
					type2[i], dex[i], base[i]));
			pokeAI[i] = (new Pokemon(name[i], hp[i], attack[i], spAttack[i], defense[i], spDefense[i], speed[i],
					type1[i], type2[i], dex[i], base[i]));
		}
	}

	// method for attacking another pokemon
	public static String attack(Pokemon attacker, Move attack, Pokemon attacked, boolean special) {
		String superEffective;
		String crit = "";
		if(Move.effective(attack, attacked) >= 2) {
			superEffective = " it was SUPER EFFECTIVE ";
		}
		else if(Move.effective(attack, attacked) <= 0.5) {
			superEffective = " it was not very effective ";
		}
		else {
			superEffective = " ";
		}
		if (attack.getName().equals("Recover")) {
			int heal = (int) (attacker.getMaxHp() * .5);
			attacker.setHp(-heal);
			if (attacker.getHp() > attacker.getMaxHp())
				attacker.setHp(-2);
			return attacker.getName() + " healed for "  + "50% and now has " + attacker.getHp() + " HP!";
		}
		double stab = 1;
		if (attacker.getType1().equals(attack.getType()) || attacker.getType2().equals(attack.getType()))
			stab = 1.5;
		if(Math.random()*101 < 6.25) {
			crit = " it was a Critial Hit!";
			stab *=1.5;
		}
		double random = stab * (Math.random() * 0.15 + 0.85);
		int damage = damageCalc(attacker, attack, attacked, special, random);
		if ((int) (Math.random() * 101) <= attack.getAccuracy()) {
			attacked.setHp(damage);
			if (attacked.getHp() <= 0) {
				attacked.setHp(0);
				return attacker.getName() + " used " + attack.getName() + " and " + attacked.getName() + " fainted!"  + crit;
			}
			return attacker.getName() + " used " + attack.getName() + superEffective +  "and it did " + damage*100/attacked.getMaxHp() + "%" + crit;
		}
		return attack.getName() + " missed!";
	}

	// method that calculates the damage that the move does called by the damage
	// method
	public static int damageCalc(Pokemon attacker, Move attack, Pokemon attacked, boolean special, double random) {
		if (!special)
			return (int) ((((((((2 * 100) / 5) + 2) * attacker.getAttack() * attack.getPower()) / attacked.getDefense())
					/ 50) + 2) * Move.effective(attack, attacked) * random);
		return (int) ((((((((2 * 100) / 5) + 2) * attacker.getSPAttack() * attack.getPower()) / attacked.getSPDefense())
				/ 50) + 2) * Move.effective(attack, attacked) * random);
	}
	public static void mute(boolean mute) {
		volume.setValue(-100000f);
		if (!mute) {
			volume.setValue(-12.f);
		}
	}
}