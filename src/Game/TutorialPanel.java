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

public class TutorialPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private final String html1 = "<html><body style = 'width: ";
	private final String html2 = "px'>";
	private JPanel blank; 

	public TutorialPanel(Game g) {
		
		this.setBackground(ImageFactory.Colors.customOrange);
		this.setLayout(new GridBagLayout());
		
		JPanel topWall = new JPanel(new BorderLayout());
		JLabel topWallLabel = new JLabel(ImageFactory.topWall, JLabel.CENTER);
		topWall.setBackground(ImageFactory.Colors.customOrange);
		topWall.add(topWallLabel);
		addGridComponent(topWall, 0, 0);
		
		blank = blankPanel();
		addGridComponent(blank, 0, 1);
		
		JLabel title = new JLabel("TUTORIAL");
		title.setFont(new Font("Tahoma", Font.BOLD, 32));
		title.setForeground(Color.WHITE);
		addGridComponent(title, 0, 2);
		
		blank = blankPanel();
		addGridComponent(blank, 0, 3);
		
		String rulesString = "AIM : Move the character to push the wooden boxes onto the green crosses. " +
				"Use the arrow keys or the on-screen buttons to push the character around.";
		JLabel rules = addText(rulesString);
		addGridComponent(rules, 0, 4);
		
		blank = blankPanel();
		addGridComponent(blank, 0, 5);
				
		JPanel gamePlay = new JPanel(new BorderLayout());
		JLabel gamePlayLabel = new JLabel(ImageFactory.tutorialGif, JLabel.CENTER);
		gamePlayLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		gamePlay.setBackground(ImageFactory.Colors.customOrange);
		gamePlay.add(gamePlayLabel);
		addGridComponent(gamePlay, 0, 6);
		
		blank = blankPanel();
		addGridComponent(blank, 0, 7);
		
		String undoRules = "If you make a mistake and want to undo a move, simply press the on-screen undo "
				+ " button or hit the 'U' key on your keyboard: ";
		JLabel undo = addText(undoRules);
		addGridComponent(undo, 0, 8);
		
		blank = blankPanel();
		addGridComponent(blank, 0, 9);
		
		JPanel undoEx = new JPanel(new BorderLayout());
		JLabel undoExample = new JLabel(ImageFactory.undoGif, JLabel.CENTER);
		undoExample.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		undoEx.setBackground(ImageFactory.Colors.customOrange);
		undoEx.add(undoExample);
		addGridComponent(undoEx, 0, 10);
		
		blank = blankPanel();
		addGridComponent(blank, 0, 11);
		
		String resetRules = "If you want to restart the game, press the on-screen reset button or hit the"
				+ " 'R' key on your keyboard: ";
		JLabel reset = addText(resetRules);
		addGridComponent(reset, 0, 12);
		
		blank = blankPanel();
		addGridComponent(blank, 0, 13);
		
		JPanel resetEx = new JPanel(new BorderLayout());
		JLabel resetExample = new JLabel(ImageFactory.resetGif, JLabel.CENTER);
		resetExample.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		resetEx.setBackground(ImageFactory.Colors.customOrange);
		resetEx.add(resetExample);
		addGridComponent(resetEx, 0, 14);
		
		blank = blankPanel();
		addGridComponent(blank, 0, 15);
		
		String shadowRules1 = "Want a challenge? Use shadow mode to limit your on-screen visibility and try to move the boxes to their goal state. " +
				"To activate shadow mode, go to either Single Player or Multiplayer and check the 'Shadow Mode' checkbox at the bottom.";
		JLabel shadow1 = addText(shadowRules1);
		addGridComponent(shadow1, 0, 16);
		
		blank = blankPanel();
		addGridComponent(blank, 0, 17);
		
		JPanel shadowEx1 = new JPanel(new BorderLayout());
		JLabel shadowExample1 = new JLabel(ImageFactory.shadowGif1, JLabel.CENTER);
		shadowExample1.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		shadowEx1.setBackground(ImageFactory.Colors.customOrange);
		shadowEx1.add(shadowExample1);
		addGridComponent(shadowEx1, 0, 18);
		
		blank = blankPanel();
		addGridComponent(blank, 0, 19);
		
		String shadowRules2 = "Game play will look like this: ";
		JLabel shadow2 = addText(shadowRules2);
		addGridComponent(shadow2, 0, 20);
		
		blank = blankPanel();
		addGridComponent(blank, 0, 21);
		
		JPanel shadowEx2 = new JPanel(new BorderLayout());
		JLabel shadowExample2 = new JLabel(ImageFactory.shadowGif2, JLabel.CENTER);
		shadowExample2.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		shadowEx2.setBackground(ImageFactory.Colors.customOrange);
		shadowEx2.add(shadowExample2);
		addGridComponent(shadowEx2, 0, 22);
		
		blank = blankPanel();
		addGridComponent(blank, 0, 23);
		
		JButton returnButton = new JButton("Main Menu");
		returnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				g.showMenuScreen();
			}
		});
		addGridComponent(returnButton, 0, 24);
		
		JButton multiplayerRules = new JButton("Multiplayer Rules");
		multiplayerRules.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				g.showMenuScreen();
				//Implement function to show tutorial for multiplayer
			}
		});
		addGridComponent(multiplayerRules, 1, 13);
		
		blank = blankPanel();
		addGridComponent(blank, 0, 25);
		
		JPanel bottomWall = new JPanel(new BorderLayout());
		JLabel bottomWallLabel = new JLabel("", ImageFactory.bottomWall, JLabel.CENTER);
		bottomWall.setBackground(ImageFactory.Colors.customOrange);
		bottomWall.add(bottomWallLabel);
		bottomWall.setVisible(true);
		addGridComponent(bottomWall, 0, 26);
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
