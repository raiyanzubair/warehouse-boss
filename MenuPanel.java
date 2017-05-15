package Game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	
	public PuzzleGrid selectedLevel;
	
	public MenuPanel (PuzzleGridGenerator psg) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JButton levelOne = new JButton("Level One");
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(levelOne,gbc);
		levelOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedLevel = psg.generatePuzzleGrid();
			}
		});
		
		JButton levelTwo = new JButton("Level Two");
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(levelTwo,gbc);
		levelTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedLevel = psg.generatePuzzleGrid();
			}
		});
		
		JButton levelThree = new JButton("Level Three");
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(levelThree,gbc);
		levelThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedLevel = psg.generatePuzzleGrid();
			}
		});
	}

	public PuzzleGrid getSelectedLevel() {
		return selectedLevel;
	}

	public void setSelectedLevel(PuzzleGrid selectedLevel) {
		this.selectedLevel = selectedLevel;
	}
	
	
}
