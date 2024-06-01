import java.awt.event.*; //from https://docs.oracle.com/javase/8/docs/api/java/awt/event/package-summary.html
import java.awt.*; // from https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/awt/package-summary.html

import javax.imageio.ImageIO;
import javax.swing.*; //from https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/package-summary.html
import javax.swing.event.*;

import java.io.*; //from https://docs.oracle.com/javase/8/docs/api/java/io/package-summary.html
import java.net.MalformedURLException;
import java.net.*;

public class GUI extends JFrame implements ActionListener {
	// default serial version UID
	private static final long serialVersionUID = 1L;
	// the buttons that are on the Jframe
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
	protected JLabel ball1;
	protected JLabel ball2;
	protected JLabel ball3;
	protected JLabel ball4;
	protected JLabel ball5;
	protected JLabel ball6;
	protected int ballcount = 1;
	protected boolean isMuted;

	// the constructor to create the initial GUI
	public ImageIcon gifsIcon(boolean pokemon) {
		ImageIcon imageIcon = new ImageIcon(spriteInit(pokemon));
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(width / 6, width / 6, Image.SCALE_DEFAULT);
		return new ImageIcon(newimg);
	}

	public JLabel pokemongifs(boolean pokemon) {
		ImageIcon imageIcon = new ImageIcon(spriteInit(pokemon));
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(width / 6, width / 6, Image.SCALE_DEFAULT);
		return new JLabel(new ImageIcon(newimg));
	}

	public GUI(Player user, AI ai, Pokemon[] mons) throws IOException {
		this.user = user;
		this.ai = ai;
		this.setLayout(null);
		muteButton();
		userBar();
		aiBar();
		addBalls();
		userMon = (pokemongifs(true));
		this.add(userMon);
		userMon.setBounds((int) (width / 3.4), (int) (height / 3.1), width / 6, width / 6);
		aiMon = (pokemongifs(false));
		add(aiMon);
		aiMon.setBounds((int) (width / 1.65), height / 6, width / 6, width / 6);
		ImageIcon tempIcon = new ImageIcon("AISprite_CSAProject_Raeed.png");
		Image temp = tempIcon.getImage();
		Image newTemp = temp.getScaledInstance(width / 4, height / 2, Image.SCALE_DEFAULT);

		Image userTemp = new ImageIcon("trainer-back.png").getImage();
		Image newUserTemp = userTemp.getScaledInstance(width / 4, height / 2, Image.SCALE_DEFAULT);

		JLabel userImage = new JLabel(new ImageIcon(newUserTemp));
		JLabel aiImage = new JLabel(new ImageIcon(newTemp));
		userImage.setBounds(width / 12, height / 6, width / 4, height / 2);
		aiImage.setBounds((int) (width / 1.28), -height / 27, width / 4, height / 2);
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
		userName.setBounds((int) (width / 3.5), (int) (-height / 4), width / 2, (int) (height / 1.1));
		this.add(userName);
		AIname.setFont(new Font("Arial", Font.BOLD, 80));
		AIname.setBounds((int) (width / 1.7), (int) (-height / 2.5), width / 2, (int) (height / 1.1));
		this.add(AIname);
		console.setBounds(width / 6, (int) (height - height / 4), width - width / 6, height / 12);
		console.setFont(new Font("Arial", Font.ITALIC, 30));
		this.add(console);
		AIconsole.setBounds(width / 6, (int) (height - height / 5), width - width / 6, height / 12);
		AIconsole.setFont(new Font("Arial", Font.ITALIC, 30));
		AIconsole.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		console.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		this.add(AIconsole);
		this.setVisible(true);
		this.remove(backgroundFinal);
		this.add(backgroundFinal);

	}

	public void onPaintEvent(Graphics g) {
		aiMon.update(g);
	}

