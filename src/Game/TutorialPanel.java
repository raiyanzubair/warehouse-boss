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
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

public class TutorialPanel extends JPanel {
	
	private final String html1 = "<html><body style = 'width: ";
	private final String html2 = "px'>";

	public TutorialPanel(Game g) {
		
		JPanel container = new JPanel();
		JScrollPane scrollPane = new JScrollPane(container);
		
		this.setBackground(ImageFactory.Colors.customOrange);
		this.setLayout(new GridBagLayout());
		
		JPanel topWall = new JPanel(new BorderLayout());
		JLabel topWallLabel = new JLabel(ImageFactory.topWall, JLabel.CENTER);
		topWall.setBackground(ImageFactory.Colors.customOrange);
		topWall.add(topWallLabel);
		addGridComponent(topWall, 0, 0);
		
		JLabel title = new JLabel("TUTORIAL");
		title.setFont(new Font("Tahoma", Font.BOLD, 32));
		title.setForeground(Color.WHITE);
		addGridComponent(title, 0, 2);
		
		String rulesString = "Move the character to push the wooden boxes onto the green squares. " +
				"Use the arrow keys or the on-screen buttons to push the character around.";
		JLabel rules = addText(rulesString);
		addGridComponent(rules, 0, 3);
				
		JPanel gamePlay = new JPanel(new BorderLayout());
		JLabel gamePlayLabel = new JLabel(ImageFactory.tutorialGif, JLabel.CENTER);
		gamePlayLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		gamePlay.setBackground(ImageFactory.Colors.customOrange);
		gamePlay.add(gamePlayLabel);
		addGridComponent(gamePlay, 0, 4);
		
		String undoRules = "If you make a mistake and want to undo a move, simply press the on-screen undo "
				+ " button or hit the 'U' key on your keyboard";
		JLabel undo = addText(undoRules);
		addGridComponent(undo, 0, 5);
		
		JPanel undoEx = new JPanel(new BorderLayout());
		JLabel undoExample = new JLabel(ImageFactory.undoGif, JLabel.CENTER);
		undoExample.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		undoEx.setBackground(ImageFactory.Colors.customOrange);
		undoEx.add(undoExample);
		addGridComponent(undoEx, 0, 6);
		
		String resetRules = "If you want to restart the game, press the on-screen reset button or hit the"
				+ " 'R' key on your keyboard";
		JLabel reset = addText(resetRules);
		addGridComponent(reset, 0, 7);
		
		JPanel resetEx = new JPanel(new BorderLayout());
		JLabel resetExample = new JLabel(ImageFactory.resetGif, JLabel.CENTER);
		resetExample.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		resetEx.setBackground(ImageFactory.Colors.customOrange);
		resetEx.add(resetExample);
		addGridComponent(resetEx, 0, 8);
		
		JButton returnButton = new JButton("Main Menu");
		returnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				g.showMenuScreen();
			}
		});
		addGridComponent(returnButton, 0, 9);
		
		JButton multiplayerRules = new JButton("Multiplayer Rules");
		multiplayerRules.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				g.showMenuScreen();
				//Implement function to show tutorial for multiplayer
			}
		});
		addGridComponent(multiplayerRules, 1, 9);
		
		JPanel bottomWall = new JPanel(new BorderLayout());
		JLabel bottomWallLabel = new JLabel("", ImageFactory.bottomWall, JLabel.CENTER);
		bottomWall.setBackground(ImageFactory.Colors.customOrange);
		bottomWall.add(bottomWallLabel);
		bottomWall.setVisible(true);
		addGridComponent(bottomWall, 0, 10);
	}
	
	private JLabel addText (String string) 
	{
		JLabel newLabel = new JLabel(html1 + "275" + html2 + string);
		newLabel.setForeground(Color.DARK_GRAY);
		
		return newLabel;
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
