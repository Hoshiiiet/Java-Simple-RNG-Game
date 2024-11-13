package frontEndGUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import backEnd.*;

public class CenterPanel {
	
	public CenterPanel() {
		
	}
	
	//First Center Panel
	public void mainGameButtons(JPanel centerPanel, Player player, PowerUps powerUp, CardLayout cardLayout) {
		JPanel mainGameButtons = new JPanel(new GridLayout (2, 2, 0, 0));
		
		//Save Game
		JButton saveGame = new JButton("Save");
		saveGame.setFocusable(false);
		saveGame.setFocusPainted(false);
		saveGame.setContentAreaFilled(false);
		
		Font saveGameCurrent = saveGame.getFont();
		Font saveGameNew = saveGameCurrent.deriveFont(
				saveGameCurrent.getSize() + 10f);
		saveGame.setFont(saveGameNew);
		
		saveGame.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		saveGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				player.saveGame(player, powerUp);
			}
		});
		
		mainGameButtons.add(saveGame);
		
		//Upgrades
		JButton upgrade = new JButton("Upgrade");
		upgrade.setFocusable(false);
		upgrade.setFocusPainted(false);
		upgrade.setContentAreaFilled(false);
		
		Font upgradeCurrent = upgrade.getFont();
		Font upgradeNew = upgradeCurrent.deriveFont(
				upgradeCurrent.getSize() + 10f);
		upgrade.setFont(upgradeNew);
		
		upgrade.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		upgrade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(centerPanel, "powerUps");
			}
		});
		
		mainGameButtons.add(upgrade);
		
		for (int i = 1; i <= 2; i++) {
			JButton button = new JButton("Button " + Integer.toString(i));
			button.setFocusable(false);
			button.setFocusPainted(false);
			button.setContentAreaFilled(false);
			
			Font currentFont = button.getFont();
			Font newFont = currentFont.deriveFont(
					currentFont.getSize() + 10f);
			button.setFont(newFont);
			
			button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			mainGameButtons.add(button);
		}
		
		centerPanel.add(mainGameButtons, "mainGameButtons");
	}
	
	//Player Upgrades
		public void powerUps(JPanel centerPanel, Player player, PowerUps powerUp, CardLayout cardLayout) {
			JPanel powerUps = new JPanel(new GridLayout(2, 2, 0, 0));
			
			JButton backButton = new JButton("Back");
			backButton.setFocusable(false);
			backButton.setFocusPainted(false);
			backButton.setContentAreaFilled(false);
			
			Font backCurrent = backButton.getFont();
			Font backNew = backCurrent.deriveFont(
					backCurrent.getSize() + 10f);
			backButton.setFont(backNew);
			
			backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			backButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cardLayout.show(centerPanel, "mainGameButtons");
					System.out.println("Button is pressed");
				}
			});
			
			powerUps.add(backButton);
			
			powerUp.coinsPerClick(powerUps, player);
			powerUp.addPassiveCoinIncome(powerUps, player);
			
			for (int i = 1; i <= 1; i++) {
				JButton button = new JButton("Button " + Integer.toString(i));
				button.setFocusable(false);
				button.setFocusPainted(false);
				button.setContentAreaFilled(false);
				
				Font currentFont = button.getFont();
				Font newFont = currentFont.deriveFont(
						currentFont.getSize() + 5f);
				button.setFont(newFont);
				
				button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				powerUps.add(button);
			}
			
			centerPanel.add(powerUps, "powerUps");
		}
}
