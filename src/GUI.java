import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class GUI extends JFrame implements ActionListener{
	JButton attack;
	JButton Switch;
	GUI(){
		this.setSize(820, 620); //sets dimensions of frame
		this.setVisible(true); //makes frame visible 
		this.setTitle("battle window"); //label frame 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes frame when you press the x button
		//h
		attack = new JButton("attack");
		attack.setBounds(0, 300, 350, 300);
		attack.setFont(new Font("Arial", Font.PLAIN, 50));
		attack.setBackground(Color.WHITE);
		Switch = new JButton("Switch");
		Switch.setBounds(20, 10, 80, 80);
		this.add(attack);
		this.setLayout(null);
		this.add(Switch);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		attack.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==attack) {
			
		}
		if(e.getSource() == Switch) {
			this.setVisible(false);
			this.dispose();
		}
			
	}
}
