package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
	private JFrame winFrame;
	private JFrame tutorialFrame;
	
	public Game()
	{
		gameFrame = new JFrame();
		menuFrame = new JFrame();
		winFrame = new JFrame();
		tutorialFrame = new JFrame();
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
		gameFrame.setVisible(false);
		
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
		
		TitleMenuPanel menuPanel = new TitleMenuPanel(this, psg);
		
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
		wholePanel.add(menuPanel, BorderLayout.CENTER);
		wholePanel.add(bottomWall, BorderLayout.CENTER);
		wholePanel.setVisible(true);
		
		menuFrame.add(wholePanel, BorderLayout.CENTER);
	
		menuFrame.pack();
		menuFrame.setTitle("Warehouse Boss");
		menuFrame.setSize(400, 560);
		menuFrame.setResizable(false);
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setVisible(true);
	}
	
	/**
	 * This method initializes a JFrame with just the puzzle in a panel and 
	 * displays it to the user with a key event listener for the controls
	 */
	public void showGameScreen(PuzzleGrid grid)
	{
		menuFrame.setVisible(false);
		gameFrame.dispose();
		gameFrame = new JFrame();
		
		PuzzleDisplayPanel panel = new PuzzleDisplayPanel(grid.getRows(), grid.getColumns());
		PuzzleManager manager = new PuzzleManager(panel, grid, this);
		PuzzleControlPanel buttons = new PuzzleControlPanel(manager, this);

		panel.addKeyListener(new KeyAction()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				manager.handleKeyPress(e);
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
	
	public void showWinScreen(int level)
	{
		winFrame = new JFrame();
		JPanel container;
		JButton menuButton;
		JButton nextLevelButton;
		
		//JOptionPane.showMessageDialog(this, "Finished", "Congratulation", JOptionPane.INFORMATION_MESSAGE);

		menuButton = new JButton("Return to Main Menu");
		menuButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				showMenuScreen();
				winFrame.setVisible(false);
			}
		});
		
		nextLevelButton = new JButton("Next Level");
		nextLevelButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				showGameScreen(psg.generatePuzzleGrid(level+1));
				winFrame.setVisible(false);
			}
		});

		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBackground(ImageFactory.Colors.customOrange);
		container.add(menuButton, BorderLayout.CENTER);
		if(level+1 < psg.getNumberOfLevels())
		{
			container.add(nextLevelButton, BorderLayout.CENTER);
		}		
		
		int levelNumber = level+1;
		winFrame.setTitle("Level " + levelNumber + " Complete");
		winFrame.setResizable(false);
		winFrame.setLocationRelativeTo(null);
		winFrame.setVisible(true);
		winFrame.add(container);
		winFrame.pack();
	}
	
	public void showTutorialScreen(Game g) {
		menuFrame.setVisible(false);
		tutorialFrame = new JFrame();
		
		JPanel tutorialPanel = new TutorialPanel(g);
		JScrollPane scrollPane = new JScrollPane(tutorialPanel);
		scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		tutorialFrame.add(scrollPane);
		tutorialFrame.setTitle("Tutorial");
		tutorialFrame.setResizable(false);
		tutorialFrame.setLocationRelativeTo(null);
		tutorialFrame.setVisible(true);
		tutorialFrame.setSize(400, 560);
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



