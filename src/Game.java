import java.util.ArrayList;
import java.util.Scanner;
public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUI game = new GUI();
		Scanner scan = new Scanner(System.in);
		 //List<String> CNames = Arrays.asList("Copy of All Pokemon Spreadsheet");
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