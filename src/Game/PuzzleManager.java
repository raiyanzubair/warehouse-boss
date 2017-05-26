package Game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import Game.ImageFactory.Player;
import Game.ImageFactory.Type;

/**
 * Class controls the backend and logic for the game.
 * Only used for singlePlayer.
 * @field level: the current level number
 * @field ROWS: the number of rows of puzzle labels to add to the panel
 * @field COLUMNS: The number of columns of puzzle labels
 * @field nMoves: number of moves taken so far in the game
 * @field game: Game object that will be used to call on methods in game
 * @field currentLabelSequence: The ordered list of puzzle labels to add to the panel from the top row
 * @field previousStates: Stack of PuzzleGrids to be used as previous states in undoing of moves 
 * @field playerOnePiece: PuzzleLabel for the position of Player one
 * @field playerTwoPiece: PuzzleLabel for the position of Player two
 * @field panel: PuzzleDisplayPanel object that will be used to display the current game state
 */
public class PuzzleManager
{
	private final int level;
	private final int ROWS;
	private final int COLUMNS;
	private int nMoves;
	private Game game;
	protected ArrayList<PuzzleLabel> currentLabelSequence;
	private Stack<PuzzleGrid> previousStates;
	private PuzzleLabel playerOnePiece;
	protected PuzzleLabel playerTwoPiece;

	private PuzzleDisplayPanel panel;
	
	public PuzzleManager(PuzzleDisplayPanel panel, PuzzleGrid grid, Game g)
	{	
		this.level = grid.getLevelID();
		this.ROWS = grid.getRows();
		this.COLUMNS = grid.getColumns();
		this.nMoves = 0;
		this.game = g;
		
		this.currentLabelSequence = grid.getLabelSequence();
		this.playerOnePiece = grid.getPlayer(Player.ONE);
		this.playerTwoPiece = grid.getPlayer(Player.TWO);
		this.previousStates = new Stack<PuzzleGrid>();
		
		this.panel = panel;
		this.panel.reloadPanelLabels(currentLabelSequence, PuzzleGridGenerator.shadowMode);
	}
	
	public int getnMoves() 
	{
		return nMoves;
	}
	
	/**
	 * Method that saves the current label state which is used to undo moves, and return to previous states
	 */
	private void saveLabelsState()
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
		
