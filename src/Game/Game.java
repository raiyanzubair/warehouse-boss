package Game;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

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
	
	/**
	 * The main method which just creates a GameFrame frame window and sets its
	 * properties to display for the user
	 * @param args: Any command line arguments
	 */
	public static void main(String[] args)
	{
		Game g = new Game();
		g.showGameScreen();
	}
	
	/**
	 * This method initializes a JFrame with just the puzzle in a panel and 
	 * displays it to the user with a key event listener for the controls
	 */
	private void showGameScreen()
	{
		JFrame gameFrame = new JFrame();
		
//		ButtonPanel buttons = new ButtonPanel();
//		gameFrame.add(buttons, BorderLayout.WEST);
		PuzzlePanel panel = new PuzzlePanel(psg.generatePuzzleGrid());
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
