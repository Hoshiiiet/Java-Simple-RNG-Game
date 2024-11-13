package backEnd;

import javax.swing.SwingUtilities;
import frontEndGUI.MainWindow;

public class MainClass {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainWindow window = new MainWindow();
				window.show();
				window.mainPanel();
			}
		});
		
	}

}
