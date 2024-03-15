import java.awt.Point;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.net.URL;
import java.util.ArrayList;
public class GUI {
	public static void main(String args[]) {
		startGame();
	}
	public static void startGame() {
		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setTitle("battle window");
		JButton button = new JButton("hello");
		JLabel label = new JLabel("hey");
		frame.add(button);
		frame.add(label);

	}
}
