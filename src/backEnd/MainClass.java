package backEnd;

import java.io.IOException;

import javax.swing.SwingUtilities;

import frontEndGUI.MainWindow;

public class MainClass {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run()  {
				
				try {
					MainWindow window = new MainWindow();
					window.show();
					window.mainPanel();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
