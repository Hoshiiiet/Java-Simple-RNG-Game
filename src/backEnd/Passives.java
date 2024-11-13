package backEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Passives {
	
	public Passives() {
		
	}
	
	public void passiveIncome(Player player) {
		Timer passiveIncome = new Timer(10000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				player.clickCount += player.passiveIncome;
				player.clickCountLabel.setText("Coins: " + player.clickCount);
			}
		});
		passiveIncome.start();
	}

}