	// adds the moves and resizes them to the Jframe
	public void addMoves() {
		add(aiMon);
		move1 = new JButton(user.getCurrent().getMove(0).getName());
		move1.setBounds(width / 5, (int) (height - height / 3), (int) (width / 6.3), height / 14);
		move1.setFont(new Font("Arial", Font.PLAIN, 25));
		move1.setBackground(Color.WHITE);
		move1.addActionListener(this);
		move2 = new JButton(user.getCurrent().getMove(1).getName());
		move2.setBounds(2 * (width / 5), (int) (height - height / 3), (int) (width / 6.3), height / 14);
		move2.setFont(new Font("Arial", Font.PLAIN, 25));
		move2.setBackground(Color.WHITE);
		move2.addActionListener(this);
		move3 = new JButton(user.getCurrent().getMove(2).getName());
		move3.setBounds(3 * (width / 5), (int) (height - height / 3), (int) (width / 6.3), height / 14);
		move3.setFont(new Font("Arial", Font.PLAIN, 25));
		move3.setBackground(Color.WHITE);
		move3.addActionListener(this);
		move4 = new JButton(user.getCurrent().getMove(3).getName());
		move4.setBounds(4 * (width / 5), (int) (height - height / 3), (int) (width / 6.3), height / 14);
		move4.setFont(new Font("Arial", Font.PLAIN, 25));
		move4.setBackground(Color.WHITE);
		move4.addActionListener(this);
		this.add(move1);
		this.add(move2);
		this.add(move3);
		this.add(move4);
		this.setVisible(true);
		ImageIcon background = new ImageIcon("city.jpg");
		Image backgroundTemp = background.getImage();
		Image backgroundscaled = backgroundTemp.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		backgroundFinal = new JLabel(new ImageIcon(backgroundscaled));
		backgroundFinal.setBounds(0, 0, width, height);
		this.remove(backgroundFinal);
		this.add(backgroundFinal);
	}

	// adds the switch buttons to the Jframe
	private void addSwitch() {
		pokemon1 = new JButton(user.getPokemon(0).getName());
		pokemon1.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon1.setBounds(width / 200, height / 36, width / 8, width / 16);
		pokemon1.addActionListener(this);
		this.add(pokemon1);
		pokemon2 = new JButton(user.getPokemon(1).getName());
		pokemon2.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon2.setBounds(width / 200, height / 36 + (width / 14), width / 8, width / 16);
		pokemon2.addActionListener(this);
		this.add(pokemon2);
		pokemon3 = new JButton(user.getPokemon(2).getName());
		pokemon3.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon3.setBounds(width / 200, height / 36 + 2 * (width / 14), width / 8, width / 16);
		pokemon3.addActionListener(this);
		this.add(pokemon3);
		pokemon4 = new JButton(user.getPokemon(3).getName());
		pokemon4.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon4.setBounds(width / 200, height / 36 + 3 * (width / 14), width / 8, width / 16);
		pokemon4.addActionListener(this);
		this.add(pokemon4);
		pokemon5 = new JButton(user.getPokemon(4).getName());
		pokemon5.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon5.setBounds(width / 200, height / 36 + 4 * (width / 14), width / 8, width / 16);
		pokemon5.addActionListener(this);
		this.add(pokemon5);
		pokemon6 = new JButton(user.getPokemon(5).getName());
		pokemon6.setFont(new Font("Arial", Font.BOLD, 20));
		pokemon6.setBounds(width / 200, height / 36 + 5 * (width / 14), width / 8, width / 16);
		pokemon6.addActionListener(this);
		this.add(pokemon6);
		this.setVisible(true);
		this.remove(backgroundFinal);
		this.add(backgroundFinal);
	}

	// makes the user unable to click a move thus forcing them to switch
	public void forceSwitch() {
		console.setText("Your Pokemon has fainted!");
		move1.setEnabled(false);
		move2.setEnabled(false);
		move3.setEnabled(false);
		move4.setEnabled(false);
		skipturn = true;
	}

	// sets the GUI to say you have lost and prevents you from moving
	public void displayLose() {
		AIconsole.setText("You have lost :(");
		forceSwitch();
	}

	// updates the GUI to the new pokemon the player switched to
	public boolean playerSwitch(int position) {
		String canSwitch = user.Switch(position);
		if (canSwitch == null)
			return true;
		console.setText(canSwitch);
		AIconsole.setText(" ");
		checkDead();
		userMon.setIcon(gifsIcon(true));
		switchBar(userHP, user.getCurrent());
		this.remove(backgroundFinal);
		this.add(backgroundFinal);
		return false;

	}

