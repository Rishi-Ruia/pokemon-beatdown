import java.util.ArrayList; //  https://docs.oracle.com/javase/8/docs/api/java/util/package-summary.html

public class Player extends Game {
	//array list of all the users pokemon
	protected ArrayList<Pokemon> playerPokemons = new ArrayList<Pokemon>();
	private Pokemon current;
	//constructor to set playerPokemons to contain random pokemon
	public Player() {
		for(int i =0; i < 6; i++) {
			int pokemon = (int) ((Math.random() * poke.length));
			playerPokemons.add(Game.poke[(pokemon)]);
		}
			
		for(int i =1; i < 6; i ++) {
			for(int j =i+1; j < 6; j++) {
				while( playerPokemons.get(j).equals(playerPokemons.get(i))) {
					playerPokemons.set(j, Game.poke[(int) (Math.random() * poke.length)]);
				}
					
			}
		}
		current = playerPokemons.get(0);
	}
	//returns the users current pokemon
	public Pokemon getCurrent() {
		return current;
	}
	//switches the users pokemon out for a new one
	public String Switch(int i) {
		if (playerPokemons.get(i).getHp() != 0 && playerPokemons.get(i) != current) {
			String name = current.getName();
			current = playerPokemons.get(i);
			return name + " switched out and " + current.getName() + " switched in!";
		}
		return null;
	}
	//checks if the user has lost 
	public  boolean lost() {
		for(int i =0; i < 6; i++) {
			if(playerPokemons.get(i).getHp() !=0) {
				return false;
			}
		}
		game.dispose();
		 return true;
	}
	//returns the pokemon at the position i 
	public Pokemon getPokemon(int i) {
		return playerPokemons.get(i);
	}
}