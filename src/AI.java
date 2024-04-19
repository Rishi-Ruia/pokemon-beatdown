import java.util.ArrayList;
public class AI extends Game {
	private ArrayList<Pokemon> AIPokemon = new ArrayList<Pokemon>();
	private Pokemon current;
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
	public Pokemon getCurrent() {
		return current;
	}
	public String Switch(int i) {
		if(AIPokemon.get(i) != null || (AIPokemon.get(i).getHp() !=0
				&& current != AIPokemon.get(i))) {
			String temp = current.getName();
			current = AIPokemon.get(i);
			return temp +  " switched to " + current.getName();
		}
		return null;
	}
	public String AITurn(Pokemon current) {
		for(int i =0; i < 4; i++) {
			if(Move.effective(this.current.getMove(i), current) >= 2.0) {
				String damage = Game.attack(this.current,
						this.current.getMove(i), current, this.current.getMove(i).isSpecial());
				if(current.getHp() ==0) {
					if(user.lost()) {
						game.displayLose();;
						return "you lost!";
					}
					game.forceSwitch();
				}
				return this.current.getName() + damage.substring(3);
			}
		}
			Move random = this.current.getMove((int) (Math.random()*5));
			if(Move.effective(random, current) ==0 && !(random.equals(current.getMove(0))))
				random = AIPokemon.get(0).getMove(0);
			if(Move.effective(random, current) ==0) random.equals(current.getMove(1));
			String damage = Game.attack(this.current, random, current, random.isSpecial());
			if(damage.contains("healed")) return this.current.getName() + " healed!";
			if(current.getHp() ==0) {
				if(user.lost()) {
					game.displayLose();
					return "you lost!";
				}
				game.forceSwitch();
			}
			return this.current.getName() + damage.replaceAll("you", " ");
		}
	public  boolean lost() {
		for(int i =0; i < 6; i++) {
			if(AIPokemon.get(i).getHp() !=0) {
				return false;
			}
		}
		 return true;
	}
	public void fainted() {
		for(int i = 0; i < 6; i++) {
			if(!(AIPokemon.get(i).equals(current)) && AIPokemon.get(i).getHp() != 0) {
				current =AIPokemon.get(i);
				break;
			}
		}
	}
}
