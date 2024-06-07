import java.awt.event.*; //from https://docs.oracle.com/javase/8/docs/api/java/awt/event/package-summary.html
import java.awt.*; // from https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/awt/package-summary.html

import javax.imageio.ImageIO;
import javax.swing.*; //from https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/package-summary.html
import javax.swing.event.*;

import java.io.*; //from https://docs.oracle.com/javase/8/docs/api/java/io/package-summary.html
import java.net.MalformedURLException;
import java.net.*;
import java.util.*;

public class GUI extends JFrame implements ActionListener {
	// default serial version UID
	private static final long serialVersionUID = 1L;
	// the buttons that are on the Jframe
	private javax.swing.Timer timer;

	private JLabel move1 = new JLabel();
	private JLabel move2 = new JLabel();
	private JLabel move3 = new JLabel();
	private JLabel move4 = new JLabel();
	private JLabel[] moveButtons = {move1, move2, move3, move4};
	private JButton pokemon1 = new JButton();
	private JButton pokemon2 = new JButton();
	private JButton pokemon3 = new JButton();
	private JButton pokemon4 = new JButton();
	private JButton pokemon5 = new JButton();
	private JButton pokemon6 = new JButton();
	private JButton[] switchButtons = {pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6};;
	private Player user;
	private AI ai;
	private JLabel userName;
	private JLabel AIname;
	private boolean skipturn = false;
	private JLabel console = new JLabel("A Pokemon battle has started!");
	private JLabel console2 = new JLabel();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = (int) screenSize.getWidth();
	private int height = (int) screenSize.getHeight();
	private JLabel userMon;
	private JLabel aiMon;
	private JLabel backgroundFinal;
	protected int round = 0;
	protected JProgressBar userHP;
	protected JProgressBar aiHP;
	private JButton mute;
	protected JLabel ball1 = new JLabel();
	protected JLabel ball2 = new JLabel();
	protected JLabel ball3 = new JLabel();
	protected JLabel ball4 = new JLabel();
	protected JLabel ball5 = new JLabel();
	protected JLabel ball6 = new JLabel();
	protected JLabel[] pokeballs = {ball1, ball2, ball3, ball4, ball5, ball6};
	protected int ballcount = 1;
	protected boolean isMuted;
	private Font pokemonFont;
	private JLabel consoleBG;

	// the constructor to create the initial GUI
	public GUI(Player user, AI ai, Pokemon[] mons) throws IOException, FontFormatException {
		this.user = user;
		this.ai = ai;
		this.setLayout(null);
		addFont();
		addMuteButton();
		addUserBar();
		addAIBar();
		addBalls();
		addTrainerSprites();
		addPokemonSprites();
		addMoves();
		addSwitches();
		initializeWindow();
		addUserName();
		addAIName();		
		addConsole();
		addConsole2();
		addConsoleBG();
		this.setVisible(true);
		addBackground();
	}
	
	public static ImageIcon getScaledIcon(String filename, int scaleX, int scaleY) {
		ImageIcon imageIcon = new ImageIcon(filename);
		Image image = imageIcon.getImage();
		return new ImageIcon(image.getScaledInstance(scaleX, scaleY, Image.SCALE_DEFAULT));
	}
	
	public ImageIcon getScaledMoveButton(int index) {
		Image moveImage = new ImageIcon("MOVE_BUTTONS/moveButton" + user.getCurrent().getMove(index).getType() + ".png")
				.getImage();
		Image moveScaled = moveImage.getScaledInstance((int) (width / 6.3), height / 12, Image.SCALE_DEFAULT);
		return new ImageIcon(moveScaled);
	}
	
