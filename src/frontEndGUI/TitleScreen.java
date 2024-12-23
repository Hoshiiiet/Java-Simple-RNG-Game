package frontEndGUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import backEnd.GameShopItems;
import backEnd.Passives;
import backEnd.Player;
import backEnd.PowerUps;

public class TitleScreen {
	
	Player player = new Player();
	Passives passive = new Passives();

	public TitleScreen() {
		
	}
	
	public JButton newGame(JPanel mainPanel, Player player, CardLayout cardLayout) {
		JButton newGame = new JButton("New Game");
		newGame.setFocusable(false);
		newGame.setFocusPainted(false);
		
		Font newGameCurrent = newGame.getFont();
		Font newGameNewFont = newGameCurrent.deriveFont(newGameCurrent.getSize() + 10f);
		newGame.setFont(newGameNewFont);
		
		newGame.setBackground(ConstantUIValues.buttonReady);
		newGame.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		newGame.setPreferredSize(new Dimension(250, 75));
		
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				passive.passiveIncome(player);
				GameShopItems.loadDefaultShop();
				cardLayout.show(mainPanel, "inGamePanel");
			}
		});
		
		return newGame;
	}
	
	public JButton loadSavedGame(JPanel mainPanel, PowerUps powerUp, Player player, CardLayout cardLayout) {
		JButton loadGame = new JButton("Load Game");
		loadGame.setFocusable(false);
		loadGame.setFocusPainted(false);
		
		Font loadGameCurrent = loadGame.getFont();
		Font loadGameNewFont = loadGameCurrent.deriveFont(loadGameCurrent.getSize() + 10f);
		loadGame.setFont(loadGameNewFont);
		
		loadGame.setBackground(ConstantUIValues.buttonReady);
		loadGame.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		loadGame.setPreferredSize(new Dimension(250, 75));
		
		loadGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				passive.passiveIncome(player);
				player.loadGame(player, powerUp, passive, cardLayout, mainPanel);
			}
		});
		
		return loadGame;
	}
}
