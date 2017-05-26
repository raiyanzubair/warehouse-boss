package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * This is an example of a single GUI window that just has a panel with the puzzle
 * for now. It also has a puzzle grid generator that creates a puzzle starting grid
 * for the panel to display
 * @field panel: The single game panel being added
 * @field psg: The puzzle generator object for making puzzles
 */
public class Game
{
	private static PuzzleGridGenerator psg = new PuzzleGridGenerator();
	private JFrame gameFrame;
	private JFrame menuFrame;
	private JFrame levelFrame;
	private JFrame winFrame;
	private JFrame tutorialFrame;
	
	public Game()
	{
		this.gameFrame = new JFrame();
		this.levelFrame = new JFrame();
		this.menuFrame = new JFrame();
		this.winFrame = new JFrame();
		this.tutorialFrame = new JFrame();
	}
	
	/**
	 * The main method which just creates a GameFrame frame window and sets its
	 * properties to display for the user
	 * @param args: Any command line arguments
	 */
	public static void main(String[] args)
	{
		Game g = new Game();
		g.showMenuScreen();
	}
	
	public void showMenuScreen() 
	{
		tutorialFrame.setVisible(false);
		levelFrame.setVisible(false);
		gameFrame.setVisible(false);
		
		menuFrame = new JFrame();
		TitleMenuPanel menuPanel = new TitleMenuPanel(this, psg);
		setDefaultLayout(menuPanel, menuFrame);	
	}
	
	public void showLevelSelect(boolean multiPlayer) 
	{
		gameFrame.setVisible(false);
		menuFrame.setVisible(false);
		
		levelFrame = new JFrame();
		LevelSelectPanel lsp = new LevelSelectPanel(this, psg, multiPlayer);
		setDefaultLayout(lsp, this.levelFrame);
	}
	
	/**
	 * This method initializes a JFrame with just the puzzle in a panel and 
	 * displays it to the user with a key event listener for the controls
	 */
	public void showGameScreen(PuzzleGrid grid)
	{
		levelFrame.setVisible(false);
		menuFrame.setVisible(false);
		gameFrame = new JFrame();
		
		PuzzleDisplayPanel panel = new PuzzleDisplayPanel(grid.getRows(), grid.getColumns());
		PuzzleManager manager = !grid.isMultiplayer() ? new PuzzleManager(panel, grid, this) : new PuzzleManagerMultiplayer(panel, grid, this);
		PuzzleControlPanel buttons = new PuzzleControlPanel(manager, grid, this);

		panel.addKeyListener(new KeyAction()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				manager.handleKeyPress(e, grid);
				buttons.updateMoves(manager.getnMoves());
			}
		});

		gameFrame.add(buttons, BorderLayout.SOUTH);
		gameFrame.add(panel, BorderLayout.CENTER);
		gameFrame.pack();
		gameFrame.setTitle("Puzzle");
		gameFrame.setResizable(false);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		gameFrame.setVisible(true);
		gameFrame.validate();
	}
	
	public void showWinScreen(int level, PuzzleGrid grid)
	{
		winFrame = new JFrame();
		JPanel container;
		JButton nextLevelButton;
		JButton levelSelectButton;
		JButton menuButton;
		int levelNumber = level+1;
		int numLevels = (grid.isMultiplayer() ? psg.getNumberOfMultiPlayerLevels():psg.getNumberOfSinglePlayerLevels());
		PuzzleGrid nextLevel = (grid.isMultiplayer() ? psg.getMultiLevel(levelNumber):psg.getLevel(levelNumber));

		nextLevelButton = new JButton("Next Level");
		nextLevelButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				gameFrame.setVisible(false);
				showGameScreen(nextLevel);
				winFrame.setVisible(false);
			}
		});
		
		levelSelectButton = new JButton("Return to Level Select");
		levelSelectButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				showLevelSelect(grid.isMultiplayer());
				winFrame.setVisible(false);
			}
		});
		
		menuButton = new JButton("Return to Main Menu");
		menuButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				showMenuScreen();
				winFrame.setVisible(false);
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

		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBackground(ImageFactory.Colors.customOrange);
		if (levelNumber < numLevels)
		{
			container.add(nextLevelButton, BorderLayout.CENTER);
		}
		container.add(levelSelectButton, BorderLayout.CENTER);		
		container.add(menuButton, BorderLayout.CENTER);
		container.add(checkBox, BorderLayout.CENTER);
		
		
		winFrame.setTitle("Level " + levelNumber + " Complete");
		winFrame.setResizable(false);
		winFrame.setLocationRelativeTo(null);
		winFrame.setVisible(true);
		winFrame.add(container);
		winFrame.pack();
	}
	
	public void showTutorialScreen() 
	{
		tutorialFrame = new JFrame();
		
		JPanel tutorialPanel = new TutorialPanel(this);
		JScrollPane scrollPane = new JScrollPane(tutorialPanel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		tutorialFrame.add(scrollPane);
		tutorialFrame.setTitle("Tutorial");
		tutorialFrame.setResizable(true);
		tutorialFrame.setVisible(true);
		tutorialFrame.setSize(460, 600);
		tutorialFrame.setLocationRelativeTo(null);
		//packTutorial();
	}
	
	public void packTutorial()
	{
		tutorialFrame.pack();		
	}
	
	private void setDefaultLayout (JPanel targetPanel, JFrame targetFrame) 
	{
		JPanel topWall = new JPanel(new BorderLayout());
		JLabel topWallLabel = new JLabel(ImageFactory.topWall, JLabel.CENTER);
		topWall.setBackground(ImageFactory.Colors.customOrange);
		topWall.add(topWallLabel);
		topWall.setVisible(true);
		
		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("WAREHOUSE BOSS");
		title.setFont(new Font("Tahoma", Font.BOLD, 32));
		title.setForeground(Color.WHITE);
		titlePanel.add(title);
		titlePanel.setBackground(ImageFactory.Colors.customOrange);
		titlePanel.setVisible(true);
		
		JPanel bottomWall = new JPanel(new BorderLayout());
		JLabel bottomWallLabel = new JLabel("", ImageFactory.bottomWall, JLabel.CENTER);
		bottomWall.setBackground(ImageFactory.Colors.customOrange);
		bottomWall.add(bottomWallLabel);
		bottomWall.setVisible(true);
		
		JPanel wholePanel = new JPanel();
		wholePanel.setLayout(new BoxLayout(wholePanel, BoxLayout.Y_AXIS));
		wholePanel.setBackground(ImageFactory.Colors.customOrange);
		wholePanel.add(topWall,BorderLayout.CENTER);
		wholePanel.add(titlePanel, BorderLayout.CENTER);
		wholePanel.add(targetPanel, BorderLayout.CENTER);
		wholePanel.add(bottomWall, BorderLayout.CENTER);
		wholePanel.setVisible(true);
		
		targetFrame.add(wholePanel, BorderLayout.CENTER);
	
		targetFrame.setTitle("Warehouse Boss");
		targetFrame.setSize(400, 560);
		targetFrame.setResizable(false);
		targetFrame.setLocationRelativeTo(null);
		targetFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		targetFrame.setVisible(true);
		targetFrame.pack();
	}
	
	
	/**
	 * This class handles the incoming key events from the GameFram object and
	 * delegates the event to event handler which is the panel in this case
	 * to check or arrow key presses
	 */
	private class KeyAction implements KeyListener
	{
		@Override
		public void keyTyped(KeyEvent e){}

		@Override
		public void keyPressed(KeyEvent e){}

		@Override
		public void keyReleased(KeyEvent e){}	
	}
}



