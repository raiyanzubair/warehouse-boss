package Game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class TitleMenuPanel extends JPanel 
{	
	private static final long serialVersionUID = 1L;
	private int numComponents;
	
	JButton levelButton;
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
		
		tutorialButton = new JButton("HOW TO PLAY");
		tutorialButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				g.showTutorialScreen(g);
			}
		});
		addGridComponent(tutorialButton, 0, numComponents++);
		
		levelButton = new JButton("LEVEL SELECT");
		levelButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				g.showLevelSelect(g, psg);
			}
		});
		addGridComponent(levelButton, 0, numComponents++);
		
		levelButton = new JButton("MULTIPLAYER");
		levelButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) 
			{
				g.showGameScreen(psg.getDefaultMultiplayerLevel());
			}
		});
		addGridComponent(levelButton, 0, numComponents++);
		
		quitButton = new JButton("QUIT");
		quitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		addGridComponent(quitButton, 0, numComponents++);
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
