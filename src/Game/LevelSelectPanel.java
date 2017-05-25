package Game;

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

public class LevelSelectPanel extends JPanel 
{	
	private static final long serialVersionUID = 1L;
	private boolean shadowMode;
	private boolean multiPlayer;
	
	public LevelSelectPanel (Game g, PuzzleGridGenerator psg, boolean multiPlayer)
	{
		this.setBackground(ImageFactory.Colors.customOrange);
		this.setLayout(new GridBagLayout());
		this.shadowMode = false;
		this.multiPlayer = multiPlayer;
		populateComponents(g, psg);
		
	}
	
	private void populateComponents(Game g, PuzzleGridGenerator psg)
	{
		int i =0;
		int numLevels = (this.multiPlayer==true) ? psg.getNumberOfMultiPlayerLevels():psg.getNumberOfSinglePlayerLevels();
		for(i = 0; i < numLevels; i++)
		{
			String levelString = numberToWord(i+1);
			JButton newLevel = new JButton("LEVEL " + levelString);
			PuzzleGrid level = (this.multiPlayer==true) ? psg.getMultiLevel(i):psg.getLevel(i);
			registerLevelClickToLoadPuzzle(g, newLevel, level);
			addGridComponent(newLevel, 0, i);
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
		
		
		JCheckBox checkBox = new JCheckBox("Shadow Mode");
		checkBox.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            shadowMode = true;
		        } else {
		        	shadowMode = false;
		        }
		    }
		});
		checkBox.setFocusable(false);
		addGridComponent(checkBox, 0, i+2);
	}
	
	private void registerLevelClickToLoadPuzzle(Game g, JButton button, PuzzleGrid level)
	{
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				level.setShadow(shadowMode);
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
	
