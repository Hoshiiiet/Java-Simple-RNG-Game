package frontEndGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dialog.ModalityType;

import backEnd.Player;
import backEnd.PowerUps;

public class PopUpDialogs {
	
	public PopUpDialogs() {
		
	}
	
	public void loadGameSuccess(JFrame window) {
		JDialog dialog = new JDialog(window, null, ModalityType.APPLICATION_MODAL);
		dialog.setTitle("Load Game");
		dialog.setResizable(false);
		dialog.setIconImage(new ImageIcon(getClass().getResource("assets/icon.png")).getImage());
		
		JLabel message = new JLabel("Game Loaded Successfully!");
		message.setFont(new Font("Arial", Font.PLAIN, 27));
		message.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel dialogPanel = new JPanel();
		dialogPanel.setBackground(ConstantUIValues.mainColor);
		dialogPanel.add(message);
		
		dialog.add(dialogPanel);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
	
	public void loadGameFail(JFrame window) {
		JDialog dialog = new JDialog(window, null, ModalityType.APPLICATION_MODAL);
		dialog.setTitle("Load Game");
		dialog.setResizable(false);
		dialog.setIconImage(new ImageIcon(getClass().getResource("assets/icon.png")).getImage());
		
		JLabel message = new JLabel("There's no saved game to load");
		message.setFont(new Font("Arial", Font.PLAIN, 27));
		message.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel dialogPanel = new JPanel();
		dialogPanel.setBackground(ConstantUIValues.mainColor);
		dialogPanel.add(message);
		
		dialog.add(dialogPanel);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
	
	public void saveGameSuccess(JFrame window) {
		JDialog dialog = new JDialog(window, null, ModalityType.APPLICATION_MODAL);
		dialog.setTitle("Save Game");
		dialog.setResizable(false);
		dialog.setIconImage(new ImageIcon(getClass().getResource("assets/icon.png")).getImage());
		
		JLabel message = new JLabel("Game Saved Successfully!");
		message.setFont(new Font("Arial", Font.PLAIN, 27));
		message.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel dialogPanel = new JPanel();
		dialogPanel.add(message);
		
		dialog.add(dialogPanel);
		dialogPanel.setBackground(ConstantUIValues.mainColor);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
	
	public void rewriteSavePrompt(JFrame window, Player player, PowerUps powerUp) {
		JDialog dialog = new JDialog(window, null, ModalityType.APPLICATION_MODAL);
		dialog.setTitle("Save Game");
		dialog.setResizable(false);
		dialog.setIconImage(new ImageIcon(getClass().getResource("assets/icon.png")).getImage());
		
		JLabel message = new JLabel("Do you want to rewrite existing save?");
		message.setFont(new Font("Arial", Font.PLAIN, 27));
		message.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		String[] buttons = {"Yes", "No"};
		JButton[] button = new JButton[2];
		JPanel options = new JPanel();
		options.setBackground(ConstantUIValues.mainColor);
		
		for(int i = 0; i < button.length; i++) {
			button[i] = new JButton(buttons[i]);
			button[i].setFocusable(true);
	        button[i].setFocusPainted(false);
	        
	        button[i].setBackground(ConstantUIValues.buttonReady);
	        
	        Font current = button[i].getFont();
	        Font newFont = current.deriveFont(current.getSize() + 7f);
	        button[i].setFont(newFont);
	        
	        button[i].setBorder(BorderFactory.createCompoundBorder(
	        		BorderFactory.createLineBorder(Color.BLACK),
	        		BorderFactory.createEmptyBorder(10, 20, 10, 20)));
	        options.add(button[i]);
		}
		
		button[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				player.saveGame(player, powerUp);
				dialog.setVisible(false);
			}
		});
		
		button[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});
		
		JPanel dialogPanel = new JPanel(new GridLayout(2, 1));
		dialogPanel.setBackground(ConstantUIValues.mainColor);
		dialogPanel.add(message);
		dialogPanel.add(options);
		
		dialog.add(dialogPanel);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
	
	public void confirmExit(JFrame window) {
		JDialog dialog = new JDialog(window, "Exit", ModalityType.APPLICATION_MODAL);
		dialog.setResizable(false);
		dialog.setIconImage(new ImageIcon(getClass().getResource("assets/icon.png")).getImage());
		
		JLabel message = new JLabel("Do you really want to exit?");
		message.setFont(new Font("Arial", Font.PLAIN, 27));
		message.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		String[] buttons = {"Yes", "No"};
		JButton[] button = new JButton[2];
		JPanel options = new JPanel();
		options.setBackground(ConstantUIValues.mainColor);
		
		for(int i = 0; i < button.length; i++) {
			button[i] = new JButton(buttons[i]);
			button[i].setFocusable(true);
	        button[i].setFocusPainted(false);
	        
	        button[i].setBackground(ConstantUIValues.buttonReady);
	        
	        Font current = button[i].getFont();
	        Font newFont = current.deriveFont(current.getSize() + 7f);
	        button[i].setFont(newFont);
	        
	        button[i].setBorder(BorderFactory.createCompoundBorder(
	        		BorderFactory.createLineBorder(Color.BLACK),
	        		BorderFactory.createEmptyBorder(10, 20, 10, 20)));
	        options.add(button[i]);
		}
		
		button[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				System.exit(0);
			}
		});
		
		button[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});
		
		JPanel dialogPanel = new JPanel(new GridLayout(2, 1));
		dialogPanel.setBackground(ConstantUIValues.mainColor);
		dialogPanel.add(message);
		dialogPanel.add(options);
		
		dialog.add(dialogPanel);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
}