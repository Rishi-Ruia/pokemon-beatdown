import java.util.ArrayList; //from https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
public class AI extends Game {
	//instance data
	private ArrayList<Pokemon> AIPokemon = new ArrayList<Pokemon>();
	private Pokemon current;
	//constructor to make the ai have random pokemon
	public AI() {
		for(int i =0; i < 6; i++)
		AIPokemon.add(Game.poke[(int) (Math.random() * Game.poke.length)]);
		for(int i =0; i < 6; i ++) {
			for(int j =i+1; j < 6; j++) {
				while(AIPokemon.get(j).equals(AIPokemon.get(i)))
					AIPokemon.set(i, Game.poke[(int) (Math.random() * poke.length)]);
			}
		}
		current = AIPokemon.get(0);
	}
	//returns the pokemon the ai currently is using in battle
	public Pokemon getCurrent() {
		return current;
	}
	//used by the ai to switch its pokemon to a new one
	public String Switch(int i) {
		if(AIPokemon.get(i) != null || (AIPokemon.get(i).getHp() !=0
				&& current != AIPokemon.get(i))) {
			String temp = current.getName();
			current = AIPokemon.get(i);
			return temp +  " switched to " + current.getName();
		}
		return null;
	}
	//this is where the ai chooses what to do this turn
	public String AITurn(Pokemon current) {
//		for(int i =0; i < 4; i++) {
//			if(Move.effective(this.current.getMove(i), current) >= 2.0) {
//				String damage = Game.attack(this.current,
//						this.current.getMove(i), current, this.current.getMove(i).isSpecial());
//				if(current.getHp() ==0) {
//					if(user.lost()) {
//						game.displayLose();;
//						return "you lost!";
//					}
//					game.forceSwitch();
//				}
//				return this.current.getName() + damage.substring(3);
//			}
//		}
		Move use = new Move();
		Move random;
		String damage = "";
		int max = 0;
		for(int  i =0; i < 4; i++) {
			random = this.current.getMove(i);
			double stab =1;
			if(this.current.getType1().equals(random.getType())
					|| this.current.getType2().equals(random.getType()))  stab = 1.5;
			double mod = stab *(Math.random()*0.16 +0.85);
			int currentDamage = Game.damageCalc(this.current, random, current, random.isSpecial(), mod);
			if(currentDamage > max) {
				use = random;
				max = currentDamage;
			}
		}		
		 damage = Game.attack(this.current, use, current, use.isSpecial());
			if(damage.contains("healed")) return this.current.getName() + " healed!";
			if(current.getHp() ==0) {
				if(user.lost()) {
					game.displayLose();
					return "you lost!";
				}
				game.forceSwitch();
			}
			return damage;
		}
	//checks if the AI has lost and
	public  boolean lost() {
		for(int i =0; i < 6; i++) {
			if(AIPokemon.get(i).getHp() !=0) {
				return false;
			}
		}
		 return true;
	}
	//checks if the AI's current pokemon has fainted
	public void fainted() {
		for(int i = 0; i < 6; i++) {
			if(!(AIPokemon.get(i).equals(current)) && AIPokemon.get(i).getHp() != 0) {
				current =AIPokemon.get(i);
				break;
			}
		}
	}
}
