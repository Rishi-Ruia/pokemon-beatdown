
public class Mirror {
	private static final int size = 3;
	public static void main(String[] args) {
		//print top line
	line();
	// middle top
	topHalf();
	//middle bottom
	bottomHalf();
	line();
	//bottom
	}
	
	public static void line() {
		String top = "";
		for(int i = 1; i <= size * 4; i++)
			top += "=";
		System.out.println("#" + top + "#");
	}

	public static void topHalf() {
		for(int line = 1; line <= size; line++) {
			System.out.print("|");
			for(int space = 1; space <= line * -2 + 2 * size; space++) {
				System.out.print(" ");
			}
			System.out.print("<>");
			for(int dots = 1; dots <= line *4 - 4; dots++ ) {
				System.out.print(".");
			}
			System.out.print("<>");
			for(int space = 1; space <= line * -2 + 2 * size; space++) {
				System.out.print(" ");
			}
			System.out.println("|");
		}
		
	}
	public static void bottomHalf() {
		for(int line = size; line >= 1; line--) {
				System.out.print("|");
				for(int space = 1; space <= line * -2 + 2 * size; space++) {
					System.out.print(" ");
				}
				System.out.print("<>");
				for(int dots = 1; dots <= line *4 - 4; dots++ ) {
					System.out.print(".");
				}
				System.out.print("<>");
				for(int space = 1; space <= line * -2 + 2 * size; space++) {
					System.out.print(" ");
				}
				System.out.println("|");
			}
		}
}
