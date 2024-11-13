package backEnd;

import javax.swing.JLabel;
import java.io.*;

public class Player{

	public int gemCount = 0;
	public int clickCount = 0;
	public int perClick = 1;
	public int passiveIncome = 0;
	
	public JLabel clickCountLabel;
	public JLabel gemCountLabel;
	public JLabel clickerLabel;
	
	public Player() {
		
	}
	
	public void saveGame(Player player, PowerUps powerUp) {
		try(PrintWriter writer = new PrintWriter("playerData.json")) {
			writer.println(gemCount);
			writer.println(clickCount);
			writer.println(perClick);
			writer.println(passiveIncome);		
			
	        System.out.println("Game Saved Successfully!");
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("Error Saving Game!");
	    }
	}
	
	public void loadGame(Player player) {
	    try(BufferedReader reader = new BufferedReader(new FileReader("playerData.json"))) {
	    	gemCount = Integer.parseInt(reader.readLine());
	    	clickCount = Integer.parseInt(reader.readLine());
	    	perClick = Integer.parseInt(reader.readLine());
	    	passiveIncome = Integer.parseInt(reader.readLine());
	    	
	    	player.clickerLabel.setText("CLICK HERE FOR COINS! (+" + player.perClick + ")");
            player.clickCountLabel.setText("Coins: " + player.clickCount);
        	player.gemCountLabel.setText("Gems: " + player.gemCount);
	    	
	        System.out.println("Game Loaded Successfully!");
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("Error Loading Game!");
	        // Consider creating a new Player object with default values if loading fails
	    }
	}

}
