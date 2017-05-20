package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WinScreen extends JFrame {
	
	public WinScreen (Game g, JFrame gameFrame) {
		this.setTitle("Level Complete");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		JPanel title = new JPanel();
		JButton menuButton = new JButton("Return to Main Menu");
		title.add(menuButton);
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameFrame.setVisible(false);
				g.showMenuScreen();
			}
		});
		
		this.add(title);
		this.pack();
	}
}
