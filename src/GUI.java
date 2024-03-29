import java.awt.event.*; //from 
import java.awt.*; // from https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/awt/package-summary.html
import javax.swing.*; //from https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/package-summary.html
import java.io.*; //from https://docs.oracle.com/javase/8/docs/api/java/io/package-summary.html
public class GUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
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
	private JLabel userName;
	private JLabel AIname;
	private boolean skipturn = false;
	private JLabel console = new JLabel("A pokemon battle has started!");
	private JLabel AIconsole = new JLabel();
	public GUI(Player user, AI ai) throws FontFormatException, IOException{
		
		this.user = user;
		this.ai = ai;
		this.setLayout(null);
		addMoves();
		addSwitch();
		this.setSize(2000, 1300); 
		this.setVisible(true); 
		this.setTitle("battle window"); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes frame when you press the x button
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		userName = new JLabel(user.getCurrent().getName());
		AIname = new JLabel(ai.getCurrent().getName());
		userName.setFont(new Font("Arial", Font.BOLD, 80));
		userName.setBounds(20, 0, 800, 800);
		this.add(userName);
		AIname.setFont(new Font("Arial", Font.BOLD, 80));
		AIname.setBounds(450, -300 , 800, 800);
		this.add(AIname);
		console.setBounds(20, 0, 2000, 2000);		
		console.setFont(new Font("Arial", Font.ITALIC, 50));
		this.add(console);
		AIconsole.setBounds(20, 200, 2000, 2000);		
		AIconsole.setFont(new Font("Arial", Font.ITALIC, 50));
		this.add(AIconsole);
	}
	public void addMoves() {
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
		pokemon1.addActionListener(this);
		this.add(pokemon1);
		pokemon2 = new JButton(user.getPokemon(1).getName());
		pokemon2.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon2.setBounds(770, 600, 170, 150);
		pokemon2.addActionListener(this);
		this.add(pokemon2);
		pokemon3 = new JButton(user.getPokemon(2).getName());
		pokemon3.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon3.setBounds(940, 600, 185, 150);
		pokemon3.addActionListener(this);
		this.add(pokemon3);
		pokemon4 = new JButton(user.getPokemon(3).getName());
		pokemon4.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon4.setBounds(600, 750, 170, 150);
		pokemon4.addActionListener(this);
		this.add(pokemon4);
		pokemon5 = new JButton(user.getPokemon(4).getName());
		pokemon5.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon5.setBounds(770, 750, 170, 150);
		pokemon5.addActionListener(this);
		this.add(pokemon5);
		pokemon6 = new JButton(user.getPokemon(5).getName());
		pokemon6.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon6.setBounds(940, 750, 185, 150);
		pokemon6.addActionListener(this);
		this.add(pokemon6);
	}
	public  void forceSwitch() {
		console.setText("you died");
		move1.setEnabled(false);
		move2.setEnabled(false);
		move3.setEnabled(false);
		move4.setEnabled(false);
		skipturn = true;
	}
	public void displayLose() {

	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==move1) {
			console.setText(
					Game.attack(user.getCurrent(), user.getCurrent().getMove(0),
							ai.getCurrent(), user.getCurrent().getMove(0).isSpecial()));
			AIconsole.setText(" ");
		}
		else if(e.getSource() == move2) {
			console.setText(
					Game.attack(user.getCurrent(), user.getCurrent().getMove(1),
							ai.getCurrent(), user.getCurrent().getMove(1).isSpecial()));
			AIconsole.setText(" ");
		}
		else if(e.getSource() == move3) {
			console.setText(
					Game.attack(user.getCurrent(), user.getCurrent().getMove(2),
							ai.getCurrent(), user.getCurrent().getMove(2).isSpecial()));
			AIconsole.setText(" ");
		}
		else if(e.getSource() == move4) {
			console.setText(
					Game.attack(user.getCurrent(), user.getCurrent().getMove(3),
							ai.getCurrent(), user.getCurrent().getMove(3).isSpecial()));
		AIconsole.setText(" ");
		}
		else if(e.getSource() == pokemon1) {
			String canSwitch = user.Switch(0);
			if(canSwitch == null) return;
			console.setText(canSwitch);
			AIconsole.setText(" ");
			checkDead();
		}
		else if(e.getSource() == pokemon2) {
			String canSwitch = user.Switch(1);
			if(canSwitch == null)return;
			console.setText(canSwitch);
			AIconsole.setText(" ");
			checkDead();
		}
		else if(e.getSource() == pokemon3) {
			String canSwitch = user.Switch(2);
			if(canSwitch == null) return;
			console.setText(canSwitch);
			AIconsole.setText(" ");
			checkDead();
		}
		else if(e.getSource() == pokemon4) {
			String canSwitch = user.Switch(3);
			if(canSwitch == null) return;
			console.setText(canSwitch);
			AIconsole.setText(" ");
			checkDead();
		}
		else if(e.getSource() == pokemon5) {
			String canSwitch = user.Switch(4);
			if(canSwitch == null) return;
			console.setText(canSwitch);
			AIconsole.setText(" ");
			checkDead();
		}
		else if(e.getSource() == pokemon6) {
			String canSwitch = user.Switch(5);
			if(canSwitch == null) return;
			console.setText(canSwitch);
			AIconsole.setText(" ");
			checkDead();
		}
		user.lost();
		if(ai.getCurrent().getHp() ==0 ) {
			if(ai.lost()) {
				console.setText("GG you have won!");
				//this.dispose();
			}
			else {
				ai.fainted();
				this.AIname.setText(ai.getCurrent().getName());
			}
		}
		else {
			if(skipturn == true) {
				skipturn = false;
				return;
			}
			AIconsole.setText(ai.AITurn(user.getCurrent()));
		}

	}

	public void checkDead() {
		userName.setText(user.getCurrent().getName());
		move1.setText(user.getCurrent().getMove(0).getName());
		move2.setText(user.getCurrent().getMove(1).getName());
		move3.setText(user.getCurrent().getMove(2).getName());
		move4.setText(user.getCurrent().getMove(3).getName());
		if(user.getPokemon(0).getHp() ==0)
			pokemon1.setEnabled(false);
		if(user.getPokemon(1).getHp() ==0)
			pokemon2.setEnabled(false);
		if(user.getPokemon(2).getHp() ==0)
			pokemon3.setEnabled(false);
		if(user.getPokemon(3).getHp() ==0)
			pokemon4.setEnabled(false);
		if(user.getPokemon(4).getHp() ==0)
			pokemon5.setEnabled(false);
		if(user.getPokemon(5).getHp() ==0)
			pokemon6.setEnabled(false);
		revalidate();
		move1.setEnabled(true);
		move2.setEnabled(true);
		move3.setEnabled(true);
		move4.setEnabled(true);
	}
}