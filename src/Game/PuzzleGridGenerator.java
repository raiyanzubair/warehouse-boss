package Game;

import java.util.ArrayList;

import Game.ImageFactory.Type;

/**
 * This class stores PuzzleGrid objects which are essentially puzzles defined as an array of 'Type' enums
 * that represent each different type of object in the game. 
 * @field shadowMode: boolean to signal if shadowMode is enabled
 * @field singlePlayerLevels: ArrayList of single player PuzzleGrid objects
 * @field multiPlayerLevels:  ArrayList of multiplayer PuzzleGrid objects
 */
public class PuzzleGridGenerator
{
	public static boolean shadowMode;
	private ArrayList<PuzzleGrid> singlePlayerLevels;
	private ArrayList<PuzzleGrid> multiPlayerLevels;

	public PuzzleGridGenerator()
	{
		this.singlePlayerLevels = new ArrayList<PuzzleGrid>();
		this.multiPlayerLevels = new ArrayList<PuzzleGrid>();
		this.populateSinglePlayerLevels();
		this.populateMultiPlayerLevels();
	}
	
	public int getNumberOfSinglePlayerLevels()
	{
		return singlePlayerLevels.size();
	}
	
	public int getNumberOfMultiPlayerLevels()
	{
		return multiPlayerLevels.size();
	}
	
	/**
	 * Returns a single player PuzzleGrid object with a given level ID
	 * @return: PuzzleGrid of the single player level
	 */
	public PuzzleGrid getLevel(int ID)
	{
		PuzzleGrid level = singlePlayerLevels.stream().filter(l -> l.getLevelID() == ID).findFirst().orElse(null);
		return level;
	}
	
	/**
	 * Returns a multiplayer PuzzleGrid object with a given level ID
	 * @return: PuzzleGrid of the multiplayer level
	 */
	public PuzzleGrid getMultiLevel(int ID) {
		PuzzleGrid level = multiPlayerLevels.stream().filter(l -> l.getLevelID() == ID).findFirst().orElse(null);
		return level;
	}
	
