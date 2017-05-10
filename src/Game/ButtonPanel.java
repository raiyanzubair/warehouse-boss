package Game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	
	public ButtonPanel() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JButton UndoButton = new JButton("Undo (U)");
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(UndoButton, gbc);
		UndoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		JButton ResetButton = new JButton("Reset (R)");
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(ResetButton, gbc);
		ResetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton ExitButton = new JButton("Exit");
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(ExitButton, gbc);
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}
