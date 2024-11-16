package frontEndGUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.swing.*;

import javax.imageio.*;

import java.net.*;

import backEnd.*;

public class CenterPanel {
	
	PopUpDialogs popUp = new PopUpDialogs();
	
	public CenterPanel() {
		
	}
	
	//First Center Panel
	public void mainGameButtons(JPanel centerPanel, Player player, PowerUps powerUp, CardLayout cardLayout) {
		JPanel mainGameButtons = new JPanel(new GridLayout (2, 2, 0, 0));
		
		//Save Game
		JButton saveGame = new JButton("Save");
		saveGame.setFocusable(false);
		saveGame.setFocusPainted(false);
		saveGame.setBackground(ConstantUIValues.buttonReady);
		
		Font saveGameCurrent = saveGame.getFont();
		Font saveGameNew = saveGameCurrent.deriveFont(
				saveGameCurrent.getSize() + 10f);
		saveGame.setFont(saveGameNew);
		
		saveGame.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		saveGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File checkSave = new File("playerFiles/playerData.json");
				
				if (!checkSave.exists()) player.saveGame(player, powerUp);
				else popUp.rewriteSavePrompt(player.window, player, powerUp);
			}
		});
		
		mainGameButtons.add(saveGame);
		
		//Upgrades
		JButton upgrade = new JButton("Upgrades");
		upgrade.setFocusable(false);
		upgrade.setFocusPainted(false);
		upgrade.setBackground(ConstantUIValues.buttonReady);
		
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
			button.setBackground(ConstantUIValues.buttonReady);
			
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
			
			JButton backButton = new JButton();
			backButton.setFocusable(false);
			backButton.setFocusPainted(false);
			backButton.setBackground(ConstantUIValues.buttonReady);
			
			ImageIcon backIcon = null;
			
			try {
				URL backImagePath = getClass().getResource("assets/back.png");
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
			
			powerUps.add(backButton);
			
			powerUp.coinsPerClick(powerUps, player);
			powerUp.addPassiveCoinIncome(powerUps, player);
			
			for (int i = 1; i <= 1; i++) {
				JButton button = new JButton("Button " + Integer.toString(i));
				button.setFocusable(false);
				button.setFocusPainted(false);
				button.setBackground(ConstantUIValues.buttonReady);
				
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
