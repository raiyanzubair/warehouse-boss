package Game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Game.PuzzleGridGenerator.Type;

/**
 * Represents a single puzzle square which shows a single image according to its type (defined
 * in the PuzzleGridGenerator)
 * @field icon: The ImageIcon that this puzzle label is to display
 * @field type: The type of this puzzle label
 */
public class PuzzleLabel extends JLabel
{
	private static final long serialVersionUID = 1L;
	private ImageIcon icon;
	Type type;

	public PuzzleLabel(Type type)
	{		
		setTypeAndImage(type);
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
	 * Sets only the image to the ImageIcon corresponding to the type passed
	 * @param type: The type passed
	 */
	public void setImage(Type type)
	{
		if(type != null)
		{
			this.icon = PuzzleGridGenerator.icons[type.ordinal()];
			this.setIcon(icon);
		}
	}
	
	/**
	 * Sets only the type of this label to the type passed
	 * @param type: The type passed
	 */
	public void setType(Type type)
	{
		this.type = (type != null) ? type : Type.EMPTY;
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
}
