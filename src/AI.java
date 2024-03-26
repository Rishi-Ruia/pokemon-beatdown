
public class AI extends Game {
	private Pokemon pokemon1 = new Pokemon();
	private Pokemon pokemon2 = new Pokemon();
	private Pokemon pokemon3 = new Pokemon();
	private Pokemon pokemon4 = new Pokemon();
	private Pokemon pokemon5 = new Pokemon();
	private Pokemon pokemon6 = new Pokemon();
	private Pokemon current;
	public AI() {
		pokemon1 = Game.poke[(int) (Math.random() * poke.length)];
		pokemon2 = Game.poke[(int) (Math.random() * poke.length)];
		pokemon3 = Game.poke[(int) (Math.random() * poke.length)];
		pokemon4 = Game.poke[(int) (Math.random() * poke.length)];
		pokemon5 = Game.poke[(int) (Math.random() * poke.length)];
		pokemon6 = Game.poke[(int) (Math.random() * poke.length)];
		while(pokemon1.equals(pokemon2)) 
			pokemon2 = Game.poke[(int) (Math.random() * poke.length)];
		while(pokemon3.equals(pokemon1) || pokemon3.equals(pokemon2))
			pokemon3 = Game.poke[(int) (Math.random() * poke.length)];
		while(pokemon4.equals(pokemon1) || pokemon4.equals(pokemon2)
				|| pokemon4.equals(pokemon3))
			pokemon4 = Game.poke[(int) (Math.random() * poke.length)];
		while(pokemon5.equals(pokemon1) || pokemon5.equals(pokemon2)
				|| pokemon5.equals(pokemon3) || pokemon5.equals(pokemon4))
			pokemon5 = Game.poke[(int) (Math.random() * poke.length)];
		while(pokemon6.equals(pokemon1) || pokemon6.equals(pokemon2)
				|| pokemon6.equals(pokemon3) || pokemon6.equals(pokemon4)
				|| pokemon6.equals(pokemon5))
			pokemon6 = Game.poke[(int) (Math.random() * poke.length)];
		current = pokemon1;
	}
	public Pokemon getCurrent() {
		return current;
	}
	public String Switch(int i) {
		if(i ==1 && pokemon1.getHp() !=0 && current != pokemon1) {
			current = pokemon1;
			return current.getName() +  " switched to " + pokemon1.getName();
		}if(i ==2 && pokemon2.getHp() !=0 && current != pokemon2) {
			current = pokemon2;
			return current.getName() +  " switched to " + pokemon2.getName();
		}
		if(i ==3 && pokemon3.getHp() !=0 && current != pokemon3) {
			current = pokemon3;
			return current.getName() +  " switched to " + pokemon3.getName();
		}
		if(i ==4 && pokemon4.getHp() !=0 && current != pokemon4) {
			current = pokemon4;
			return current.getName() +  " switched to " + pokemon4.getName();
		}
		if(i ==5 && pokemon5.getHp() !=0 && current != pokemon5) {
			current = pokemon5;
			return current.getName() +  " switched to " + pokemon5.getName();
		}
		if(i ==6 && pokemon6.getHp() !=0 && current != pokemon6) {
			current = pokemon6;
			return current.getName() +  " switched to " + pokemon6.getName();
		}
		return null;
	}
	public String AIattack(Pokemon current) {
		if(Move.effective(this.current.getMove1(), current) == 2) {
			Game.attack(this.current, current.getMove1(), current);
			return this.current.getName();
		}
		return "";
	}
}