	/**
	 * Method that fills the ArrayList<PuzzleGrid> singlePlayerLevels with hard coded levels
	 */
	public void populateSinglePlayerLevels()
	{
		int numLevels = 0;
		singlePlayerLevels.add(numLevels++, new PuzzleGrid(false, 0, 8, 6, new Type[]
		{
			Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY,
			Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.EMPTY,
			Type.BRICK, 	Type.P1_RIGHT,	Type.BOX, 		Type.EMPTY, 	Type.BRICK, 	Type.EMPTY,
			Type.BRICK, 	Type.BRICK, 	Type.BOX, 		Type.EMPTY, 	Type.BRICK, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.EMPTY,		Type.BOX, 		Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.P1_CROSS, 	Type.BOX, 		Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.P1_CROSS, 	Type.P1_CROSS, 	Type.P1_BOXED, 	Type.P1_CROSS, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK
		}));
		
		singlePlayerLevels.add(numLevels++, new PuzzleGrid(false, 1, 8, 9, new Type[]
		{
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,  
			Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.P1_RIGHT,	Type.P1_CROSS,	Type.P1_CROSS,	Type.P1_CROSS,	Type.BRICK, 	Type.BRICK,  
			Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK,  
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BOX, 		Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,  
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 
			Type.BRICK, 	Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.BRICK, 	Type.BOX, 		Type.BRICK, 	Type.EMPTY, 	Type.BRICK,  
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK
		}));
	
		singlePlayerLevels.add(numLevels++, new PuzzleGrid(false, 2, 12, 7, new Type[]
		{
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.P1_CROSS, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK,
			Type.P1_CROSS, 	Type.EMPTY,		Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.P1_CROSS,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.P1_CROSS, 	Type.BOX,		Type.BOX, 		Type.BOX, 		Type.P1_CROSS, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK,
			Type.BRICK, 	Type.P1_CROSS, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.P1_CROSS, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.P1_RIGHT, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK
		}));
	
		singlePlayerLevels.add(numLevels++, new PuzzleGrid(false, 3, 12, 7, new Type[]
		{
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.P1_CROSS, 	Type.EMPTY, 	Type.P1_CROSS, 	Type.BRICK, 	Type.BRICK,
			Type.BRICK, 	Type.P1_BOXED, 	Type.P1_BOXED, 	Type.EMPTY, 	Type.BOX, 		Type.P1_BOXED, 	Type.BRICK,
			Type.BRICK, 	Type.P1_CROSS,	Type.BOX, 		Type.BOX, 		Type.BOX, 		Type.P1_CROSS, 	Type.BRICK,
			Type.BRICK, 	Type.P1_BOXED, 	Type.P1_CROSS, 	Type.EMPTY, 	Type.EMPTY, 	Type.P1_BOXED, 	Type.BRICK,
			Type.BRICK, 	Type.P1_CROSS, 	Type.BOX,		Type.BOX, 		Type.BOX, 		Type.P1_CROSS, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.P1_RIGHT, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,
		}));
	
		singlePlayerLevels.add(numLevels++, new PuzzleGrid(false, 4, 12, 12, new Type[]
		{
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.P1_CROSS, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.BRICK,		Type.BRICK, 	Type.P1_CROSS, 	Type.BOX, 		Type.P1_CROSS, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.P1_CROSS, 	Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.P1_CROSS, 	Type.EMPTY, 	Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.P1_CROSS, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BOX, 		Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.P1_RIGHT, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 
		}));
		
		singlePlayerLevels.add(numLevels++, new PuzzleGrid(false, 5, 8, 8, new Type[]
		{		
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.P1_CROSS,	Type.BRICK, 	Type.BRICK, 	Type.BRICK,  
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BOX, 		Type.BRICK, 	Type.BRICK, 	Type.BRICK, 
			Type.BRICK, 	Type.P1_CROSS,	Type.EMPTY, 	Type.BOX, 		Type.P1_RIGHT,	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BOX, 		Type.EMPTY, 	Type.BOX, 		Type.P1_CROSS,	Type.BRICK,  
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.P1_CROSS,	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,  
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK	
		}));
		
		singlePlayerLevels.add(numLevels++, new PuzzleGrid(false, 6, 6, 10, new Type[]
		{
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,  
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 
			Type.BRICK, 	Type.BRICK, 	Type.P1_CROSS,	Type.EMPTY, 	Type.BOX, 		Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 
			Type.BRICK, 	Type.P1_CROSS,	Type.P1_CROSS,		Type.BOX, 	Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.EMPTY, 	Type.P1_RIGHT,	Type.BRICK, 
			Type.BRICK, 	Type.P1_CROSS,	Type.P1_CROSS,		Type.EMPTY, Type.BOX, 		Type.EMPTY,		Type.BOX, 		Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK
		}));
		
		singlePlayerLevels.add(numLevels++, new PuzzleGrid(false, 7, 8, 8, new Type[]
		{
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 
			Type.BRICK, 	Type.BRICK, 	Type.P1_CROSS, 	Type.BRICK,		Type.P1_CROSS,	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 
			Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.BRICK, 	Type.P1_CROSS,	Type.BRICK, 	Type.BRICK, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.BRICK, 	Type.BOX, 		Type.P1_CROSS,	Type.BRICK, 	Type.BRICK, 
			Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.BOX, 		Type.EMPTY,	 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.BOX, 		Type.BOX, 		Type.EMPTY, 	Type.BRICK, 
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.P1_RIGHT,	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK
		}));
		
		singlePlayerLevels.add(numLevels++, new PuzzleGrid(false, 8, 7, 9, new Type[]
		{
			Type.BRICK,		Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 
			Type.BRICK,		Type.BRICK, 	Type.EMPTY, 	Type.P1_CROSS, 	Type.BRICK, 	Type.P1_CROSS, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 
			Type.BRICK,		Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.EMPTY,		Type.EMPTY,		Type.BOX, 		Type.EMPTY, 	Type.BRICK,  
			Type.BRICK,		Type.P1_RIGHT, 	Type.BOX,		Type.P1_CROSS, 	Type.EMPTY,		Type.P1_CROSS, 	Type.BOX, 		Type.EMPTY, 	Type.BRICK,  
			Type.BRICK,		Type.EMPTY, 	Type.BOX,		Type.P1_CROSS, 	Type.EMPTY,		Type.P1_CROSS,	Type.BOX, 		Type.EMPTY, 	Type.BRICK, 
			Type.BRICK,		Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 
			Type.BRICK,		Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK
		}));	
		
		singlePlayerLevels.add(numLevels++, new PuzzleGrid(false, 9, 7, 8, new Type[]
		{
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY,	 	Type.BRICK, 	Type.BRICK, 
			Type.BRICK, 	Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.BOX, 		Type.BOX, 		Type.EMPTY, 	Type.BRICK, 
			Type.BRICK, 	Type.P1_CROSS,	Type.P1_CROSS,	Type.P1_CROSS,	Type.P1_CROSS,	Type.P1_CROSS,	Type.P1_CROSS,	Type.BRICK, 
			Type.BRICK, 	Type.EMPTY, 	Type.BOX, 		Type.BOX, 		Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.BRICK, 
			Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.P1_RIGHT,	Type.EMPTY, 	Type.EMPTY,	 	Type.BRICK, 
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,		Type.BRICK, 	Type.BRICK, 	Type.BRICK		
		}));
	}

