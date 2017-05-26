package Game;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * 
 * @field serialVersionID: needed to prevent bugs when extending JPanels
 * @field grid: JPanel object that displays the map
 * @field cols: number of columns
 */
public class PuzzleDisplayPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel grid;
	private int cols;
	
	public PuzzleDisplayPanel(int rows, int columns)
	{
		this.cols = columns;
		
		grid = new JPanel();
		grid.setBorder(BorderFactory.createLineBorder(Color.black));
		grid.setLayout(new GridLayout(rows, columns, 0, 0));		
		
		this.add(grid);
		this.setBackground(ImageFactory.Colors.customOrange);
		this.setFocusable(true);
	}
	
	/**
	 * Reloads the puzzle squares from the labels array so that the ordering of the labels in the
	 * panel matches the new order of puzzle squares in the labels array list.
	 * @param grid: ArrayList of PuzzleLabels that represents the grid
	 * @param shadowMode: boolean for if shadowmode is enabled
	 */
	public void reloadPanelLabels(ArrayList<PuzzleLabel> grid, boolean shadowMode)
	{
		int index = 0;
		this.grid.removeAll();
		for(PuzzleLabel lbl : grid)
		{
			lbl.setImageIcon();				
			if(shadowMode && !isWithinSight(grid,index++))
			{
				lbl.setToShadow();
			}
			this.grid.add(lbl);
		}
		this.grid.updateUI();
		this.updateUI();
	}
	
	/**
	 * Method used in shadowMode to only display grids which are within a certain sight radius of the player
	 * @param list: ArrayList of PuzzleLabels that represents the grid
	 * @param index: number used in displaying shadowmode
	 * @return boolean value for if a PuzzleLabel is within the sight radius of the player
	 */
	private boolean isWithinSight(ArrayList<PuzzleLabel> list, int index)
	{
		for(int j = -cols; j <= cols; j+= cols)
		{
			for(int i = -1; i <= 1; i++)
			{
				int listIndex = index+i+j;
				if(listIndex < 0 || listIndex >= list.size() || index % cols == 0 && listIndex % cols == cols-1 || index % cols == cols-1 && listIndex % cols == 0)
				{
					continue;
				}
				
				PuzzleLabel piece = list.get(listIndex);
				if(piece.isPlayer())
				{
					return true;
				}
			}
		}
		return false;
	}

}
