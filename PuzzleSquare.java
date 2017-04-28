import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PuzzleSquare extends JLabel
{
	private static final long serialVersionUID = 1L;
	private static final int DESIRED_HEIGHT = 50;
	private Type type;
	private ImageIcon icon;
	
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
	private static ImageIcon[] icons = new ImageIcon[images.length];
	private static boolean initialized = false;
	
	public enum Type
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
	
	public PuzzleSquare(Type type)
	{		
		if(!initialized)
		{
			loadImages();
			initialized = true;
		}
		
		this.type = type;
		this.setIcon(icons[type.ordinal()]);
	}
	
	private void loadImages()
	{
		for(Type t : Type.values())
		{
			int index = t.ordinal();
			icons[index] =loadIcon(images[index]); 
		}
	}
	
	private ImageIcon loadIcon(String filename)
	{
		return new ImageIcon(resizeImage(filename));
	}
	
	private BufferedImage resizeImage(String filename)
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
	
	private BufferedImage loadImage(String filename)
	{
		BufferedImage bimg = null;
		try
		{
			bimg = ImageIO.read(new File("Icons74/"+filename));
		}
		catch (IOException e)
		{
			Logger.getLogger(PuzzleInstance.class.getName()).log(Level.SEVERE, null, e);
		}
		return bimg;
	}

	
	public void setImage(Type type)
	{
		this.icon = icons[type.ordinal()];
		this.setIcon(icon);
	}
	
	public void setType(Type type)
	{
		this.type = type;
	}
	
	public void setTypeAndImage(Type type)
	{
		setType(type);
		setImage(type);
	}
	
	public boolean isType(Type type)
	{
		return this.type == type;
	}
}