public class move {
	String type;
	int power;
	int accuracy;
	public move(String type, int power, int accuracy) {
		this.type = type;
		this.power = power;
		this.accuracy = accuracy;
	}
	public static int effective(move attack, Pokemon attacked) {
		String moveType = attack.getType();
		String attackedType1 = attacked.getType1();
		String attackedType2 = attacked.getType1();
		if(attackedType2.length() <=2) {
			   String [][] NORMAL = { {""},
					   {"Rock", "Steel"},
					   {"Ghost"}};
			   String [][] FIGHTING = {{"Normal", "Rock", "Steel", "Ice", "Dark"},
			            {"Flying", "Poison", "Bug", "Psychic"},
			            {"Ghost"}};
			   String [][] FLYING ={{"Fighting", "Grass"},
			            {"Rock", "Steel", "Electric"},
			            {}};
			   String [][] POISON = {{"Grass"},
			            new String[]{"Poison", "Ground", "Rock", "Bug"},
			            new String[]{"Steel"}};
			   String [][] GROUND = {(new String[]{"Poison", "Rock", "Steel", "Fire", "Electric"}),
			            new String[]{"Bug", "Grass"},
			            new String[]{"Flying"}};
			   String [][] ROCK = { new String[]{"Flying", "Bug", "Fire"},
			            new String[]{"Fighting", "Ground", "Steel"},
			            new String[]{}};
			   String [][] BUG = {(new String[]{"Grass", "Psychic", "Dark"}),
			            new String[]{"Fighting", "Flying", "Poison", "Ghost", "Steel", "Fire"},
			            new String[]{}};
			   String [][] GHOST = {new String[]{"Ghost", "Psychic"},
			            new String[]{"Steel", "Dark"},
			            new String[]{"Normal"}};
			   String [][] STEEL = {new String[]{"Rock", "Ice"},
			            new String[]{"Steel", "Fire", "Water", "Electric"},
			            new String[]{}};
			   String [][] FIRE =  {new String[]{"Grass", "Ice", "Bug"},
			            new String[]{"Fire", "Water", "Rock", "Dragon"},
			            new String[]{}};
			   String [][] WATER = {new String[]{"Fire", "Rock", "Ground"},
			            new String[]{"Water", "Grass", "Dragon"},
			            new String[]{}};
			   String [][] GRASS = {new String[]{"Water", "Ground", "Rock"},
			            new String[]{"Fire", "Grass", "Poison", "Flying", "Bug", "Dragon", "Steel"},
			            new String[]{}};
			   String [][] ELECTRIC = {new String[]{"Water", "Flying"},
			            new String[]{"Grass", "Electric", "Dragon"},
			            new String[]{"Ground"}};
			   String [][] PSYCHIC = {new String[]{"Fighting", "Poison"},
			            new String[]{"Psychic", "Steel"},
			            new String[]{"Dark"}};
			   String [][] ICE =  {new String[]{"Grass", "Ground", "Flying", "Dragon"},
			            new String[]{"Fire", "Water", "Ice", "Steel"},
			            new String[]{}};
			   String [][] DRAGON = {new String[]{"Dragon"},
			            new String[]{"Steel"},
			            new String[]{}};
			   String [][] DARK = {new String[]{"Psychic", "Ghost"},
			            new String[]{"Fighting", "Dark", "Steel"},
			            new String[]{}};
			   String [][] FAIRY = {new Sring[]{"Dark", "Dragon", "Fighting"}, newString[]
					   
			   }
		}
		return 1;
	}
	public String getType() {
		return type;
	}
	public int getPower() {
		return power;
	}
	public int getAccuracy() {
		return accuracy;
	}
}