	public void initializeWindow() {
		this.setSize(width, height);
		this.setTitle("Battle Window");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void addPokemonSprites() {
		userMon = new JLabel(getCurrentSprite(true));
		this.add(userMon);
		userMon.setBounds((int) (width / 3.4), (int) (height / 3.1), width / 6, width / 6);
		aiMon = new JLabel(getCurrentSprite(false));
		add(aiMon);
		aiMon.setBounds((int) (width / 1.65), height / 6, width / 6, width / 6);
	}
	
	public void addTrainerSprites() {
		JLabel userImage = new JLabel(getScaledIcon("trainer-back.png", width / 4, height / 2));
		JLabel aiImage = new JLabel(getScaledIcon("AISprite_CSAProject_Raeed.png", width / 4, height / 2));
		userImage.setBounds(width / 12, height / 6, width / 4, height / 2);
		aiImage.setBounds((int) (width / 1.28), -height / 27, width / 4, height / 2);
		this.add(userImage);
		this.add(aiImage);
	}
	
	public void addAIName() {
		AIname = new JLabel(ai.getCurrent().getName());
		AIname.setFont(pokemonFont.deriveFont(Font.BOLD, 50));
		AIname.setBounds((int) (width / 1.7), (int) (-height / 2.5), width / 2, (int) (height / 1.1));
		this.add(AIname);
	}
	
	public void addUserName() {
		userName = new JLabel(user.getCurrent().getName());
		userName.setFont(pokemonFont.deriveFont(Font.BOLD, 50));
		userName.setBounds((int) (width / 3.5), (int) (-height / 4), width / 2, (int) (height / 1.1));
		this.add(userName);
	}
	
	public void addConsole() {
		console.setBounds((int) (width / 4.5), (int) (height - height / 4.5), width - width / 6, height / 12);
		console.setFont(pokemonFont.deriveFont(Font.ITALIC, 20));
		console.setForeground(Color.white);
		this.add(console);
	}
	
	public void addConsole2() {
		console2.setBounds((int) (width / 4.5), (int) (height - height / 5.5), width - width / 6, height / 12);
		console2.setFont(pokemonFont.deriveFont(Font.ITALIC, 20));
		console2.setForeground(Color.white);
		console2.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		console.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		this.add(console2);
	}
	
	public void addConsoleBG() {
		consoleBG = new JLabel(getScaledIcon("console.png", width - width / 4, (int) height / 5));
		consoleBG.setBounds((int) (width / 5), (int) (height - height / 2.55), width - width / 4, height / 2);
		consoleBG.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		this.add(consoleBG);
	}
	
	public void addFont() throws FontFormatException, IOException {
		File pokemonFontFile = new File("pokemon-font.ttf");
		pokemonFont = Font.createFont(Font.TRUETYPE_FONT, pokemonFontFile);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, pokemonFontFile));
	}
	
	public ImageIcon getCurrentSprite(boolean isUser) {
		Pokemon current = user.getCurrent();
		String folder = "spritesback";
		
		if (!isUser) {
			current = ai.getCurrent();
			folder = "sprites";
		}
		
		String spritePath = getSpritePath(current, getSpriteIndex(current.getDex()), folder);
		
		return getScaledIcon(spritePath, width / 6, width / 6);
	}

	public void onPaintEvent(Graphics g) {
		aiMon.update(g);
	}

	// adds the moves and resizes them to the Jframe
	public void addMoves() {
		add(aiMon);
		
		for (int i = 0; i < moveButtons.length; i++) {
			moveButtons[i].setText(user.getCurrent().getMove(i).getName());
			// Sets Move's image to a scaled instance of the move button of its type
			ImageIcon moveButton = getScaledMoveButton(i);
			moveButtons[i].setIcon(moveButton);
			moveButtons[i].setHorizontalTextPosition(JLabel.CENTER);
			moveButtons[i].setBounds((i + 1) * width / 5, (int) (height - height / 3), (int) (width / 6.3), height / 12);
			moveButtons[i].setFont(pokemonFont.deriveFont(Font.PLAIN, 20));
			moveButtons[i].setForeground(Color.white);
			moveButtons[i].setBackground(Color.WHITE);
			moveButtons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			int index = i;
			moveButtons[i].addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) {}

				public void mousePressed(MouseEvent e) {
					doAction("move" + (index + 1));
				}

				public void mouseExited(MouseEvent e) {}

				public void mouseEntered(MouseEvent e) {}

				public void mouseClicked(MouseEvent e) {}
			});
			
			this.add(moveButtons[i]);
		}		
		this.setVisible(true);
	}
	
	public void addBackground() {
		ImageIcon background = new ImageIcon("city.jpg");
		Image backgroundTemp = background.getImage();
		Image backgroundscaled = backgroundTemp.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		backgroundFinal = new JLabel(new ImageIcon(backgroundscaled));
		backgroundFinal.setBounds(0, 0, width, height);
		this.remove(backgroundFinal);
		this.add(backgroundFinal);
	}

	// adds the switch buttons to the Jframe
	private void addSwitches() {
		for (int i = 0; i < switchButtons.length; i++) {
			switchButtons[i].setText(user.getPokemon(i).getName());
			switchButtons[i].setFont(new Font("Lucida Console", Font.PLAIN, 20));
			switchButtons[i].setBounds(width / 200, height / 36 + i * (width / 14), width / 8, width / 16);
			switchButtons[i].setIcon(getFrontSprite(i));
			switchButtons[i].addActionListener(this);
			this.add(switchButtons[i]);
		}
		this.setVisible(true);
	}

	// makes the user unable to click a move thus forcing them to switch
	public void forceSwitch() {
		slowPrint("Your Pokemon has fainted!", console2);
		for(int i =0; i < 4; i++) moveButtons[i].setEnabled(false);
		skipturn = true;
	}

	// sets the GUI to say you have lost and prevents you from moving
	public void displayLose() {
		slowPrint("You have lost :(", console2);
		forceSwitch();
	}

	// updates the GUI to the new pokemon the player switched to
	public boolean playerSwitch(int position) {
		String canSwitch = user.Switch(position);
		if (canSwitch == null)
			return true;
		slowPrint(canSwitch, console);
		checkDead();
		userMon.setIcon(getCurrentSprite(true));
		switchBar(userHP, user.getCurrent());
		switchMoveButtons();
		this.remove(backgroundFinal);
		this.add(backgroundFinal);
		return false;

	}

	public Move AIMove() {
		Move use = new Move();
		Move random = new Move();
		int max = 0;
		for (int i = 0; i < 4; i++) {
			random = ai.getCurrent().getMove(i);
			double stab = 1;
			if (ai.getCurrent().getType1().equals(random.getType())
					|| ai.getCurrent().getType2().equals(random.getType()))
				stab = 1.5;
			double mod = stab * (Math.random() * 0.16 + 0.85);
			int currentDamage = Game.damageCalc(ai.getCurrent(), random, user.getCurrent(), random.isSpecial(), mod);
			if (currentDamage > max) {
				use = random;
				max = currentDamage;
			}
		}
		return use;
	}

	// checks what is clicked on the GUI and calls the corresponding method
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mute) {
			doAction("mute");
		} else if (e.getSource() == pokemon1) {
			doAction("pokemon1");
		} else if (e.getSource() == pokemon2) {
			doAction("pokemon2");
		} else if (e.getSource() == pokemon3) {
			doAction("pokemon3");
		} else if (e.getSource() == pokemon4) {
			doAction("pokemon4");
		} else if (e.getSource() == pokemon5) {
			doAction("pokemon5");
		} else if (e.getSource() == pokemon6) {
			doAction("pokemon6");
		}
	}
	
	public void updateGuiForAiFaint() {
		disaBall();
		ballcount++;
		skipturn = true;
		if (ai.lost()) {
			ball6.disable();
			console.setText("GG, you win!");
			aiMon.disable();
		} else {
			ai.fainted();
			aiMon.setIcon(getCurrentSprite(false));
			this.AIname.setText(ai.getCurrent().getName());
			switchBar(aiHP, ai.getCurrent());
		}
	}
	
	public void refreshGUI() {
		this.setVisible(true);
		this.repaint();
		this.revalidate();
	}
	
	public void printMoveToConsole(String source) {
		if (source.equals("move1")) {
			slowPrint(Game.attack(user.getCurrent(), user.getCurrent().getMove(0), ai.getCurrent(),
					user.getCurrent().getMove(0).isSpecial()), console);
		} else if (source.equals("move2")) {
			slowPrint(Game.attack(user.getCurrent(), user.getCurrent().getMove(1), ai.getCurrent(),
					user.getCurrent().getMove(1).isSpecial()), console);
		} else if (source.equals("move3")) {
			slowPrint(Game.attack(user.getCurrent(), user.getCurrent().getMove(2), ai.getCurrent(),
					user.getCurrent().getMove(2).isSpecial()), console);
		} else if (source.equals("move4")) {
			slowPrint(Game.attack(user.getCurrent(), user.getCurrent().getMove(3), ai.getCurrent(),
					user.getCurrent().getMove(3).isSpecial()), console);
		}
	}
	
	public void doMove(int moveNumber) {
		
	}
	
	public void doSwitch(int switchNumber) {
		
	}

	public void doAction(String source) {
		Move m = AIMove();
		if (source.equals("mute")) {
			if (!isMuted) {
				Game.mute(false);
				isMuted = true;
				return;
			} else {
				Game.mute(true);
				isMuted = false;
				return;
			}
		} else if (source.equals("pokemon1") || source.equals("pokemon2") || source.equals("pokemon3")
				|| source.equals("pokemon4") || source.equals("pokemon5") || source.equals("pokemon6")) {
			if (source.equals("pokemon1")) {
				if (playerSwitch(0)) return;
			} else if (source.equals("pokemon2")) {
				if (playerSwitch(1)) return;
			} else if (source.equals("pokemon3")) {
				if (playerSwitch(2)) return;
			} else if (source.equals("pokemon4")) {
				if (playerSwitch(3)) return;
			} else if (source.equals("pokemon5")) {
				if (playerSwitch(4)) return;
			} else if (source.equals("pokemon6")) {
				if (playerSwitch(5)) return;
			}
			
			userMon.enable();
			if (ai.getCurrent().getHp() == 0) {
				updateGuiForAiFaint();
			}
			
			if (skipturn == true) {
				skipturn = false;
				return;
			}
			
			String AITurn = ai.AITurn(user.getCurrent(), m);
			slowPrint(AITurn, console2);
			console2.setText(AITurn);
			if (user.getCurrent().getHp() == 0)
				userMon.setIcon(getCurrentSprite(true));
			
			refreshGUI();
			
			if (user.getCurrent().getHp() == 0) {
				userMon.disable();
			}
			
			if (user.lost()) {
				this.forceSwitch();
				slowPrint("You lost", console2);
			}

			update(userHP, user.getCurrent());
			update(aiHP, ai.getCurrent());
			
			refreshGUI();
			
		} else if (user.getCurrent().getSpeed() > ai.getCurrent().getSpeed()) {
			if (user.getCurrent().getHp() <= 0)
				return;
			
			printMoveToConsole(source);
			
			timer.start();
			
			if (ai.getCurrent().getHp() == 0) {
				updateGuiForAiFaint();
				update(userHP, user.getCurrent());
				update(aiHP, ai.getCurrent());
			}
			
			if (skipturn == true) {
				skipturn = false;
				return;
			}
			
			String temp = ai.AITurn(user.getCurrent(), m);
			slowPrint(temp, console2);
			if (user.getCurrent().getHp() == 0)
				userMon.setIcon(getCurrentSprite(true));
			refreshGUI();
			if (user.getCurrent().getHp() == 0) {
				userMon.disable();
			}
			if (user.lost()) {
				this.forceSwitch();
				slowPrint("You lost", console);
			}

			update(userHP, user.getCurrent());
			update(aiHP, ai.getCurrent());
			refreshGUI();
		} else {
			if (user.getCurrent().getHp() <= 0)
				return;
			
			if (skipturn) {
				skipturn = false;
				return;
			}
			
			if (ai.lost())
				return;
			
			String temp = ai.AITurn(user.getCurrent(), m);
			slowPrint(temp, console2);
			
			if (user.getCurrent().getHp() == 0) {
				userMon.setIcon(getCurrentSprite(true));
				refreshGUI();
				if (user.getCurrent().getHp() == 0) {
					userMon.disable();
					slowPrint("Your Pokemon has fainted!", console2);
				}
				if (user.lost()) {
					this.forceSwitch();
					slowPrint("You lost!", console2);
				}
				update(userHP, user.getCurrent());
				update(aiHP, ai.getCurrent());
				return;
			}
			
			printMoveToConsole(source);
			
			if (ai.getCurrent().getHp() == 0) {
				updateGuiForAiFaint();
			}
		}
		
		refreshGUI();
		if (user.getCurrent().getHp() == 0) {
			skipturn = true;
			userMon.disable();
		}
		
		if (user.lost()) {
			this.forceSwitch();
			slowPrint("You lost!", console2);
		}

		update(userHP, user.getCurrent());
		update(aiHP, ai.getCurrent());
	}

	// checks if the user pokemon have fainted, if they have it prevents the user
	// from being able to switch to them
	public void checkDead() {
		userName.setText(user.getCurrent().getName());
		
		for (int i = 0; i < moveButtons.length; i++) {
			moveButtons[i].setText(user.getCurrent().getMove(i).getName());
			moveButtons[i].setEnabled(true);
		}
		
		for (int i = 0; i < switchButtons.length; i++) {
			if (user.getPokemon(i).getHp() == 0)
				switchButtons[i].setEnabled(false);
		}
		
		revalidate();
		this.setVisible(true);
	}
	
	public String getSpriteIndex(int dex) {
		String spriteIndex = dex + "";
		while (spriteIndex.length() < 3) {
			spriteIndex = "0" + spriteIndex;
		}
		return spriteIndex;
	}
	
	public String getSpritePath(Pokemon current, String spriteIndex, String folder) {
		String backOrFront = "a-b_bw_";
		
		if (folder.equals("sprites")) {
			backOrFront = "ani_bw_";
		}
		
		String[] rotoms = {" Wash", " Mow", " Heat", " Fan", " Frost"};
		
		for (String rotomVariant : rotoms) {
			if (current.getName().contains(rotomVariant))
				return folder + "/" + backOrFront + spriteIndex + "-" + rotomVariant.toLowerCase().substring(1) + ".gif";
		}
		
		if (new File(folder + "/" + backOrFront + spriteIndex + ".gif").exists()) {
			return folder + "/" + backOrFront + spriteIndex + ".gif";
		}
			
		return folder + "/" + backOrFront + spriteIndex + "_f.gif";
	}

	public ImageIcon getFrontSprite(int index) {
		Pokemon mon = user.getPokemon(index);
		
		return new ImageIcon(getSpritePath(mon, getSpriteIndex(mon.getDex()), "sprites"));
	}

	public void addMuteButton() {
		isMuted = false;
		mute = new JButton("Mute");
		mute.setLayout(null);
		mute.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		mute.setBounds(width / 50, height - (height / 7), width / 16, width / 32);
		mute.addActionListener(this);
		add(mute);
		this.setVisible(true);
	}

	public void addUserBar() {
		userHP = new JProgressBar(0, user.getCurrent().getHp());
		userHP.setLayout(null);
		userHP.setFont(new Font("Arial", Font.BOLD, 30));
		userHP.setBounds((int) (width / 3.5), height / 4, (int) (width / 4.3), height / 20);
		userHP.setForeground(Color.green);
		userHP.setBackground(Color.black);
		userHP.setValue(user.getCurrent().getMaxHp());
		userHP.setStringPainted(true);
		add(userHP);
	}

	public void switchBar(JProgressBar s, Pokemon p) {
		s.setMaximum(p.getMaxHp());
		s.setValue(p.getHp());
		update(s, p);
	}

	public void switchMoveButtons() {
		for (int i = 0; i < moveButtons.length; i++) {
			moveButtons[i].setIcon(getScaledMoveButton(i));
		}
	}

	public void addAIBar() {
		aiHP = new JProgressBar(0, ai.getCurrent().getHp());
		aiHP.setLayout(null);
		aiHP.setFont(new Font("Arial", Font.BOLD, 30));
		aiHP.setBounds((int) (width / 1.7), height / 9, (int) (width / 4.3), height / 20);
		aiHP.setForeground(Color.green);
		aiHP.setBackground(Color.black);
		aiHP.setValue(ai.getCurrent().getMaxHp());
		aiHP.setStringPainted(true);
		add(aiHP);
	}

	public void update(JProgressBar hp, Pokemon current) {
		hp.setValue(current.getHp());

		if ((double) current.getHp() / current.getMaxHp() <= 0.2) {
			hp.setForeground(Color.red);
		} else if ((double) current.getHp() / current.getMaxHp() <= 0.5) {
			hp.setForeground(Color.yellow);
		} else {
			hp.setForeground(Color.green);
		}
	}

	public void addBalls() {
		for (int i = 0; i < pokeballs.length; i++) {
			pokeballs[i].setIcon(getScaledIcon("pokeball.png", width / 17, height / 15));
			pokeballs[i].setBounds((int) (width / 1.06), (width / 30) * i, width / 16, height / 15);
			add(pokeballs[i]);
		}
	}

	public void disaBall() {
		for (int i = 0; i < 6; i++) {
			if (ballcount == (i + 1)) {
				pokeballs[i].disable();
			}
		}
	}

	int index = 0;

	public void slowPrint(String message, JLabel texter) {

		if (timer != null && timer.isRunning()) {
			repaint();
			
			return;
			
		}
			
		index = 0;
		texter.setText("");

		timer = new javax.swing.Timer(30, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {

				texter.setText(texter.getText() + String.valueOf(message.charAt(index)));
				index++;
				if (index >= message.length()) {
					timer.stop();
				}
			}
		});
		timer.start();
	}
}