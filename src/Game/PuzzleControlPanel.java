package Game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PuzzleControlPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private PuzzleManager manager;
	private PuzzleGrid grid;
	private boolean multiPlayer;
	
	JLabel moveCounter;
	JLabel highScore;
	JButton leftButton;
	JButton rightButton;
	JButton upButton;
	JButton downButton;
	JButton undoButton;
	JButton resetButton;
	JButton exitButton;
	
	public PuzzleControlPanel(PuzzleManager manager, PuzzleGrid grid, Game g) 
	{	
		this.manager = manager;
		this.multiPlayer = grid.isMultiplayer();
		this.grid = grid;
		this.setBackground(ImageFactory.Colors.customOrange);	
		this.setLayout(new GridBagLayout());

		populateComponents(g);
	}
	
	private void populateComponents(Game g)
	{
		moveCounter = new JLabel("Moves: " + Integer.toString(manager.getnMoves()));
		addGridComponent(moveCounter, 0, 3);
		
		String nScore;
		if (grid.getHighScore() >= 0) {
			nScore = Integer.toString((grid.getHighScore()));
		} else {
			nScore = "Not set yet";
		}
		highScore = new JLabel("High Score: " + nScore);
		addGridComponent(highScore, 0, 4);
		
		leftButton = new JButton("←");
		registerSyntheticKey(leftButton, KeyEvent.VK_LEFT);
		addGridComponent(leftButton, 1, 1);
		
		rightButton = new JButton("→");
		registerSyntheticKey(rightButton, KeyEvent.VK_RIGHT);
		addGridComponent(rightButton, 3, 1);
		
		upButton = new JButton("↑");
		registerSyntheticKey(upButton, KeyEvent.VK_UP);
		addGridComponent(upButton, 2, 0);
		
		downButton = new JButton("↓");
		registerSyntheticKey(downButton, KeyEvent.VK_DOWN);
		addGridComponent(downButton, 2, 2);
		
		if (this.multiPlayer) {
			JButton left2 = new JButton("A");
			registerSyntheticKey(left2, KeyEvent.VK_W);
			addGridComponent(left2, 1, 4);
			
			JButton right2 = new JButton("D");
			registerSyntheticKey(right2, KeyEvent.VK_D);
			addGridComponent(right2, 3, 4);
			
			JButton up2 = new JButton("W");
			registerSyntheticKey(up2, KeyEvent.VK_W);
			addGridComponent(up2, 2, 3);
			
			JButton down2 = new JButton("S");
			registerSyntheticKey(down2, KeyEvent.VK_S);
			addGridComponent(down2, 2, 5);
		}
		
		undoButton = new JButton("Undo (U)");
		undoButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				manager.reloadLastLabelState();
			}
		});
		addGridComponent(undoButton, 0, 0);

		resetButton = new JButton("Reset (R)");
		resetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				manager.resetGame();
				updateMoves(manager.getnMoves());
			}
		});
		addGridComponent(resetButton, 0, 1);

		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				g.showMenuScreen();
			}
		});
		addGridComponent(exitButton, 0, 2);
	}
	
	private void addGridComponent(JComponent component, int gridX, int gridY)
	{
		component.setFocusable(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gridX;
		gbc.gridy = gridY;
		this.add(component, gbc);
	}
	
	private void registerSyntheticKey(JButton button, int keyCode)
	{
		JPanel panel = this;
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				KeyEvent ke = new KeyEvent(panel, 0, 0, 0, keyCode, '\0');
				manager.handleKeyPress(ke, grid);
				updateMoves(manager.getnMoves());
			}
		});
	}
	
	public void updateMoves(int nMoves)
	{
		moveCounter.setText("Moves: " + Integer.toString(nMoves));
		moveCounter.updateUI();
		this.updateUI();
	}
}

