package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Main class that stores the various JFrames used in navigation and playing of the game. 
 * Handles the construction of these JFrames and the methods to display them. 
 * @field psg: The puzzle generator object for making puzzles
 * @field gameFrame: JFrame that holds the actual puzzle game screen and its associated JPanels
 * @field menuFrame: JFrame that holds the main menu screen
 * @field levelFrame: JFrame that holds the level select screen
 * @field winFrame: JFrame that holds the win screen
 * @field tutorialFrame: JFrame that holds the tutorial screen
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
		this.winFrame = new JFrame();
		this.tutorialFrame = new JFrame();
		this.menuFrame = new JFrame();
		TitleMenuPanel menuPanel = new TitleMenuPanel(this, psg);
		setDefaultLayout(menuPanel, menuFrame);	
	}
	
	/**
	 * The main method which creates a game object and from there
	 * shows the menu screen and allows the user to open more screens.
	 * @param args: Any command line arguments
	 */
	public static void main(String[] args)
	{
		Game g = new Game();
		g.showMenuScreen();
	}
	
	/**
	 * Method that fills the menuFrame with a TitleMenuPanel, displaying a title menu
	 * which allows the user to select single player, mutltiplayer, tutorial, or exit
	 */
	public void showMenuScreen() 
	{
		tutorialFrame.setVisible(false);
		levelFrame.setVisible(false);
		gameFrame.setVisible(false);
		winFrame.setVisible(false);
		
		menuFrame.setVisible(true);
	}
	
	/**
	 * Method that fills the levelFrame with a LevelSelectPanel and allows the user to select levels.
	 * Shows a different level select screen depending on if multiplayer was selected.
	 * @param multiPlayer: boolean value that determines if multiplayer is enabled or not
	 */
	public void showLevelSelect(boolean multiPlayer) 
	{
		gameFrame.setVisible(false);
		menuFrame.setVisible(false);
		winFrame.setVisible(false);
		
		levelFrame = new JFrame();
		LevelSelectPanel lsp = new LevelSelectPanel(this, psg, multiPlayer);
		setDefaultLayout(lsp, this.levelFrame);
	}
	
	/**
	 * Method that fills the gameFrame with a PuzzleDisplayPanel to display the puzzle grid,
	 * and a PuzzleControlPanel which displays information and has access buttons.
	 * Also either creates a PuzzleManager that handles the backend of the actual game logic. 
	 * @param grid: PuzzleGrid object that is the specified level puzzle that will be used
	 */
	public void showGameScreen(PuzzleGrid grid)
	{
		gameFrame.setVisible(false);
		levelFrame.setVisible(false);
		menuFrame.setVisible(false);
		winFrame.setVisible(false);
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
	

	/**
	 * Method that fills the winFrame with a panel to display the win screen and allow you to return
	 * back to the main menu or continue on to complete the next level.
	 * @param level: integer index of the level we just completed
	 * @param grid: PuzzleGrid object of the completed level
	 */
	public void showWinScreen(int level, boolean multiplayer)
	{
		winFrame = new JFrame();
		
		int numLevels = (multiplayer ? psg.getNumberOfMultiPlayerLevels():psg.getNumberOfSinglePlayerLevels());
		PuzzleGrid nextLevel = (multiplayer ? psg.getMultiLevel(level+1):psg.getLevel(level+1));
		JPanel winPanel = new WinPanel(this, level+1, numLevels, nextLevel, multiplayer);

		winFrame.setTitle("Level " + level + " Complete");
		winFrame.setResizable(false);
		winFrame.setLocationRelativeTo(null);
		winFrame.setVisible(true);
		winFrame.add(winPanel);
		winFrame.pack();
	}
	
	/**
	 * Method that fills the tutorialFrame with a TutorialPanel that gives the user
	 * instructions on how to play single and multiplayer.
	 */
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
	}
	
	/**
	 * Fills a JFrame with a specified JPanel in our default style. Standardizes colours, fonts,
	 * images, spacing etc. Used mainly for menu panels.
	 * @param targetPanel: JPanel that will be placed inside the target JFrame
	 * @param targetFrame: JFrame that will be used and set to a default layout/look
	 */
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
	 * KeyListener that is used to register keys.
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



