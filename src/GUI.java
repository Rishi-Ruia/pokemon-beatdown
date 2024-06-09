import java.awt.event.*; //from https://docs.oracle.com/javase/8/docs/api/java/awt/event/package-summary.html
import java.awt.*; // from https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/awt/package-summary.html

import javax.imageio.ImageIO;
import javax.swing.*; //from https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/package-summary.html
import javax.swing.Timer;
import javax.swing.event.*;

import java.io.*; //from https://docs.oracle.com/javase/8/docs/api/java/io/package-summary.html
import java.net.MalformedURLException;
import java.net.*;
import java.util.*;

public class GUI extends JFrame implements ActionListener {
	// default serial version UID
	private static final long serialVersionUID = 1L;
	// the buttons that are on the Jframe
	private javax.swing.Timer timer1 = new Timer(2000, null);
	private javax.swing.Timer timer2;
	private int index1 =0;
	private int index2 =0;
	private JLabel move1 = new JLabel();
	private JLabel move2 = new JLabel();
	private JLabel move3 = new JLabel();
	private JLabel move4 = new JLabel();
	private JLabel[] moveButtons = {move1, move2, move3, move4};
	private JLabel pokemon1 = new JLabel();
	private JLabel pokemon2 = new JLabel();
	private JLabel pokemon3 = new JLabel();
	private JLabel pokemon4 = new JLabel();
	private JLabel pokemon5 = new JLabel();
	private JLabel pokemon6 = new JLabel();
	private JLabel switchSprite1 = new JLabel();
	private JLabel switchSprite2 = new JLabel();
	private JLabel switchSprite3 = new JLabel();
	private JLabel switchSprite4 = new JLabel();
	private JLabel switchSprite5 = new JLabel();
	private JLabel switchSprite6 = new JLabel();
	private JLabel[] switchButtons = {pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6};
	private JLabel[] switchSprites = {switchSprite1, switchSprite2, switchSprite3, switchSprite4, switchSprite5, switchSprite6};
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
	private Move aimove;
	// the constructor to create the initial GUI
	public GUI(Player user, AI ai, Pokemon[] mons) throws IOException, FontFormatException {
		this.user = user;
		this.ai = ai;
		
		// It is important to keep the order of the following methods
		this.setLayout(null);
		this.addFont();
		this.addMuteButton();
		this.addHpBars();
		this.addBalls();
		this.addSprites();
		this.addMoves();
		this.addSwitches();
		this.initializeWindow();
		this.addNames();
		this.addConsoles();
		this.setVisible(true);
		this.addBackground();
	}
	
	public static ImageIcon getScaledIcon(String filename, int scaleX, int scaleY) {
		Image image = new ImageIcon(filename).getImage();
		return new ImageIcon(image.getScaledInstance(scaleX, scaleY, Image.SCALE_DEFAULT));
	}
	
	public static ImageIcon getScaledIcon(ImageIcon imageIcon, int scaleX, int scaleY) {
		Image image = imageIcon.getImage();
		return new ImageIcon(image.getScaledInstance(scaleX, scaleY, Image.SCALE_DEFAULT));
	}
	
	public ImageIcon getScaledMoveButton(int index) {
		return getScaledIcon("MOVE_BUTTONS/moveButton" + user.getCurrent().getMove(index).getType() + ".png", (int) (width / 6.3), height / 12);
	}
	
