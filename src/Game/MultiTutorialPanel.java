package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class MultiTutorialPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private final String html1 = "<html><body style = 'width: ";
	private final String html2 = "px'>";
	private JPanel blank; 

	public MultiTutorialPanel(Game g) {
		
		this.setBackground(ImageFactory.Colors.customOrange);
		this.setLayout(new GridBagLayout());
		
		JPanel topWall = new JPanel(new BorderLayout());
		JLabel topWallLabel = new JLabel(ImageFactory.topWall, JLabel.CENTER);
		topWall.setBackground(ImageFactory.Colors.customOrange);
		topWall.add(topWallLabel);
		addGridComponent(topWall, 0, 0);
		
		blank = blankPanel();
		addGridComponent(blank, 0, 1);
		
		JButton singlePlayerRules = new JButton("Single Player Rules");
		singlePlayerRules.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				g.showTutorialScreen(g);
			}
		});
		addGridComponent(singlePlayerRules, 0, 2);
		
		JLabel title = new JLabel("MULTIPLAYER TUTORIAL");
		title.setFont(new Font("Tahoma", Font.BOLD, 32));
		title.setForeground(Color.WHITE);
		addGridComponent(title, 0, 3);
		
		blank = blankPanel();
		addGridComponent(blank, 0, 4);
		
		String rulesString = "AIM : Work with a teammate to push the green and red boxes onto their respective coloured goals. " +
				"The green player will use the arrow keys and the red player will use WASD controls to control their characters.";
		JLabel rules = addText(rulesString);
		addGridComponent(rules, 0, 5);
		
		blank = blankPanel();
		addGridComponent(blank, 0, 6);
				
		JPanel gamePlay = new JPanel(new BorderLayout());
		JLabel gamePlayLabel = new JLabel(ImageFactory.multiplayerGif, JLabel.CENTER);
		gamePlayLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		gamePlay.setBackground(ImageFactory.Colors.customOrange);
		gamePlay.add(gamePlayLabel);
		addGridComponent(gamePlay, 0, 7);
		
		blank = blankPanel();
		addGridComponent(blank, 0, 8);
		
		JButton returnButton = new JButton("Main Menu");
		returnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				g.showMenuScreen();
				
			}
		});
		addGridComponent(returnButton, 0, 9);
		
		blank = blankPanel();
		addGridComponent(blank, 0, 10);
		
		JPanel bottomWall = new JPanel(new BorderLayout());
		JLabel bottomWallLabel = new JLabel("", ImageFactory.bottomWall, JLabel.CENTER);
		bottomWall.setBackground(ImageFactory.Colors.customOrange);
		bottomWall.add(bottomWallLabel);
		bottomWall.setVisible(true);
		addGridComponent(bottomWall, 0, 11);
	}
	
	private JLabel addText (String string) 
	{
		JLabel newLabel = new JLabel(html1 + "275" + html2 + string);
		newLabel.setForeground(Color.DARK_GRAY);
		
		return newLabel;
	}
	
	private JPanel blankPanel()
	{
		JPanel panel = new JPanel();
		panel.setBackground(ImageFactory.Colors.customOrange);
		JLabel blank = new JLabel(" ");
		panel.add(blank);
		return panel;
	}
	
	private void addGridComponent(JComponent component, int gridX, int gridY)
	{
		component.setFocusable(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gridX;
		gbc.gridy = gridY;
		this.add(component, gbc);
	}
	
}
