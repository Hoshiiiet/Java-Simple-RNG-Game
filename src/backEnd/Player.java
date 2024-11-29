package backEnd;

import java.awt.CardLayout;
import java.awt.Color;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import frontEndGUI.ConstantUIValues;
import frontEndGUI.PopUpDialogs;

public class Player{

	public int gemCount = 0;
	public double clickCount = 0;
	public int perClick = 1;
	public int passiveIncome = 0;
	
	public JFrame window;
	
	public JLabel clickCountLabel;
	public JLabel gemCountLabel;
	public JLabel clickerLabel;
	
	PopUpDialogs popUp = new PopUpDialogs();
	
	public Player() {
		UIManager.put("Button.select", new Color(232, 202, 162));
	}
	
	public void setWindow(JFrame window) {
		this.window = window;
	}
	
	public void saveGame(Player player, PowerUps powerUp) {
		try(PrintWriter writer = new PrintWriter("playerFiles/playerData.json")) {
			writer.println(gemCount + " " + clickCount + " " + perClick + " " + passiveIncome);	
			writer.println(powerUp.powerUpOnePrice + " " + powerUp.powerUpOneLevel + " " + powerUp.powerUpOneMaxLevel);
			writer.println(powerUp.powerUpTwoPrice + " " + powerUp.powerUpTwoLevel + " " + powerUp.powerUpTwoMaxLevel);
			popUp.saveGameSuccess(player.window);
	        System.out.println("Game Saved Successfully!");
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("Error Saving Game!");
	    }
	}
	
	public void loadGame(Player player, PowerUps powerUp, Passives passive, CardLayout cardLayout, JPanel mainPanel) {
	    try(Scanner scanner = new Scanner(new FileReader("playerFiles/playerData.json"))) {	 
	    	String playerLine = scanner.nextLine();
	    	String[] playerValues = playerLine.split(" ");
	    	
	    	player.gemCount = Integer.parseInt(playerValues[0]);
	    	player.clickCount = Double.parseDouble(playerValues[1]);
	    	player.perClick = Integer.parseInt(playerValues[2]);
	    	player.passiveIncome = Integer.parseInt(playerValues[3]);
	    	
	    	for(int i = 1; i <= 2; i++) {
	    		String powerUpLine = scanner.nextLine();
	    		String[] powerUpValues = powerUpLine.split(" ");
	    		
	    		int price = Integer.parseInt(powerUpValues[0]);
	    		int level = Integer.parseInt(powerUpValues[1]);
	    		boolean maxLevel = Boolean.parseBoolean(powerUpValues[2]);
	    		
	    		switch(i) {
	    			case 1:
						JButton increaseCoinValue = powerUp.increaseCoinValue();
						
	    				powerUp.powerUpOnePrice = price;
	    				powerUp.powerUpOneLevel = level;
	    				powerUp.powerUpOneMaxLevel = maxLevel;
						increaseCoinValue.setText("Coin +1 / click (" + price + " coins)");
						
						if (player.clickCount >= powerUp.powerUpOnePrice()
		                		&& !powerUp.powerUpOneMaxLevel()) {
		                	increaseCoinValue.setEnabled(true);
		                	increaseCoinValue.setBackground(ConstantUIValues.buttonReady);
		                } else {
		                	increaseCoinValue.setEnabled(false);
		                	increaseCoinValue.setBackground(ConstantUIValues.buttonDisabled);
		                }						
	    				break;
	    			case 2:
	    				JButton addPassiveCoin = powerUp.addPassiveCoinIncome();
	    				
	    				powerUp.powerUpTwoPrice = price;
	    				powerUp.powerUpTwoLevel = level;
	    				powerUp.powerUpTwoMaxLevel = maxLevel;
						addPassiveCoin.setText("+5 coins / 10 seconds (" + price + " coins)");
						
						if (player.clickCount >= powerUp.powerUpTwoPrice()
		                		&& !powerUp.powerUpTwoMaxLevel()) {
							addPassiveCoin.setEnabled(true);
							addPassiveCoin.setBackground(ConstantUIValues.buttonReady);
		                } else {
		                	addPassiveCoin.setEnabled(false);
		                	addPassiveCoin.setBackground(ConstantUIValues.buttonDisabled);
		                }
	    				break;
	    		}
	    	}
	    	
	    	player.clickerLabel.setText("CLICK HERE FOR COINS! (+" + player.perClick + ")");

	    	Conversion convert = new Conversion(player);
	    	convert.coinConvert();
	    	convert.gemConvert();

			passive.passiveIncome(player);
			cardLayout.show(mainPanel, "inGamePanel");
	    	
        	popUp.loadGameSuccess(player.window);
	        System.out.println("Game Loaded Successfully!");
	    } catch (IOException e) {
	        popUp.loadGameFail(player.window);
	        System.out.println("Error Loading Game!");
	    }
	}

}