	public Move AIMove() {
		Move use = new Move();
		Move random = new Move();
		String damage = "";
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

		Move m = AIMove();
		if (e.getSource() == mute) {
			if (!isMuted) {
				Game.mute(false);
				isMuted = true;
				return;
			} else {
				Game.mute(true);
				isMuted = false;
				return;
			}
		} else if (e.getSource() == pokemon1 || e.getSource() == pokemon2 || e.getSource() == pokemon3
				|| e.getSource() == pokemon4 || e.getSource() == pokemon5 || e.getSource() == pokemon6) {
			if (e.getSource() == pokemon1) {
				if (playerSwitch(0))
					return;
			} else if (e.getSource() == pokemon2) {
				if (playerSwitch(1))
					return;
			} else if (e.getSource() == pokemon3) {
				if (playerSwitch(2))
					return;
			} else if (e.getSource() == pokemon4) {
				if (playerSwitch(3))
					return;
			} else if (e.getSource() == pokemon5) {
				if (playerSwitch(4))
					return;
			} else if (e.getSource() == pokemon6) {
				if (playerSwitch(5))
					return;
			}
			userMon.enable();
			if (ai.getCurrent().getHp() == 0) {
				disaBall();
				ballcount++;
				skipturn = true;
				if (ai.lost()) {
					ball6.disable();
					console.setText("GG, you win!");
					aiMon.disable();
				} else {
					ai.fainted();
					aiMon.setIcon(gifsIcon(false));
					this.AIname.setText(ai.getCurrent().getName());
					switchBar(aiHP, ai.getCurrent());
				}
			}
			if (skipturn == true) {
				skipturn = false;
				return;
			}
			String temp = ai.AITurn(user.getCurrent(), m);
			AIconsole.setText(temp);
			temp = temp.replaceAll("[^\\d]", "");
			if (user.getCurrent().getHp() == 0)
				userMon.setIcon(gifsIcon(true));
			this.setVisible(true);
			this.repaint();
			this.revalidate();
			if (user.getCurrent().getHp() == 0) {
				userMon.disable();
			}
			if (user.lost()) {

				this.forceSwitch();
				AIconsole.setText("");
				console.setText("you lost");
			}

			update(userHP, user.getCurrent());
			update(aiHP, ai.getCurrent());
			this.setVisible(true);
			this.repaint();
			this.revalidate();
			if (user.getCurrent().getHp() == 0) {
				userMon.disable();
			}
			if (user.lost()) {
				this.forceSwitch();
				AIconsole.setText("");
				console.setText("you lost");
			}
		} else if (user.getCurrent().getSpeed() > ai.getCurrent().getSpeed()) {
			if (e.getSource() == move1) {
				console.setText(Game.attack(user.getCurrent(), user.getCurrent().getMove(0), ai.getCurrent(),
						user.getCurrent().getMove(0).isSpecial()));
				AIconsole.setText(" ");
			} else if (e.getSource() == move2) {
				console.setText(Game.attack(user.getCurrent(), user.getCurrent().getMove(1), ai.getCurrent(),
						user.getCurrent().getMove(1).isSpecial()));
				AIconsole.setText(" ");
			} else if (e.getSource() == move3) {
				console.setText(Game.attack(user.getCurrent(), user.getCurrent().getMove(2), ai.getCurrent(),
						user.getCurrent().getMove(2).isSpecial()));
				AIconsole.setText(" ");
			} else if (e.getSource() == move4) {
				console.setText(Game.attack(user.getCurrent(), user.getCurrent().getMove(3), ai.getCurrent(),
						user.getCurrent().getMove(3).isSpecial()));

			}
			if (ai.getCurrent().getHp() == 0) {
				disaBall();
				ballcount++;
				skipturn = true;
				if (ai.lost()) {
					disaBall();
					repaint();
					console.setText("GG, you win!");
					aiMon.disable();
				} else {
					ai.fainted();
					aiMon.setIcon(gifsIcon(false));
					this.AIname.setText(ai.getCurrent().getName());
					switchBar(aiHP, ai.getCurrent());
				}
				update(userHP, user.getCurrent());
				update(aiHP, ai.getCurrent());
			}
			if (skipturn == true) {
				skipturn = false;
				return;
			}
			String temp = ai.AITurn(user.getCurrent(), m);
			AIconsole.setText(temp);
			temp = temp.replaceAll("[^\\d]", "");
			if (user.getCurrent().getHp() == 0)
				userMon.setIcon(gifsIcon(true));
			this.setVisible(true);
			this.repaint();
			this.revalidate();
			if (user.getCurrent().getHp() == 0) {
				userMon.disable();
			}
			if (user.lost()) {

				this.forceSwitch();
				AIconsole.setText("");
				console.setText("you lost");
			}

			update(userHP, user.getCurrent());
			update(aiHP, ai.getCurrent());
			this.setVisible(true);
			this.repaint();
			this.revalidate();
			if (user.getCurrent().getHp() == 0) {
				userMon.disable();
			}
			if (user.lost()) {
				this.forceSwitch();
				AIconsole.setText("");
				console.setText("you lost");
			}
		} else {
			if (skipturn) {
				skipturn = false;
				return;
			}
			if(ai.lost()) return;
			String temp = ai.AITurn(user.getCurrent(), m);
			AIconsole.setText(temp);
			temp = temp.replaceAll("[^\\d]", "");
			if (user.getCurrent().getHp() == 0) {
				userMon.setIcon(gifsIcon(true));
				this.setVisible(true);
				this.repaint();
				this.revalidate();
				if (user.getCurrent().getHp() == 0) {
					userMon.disable();
				}
				if (user.lost()) {
					this.forceSwitch();
					AIconsole.setText("");
					console.setText("you lost!");
				}
				update(userHP, user.getCurrent());
				update(aiHP, ai.getCurrent());
				return;
			}
			if (e.getSource() == move1) {
				console.setText(Game.attack(user.getCurrent(), user.getCurrent().getMove(0), ai.getCurrent(),
						user.getCurrent().getMove(0).isSpecial()));
				//AIconsole.setText(" ");
			} else if (e.getSource() == move2) {
				console.setText(Game.attack(user.getCurrent(), user.getCurrent().getMove(1), ai.getCurrent(),
						user.getCurrent().getMove(1).isSpecial()));
				//AIconsole.setText(" ");
			} else if (e.getSource() == move3) {
				console.setText(Game.attack(user.getCurrent(), user.getCurrent().getMove(2), ai.getCurrent(),
						user.getCurrent().getMove(2).isSpecial()));
				//AIconsole.setText(" ");
			} else if (e.getSource() == move4) {
				console.setText(Game.attack(user.getCurrent(), user.getCurrent().getMove(3), ai.getCurrent(),
						user.getCurrent().getMove(3).isSpecial()));

			}

			if (ai.getCurrent().getHp() == 0) {
				disaBall();
				ballcount++;

				if (ai.lost()) {
					//ball6.disable();
					console.setText("GG, you win!");
					aiMon.disable();

				} else {
					ai.fainted();
					aiMon.setIcon(gifsIcon(false));
					this.AIname.setText(ai.getCurrent().getName());
					switchBar(aiHP, ai.getCurrent());
				}
			}
		}
		this.setVisible(true);
		this.repaint();
		this.revalidate();
		if (user.getCurrent().getHp() == 0) {
			skipturn = true;
			userMon.disable();
		}
		if (user.lost()) {
			this.forceSwitch();
			AIconsole.setText("");
			console.setText("you lost!");
		}

		update(userHP, user.getCurrent());
		update(aiHP, ai.getCurrent());
	}

