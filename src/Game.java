import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	private Pokemon[] poke = new Pokemon[800];
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		GUI game = new GUI();
		Scanner scan = new Scanner(System.in);
		 //List<String> CNames = Arrays.asList("Copy of All Pokemon Spreadsheet");
		String line = "";
		String[][] pokemons = new String[801][13];
		BufferedReader br = new BufferedReader(new FileReader("Copy of All Pokemon Data Spreadsheet_exported.csv"));
		int i = 0;
		while((line = br.readLine()) != null) {
			String[] pokemon = line.split(",");
			pokemons[i] = pokemon;
			i++;
		}
		for(i = 0; i < pokemons.length; i++) {
			//poke[i] = new Pokemon(pokemons[i][])
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