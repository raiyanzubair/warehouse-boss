package Game;

import java.awt.event.KeyEvent;
import java.util.Collections;

import Game.ImageFactory.Player;
import Game.ImageFactory.Type;

public class PuzzleManagerMultiplayer extends PuzzleManager
{
	public PuzzleManagerMultiplayer(PuzzleDisplayPanel panel, PuzzleGrid grid, Game g)
	{
		super(panel, grid, g);
	}
	
	
	@Override
	public void handleKeyPress(KeyEvent e, PuzzleGrid grid)
	{	
		super.handleKeyPress(e, grid);
		
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D)
		{
			registerMove(e, playerTwoPiece);
			validatePuzzleSolved(grid);
		}	
	}

	@Override
	protected boolean handleSwapObjectBehaviour(KeyEvent e, int playerIndex, int destinationIndex)
	{
		PuzzleLabel playerPiece = currentLabelSequence.get(playerIndex);
		PuzzleLabel toSwap = currentLabelSequence.get(destinationIndex);
		if(toSwap.isType(Type.BRICK) || toSwap.isPlayer())
		{
			return false;
		}
		else if(toSwap.isType(Type.EMPTY))
		{
			if(playerPiece.isGenericType(Type.P1_CROSS))
			{
				toSwap.setTypeAndImage(playerPiece.getType());
			}
			Type newPlayerType = getSpecificType("PLAYER", playerPiece.getPlayer());
			playerPiece.setType(newPlayerType);
		}
		else if(toSwap.isGenericType(Type.P1_CROSS) && toSwap.hasGenericImageType(Type.P1_CROSS))
		{
			Type toSwapOldType = toSwap.getType();
			if(playerPiece.isGenericType(Type.P1_CROSS))
			{
				toSwap.setTypeAndImage(playerPiece.getType());
			}
			else
			{
				toSwap.setTypeAndImage(Type.EMPTY);
			}
			playerPiece.setType(toSwapOldType);
		}
		else if(toSwap.isGenericType(Type.P1_BOX) || toSwap.isGenericType(Type.P1_BOXED) || toSwap.hasGenericImageType(Type.P1_BOX))
		{
			int toSwapSwapIndex = validateKeyArrowDirection(e, destinationIndex);
			if(toSwapSwapIndex == -1)
			{
				return false;
			}
			PuzzleLabel toSwapWithToSwap = currentLabelSequence.get(toSwapSwapIndex);
			
			if(toSwapWithToSwap.isType(Type.EMPTY))
			{
				if(playerPiece.isGenericType(Type.P1_CROSS))
				{
					toSwapWithToSwap.setTypeAndImage(playerPiece.getType());
				}

				if(toSwap.isGenericType(Type.P1_BOX))
				{
					Type newPlayerType = getSpecificType("PLAYER", playerPiece.getPlayer());
					playerPiece.setType(newPlayerType);
				}
				else if(toSwap.isGenericType(Type.P1_CROSS))
				{
					Type newBoxType = getSpecificType("BOX", toSwap.getPlayer());
					playerPiece.setType(toSwap.getType());
					toSwap.setType(newBoxType);
				}
				else if(toSwap.isGenericType(Type.P1_BOXED))
				{
					Type newCrossType = getSpecificType("CROSS", toSwap.getPlayer());
					Type newBoxType = getSpecificType("BOX", toSwap.getPlayer());
					toSwap.setTypeAndImage(newBoxType);
					playerPiece.setType(newCrossType);
				}
			}
			else if(toSwapWithToSwap.isGenericType(Type.P1_CROSS) && !toSwapWithToSwap.isPlayer() && !toSwapWithToSwap.hasGenericImageType(Type.P1_BOX))
			{
				Type oldToSwapWithToSwapType = toSwapWithToSwap.getType();
				Player oldToSwapWithToSwapPlayer = toSwapWithToSwap.getPlayer();
				if(playerPiece.isGenericType(Type.P1_CROSS))
				{
					toSwapWithToSwap.setTypeAndImage(playerPiece.getType());
				}
				else
				{
					toSwapWithToSwap.setTypeAndImage(Type.EMPTY);
				}

				if(toSwap.isGenericType(Type.P1_BOX) || toSwap.hasGenericImageType(Type.P1_BOX))
				{
					if(toSwap.isGenericType(Type.P1_CROSS))
					{
						playerPiece.setType(toSwap.getType());
					}
					
					if(oldToSwapWithToSwapPlayer != toSwap.getPlayer())
					{
						toSwap.setType(oldToSwapWithToSwapType);
					}
					else if(oldToSwapWithToSwapPlayer == toSwap.getPlayer())
					{
						Type newBoxType = getSpecificType("BOXED", oldToSwapWithToSwapPlayer);
						toSwap.setTypeAndImage(newBoxType);
					}
				}
				else if(toSwap.isGenericType(Type.P1_BOXED))
				{
					Type newPlayerType = getSpecificType("CROSS", toSwap.getPlayer());
					playerPiece.setType(newPlayerType);	
					
					if(oldToSwapWithToSwapPlayer != toSwap.getPlayer())
					{
						Type newBoxImage = getSpecificType("BOX", toSwap.getPlayer());					
						toSwap.setType(oldToSwapWithToSwapType);
						toSwap.setImage(newBoxImage);
					}
					else if(oldToSwapWithToSwapPlayer == toSwap.getPlayer())
					{
					}
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
	
	private Type getSpecificType(String object, Player player)
	{
		switch(object)
		{
		case "PLAYER":	return player == Player.ONE ? Type.P1_RIGHT : player == Player.TWO ? Type.P2_RIGHT : null;
		case "BOX":		return player == Player.ONE ? Type.P1_BOX : player == Player.TWO ? Type.P2_BOX : null;
		case "BOXED":	return player == Player.ONE ? Type.P1_BOXED : player == Player.TWO ? Type.P2_BOXED : null;
		case "CROSS":	return player == Player.ONE ? Type.P1_CROSS : player == Player.TWO ? Type.P2_CROSS : null;
		}
		return null;
	}
}

