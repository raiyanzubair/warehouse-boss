package Game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import Game.ImageFactory.Player;
import Game.ImageFactory.Type;

/**
 * This class is a custom JPanel object that has properties for the puzzle dimensions
 * and the puzzle labels to load into the panel and reshuffle when the player changes
 * the puzzle state with the arrow keys which send a key event which is handled by this
 * panel as well
 * @field ROWS: the number of rows of puzzle labels to add to the panel
 * @field COLUMNS: The number of columns of puzzle labels
 * @field nMoves
 * @field labels: The ordered list of puzzle labels to add to the panel from the top row
 * and wrapping to the next rows until the last column of the last row
 * @field: playerPiece: The reference to the player square that is to be moved around
 * when any arrow keys are pressed.
 */
public class PuzzleManager
{
	private boolean shadowMode;
	private final int level;
	private final int ROWS;
	private final int COLUMNS;
	private int nMoves;
	private Game game;
	private ArrayList<PuzzleLabel> currentLabelSequence;
	private Stack<PuzzleGrid> previousStates;
	private PuzzleLabel playerOnePiece;
	private PuzzleLabel playerTwoPiece;

	private PuzzleDisplayPanel panel;
	
	public PuzzleManager(PuzzleDisplayPanel panel, PuzzleGrid grid, Game g)
	{	
	
		this.level = grid.getLevelID();
		this.ROWS = grid.getRows();
		this.COLUMNS = grid.getColumns();
		this.shadowMode = grid.isShadow();
		this.nMoves = 0;
		this.game = g;
		
		this.currentLabelSequence = grid.getLabelSequence();
		this.playerOnePiece = grid.getPlayer(Player.ONE);
		this.playerTwoPiece = grid.getPlayer(Player.TWO);
		this.previousStates = new Stack<PuzzleGrid>();
		
		this.panel = panel;
		this.panel.reloadPanelLabels(currentLabelSequence, shadowMode);
	}
	
	public int getnMoves() 
	{
		return nMoves;
	}
	
	public void saveLabelsState()
	{
		PuzzleLabel newPlayerOne = playerOnePiece.Clone();
		PuzzleLabel newPlayerTwo = playerTwoPiece != null ? playerTwoPiece.Clone() : null;
		ArrayList<PuzzleLabel> savedState = new ArrayList<PuzzleLabel>();
		for(PuzzleLabel pl : currentLabelSequence)
		{
			PuzzleLabel toAdd = pl.equals(playerOnePiece) ? newPlayerOne : pl.equals(playerTwoPiece) ? newPlayerTwo : pl.Clone();
			savedState.add(toAdd);
		}
		previousStates.push(new PuzzleGrid(ROWS, COLUMNS, currentLabelSequence, playerOnePiece, playerTwoPiece));
		
		currentLabelSequence = savedState;
		playerOnePiece = newPlayerOne;
		playerTwoPiece = newPlayerTwo;
		
		panel.reloadPanelLabels(currentLabelSequence, shadowMode);
	}
	
	public void reloadLastLabelState()
	{
		if(previousStates.size() > 0)
		{
			PuzzleGrid savedState = previousStates.pop();
			
			currentLabelSequence = savedState.getLabelSequence();
			playerOnePiece = savedState.getPlayer(Player.ONE);
			playerTwoPiece = savedState.getPlayer(Player.TWO);
			
			panel.reloadPanelLabels(currentLabelSequence, shadowMode);
		}
	}
	
	public void resetGame()
	{
		if(previousStates.size() > 0)
		{
			PuzzleGrid start = previousStates.get(0);
			currentLabelSequence = start.getLabelSequence();
			playerOnePiece = start.getPlayer(Player.ONE);
			playerTwoPiece = start.getPlayer(Player.TWO);
			previousStates.clear();
			nMoves = 0;
			panel.reloadPanelLabels(currentLabelSequence, shadowMode);
		}
	}
	

