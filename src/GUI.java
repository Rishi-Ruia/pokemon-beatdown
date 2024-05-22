import java.awt.event.*; //from https://docs.oracle.com/javase/8/docs/api/java/awt/event/package-summary.html
import java.awt.*; // from https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/awt/package-summary.html

import javax.imageio.ImageIO;
import javax.swing.*; //from https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/package-summary.html
import java.io.*; //from https://docs.oracle.com/javase/8/docs/api/java/io/package-summary.html
import java.net.MalformedURLException;
import java.net.*;
public class GUI extends JFrame implements ActionListener{
	//default serial version UID
	private static final long serialVersionUID = 1L;
	//the buttons that are on the Jframe
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
	private JLabel console = new JLabel("A Pokemon battle has started!");
	private JLabel AIconsole = new JLabel();
	private Font font;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int) screenSize.getWidth();
	private int height = (int) screenSize.getHeight();
	private JLabel userMonLabel;
	
	//the constructor to create the initial GUI 
	public GUI(Player user, AI ai) throws  IOException{
		try {
		font = Font.createFont(Font.TYPE1_FONT, new File("PokemonGb-RAeo (1).ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("PokemonGb-Raeo(1).ttf")));
		}
		catch(Exception e) {
			
		}
		this.user = user;
		this.ai = ai;
		this.setLayout(null);
		JLabel userImage = new JLabel(new ImageIcon("AISprite_CSAProject_Raeed.png")); 
		JLabel aiImage = new JLabel(new ImageIcon("AISprite_CSAProject_Raeed.png")); 
		int imageWidth = ImageIO.read(new File("AISprite_CSAProject_Raeed.png")).getWidth();
		userImage.setBounds(width/5,height/2,imageWidth, imageWidth);
		aiImage.setBounds(width - imageWidth,imageWidth,imageWidth,imageWidth);
		this.add(userImage);
		this.add(aiImage);
		addMoves();
		addSwitch();		
		this.setSize(width, height);
		this.setTitle("battle window"); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		userName = new JLabel(user.getCurrent().getName());
		AIname = new JLabel(ai.getCurrent().getName());
		userName.setFont(new Font("Arial", Font.BOLD, 80));
		userName.setBounds(width/4, -height/10, 1000, 1000);
		this.add(userName);
		AIname.setFont(new Font("Arial", Font.BOLD, 80));
		AIname.setBounds((int) (width/1.5), (int) (-height/2.5) , 1000, 1000);
		this.add(AIname);
		console.setBounds(width / 6, (int) (height - height / 4), width - width / 6, height / 12);		
		console.setFont(new Font("Arial", Font.ITALIC, 30));
		this.add(console);
		AIconsole.setBounds(width / 6, (int) (height - height / 5), width - width / 6, height / 12);		
		AIconsole.setFont(new Font("Arial", Font.ITALIC, 30));
		AIconsole.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		console.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		this.add(AIconsole);
		
		URL userMonURL = new URL("https://play.pokemonshowdown.com/sprites/gen5ani-back/" + user.getCurrent().getRawName() + ".gif");
		ImageIcon userMon = new ImageIcon(userMonURL);
		userMon.setImage(userMon.getImage().getScaledInstance(userMon.getIconWidth() * 3, 
				userMon.getIconHeight() * 3, Image.SCALE_DEFAULT));
		userMonLabel = new JLabel(userMon);
		this.add(userMonLabel);
		userMonLabel.setBounds(width / 4, (int) (height / 2.5), 
				userMon.getIconWidth() * 3, userMon.getIconHeight() * 3);
		userMonLabel.setAlignmentX(width / 4f);
		
		this.setVisible(true);
	}
	//adds the moves and resizes them to the Jframe
	public void addMoves() {
		move1 = new JButton(user.getCurrent().getMove(0).getName());
		move1.setBounds(width / 6, (int) (height - height / 3), (int) (width / 6.5), height / 14);
		move1.setFont(font);
		move1.setBackground(Color.WHITE);
		move1.addActionListener(this);
		move2 = new JButton(user.getCurrent().getMove(1).getName());
		move2.setBounds(2 * (width / 6), (int) (height - height / 3), (int) (width / 6.5), height / 14);
		move2.setFont(new Font("Arial", Font.PLAIN, 20));
		move2.setBackground(Color.WHITE);
		move2.addActionListener(this);
		move3 = new JButton(user.getCurrent().getMove(2).getName());
		move3.setBounds(3 * (width / 6), (int) (height - height / 3), (int) (width / 6.5), height / 14);
		move3.setFont(new Font("Arial", Font.PLAIN, 20));
		move3.setBackground(Color.WHITE);
		move3.addActionListener(this);
		move4 = new JButton(user.getCurrent().getMove(3).getName());
		move4.setBounds(4 * (width / 6), (int) (height - height / 3), (int) (width / 6.5), height / 14);
		move4.setFont(new Font("Arial", Font.PLAIN, 20));
		move4.setBackground(Color.WHITE);
		move4.addActionListener(this);
		this.add(move1);
		this.add(move2);
		this.add(move3);
		this.add(move4);
		this.setVisible(true);
	}
	//adds the switch buttons to the Jframe
	private void addSwitch() {
		pokemon1 = new JButton(user.getPokemon(0).getName());
		pokemon1.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon1.setBounds(width / 84, height / 36, width / 8, width / 16);
		pokemon1.addActionListener(this);
		this.add(pokemon1);
		pokemon2 = new JButton(user.getPokemon(1).getName());
		pokemon2.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon2.setBounds(width / 84, height / 36 + (width / 14), width / 8, width / 16);
		pokemon2.addActionListener(this);
		this.add(pokemon2);
		pokemon3 = new JButton(user.getPokemon(2).getName());
		pokemon3.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon3.setBounds(width / 84, height / 36 + 2 * (width / 14), width / 8, width / 16);
		pokemon3.addActionListener(this);
		this.add(pokemon3);
		pokemon4 = new JButton(user.getPokemon(3).getName());
		pokemon4.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon4.setBounds(width / 84, height / 36 + 3 * (width / 14), width / 8, width / 16);
		pokemon4.addActionListener(this);
		this.add(pokemon4);
		pokemon5 = new JButton(user.getPokemon(4).getName());
		pokemon5.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon5.setBounds(width / 84, height / 36 + 4 * (width / 14), width / 8, width / 16);
		pokemon5.addActionListener(this);
		this.add(pokemon5);
		pokemon6 = new JButton(user.getPokemon(5).getName());
		pokemon6.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon6.setBounds(width / 84, height / 36 + 5 * (width / 14), width / 8, width / 16);
		pokemon6.addActionListener(this);
		this.add(pokemon6);
		this.setVisible(true);
	}
	//makes the user unable to click a move thus forcing them to switch
	public  void forceSwitch() {
		console.setText("Your Pokemon has fainted!");
		move1.setEnabled(false);
		move2.setEnabled(false);
		move3.setEnabled(false);
		move4.setEnabled(false);
		skipturn = true;
	}
	//sets the GUI to say you have lost and prevents you from moving
	public void displayLose() {
		AIconsole.setText("You have lost :(");
		forceSwitch();
	}
	//updates the GUI to the new pokemon the player switched to
	public boolean playerSwitch(int position) {
		String canSwitch = user.Switch(position);
		if(canSwitch == null) return true;
		console.setText(canSwitch);
		AIconsole.setText(" ");
		checkDead();
		return false;
	}
	//checks what is clicked on the GUI and calls the corresponding method
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
				console.setText("GG, you win!");
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
	//checks if the user pokemon have fainted, if they have it prevents the user from being able to switch to them
	public void checkDead() {
		userName.setText(user.getCurrent().getName());
		try {
			URL iconURL = new URL(
					"https://play.pokemonshowdown.com/sprites/gen5ani-back/" + user.getCurrent().getRawName() + ".gif");
			if (!isValidURL(iconURL)) {
				iconURL = new URL("https://play.pokemonshowdown.com/sprites/gen5/0.png");
			}
			userMonLabel.setIcon(new ImageIcon(iconURL));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
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
	
	public static boolean isValidURL(URL url) {
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("HEAD");
			int responseCode = connection.getResponseCode();
			return (responseCode == HttpURLConnection.HTTP_OK);
		} catch (IOException e) {
			return false;
		}
	}
	
//	public void userHP() {
//		JProgressBar hp = new JProgressBar(0, user.getCurrent().getHp());	
//		hp.setBounds();
//	}
//	
//	public void aiHP() {
//		
//	}
}