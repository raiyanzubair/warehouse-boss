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
	
	/**
	 * Creates a JPanel object with an empty grid structure with the specified rows and columns
	 * @param grid: PuzzleGrid object that holds information on rows and columns in the puzzle
	 * @return panel: The JPanel with the grid
	 */
	public PuzzleDisplayPanel(int rows, int columns)
	{
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
	public void reloadPanelLabels(ArrayList<PuzzleLabel> grid)
	{
		this.grid.removeAll();
		for(PuzzleLabel lbl : grid)
		{
			lbl.setImageIcon();
			this.grid.add(lbl);
		}
		this.grid.updateUI();
		this.updateUI();
	}
	
}

