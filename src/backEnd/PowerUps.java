package backEnd;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import frontEndGUI.ConstantUIValues;

public class PowerUps {
	Conversion convert;
	
//Global Variables
	//coinsPerClick Global Variables
	private JButton increaseCoinValue;
	int powerUpOnePrice = 300;
	int powerUpOneLevel = 1;
	boolean powerUpOneMaxLevel = false;
	
	//addPassiveCoinIncome Global Variables
	private JButton addPassiveCoin;
	int powerUpTwoPrice = 10000;
	int powerUpTwoLevel = 1;
	boolean powerUpTwoMaxLevel = false;
	
//Constructor
	public PowerUps(Player player) {
		convert = new Conversion(player);
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
	
	//addPassiveCoinIncome Variables
	public JButton addPassiveCoinIncome() {
		return addPassiveCoin;
	}
	
	public int powerUpTwoPrice() {
		return powerUpTwoPrice;
	}
	
	public boolean powerUpTwoMaxLevel() {
		return powerUpTwoMaxLevel;
	}
	
	//coinsPerClick Method
	public void coinsPerClick(JPanel powerUps, Player player) {
		increaseCoinValue = new JButton("Coin +1 / click (" + powerUpOnePrice + " coins)");
		increaseCoinValue.setEnabled(false);
		increaseCoinValue.setFocusable(false);
		increaseCoinValue.setFocusPainted(false);
		increaseCoinValue.setBackground(ConstantUIValues.buttonDisabled);
		
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
					powerUpOnePrice += (powerUpOnePrice / 2) * powerUpOneLevel;

					convert.coinConvert();
					
					player.clickerLabel.setText("CLICK HERE FOR COINS! (+" + player.perClick + ")");
					increaseCoinValue.setText("Coin +1 / click (" + powerUpOnePrice + " coins)");
					increaseCoinValue.setEnabled(player.clickCount >= powerUpOnePrice);
					
					if (increaseCoinValue.isEnabled()) {
						increaseCoinValue.setBackground(ConstantUIValues.buttonReady);
					} else increaseCoinValue.setBackground(ConstantUIValues.buttonDisabled);
				}
				
				if (powerUpOneLevel == 10) {
					powerUpOneMaxLevel = true;
					increaseCoinValue.setText("MAX LEVEL");
					increaseCoinValue.setEnabled(false);
					increaseCoinValue.setBackground(ConstantUIValues.buttonDisabled);
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
		addPassiveCoin.setBackground(ConstantUIValues.buttonDisabled);
		
		Font currentPowerUpFont = addPassiveCoin.getFont();
		Font newPowerUpFont = currentPowerUpFont.deriveFont(
				currentPowerUpFont.getSize() + 10f);
		addPassiveCoin.setFont(newPowerUpFont);
		
		addPassiveCoin.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		addPassiveCoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (player.clickCount >= powerUpTwoPrice && !powerUpTwoMaxLevel) {
					powerUpTwoLevel++;
					player.passiveIncome += 5;
					player.clickCount -= powerUpTwoPrice;
					powerUpTwoPrice += (powerUpTwoPrice / 2) * powerUpTwoLevel;

					convert.coinConvert();
					
					addPassiveCoin.setText("+5 coins / 10 seconds (" + powerUpTwoPrice + " coins)");
					addPassiveCoin.setEnabled(player.clickCount >= powerUpTwoPrice);
					
					if (addPassiveCoin.isEnabled()) {
						addPassiveCoin.setBackground(ConstantUIValues.buttonReady);
					} else addPassiveCoin.setBackground(ConstantUIValues.buttonDisabled);
				}
				
				if (powerUpTwoLevel == 10) {
					powerUpTwoMaxLevel = true;
					addPassiveCoin.setText("MAX LEVEL");
					addPassiveCoin.setEnabled(false);
					addPassiveCoin.setBackground(ConstantUIValues.buttonDisabled);
				}
			}
		});
		
		powerUps.add(addPassiveCoin);
	}
}
