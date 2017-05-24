package Game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LevelSelectPanel extends JPanel 
{	
	private static final long serialVersionUID = 1L;
	public boolean shadowMode;
	
	public LevelSelectPanel (Game g, PuzzleGridGenerator psg)
	{
		this.setBackground(ImageFactory.Colors.customOrange);
		this.setLayout(new GridBagLayout());
		
		populateComponents(g, psg);
		
	}
	
	private void populateComponents(Game g, PuzzleGridGenerator psg)
	{
		int i =0;
		for(i = 0; i < psg.getNumberOfSinglePlayerLevels(); i++)
		{
			String level = numberToWord(i+1);
			JButton newLevel = new JButton("LEVEL " + level);
			registerLevelClickToLoadPuzzle(g, newLevel, psg.getLevel(i), shadowMode);
			
			String highScoreValue;
			if (psg.getLevel(i).getHighScore() >= 0) {
				highScoreValue = "High Score: " + Integer.toString(psg.getLevel(i).getHighScore());
			} else {
				highScoreValue = "Level not complete";
			}
			JLabel highScore = new JLabel(highScoreValue);
			addGridComponent(newLevel, 0, i);
			addGridComponent(highScore, 1, i);
		}
		
		JButton returnButton = new JButton("RETURN");
		returnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				g.showMenuScreen();
			}
		});
		addGridComponent(returnButton, 0, i+1);
		
		JCheckBox checkBox = new JCheckBox(String.valueOf(shadowMode));
		checkBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (checkBox.isSelected()) {
		            shadowMode = true;
		        } else {
		        	shadowMode = false;
		        }
		    }
		});
		addGridComponent(checkBox, 1, i+1);
	}
	
	private void registerLevelClickToLoadPuzzle(Game g, JButton button, PuzzleGrid level, boolean shadowMode)
	{
		level.setShadow(shadowMode);
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
		this.add(component, gbc);
	}
	
	public static String numberToWord(int num) 
	{
        String ones[] = {" ", " ONE", " TWO", " THREE", " FOUR", " FIVE", " SIX", " SEVEN", " EIGHT", " NINE", " TEN", " ELEVEN", " TWELVE", " THIRTEEN", " FOURTEEN", " FIFTEEN", " SIXTEEN", " SEVENTEEN", " EIGHTEEN", " NINETEEN"};
        String tens[] = {" ", " ", " TWENTY", " THIRTY", " FOURTY", " FIFTY", " SIXTY", " SEVENTY", " EIGHTY", " NINETY"};
        return (num < 20) ? ones[num] : tens[num / 10] + " " + ones[num % 10];
    }
}
	
