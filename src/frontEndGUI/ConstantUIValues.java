package frontEndGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

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
		
		ImageIcon backIcon = null;
		
		try {
			URL backImagePath = ConstantUIValues.class.getResource("assets/back.png");
			BufferedImage backImage = ImageIO.read(backImagePath);
			backIcon = new ImageIcon(backImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
