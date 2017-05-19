package Game;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuPanel extends JPanel 
{	
	public MenuPanel(Game g, JFrame menuFrame, PuzzleGridGenerator psg)
	{
		Color customOrange = new Color(255, 165, 96);
		this.setBackground(customOrange);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JButton startGame = new JButton("START GAME");
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(startGame, gbc);
		startGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				menuFrame.setVisible(false);
				g.showGameScreen(psg.generatePuzzleGrid());
			}
		});
		
		//JButton howToPlay = new JButton("HOW TO PLAY");

		JButton levelOne = new JButton("LEVEL ONE");
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(levelOne, gbc);
		levelOne.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				menuFrame.setVisible(false);
				g.showGameScreen(psg.getDefaultMap());
			}
		});

		JButton levelTwo = new JButton("LEVEL TWO");
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(levelTwo, gbc);
		levelTwo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				menuFrame.setVisible(false);
				g.showGameScreen(psg.getLevelTwo());
			}
		});

		JButton levelThree = new JButton("LEVEL THREE");
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(levelThree, gbc);
		levelThree.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				menuFrame.setVisible(false);
				g.showGameScreen(psg.getLevelThree());
			}
		});
	}
}
