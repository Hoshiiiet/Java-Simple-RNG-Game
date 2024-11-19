package backEnd;

public class Conversion {
	
	Player player;

	public Conversion(Player player) {
		this.player = player;
	}
	
	public void gemConvert() {
		if (player.clickCount == 20000) {
        	player.clickCount = 0;
        	player.gemCount++;
            coinConvert();
        	player.gemCountLabel.setText("Gems: " + player.gemCount);
        }
	}
	
	public void coinConvert() {
		if (player.clickCount >= 10000 && player.clickCount < 10100) {
			double format = player.clickCount / 1000;
			int roundedCoin = (int) format;
			String temp = String.format("Coins: " + roundedCoin + "k");
            player.clickCountLabel.setText(temp);
		} 
		else if (player.clickCount >= 10100) {
			double format = player.clickCount / 1000;
			String temp = String.format("Coins: %.1fk", format);
            player.clickCountLabel.setText(temp);
		} 
		else {
			int roundedCoin = (int) player.clickCount;
			player.clickCountLabel.setText("Coins: " + roundedCoin);
		}
	}
}
