import java.util.ArrayList; //from https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html

public class AI extends Game {
	// instance data
	private ArrayList<Pokemon> AIPokemon = new ArrayList<Pokemon>();
	private Pokemon current;

	// constructor to make the ai have random pokemon
	public AI() {
		for (int i = 0; i < 6; i++)
			AIPokemon.add(Game.pokeAI[(int) (Math.random() * Game.pokeAI.length)]);
		for (int i = 0; i < 6; i++) {
			for (int j = i + 1; j < 6; j++) {
				while (AIPokemon.get(j).equals(AIPokemon.get(i)) || AIPokemon.get(j).getBase() < 319)
					AIPokemon.set(j, Game.pokeAI[(int) (Math.random() * pokeAI.length)]);
			}
		}
		current = AIPokemon.get(0);
	}

	// returns the pokemon the ai currently is using in battle
	public Pokemon getCurrent() {
		return current;
	}

	// used by the ai to switch its pokemon to a new one
	public String Switch(int i) {
		if (AIPokemon.get(i) != null || (AIPokemon.get(i).getHp() != 0 && current != AIPokemon.get(i))) {
			String temp = current.getName();
			current = AIPokemon.get(i);
			return temp + " switched to " + current.getName();
		}
		return null;
	}

	// this is where the ai chooses what to do this turn
	public String AITurn(Pokemon current, Move m) {

		String damage = Game.attack(this.current, m, current, m.isSpecial());
		if (damage.contains("healed"))
			return this.current.getName() + " healed!";
		if (current.getHp() == 0) {
			game.forceSwitch();
		}
		return damage;
	}

	// checks if the AI has lost and
	public boolean lost() {
		for (int i = 0; i < 6; i++) {
			if (AIPokemon.get(i).getHp() != 0) {
				return false;
			}
		}
		return true;
	}

	// checks if the AI's current pokemon has fainted
	public void fainted() {
		for (int i = 0; i < 6; i++) {
			if (!(AIPokemon.get(i).equals(current)) && AIPokemon.get(i).getHp() != 0) {
				current = AIPokemon.get(i);
				break;
			}
		}
	}
}
