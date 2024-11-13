package backEnd;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class PowerUps {
//Global Variables
	//coinsPerClick Global Variables
	public JButton increaseCoinValue;
	int powerUpOnePrice = 300;
	int powerUpOneLevel = 1;
	boolean powerUpOneMaxLevel = false;
	
	//addPassiveCoinIncome Global Variables
	public JButton addPassiveCoin;
	int powerUpTwoPrice = 10000;
	int powerUpTwoLevel = 1;
	boolean powerUpTwoMaxLevel = false;
	
//Constructor
	public PowerUps() {
		
	}
	
//Getters & Setters
	//coinsPerClick Variables
	public JButton increaseCoinValue() {
		return increaseCoinValue;
	}
	
	public int powerUpOnePrice() {
		return powerUpOnePrice;
	}
	
	public boolean powerUpOneMaxLevel() {
		return powerUpOneMaxLevel;
	}
	
//Class Methods
	//coinsPerClick Method
	public void coinsPerClick(JPanel powerUps, Player player) {
		increaseCoinValue = new JButton("Coin +1 / click (" + powerUpOnePrice + " coins)");
		increaseCoinValue.setEnabled(false);
		increaseCoinValue.setFocusable(false);
		increaseCoinValue.setFocusPainted(false);
		increaseCoinValue.setContentAreaFilled(false);
		
		Font currentPowerUpFont = increaseCoinValue.getFont();
		Font newPowerUpFont = currentPowerUpFont.deriveFont(
				currentPowerUpFont.getSize() + 10f);
		increaseCoinValue.setFont(newPowerUpFont);
		
		increaseCoinValue.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		increaseCoinValue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (player.clickCount >= powerUpOnePrice && !powerUpOneMaxLevel) {
					powerUpOneLevel++;
					player.perClick++;
					player.clickCount -= powerUpOnePrice;
					powerUpOnePrice += (powerUpOnePrice * powerUpOneLevel);
					player.clickCountLabel.setText("Coins: " + player.clickCount);
					player.clickerLabel.setText("CLICK HERE FOR COINS! (+" + player.perClick + ")");
					increaseCoinValue.setText("Coin +1 / click (" + powerUpOnePrice + " coins)");
					increaseCoinValue.setEnabled(player.clickCount >= powerUpOnePrice);
				}
				
				if (powerUpOneLevel == 10) {
					powerUpOneMaxLevel = true;
					increaseCoinValue.setText("MAX LEVEL");
					increaseCoinValue.setEnabled(false);
				}
			}
		});
		
		powerUps.add(increaseCoinValue);
	}
	
	//addPassiveCoinIncome Method
	public void addPassiveCoinIncome(JPanel powerUps, Player player) {
		addPassiveCoin = new JButton("+5 coins / 10 seconds (" + powerUpTwoPrice + " coins)");
		addPassiveCoin.setEnabled(false);
		addPassiveCoin.setFocusable(false);
		addPassiveCoin.setFocusPainted(false);
		addPassiveCoin.setContentAreaFilled(false);
		
		Font currentPowerUpFont = addPassiveCoin.getFont();
		Font newPowerUpFont = currentPowerUpFont.deriveFont(
				currentPowerUpFont.getSize() + 10f);
		addPassiveCoin.setFont(newPowerUpFont);
		
		addPassiveCoin.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		addPassiveCoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (player.clickCount >= powerUpTwoPrice && !powerUpTwoMaxLevel) {
					powerUpOneLevel++;
					player.perClick++;
					player.clickCount -= powerUpOnePrice;
					powerUpTwoPrice += powerUpTwoPrice * powerUpTwoLevel;
					player.clickCountLabel.setText("Coins: " + player.clickCount);
					addPassiveCoin.setText("+5 coins / 10 seconds (" + powerUpTwoPrice + " coins)");
					addPassiveCoin.setEnabled(player.clickCount >= powerUpTwoPrice);
				}
				
				if (powerUpTwoLevel == 10) {
					powerUpTwoMaxLevel = true;
					addPassiveCoin.setText("MAX LEVEL");
					addPassiveCoin.setEnabled(false);
				}
			}
		});
		
		powerUps.add(addPassiveCoin);
	}
}
