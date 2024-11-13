package frontEndGUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import backEnd.*;

public class MainWindow {
	
	private JFrame window;

	private JPanel mainPanel;
	private JPanel titleScreen;
	private JPanel inGamePanel;
	
	private CardLayout cardLayout;
	
	Player player = new Player();
	TitleScreen titleScreenButtons = new TitleScreen();
	Passives passive = new Passives();
	PowerUps powerUp = new PowerUps();
	
	public MainWindow() {
		//Main Game Window
		window = new JFrame();
		window.setTitle("Good Ol' Clicker Game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1280, 680);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
	    window.setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
	}
	
	//Open Game Window
	public void show() {
	    window.setVisible(true);
	}
	
	//Main Window Panel
	public void mainPanel() {
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
		titleScreen = new JPanel(new GridLayout(2,1));
		
		//Game Title
		JPanel gameTitle = new JPanel();
		
		//Buttons
		JPanel buttonArea = new JPanel();
		
		JButton newGame = titleScreenButtons.newGame(mainPanel, player, cardLayout);
		buttonArea.add(newGame);
		
		JButton loadGame = titleScreenButtons.loadSavedGame(mainPanel, player, cardLayout);
		buttonArea.add(loadGame);
		
		//Add to Title Screen
		titleScreen.add(gameTitle);
		titleScreen.add(buttonArea);
	}
	
	//Main Game Interface
	public void inGamePanel() {
		//Main Panel
		inGamePanel = new JPanel(new BorderLayout());
		
		//Currency Method
		currency(inGamePanel);
		
		//Center Panel
		CenterPanel centerPanelButtons = new CenterPanel();
		JPanel centerPanel = new JPanel(cardLayout);
		
		centerPanelButtons.powerUps(centerPanel, player, powerUp, cardLayout);
		centerPanelButtons.mainGameButtons(centerPanel, player, powerUp, cardLayout);
		
		cardLayout.show(centerPanel, "mainGameButtons");
		
		inGamePanel.add(centerPanel);
		
		//Clicker Method
		clicker(inGamePanel);
	}
	
	//Clicker Method
	private void clicker(JPanel panel) {
		JPanel clicker = new JPanel();
		clicker.setOpaque(false);
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
                player.clickCountLabel.setText("Coins: " + player.clickCount);
                
                if (player.clickCount == 100000) {
                	player.clickCount = 0;
                	player.gemCount++;
                    player.clickCountLabel.setText("Coins: " + player.clickCount);
                	player.gemCountLabel.setText("Gems: " + player.gemCount);
                }
                
                JButton increaseCoinValue = powerUp.increaseCoinValue();
                
                if (player.clickCount >= powerUp.powerUpOnePrice()
                		&& !powerUp.powerUpOneMaxLevel()) {
                	increaseCoinValue.setEnabled(true);
                } else {
                	increaseCoinValue.setEnabled(false);
                }
            }
        });
		panel.add(clicker, BorderLayout.SOUTH);
	}
	
	//Currency Method
	private void currency(JPanel panel) {
		//Currency
		JPanel currencyPanel = new JPanel();
		currencyPanel.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK),
			BorderFactory.createEmptyBorder(15, 0, 15, 0)));
		currencyPanel.setOpaque(false);
		
		//Coins
		player.clickCountLabel = new JLabel("Coins: 0");
		Font currentCountLabelFont = player.clickCountLabel.getFont();
		Font currencyFont = currentCountLabelFont.deriveFont(
				currentCountLabelFont.getSize() + 10f);
		player.clickCountLabel.setFont(currencyFont);
		
		JPanel coinsPanel = new JPanel();
		coinsPanel.setOpaque(false);
		coinsPanel.add(player.clickCountLabel);
		
		currencyPanel.add(coinsPanel, BorderLayout.CENTER);
		
		//Gems
		player.gemCountLabel = new JLabel("Gems: 0");
		player.gemCountLabel.setFont(currencyFont);
		
		JPanel gemsPanel = new JPanel();
		gemsPanel.setOpaque(false);
		gemsPanel.add(player.gemCountLabel);
		
		currencyPanel.add(gemsPanel, BorderLayout.CENTER);
		
		//Adds Currency to the Main Panel
		panel.add(currencyPanel, BorderLayout.NORTH);
	}
}