	/**
	 * Checks the location of the player piece and if the destination piece is valid (within the 
	 * grid), then the event is passed to the method to check whether the piece that the player
	 * piece is being moved to can be moved (box) or moved to (empty space, cross)
	 * @param e: The event from a key press
	 */
	public void handleKeyPress(KeyEvent e, PuzzleGrid grid)
	{	
		if (e.getKeyCode() == KeyEvent.VK_R)
		{
			resetGame();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_U)
		{
			reloadLastLabelState();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			registerMove(e, playerOnePiece);
			validatePuzzleSolved(grid);
		}	
		
		if (grid.isMultiplayer()) {	
			if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D)
			{
				registerMove(e, playerTwoPiece);
				validatePuzzleSolved(grid);
			}
		}
	}
	
	public void registerMove(KeyEvent e, PuzzleLabel playerPiece)
	{
		saveLabelsState();
		
		int manIndex = currentLabelSequence.indexOf(playerPiece);
		int swapIndex = validateKeyArrowDirection(e, manIndex);
		if(swapIndex != -1)
		{
			setPlayerFacingDirection(e, currentLabelSequence.get(manIndex));
			handleSwapObjectBehaviour(e, manIndex, swapIndex);
			panel.reloadPanelLabels(currentLabelSequence, shadowMode);
			validateNumMoves(manIndex, playerPiece);
		}
	}
	
	public void validateNumMoves(int originalManIndex, PuzzleLabel playerPiece)
	{
		int manIndex = playerPiece.getPlayer() == Player.ONE ? currentLabelSequence.indexOf(playerOnePiece) :
					   playerPiece.getPlayer() == Player.TWO ? currentLabelSequence.indexOf(playerTwoPiece) : -1;
				
		if(manIndex != originalManIndex)
		{
			nMoves++;
		}
	}

	public void validatePuzzleSolved(PuzzleGrid grid)
	{
		if (puzzleSolved())
		{
			if (nMoves < grid.getHighScore() || grid.getHighScore() == -1) {
				grid.setHighScore(this.nMoves);
			}
			game.showWinScreen(level, grid);
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
	public int validateKeyArrowDirection(KeyEvent e, int playerIndex)
	{
		int swapIndex = -1;

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
	public void setPlayerFacingDirection(KeyEvent e, PuzzleLabel playerPiece)
	{
		int keyCode = translateKeyCode(e);	
		switch(keyCode)
		{
			case KeyEvent.VK_UP: 	playerPiece.setImage(Type.P1_UP); 		break;
			case KeyEvent.VK_DOWN: 	playerPiece.setImage(Type.P1_DOWN); 	break;
			case KeyEvent.VK_LEFT: 	playerPiece.setImage(Type.P1_LEFT); 	break;
			case KeyEvent.VK_RIGHT: playerPiece.setImage(Type.P1_RIGHT); 	break;
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
		int keyCode = translateKeyCode(e);	
		switch(keyCode)
		{
			case KeyEvent.VK_UP: 	return playerIndex >= COLUMNS;
			case KeyEvent.VK_DOWN: 	return playerIndex <= COLUMNS*(ROWS-1);
			case KeyEvent.VK_LEFT: 	return playerIndex % COLUMNS > 0;
			case KeyEvent.VK_RIGHT: return playerIndex % COLUMNS < COLUMNS-1;
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
		int keyCode = translateKeyCode(e);	
		switch(keyCode)
		{
			case KeyEvent.VK_UP: 	return -COLUMNS;
			case KeyEvent.VK_DOWN: 	return COLUMNS;
			case KeyEvent.VK_LEFT: 	return -1;
			case KeyEvent.VK_RIGHT: return 1;
		}
		return 0;
	}
	
	private int translateKeyCode(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		switch(keyCode)
		{
			case KeyEvent.VK_W: 	return KeyEvent.VK_UP;
			case KeyEvent.VK_S: 	return KeyEvent.VK_DOWN;
			case KeyEvent.VK_A: 	return KeyEvent.VK_LEFT;
			case KeyEvent.VK_D: 	return KeyEvent.VK_RIGHT;
		}
		return keyCode;
	}
	
	/**
	 * Assumes that the move from the player index to the destination index is valid and checks
	 * what object is at the destination index and either checks if it can move it in the same 
	 * direction if it is a box, or simply swaps it with the player if it can be moved over like
	 * an empty square or a cross
	 * @param e: The key event
	 * @param playerIndex: The index of the player piece
	 * @param destinationIndex: The index of the  destination piece
	 * @return 
	 * @precondition: player to destination is a valid move
	 */
	private boolean handleSwapObjectBehaviour(KeyEvent e, int playerIndex, int destinationIndex)
	{
		PuzzleLabel playerPiece = currentLabelSequence.get(playerIndex);
		PuzzleLabel toSwap = currentLabelSequence.get(destinationIndex);
		if(toSwap.isType(Type.BRICK, Player.NONE) || toSwap.isType(Type.P1_RIGHT, playerPiece.otherPlayer()))
		{
			return false;
		}
		else if(toSwap.isType(Type.EMPTY, Player.NONE))
		{
			if(playerPiece.isType(Type.P1_CROSS, playerPiece.getPlayer()))
			{
				toSwap.setTypeAndImage(Type.P1_CROSS);
			}
			playerPiece.setType(Type.P1_RIGHT);
		}
		else if(toSwap.isType(Type.P1_CROSS, Player.NONE))
		{
			if(!playerPiece.isType(Type.P1_CROSS, playerPiece.getPlayer()))
			{
				toSwap.setTypeAndImage(Type.EMPTY);
			}
			playerPiece.setType(Type.P1_CROSS);
		}
		else if(toSwap.isType(Type.BOX, Player.NONE) || toSwap.isType(Type.P1_BOXED, Player.NONE))
		{
			int toSwapSwapIndex = validateKeyArrowDirection(e, destinationIndex);
			if(toSwapSwapIndex == -1)
			{
				return false;
			}
			PuzzleLabel toSwapWithToSwap = currentLabelSequence.get(toSwapSwapIndex);
			
			if(toSwapWithToSwap.isType(Type.EMPTY, Player.NONE))
			{
				if(playerPiece.isType(Type.P1_CROSS, playerPiece.getPlayer()))
				{
					toSwapWithToSwap.setTypeAndImage(Type.P1_CROSS);
					playerPiece.setType(Type.P1_RIGHT);
				}

				if(toSwap.isType(Type.P1_BOXED, Player.NONE))
				{
					toSwap.setTypeAndImage(Type.BOX);
					playerPiece.setType(Type.P1_CROSS);
				}
			}
			else if(toSwapWithToSwap.isType(Type.P1_CROSS, Player.NONE))
			{
				if(!playerPiece.isType(Type.P1_CROSS, playerPiece.getPlayer()))
				{
					toSwapWithToSwap.setTypeAndImage(Type.EMPTY);
				}

				if(toSwap.isType(Type.P1_BOXED, Player.NONE))
				{
					playerPiece.setType(Type.P1_CROSS);
				}
				else if(toSwap.isType(Type.BOX, Player.NONE))
				{
					toSwap.setTypeAndImage(Type.P1_BOXED);
					playerPiece.setType(Type.P1_RIGHT);
				}			
			}
			else
			{
				return false;
			}

			Collections.swap(currentLabelSequence, destinationIndex, toSwapSwapIndex);
		}
		else
		{
			return false;
		}

		Collections.swap(currentLabelSequence, playerIndex, destinationIndex);

		return true;
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
		for(PuzzleLabel ps : currentLabelSequence)
		{
			if(ps.isType(Type.BOX, Player.NONE))
			{
				solved = false;
				break;
			}
		}
		return solved;
	}

}
