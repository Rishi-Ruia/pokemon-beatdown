import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class GUI extends JFrame implements ActionListener{
	JButton move1;
	JButton move2;
	JButton move3;
	JButton move4;
	JButton pokemon1;
	JButton pokemon2;
	JButton pokemon3;
	JButton pokemon4;
	JButton pokemon5;
	JButton pokemon6;
	public GUI(){
		this.setLayout(null);
		addbuttons();
		this.setSize(1140, 940); //sets dimensions of frame
		this.setVisible(true); //makes frame visible 
		this.setTitle("battle window"); //label frame 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes frame when you press the x button
		
		pokemon1 = new JButton("Switch");
		pokemon1.setBounds(20, 10, 80, 80);
		
		
		this.add(pokemon1);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		
	}
	private void addbuttons() {
		move1 = new JButton("move1");
		move1.setBounds(0, 250, 150, 150);
		move1.setFont(new Font("Arial", Font.PLAIN, 20));
		move1.setBackground(Color.WHITE);
		move1.addActionListener(this);
		move2 = new JButton("move2");
		move2.setBounds(150, 150, 150, 150);
		move2.setFont(new Font("Arial", Font.PLAIN, 20));
		move2.setBackground(Color.WHITE);
		move2.addActionListener(this);
		move3 = new JButton("move3");
		move3.setBounds(0, 420, 150, 150);
		move3.setFont(new Font("Arial", Font.PLAIN, 20));
		move3.setBackground(Color.WHITE);
		move3.addActionListener(this);
		move4 = new JButton("move4");
		move4.setBounds(150, 400, 150, 150);
		move4.setFont(new Font("Arial", Font.PLAIN, 20));
		move4.setBackground(Color.WHITE);
		move4.addActionListener(this);
		this.add(move1);
		this.add(move2);
		this.add(move3);
		this.add(move4);
	}
	public  void forceSwitch() {
		
	}
	public void lost() {
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==move1) {
			
		}
		if(e.getSource() == pokemon1) {
			this.setVisible(false);
			this.dispose();
		}
			
	}
}
