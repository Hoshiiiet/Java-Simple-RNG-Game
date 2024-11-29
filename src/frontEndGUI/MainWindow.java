package frontEndGUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import backEnd.*;

public class MainWindow {
	
	public JFrame window;

	private JPanel mainPanel;
	private JPanel titleScreen;
	private JPanel inGamePanel;
	
	private CardLayout cardLayout;
	
	Player player = new Player();
	TitleScreen titleScreenButtons = new TitleScreen();
	Passives passive = new Passives();
	PowerUps powerUp = new PowerUps(player);
	PopUpDialogs popUp = new PopUpDialogs();
	
	public MainWindow() throws IOException {
		//Main Game Window
		window = new JFrame();
		window.setTitle("Good Ol' Clicker Game");
		window.setSize(1280, 680);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		
		ImageIcon iconImage = new ImageIcon(".//assets//icon.png");
	    window.setIconImage(iconImage.getImage());
	   
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				popUp.confirmExit(window);
			}
		});
		
		player.setWindow(window);
	}
	
	//Open Game Window
	public void show() {
	    window.setVisible(true);
	}
	
	//Main Window Panel
	public void mainPanel() throws IOException{
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		window.add(mainPanel);
		
		titleScreen();
		inGamePanel();
		
		mainPanel.add(titleScreen, "titleScreen");
		mainPanel.add(inGamePanel, "inGamePanel");
		cardLayout.show(mainPanel, "titleScreen");
	}
	
	//Title Screen
	public void titleScreen() {
		//Title Screen Panel
		titleScreen = new JPanel();
		titleScreen.setBackground(ConstantUIValues.mainColor);
		
		//Game Title
		JPanel gameTitle = new JPanel();
		gameTitle.setPreferredSize(new Dimension(1280, 450));
		gameTitle.setBackground(ConstantUIValues.mainColor);
		
		JLabel title = new JLabel(new ImageIcon(".//assets//title.png"));
		gameTitle.add(title);
		
		//Buttons
		JPanel buttonArea = new JPanel();
		buttonArea.setBorder(BorderFactory.createEmptyBorder(18, 0, 0, 0));
		buttonArea.setBackground(ConstantUIValues.mainColor);
		
		JButton newGame = titleScreenButtons.newGame(mainPanel, player, cardLayout);
		buttonArea.add(newGame);
		
		JButton loadGame = titleScreenButtons.loadSavedGame(mainPanel, powerUp, player, cardLayout);
		buttonArea.add(loadGame);
		
		//Add to Title Screen
		titleScreen.add(gameTitle, BorderLayout.NORTH);
		titleScreen.add(buttonArea, BorderLayout.CENTER);
	}
	
	//Main Game Interface
	public void inGamePanel() throws IOException {
		//Main Panel
		inGamePanel = new JPanel(new BorderLayout());
		
		//Currency Method
		currency(inGamePanel);
		
		//Center Panel
		CenterPanel centerPanelButtons = new CenterPanel(powerUp);
		JPanel centerPanel = new JPanel(cardLayout);
		
		centerPanelButtons.mainGameButtons(centerPanel, player, cardLayout);
		
		cardLayout.show(centerPanel, "mainGameButtons");
		
		inGamePanel.add(centerPanel);
		
		//Clicker Method
		clicker(inGamePanel);
	}
	
	//Clicker Method
	private void clicker(JPanel panel) {
		JPanel clicker = new JPanel();
		clicker.setBackground(new Color(255, 180, 0));
		clicker.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
		clicker.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
		
		player.clickerLabel = new JLabel("CLICK HERE FOR COINS! (+" + player.perClick + ")");
		Font currentFont = player.clickerLabel.getFont();
		Font newFont = currentFont.deriveFont(currentFont.getSize() + 10f);
		player.clickerLabel.setFont(newFont);
		clicker.add(player.clickerLabel);
		
		clicker.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                player.clickCount += player.perClick;
                
                Conversion convert = new Conversion(player);
                convert.gemConvert();
                convert.coinConvert();
                
                //Power Up One Checking
                JButton increaseCoinValue = powerUp.increaseCoinValue();
                if (player.clickCount >= powerUp.powerUpOnePrice()
                		&& !powerUp.powerUpOneMaxLevel()) {
                	increaseCoinValue.setEnabled(true);
                } else {
                	increaseCoinValue.setEnabled(false);
                }
                
                if (increaseCoinValue.isEnabled()) increaseCoinValue.setBackground(ConstantUIValues.buttonReady);
                else increaseCoinValue.setBackground(ConstantUIValues.buttonDisabled);
                
                //Power Up Two Checking
                JButton addPassiveIncome = powerUp.addPassiveCoinIncome();
                if (player.clickCount >= powerUp.powerUpTwoPrice()
                		&& !powerUp.powerUpTwoMaxLevel()) {
                	addPassiveIncome.setEnabled(true);
                } else {
                	addPassiveIncome.setEnabled(false);
                }
                
                if (addPassiveIncome.isEnabled()) addPassiveIncome.setBackground(ConstantUIValues.buttonReady);
                else addPassiveIncome.setBackground(ConstantUIValues.buttonDisabled);
            }
        });
		panel.add(clicker, BorderLayout.SOUTH);
	}
	
	//Currency Method
	private void currency(JPanel panel) {
		//Currency
		JPanel currencyPanel = new JPanel();
		currencyPanel.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
			BorderFactory.createEmptyBorder(15, 0, 15, 0)));
		currencyPanel.setBackground(new Color(255, 180, 0));
		
		//Coins
		player.clickCountLabel = new JLabel("Coins: 0");
		Font currentCountLabelFont = player.clickCountLabel.getFont();
		Font currencyFont = currentCountLabelFont.deriveFont(
				currentCountLabelFont.getSize() + 12f);
		player.clickCountLabel.setFont(currencyFont);
		
		JPanel coinsPanel = new JPanel();
		coinsPanel.setOpaque(false);
		coinsPanel.add(player.clickCountLabel);
		
		currencyPanel.add(coinsPanel, BorderLayout.CENTER);
		
		//Gems
		player.gemCountLabel = new JLabel("Gems: 0");
		player.gemCountLabel.setFont(currencyFont);

		JLabel coinsToGem = new JLabel ("(20k coins = 1 gem)");
		Font currencySubFont = currencyFont.deriveFont(currencyFont.getSize() - 5f);
		coinsToGem.setFont(currencySubFont);
		coinsToGem.setForeground(new Color(84, 84, 84));
		
		JPanel gemsPanel = new JPanel();
		gemsPanel.setOpaque(false);
		gemsPanel.add(player.gemCountLabel);
		gemsPanel.add(coinsToGem);
		
		currencyPanel.add(gemsPanel, BorderLayout.CENTER);
		
		
		//Adds Currency to the Main Panel
		panel.add(currencyPanel, BorderLayout.NORTH);
	}
}
