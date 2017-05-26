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

public class TutorialPanel extends JPanel 
{	
	private static final long serialVersionUID = 1L;
	private static final int numPages = 5;
	private final String html1 = "<html><body style = 'width: ";
	private final String html2 = "px'>";
	private int components;

	public TutorialPanel(Game g) 
	{	
		this.setBackground(ImageFactory.Colors.customOrange);
		this.setLayout(new GridBagLayout());
		
		populateComponents(g,1);
	}
	
	private void populateComponents(Game g, int page)
	{
		JPanel topWall = new JPanel(new BorderLayout());
		JLabel topWallLabel = new JLabel(ImageFactory.topWall, JLabel.CENTER);
		topWall.setBackground(ImageFactory.Colors.customOrange);
		topWall.add(topWallLabel);
		
		JButton backButton = new JButton("Back");
		int pageTo = page > 1 ? page - 1 : 1;
		registerTutorialNavigationButton(g, backButton, pageTo);
	
		JButton nextButton = new JButton("Next");
		pageTo = page < numPages ? page + 1 : numPages;
		registerTutorialNavigationButton(g, nextButton, pageTo);
		
		JButton returnButton = new JButton("Return");
		returnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				g.showMenuScreen();
			}
		});
			
		JPanel bottomWall = new JPanel(new BorderLayout());
		JLabel bottomWallLabel = new JLabel("", ImageFactory.bottomWall, JLabel.CENTER);
		bottomWall.setBackground(ImageFactory.Colors.customOrange);
		bottomWall.add(bottomWallLabel);
		bottomWall.setVisible(true);

		addGridComponent(topWall, 0, components++);
		addGridComponent(blankPanel(), 0, components++);
		
		loadPage(g, page);

		addGridComponent(blankPanel(), 0, components++);
		if(page > 1)
		{
			addGridComponent(backButton, 0, components++);
		}
		if(page > 0 && page < numPages)
		{
			addGridComponent(nextButton, 0, components++);
		}
		addGridComponent(returnButton, 0, components++);
		addGridComponent(bottomWall, 0, components++);
	}
	
	private void loadPage(Game g, int page)
	{	
		if(page == 1)
		{	
			JButton multiplayerButton = new JButton("Multiplayer Rules");
			registerTutorialNavigationButton(g, multiplayerButton, 0);
			
			JLabel title = new JLabel("TUTORIAL");
			title.setFont(new Font("Tahoma", Font.BOLD, 32));
			title.setForeground(Color.WHITE);

			String rulesString = "AIM : Move the character to push the wooden boxes onto the green crosses. " +
					"Use the arrow keys or the on-screen buttons to push the character around.";
			JLabel rules = addText(rulesString);
			
			JPanel gamePlay = new JPanel(new BorderLayout());
			JLabel gamePlayLabel = new JLabel(ImageFactory.tutorialGif, JLabel.CENTER);
			gamePlayLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			gamePlay.setBackground(ImageFactory.Colors.customOrange);
			gamePlay.add(gamePlayLabel);

			addGridComponent(multiplayerButton, 0, components++);
			addGridComponent(title, 0, components++);
			addGridComponent(blankPanel(), 0, components++);
			addGridComponent(rules, 0, components++);
			addGridComponent(blankPanel(), 0, components++);
			addGridComponent(gamePlay, 0, components++);
		}
		else if(page == 2)
		{
			
			String undoRules = "If you make a mistake and want to undo a move, simply press the on-screen undo "
					+ " button or hit the 'U' key on your keyboard: ";
			JLabel undo = addText(undoRules);
					
			JPanel undoEx = new JPanel(new BorderLayout());
			JLabel undoExample = new JLabel(ImageFactory.undoGif, JLabel.CENTER);
			undoExample.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			undoEx.setBackground(ImageFactory.Colors.customOrange);
			undoEx.add(undoExample);

			addGridComponent(undo, 0, components++);
			addGridComponent(blankPanel(), 0, components++);
			addGridComponent(undoEx, 0, components++);
		}
		else if(page == 3)
		{
			
			String resetRules = "If you want to restart the game, press the on-screen reset button or hit the"
					+ " 'R' key on your keyboard: ";
			JLabel reset = addText(resetRules);
							
			JPanel resetEx = new JPanel(new BorderLayout());
			JLabel resetExample = new JLabel(ImageFactory.resetGif, JLabel.CENTER);
			resetExample.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			resetEx.setBackground(ImageFactory.Colors.customOrange);
			resetEx.add(resetExample);

			addGridComponent(reset, 0, components++);
			addGridComponent(blankPanel(), 0, components++);
			addGridComponent(resetEx, 0, components++);
		}
		else if(page == 4)
		{
			
			String shadowRules1 = "Want a challenge? Use shadow mode to limit your on-screen visibility and try to move the boxes to their goal state. " +
					"To activate shadow mode, go to either Single Player or Multiplayer and check the 'Shadow Mode' checkbox at the bottom.";
			JLabel shadow1 = addText(shadowRules1);
			
			JPanel shadowEx1 = new JPanel(new BorderLayout());
			JLabel shadowExample1 = new JLabel(ImageFactory.shadowGif1, JLabel.CENTER);
			shadowExample1.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			shadowEx1.setBackground(ImageFactory.Colors.customOrange);
			shadowEx1.add(shadowExample1);

			addGridComponent(shadow1, 0, components++);
			addGridComponent(blankPanel(), 0, components++);
			addGridComponent(shadowEx1, 0, components++);
		}
		else if(page == 5)
		{	
			String shadowRules2 = "Game play will look like this: ";
			JLabel shadow2 = addText(shadowRules2);
			
			JPanel shadowEx2 = new JPanel(new BorderLayout());
			JLabel shadowExample2 = new JLabel(ImageFactory.shadowGif2, JLabel.CENTER);
			shadowExample2.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			shadowEx2.setBackground(ImageFactory.Colors.customOrange);
			shadowEx2.add(shadowExample2);

			addGridComponent(shadow2, 0, components++);
			addGridComponent(blankPanel(), 0, components++);
			addGridComponent(shadowEx2, 0, components++);
		}
		else if(page == 0)
		{
			JButton singlePlayerRules = new JButton("Single Player Rules");
			registerTutorialNavigationButton(g, singlePlayerRules, 1);
			
			JLabel title = new JLabel("MULTIPLAYER TUTORIAL");
			title.setFont(new Font("Tahoma", Font.BOLD, 32));
			title.setForeground(Color.WHITE);
			
			String rulesString = "AIM : Work with a teammate to push the green and red boxes onto their respective coloured goals. " +
					"The green player will use the arrow keys and the red player will use WASD controls to control their characters.";
			JLabel rules = addText(rulesString);
					
			JPanel gamePlay = new JPanel(new BorderLayout());
			JLabel gamePlayLabel = new JLabel(ImageFactory.multiplayerGif, JLabel.CENTER);
			gamePlayLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			gamePlay.setBackground(ImageFactory.Colors.customOrange);
			gamePlay.add(gamePlayLabel);
			
			addGridComponent(singlePlayerRules, 0, components++);
			addGridComponent(title, 0, components++);
			addGridComponent(blankPanel(), 0, components++);
			addGridComponent(rules, 0, components++);
			addGridComponent(blankPanel(), 0, components++);
			addGridComponent(gamePlay, 0, components++);
		}
	}
	
	public void registerTutorialNavigationButton(Game g, JButton b, int pageTo)
	{
		b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				removeAll();
				populateComponents(g, pageTo);
				updateUI();
				//g.packTutorial();
			}
		});
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


