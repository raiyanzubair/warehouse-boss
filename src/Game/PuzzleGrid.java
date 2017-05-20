package Game;

import java.util.ArrayList;
import java.util.Stack;

import Game.PuzzleGridGenerator.Type;

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
	private PuzzleLabel playerLabel;
	private ArrayList<PuzzleLabel> labelSequence;
	private Stack<PuzzleGrid> previousStates;

	
	public PuzzleGrid(int ID, int rows, int columns, Type[] startingLabelTypes)
	{
		this.levelID = ID;
		this.rows = rows;
		this.columns = columns;
		this.playerLabel = new PuzzleLabel(Type.MANDOWN);
		this.labelSequence = initializeStartingLabels(startingLabelTypes);
	}
	
	public PuzzleGrid(int rows, int columns, ArrayList<PuzzleLabel> labelSequence, PuzzleLabel playerLabel)
	{
		this.rows = rows;
		this.columns = columns;
		this.playerLabel = playerLabel;
		this.labelSequence = labelSequence;
	}
	
	private ArrayList<PuzzleLabel> initializeStartingLabels(Type[] startingLabelTypes)
	{
		ArrayList<PuzzleLabel> labels = new ArrayList<PuzzleLabel>();
		
		for(Type type : startingLabelTypes)
		{
			switch(type) 
			{
				case MANDOWN:	labels.add(playerLabel);			break;
				default:		labels.add(new PuzzleLabel(type));	break;
			}
		}
		
		return labels;
	}
	
	public int getLevelID() {
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
	public PuzzleLabel getPlayer()
	{
		return playerLabel;
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

	
}
