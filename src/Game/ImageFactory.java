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
	public static final ImageIcon[] puzzleIcons = loadImages();
	public static final ImageIcon topWall = new ImageIcon("src/menu/topwall.jpg");
	public static final ImageIcon bottomWall = new ImageIcon("src/menu/bottomwall.jpg");

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
