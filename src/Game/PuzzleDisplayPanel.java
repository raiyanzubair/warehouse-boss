package Game;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PuzzleDisplayPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel grid;
	private int cols;
	
	/**
	 * Creates a JPanel object with an empty grid structure with the specified rows and columns
	 * @param grid: PuzzleGrid object that holds information on rows and columns in the puzzle
	 * @return panel: The JPanel with the grid
	 */
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
