
public class Mirror {

	public static void main(String[] args) {
	System.out.println(line());
	
	System.out.println(line());
	}
	public static String line() {
		String top = "";
		for(int i = 1; i <= 16; i++)
		top += "=";
		return "#" + top + "#" ;
	}

}
