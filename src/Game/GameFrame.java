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
public class GameFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	private PuzzlePanel panel;
	private PuzzleGridGenerator psg;
	
	public GameFrame()
	{
		psg = new PuzzleGridGenerator();
		panel = new PuzzlePanel(psg.generatePuzzleGrid());
		add(panel, BorderLayout.CENTER);
		addKeyListener(new KeyAction());
	}
	
	/**
	 * The main method which just creates a GameFrame frame window and sets its
	 * properties to display for the user
	 * @param args: Any command line arguments
	 */
	public static void main(String[] args)
	{
		GameFrame puzzle = new GameFrame();
		puzzle.pack();
		puzzle.setTitle("Puzzle");
		puzzle.setResizable(false);
		puzzle.setLocationRelativeTo(null);
		puzzle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		puzzle.setVisible(true);
	}
	
	/**
	 * This class handles the incoming key events from the GameFram object and
	 * delegates the event to event handler which is the panel in this case
	 * to check or arrow key presses
	 */
	private class KeyAction implements KeyListener
	{
		@Override
		public void keyTyped(KeyEvent e)
		{
		}

		@Override
		public void keyPressed(KeyEvent e)
		{
			panel.handleKeyPress(e);
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
		}

		
	}
	
}
