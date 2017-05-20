package Game;

import java.util.ArrayList;

import Game.ImageFactory.Type;

/**
 * This class manages the generation of puzzles defined as an array of 'Type' enums
 * that represent each different type of object in the game. Each object type is also
 * linked to an image filename which is loaded at the start, and then an array of
 * ImageIcons is initialized that can be accessed by index from Type.Ordinal()
 * @field DESIRED_HEIGHT: The preset height of images loaded
 * @field images: An array of the names of the image filenames
 * @field icons: An array of the icon images for each image filename
 */
public class PuzzleGridGenerator
{
	private ArrayList<PuzzleGrid> levels;

	public PuzzleGridGenerator()
	{
		this.levels = new ArrayList<PuzzleGrid>();
		this.populateLevels();
	}
	
	public int getNumberOfLevels()
	{
		return levels.size();
	}
	
	/**
	 * Creates a puzzle grid object with an array of image types to place in the
	 * puzzle and the dimensions to arrange the images in a grid of the form
	 * rows x columns.
	 * @return: The puzzle grid with the puzzle starting state properties
	 */
	public PuzzleGrid generatePuzzleGrid(int ID)
	{
		PuzzleGrid level = levels.stream().filter(l -> l.getLevelID() == ID).findFirst().orElse(null);
		return level;
	}
	
	/**
	 * @return: A default map layout
	 */
	public void populateLevels()
	{
		int numLevels = 0;
		levels.add(numLevels++, new PuzzleGrid(0, 8, 6, new Type[]
		{
			Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY,
			Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.EMPTY,
			Type.BRICK, 	Type.MANDOWN,	Type.BOX, 		Type.EMPTY, 	Type.BRICK, 	Type.EMPTY,
			Type.BRICK, 	Type.BRICK, 	Type.BOX, 		Type.EMPTY, 	Type.BRICK, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.EMPTY,		Type.BOX, 		Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.CROSS, 	Type.BOX, 		Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.CROSS, 	Type.CROSS, 	Type.GREENBOX, 	Type.CROSS, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,
		}));
	
		levels.add(numLevels++, new PuzzleGrid(1, 12, 7, new Type[]
		{
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.CROSS, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK,
			Type.CROSS, 	Type.EMPTY,		Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.CROSS,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.CROSS, 	Type.BOX,		Type.BOX, 		Type.BOX, 		Type.CROSS, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK,
			Type.BRICK, 	Type.CROSS, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.CROSS, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.MANDOWN, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,
		}));
	
		levels.add(numLevels++, new PuzzleGrid(2, 12, 7, new Type[]
		{
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.CROSS, 	Type.EMPTY, 	Type.CROSS, 	Type.BRICK, 	Type.BRICK,
			Type.BRICK, 	Type.GREENBOX, 	Type.GREENBOX, 	Type.EMPTY, 	Type.BOX, 		Type.GREENBOX, 	Type.BRICK,
			Type.BRICK, 	Type.CROSS,		Type.BOX, 		Type.BOX, 		Type.BOX, 		Type.CROSS, 	Type.BRICK,
			Type.BRICK, 	Type.GREENBOX, 	Type.CROSS, 	Type.EMPTY, 	Type.EMPTY, 	Type.GREENBOX, 	Type.BRICK,
			Type.BRICK, 	Type.CROSS, 	Type.BOX,		Type.BOX, 		Type.BOX, 		Type.CROSS, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.MANDOWN, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,
		}));
	
		levels.add(numLevels++, new PuzzleGrid(3, 12, 12, new Type[]
		{
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.CROSS, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.BRICK,		Type.BRICK, 	Type.CROSS, 	Type.BOX, 		Type.CROSS, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.CROSS, 	Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.CROSS, 	Type.EMPTY, 	Type.EMPTY, 	Type.BOX, 		Type.EMPTY, 	Type.CROSS, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BOX, 		Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,
			Type.BRICK, 	Type.EMPTY, 	Type.EMPTY, 	Type.MANDOWN, 	Type.EMPTY, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK,
			Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 	Type.BRICK, 
		}));
	}
}
