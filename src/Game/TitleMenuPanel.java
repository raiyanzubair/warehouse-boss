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
	
	JButton levelOne;
	JButton levelTwo;
	JButton levelThree;
	JButton levelFour;
	JButton quitButton;
	
	public TitleMenuPanel(Game g, PuzzleGridGenerator psg)
	{
		this.setBackground(ImageFactory.Colors.customOrange);
		this.setLayout(new GridBagLayout());
		
		populateComponents(g, psg);
	}
	
	private void populateComponents(Game g, PuzzleGridGenerator psg)
	{
		//JButton howToPlay = new JButton("HOW TO PLAY");

		for(int i = 0; i < psg.getNumberOfLevels(); i++)
		{
			String level = numberToWord(i+1);
			levelOne = new JButton("LEVEL " + level);
			registerLevelClickToLoadPuzzle(g, levelOne, psg.generatePuzzleGrid(i));
			addGridComponent(levelOne, 0, i);
		}	
		
		quitButton = new JButton("QUIT");
		quitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		addGridComponent(quitButton, 0, 4);
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
		this.add(component, gbc);
	}

	public static String numberToWord(int num) 
	{
        String ones[] = {" ", " ONE", " TWO", " THREE", " FOUR", " FIVE", " SIX", " SEVEN", " EIGHT", " NINE", " TEN", " ELEVEN", " TWELVE", " THIRTEEN", " FOURTEEN", " FIFTEEN", " SIXTEEN", " SEVENTEEN", " EIGHTEEN", " NINETEEN"};
        String tens[] = {" ", " ", " TWENTY", " THIRTY", " FOURTY", " FIFTY", " SIXTY", " SEVENTY", " EIGHTY", " NINETY"};
        return (num < 20) ? ones[num] : tens[num / 10] + " " + ones[num % 10];
    }
}
