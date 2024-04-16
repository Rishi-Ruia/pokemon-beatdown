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
	public GUI(Player user, AI ai) throws  IOException{
		this.user = user;
		this.ai = ai;
		this.setLayout(null);
		JLabel userImage = new JLabel(new ImageIcon("user.png")); //from ALI MOHAMMED
		JLabel aiImage = new JLabel(new ImageIcon("ai.png")); //from ALI MOHAMMED
		userImage.setBounds(-100,300,600,600);
		aiImage.setBounds(1558,-20,600,600);
		this.add(userImage);
		this.add(aiImage);
		addMoves();
		addSwitch();
		this.setSize(2000, 1300); 
		this.setTitle("battle window"); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes frame when you press the x button
		setLocationRelativeTo(null);	
		userName = new JLabel(user.getCurrent().getName());
		AIname = new JLabel(ai.getCurrent().getName());
		userName.setFont(new Font("Arial", Font.BOLD, 80));
		userName.setBounds(500, 50, 1000, 1000);
		this.add(userName);
		AIname.setFont(new Font("Arial", Font.BOLD, 80));
		AIname.setBounds(1000, -250 , 1000, 1000);
		this.add(AIname);
		console.setBounds(20, 0, 2000, 2000);		
		console.setFont(new Font("Arial", Font.ITALIC, 50));
		this.add(console);
		AIconsole.setBounds(20, 200, 2000, 2000);		
		AIconsole.setFont(new Font("Arial", Font.ITALIC, 50));
		AIconsole.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		console.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		this.add(AIconsole);
		this.setVisible(true);
	}
	public void addMoves() {
		move1 = new JButton(user.getCurrent().getMove(0).getName());
		move1.setBounds(400, 650, 300, 150);
		move1.setFont(new Font("Arial", Font.PLAIN, 50));
		move1.setBackground(Color.WHITE);
		move1.addActionListener(this);
		move2 = new JButton(user.getCurrent().getMove(1).getName());
		move2.setBounds(700, 650, 300, 150);
		move2.setFont(new Font("Arial", Font.PLAIN, 50));
		move2.setBackground(Color.WHITE);
		move2.addActionListener(this);
		move3 = new JButton(user.getCurrent().getMove(2).getName());
		move3.setBounds(400, 800, 300, 150);
		move3.setFont(new Font("Arial", Font.PLAIN, 50));
		move3.setBackground(Color.WHITE);
		move3.addActionListener(this);
		move4 = new JButton(user.getCurrent().getMove(3).getName());
		move4.setBounds(700, 800, 300, 150);
		move4.setFont(new Font("Arial", Font.PLAIN, 50));
		move4.setBackground(Color.WHITE);
		move4.addActionListener(this);
		this.add(move1);
		this.add(move2);
		this.add(move3);
		this.add(move4);
		this.setVisible(true);
	}
	private void addSwitch() {
		pokemon1 = new JButton(user.getPokemon(0).getName());
		pokemon1.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon1.setBounds(1000, 650, 170, 150);
		pokemon1.addActionListener(this);
		this.add(pokemon1);
		pokemon2 = new JButton(user.getPokemon(1).getName());
		pokemon2.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon2.setBounds(1170, 650, 170, 150);
		pokemon2.addActionListener(this);
		this.add(pokemon2);
		pokemon3 = new JButton(user.getPokemon(2).getName());
		pokemon3.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon3.setBounds(1340, 650, 185, 150);
		pokemon3.addActionListener(this);
		this.add(pokemon3);
		pokemon4 = new JButton(user.getPokemon(3).getName());
		pokemon4.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon4.setBounds(1000, 800, 170, 150);
		pokemon4.addActionListener(this);
		this.add(pokemon4);
		pokemon5 = new JButton(user.getPokemon(4).getName());
		pokemon5.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon5.setBounds(1170, 800, 170, 150);
		pokemon5.addActionListener(this);
		this.add(pokemon5);
		pokemon6 = new JButton(user.getPokemon(5).getName());
		pokemon6.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon6.setBounds(1340, 800, 185, 150);
		pokemon6.addActionListener(this);
		this.add(pokemon6);
		this.setVisible(true);
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
		AIconsole.setText("you have lost :(");
		forceSwitch();
	}
	
	public boolean  playerSwitch(int possition) {
		String canSwitch = user.Switch(possition);
		if(canSwitch == null) return true;
		console.setText(canSwitch);
		AIconsole.setText(" ");
		checkDead();
		return false;
	}
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
			if( playerSwitch(0))return;
		}
		else if(e.getSource() == pokemon2) {
			if( playerSwitch(1))return;
		}
		else if(e.getSource() == pokemon3) {
			if( playerSwitch(2))return;
		}
		else if(e.getSource() == pokemon4) {
			if( playerSwitch(3))return;
		}
		else if(e.getSource() == pokemon5) {
			if( playerSwitch(4))return;
		}
		else if(e.getSource() == pokemon6) {
			if( playerSwitch(5))return;
		}
		if(user.lost()) {
			this.dispose();
		};
		if(ai.getCurrent().getHp() ==0 ) {
			if(ai.lost()) {
				console.setText("GG you have won!");
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
		this.setVisible(true);
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
		this.setVisible(true);
	}
}