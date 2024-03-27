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
		if(AIPokemon.get(i) != null || (AIPokemon.get(i).getHp() !=0 && current != AIPokemon.get(i))) {
			String temp = current.getName();
			current = AIPokemon.get(i);
			return temp +  " switched to " + current.getName();
		}
		return null;
	}
	public String AITurn(Pokemon current) {
		for(int i =0; i < 4; i++) {
			if(Move.effective(this.current.getMove(i), current) == 2.0) {
				String damage = Game.attack(this.current, this.current.getMove(i), current, this.current.getMove(i).isSpecial());
				if(current.getHp() ==0) {
					if(user.lost()) {
						game.lost();
						return "you lost!";
					}
					game.forceSwitch();
				}
				return this.current.getName() + damage.substring(3);
			}
		}
			Move random = new Move();
			int rand = (int) (Math.random()*4);
			random = this.current.getMove(rand);
			String temp = current.getName();
			if(rand >= 5) if(Switch((int) Math.random()*6+1) != null) {
				return temp + " switched in to " + this.current.getName(); 
			}
			if(Move.effective(random, current) ==0 && !(random.equals(current.getMove(0))))
				random = AIPokemon.get(0).getMove(0);
			if(Move.effective(random, current) ==0) random.equals(current.getMove(1));
			String damage = Game.attack(this.current, random, current, random.isSpecial());
			if(damage.contains("healed")) return this.current.getName() + " healed!";
			if(current.getHp() ==0) {
				if(user.lost()) {
					game.lost();
					return "you lost!";
				}
				game.forceSwitch();
			}
			return this.current.getName() + damage.substring(3);
		}
	public  boolean lost() {
		for(int i =0; i < 6; i++) {
			if(AIPokemon.get(i).getHp() !=0) {
				return false;
			}
		}
		 return true;
	}
	public boolean fainted() {
		for(int i = 0; i < 6; i++) {
			if(!(AIPokemon.get(i).equals(current)) && AIPokemon.get(i).getHp() != 0) {
				current =AIPokemon.get(i);
				break;
			}
		}
		return lost();
	}
}
