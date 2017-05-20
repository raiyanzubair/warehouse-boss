package Game;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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
	private static final int DESIRED_HEIGHT = 50;
	public static enum Type
	{
		EMPTY,
		BRICK,
		BOX,
		GREENBOX,
		CROSS,
		MANDOWN,
		MANUP,
		MANLEFT,
		MANRIGHT
	}
	private static String[] images =
	{
		"Empty.png",
		"Brick.png",
		"Box.png",
		"GreenBox.png",
		"Cross.png",
		"ManDown.png",
		"ManUp.png",
		"ManLeft.png",
		"ManRight.png"
	};
	public static ImageIcon[] icons = new ImageIcon[images.length];
	private ArrayList<PuzzleGrid> levels;

	public PuzzleGridGenerator()
	{
		loadImages();
		this.levels = new ArrayList<PuzzleGrid>();
		this.populateLevels();
	}
	
	/**
	 * Initializes the images array with the ImageIcons from the filenames
	 */
 	private static void loadImages()
	{
		for(Type t : Type.values())
		{
			int index = t.ordinal();
			icons[index] =loadIcon(images[index]);
		}
	}

 	/**
 	 * Converts an image file loaded from a filename into an ImageIcon
 	 * @param filename: The name of the file to convert
 	 * @return: The converted ImageIcon
 	 */
	private static ImageIcon loadIcon(String filename)
	{
		return new ImageIcon(resizeImage(filename));
	}

	/**
	 * Loads an image of the given filename and resizes its dimensions
	 * to have the same height as DESIRED_HEIGHT
	 * @param filename: The name of the image file to load and resize
	 * @return: The resized image
	 */
	private static BufferedImage resizeImage(String filename)
	{
		BufferedImage originalImage = loadImage(filename);

		int originalHeight = originalImage.getHeight();
		int originalWidth = originalImage.getWidth();
		int newHeight = DESIRED_HEIGHT;
		int newWidth = newHeight * (originalWidth/originalHeight);

		BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resizedImage.createGraphics();

		g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
		g.dispose();

		return resizedImage;
	}

	/**
	 * Looks for the image with the given filename in the Icons74 directory
	 * and returns the image as a BufferedImage
	 * @param filename: The name of the file to load
	 * @return: The loaded BufferedImage
	 */
	private static BufferedImage loadImage(String filename)
	{
		BufferedImage bimg = null;
		try
		{
			bimg = ImageIO.read(new File("src/icons74/"+filename));
		}
		catch (IOException e)
		{
			Logger.getLogger(PuzzleLabel.class.getName()).log(Level.SEVERE, null, e);
		}
		return bimg;
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
