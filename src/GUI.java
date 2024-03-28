import java.awt.event.*; //from 
import java.awt.*; // from https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/awt/package-summary.html
import javax.swing.*; //from https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/package-summary.html
public class GUI extends JFrame implements ActionListener{
	private JButton move1;
	private JButton move2;
	private JButton move3;
	private JButton move4;
	private JButton pokemon1;
	private JButton pokemon2;
	private JButton pokemon3;
	private JButton pokemon4;
	private JButton pokemon5;
	private JButton pokemon6;
	private Player user;
	private AI ai;
	public GUI(Player user, AI ai){
		this.user = user;
		this.ai = ai;
		this.setLayout(null);
		addMoves();
		addSwitch();
		this.setSize(1140, 938); 
		this.setVisible(true); 
		this.setTitle("battle window"); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes frame when you press the x button
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		JLabel userName = new JLabel(user.getCurrent().getName());
		JLabel AIname = new JLabel(ai.getCurrent().getName());
		userName.setFont(new Font("Arial", Font.BOLD, 80));
		userName.setBounds(20, 0, 800, 800);
		this.add(userName);
		AIname.setFont(new Font("Arial", Font.BOLD, 80));
		AIname.setBounds(600, -300 , 800, 800);
		this.add(AIname);
	}
	private void addMoves() {
		move1 = new JButton(user.getCurrent().getMove(0).getName());
		move1.setBounds(0, 600, 300, 150);
		move1.setFont(new Font("Arial", Font.PLAIN, 40));
		move1.setBackground(Color.WHITE);
		move1.addActionListener(this);
		move2 = new JButton(user.getCurrent().getMove(1).getName());
		move2.setBounds(300, 600, 300, 150);
		move2.setFont(new Font("Arial", Font.PLAIN, 40));
		move2.setBackground(Color.WHITE);
		move2.addActionListener(this);
		move3 = new JButton(user.getCurrent().getMove(2).getName());
		move3.setBounds(0, 750, 300, 150);
		move3.setFont(new Font("Arial", Font.PLAIN, 40));
		move3.setBackground(Color.WHITE);
		move3.addActionListener(this);
		move4 = new JButton(user.getCurrent().getMove(3).getName());
		move4.setBounds(300, 750, 300, 150);
		move4.setFont(new Font("Arial", Font.PLAIN, 40));
		move4.setBackground(Color.WHITE);
		move4.addActionListener(this);
		this.add(move1);
		this.add(move2);
		this.add(move3);
		this.add(move4);
	}
	private void addSwitch() {
		pokemon1 = new JButton(user.getPokemon(0).getName());
		pokemon1.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon1.setBounds(600, 600, 170, 150);
		this.add(pokemon1);
		pokemon2 = new JButton(user.getPokemon(1).getName());
		pokemon2.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon2.setBounds(770, 600, 170, 150);
		this.add(pokemon2);
		pokemon3 = new JButton(user.getPokemon(2).getName());
		pokemon3.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon3.setBounds(940, 600, 185, 150);
		this.add(pokemon3);
		pokemon4 = new JButton(user.getPokemon(3).getName());
		pokemon4.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon4.setBounds(600, 750, 170, 150);
		this.add(pokemon4);
		pokemon5 = new JButton(user.getPokemon(4).getName());
		pokemon5.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon5.setBounds(770, 750, 170, 150);
		this.add(pokemon5);
		pokemon6 = new JButton(user.getPokemon(5).getName());
		pokemon6.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon6.setBounds(940, 750, 185, 150);
		this.add(pokemon6);
	}
	public  void forceSwitch() {
		
	}
	public void displayLose() {
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==move1) {
			System.out.println(
			Game.attack(user.getCurrent(), user.getCurrent().getMove(0)
					, ai.getCurrent(), user.getCurrent().getMove(0).isSpecial()));
		
		}
		if(e.getSource() == pokemon1) {
			
		}
			
	}
}
