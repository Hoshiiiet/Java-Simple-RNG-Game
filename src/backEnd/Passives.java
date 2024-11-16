package backEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Passives {
	
	public Passives() {
		
	}
	
	public void passiveIncome(Player player) {
		Timer passiveIncome = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Conversion convert = new Conversion(player);
				player.clickCount += player.passiveIncome;
				convert.coinConvert();
			}
		});
		
		if (player.passiveIncome != 0) passiveIncome.start();
	}

}
