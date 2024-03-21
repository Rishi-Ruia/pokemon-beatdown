public class Item {
	
	public static Item[] itemList = new Item[4];
	private String name;
	private double damageMod;
	private double speedMod;
	private double defenseMod;
	private boolean moveLock;
	private boolean selfDamage;
	private Item item;
	
	/**
	 * @param n is the Name
	 * @param dm is the 
	 * @param sm
	 * @param dfm
	 * @param ml
	 * @param h
	 * @param sd
	 */
	public Item(String n, double dm, double sm, double dfm, boolean ml, boolean sd) {
		name = n;
		damageMod = dm;
		speedMod = sm;
		defenseMod = dfm;
		moveLock = ml;
		selfDamage = sd;
		item = 
	}
	
	
	public static void main(String[] args) {
		itemList[0] = new Item("choice scarf", 1, 1.5, 1, true,  false);
		itemList[1] = new Item("choice band", 1.5, 1, 1, true,  false);
		itemList[2] = new Item("assault vest", 1, 1, 1.5, false,  false);
		itemList[3] = new Item("life orb", 1.3, 1, 1, false,  true);
	}

}
