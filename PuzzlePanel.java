package Game;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Game.PuzzleGridGenerator.Type;

/**
 * This class is a custom JPanel object that has properties for the puzzle dimensions
 * and the puzzle labels to load into the panel and reshuffle when the player changes
 * the puzzle state with the arrow keys which send a key event which is handled by this
 * panel as well
 * @field ROWS: the number of rows of puzzle labels to add to the panel
 * @field COLUMNS: The number of columns of puzzle labels
 * @field labels: The ordered list of puzzle labels to add to the panel from the top row
 * and wrapping to the next rows until the last column of the last row
 * @field: playerPiece: The reference to the player square that is to be moved around
 * when any arrow keys are pressed.
 */
public class PuzzlePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private final int ROWS;
	private final int COLUMNS;
	private PuzzleGrid grid;
	
	private ArrayList<PuzzleLabel> labels;
	private PuzzleLabel playerPiece;
	
	/**
	 * Creates a JPanel object with an empty grid structure with the specified rows and columns
	 * @param rows: The number of rows
	 * @param columns: The number of columns
	 * @return panel: The JPanel with the grid
	 */
	public PuzzlePanel(PuzzleGrid grid)
	{		
		this.ROWS = grid.getRows();
		this.COLUMNS = grid.getColumns();
		this.grid = grid;

		this.playerPiece = grid.getPlayer();
		this.labels = grid.getStartingLabels();
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setLayout(new GridLayout(ROWS, COLUMNS, 0, 0));

		this.reloadPanelLabels();		
	}
	
	/**
	 * Reloads the puzzle squares from the labels array so that the ordering of the labels in the
	 * panel matches the new order of puzzle squares in the labels array list.
	 */
	private void reloadPanelLabels()
	{
		this.removeAll();		
		for(PuzzleLabel lbl : labels)
		{
			this.add(lbl);
		}
		this.validate();
	}
	
	private void resetGame() {
		this.labels = grid.getStartingLabels();
		reloadPanelLabels();
	}
	/**
	 * Checks the location of the player piece and if the destination piece is valid (within the 
	 * grid), then the event is passed to the method to check whether the piece that the player
	 * piece is being moved to can be moved (box) or moved to (empty space, cross)
	 * @param e: The event from a key press
	 */
	public void handleKeyPress(KeyEvent e)
	{	
		if (e.getKeyCode() == KeyEvent.VK_R) {
			resetGame();
		}
		int manIndex = labels.indexOf(playerPiece);
		int swapIndex = validateKeyArrowDirection(e, manIndex);
		
		if(swapIndex != -1)
		{
			handleSwapObjectBehaviour(e, manIndex, swapIndex);
			reloadPanelLabels();
		}
		
		if(puzzleSolved())
		{
			JOptionPane.showMessageDialog(this, "Finished", "Congratulation", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}

	/**
	 * Updates the direction of the player to face in the direction of the arrow key pressed
	 * and then verifies if the destination cell is valid, then calculates the index of the 
	 * puzzle label that corresponds to the destination
	 * @param e: The key event passed
	 * @param playerIndex: The index in the labels array of the player piece
	 * @return swapIndex: The index of the piece at the destination
	 */
	private int validateKeyArrowDirection(KeyEvent e, int playerIndex)
	{
		int swapIndex = -1;
		
		setPlayerFacingDirection(e);
		if(checkDestinationIsWithinGrid(e, playerIndex))
		{
			swapIndex = playerIndex + getDestinationIndexOffset(e);
		}
		return swapIndex;
	}
	
	/**
	 * Sets the image of the player piece to the one corresponding to the direction of the
	 * arrow key pressed in the key event
	 * @param e: The key event passed
	 */
	private void setPlayerFacingDirection(KeyEvent e)
	{
		int keyCode = e.getKeyCode();	
		switch(keyCode)
		{
			case KeyEvent.VK_UP: 	playerPiece.setImage(PuzzleGridGenerator.Type.MANUP); 		break;
			case KeyEvent.VK_DOWN: 	playerPiece.setImage(PuzzleGridGenerator.Type.MANDOWN); 	break;
			case KeyEvent.VK_LEFT: 	playerPiece.setImage(PuzzleGridGenerator.Type.MANLEFT); 	break;
			case KeyEvent.VK_RIGHT: playerPiece.setImage(PuzzleGridGenerator.Type.MANRIGHT); 	break;
		}
	}
	
	/**
	 * Checks the index of the player piece in the array and returns true if the player is
	 * at least 1 puzzle piece away from the border of the panel in the direction of movement
	 * @param e: The key event passed
	 * @param playerIndex: The index of the player piece in the labels array
	 * @return: True if the destination is within the panel or false otherwise
	 */
	private boolean checkDestinationIsWithinGrid(KeyEvent e, int playerIndex)
	{
		int keyCode = e.getKeyCode();
		switch(keyCode)
		{
			case KeyEvent.VK_UP: 	return playerIndex >= COLUMNS;
			case KeyEvent.VK_DOWN: 	return playerIndex <= COLUMNS*(ROWS-1);
			case KeyEvent.VK_LEFT: 	return playerIndex % COLUMNS != 0;
			case KeyEvent.VK_RIGHT: return playerIndex % COLUMNS != ROWS-1;
		}
		return false;
	}
	
	/**
	 * Calculates the index offset to get from the index of the player piece to the index of the
	 * destination piece.
	 * @param e: The key event
	 * @return: The integer offset or 0 if not an arrow key
	 */
	private int getDestinationIndexOffset(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		switch(keyCode)
		{
			case KeyEvent.VK_UP: 	return -COLUMNS;
			case KeyEvent.VK_DOWN: 	return COLUMNS;
			case KeyEvent.VK_LEFT: 	return -1;
			case KeyEvent.VK_RIGHT: return 1;
		}
		return 0;
	}
	
	/**
	 * Assumes that the move from the player index to the destination index is valid and checks
	 * what object is at the destination index and either checks if it can move it in the same 
	 * direction if it is a box, or simply swaps it with the player if it can be moved over like
	 * an empty square or a cross
	 * @param e: The key event
	 * @param playerIndex: The index of the player piece
	 * @param destinationIndex: The index of the  destination piece
	 * @precondition: player to destination is a valid move
	 */
	private void handleSwapObjectBehaviour(KeyEvent e, int playerIndex, int destinationIndex)
	{
		PuzzleLabel toSwap = labels.get(destinationIndex);
		if(toSwap.isType(Type.BRICK))
		{
			return;
		}
		else if(toSwap.isType(Type.EMPTY))
		{
			if(playerPiece.isType(Type.CROSS))
			{
				toSwap.setTypeAndImage(Type.CROSS);
			}
			playerPiece.setType(Type.MANDOWN);
		}
		else if(toSwap.isType(Type.CROSS))
		{
			if(!playerPiece.isType(Type.CROSS))
			{
				toSwap.setTypeAndImage(Type.EMPTY);
			}
			playerPiece.setType(Type.CROSS);
		}
		else if(toSwap.isType(Type.BOX) || toSwap.isType(Type.GREENBOX))
		{
			int toSwapSwapIndex = validateKeyArrowDirection(e, destinationIndex);
			PuzzleLabel toSwapWithToSwap = labels.get(toSwapSwapIndex);
			if(toSwapWithToSwap.isType(Type.EMPTY))
			{
				if(playerPiece.isType(Type.CROSS))
				{
					toSwapWithToSwap.setTypeAndImage(Type.CROSS);
					playerPiece.setType(Type.MANDOWN);
				}
				
				if(toSwap.isType(Type.GREENBOX))
				{
					toSwap.setTypeAndImage(Type.BOX);
					playerPiece.setType(Type.CROSS);
				}
			}
			else if(toSwapWithToSwap.isType(Type.CROSS))
			{
				if(!playerPiece.isType(Type.CROSS))
				{
					toSwapWithToSwap.setTypeAndImage(Type.EMPTY);
				}
				
				if(toSwap.isType(Type.GREENBOX))
				{
					playerPiece.setType(Type.CROSS);
				}
				else if(toSwap.isType(Type.BOX))
				{
					toSwap.setTypeAndImage(Type.GREENBOX);
					playerPiece.setType(Type.MANDOWN);
				}			
			}
			else
			{
				return;
			}
			
			Collections.swap(labels, destinationIndex, toSwapSwapIndex);
		}
	
		Collections.swap(labels, playerIndex, destinationIndex);
	}

	/**
	 * Checks if the puzzle has been solved by looking for any puzzle pieces in the labels array
	 * for any objects of type 'box' which indicate that there are still pieces left to move onto
	 * a cross to turn into a type 'greenbox'
	 * @return
	 */
	private boolean puzzleSolved()
	{
		boolean solved = true;
		for(PuzzleLabel ps : labels)
		{
			if(ps.isType(PuzzleGridGenerator.Type.BOX))
			{
				solved = false;
				break;
			}
		}
		return solved;
	}

}