	// checks if the user pokemon have fainted, if they have it prevents the user
	// from being able to switch to them
	public void checkDead() {
		userName.setText(user.getCurrent().getName());
		this.spriteInit(true);
		move1.setText(user.getCurrent().getMove(0).getName());
		move2.setText(user.getCurrent().getMove(1).getName());
		move3.setText(user.getCurrent().getMove(2).getName());
		move4.setText(user.getCurrent().getMove(3).getName());
		if (user.getPokemon(0).getHp() == 0)
			pokemon1.setEnabled(false);
		if (user.getPokemon(1).getHp() == 0)
			pokemon2.setEnabled(false);
		if (user.getPokemon(2).getHp() == 0)
			pokemon3.setEnabled(false);
		if (user.getPokemon(3).getHp() == 0)
			pokemon4.setEnabled(false);
		if (user.getPokemon(4).getHp() == 0)
			pokemon5.setEnabled(false);
		if (user.getPokemon(5).getHp() == 0)
			pokemon6.setEnabled(false);
		revalidate();
		move1.setEnabled(true);
		move2.setEnabled(true);
		move3.setEnabled(true);
		move4.setEnabled(true);
		this.remove(backgroundFinal);
		this.add(backgroundFinal);
		this.setVisible(true);
	}

	public String spriteInit(boolean isUser) {
		if (isUser) {
			Pokemon current = user.getCurrent();
			String spriteIndex = "" + current.getDex();

			while (spriteIndex.length() < 3) {
				spriteIndex = "0" + spriteIndex;
			}
			if (new File("spritesback/" + "a-b_bw_" + spriteIndex + ".gif").exists())
				return "spritesback/" + "a-b_bw_" + spriteIndex + ".gif";
			return "spritesback/" + "a-b_bw_" + spriteIndex + "_f.gif";
		} else {
			Pokemon current = ai.getCurrent();
			String spriteIndex = "" + current.getDex();
			while (spriteIndex.length() < 3) {
				spriteIndex = "0" + spriteIndex;
			}
			if (new File("sprites/" + "ani_bw_" + spriteIndex + ".gif").exists())
				return "sprites/" + "ani_bw_" + spriteIndex + ".gif";
			return "sprites/" + "ani_bw_" + spriteIndex + "_f.gif";
		}
	}