		panel.reloadPanelLabels(currentLabelSequence, PuzzleGridGenerator.shadowMode);
	}
	
	/**
	 * Sets the current panel to display the previous label state. Used in undoing of moves.
	 */
	public void reloadLastLabelState()
	{
		if(previousStates.size() > 0)
		{
			PuzzleGrid savedState = previousStates.pop();
			
			currentLabelSequence = savedState.getLabelSequence();
			playerOnePiece = savedState.getPlayer(Player.ONE);
			playerTwoPiece = savedState.getPlayer(Player.TWO);
			
			panel.reloadPanelLabels(currentLabelSequence, PuzzleGridGenerator.shadowMode);
		}
	}
	
	/**
	 * Resets the PuzzleGrid to its initial state, resets move counters, and makes the PuzzlePanel display the start.
	 */
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
			panel.reloadPanelLabels(currentLabelSequence, PuzzleGridGenerator.shadowMode);
		}
	}
	
	/**
	 * Performs various actions based upon incoming keyEvents
	 * @param e: The event from a key press
	 * @param grid: the puzzlegrid level that is being passed through and being acted on
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
	}
	
	/**
	 * Method that registers that a move action has been made
	 * @param e: Keyevent that is passed through and determines what move is made
	 * @param playerPiece: reference to the player piece
	 */
	public void registerMove(KeyEvent e, PuzzleLabel playerPiece)
	{
		saveLabelsState();
		
		int manIndex = getPlayerIndex(playerPiece);
		int swapIndex = validateKeyArrowDirection(e, manIndex);
		if(swapIndex != -1)
		{
			setPlayerFacingDirection(e);
			handleSwapObjectBehaviour(e, manIndex, swapIndex);
			panel.reloadPanelLabels(currentLabelSequence, PuzzleGridGenerator.shadowMode);
			validateNumMoves(manIndex, playerPiece);
		}
	}
	
	private int getPlayerIndex(PuzzleLabel playerPiece)
	{
		Player p = playerPiece.getPlayer();
		int manIndex = p == Player.ONE ? currentLabelSequence.indexOf(playerOnePiece) : 
			p == Player.TWO ? currentLabelSequence.indexOf(playerTwoPiece) : -1;
			return manIndex;
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
	
	/**
	 * Converts key events from player 1 and 2s controls into one easy to use key code that can be passed elsewhere
	 * @param e: passed through key code
	 * @return keyCode that can be passed elsewhere
	 */
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
	 * Sets the image of the player piece to the one corresponding to the direction of the
	 * arrow key pressed in the key event
	 * @param e: The key event passed
	 */
	private void setPlayerFacingDirection(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_UP: 	playerOnePiece.setImage(Type.P1_UP); 		break;
			case KeyEvent.VK_DOWN: 	playerOnePiece.setImage(Type.P1_DOWN); 		break;
			case KeyEvent.VK_LEFT: 	playerOnePiece.setImage(Type.P1_LEFT); 		break;
			case KeyEvent.VK_RIGHT: playerOnePiece.setImage(Type.P1_RIGHT); 	break;
			case KeyEvent.VK_W: 	playerTwoPiece.setImage(Type.P2_UP); 		break;
			case KeyEvent.VK_S: 	playerTwoPiece.setImage(Type.P2_DOWN); 		break;
			case KeyEvent.VK_A: 	playerTwoPiece.setImage(Type.P2_LEFT); 		break;
			case KeyEvent.VK_D:	 	playerTwoPiece.setImage(Type.P2_RIGHT); 	break;
		}
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
	protected boolean handleSwapObjectBehaviour(KeyEvent e, int playerIndex, int destinationIndex)
	{
		PuzzleLabel playerPiece = currentLabelSequence.get(playerIndex);
		PuzzleLabel toSwap = currentLabelSequence.get(destinationIndex);
		if(toSwap.isType(Type.BRICK))
		{
			return false;
		}
		else if(toSwap.isType(Type.EMPTY))
		{
			if(playerPiece.isType(Type.P1_CROSS))
			{
				toSwap.setTypeAndImage(Type.P1_CROSS);
			}
			playerPiece.setType(Type.P1_RIGHT);
		}
		else if(toSwap.isType(Type.P1_CROSS))
		{
			if(!playerPiece.isType(Type.P1_CROSS))
			{
				toSwap.setTypeAndImage(Type.EMPTY);
			}
			playerPiece.setType(Type.P1_CROSS);
		}
		else if(toSwap.isType(Type.BOX) || toSwap.isType(Type.P1_BOXED))
		{
			int toSwapSwapIndex = validateKeyArrowDirection(e, destinationIndex);
			if(toSwapSwapIndex == -1)
			{
				return false;
			}
			PuzzleLabel toSwapWithToSwap = currentLabelSequence.get(toSwapSwapIndex);
			
			if(toSwapWithToSwap.isType(Type.EMPTY))
			{
				if(playerPiece.isType(Type.P1_CROSS))
				{
					toSwapWithToSwap.setTypeAndImage(Type.P1_CROSS);
					playerPiece.setType(Type.P1_RIGHT);
				}

				if(toSwap.isType(Type.P1_BOXED))
				{
					toSwap.setTypeAndImage(Type.BOX);
					playerPiece.setType(Type.P1_CROSS);
				}
			}
			else if(toSwapWithToSwap.isType(Type.P1_CROSS))
			{
				if(!playerPiece.isType(Type.P1_CROSS))
				{
					toSwapWithToSwap.setTypeAndImage(Type.EMPTY);
				}

				if(toSwap.isType(Type.P1_BOXED))
				{
					playerPiece.setType(Type.P1_CROSS);
				}
				else if(toSwap.isType(Type.BOX))
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
	 * ensures move counter is being incremented properly only when the player actually moves
	 * @param originalManIndex: Index of the player
	 * @param playerPiece: PuzzleLabel of the player
	 */
	public void validateNumMoves(int originalManIndex, PuzzleLabel playerPiece)
	{
		int manIndex = getPlayerIndex(playerPiece);	
		if(manIndex != originalManIndex)
		{
			nMoves++;
		}
	}
	
	/**
	 * Checks if the PuzzleGrid has been solved, and if it has it opens the win screen
	 * @param grid: PuzzleGrid that is being passed through
	 */
	public void validatePuzzleSolved(PuzzleGrid grid)
	{
		if(puzzleSolved())
		{
			if (nMoves < grid.getHighScore() || grid.getHighScore() == -1) 
			{
				grid.setHighScore(this.nMoves);
			}
			game.showWinScreen(level, grid.isMultiplayer());
		}
	}

	/**
	 * Checks if the puzzle has been solved by looking for any puzzle pieces in the labels array
	 * for any objects of type 'box' which indicate that there are still pieces left to move onto
	 * a cross to turn into a type 'greenbox'
	 * @return boolean that tells if puzzle is solved or not
	 */
	private boolean puzzleSolved()
	{
		boolean solved = true;
		for(PuzzleLabel ps : currentLabelSequence)
		{
			if(ps.isType(Type.BOX) || ps.isType(Type.P1_BOX) || ps.isType(Type.P2_BOX))
			{
				solved = false;
				break;
			}
		}
		return solved;
	}

}