	/**
	 * Method that fills the ArrayList<PuzzleGrid> multiPlayerLevels with hard coded levels
	 */
	public void populateMultiPlayerLevels()
	{
		int numLevels = 0;
		multiPlayerLevels.add(numLevels++, new PuzzleGrid(true, 0, 9, 11, new Type[]
		{
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,		Type.BRICK,  	Type.BRICK,	 
			Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY,	 	Type.BRICK,  	Type.BRICK,	
			Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.P1_BOX, 	Type.P1_BOX, 	Type.P1_BOX, 	Type.P2_BOX, 	Type.P1_BOX, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 
			Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.P2_BOX, 	Type.EMPTY, 	Type.P2_BOX, 	Type.EMPTY, 	Type.P2_BOX, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.P1_BOX, 	Type.EMPTY, 	Type.P1_RIGHT, 	Type.EMPTY, 	Type.P2_RIGHT, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.P2_BOX,	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK,	
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.P1_CROSS, 	Type.P1_CROSS, 	Type.P1_CROSS, 	Type.P1_CROSS, 	Type.P1_CROSS, 	Type.BRICK,	 	Type.BRICK,	
			Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.P2_CROSS, 	Type.P2_CROSS, 	Type.P2_CROSS, 	Type.P2_CROSS, 	Type.P2_CROSS, 	Type.BRICK,	 	Type.BRICK,			
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,	 	Type.BRICK,			
		}));
		
		multiPlayerLevels.add(numLevels++, new PuzzleGrid(true, 1, 10, 12, new Type[]
		{
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,		Type.BRICK,  	Type.BRICK,	 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY,	 	Type.BRICK,  	Type.BRICK,		Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.P1_BOX, 	Type.EMPTY, 	Type.P1_CROSS, 	Type.P1_CROSS, 	Type.P1_CROSS, 	Type.P1_CROSS, 	Type.BRICK, 	Type.EMPTY, 	Type.BRICK,		Type.BRICK,
			Type.BRICK, 	Type.P1_RIGHT, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 	Type.P2_CROSS, 	Type.P2_CROSS, 	Type.P2_BOXED, 	Type.P2_CROSS, 	Type.EMPTY, 	Type.EMPTY,		Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.P2_BOX,	Type.EMPTY, 	Type.EMPTY, 	Type.P2_BOX, 	Type.BRICK, 	Type.BRICK, 	Type.P1_BOX, 	Type.BRICK, 	Type.EMPTY,		Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.P1_BOX, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.P2_BOX, 	Type.EMPTY,	 	Type.P2_RIGHT,	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.P1_BOX, 	Type.BRICK,	 	Type.EMPTY,		Type.BRICK,	
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY,	 	Type.EMPTY,		Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,	 	Type.BRICK,		Type.BRICK,
		}));
	}
}

