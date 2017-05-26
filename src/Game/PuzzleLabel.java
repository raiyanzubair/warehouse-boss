package Game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Game.ImageFactory.Player;
import Game.ImageFactory.Type;

/**
 * Represents a single puzzle square which shows a single image according to its type (defined
 * in the PuzzleGridGenerator)
 * @field serialVersionID: needed to prevent bugs when extending JComponents
 * @field image: The ImageIcon that this puzzle label is to display
 * @field type: The type of this puzzle label
 * @field isPlayer: boolean to check if the PuzzleLabel is a player or not
 */
public class PuzzleLabel extends JLabel
{
	private static final long serialVersionUID = 1L;
	private Type image;
	private Type type;
	private boolean isPlayer;

	/**
	 * Constructs a PuzzleLabel from a basic Type enum and assigns it an image
	 * @param type Type enum
	 */
	public PuzzleLabel(Type type)
	{
		this.isPlayer = isPlayer(type);
		setTypeAndImage(type);
	}
	
	/**
	 * Constructor for cloning a puzzle label
	 * @param type: Type enum to be cloned
	 * @param img: Image to be cloned
	 * @param isPlayer: if clone is player or not
	 */
	public PuzzleLabel(Type type, Type img, boolean isPlayer)
	{
		this.isPlayer = isPlayer;
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
	
	/**
	 * Sets a PuzzleLable to a shadow tile
	 */
	public void setToShadow()
	{
		ImageIcon icon = ImageFactory.puzzleIcons[Type.SHADOW.ordinal()];
		this.setIcon(icon);
	}
	
	/**
	 * Returns Player enum from the puzzlelabel
	 * @return: the return player enum
	 */
	public Player getPlayer()
	{
		switch(image)
		{
			case BOX:
			case P1_BOX:
			case P1_BOXED:
			case P1_CROSS:
			case P1_RIGHT:
			case P1_LEFT:
			case P1_UP:
			case P1_DOWN:	return Player.ONE;
			case P2_BOX:
			case P2_BOXED:
			case P2_CROSS:
			case P2_RIGHT:
			case P2_LEFT:
			case P2_UP:
			case P2_DOWN:	return Player.TWO;
			default:	break;
		}
		return Player.NONE;
	}
	
	public Type getType()
	{
		return this.type;
	}
	
	/**
	 * gets the generic type of the current PuzzleLabel, not differentiating between players
	 * @param type: The type being passed
	 * @return Type enum of the PuzzleLabel
	 */
	public Type getGenericType(Type type)
	{
		switch(type)
		{
			case BOX:
			case P1_BOX:
			case P2_BOX: return Type.P1_BOX;
			case P1_BOXED:
			case P2_BOXED: return Type.P1_BOXED;
			case P1_CROSS:
			case P2_CROSS: return Type.P1_CROSS;
			default:	break;
		}
		return this.type;
	}
	
	/**
	 * Checks whether this label is of the type specified
	 * @param type: The type to check
	 * @return: True if it is that type or false otherwise
	 */
	public boolean isType(Type type)
	{
		return this.type == type;
	}
	
	public boolean hasGenericImageType(Type type)
	{
		return getGenericType(this.image) == type;
	}
	
	public boolean isGenericType(Type type)
	{
		return getGenericType(this.type) == type;
	}
		
	private boolean isPlayer(Type t)
	{
		if(t == Type.P1_UP || t == Type.P1_DOWN || t == Type.P1_LEFT || t == Type.P1_RIGHT || 
		   t == Type.P2_UP || t == Type.P2_DOWN || t == Type.P2_LEFT || t == Type.P2_RIGHT	)
		{
			return true;
		}
		return false;
	}
	
	public boolean isPlayer()
	{
		return this.isPlayer;
	}
	
	@Override
	/**
	 * Overriding of equals method for comparing puzzlepanels
	 * @param o: PuzzleLabel object we are comparing to this
	 * @return equals boolean
	 */
	public boolean equals(Object o)
	{
		if(o == null)
		{
			return false;
		}
		PuzzleLabel pl = (PuzzleLabel)o;
		return this.type == pl.type && this.image == pl.image && pl.isPlayer == isPlayer;
	}
	
	public PuzzleLabel Clone()
	{
		return new PuzzleLabel(this.type, this.image, this.isPlayer);
	}
}

