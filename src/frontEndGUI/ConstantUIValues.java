package frontEndGUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public final class ConstantUIValues {

	private ConstantUIValues() {
		
	}
	
	public static final Color buttonReady = new Color(255, 223, 182);
	public static final Color buttonDisabled = new Color(61, 53, 43);
	
	public static final Color mainColor = new Color(255, 180, 0);
	
	public static void backButton(CardLayout cardLayout, JPanel centerPanel, JPanel subPanel) {
		JButton backButton = new JButton();
		backButton.setFocusable(false);
		backButton.setFocusPainted(false);
		backButton.setBackground(ConstantUIValues.buttonReady);
		
		ImageIcon backIcon = new ImageIcon(".//assets//back.png");
		
		backButton.setIcon(backIcon);
		
		backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(centerPanel, "mainGameButtons");
			}
		});
		
		subPanel.add(backButton);
	}
}
