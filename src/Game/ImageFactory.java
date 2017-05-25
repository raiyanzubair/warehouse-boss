package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageFactory
{
	private static final int DESIRED_HEIGHT = 50;
	public static enum Player
	{
		NONE,
		ONE,
		TWO
	}
	public static enum Type
	{
		EMPTY,
		BRICK,
		SHADOW,
		BOX,
		P1_BOX,
		P1_BOXED,
		P1_CROSS,
		P1_DOWN,
		P1_UP,
		P1_LEFT,
		P1_RIGHT,
		P2_BOX,
		P2_BOXED,
		P2_CROSS,
		P2_DOWN,
		P2_UP,
		P2_LEFT,
		P2_RIGHT,
	}
	private static Player[] playerMap = 
	{
		Player.NONE,
		Player.NONE,
		Player.NONE,
		Player.NONE,
		Player.ONE,
		Player.ONE,
		Player.ONE,
		Player.ONE,
		Player.ONE,
		Player.ONE,
		Player.ONE,
		Player.TWO,
		Player.TWO,
		Player.TWO,
		Player.TWO,
		Player.TWO,
		Player.TWO,
		Player.TWO,
	};
	private static String[] images =
	{
		"Empty.png",
		"Brick.png",
		"Shadow.png",
		"Box.png",
		"P1_Box.png",
		"P1_Boxed.png",
		"P1_Cross.png",
		"P1_Down.png",
		"P1_Up.png",
		"P1_Left.png",
		"P1_Right.png",
		"P2_Box.png",
		"P2_Boxed.png",
		"P2_Cross.png",
		"P2_Down.png",
		"P2_Up.png",
		"P2_Left.png",
		"P2_Right.png",
	};

	public static final ImageIcon[] puzzleIcons = loadImages();
	
	//Images for the title menu panel
	public static final ImageIcon topWall = new ImageIcon("src/menu/topwall.jpg");
	public static final ImageIcon bottomWall = new ImageIcon("src/menu/bottomwall.jpg");

	//Images for the tutorial panel
	
	public static final ImageIcon tutorialGif = new ImageIcon("src/tutorial/tutorial.gif");
	public static final ImageIcon undoGif = new ImageIcon("src/tutorial/UndoButton.gif");
	public static final ImageIcon resetGif = new ImageIcon("src/tutorial/ResetButton.gif");
	
	static class Colors
	{
		public static final Color customOrange = new Color(255, 165, 96);
	}

	/**
	 * Initializes the images array with the ImageIcons from the filenames
	 */
 	private static ImageIcon[] loadImages()
	{
 		ImageIcon[] icons = new ImageIcon[images.length];
		for(Type t : Type.values())
		{
			int index = t.ordinal();
			icons[index] =loadIcon(images[index]);
		}
		return icons;
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

}

