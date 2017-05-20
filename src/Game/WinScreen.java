package Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class WinScreen extends JFrame
{

	public WinScreen(Game g)
	{
		//JOptionPane.showMessageDialog(this, "Finished", "Congratulation", JOptionPane.INFORMATION_MESSAGE);
		this.setTitle("Level Complete");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		JPanel title = new JPanel();
		title.setBackground(new Color(255, 165, 96));
		
		JButton menuButton = new JButton("Main Menu");
		title.add(menuButton);
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g.showMenuScreen();
				setVisible(false);
			}
		});
		
		JButton retryButton = new JButton("Retry Level");
		title.add(retryButton);
		retryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton nextButton = new JButton("Next Level");
		title.add(nextButton);
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		this.add(title);
		this.pack();
	}
}
