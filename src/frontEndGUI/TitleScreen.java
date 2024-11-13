package frontEndGUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import backEnd.*;

public class TitleScreen {
	
	Passives passive = new Passives();
	Player player = new Player();

	public TitleScreen() {
		
	}
	
	public JButton newGame(JPanel mainPanel, Player player, CardLayout cardLayout) {
		JButton newGame = new JButton("New Game");
		newGame.setFocusable(false);
		newGame.setFocusPainted(false);
		newGame.setContentAreaFilled(false);
		
		Font newGameCurrent = newGame.getFont();
		Font newGameNewFont = newGameCurrent.deriveFont(newGameCurrent.getSize() + 10f);
		newGame.setFont(newGameNewFont);
		
		newGame.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		newGame.setPreferredSize(new Dimension(250, 75));
		
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				passive.passiveIncome(player);
				cardLayout.show(mainPanel, "inGamePanel");
			}
		});
		
		return newGame;
	}
	
	public JButton loadSavedGame(JPanel mainPanel, Player player, CardLayout cardLayout) {
		JButton loadGame = new JButton("Load Game");
		loadGame.setFocusable(false);
		loadGame.setFocusPainted(false);
		loadGame.setContentAreaFilled(false);
		
		Font loadGameCurrent = loadGame.getFont();
		Font loadGameNewFont = loadGameCurrent.deriveFont(loadGameCurrent.getSize() + 10f);
		loadGame.setFont(loadGameNewFont);
		
		loadGame.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		loadGame.setPreferredSize(new Dimension(250, 75));
		
		loadGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				player.loadGame(player);
				passive.passiveIncome(player);
				cardLayout.show(mainPanel, "inGamePanel");
			}
		});
		
		return loadGame;
	}
}