	public void addFont() throws FontFormatException, IOException {
		File pokemonFontFile = new File("FONTS/pkmndp.ttf");
		pokemonFont = Font.createFont(Font.TRUETYPE_FONT, pokemonFontFile);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, pokemonFontFile));
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
	
	public void addHpBars() {
		// Adding user's HP bar
		userHP = new JProgressBar(0, user.getCurrent().getHp());
		userHP.setLayout(null);
		userHP.setFont(pokemonFont.deriveFont(Font.BOLD, (6 * width) / 384));
		userHP.setBounds((int) (width / 3.5), height / 4, (int) (width / 4.3), height / 20);
		userHP.setForeground(Color.green);
		userHP.setBackground(Color.black);
		userHP.setValue(user.getCurrent().getMaxHp());
		userHP.setStringPainted(true);
		add(userHP);
		// Adding AI's HP bar
		aiHP = new JProgressBar(0, ai.getCurrent().getHp());
		aiHP.setLayout(null);
		aiHP.setFont(pokemonFont.deriveFont(Font.BOLD, (6 * width) / 384));
		aiHP.setBounds((int) (width / 1.7), height / 6, (int) (width / 4.3), height / 20);
		aiHP.setForeground(Color.green);
		aiHP.setBackground(Color.black);
		aiHP.setValue(ai.getCurrent().getMaxHp());
		aiHP.setStringPainted(true);
		add(aiHP);
	}
	
	public void addBalls() {
		for (int i = 0; i < pokeballs.length; i++) {
			pokeballs[i].setIcon(getScaledIcon("pokeball.png", width / 17, height / 15));
			pokeballs[i].setBounds((int) (width / 1.06), (width / 30) * i, width / 16, height / 15);
			add(pokeballs[i]);
		}
	}
	
	public void addSprites() {
		// Adding user Pokemon sprites
		userMon = new JLabel(getCurrentSprite(true));
		this.add(userMon);
		userMon.setBounds((int) (width / 3.4), (int) (height / 3.1), width / 6, width / 6);
		// Adding AI Pokemon sprites
		aiMon = new JLabel(getCurrentSprite(false));
		add(aiMon);
		aiMon.setBounds((int) (width / 1.65), (int) (height / 4.5), width / 6, width / 6);
		// Adding Trainer sprites
		JLabel userImage = new JLabel(getScaledIcon("user-trainer.png", width / 4, height / 2));
		JLabel aiImage = new JLabel(getScaledIcon("ai-trainer.png", width / 4, height / 2));
		userImage.setBounds(width / 12, height / 6, width / 4, height / 2);
		aiImage.setBounds((int) (width / 1.28), -height / 27, width / 4, height / 2);
		this.add(userImage);
		this.add(aiImage);
	}
	
	public void addMoves() {
		add(aiMon);
		
		for (int i = 0; i < moveButtons.length; i++) {
			moveButtons[i].setText(user.getCurrent().getMove(i).getName());
			// Sets Move's image to a scaled instance of the move button of its type
			ImageIcon moveButton = getScaledMoveButton(i);
			moveButtons[i].setIcon(moveButton);
			moveButtons[i].setHorizontalTextPosition(JLabel.CENTER);
			moveButtons[i].setBounds((i + 1) * width / 5, (int) (height - height / 3), (int) (width / 6.3), height / 12);
			moveButtons[i].setFont(pokemonFont.deriveFont(Font.PLAIN, (7 * width) / 384));
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
	
	public void addSwitches() {
		for (int i = 0; i < switchButtons.length; i++) {
			switchButtons[i].setText(user.getPokemon(i).getName());
			switchSprites[i].setIcon(getScaledIcon(getFrontSprite(i), width / 20, width / 20));
			if (i == 0) {
				switchButtons[i].setIcon(getScaledIcon("switchButtonSelected.png", width / 8, width / 16));
			} else {
				switchButtons[i].setIcon(getScaledIcon("switchButton.png", width / 8, width / 16));
			}
			switchButtons[i].setHorizontalTextPosition(JLabel.CENTER);
			switchSprites[i].setBounds(width / 10, height / 22 + i * (width / 14), width / 10, height / 10);
			switchButtons[i].setBounds(width / 200, height / 36 + i * (width / 14), (int) width / 6, height / 9);
			switchButtons[i].setFont(pokemonFont.deriveFont(Font.PLAIN, (6 * width) / 384));
			switchButtons[i].setForeground(Color.white);
			switchButtons[i].setBackground(Color.WHITE);
			switchButtons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			int buttonNumber = i + 1;
			switchButtons[i].addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) {}

				public void mousePressed(MouseEvent e) {
					doAction("pokemon" + buttonNumber);
				}

				public void mouseExited(MouseEvent e) {}

				public void mouseEntered(MouseEvent e) {}

				public void mouseClicked(MouseEvent e) {}
			});
			
			
			this.add(switchSprites[i]);
			this.add(switchButtons[i]);
		}
		this.setVisible(true);
	}
	
	public void initializeWindow() {
		this.setSize(width, height);
		this.setTitle("Battle Window");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void addNames() {
		// Adding AI Name
		AIname = new JLabel(ai.getCurrent().getName());
		AIname.setFont(pokemonFont.deriveFont(Font.BOLD, (15 * width) / 384));
		AIname.setForeground(Color.WHITE);
		AIname.setBounds((int) (width / 1.7), (int) (-height / 3), width / 2, (int) (height / 1.1));
		this.add(AIname);
		// Adding User Name
		userName = new JLabel(user.getCurrent().getName());
		userName.setFont(pokemonFont.deriveFont(Font.BOLD, (15 * width) / 384));
		userName.setForeground(Color.WHITE);
		userName.setBounds((int) (width / 3.5), (int) (-height / 4), width / 2, (int) (height / 1.1));
		this.add(userName);
	}
	
	public void addConsoles() {
		// Adding console 1
		console.setBounds((int) (width / 4.5), (int) (height - height / 4.5), width - width / 6, height / 12);
		console.setFont(pokemonFont.deriveFont(Font.ITALIC, (6 * width) / 384));
		console.setForeground(Color.white);
		this.add(console);
		// Adding console 2
		console2.setBounds((int) (width / 4.5), (int) (height - height / 5.5), width - width / 6, height / 12);
		console2.setFont(pokemonFont.deriveFont(Font.ITALIC, (6 * width) / 384));
		console2.setForeground(Color.white);
		console2.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		console.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		this.add(console2);
		// Adding console background
		consoleBG = new JLabel(getScaledIcon("console.png", width - width / 4, (int) height / 5));
		consoleBG.setBounds((int) (width / 5), (int) (height - height / 2.55), width - width / 4, height / 2);
		consoleBG.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		this.add(consoleBG);
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

	// makes the user unable to click a move thus forcing them to switch
	public void forceSwitch() {
		console2print("Your Pokemon has fainted!");
		for(int i =0; i < 4; i++) moveButtons[i].setEnabled(false);
		skipturn = true;
	}

	// sets the GUI to say you have lost and prevents you from moving
	public void displayLose() {
		console2print("You have lost :(");
		forceSwitch();
	}

	// updates the GUI to the new pokemon the player switched to
	public boolean playerSwitch(int position) {
		String canSwitch = user.Switch(position);
		if (canSwitch == null)
			return true;
		console1print(canSwitch);
		checkDead();
		userMon.setIcon(getCurrentSprite(true));
		switchBar(userHP, user.getCurrent());
		switchMoveButtons();
		highlightSelectedSwitchButton(position);
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
	
	public void printUserMoveToConsole1(String source) {			
		if (source.equals("move1")) {
			console1print(Game.attack(user.getCurrent(), user.getCurrent().getMove(0), ai.getCurrent(),
					user.getCurrent().getMove(0).isSpecial()));
		} else if (source.equals("move2")) {
			console1print(Game.attack(user.getCurrent(), user.getCurrent().getMove(1), ai.getCurrent(),
					user.getCurrent().getMove(1).isSpecial()));
		} else if (source.equals("move3")) {
			console1print(Game.attack(user.getCurrent(), user.getCurrent().getMove(2), ai.getCurrent(),
					user.getCurrent().getMove(2).isSpecial()));
		} else if (source.equals("move4")) {
			console1print(Game.attack(user.getCurrent(), user.getCurrent().getMove(3), ai.getCurrent(),
					user.getCurrent().getMove(3).isSpecial()));
		}
	}
	
	public void printUserMoveToConsole2(String source) {			
		if (source.equals("move1")) {
			console2print(Game.attack(user.getCurrent(), user.getCurrent().getMove(0), ai.getCurrent(),
					user.getCurrent().getMove(0).isSpecial()));
		} else if (source.equals("move2")) {
			console2print(Game.attack(user.getCurrent(), user.getCurrent().getMove(1), ai.getCurrent(),
					user.getCurrent().getMove(1).isSpecial()));
		} else if (source.equals("move3")) {
			console2print(Game.attack(user.getCurrent(), user.getCurrent().getMove(2), ai.getCurrent(),
					user.getCurrent().getMove(2).isSpecial()));
		} else if (source.equals("move4")) {
			console2print(Game.attack(user.getCurrent(), user.getCurrent().getMove(3), ai.getCurrent(),
					user.getCurrent().getMove(3).isSpecial()));
		}
	}

	public void doAction(String source) {
		aimove = AIMove();
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
			
			
			// AI turn if it is slower, prints user loss to console 2 if true, console 1 if false
			doSlowerAITurn(true);
			
		} else if (user.getCurrent().getSpeed() > ai.getCurrent().getSpeed()) {
			if (user.getCurrent().getHp() <= 0)
				return;
			
			printUserMoveToConsole1(source);
			
			console2print("");
			
			// Checking if AI is dead
			if (ai.getCurrent().getHp() == 0) {
				updateGuiForAiFaint();
				update(userHP, user.getCurrent());
				update(aiHP, ai.getCurrent());
			}
			
			if (skipturn == true) {
				skipturn = false;
				return;
			}
			
			// AI turn if it is slower, prints user loss to console 1 if false, console 2 if true
			doSlowerAITurn(false);
		} else {
			if (user.getCurrent().getHp() <= 0)
				return;
			
			if (skipturn) {
				skipturn = false;
				return;
			}
			
			if (ai.lost())
				return;
			
			// AI turn if it is faster, returns true if it kills user, false otherwise
			if (doFasterAITurn()) {
				return;
			}
			
			printUserMoveToConsole2(source);
			
			if (ai.getCurrent().getHp() == 0) {
				updateGuiForAiFaint();
			}
		}
		
		refreshGUI();
		
		if (user.getCurrent().getHp() == 0) {
			skipturn = true;
			userMon.disable();
			checkDead();
		}
		
		// Updates HP bars if you don't die
		update(userHP, user.getCurrent());
		update(aiHP, ai.getCurrent());
	}
	
	public boolean doFasterAITurn() {
		String temp = ai.AITurn(user.getCurrent(), aimove);
		console1print(temp);
		
		if (user.getCurrent().getHp() == 0) {
			userMon.setIcon(getCurrentSprite(true));
			refreshGUI();
			if (user.getCurrent().getHp() == 0) {
				userMon.disable();
				checkDead();
				console2print("Your Pokemon has fainted!");
			}
			if (user.lost()) {
				this.forceSwitch();
				console2print("You lost!");
			}
			update(userHP, user.getCurrent());
			update(aiHP, ai.getCurrent());
			
			return true;
		}
		
		return false;
	}
	
	public void doSlowerAITurn(boolean isOnSwitch) {
		String AITurn = ai.AITurn(user.getCurrent(), aimove);
		console2print(AITurn);
		
		if (user.getCurrent().getHp() == 0) {
			userMon.setIcon(getCurrentSprite(true));
			refreshGUI();
			userMon.disable();
			checkDead();
		}
		
		if (user.lost() && isOnSwitch) {
			this.forceSwitch();
			console2print("You lost!");
		} else if (user.lost()) {
			this.forceSwitch();
			console1print("You lost!");
		}

		update(userHP, user.getCurrent());
		update(aiHP, ai.getCurrent());
		
		refreshGUI();
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
			if (user.getPokemon(i).getHp() == 0) {
				switchButtons[i].setEnabled(false);
				switchSprites[i].setEnabled(false);
			}
		}
		
		if (user.getCurrent().getHp() == 0) {
			toggleMoves(false);
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
	
	public void highlightSelectedSwitchButton(int index) {
		for (int i = 0; i < switchButtons.length; i++) {
			switchButtons[i].setIcon(getScaledIcon("switchButton.png", (int) width / 8, height / 9));
		}
		
		switchButtons[index].setIcon(getScaledIcon("switchButtonSelected.png", (int) width / 8, height / 9));
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

	public void disaBall() {
		for (int i = 0; i < 6; i++) {
			if (ballcount == (i + 1)) {
				pokeballs[i].disable();
			}
		}
	}
	
	public void toggleMoves(boolean toggle) {
		for (int i = 0; i < moveButtons.length; i++) {
			moveButtons[i].setEnabled(toggle);
		}
	}
	
	public void console1print(String message) {
//		toggleMoves(false);
//		index1 = 0;
//		console.setText("");
//		timer1 = new javax.swing.Timer(-80, new AbstractAction() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				console.setText(console.getText() + String.valueOf(message.charAt(index1)));
//				index1++;
//				
//				// Ends the print by checking if index is over the length of the message
//				if (index1 >= message.length()) {
//					timer1.stop();
//					if (user.getCurrent().getHp() != 0) {
//						toggleMoves(true);
//					}
//					return;
//				}
//			}
//
//		});
//		timer1.start();
		console.setText(message);
	}
	
	public void console2print(String message) {
//		toggleMoves(false);
//		index2 = 0;
//		console2.setText("");
//		timer2 = new javax.swing.Timer(-80, new AbstractAction() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				console2.setText(console2.getText() + String.valueOf(message.charAt(index2)));
//				index2++;
//				
//				// Ends the print by checking if index is over the length of the message
//				if (index2 >= message.length()) {
//					timer2.stop();
//					if (user.getCurrent().getHp() != 0) {
//						toggleMoves(true);
//					}
//					return;
//				}
//			}
//		});
//		timer2.start();
		console2.setText(message);
	}
}