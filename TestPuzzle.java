import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TestPuzzle extends JFrame
{
	public enum Type
	{
		EMPTY,
		BRICK,
		BOX,
		GREENBOX,
		CROSS,
		MAN
	}

	private static final long serialVersionUID = 1L;
	private final int DESIRED_HEIGHT = 50;
	private final int ROWS = 8;
	private final int COLUMNS = 6;
	private JPanel panel;
	
	private ImageIcon empty;
	private ImageIcon brick;
	private ImageIcon greenBox;
	private ImageIcon cross;
	private ImageIcon box;
	private ImageIcon manDown;
	private ImageIcon manUp;
	private ImageIcon manRight;
	private ImageIcon manLeft;
	
	Block manLabel;
	private ArrayList<Block> labels;
	
	public TestPuzzle()
	{
		initUI();
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() -> 
		{
			TestPuzzle puzzle = new TestPuzzle();
			puzzle.setVisible(true);
		});
	}
	
	private void initUI()
	{
		labels = new ArrayList<Block>();
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setLayout(new GridLayout(ROWS, COLUMNS, 0, 0));
		add(panel, BorderLayout.CENTER);
		
		loadImages();
		
		manLabel = new Block(Type.MAN);
			
		initializeLabels();
		
		for(Block lbl : labels)
		{
			panel.add(lbl);
		}
				
		pack();
		setTitle("Puzzle");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new KeyAction());
	}

	private void loadImages()
	{
		empty = loadIcon("Empty.png");
		brick = loadIcon("Brick.png");
		greenBox = loadIcon("GreenBox.png");
		cross = loadIcon("Cross.png");
		box = loadIcon("Box.png");
		manDown = loadIcon("ManDown.png");
		manUp = loadIcon("ManUp.png");
		manRight = loadIcon("ManRight.png");
		manLeft = loadIcon("ManLeft.png");
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
			bimg = ImageIO.read(new File("icons74/"+filename));
		}
		catch (IOException e)
		{
			Logger.getLogger(TestPuzzle.class.getName()).log(Level.SEVERE, null, e);
		}
		return bimg;
	}

	private void initializeLabels()
	{
		labels.add(new Block(Type.EMPTY));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.EMPTY));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.EMPTY));
		labels.add(new Block(Type.EMPTY));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.EMPTY));
		labels.add(new Block(Type.BRICK));
		labels.add(manLabel);
		labels.add(new Block(Type.BOX));
		labels.add(new Block(Type.EMPTY));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.EMPTY));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.BOX));
		labels.add(new Block(Type.EMPTY));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.EMPTY));
		labels.add(new Block(Type.BOX));
		labels.add(new Block(Type.EMPTY));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.CROSS));
		labels.add(new Block(Type.BOX));
		labels.add(new Block(Type.EMPTY));
		labels.add(new Block(Type.EMPTY));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.CROSS));
		labels.add(new Block(Type.CROSS));
		labels.add(new Block(Type.GREENBOX));
		labels.add(new Block(Type.CROSS));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.BRICK));
		labels.add(new Block(Type.BRICK));
	}
	
	private class KeyAction implements KeyListener
	{
		@Override
		public void keyTyped(KeyEvent e)
		{
		}

		@Override
		public void keyPressed(KeyEvent e)
		{
			handleKeyPress(e);
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
		}

		private void handleKeyPress(KeyEvent e)
		{
			if(e.getKeyCode() == KeyEvent.VK_R)
			{
				labels.clear();
				initializeLabels();
				updateDisplay();
				return;
			}
			
			int manIndex = labels.indexOf(manLabel);
			int swapIndex = validateKeyArrowDirection(e, manIndex);
			
			if(swapIndex != -1)
			{
				handleSwapObjectBehaviour(e, manIndex, swapIndex);
				updateDisplay();
			}
			
			if(puzzleSolved())
			{
				JOptionPane.showMessageDialog(panel, "Finished", "Congratulation", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		}

		private int validateKeyArrowDirection(KeyEvent e, int manIndex)
		{
			int swapIndex = -1;
			
			int keyCode = e.getKeyCode();		
			if(keyCode == KeyEvent.VK_UP)
			{
				manLabel.setImage(manUp);
				if(manIndex >= COLUMNS)
				{
					swapIndex = manIndex-COLUMNS;
				}
			}
			else if(keyCode == KeyEvent.VK_DOWN)
			{
				manLabel.setImage(manDown);
				if(manIndex <= COLUMNS*(ROWS-1))
				{
					swapIndex = manIndex+COLUMNS;
				}
			}
			else if(keyCode == KeyEvent.VK_LEFT)
			{
				manLabel.setImage(manLeft);
				if(manIndex % COLUMNS != 0)
				{
					swapIndex = manIndex-1;
				}
			}
			else if(keyCode == KeyEvent.VK_RIGHT)
			{
				manLabel.setImage(manRight);
				if(manIndex % COLUMNS != ROWS-1)
				{
					swapIndex = manIndex+1;
				}
			}

			return swapIndex;
		}
		
		private void handleSwapObjectBehaviour(KeyEvent e, int manIndex, int swapIndex)
		{
			Block toSwap = labels.get(swapIndex);
			if(toSwap.isType(Type.BRICK))
			{
				return;
			}
			else if(toSwap.isType(Type.EMPTY))
			{
				if(manLabel.isType(Type.CROSS))
				{
					toSwap.setType(Type.CROSS);
					toSwap.setImage(cross);
				}
				manLabel.setType(Type.MAN);
			}
			else if(toSwap.isType(Type.CROSS))
			{
				if(!manLabel.isType(Type.CROSS))
				{
					toSwap.setType(Type.EMPTY);
					toSwap.setImage(empty);
				}
				manLabel.setType(Type.CROSS);
			}
			else if(toSwap.isType(Type.BOX) || toSwap.isType(Type.GREENBOX))
			{
				int toSwapSwapIndex = validateKeyArrowDirection(e, swapIndex);
				Block toSwapWithToSwap = labels.get(toSwapSwapIndex);
				if(toSwapWithToSwap.isType(Type.EMPTY))
				{
					if(manLabel.isType(Type.CROSS))
					{
						toSwapWithToSwap.setType(Type.CROSS);
						toSwapWithToSwap.setImage(cross);
						manLabel.setType(Type.MAN);
					}
					
					if(toSwap.isType(Type.GREENBOX))
					{
						toSwap.setType(Type.BOX);
						toSwap.setImage(box);
						manLabel.setType(Type.CROSS);
					}
				}
				else if(toSwapWithToSwap.isType(Type.CROSS))
				{
					if(!manLabel.isType(Type.CROSS))
					{
						toSwapWithToSwap.setType(Type.EMPTY);
						toSwapWithToSwap.setImage(empty);
					}
					
					if(toSwap.isType(Type.GREENBOX))
					{
						manLabel.setType(Type.CROSS);
					}
					else if(toSwap.isType(Type.BOX))
					{
						toSwap.setType(Type.GREENBOX);
						toSwap.setImage(greenBox);
						manLabel.setType(Type.MAN);
					}			
				}
				else
				{
					return;
				}
				
				Collections.swap(labels, swapIndex, toSwapSwapIndex);
			}
		
			Collections.swap(labels, manIndex, swapIndex);
		}

		private void updateDisplay()
		{
			panel.removeAll();
			for(Block lbl : labels)
			{
				panel.add(lbl);
			}
			panel.validate();
		}

		private boolean puzzleSolved()
		{
			boolean solved = true;
			for(Block blk : labels)
			{
				if(blk.isType(Type.BOX))
				{
					solved = false;
					break;
				}
			}
			return solved;
		}
	}
	
	private class Block extends JLabel
	{
		private static final long serialVersionUID = 1L;
		private Type type;
		private ImageIcon icon;
		
		public Block(Type type)
		{
			this.type = type;
			switch(type)
			{
				case EMPTY: 		this.icon = empty; 		break;
				case BRICK: 		this.icon = brick; 		break;
				case BOX: 		this.icon = box; 		break;
				case GREENBOX: 	this.icon = greenBox; 	break;
				case CROSS: 		this.icon = cross; 		break;
				case MAN: 		this.icon = manDown; 	break;
			}
			this.setIcon(icon);
		}
		
		private void setImage(ImageIcon icon)
		{
			this.icon = icon;
			this.setIcon(icon);
		}
		
		private void setType(Type type)
		{
			this.type = type;
		}
		
		public boolean isType(Type type)
		{
			return this.type == type;
		}
	}
}


