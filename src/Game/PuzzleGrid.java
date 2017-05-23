package Game;

import java.util.ArrayList;

import Game.ImageFactory.Player;
import Game.ImageFactory.Type;

/**
 * Holds the information for an initial state (including the dimensions and the layout of the
 * puzzle as an array of image types) of a puzzle generated by the PuzzleGridGenerator
 * which can be passed to the PuzzlePanel
 * @field rows: The number of rows of the grid
 * @field columns: The number of columns in the grid
 * @field playerLabel: A link to the player piece that is placed somewhere in the grid
 * @field startingLabelTypes: An array of the grid in linear form
 */
public class PuzzleGrid
{
	private int levelID;
	private int rows;
	private int columns;
	private PuzzleLabel playerOne;
	private PuzzleLabel playerTwo;
	private ArrayList<PuzzleLabel> labelSequence;
	private int highScore;

	
	public PuzzleGrid(boolean multiPlayer, int ID, int rows, int columns, Type[] startingLabelTypes)
	{
		this.levelID = ID;
		this.rows = rows;
		this.columns = columns;
		this.playerOne = new PuzzleLabel(Type.MANRIGHT, Player.ONE);
		this.playerTwo = multiPlayer ? new PuzzleLabel(Type.MANRIGHT, Player.TWO) : null;
		this.labelSequence = initializeStartingLabels(startingLabelTypes);
		this.highScore = -1;
	}
	
	public PuzzleGrid(int rows, int columns, ArrayList<PuzzleLabel> labelSequence, PuzzleLabel playerOne, PuzzleLabel playerTwo)
	{
		this.rows = rows;
		this.columns = columns;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.labelSequence = labelSequence;
	}
	
	private ArrayList<PuzzleLabel> initializeStartingLabels(Type[] startingLabelTypes)
	{
		ArrayList<PuzzleLabel> labels = new ArrayList<PuzzleLabel>();
		
		for(Type type : startingLabelTypes)
		{
			switch(type) 
			{
				case MANRIGHT:	addNextPlayer(labels);				break;
				default:		labels.add(new PuzzleLabel(type, Player.NONE));	break;
			}
		}
		
		return labels;
	}
	
	private void addNextPlayer(ArrayList<PuzzleLabel> labels)
	{
		if(!labels.contains(playerOne))
		{
			labels.add(playerOne);
		}
		else if(!labels.contains(playerTwo))
		{
			labels.add(playerTwo);
		}
	}
	
	public int getLevelID() 
	{
		return levelID;
	}

	/**
	 * @return: The number of rows for this grid
	 */
	public int getRows()
	{
		return rows;
	}
	
	/**
	 * @return: The number of columns of this grid
	 */
	public int getColumns()
	{
		return columns;
	}
	
	/**
	 * @return The reference for the player object
	 */
	public PuzzleLabel getPlayer(Player playerNumber)
	{
		return playerNumber == Player.ONE ? playerOne : playerNumber == Player.TWO ? playerTwo : null;
	}
	
	/**
	 * Adds a set number of puzzle squares to the labels array list. It adds labels in the order
	 * that they should be displayed starting at the top of the panel and going from left to right
	 * adding the number of labels for each column in that row, then goes to the next row until
	 * it reaches the bottom
	 * @return labels: The array list with the ordered puzzle squares
	 */
	public ArrayList<PuzzleLabel> getLabelSequence()
	{
		return labelSequence;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	
	
}
