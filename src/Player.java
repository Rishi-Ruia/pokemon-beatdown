import java.util.ArrayList;

public class Player extends Game {
	protected ArrayList<Pokemon> playerPokemons = new ArrayList<Pokemon>();
	private Pokemon current;
	public Player() {
		for(int i =0; i < 6; i++) {
			int pokemon = (int) ((Math.random() * poke.length));
			playerPokemons.add(Game.poke[(pokemon)]);
		}
			
		for(int i =0; i < 6; i ++) {
			for(int j =i+1; j < 6; j++) {
				while( playerPokemons.get(j).equals(playerPokemons.get(i))) {
					playerPokemons.set(j, Game.poke[(int) (Math.random() * poke.length)]);
				}
					
			}
		}
		current = playerPokemons.get(0);
	}
	public Pokemon getCurrent() {
		return current;
	}
	public String Switch(int i) {
		if(playerPokemons.get(i).getHp() != 0) {
			String name = current.getName();
			current = playerPokemons.get(i);
			return name + " switched out and " + current.getName() + "switched in!";
		}
		return null;
	}
	public  boolean lost() {
		for(int i =0; i < 6; i++) {
			if(playerPokemons.get(i).getHp() !=0) {
				return false;
			}
		}
		 return true;
	}
}
