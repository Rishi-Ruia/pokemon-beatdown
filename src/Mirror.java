
public class Mirror {

	public static void main(String[] args) {
		//print top line
	line();
	// middle top
	topHalf();
	//middle bottom
	line();
	//bottom
	}
	
	public static void line() {
		String top = "";
		for(int i = 1; i <= 16; i++)
			top += "=";
		System.out.println("#" + top + "#");
	}

	public static void topHalf() {
		System.out.print("|");
		
	}
}
