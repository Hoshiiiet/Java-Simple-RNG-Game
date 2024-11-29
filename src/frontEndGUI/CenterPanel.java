package frontEndGUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import backEnd.Player;
import backEnd.PowerUps;
import backEnd.GameShopItems;

public class CenterPanel {
	
	PopUpDialogs popUp = new PopUpDialogs();
	
	PowerUps powerUp;
	
	public CenterPanel(PowerUps powerUp) {
		this.powerUp = powerUp;
	}
	
//First Center Panel
	public void mainGameButtons(JPanel centerPanel, Player player, CardLayout cardLayout) throws IOException {
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
		powerUps(centerPanel, player, cardLayout);
		
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
		
		//Shop
		shop(centerPanel, player, cardLayout);
		
		JButton shop = new JButton("Shop");
		shop.setFocusable(false);
		shop.setFocusPainted(false);
		shop.setBackground(ConstantUIValues.buttonReady);
		
		Font shopCurrent = shop.getFont();
		Font shopNew = shopCurrent.deriveFont(
				shopCurrent.getSize() + 10f);
		shop.setFont(shopNew);
		
		shop.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		shop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(centerPanel, "shop");
			}
		});
		
		mainGameButtons.add(shop);
		
		for (int i = 1; i <= 1; i++) {
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
	
//Upgrades Panel
	public void powerUps(JPanel centerPanel, Player player, CardLayout cardLayout) {
		JPanel powerUps = new JPanel(new GridLayout(2, 2, 0, 0));
		
		ConstantUIValues.backButton(cardLayout, centerPanel, powerUps);
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
		
//Shop
	public void shop(JPanel centerPanel, Player player, CardLayout cardLayout) throws IOException {
		
		JPanel shop = new JPanel(new GridLayout(2, 1, 0, 0));
		
		HashMap<String, GameShopItems> items = GameShopItems.loadDefaultShop();
		
		ConstantUIValues.backButton(cardLayout, centerPanel, shop);
		
		for (int i = 1; i <= 13; i++) {
			GameShopItems item = items.get("0013");
			String name = item.itemName;
			int price = item.itemGemPrice;
			JButton button = new JButton(name + " (" + price + " gems)");
			button.setFocusable(false);
			button.setFocusPainted(false);
			button.setBackground(ConstantUIValues.buttonReady);
			
			Font currentFont = button.getFont();
			Font newFont = currentFont.deriveFont(
					currentFont.getSize() + 5f);
			button.setFont(newFont);
			
			button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			shop.add(button);
		}
		
		centerPanel.add(shop, "shop");
	}
}
