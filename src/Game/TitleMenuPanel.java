package Game;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * JPanel class that displays the main title menu and allows traversal to other screens 
 * @field serialVersionID: needed to prevent bugs when extending JPanels
 * @field numComponents: integer representing the number of components in the panel
 * 
 * @field levelButton: JButton that takes you to the level select screen for single player
 * @field multiPlayerButton: JButton that takes you to the level select screen for multiplayer
 * @field tutorialButton: JButton that takes you to the tutorial
 * @field quitButton: JButton that exits the game *
 */
public class TitleMenuPanel extends JPanel 
{	
	private static final long serialVersionUID = 1L;
	private int numComponents;
	
	JButton levelButton;
	JButton multiplayerButton;
	JButton tutorialButton;
	JButton quitButton;
	
	public TitleMenuPanel(Game g, PuzzleGridGenerator psg)
	{
		this.setBackground(ImageFactory.Colors.customOrange);
		this.setLayout(new GridBagLayout());
		populateComponents(g, psg);
	}
	
	private void populateComponents(Game g, PuzzleGridGenerator psg)
	{
		numComponents = 0;
		
		levelButton = new JButton("SINGLE PLAYER");
		levelButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		levelButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				g.showLevelSelect(false);
			}
		});
		
		multiplayerButton = new JButton("MULTIPLAYER");
		multiplayerButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		multiplayerButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed (ActionEvent e) 
			{
				g.showLevelSelect(true);
			}
		});
		
		tutorialButton = new JButton("HOW TO PLAY");
		tutorialButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				g.showTutorialScreen();
			}
		});
		
		quitButton = new JButton("QUIT");
		quitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});

		addGridComponent(blankPanel(), 0, numComponents++);
		addGridComponent(levelButton, 0, numComponents++);
		addGridComponent(multiplayerButton, 0, numComponents++);
		addGridComponent(tutorialButton, 0, numComponents++);
		addGridComponent(quitButton, 0, numComponents++);
		addGridComponent(blankPanel(), 0, numComponents++);
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
