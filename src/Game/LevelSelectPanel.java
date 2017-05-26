package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LevelSelectPanel extends JPanel 
{	
	private static final long serialVersionUID = 1L;
	
	public LevelSelectPanel (Game g, PuzzleGridGenerator psg, boolean multiPlayer)
	{
		this.setBackground(ImageFactory.Colors.customOrange);
		this.setLayout(new GridBagLayout());
		
		String titleText = (multiPlayer) ? "MULTIPLAYER":"SINGLE PLAYER";
		JLabel title = new JLabel(titleText);
		title.setFont(new Font("Tahoma", Font.BOLD, 22));
		title.setForeground(Color.BLACK);
		addGridComponent(title, 0, 0);
		
		populateComponents(g, psg, multiPlayer);
	}
	
	private void populateComponents(Game g, PuzzleGridGenerator psg, boolean multiPlayer)
	{
		int numLevels = (multiPlayer) ? psg.getNumberOfMultiPlayerLevels():psg.getNumberOfSinglePlayerLevels();
		int components;
		for(components = 1; components < numLevels+1; components++)
		{
			String levelString = numberToWord(components);
			JButton newLevel = new JButton("LEVEL " + levelString);
			PuzzleGrid level = (multiPlayer) ? psg.getMultiLevel(components-1):psg.getLevel(components-1);
			registerLevelClickToLoadPuzzle(g, newLevel, level);
			addGridComponent(newLevel, 0, components);
		}
		
		JButton returnButton = new JButton("RETURN");
		returnButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		returnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				g.showMenuScreen();
			}
		});
		addGridComponent(returnButton, 0, components++);
		
		
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
		addGridComponent(checkBox, 0, components++);
	}
	
	private void registerLevelClickToLoadPuzzle(Game g, JButton button, PuzzleGrid level)
	{
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				g.showGameScreen(level);
			}
		});
	}
	
	private void addGridComponent(JComponent component, int gridX, int gridY)
	{
		component.setFocusable(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gridX;
		gbc.gridy = gridY;
		gbc.insets = new Insets (0,2,2,2);
		this.add(component, gbc);
	}
	
	private String numberToWord(int num) 
	{
        String ones[] = {" ", " ONE", " TWO", " THREE", " FOUR", " FIVE", " SIX", " SEVEN", " EIGHT", " NINE", " TEN", " ELEVEN", " TWELVE", " THIRTEEN", " FOURTEEN", " FIFTEEN", " SIXTEEN", " SEVENTEEN", " EIGHTEEN", " NINETEEN"};
        String tens[] = {" ", " ", " TWENTY", " THIRTY", " FOURTY", " FIFTY", " SIXTY", " SEVENTY", " EIGHTY", " NINETY"};
        return (num < 20) ? ones[num] : tens[num / 10] + " " + ones[num % 10];
    }
}
