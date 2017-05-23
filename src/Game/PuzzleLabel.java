package Game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Game.ImageFactory.Player;
import Game.ImageFactory.Type;

/**
 * Represents a single puzzle square which shows a single image according to its type (defined
 * in the PuzzleGridGenerator)
 * @field icon: The ImageIcon that this puzzle label is to display
 * @field type: The type of this puzzle label
 */
public class PuzzleLabel extends JLabel
{
	private static final long serialVersionUID = 1L;
	private Type image;
	private Type type;
	private Player player;

	public PuzzleLabel(Type type, Player player)
	{
		this.player = player;
		setTypeAndImage(type);
	}
	
	public PuzzleLabel(Type type, Type img, Player player)
	{
		this.player = player;
		setType(type);
		setImage(img);
	}
	
	/**
	 * Sets the type of this label and also the image of the label to match the new type
	 * @param type: The new type
	 */
	public void setTypeAndImage(Type type)
	{
		setType(type);
		setImage(type);
	}
	
	/**
	 * Sets only the type of this label to the type passed
	 * @param type: The type passed
	 */
	public void setType(Type type)
	{
		this.type = (type != null) ? type : Type.EMPTY;
	}
	
	public void setImage(Type type)
	{
		this.image = type;
	}

	/**
	 * Sets only the image to the ImageIcon corresponding to the type passed
	 * @param type: The type passed
	 */
	public void setImageIcon()
	{
		if(image != null)
		{
			ImageIcon icon = ImageFactory.puzzleIcons[image.ordinal()];
			this.setIcon(icon);
		}
	}
	
	public void setToShadow()
	{
		ImageIcon icon = ImageFactory.puzzleIcons[Type.SHADOW.ordinal()];
		this.setIcon(icon);
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
	
	/**
	 * Checks whether this label is of the type specified
	 * @param type: The type to check
	 * @return: True if it is that type or false otherwise
	 */
	public boolean isType(Type type, Player player)
	{
		return this.type == type && this.player == player;
	}
	
	public Player otherPlayer()
	{
		return this.player == Player.ONE ? Player.TWO : Player.ONE;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == null)
		{
			return false;
		}
		PuzzleLabel pl = (PuzzleLabel)o;
		return this.type == pl.type && this.image == pl.image && this.player == pl.player;
	}
	
	public PuzzleLabel Clone()
	{
		return new PuzzleLabel(this.type, this.image, this.player);
	}
}
