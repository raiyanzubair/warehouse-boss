package Game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	
	public ButtonPanel(PuzzlePanel puzzle) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JButton LeftButton = new JButton("Left");
		LeftButton.setFocusable(false);
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(LeftButton, gbc);
		LeftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeyEvent ke = new KeyEvent(puzzle, 1, 0, 0, KeyEvent.VK_LEFT, '\0');
				puzzle.handleKeyPress(ke);
			}
		});
		
		JButton RightButton = new JButton("Right");
		RightButton.setFocusable(false);
		gbc.gridx = 3;
		gbc.gridy = 1;
		this.add(RightButton, gbc);
		RightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeyEvent ke = new KeyEvent(puzzle, 1, 0, 0, KeyEvent.VK_RIGHT, '\0');
				puzzle.handleKeyPress(ke);
			}
		});
		
		JButton UpButton = new JButton("Up");
		UpButton.setFocusable(false);
		gbc.gridx = 2;
		gbc.gridy = 0;
		this.add(UpButton, gbc);
		UpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeyEvent ke = new KeyEvent(puzzle, 1, 0, 0, KeyEvent.VK_UP, '\0');
				puzzle.handleKeyPress(ke);
			}
		});
		
		JButton DownButton = new JButton("Down");
		DownButton.setFocusable(false);
		gbc.gridx = 2;
		gbc.gridy = 2;
		this.add(DownButton, gbc);
		DownButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeyEvent ke = new KeyEvent(puzzle, 1, 0, 0, KeyEvent.VK_DOWN, '\0');
				puzzle.handleKeyPress(ke);
			}
		});
		
		JButton UndoButton = new JButton("Undo (U)");
		UndoButton.setFocusable(false);
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(UndoButton, gbc);
		UndoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				puzzle.reloadLastLabelState();
			}
		});

		JButton ResetButton = new JButton("Reset (R)");
		ResetButton.setFocusable(false);
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(ResetButton, gbc);
		ResetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				puzzle.resetGame();
			}
		});
		
		JButton ExitButton = new JButton("Exit");
		ExitButton.setFocusable(false);
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(ExitButton, gbc);
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
	}
}
