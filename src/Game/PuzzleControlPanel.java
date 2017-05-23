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
	
	JLabel moveCounter;
	JButton leftButton;
	JButton rightButton;
	JButton upButton;
	JButton downButton;
	JButton undoButton;
	JButton resetButton;
	JButton exitButton;
	
	public PuzzleControlPanel(PuzzleManager manager, Game g) 
	{	
		this.manager = manager;
		this.setBackground(ImageFactory.Colors.customOrange);	
		this.setLayout(new GridBagLayout());
		
		populateComponents(g);
	}
	
	private void populateComponents(Game g)
	{
		moveCounter = new JLabel("Moves: " + Integer.toString(manager.getnMoves()));
		addGridComponent(moveCounter, 0, 3);
		
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
				manager.handleKeyPress(ke);
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

