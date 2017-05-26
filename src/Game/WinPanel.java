package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WinPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private int components;

	public WinPanel(Game g, int levelNumber, int numLevels, PuzzleGrid nextLevel, boolean isMultiplayer) 
	{	
		this.setBackground(ImageFactory.Colors.customOrange);
		this.setLayout(new GridBagLayout());
		
		populateComponents(g, levelNumber, numLevels, nextLevel, isMultiplayer);
	}
	
	private void populateComponents(Game g, int levelNumber, int numLevels, PuzzleGrid grid, boolean multiplayer)
	{
		JPanel topWall = new JPanel(new BorderLayout());
		JLabel topWallLabel = new JLabel(ImageFactory.topWall, JLabel.CENTER);
		topWall.setBackground(ImageFactory.Colors.customOrange);
		topWall.add(topWallLabel);

		JLabel title = new JLabel("CONGRATULATIONS");
		title.setFont(new Font("Tahoma", Font.BOLD, 32));
		title.setForeground(Color.WHITE);
		
		JLabel message = new JLabel("You passed level " + levelNumber);
		title.setFont(new Font("Tahoma", Font.ITALIC, 16));
		title.setForeground(Color.WHITE);
		
		JButton nextLevelButton = new JButton("Next Level");
		nextLevelButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				g.showGameScreen(grid);
			}
		});
		
		JButton levelSelectButton = new JButton("Return to Level Select");
		levelSelectButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				g.showLevelSelect(multiplayer);
			}
		});
		
		JButton menuButton = new JButton("Return to Main Menu");
		menuButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				g.showMenuScreen();
			}
		});
		
		JCheckBox checkBox = new JCheckBox("Shadow Mode");
		checkBox.setSelected(PuzzleGridGenerator.shadowMode);
		checkBox.addItemListener(new ItemListener() 
		{
		    public void itemStateChanged(ItemEvent e) 
		    {
	            PuzzleGridGenerator.shadowMode = checkBox.isSelected();
		    }
		});
		checkBox.setFocusable(false);
			
		JPanel bottomWall = new JPanel(new BorderLayout());
		JLabel bottomWallLabel = new JLabel("", ImageFactory.bottomWall, JLabel.CENTER);
		bottomWall.setBackground(ImageFactory.Colors.customOrange);
		bottomWall.add(bottomWallLabel);
		bottomWall.setVisible(true);

		addGridComponent(topWall, 0, components++);
		addGridComponent(blankPanel(), 0, components++);
		addGridComponent(title, 0, components++);
		addGridComponent(message, 0, components++);
		addGridComponent(blankPanel(), 0, components++);
		if (levelNumber < numLevels)
		{
			addGridComponent(nextLevelButton, 0, components++);
		}
		addGridComponent(levelSelectButton, 0, components++);
		addGridComponent(menuButton, 0, components++);
		addGridComponent(checkBox, 0, components++);
		addGridComponent(blankPanel(), 0, components++);
		addGridComponent(bottomWall, 0, components++);
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
