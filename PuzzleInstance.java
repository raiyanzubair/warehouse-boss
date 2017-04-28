import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PuzzleInstance extends JFrame
{

	private static final long serialVersionUID = 1L;
	private final int ROWS = 8;
	private final int COLUMNS = 6;
	private JPanel panel;
	
	PuzzleSquare manLabel;
	private ArrayList<PuzzleSquare> labels;
	
	public PuzzleInstance()
	{
		initializeGame();
	}
	
	public static void main(String[] args)
	{
		PuzzleInstance puzzle = new PuzzleInstance();
		puzzle.setVisible(true);
	}
	
	private void initializeGame()
	{
		labels = new ArrayList<PuzzleSquare>();
		manLabel = new PuzzleSquare(PuzzleSquare.Type.MANDOWN);
		
		add(initializeGamePanel(ROWS, COLUMNS), BorderLayout.CENTER);
		initializeLabels();
		updateDisplay();
				
		pack();
		setTitle("Puzzle");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new KeyAction());
	}
	
	private JPanel initializeGamePanel(int rows, int columns)
	{
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setLayout(new GridLayout(rows, columns, 0, 0));
		return panel;
	}

	private void initializeLabels()
	{
		labels.add(new PuzzleSquare(PuzzleSquare.Type.EMPTY));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.EMPTY));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.EMPTY));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.EMPTY));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.EMPTY));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(manLabel);
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BOX));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.EMPTY));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.EMPTY));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BOX));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.EMPTY));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.EMPTY));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BOX));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.EMPTY));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.CROSS));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BOX));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.EMPTY));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.EMPTY));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.CROSS));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.CROSS));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.GREENBOX));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.CROSS));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
		labels.add(new PuzzleSquare(PuzzleSquare.Type.BRICK));
	}
	
	private void updateDisplay()
	{
		panel.removeAll();
		for(PuzzleSquare lbl : labels)
		{
			panel.add(lbl);
		}
		panel.validate();
	}
	
	private class KeyAction implements KeyListener
	{
		@Override
		public void keyTyped(KeyEvent e)
		{
		}

		@Override
		public void keyPressed(KeyEvent e)
		{
			handleKeyPress(e);
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
		}

		private void handleKeyPress(KeyEvent e)
		{
			if(e.getKeyCode() == KeyEvent.VK_R)
			{
				labels.clear();
				initializeLabels();
				updateDisplay();
				return;
			}
			
			int manIndex = labels.indexOf(manLabel);
			int swapIndex = validateKeyArrowDirection(e, manIndex);
			
			if(swapIndex != -1)
			{
				handleSwapObjectBehaviour(e, manIndex, swapIndex);
				updateDisplay();
			}
			
			if(puzzleSolved())
			{
				JOptionPane.showMessageDialog(panel, "Finished", "Congratulation", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		}

		private int validateKeyArrowDirection(KeyEvent e, int manIndex)
		{
			int swapIndex = -1;
			
			int keyCode = e.getKeyCode();		
			if(keyCode == KeyEvent.VK_UP)
			{
				manLabel.setImage(PuzzleSquare.Type.MANUP);
				if(manIndex >= COLUMNS)
				{
					swapIndex = manIndex-COLUMNS;
				}
			}
			else if(keyCode == KeyEvent.VK_DOWN)
			{
				manLabel.setImage(PuzzleSquare.Type.MANDOWN);
				if(manIndex <= COLUMNS*(ROWS-1))
				{
					swapIndex = manIndex+COLUMNS;
				}
			}
			else if(keyCode == KeyEvent.VK_LEFT)
			{
				manLabel.setImage(PuzzleSquare.Type.MANLEFT);
				if(manIndex % COLUMNS != 0)
				{
					swapIndex = manIndex-1;
				}
			}
			else if(keyCode == KeyEvent.VK_RIGHT)
			{
				manLabel.setImage(PuzzleSquare.Type.MANRIGHT);
				if(manIndex % COLUMNS != ROWS-1)
				{
					swapIndex = manIndex+1;
				}
			}

			return swapIndex;
		}
		
		private void handleSwapObjectBehaviour(KeyEvent e, int manIndex, int swapIndex)
		{
			PuzzleSquare toSwap = labels.get(swapIndex);
			if(toSwap.isType(PuzzleSquare.Type.BRICK))
			{
				return;
			}
			else if(toSwap.isType(PuzzleSquare.Type.EMPTY))
			{
				if(manLabel.isType(PuzzleSquare.Type.CROSS))
				{
					toSwap.setTypeAndImage(PuzzleSquare.Type.CROSS);
				}
				manLabel.setType(PuzzleSquare.Type.MANDOWN);
			}
			else if(toSwap.isType(PuzzleSquare.Type.CROSS))
			{
				if(!manLabel.isType(PuzzleSquare.Type.CROSS))
				{
					toSwap.setTypeAndImage(PuzzleSquare.Type.EMPTY);
				}
				manLabel.setType(PuzzleSquare.Type.CROSS);
			}
			else if(toSwap.isType(PuzzleSquare.Type.BOX) || toSwap.isType(PuzzleSquare.Type.GREENBOX))
			{
				int toSwapSwapIndex = validateKeyArrowDirection(e, swapIndex);
				PuzzleSquare toSwapWithToSwap = labels.get(toSwapSwapIndex);
				if(toSwapWithToSwap.isType(PuzzleSquare.Type.EMPTY))
				{
					if(manLabel.isType(PuzzleSquare.Type.CROSS))
					{
						toSwapWithToSwap.setTypeAndImage(PuzzleSquare.Type.CROSS);
						manLabel.setType(PuzzleSquare.Type.MANDOWN);
					}
					
					if(toSwap.isType(PuzzleSquare.Type.GREENBOX))
					{
						toSwap.setTypeAndImage(PuzzleSquare.Type.BOX);
						manLabel.setType(PuzzleSquare.Type.CROSS);
					}
				}
				else if(toSwapWithToSwap.isType(PuzzleSquare.Type.CROSS))
				{
					if(!manLabel.isType(PuzzleSquare.Type.CROSS))
					{
						toSwapWithToSwap.setTypeAndImage(PuzzleSquare.Type.EMPTY);
					}
					
					if(toSwap.isType(PuzzleSquare.Type.GREENBOX))
					{
						manLabel.setType(PuzzleSquare.Type.CROSS);
					}
					else if(toSwap.isType(PuzzleSquare.Type.BOX))
					{
						toSwap.setTypeAndImage(PuzzleSquare.Type.GREENBOX);
						manLabel.setType(PuzzleSquare.Type.MANDOWN);
					}			
				}
				else
				{
					return;
				}
				
				Collections.swap(labels, swapIndex, toSwapSwapIndex);
			}
		
			Collections.swap(labels, manIndex, swapIndex);
		}

		private boolean puzzleSolved()
		{
			boolean solved = true;
			for(PuzzleSquare blk : labels)
			{
				if(blk.isType(PuzzleSquare.Type.BOX))
				{
					solved = false;
					break;
				}
			}
			return solved;
		}
	}
}


