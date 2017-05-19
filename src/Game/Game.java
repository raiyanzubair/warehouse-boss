package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
	public Game()
	{
		gameFrame = new JFrame();
		menuFrame = new JFrame();
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
		JPanel wholePanel = new JPanel();
		wholePanel.setLayout(new BoxLayout(wholePanel, BoxLayout.Y_AXIS));
		Color customOrange = new Color(255, 165, 96);
		wholePanel.setBackground(customOrange);
		wholePanel.setVisible(true);
		
		JPanel topWall = new JPanel(new BorderLayout());
		ImageIcon topWallImage = new ImageIcon("src/menu/try2.jpg");
		JLabel label = new JLabel("", topWallImage, JLabel.CENTER);
		topWall.setBackground(customOrange);
		topWall.add(label);
		topWall.setVisible(true);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(customOrange);
		JLabel title = new JLabel("WAREHOUSE BOSS");
		title.setFont(new Font("Tahoma", Font.BOLD, 32));
		title.setForeground(Color.WHITE);
		titlePanel.add(title);
		titlePanel.setVisible(true);
		
		MenuPanel menuPanel = new MenuPanel(this, this.menuFrame, psg);
		
		JPanel bottomWall = new JPanel(new BorderLayout());
		ImageIcon bottomWallImage = new ImageIcon("src/menu/bottomwall.jpg");
		JLabel bottomLabel = new JLabel("", bottomWallImage, JLabel.CENTER);
		bottomWall.setBackground(customOrange);
		bottomWall.add(bottomLabel);
		bottomWall.setVisible(true);
		
		wholePanel.add(topWall,BorderLayout.CENTER);
		wholePanel.add(titlePanel, BorderLayout.CENTER);
		wholePanel.add(menuPanel, BorderLayout.CENTER);
		wholePanel.add(bottomWall, BorderLayout.CENTER);
		
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
		gameFrame.dispose();
		gameFrame = new JFrame();
		PuzzlePanel panel = new PuzzlePanel(grid);
		ButtonPanel buttons = new ButtonPanel(panel, gameFrame, this);

		
		gameFrame.add(buttons, BorderLayout.WEST);
		gameFrame.add(panel, BorderLayout.CENTER);
		gameFrame.pack();
		gameFrame.setTitle("Puzzle");
		gameFrame.setResizable(false);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		gameFrame.addKeyListener(new KeyAction()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				panel.handleKeyPress(e);
			}

		});
		gameFrame.setVisible(true);
		gameFrame.validate();
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
