import java.util.ArrayList; //  https://docs.oracle.com/javase/8/docs/api/java/util/package-summary.html

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
		if (playerPokemons.get(i).getHp() != 0 && playerPokemons.get(i) != current) {
			String name = current.getName();
			current = playerPokemons.get(i);
			//game.addMoves();
			return name + " switched out and " + current.getName() + " switched in!";
		}
		return null;
	}
	public  boolean lost() {
		for(int i =0; i < 6; i++) {
			if(playerPokemons.get(i).getHp() !=0) {
				return false;
			}
		}
		game.dispose();
		 return true;
	}
	public Pokemon getPokemon(int i) {
		return playerPokemons.get(i);
	}
}