	public void muteButton() {
		isMuted = false;
		mute = new JButton("Mute");
		mute.setLayout(null);
		mute.setFont(new Font("Arial", Font.PLAIN, 20));
		// mute.setBounds(width / 200, height - height / 36, width / 16, width / 32);
		mute.setBounds(width / 50, height - (height / 7), width / 16, width / 32);
		mute.addActionListener(this);
		add(mute);
		this.setVisible(true);
	}

	public void userBar() {
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

	public void aiBar() {
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

	public void update(JProgressBar hp, Pokemon current)  {
		hp.setValue(current.getHp());
		
		if ((double) current.getHp() / current.getMaxHp() < 0.2) {
			hp.setForeground(Color.red);
		} else if ((double) current.getHp() / current.getMaxHp() < 0.5) {
			hp.setForeground(Color.yellow);
		} else {
			hp.setForeground(Color.green);
		}
	}

	public void addBalls() {
		ImageIcon imageIcon = new ImageIcon("pokeball.png");
		Image image = imageIcon.getImage();
		ImageIcon ballIcon = new ImageIcon(image.getScaledInstance(width / 17, height / 15, Image.SCALE_DEFAULT));
		ball1 = new JLabel(ballIcon);
		ball2 = new JLabel(ballIcon);
		ball3 = new JLabel(ballIcon);
		ball4 = new JLabel(ballIcon);
		ball5 = new JLabel(ballIcon);
		ball6 = new JLabel(ballIcon);
		ball1.setBounds((int) (width / 1.06), 0, width / 16, height / 15);
		ball2.setBounds((int) (width / 1.06), width / 30, width / 16, height / 15);
		ball3.setBounds((int) (width / 1.06), width / 30 * 2, width / 16, height / 15);
		ball4.setBounds((int) (width / 1.06), width / 30 * 3, width / 16, height / 15);
		ball5.setBounds((int) (width / 1.06), width / 30 * 4, width / 16, height / 15);
		ball6.setBounds((int) (width / 1.06), width / 30 * 5, width / 16, height / 15);
		add(ball1);
		add(ball2);
		add(ball3);
		add(ball4);
		add(ball5);
		add(ball6);
	}

	public void disaBall() {
		if (ballcount == 1)
			ball1.disable();
		else if (ballcount == 2)
			ball2.disable();
		else if (ballcount == 3)
			ball3.disable();
		else if (ballcount == 4)
			ball4.disable();
		else if (ballcount == 5)
			ball5.disable();
		else if (ballcount == 6)
			ball6.disable();
	}
}