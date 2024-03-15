import java.awt.Color;
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
		//frame.setLayout(new GridLayout());
		attack = new JButton("attack");
		
		Switch = new JButton("Switch");
		
		this.add(attack);
		attack.setBounds(100, 100, 80, 80);
		this.add(Switch);
		Switch.setBounds(20, 0, 80, 80);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		attack.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==attack) {
			System.out.println("yo");
		}
		if(e.getSource() == Switch) {
			this.setVisible(false);
			this.dispose();
		}
			
	}
}
